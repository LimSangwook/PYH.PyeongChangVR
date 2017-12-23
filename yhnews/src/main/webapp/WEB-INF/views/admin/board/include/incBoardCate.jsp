<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/comm/taglib.jsp"%>

<!-- 카테고리영역 -->
<c:if test="${boardConfig.cate_use_yn eq 'Y' && fn:length(categoryList) > 0}">
<ul class="tabMenu menu_${fn:length(categoryList)+1}">
	<li><a href="boardList.do" <c:if test="${empty theForm.cate_id}">class="active"</c:if>><span>전체</span></a></li>
	<c:forEach var="data" items="${categoryList}">
	<li><a href="boardList.do?cate_id=${data.cate_id}" <c:if test="${data.cate_id eq theForm.cate_id}">class="active"</c:if>><span>${data.cate_name}</span></a></li>
	</c:forEach>
</ul>
</c:if>

