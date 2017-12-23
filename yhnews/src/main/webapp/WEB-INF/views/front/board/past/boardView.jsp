<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/comm/taglib.jsp"%>

<!-- 탭메뉴 -->
<jsp:include page="/WEB-INF/views/front/forum/incTabMenu.jsp"/>

<div class="bbs_wrap">
	<div class="bbs_view">
		<div class="title">
			<h4><c:out value="${result.title }"/></h4>
			<div class="author">
				<span class="writer">작성자 : <c:out value="${result.reg_name }"/></span>
				<span class="date">작성일 : <fmt:formatDate value="${result.reg_date}" type="date" pattern="yyyy-MM-dd"/></span>
				<span class="hit">조회수 : <fmt:formatNumber value="${result.read_cnt }"/></span>
			</div>
			<!-- <div class="sns_wrap">
				<a href="#" class="ico_sns ico_fb">페이스북</a>
				<a href="#" class="ico_sns ico_tw">트위터</a>
				<a href="#" class="ico_sns ico_ks">카카오스토리</a>
			</div> -->
		</div>
		
		<div class="file_wrap">
			<span class="ico ico_file">첨부파일</span> 첨부파일 : 
			<c:forEach var="data" items="${fileList}" varStatus="i">
			<c:set var="downLoadLink" value="${siteDomain}/downLoad.do?file_key=${data.file_key}&save_file_name=${data.save_file_name}"/>
			<c:if test="${i.index > 0 }">,&nbsp;&nbsp;</c:if>
			<a href="${downLoadLink}"><c:out value="${data.real_file_name}"/> (<c:out value="${util:convertFileSize(data.file_size) }"/>)</a>
			</c:forEach>
		</div><!-- //file_wrap -->
		<div class="context">
			<c:out value="${result.content}" escapeXml="false"/>
		</div>
	</div><!-- //bbs_view -->

	<div class="btn_wrap textAlign_right">
		<a href="boardPastCommunityList.do?page=${theForm.page}<c:out value="${defaultParameter}"/>" class="btn btn_basic">목록</a>
	</div>

</div>