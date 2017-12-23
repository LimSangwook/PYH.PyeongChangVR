<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/comm/taglib.jsp"%>

<!-- 카테고리영역 -->
<c:if test="${boardConfig.cate_use_yn eq 'Y'}">
<c:set var="addTabClass">
<c:choose>
<c:when test="${menuCode eq 'event'}">menu_3</c:when>
<c:when test="${menuCode eq 'reserve'}">menu_3</c:when>
</c:choose>
</c:set>
<div class="tabMenu ${addTabClass}">
	<ul>
		<c:forEach var="data" items="${categoryList}">
		<li><a href="boardList.do?cate_id=${data.cate_id}" <c:if test="${data.cate_id eq theForm.cate_id}">class="on"</c:if>>${data.cate_name}</a></li>
		</c:forEach>
	</ul>
</div>
</c:if>