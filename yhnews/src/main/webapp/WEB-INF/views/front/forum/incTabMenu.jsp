<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/comm/taglib.jsp"%>

<div class="tabMenu menu_7">
	<ul>
		<li><a href="/forum/forumInfo/pastForum/forumList.do">전체</a></li>
		<li><a href="/forum/forumInfo/pastForum/forumDetail.do?forum_key=${forumInfo.forum_key}&contTab=1" <c:if test="${empty param.contTab || param.contTab eq '1'}">class="on"</c:if>>개요 및 일정</a></li>
		<li><a href="/forum/forumInfo/pastForum/forumDetail.do?forum_key=${forumInfo.forum_key}&contTab=2" <c:if test="${param.contTab eq '2'}">class="on"</c:if>>포럼프로그램</a></li>
		<li><a href="/forum/forumInfo/pastForum/forumDetail.do?forum_key=${forumInfo.forum_key}&contTab=3" <c:if test="${param.contTab eq '3'}">class="on"</c:if>>전시장 소개</a></li>
		<li><a href="/forum/forumInfo/pastForum/forumSpeakerList.do?forum_key=${forumInfo.forum_key}&contTab=4" <c:if test="${param.contTab eq '4'}">class="on"</c:if>>연사정보</a></li>
		<li><a href="/forum/forumInfo/pastForum/forumDetail.do?forum_key=${forumInfo.forum_key}&contTab=5" <c:if test="${param.contTab eq '5'}">class="on"</c:if>>문화공연안내</a></li>
		<li><a href="/forum/forumInfo/pastForum/boardPastCommunityList.do?forum_key=${forumInfo.forum_key}&contTab=6" <c:if test="${param.contTab eq '6'}">class="on"</c:if>>커뮤니티</a></li>
	</ul>
</div>