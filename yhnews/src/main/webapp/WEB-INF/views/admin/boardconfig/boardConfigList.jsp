<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/comm/taglib.jsp"%>

<!-- 검색 -->
<%-- <fieldset>
<legend></legend>
<form:form commandName="theForm" action="userList.do" method="get">

	<div class="search_wrap">
		<form:select path="search_type" title="검색구분 선택">
		<form:option value="" label="선택"/>
		<form:option value="1" label="이름"/>
		<form:option value="2" label="아이디"/>
		</form:select>
		<form:input path="search_keyword" title="검색어 입력" cssClass="col25"/>
		<button type="submit" class="btn inverse btn_small"><span class="ico ico_search"></span>검색</button>
	</div>
</form:form>
</fieldset> --%>

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
	</colgroup>
	<thead>
		<tr>
			<th scope="col">번호</th>
			<th scope="col">게시판명</th>
			<th scope="col">게시판타입</th>
			<th scope="col">공지글 사용여부</th>
			<th scope="col">답글 사용여부</th>
			<th scope="col">편집기(에디터) 사용여부</th>
			<th scope="col">상태</th>
		</tr>
	</thead>
	<tbody>
		<c:choose>
		<c:when test="${!empty result}">
		<c:forEach var="data" items="${result}" varStatus="i">
			<tr>
				<td>${i.count}</td>
				<td><a href="boardConfigForm.do?board_id=<c:out value="${data.board_id}"/>"><c:out value="${data.board_name}"/></a></td>
				<td><c:out value="${data.board_type}"/></td>
				<td><c:out value="${data.notice_use_yn}"/></td>
				<td><c:out value="${data.reply_use_yn}"/></td>
				<td><c:out value="${data.editor_use_yn}"/></td>				
				<td>
					<c:choose>
					<c:when test="${data.status eq 'Y'}">
						<span class="btn btn_xsmall primary">사용</span>
					</c:when>
					<c:when test="${data.status eq 'N'}">
						<span class="btn btn_xsmall danger">미사용</span>
					</c:when>
					</c:choose>
				</td>
			</tr>
		</c:forEach>		
		</c:when>
		<c:otherwise>
			<tr><td colspan="7"><spring:message code="MSG.NO.DATA"/></td></tr>
		</c:otherwise>
		</c:choose>
	</tbody>
</table><!-- //table_basic -->

<div class="btn_wrap float_right">
	<a href="boardConfigForm.do" class="btn primary">등록</a>
</div>