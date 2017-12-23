<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@ include file="/WEB-INF/views/comm/taglib.jsp"%>

<!doctype html>
<html lang="ko">
<head>
    <tiles:insertAttribute name="header" flush="true"/>
</head>  
<body>
      
<div id="skip_nav">
	<a href="#tnb">주메뉴</a>
	<a href="#page_container">컨텐츠</a>
</div>

	<div class="wrapper">
		<header class="header">
	    	<tiles:insertAttribute name="top" flush="true"/>	    	
	    </header>      
		
		<div class="page_container <c:if test="${'index' eq linkAddress}">main</c:if>" id="page_container">
		
			<tiles:insertAttribute name="left" flush="true"/>
			
			<tiles:insertAttribute name="location" flush="true"/>
			
		    <div class="page_contents">	
		    	<tiles:insertAttribute name="content" flush="true"/>
		    </div>    
    	</div>      
   </div>
   
<div class="blind_black"></div>
 
<tiles:insertAttribute name="footer" flush="true"/>

</body>
</html>