<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/comm/taglib.jsp"%>


<div class="bbs_wrap">

<!-- 카테고리 -->
<jsp:include page="/WEB-INF/views/front/board/include/incBoardCate.jsp"/>

<!-- 검색 -->
<jsp:include page="/WEB-INF/views/front/board/include/incBoardSearch.jsp"/>

	<div class="newsletter_wrap">
	<div class="newsletter_list">
	
		<c:forEach var="data" items="${result}" varStatus="i">
		
		<c:set var="num" value="${theForm.total_count - ((theForm.page - 1) * theForm.pageSize) - (i.count - 1)}" />
		
		<c:set var="imageUrl">
		<c:choose>
		<c:when test="${!empty data.master_image}">${uploadDefaultUrl}/${uploadImgThumbDir}${data.master_image}</c:when>
		<c:otherwise>/assets/plugin/fileupload/img/noimage2.png</c:otherwise>
		</c:choose>
		</c:set>
		
		<div class="item">
			<div class="thumb">
				<img src="${imageUrl}" alt="<c:out value="${data.title}"/>">
			</div>
			<a href="boardView.do?board_key=${data.board_key}${defaultParameter}">				
				<h4><c:out value="${data.title}"/></h4>
				<p><c:out value="${data.summary}"/></p>
				<p class="text">
				<span class="no">No.${num}</span>
				<span class="date"><fmt:formatDate value="${data.reg_date}" pattern="yyyy.MM.dd"/></span>
				</p>
			</a>
		</div>
		</c:forEach>

	</div><!-- //bbs_list -->
	</div><!-- //bbs_wrap -->

	<!-- 페이지 -->
	<div class="pagenate_wrap">
		<ul class="pagenation">
			<c:out value="${pageNavigation}" escapeXml="false"/>
		</ul>
	</div>
	
	<!-- 글쓰기 버튼제어 -->
	<jsp:include page="/WEB-INF/views/front/board/include/incBoardWriteBtn.jsp"/>

</div><!-- //bbs_wrap -->