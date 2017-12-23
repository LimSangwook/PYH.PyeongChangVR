<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/comm/taglib.jsp"%>

<!-- delete form start -->
<form:form id="deleteForm" commandName="theForm" action="boardSave.do">
<input type="hidden" name="act" value="delete"/>
<form:hidden path="board_key"/>
</form:form>

	<section class="section-guide01 guide-type01 sub-visual04">
		<div>
			<h1>공지사항</h1>
		</div>
	</section>
	<section class="section-vr">
            <!-- ============== 내용 시작======================== -->
            
	<!-- 게시판 상세 -->
	<jsp:include page="/WEB-INF/views/front/board/include/incBoardView.jsp"/>
	
            <!-- ============== 내용 종료======================== -->

</div> <!--sub_contents -->