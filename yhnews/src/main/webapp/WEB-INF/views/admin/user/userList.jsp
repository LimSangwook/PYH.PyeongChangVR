<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/comm/taglib.jsp"%>

<!-- 검색 -->
<fieldset>
<legend></legend>
<form:form commandName="theForm" action="userList.do" method="get">

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

<c:set var="orderType">
<c:choose>
<c:when test="${theForm.order_type eq 'D'}">A</c:when>
<c:otherwise>D</c:otherwise>
</c:choose>
</c:set>
<c:set var="colspan" value="6"/>

<table class="table_basic">
	<caption>테이블</caption>
	<colgroup>		
		<col style="width:auto;" />
		<col style="width:auto;" />
		<col style="width:auto;" />
		<col style="width:auto;" />
		<c:if test="${theForm.search_type eq 'admin'}">
		<col style="width:auto;" />
		<c:set var="colspan" value="7"/>
		</c:if>						
		<col style="width:auto;" />
		<col style="width:auto;" />
	</colgroup>
	<thead>
		<tr>
			<th scope="col">번호 <a href="userList.do?order_column=1&order_type=${orderType}${defaultParameter}" class="ico ico_sort">정렬</a></th>			
			<th scope="col">이름 <a href="userList.do?order_column=2&order_type=${orderType}${defaultParameter}" class="ico ico_sort">정렬</a></th>
			<th scope="col">아이디 <a href="userList.do?order_column=3&order_type=${orderType}${defaultParameter}" class="ico ico_sort">정렬</a></th>			
			<th scope="col">이메일</th>
			<c:if test="${theForm.search_type eq 'admin'}">
			<th scope="col">권한구분</th>			
			</c:if>			
			<th scope="col">등록일자</th>			
			<th scope="col">상태</th>
		</tr>
	</thead>
	<tbody>
		<c:choose>
		<c:when test="${!empty result}">
		<c:forEach var="data" items="${result}" varStatus="i">
		<c:set var="num" value="${theForm.total_count - ((theForm.page - 1) * theForm.pageSize) - (i.count - 1)}" />
			<tr>
				<td>${num}</td>				
				<td><a href="userForm.do?user_id=${data.user_id}${defaultParameter}"><c:out value="${data.user_name}"/></a></td>
				<td><c:out value="${data.user_id}"/></td>
				<td><c:out value="${data.email}"/></td>
				
				<c:if test="${theForm.search_type eq 'admin'}">
				<td>
					<!-- 권한구분 -->
					<c:forEach var="auth" items="${userAuthLevelCodeList}">
						<c:if test="${data.auth_level eq auth.code}">
						<c:choose>
						<c:when test="${data.auth_level eq '9' }">
							<em class="vip"><c:out value="${auth.code_name}"/></em>							
						</c:when>
						<c:otherwise>
							<c:out value="${auth.code_name}"/>
						</c:otherwise>
						</c:choose>
						</c:if>
					</c:forEach>
					<!--// 권한구분 -->
				</td>
				</c:if>
								
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
			<tr><td colspan="${colspan}"><spring:message code="MSG.NO.DATA"/></td></tr>
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

<div class="btn_wrap float_right">
	<a href="userForm.do" class="btn primary">등록</a>
</div>