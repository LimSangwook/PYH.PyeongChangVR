<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/comm/taglib.jsp"%>

<table class="table_form">
	<caption>기본정보</caption>
	<colgroup>
		<col style="width:20%;" />
		<col style="width:80%;" />
	</colgroup>
	<tbody>
		<tr>
			<th scope="row">개설일</th>
			<td>${openDay}</td>
		</tr>
		<%-- <tr>
			<th scope="row">회원수</th>
			<td><fmt:formatNumber value="${memberTotalCount}" />명</td>
		</tr> --%>
		<tr>
			<th scope="row">작성글</th>
			<td><fmt:formatNumber value="${postTotalCount}" />건</td>
		</tr>
		<tr>
			<th scope="row">답변수</th>
			<td><fmt:formatNumber value="${replyTotalCount}" />건</td>
		</tr>
		<tr>
			<th scope="row">사용량</th>
			<td>${totalUseSize}(파일 : ${fileUseSize}, DB : ${dataBaseUseSize}) </td>
		</tr>
	</tbody>
</table>