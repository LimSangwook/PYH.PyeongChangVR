<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/comm/taglib.jsp"%>

<c:if test="${!empty forumInfo.forum_key}">
<ul class="tabMenu tablist menu_3">
	 <li><a href="forumForm.do?forum_key=${forumInfo.forum_key}" <c:if test="${linkAddress eq 'forumForm'}">class="active"</c:if>><span>포럼/박람회 기본정보</span></a></li>
	 <li><a href="forumSpeakerList.do?forum_key=${forumInfo.forum_key}" <c:if test="${linkAddress eq 'forumSpeakerForm' or linkAddress eq 'forumSpeakerList'}">class="active"</c:if>><span>연사정보</span></a></li>
	 <li><a href="boardPastCommunityList.do?forum_key=${forumInfo.forum_key}" <c:if test="${linkAddress eq 'boardPastCommunityList' or linkAddress eq 'boardPastCommunityView'}">class="active"</c:if>><span>커뮤니티</span></a></li>
 </ul>
</c:if>