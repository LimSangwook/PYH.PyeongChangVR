<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/comm/taglib.jsp"%>

<script>
$(function(){
	// 데이터 삭제시
	$('.btnDelete').on('click',function(){
		if(confirm('정말로 삭제하시겠습니까?')){
			$('#deleteForm').submit();
		}
	});
});

$(document).ready(function(){
	//썸네일 개수에 따른 width값 설정
	var thumBtn = $(".thumbList div ul li a");
	var thumBtnEa = $(".thumbList div ul li a").length - 1;
	var bigImg = $(".imgSlide_big .img img");

	thumBtn.on('click', function(){
		var thumImg = $(this).find('img').attr('src').replace('/${uploadImgThumbDir}/','/${uploadImgEditorDir}/');
		thumBtn.removeClass('active');
		$(this).addClass('active');
		bigImg.attr('src',thumImg);
		return false;
	});

	$('.arrowBtn.prev').on('click', function(){
		var num = 0;
		thumBtn.each(function(i){
			if (thumBtn.eq(i).hasClass('active')) {
				if (i == 0){ num = thumBtnEa; }else{ num = i-1; };
				thumImg = thumBtn.eq(num).find('img').attr('src').replace('/${uploadImgThumbDir}/','/${uploadImgEditorDir}/');
				thumBtn.removeClass('active');
				thumBtn.eq(num).addClass('active');
				bigImg.attr('src',thumImg);
				return false;
			};
		});
	});
	$('.arrowBtn.next').on('click', function(){
		var num = 0;
		thumBtn.each(function(i){
			if (thumBtn.eq(i).hasClass('active')) {
				if (i == thumBtnEa){ num = 0; }else{ num = i+1; };
				thumImg = thumBtn.eq(num).find('img').attr('src').replace('/${uploadImgThumbDir}/','/${uploadImgEditorDir}/');
				thumBtn.removeClass('active');
				thumBtn.eq(num).addClass('active');
				bigImg.attr('src',thumImg);
				return false;
			};
		});
	});
});
</script>

<!-- delete form start -->
<form:form id="deleteForm" commandName="theForm" action="boardSave.do">
<input type="hidden" name="act" value="delete"/>
<form:hidden path="board_key"/>
</form:form>
					
<div class="bbs_wrap">

	<div class="style_imgSlide">
	
		<div class="imgSlide_photo">
			<div class="title">
				<h4><c:out value="${result.title }"/></h4>
				<div class="author">
					<span class="writer">작성자 : <c:out value="${result.reg_name}"/></span>
					<span class="date">작성일 : <fmt:formatDate value="${result.reg_date}" type="date" pattern="yyyy-MM-dd"/></span>
					<span class="hit">조회수 : <fmt:formatNumber value="${result.read_cnt }"/></span>
					<c:if test="${menuCode eq 'stampTour'}">
						<span class="hit">SNS URL : ${result.link_address}</span>
					</c:if>
				</div>
				<div class="sns_wrap">
				<jsp:include page="/WEB-INF/views/front/board/include/incSnsFeed.jsp"/>
				</div>
			</div>
			<div class="imgSlide_big">
				<div class="img">
						<c:set var="imageUrl">
						<c:choose>
						<c:when test="${!empty result.master_image}">${uploadDefaultUrl}/${uploadImgEditorDir}${result.master_image}</c:when>
						<c:otherwise>/assets/plugin/fileupload/img/noimage2.png</c:otherwise>
						</c:choose>
						</c:set>
					<img src="${imageUrl}" alt="사진">
				</div>
			</div>
			<div class="thumbList">
				<div>
					<ul>
						<c:forEach var="data" items="${fileList}" varStatus="i">
						<c:if test="${data.image_width_size > 0}">
						<c:set var="imageThumbUrl" value="${uploadDefaultUrl}/${uploadImgThumbDir}${data.file_path}${data.save_file_name}"/>
							<li><a href="#img" class="active"><img src="${imageThumbUrl}" alt="<c:out value="${data.real_file_name}"/>"></a></li>
						</c:if>
						</c:forEach>
					</ul>
					<a href="#prev" class="arrowBtn prev">이전사진</a>
					<a href="#next" class="arrowBtn next">다음사진</a>
				</div>
			</div>
			<div class="context">
				<c:out value="${result.content}" escapeXml="false"/>
			</div>
		</div><!-- //imgSlide_photo -->
	
	</div>

	<div class="btn_wrap textAlign_right">
		<a href="boardList.do<c:out value="${defaultParameter}"/>" class="btn btn_basic">목록</a>
		
		<c:if test="${result.reg_id eq GLOBAL_USER_ID || GLOBAL_USER_AUTH == 9}">
		<a href="#" class="btn danger btnDelete">삭제</a>
		<a href="boardForm.do?board_key=${result.board_key}<c:out value="${defaultParameter}"/>" class="btn primary">수정</a>
		</c:if>
	</div>

</div>