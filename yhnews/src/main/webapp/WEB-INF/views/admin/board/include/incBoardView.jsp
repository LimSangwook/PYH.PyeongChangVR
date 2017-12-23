<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/comm/taglib.jsp"%>

<script type="text/javascript">
$(function(){
	// 데이터 삭제시
	$('.btnDelete').on('click',function(){
		if(confirm('정말로 삭제하시겠습니까?')){
			$('#deleteForm').submit();
		}
	});
});	
</script>

<!-- 카테고리-->
<jsp:include page="/WEB-INF/views/admin/board/include/incBoardCate.jsp"/>
<!--// 카테고리 -->

<!-- delete form start -->
<form:form id="deleteForm" commandName="theForm" action="boardSave.do">
<input type="hidden" name="act" value="delete"/>
<form:hidden path="board_key"/>

<!-- 박물관 게시판용 추가 파라미터-->
<jsp:include page="/WEB-INF/views/admin/board/include/incMuseumBoardInput.jsp"/>
<!--// 박물관 게시판용 추가 파라미터 -->

</form:form>

<table class="table_form">
	<caption>게시글 정보</caption>
	<colgroup>
		<col style="width:17%;" />
		<col style="width:33%;" />
		<col style="width:17%;" />
		<col style="width:33%;" />
	</colgroup>
	<tbody>
		<c:if test="${boardConfig.cate_use_yn eq 'Y'}">
		<tr>
			<th scope="row">카테고리명</th>
			<td colspan="3"><c:out value="${result.cate_name }"/></td>
		</tr>
		</c:if>
		
		<c:choose>
		<c:when test="${boardConfig.board_type eq 'goods'}">
		<!-- 기념품(상품) 추가필드 -->
		
		<tr>
			<th scope="row">상품명</th>
			<td>
				<c:choose>
					<c:when test="${result.status eq 'Y'}">
						<span class="btn btn_xsmall primary">정상</span>
					</c:when>
					<c:when test="${result.status eq 'N'}">
						<span class="btn btn_xsmall success">차단</span>
					</c:when>
				</c:choose>
				<c:out value="${result.title }"/><c:if test="${result.is_secret eq 'Y'}"><span class="ico ico_rock">잠금글</span></c:if>		
			</td>
			<th scope="row">소재지</th>
			<td><c:out value="${result.add_field1}"/></td>
		</tr>
		<tr>
			<th scope="row">판매가격</th>
			<td><c:out value="${result.add_field2}"/></td>
			<th scope="row">판매수량</th>
			<td><c:out value="${result.add_field3}"/></td>
		</tr>
		<tr>
			<th scope="row">판매기간</th>
			<td><c:out value="${result.start_day}"/> ~ <c:out value="${result.end_day}"/></td>
			<th scope="row">판매URL</th>
			<td><a href="<c:out value="${result.link_address}"/>" target="_blank"><c:out value="${result.link_address}"/></a></td>
		</tr>
			
		</c:when>
		<c:otherwise>
			<tr>
				<th scope="row">제목</th>
				<td colspan="3">
					<c:choose>
						<c:when test="${result.status eq 'Y'}">
							<span class="btn btn_xsmall primary">정상</span>
						</c:when>
						<c:when test="${result.status eq 'N'}">
							<span class="btn btn_xsmall success">차단</span>
						</c:when>
					</c:choose>
					<c:out value="${result.title }"/><c:if test="${result.is_secret eq 'Y'}"><span class="ico ico_rock">잠금글</span></c:if>		
				</td>
			</tr>
			<!-- 스탬프 투어 관리 -->
			<c:if test="${menuCode eq 'stampManage'}">
				<tr>
					<th scope="row">SNS URL</th>
					<td colspan="3">${result.link_address}</td>
				</tr>
			</c:if>	
			
		</c:otherwise>
		</c:choose>
		
		
		<c:if test="${boardConfig.board_type eq 'schedule'}">
		<!-- 일정관리 추가 필드 -->
			<tr>
				<th scope="row">기간</th>
				<td colspan="3"><c:out value="${result.start_day}"/> ~ <c:out value="${result.end_day}"/></td>
			</tr>			
		<!--// 일정관리  추가 필드 -->
		</c:if>
		
		<c:if test="${boardConfig.board_type eq 'newsletter'}">
		<!-- 뉴스레터일 추가 필드 -->
			<tr>
				<th scope="row">한줄설명</th>
				<td colspan="3"><c:out value="${result.summary}"/></td>
			</tr>			
		<!--// 뉴스레터일  추가 필드 -->
		</c:if>
		
		<tr>
			<th scope="row">작성자</th>
			<td><c:out value="${result.reg_name }"/></td>
			<th scope="row">조회수</th>
			<td><fmt:formatNumber value="${result.read_cnt }"/></td>
		</tr>
		<tr>
			<th scope="row">등록일시</th>
			<td><fmt:formatDate value="${result.reg_date}" type="date" pattern="yyyy-MM-dd HH:mm:ss"/></td>
			<th scope="row">수정일시</th>
			<td><fmt:formatDate value="${result.mod_date}" type="date" pattern="yyyy-MM-dd HH:mm:ss"/></td>
		</tr>		
		<c:if test="${boardConfig.board_type eq 'gallery'}">
		
		</c:if>
		<tr>				
			<th scope="row">내용</th>
			<td colspan="3">
			<div style="min-height: 200px">
			
				<c:if test="${boardConfig.board_type eq 'video'}">
				<!-- 영상게시판 추가 필드 -->
				<div>
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
			</td>
		</tr>
		<tr>
			<th scope="row">등록아이피</th>
			<td colspan="3">
				<c:out value="${result.ip}"/>
			</td>
		</tr>
		<c:if test="${boardConfig.file_count_limit > 0}">
		<tr>
			<th scope="row">첨부파일</th>
			<td colspan="3">
				<ul class="list_style_2">
					<c:forEach var="data" items="${fileList}">
					<c:set var="downLoadLink" value="${siteDomain}/downLoad.do?file_key=${data.file_key}&save_file_name=${data.save_file_name}"/>
					<li><a href="${downLoadLink}"><i class="fa fa-paperclip"></i> <c:out value="${data.real_file_name}"/> (<c:out value="${util:convertFileSize(data.file_size) }"/>)</a></li>
					</c:forEach>
				</ul>
			</td>
		</tr>
		</c:if>
	</tbody>
</table>

<div class="btn_wrap float_left">

	<c:if test="${!empty result.prevBoard.board_key}">
	<a href="boardView.do?board_key=${result.prevBoard.board_key}<c:out value="${defaultParameter}"/>" class="btn white btn-sm">이전글</a>
	</c:if>
	<c:if test="${!empty result.nextBoard.board_key}">
	<a href="boardView.do?board_key=${result.nextBoard.board_key}<c:out value="${defaultParameter}"/>" class="btn white btn-sm">다음글</a>
	</c:if>

</div>
        
<div class="btn_wrap textAlign_center">
	<a href="boardList.do?page=${theForm.page}<c:out value="${defaultParameter}"/>" class="btn white">목록</a>
	
	<c:if test="${result.reg_id eq GLOBAL_USER_ID || GLOBAL_USER_AUTH == 9}">
	<a href="#" class="btn danger btnDelete">삭제</a>
	<a href="boardForm.do?board_key=${result.board_key}<c:out value="${defaultParameter}"/>" class="btn primary">수정</a>
	</c:if>
	
	<c:if test="${boardConfig.reply_use_yn eq 'Y' and result.order_step eq 0 and result.is_notice ne 'Y' and boardConfig.reply_limit <= GLOBAL_USER_AUTH}">
	<!-- 답글작성여부 -->
	<a href="boardForm.do?board_key=${result.board_key}&act=reply" class="btn inverse">답글작성</a>
	</c:if>			
</div>