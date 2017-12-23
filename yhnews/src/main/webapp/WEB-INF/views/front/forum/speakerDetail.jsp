<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/comm/taglib.jsp"%>

<!-- 탭메뉴 -->
<jsp:include page="/WEB-INF/views/front/forum/incTabMenu.jsp"/>

<!-- <div class="tabMenu collection">
	<ul>
		<li><a href="#" class="on">전체</a></li>
		<li><a href="#">1분과</a></li>
		<li><a href="#">2분과</a></li>
		<li><a href="#">3분과</a></li>
	</ul>
</div> -->

<div class="bbs_wrap">
	<div class="bbs_view">
		<div class="title">
			<h4><c:out value="${speakerInfo.subject}"/></h4>
			<div class="author">
				<span class="writer">연사 : <c:out value="${speakerInfo.name}"/></span>
				<span class="date">국가 : <c:out value="${speakerInfo.country}"/></span>
				<span class="date">소속 : <c:out value="${speakerInfo.belong}"/></span>
				<!-- <span class="hit">조회수 : 201</span> -->
			</div>
			<!-- <div class="sns_wrap">
				<a href="#" class="ico_sns ico_fb">페이스북</a>
				<a href="#" class="ico_sns ico_tw">트위터</a>
				<a href="#" class="ico_sns ico_ks">카카오스토리</a>
			</div> -->
		</div>
		<div class="context">
			<c:out value="${speakerInfo.content}" escapeXml="false"/>
		</div>
	</div><!-- //bbs_view -->

	<div class="btn_wrap textAlign_right">
		<a href="forumSpeakerList.do?contTab=4&forum_key=${speakerInfo.forum_key}" class="btn btn_basic">목록</a>
	</div>

</div>