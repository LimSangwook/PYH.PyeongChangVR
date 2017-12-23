<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/comm/taglib.jsp"%>

<!DOCTYPE html>
<html lang="ko">
<head>
	<jsp:include page="/WEB-INF/views/template/adminHeader.jsp"/>
	<!-- iCheck -->
	<link rel="stylesheet" href="/assets/plugin/iCheck/all.css">
	<script src="/assets/plugin/iCheck/icheck.min.js"></script>
	<script type="text/javascript">
	$(function(){
		
		// 체크박스 디자인 셋
		$('input').iCheck({
		    checkboxClass: 'icheckbox_flat-red'	    
		});
		
		// 사용자 선택 시 데이터 가공 
		/* $('input[name="userCheck"]').on('ifToggled',function(){		
			var lang = $(this).val();
			if($(this).is(':checked')){
				
			} else {
				
			}
		}); */
		
		// 선택완료
		$('.btnExecute').on('click',function(){
			userData = "";
			$('input[name="userCheck"]').each(function(){
				if($(this).is(':checked')){
					userData += userData?','+$(this).attr('userData'):$(this).attr('userData');
					//console.log($(this).attr('userData'))	
				}				
			});
			if(userData.isNvl()){
				$(opener.location).attr('href','javascript:setSmsReceiveUser("${param.smsKind}","'+userData+'");');				
				self.close();	
			} else {
				alert('SMS 발송대상을 선택해 주시기바랍니다.');
			}
			
		});		
		
	});
	</script>
</head>

<body>
<div class="newpopup_wrap ">
		<div class="popup_header">SMS 발송 대상 찾기</div>
		<a href="#close" class="ico ico_close" onclick="window.close(); return false;">창닫기</a>
		<div class="popup_body">
		
		<!-- 검색 -->
		<fieldset>
		<legend></legend>
		<form:form commandName="theForm" action="userFindListPop.do" method="get">
		
			<div class="search_wrap">
				<form:select path="search_column" title="검색구분 선택">
				<form:option value="" label="선택"/>
				<form:option value="1" label="이름"/>
				<form:option value="2" label="아이디"/>
				</form:select>
				<form:input path="search_keyword" title="검색어 입력" cssClass="col25"/>
				<button type="submit" class="btn inverse btn_small"><span class="ico ico_search"></span>검색</button>
			</div>
		</form:form>
		</fieldset>
		
		<table class="table_basic">
			<caption>테이블</caption>
			<colgroup>		
				<col style="width:auto;" />
				<col style="width:auto;" />
				<col style="width:auto;" />
				<col style="width:auto;" />
				<col style="width:auto;" />
				<col style="width:auto;" />						
				<col style="width:auto;" />
				<col style="width:auto;" />
			</colgroup>
			<thead>
				<tr>
					<th scope="col">선택</th>			
					<th scope="col">이름</th>
					<th scope="col">아이디</th>			
					<th scope="col">휴대폰</th>
					<th scope="col">권한구분</th>
					<th scope="col">이용업체명</th>
					<th scope="col">등록일자</th>			
					<th scope="col">상태</th>
				</tr>
			</thead>
			<tbody>
				<c:choose>
				<c:when test="${!empty result}">
				<c:forEach var="data" items="${result}" varStatus="i">
				<c:set var="authName" value=""/>
					<!-- 권한구분 -->
					<c:forEach var="auth" items="${userAuthLevelCodeList}">
						<c:if test="${data.auth_level eq auth.code}">
							<c:set var="authName" value="${auth.code_name}"/>
						</c:if>
					</c:forEach>
					<!--// 권한구분 -->				
					<tr>
						<td><input type="checkbox" name="userCheck" userData="${data.user_id}|${data.user_name}|${authName}"/></td>				
						<td><c:out value="${data.user_name}"/></td>
						<td><c:out value="${data.user_id}"/></td>
						<td><c:out value="${data.phone}"/></td>
						<td><c:out value="${auth.authName}"/></td>
						<td>
							<!-- 가맹점명 -->
							<c:forEach var="vo" items="${franchiseStatusList}">
							<c:if test="${vo.franchise_key eq data.franchise_key}">
								<span class="btn white btn_xsmall"><c:out value="${vo.franchise_name}"/></span>
							</c:if>
							</c:forEach>
							<!--// 가맹점명 -->
						</td>		
						<td><fmt:formatDate value="${data.reg_date}" pattern="yyyy.MM.dd"/></td>
						<td>
							<c:choose>
							<c:when test="${data.status eq 'Y'}">
								<span class="btn btn_xsmall primary">정상</span>
							</c:when>
							<c:when test="${data.status eq 'N'}">
								<span class="btn btn_xsmall success">차단</span>
							</c:when>
							</c:choose>
						</td>
					</tr>
				</c:forEach>		
				</c:when>
				<c:otherwise>
					<tr><td colspan="8"><spring:message code="MSG.NO.DATA"/></td></tr>
				</c:otherwise>
				</c:choose>
			</tbody>
		</table><!-- //table_basic -->
		
		<!-- 페이지 -->
		<div class="pagenate_wrap float_left">
			<ul class="pagenation">
				<c:out value="${pageNavigation}" escapeXml="false"/>
			</ul>
		</div><!-- //pagenate_wrap -->		
				
	</div>
</div>

	<div class="btn_wrap textAlign_center">
		<a href="#close" class="btn" onclick="window.close(); return false;">취소</a>
		<button class="btn primary btnExecute">선택완료</button>
	</div>
		
</body>
</html><!-- //하단부분 -->