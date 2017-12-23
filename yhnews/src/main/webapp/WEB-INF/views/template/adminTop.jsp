<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/comm/taglib.jsp"%>

<h1><a href="/siteManage/basic/basicInfo/basicInfo.do"><img src="/assets/admin/img/logo.png" alt=""></a></h1>
<nav class="tnb" id="tnb">
	<c:forEach var="step1" items="${leftMenuList}">
	
	
	
	<c:choose>
	<c:when test="${GLOBAL_USER_AUTH eq 7 and step1.menu_code eq 'museumManage'}"><c:set var="step1LinkAddress" value="/siteManage/museumManage/museumForm.do?museum_no=${GLOBAL_USER_MUSEUM_NO}"/></c:when>
	<c:when test="${empty step1.link_address}"><c:set var="step1LinkAddress" value="#"/></c:when>
	<c:when test="${fn:indexOf(step1.link_address,'javascript:') != -1 or fn:indexOf(step1.link_address,'http') != -1}"><c:set var="step1LinkAddress" value="${step1.link_address}"/></c:when>
	<c:otherwise><c:set var="step1LinkAddress" value="/siteManage${step1.link_address}"/></c:otherwise>
	</c:choose>
	
	<c:choose>
	<c:when test="${step1.is_new_window eq 'Y'}"><c:set var="step1Target" value="target='_blank'"/></c:when>
	<c:otherwise><c:set var="step1Target" value=""/></c:otherwise>
	</c:choose>
	
	<c:choose>
	<c:when test="${step1.menu_code eq 'basic'}"><c:set var="menuIcon" value="ico_tnb1"/></c:when>
	<c:when test="${step1.menu_code eq 'user'}"><c:set var="menuIcon" value="ico_tnb2"/></c:when>
	<c:when test="${step1.menu_code eq 'portalManage'}"><c:set var="menuIcon" value="ico_tnb3"/></c:when>
	<c:when test="${step1.menu_code eq 'forumManage'}"><c:set var="menuIcon" value="ico_tnb4"/></c:when>	
	<c:when test="${step1.menu_code eq 'board'}"><c:set var="menuIcon" value="ico_tnb5"/></c:when>
	<c:when test="${step1.menu_code eq 'museumManage'}"><c:set var="menuIcon" value="ico_tnb1"/></c:when>	
	<c:otherwise><c:set var="menuIcon" value="ico_tnb1"/></c:otherwise>
	</c:choose>
	
	<!-- depth1 메뉴권한 -->
	<c:if test="${(fn:indexOf(step1.menu_auth_level,GLOBAL_USER_AUTH) != -1 or GLOBAL_USER_AUTH eq 9) and step1.parent_menu_code eq 'admin'}">
	<li class="depth1_tnb">
		<a href="${step1LinkAddress}" ${step1Target} <c:if test="${depth1MenuInfo.menu_code eq step1.menu_code}">class="on"</c:if>><c:out value="${step1.menu_name}"/></a>
		
		<c:forEach var="step2" items="${step1.children_menu_list}" varStatus="i">
			<c:if test="${i.index eq 0}">
	    		<ul class="snb" id="${step1.menu_code}">		
	    	</c:if>
			<!-- depth2 메뉴권한 -->
    		<c:if test="${fn:indexOf(step2.menu_auth_level,GLOBAL_USER_AUTH) != -1 or GLOBAL_USER_AUTH eq 9}">
				<!-- 연결주소세팅 -->
				<c:choose>
	    		<c:when test="${empty step2.link_address}"><c:set var="step2LinkAddress" value="#"/></c:when>
	    		<c:when test="${fn:indexOf(step2.link_address,'javascript:') != -1 or fn:indexOf(step2.link_address,'http') != -1}"><c:set var="step2LinkAddress" value="${step2.link_address}"/></c:when>
	    		<c:otherwise><c:set var="step2LinkAddress" value="/siteManage${step2.link_address}"/></c:otherwise>
	    		</c:choose>
	    		<c:choose>
	    		<c:when test="${step2.is_new_window eq 'Y'}"><c:set var="step2Target" value="target='_blank'"/></c:when>
	    		<c:otherwise><c:set var="step2Target" value=""/></c:otherwise>
	    		</c:choose>
	    			    		    				
				<li><a href="${step2LinkAddress}" ${step2Target}><c:out value="${step2.menu_name}"/></a></li>				
				
    		</c:if>
    		
    		<c:if test="${fn:length(step1.children_menu_list) eq i.count}">
				</ul>
			</c:if>
	    </c:forEach>
						
	</li>
	</c:if>
		
	</c:forEach>
	
</nav><!-- //tnb -->


<script>
$(function(){
	//$('#museumManage').append($.trim('${addMuseumLinkData}'));
});
</script>

<div class="admin_info">
	<span class="admin_name">${GLOBAL_USER_NAME}(${GLOBAL_USER_ID})</span>
	<div class="admin_menu">
		<ul>
			<li><a href="#" onclick="fnLogout()" class="ico_logout">로그아웃</a></li> 
			<li><a href="/front/index.do" target="_blank" class="ico_homepage">홈페이지</a></li>
			<li><a href="#" class="ico_manual">매뉴얼</a></li>
		</ul>
	</div><!-- //admin_menu -->
</div><!-- //admin_info-->




