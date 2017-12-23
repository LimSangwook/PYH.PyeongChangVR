<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/comm/taglib.jsp"%>

<div class="forum_list">
	<c:forEach var="data" items="${result}">
	
	<div class="item">
		<div class="forum_year">
			<span>${data.turn_num}</span>
			<strong>${data.event_year}</strong>
		</div>
		<div class="forum_content">
			<p class="photo">
			<c:choose>
			<c:when test="${!empty data.master_image}">
				<img src="${uploadDefaultUrl}/${uploadImgThumbDir}${data.master_image}" alt="<c:out value="${data.event_name}"/>"/>
			</c:when>
			<c:otherwise>
				<img src="/assets/plugin/fileupload/img/noimage2.png"/>
			</c:otherwise>
			</c:choose>
			</p>
			<dl>
				<dt><c:out value="${data.event_name}"/></dt>
				<dd>
					<ul class="list_style_1">
						<li><span class="title">기간</span><c:out value="${data.period}"/></li>
						<li><span class="title">장소</span><c:out value="${data.location}"/></li>
						<li><span class="title">주최</span><c:out value="${data.host_name}"/></li>
						<li><span class="title">주관</span><c:out value="${data.organize_name}"/></li>
					</ul>
				</dd>
			</dl>
			<a href="forumDetail.do?forum_key=${data.forum_key}" class="btn">자세히보기</a>
		</div>
	</div>
	
	</c:forEach>
	
</div>