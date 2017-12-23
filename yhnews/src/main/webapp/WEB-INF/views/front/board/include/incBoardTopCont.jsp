<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/comm/taglib.jsp"%>

<c:if test="${!empty boardConfig.top_content}">
	<c:out value="${boardConfig.top_content}" escapeXml="false"/>
</c:if>

<c:if test="${depth1MenuInfo.menu_code eq 'notice'}">
	<section class="section-guide01 guide-type01 sub-visual04">
		<div>
			<h1>공지사항</h1>
		</div>
	</section>
</c:if>

<c:if test="${depth1MenuInfo.menu_code eq 'faq'}">
	<section class="section-guide01 guide-type01 sub-visual05">
		<div>
			<h1>자주묻는질문</h1>
		</div>
	</section>
</c:if>