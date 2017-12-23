<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/comm/taglib.jsp"%>

<c:set var="visualSubClass">
<c:choose>
<c:when test="${depth1MenuInfo.menu_code eq 'library'}">visualSub_library</c:when>
<c:when test="${depth1MenuInfo.menu_code eq 'collection'}">visualSub_collection</c:when>
<c:when test="${depth1MenuInfo.menu_code eq 'notify'}">visualSub_bbs</c:when>
<c:when test="${depth1MenuInfo.menu_code eq 'about'}">visualSub_about</c:when>
<c:otherwise>visualSub_etc</c:otherwise>
</c:choose>
</c:set>

      <section class="gnb_section"> <!-- gnb-section> -->
        <div class="max1300">
          <div class="gnb_list">
            <ul>
              <li><a href=""><span class="home_ico"></span>HOME</a></li>
              <li>
                <a href="#a" class="first_gnb">연구활동<span class="gnb_ico"></span></a>
                <ul class="depth_gnb first_gnb_depth" style="display:none">
                  <li><a href="">발간물</a></li>
                  <li><a href="">정보마당</a></li>
                  <li><a href="">고객마당</a></li>
                  <li><a href="">알림마당</a></li>
                  <li><a href="">연구원소개</a></li>
                </ul>
              </li>
              <li>
                <a href="#a" class="second_gnb">탄소배출권<span class="gnb_ico"></span></a>
                <ul class="depth_gnb second_gnb_depth" style="display:none">
                  <li><a href="">기후변화적응</a></li>
                  <li><a href="">탄소배출권</a></li>
                  <li><a href="">온실가스감축</a></li>
                  <li><a href="">에너지효율</a></li>
                  <li><a href="">국제협력</a></li>
                  <li><a href="">기후변화교육</a></li>
                  <li><a href="">연구과제수행</a></li>
                </ul>
              </li>
            </ul>
          </div>
        </div>
      </section>



<!-- div class="${visualSubClass}">
<nav class="snb">
<ul>
	<c:forEach var="step1" items="${leftMenuList}">
		<c:if test="${step1.menu_code eq depth1MenuInfo.menu_code}">
			<c:forEach var="step2" items="${step1.children_menu_list}" varStatus="i">
			
				<c:choose>
	    		<c:when test="${empty step2.link_address}"><c:set var="step2LinkAddress" value="#"/></c:when>
	    		<c:when test="${fn:indexOf(step2.link_address,'javascript:') != -1 or fn:indexOf(step2.link_address,'http') != -1}"><c:set var="step2LinkAddress" value="${step2.link_address}"/></c:when>
	    		<c:otherwise><c:set var="step2LinkAddress" value="${step2.link_address}"/></c:otherwise>
	    		</c:choose>
	    		<c:choose>
	    		<c:when test="${step2.is_new_window eq 'Y'}"><c:set var="step2Target" value="target='_blank'"/></c:when>
	    		<c:otherwise><c:set var="step2Target" value=""/></c:otherwise>
	    		</c:choose>
	    		
	    		<li><a href="${step2LinkAddress}" ${step2Target} <c:if test="${step2.menu_code eq depth2MenuInfo.menu_code}">class="on"</c:if>><span><c:out value="${step2.menu_name}"/></span></a></li>
	    		
			</c:forEach>
		</c:if>
	</c:forEach>	
</ul>
</nav>
</div-->