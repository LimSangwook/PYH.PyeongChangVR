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
	</colgroup>
	<thead>
		<tr>
			<th scope="col">번호</th>
			<th scope="col">콘텐츠제목</th>
			<th scope="col">상태</th>
			<th scope="col">등록일자</th>
		</tr>
	</thead>
	<tbody>
		<c:choose>
		<c:when test="${!empty result}">
		<c:forEach var="data" items="${result}" varStatus="i">
			<tr>
				<td>${i.count}</td>
				<td class="textAlign_left"><a href="htmlForm.do?page_seq=<c:out value="${data.page_seq}"/>"><c:out value="${util:crop(data.title,100,'...','UTF-8')}"/></a></td>
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
				<td><fmt:formatDate value="${data.reg_date}" pattern="yyyy.MM.dd"/> </td>
			</tr>
		</c:forEach>		
		</c:when>
		<c:otherwise>
			<tr><td colspan="4"><spring:message code="MSG.NO.DATA"/></td></tr>
		</c:otherwise>
		</c:choose>
	</tbody>
</table><!-- //table_basic -->

<div class="btn_wrap float_right">
	<a href="htmlForm.do" class="btn primary">등록</a>
</div>