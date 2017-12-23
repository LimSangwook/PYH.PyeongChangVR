<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/comm/taglib.jsp"%>

<c:if test="${!empty boardConfig.bottom_content}">
	<c:out value="${boardConfig.bottom_content}" escapeXml="false"/>
</c:if>

<c:if test="${depth1MenuInfo.menu_code eq 'brand'}">
</div>
</c:if>