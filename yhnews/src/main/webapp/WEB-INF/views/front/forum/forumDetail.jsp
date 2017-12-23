<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/comm/taglib.jsp"%>

<!-- 탭메뉴 -->
<jsp:include page="/WEB-INF/views/front/forum/incTabMenu.jsp"/>

<c:set var="content">
<c:choose>
<c:when test="${empty param.contTab || param.contTab eq '1'}">${forumInfo.content1}</c:when>
<c:when test="${param.contTab eq '2'}">${forumInfo.content2}</c:when>
<c:when test="${param.contTab eq '3'}">${forumInfo.content3}</c:when>
<c:when test="${param.contTab eq '5'}">${forumInfo.content4}</c:when>
</c:choose>
</c:set>

<c:out value="${content}" escapeXml="false"/>