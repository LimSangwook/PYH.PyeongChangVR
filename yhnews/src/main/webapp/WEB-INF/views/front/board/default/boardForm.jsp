<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/comm/taglib.jsp"%>

<div class="sub_section"> <!-- sub_section -->
  <h2 class="sub_main_title">알림마당</h2>
  <nav class="contents_menu">
    <ul>
        <li><a href="/forum/info/notice/boardList.do">공지사항</a></li>
        <li class="on"><a href="/forum/info/reference/boardList.do">자료실</a></li>
    </ul>
  </nav>
</div> <!-- sub_section -->
<div class="sub_contents"><!--sub_contents -->
  <!-- ============== 내용 시작======================== -->
  
	<!-- 글쓰기폼 -->
	<jsp:include page="/WEB-INF/views/front/board/include/incBoardForm.jsp"/>

 <!-- ============== 내용 종료======================== -->

</div> <!--sub_contents -->	