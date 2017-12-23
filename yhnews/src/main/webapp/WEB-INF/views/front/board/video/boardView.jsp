<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/comm/taglib.jsp"%>

<!-- 게시판 상단내용 -->
<jsp:include page="/WEB-INF/views/front/board/include/incBoardTopCont.jsp"/>

<div class="bbs_wrap">
	<div class="bbs_view">
		<div class="title">
			<h4><c:out value="${result.title }"/></h4>
			<div class="author">
				<span class="writer">작성자 : <c:out value="${result.reg_name }"/></span>
				<span class="date">작성일 : <fmt:formatDate value="${result.reg_date}" type="date" pattern="yyyy-MM-dd"/></span>
				<span class="hit">조회수 : <fmt:formatNumber value="${result.read_cnt }"/></span>
			</div>
			<div class="sns_wrap">
				<jsp:include page="/WEB-INF/views/front/board/include/incSnsFeed.jsp"/>
			</div>
		</div>		
		<div class="context">
		
			<c:if test="${boardConfig.board_type eq 'video'}">
				<!-- 영상게시판 추가 필드 -->
				<div style="text-align: center">
					<c:choose>
						<c:when test="${result.video_type eq '1'}">
						<!-- VR -->
						<script src="//cdn.delight-vr.com/latest/dl8-308608ed37c76002ad874469647b523adf52cad6.js" async></script>
						
						<c:forEach var="file" items="${fileList}">
							<c:if test="${file.file_ext eq 'mp4'}">
							<dl8-video title="<c:out value="${result.title }"/>" author="<c:out value="${result.reg_name }"/>" format="MONO_360" poster="${uploadDefaultUrl}<c:out value="${result.master_image}"/>" display-mode="inline" loop>
	    						<source src="${uploadDefaultUrl}${file.file_path}${file.save_file_name}" type="video/mp4" />
	 						</dl8-video>
							</c:if>							
						</c:forEach>						
 						
						</c:when>
						
						<c:when test="${result.video_type eq '2'}">
						<!-- Youtube convert Url http://nuridol.net/ut_convert.html -->
							<%-- <iframe width="100%" src="${result.link_address}" frameborder="0" allowfullscreen></iframe> --%>
							<c:set var="youtubeEmbedUrl" value="${fn:replace(result.link_address,'https://youtu.be','https://www.youtube.com/v')}"/>							
							<div>
								<object width="100%" height="450">
									<param name="movie" value="${youtubeEmbedUrl}?version=3"></param>
									<param name="allowFullScreen" value="true"></param>
									<param name="allowscriptaccess" value="always"></param>
									<embed src="${youtubeEmbedUrl}?version=3" 
										type="application/x-shockwave-flash" width="100%" height="450" allowscriptaccess="always" allowfullscreen="true">
										</embed>
								</object>
							</div>
						</c:when>
					
					</c:choose>
				
				</div>		
				<!--// 영상게시판 추가 필드 -->
			</c:if>
				
			<c:out value="${result.content}" escapeXml="false"/>
		</div>
	</div><!-- //bbs_view -->

	<div class="btn_wrap textAlign_right">
		<a href="boardList.do?page=${theForm.page}<c:out value="${defaultParameter}"/>" class="btn btn_basic">목록</a>
	</div>

</div>
