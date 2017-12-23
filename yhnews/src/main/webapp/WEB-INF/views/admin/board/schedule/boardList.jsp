<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/comm/taglib.jsp"%>

<style>
.sun {color:red !important;}
.sat {color:blue !important;}
</style>

<!-- 일정 게시판 script -->
<script type="text/javascript" src="/assets/script/board.schedule.js"></script>

<script type="text/javascript">
$(function(){
	cal.params.museumNo = "${param.museum_no}";
	cal.params.menuCode = "${param.menu_code}";	
	cal.params.searchYear = "${param.searchYear}";
	cal.params.searchMonth = "${param.searchMonth}";	
	cal.params.boardId = "${boardConfig.board_id}";
	cal.params.linkAddress = "boardView.do";
	cal.init();
});
</script>

<!-- 카테고리-->
<jsp:include page="/WEB-INF/views/admin/board/include/incBoardCate.jsp"/>
<!--// 카테고리 -->

<div class="bbs_wrap">
	
	<div id="calenda">
	<!-- 일정게시판 영역 -->
	</div>
	 
	<div class="btn_wrap float_right">
		<a href="boardForm.do?cate_id=${theForm.cate_id}${defaultParameter}" class="btn primary">등록</a>
	</div>

</div>
