<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/comm/taglib.jsp"%>

<aside class="aside" <c:if test="${'index' eq linkAddress}">style="display:none;"</c:if>>
	<h2><span class="ico ico_title1"></span>${currMenuInfo.menu_name}</h2>
	<nav class="lnb">
		<c:forEach var="step1" items="${leftMenuList}">
			<c:if test="${step1.menu_code eq depth1MenuInfo.menu_code}">
				<c:forEach var="step2" items="${step1.children_menu_list}" varStatus="i">
				
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
	    		
	    		<!-- depth2 메뉴권한 -->
	    		<c:if test="${fn:indexOf(step2.menu_auth_level,GLOBAL_USER_AUTH) != -1 or GLOBAL_USER_AUTH eq 9}">
    			<li>    				
					<a href="${step2LinkAddress}" ${step2Target} <c:if test="${step2.menu_code eq depth2MenuInfo.menu_code and empty param.museum_no}">class="on"</c:if>><span class="ico ico_setting"></span><c:out value="${step2.menu_name}"/><span class="ico ico_arrow"></span></a>   				
    				<c:if test="${fn:length(step2.children_menu_list) > 0 }">
    					<ul>
    						<c:forEach var="step3" items="${step2.children_menu_list}" varStatus="k">
    							<!-- 연결주소세팅 -->
    							<c:choose>
					    		<c:when test="${empty step3.link_address}"><c:set var="step3LinkAddress" value="#"/></c:when>
					    		<c:when test="${fn:indexOf(step3.link_address,'javascript:') != -1 or fn:indexOf(step3.link_address,'http') != -1}"><c:set var="step3LinkAddress" value="${step3.link_address}"/></c:when>
					    		<c:otherwise><c:set var="step3LinkAddress" value="/siteManage${step3.link_address}"/></c:otherwise>
					    		</c:choose>
					    		<c:choose>
					    		<c:when test="${step3.is_new_window eq 'Y'}"><c:set var="step3Target" value="target='_blank'"/></c:when>
					    		<c:otherwise><c:set var="step3Target" value=""/></c:otherwise>
					    		</c:choose>					    		
    							<li><a href="${step3LinkAddress }" ${step3Target} <c:if test="${step3.menu_code eq depth3MenuInfo.menu_code}">class="on"</c:if>><c:out value="${step3.menu_name}"/></a></li>
    						</c:forEach>
    					</ul>    				
    				</c:if>
    			</li>
    			</c:if>
    			
    			</c:forEach>
			</c:if>
		</c:forEach>
		<c:if test="${depth1MenuInfo.menu_code eq 'museumManage'}">
		<!-- 박물관 관리일때 - 박물관 목록 및 메뉴 노출 - 최고관리자 및 브랜치 관리자만 허용 -->
		<c:forEach var="data" items="${museumAdminLeftMenu.museumList}">
		<c:if test="${data.isAuthCheck eq 'Y'}">
			<li>
				<a href="museumForm.do?museum_no=${data.museum_no}" <c:if test="${data.museum_no eq param.museum_no}">class="on"</c:if>>
				<span class="ico ico_setting"></span>
				${data.museum_name}
				<%-- <c:choose>
				<c:when test="${data.status eq 'Y'}">-사용</c:when>
				<c:otherwise>-미사용</c:otherwise>
				</c:choose>		 --%>		
				<span class="ico ico_arrow"></span>
				</a>			
			</li>
		</c:if>		
		</c:forEach>
		</c:if>
		
	</nav><!-- //lnb -->
	<a href="#openclose" class="btn_openclose">Close</a>
</aside><!-- //aside -->