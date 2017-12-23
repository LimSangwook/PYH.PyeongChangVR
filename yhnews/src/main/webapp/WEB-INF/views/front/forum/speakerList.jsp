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

<div class="list_search">
	<form:form commandName="theForm" action="forumSpeakerList.do" method="get">
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
			<a href="forumSpeakerDetail.do?forum_key=${data.forum_key}&speaker_key=${data.speaker_key}&contTab=4" class="speaker_box">
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