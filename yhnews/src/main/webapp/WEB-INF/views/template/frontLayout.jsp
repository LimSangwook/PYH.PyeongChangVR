<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@ include file="/WEB-INF/views/comm/taglib.jsp"%>
		
<!doctype html>
<html lang="ko">
<head>
  <tiles:insertAttribute name="header" flush="true"/>
</head>  

<c:choose>
<c:when test="${depth1MenuInfo.menu_code eq 'notice'}">
<body class="page-notice" data-nav="notice">
</c:when>
<c:when test="${depth1MenuInfo.menu_code eq 'faq'}">
<body class="page-faq" data-nav="faq">
</c:when>
<c:otherwise>
<body>
</c:otherwise>
</c:choose>

<div class="wrap">
	<div id="skip-nav">
		<a href="http://www.yonhapnews.co.kr/">연합뉴스</a>
		<a href="#container">본문 바로가기</a>
		<a href="#nav">메뉴 바로가기</a>
	</div>
	<!-- 헤더영역 -->
	<tiles:insertAttribute name="top" flush="true"/>
	<!-- 헤더영역 -->
	
	<!-- 콘테이너 -->
	<div id="container" class="container">
		<!-- contents -->
		<tiles:insertAttribute name="content" flush="true"/>
        <!-- contents -->    
	</div>
	<!-- 콘테이너 -->
	
	<!-- 푸터영역 -->
		<tiles:insertAttribute name="footer" flush="true"/>
	<!-- 푸터영역 -->
</div>	
</body>
</html>