<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/comm/taglib.jsp"%>

<%-- <div class="tabMenu menu_5">
	<ul>
		<li><a href="/forum/visitorGuide/scheduleForum/forumCurrDetail.do?contTab=1" <c:if test="${empty param.contTab || param.contTab eq '1'}">class="on"</c:if>>개폐회식 일정</a></li>
		<li><a href="/forum/visitorGuide/programForum/forumCurrDetail.do?contTab=2" <c:if test="${param.contTab eq '2'}">class="on"</c:if>>포럼 프로그램</a></li>
		<li><a href="/forum/visitorGuide/exhibitionInfo/forumCurrDetail.do?contTab=3" <c:if test="${param.contTab eq '3'}">class="on"</c:if>>전시장 소개</a></li>
		<li><a href="/forum/visitorGuide/speaker/forumCurrDetail.do?contTab=4" <c:if test="${param.contTab eq '4'}">class="on"</c:if>>연사 소개</a></li>
		<li><a href="/forum/visitorGuide/culture/forumCurrDetail.do?contTab=5" <c:if test="${param.contTab eq '5'}">class="on"</c:if>>문화공연 행사</a></li>		
	</ul>
</div> --%>

<c:set var="content">
<c:choose>
<c:when test="${empty param.contTab || param.contTab eq '1'}">${forumInfo.content1}</c:when>
<c:when test="${param.contTab eq '2'}">${forumInfo.content2}</c:when>
<c:when test="${param.contTab eq '3'}">${forumInfo.content3}</c:when>
<c:when test="${param.contTab eq '5'}">${forumInfo.content4}</c:when>
</c:choose>
</c:set>

<c:choose>
<c:when test="${param.contTab eq '4'}">

	<c:choose>
	<c:when test="${!empty speakerInfo}">
		<!-- 연사소개 상세보기 -->
			
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
				<a href="forumCurrDetail.do?contTab=4" class="btn btn_basic">목록</a>
			</div>

		</div>
		<!-- 연사소개 상세보기 -->			
	</c:when>
	<c:otherwise>
		<!-- 연사소개 목록 -->
		<div class="list_search">
			<form:form commandName="theForm" action="forumCurrDetail.do" method="get">
			<form:hidden path="forum_key"/>
			<input type="hidden" name="contTab" value="${param.contTab}">
				<div class="search_wrap">			
					<form:select path="kind">
					<form:option value="" label="분과선택"/>
					<form:options items="${speakerKindList}" itemValue="kind" itemLabel="kind_name"/>
					</form:select>
					<form:select path="search_column">
						<form:option value="1" label="연사자"/>				
					</form:select>
					<form:input path="search_keyword" title="검색어 입력"/>
					<button type="submit">검색</button>
				</div><!-- //search_wrap -->
			</form:form>
		</div>
		
		<div class="speaker_list">
			<c:forEach var="data" items="${result}" varStatus="i">
				<div class="item">
					<a href="forumCurrDetail.do?contTab=4&speaker_key=${data.speaker_key}" class="speaker_box">
						<div class="photo">
							<c:choose>
							<c:when test="${!empty data.profile_photo}">
								<img id="profile_photoPreview" src="${uploadDefaultUrl}/${uploadImgThumbDir}${data.profile_photo}" alt="썸네일이미지">
							</c:when>
							<c:otherwise>
								<img id="profile_photoPreview" src="/assets/plugin/fileupload/img/noimage2.png" alt="썸네일이미지">
							</c:otherwise>
							</c:choose>
						</div>
						<div class="speaker_content">
							<p class="name">${data.name}<span><c:out value="${data.kind_name}"/> <c:if test="${!empty data.speaker_kind_detail}"><c:out value="(${data.speaker_kind_detail})"/></c:if></span>
								<span class="bottom_line"></span>
							</p>
							<p class="location"><c:out value="${data.country}"/></p>
							<ul class="profile">
								<li><c:out value="${data.belong}"/></li>
							</ul>
							<p class="title"><c:out value="${data.subject}"/></p>
						</div>
					</a>
				</div>
			</c:forEach>
		
		</div>     
		
		<div class="clearfix">
			<div class="pagenate_wrap">
				<ul class="pagenation">
					<c:out value="${pageNavigation}" escapeXml="false"/>
				</ul>
			</div>
		</div>
		<!--// 연사소개 목록-->
	</c:otherwise>
	</c:choose>
	
</c:when>
<c:otherwise>
	<c:out value="${content}" escapeXml="false"/>
</c:otherwise>
</c:choose>


