<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/comm/taglib.jsp"%>

<div class="navigation_wrap">
	<h3>${depth1MenuInfo.menu_name} <c:if test="${linkAddress eq 'index'}">Dashboard</c:if><c:if test="${!empty currMenuInfo}">/ <strong>${currMenuInfo.menu_name}</strong></c:if></h3>
	<div class="navigation">
		<a href="/siteManage/basicset/basic/basicInfo/basicInfo.do">Home</a> 
		<c:forEach var="data" items="${menuLocationList}" varStatus="i">
		
			<!-- 연결주소세팅 -->
			<c:choose>
	    		<c:when test="${empty data.link_address}"><c:set var="linkAddress" value="#"/></c:when>
	    		<c:when test="${fn:indexOf(data.link_address,'javascript:') != -1 or fn:indexOf(data.link_address,'http') != -1}"><c:set var="linkAddress" value="${data.link_address}"/></c:when>
	    		<c:otherwise><c:set var="linkAddress" value="/siteManage${data.link_address}"/></c:otherwise>
    		</c:choose>
    		<c:choose>
	    		<c:when test="${data.is_new_window eq 'Y'}"><c:set var="target" value="target='_blank'"/></c:when>
	    		<c:otherwise><c:set var="target" value=""/></c:otherwise>
    		</c:choose>
    		
			<c:choose>
				<c:when test="${fn:length(menuLocationList) eq i.count}">&gt; <span>${data.menu_name}</span></c:when>
				<c:otherwise>&gt; <a href="${linkAddress}" ${target}>${data.menu_name}</a></c:otherwise>
			</c:choose>		
				
		</c:forEach>
	</div><!-- //navigation -->
</div>