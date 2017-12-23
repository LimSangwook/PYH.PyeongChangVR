<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/comm/taglib.jsp"%>

<%-- <c:if test="${boardConfig.write_limit <= GLOBAL_USER_AUTH}">
<div class="btn_wrap textAlign_right">
	<a href="boardForm.do" class="btn btn_basic">등록</a>
</div>
</c:if> --%>

<c:if test="${siteGubun ne 'museum'}">
<div class="btn_wrap text_right">
	<a href="boardForm.do?cate_id=${theForm.cate_id}${defaultParameter}" class="btn btn_basic">등록</a>
</div>
</c:if>
