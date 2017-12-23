<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/comm/taglib.jsp"%>

<script type="text/javascript">
var act = "${theForm.act}";
$(function(){
	
	// 등록/수정
	$('.btnSave').on('click',function(){
		if(formValidate()){
			if(act == 'update'){
				if(!confirm('수정하시겠습니까?')){
					return;
				}
			} else {
				if(!confirm('등록하시겠습니까?')){
					return;
				}
			}
			$('#theForm').submit();
		}
	});
	
	// 삭제
	$(".btnDelete").on("click",function(){
		if(confirm('삭제하시겠습니까?')){
			$('#deleteForm').submit();
		}
	});
	
	// 아이디 중복확인
	$('.btnUserIdDoubleCheck').on('click',function(){
		var userId = $('#userId').val();		
		var params = { user_id : userId};
		if(!userId.isNvl()){
			alert('아이디를 입력해 주시기 바랍니다.');
			$('#userId').focus();
			return;
		}
		if(!userId.isValidId()){
			return;
		}
		ajaxCall(params,'userIdCheck.do');
		ajaxRes.success(function(result){				
			if(result.RESULT_CODE == 'SUCCESS'){					
				alert('사용 가능한 아이디 입니다.');
				$('#isUserIdDoubleCheck').val('Y');
			} else {
				alert('이미 사용중인 아이디 입니다.');
				$('#userId').val('');
				$('#isUserIdDoubleCheck').val('N');
			}						
		});
	});
	
	// 아이디 변경 시 중복확인
	$("#userId").on("change",function(){
		$("#isUserIdDoubleCheck").val("N");
	});
	
	// 비밀번호 변경
	$('#pwChange').on('change',function(){		
		if($(this).is(":checked")){
			$('input[type=password]').attr('disabled',false);	
		} else {
			$('input[type=password]').attr('disabled',true);
		}
	});
	
	if(act == 'update'){
		$('input[type=password]').attr('disabled',true);
	}	
	
	$('.btnBranchAdd').on('click',function(){
		ajaxCall(null,'museumListJson.do');
		ajaxRes.success(function(result){			
			//console.log(result)
			if(result.RESULT_CODE == 'SUCCESS'){
				var html=[], h=-1;				
				$(result.RESULT_DATA).each(function(i,data){
					//console.log(data)
					html[++h] = '<tr>';
					html[++h] = '<th scope="row"><input type="checkbox" name="itemCheck" museumNo="'+data.museum_no+'" museumName="'+data.museum_name+'" '+(data.status == 'Y'?"":"disabled")+'/></th>';
					html[++h] = '<td>';
					if(data.kind == '1'){
						html[++h] = '<span class="label primary">공립</span>';	
					}else{
						html[++h] = '<span class="label success">사립</span>';
					}					
					html[++h] = '</td>';
					html[++h] = '<td>'+data.museum_name+'</td>';
					html[++h] = '<td>';
					if(data.status == 'Y'){
						html[++h] = '<span class="label primary">사용중</span>';	
					}else{
						html[++h] = '<span class="label danger">미사용</span>';
					}					
					html[++h] = '</td>';
					html[++h] = '</tr>';
				});
				
				$('#museumListArea').html(html.join(''));
				// 팝업열기
				regist_view('branchAuthPop');
			}											
		});		
	});
	// 권한삭제
	$(document).on('click','.btnAuthDel',function(){
		$(this).parent().find('input[name=museumAuthStatusArr]').val('N');
		$(this).parent().hide();
	});
	// 권한추가
	$('.btnMuseumAuthAdd').on('click',function(){		
		var html=[], h=-1;
		var museumNo = "";
		$('input[name="itemCheck"]').each(function(){
			if($(this).is(':checked')){
				dataAddFlag = true;	
				museumNo = $(this).attr('museumNo');
				museumName = $(this).attr('museumName');
				$('#museumAuthMap').find('input[name=museumNoArr]').each(function(){
					if(museumNo == $(this).val()) dataAddFlag = false;
				});
				if(dataAddFlag){
					html[++h] = '<li>';
					html[++h] = '<input type="hidden" name="museumAuthStatusArr" value="Y"/>';
					html[++h] = '<input type="hidden" name="museumNoArr" value="'+museumNo+'"/>'+museumName+'<span class="ico ico_close btnAuthDel" style="cursor:pointer;">삭제</span>';					
					html[++h] = '<li>';
				}	
			}				
		});
		if(museumNo.isNvl()){
			$('#museumAuthMap').append(html.join(''));
			regist_close('branchAuthPop');
		} else {
			alert('박물관을 선택해 주시기바랍니다.');
		}		
	});
});
</script>

<fieldset>
<legend></legend>

<!-- delete form start -->
<form:form id="deleteForm" commandName="theForm" action="siteSave.do">
<form:hidden path="act" id="deleteAct" value="delete"/>
<form:hidden path="user_id"/>
</form:form>

<form:form commandName="theForm" action="vrSiteSave.do">
<form:hidden path="act"/>
<c:if test="${theForm.search_type eq 'member'}">
<!-- 일반회원관리 -->
<form:hidden path="auth_level" value="1"/>
</c:if>
<input type="hidden" id="isUserIdDoubleCheck"/>

	<table class="table_form">
		<caption>사용자 정보</caption>
		<colgroup>
			<col style="width:20%;" />
			<col style="width:80%;" />
		</colgroup>
		<tbody>
			<tr>
				<th scope="row">상태</th>
				<td>
					<form:select path="status" title="상태" isNullCheck="true">
						<form:option value="" label="선택"/>
						<form:option value="Y" label="사용"/>
						<form:option value="N" label="차단"/>
					</form:select>
				</td>
			</tr>			
			<c:if test="${theForm.search_type eq 'admin'}">
			<tr>
				<th scope="row">권한레벨</th>
				<td>
					<form:select path="auth_level" title="권한레벨">
						<form:option value="" label="선택"/>
						<c:forEach var="data" items="${userAuthLevelCodeList}">
						<c:if test="${data.code > 1}">
							<form:option value="${data.code}" label="${data.code_name}"/>
						</c:if>
						</c:forEach>
					</form:select>
				</td>
			</tr>
			</c:if>	
			<tr>
				<th scope="row">이름</th>
				<td><form:input path="user_name" title="이름" cssClass="col16" isNullCheck="true" maxlength="20"/></td>
			</tr>
			<tr>
				<th scope="row">아이디</th>
				<td>
					<c:choose>
					<c:when test="${empty theForm.user_id}">
						<p><form:input path="user_id" id="userId" title="아이디" cssClass="col16" isNullCheck="true" maxlength="20"/> <a href="#" class="btn btn_small btnUserIdDoubleCheck">중복확인</a></p>
					</c:when>
					<c:otherwise>
						<form:hidden path="user_id"/>
						<c:out value="${theForm.user_id}"/>					
					</c:otherwise>
					</c:choose>
				</td>
			</tr>
			<tr>
				<th scope="row">비밀번호</th>
				<td>
					<form:password path="passwd" title="비밀번호" cssClass="col16" isNullCheck="true" equalsTarget="passwdConfirm" maxlength="20"/>
					<c:if test="${theForm.act eq 'update'}">
					<input type="checkbox" id="pwChange"/> <label for="pwChange">비밀번호 변경</label>
					</c:if>
				</td>
			</tr>
			<tr>
				<th scope="row">비밀번호확인</th>
				<td><input type="password" id="passwdConfirm" title="비밀번호확인" class="col16" isNullCheck="true" maxlength="20"/></td>
			</tr>			
			<tr>
				<th scope="row">이메일</th>
				<td>
					<form:input path="email" title="이메일" cssClass="col16" isNullCheck="true" isEmailCheck="true" maxlength="100"/>
				</td>
			</tr>
			<tr>
				<th scope="row">전화번호</th>
				<td>
					<form:input path="phone1" title="전화번호" value="${theForm.phoneArr[0]}" cssClass="col16 numberOnly" isNullCheck="true" isNumberCheck="true" maxlength="4"/> -
					<form:input path="phone2" title="전화번호" value="${theForm.phoneArr[1]}" cssClass="col16 numberOnly" isNullCheck="true" isNumberCheck="true" maxlength="4"/> - 
					<form:input path="phone3" title="전화번호" value="${theForm.phoneArr[2]}" cssClass="col16 numberOnly" isNullCheck="true" isNumberCheck="true" maxlength="4"/>
				</td>
			</tr>	
		</tbody>
	</table>


		
	<div class="btn_wrap textAlign_center">
		<a href="userList.do" class="btn white">취소</a>
		<c:choose>
		<c:when test="${theForm.act eq 'update'}">
			<a href="#" class="btn danger btnDelete">삭제</a>
			<a href="#" class="btn primary btnSave">수정</a>
		</c:when>
		<c:otherwise>
			<a href="#" class="btn primary btnSave">등록</a>
		</c:otherwise>
		</c:choose>		
	</div>


<div id="branchAuthPop" class="bbs_form popup_wrap">
	<div class="popup_header">박물관 권한 설정</div>
	<a class="ico ico_close">창닫기</a>
	<div class="popup_body">

		<fieldset>
		<legend></legend>
		
		<table class="table_basic">
			<caption>테이블</caption>
			<colgroup>		
				<col style="width:100px;" />
				<col style="width:100px;" />
				<col style="width:auto;" />
				<col style="width:auto;" />
			</colgroup>
			<thead>
				<tr>
					<th scope="col">선택</th>		
					<th scope="col">유형</th>	
					<th scope="col">박물관명</th>
					<th scope="col">상태</th>
				</tr>
			</thead>
			<tbody id="museumListArea">
		
			</tbody>
		</table>
		
		<div class="btn_wrap clearfix textAlign_center com-mt10">			
			<a href="#" class="btn primary btnMuseumAuthAdd">등록</a>
		</div>
		
		</fieldset>
	</div>
</div>
</form:form>

</fieldset>