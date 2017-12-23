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
</script>
                

	<div class="bbs_view">
		<div class="subject_wrap">
			<h2><c:out value="${result.title }"/></h2>
			<span class="date"><fmt:formatDate value="${result.reg_date}" type="date" pattern="yyyy-MM-dd"/></span>
		</div>
		<div class="contents_wrap">
			<c:out value="${result.content}" escapeXml="false"/>
		</div>
	</div><!-- //bbs_view -->

	<div class="btn_wrap">
		<a href="boardList.do?page=${theForm.page}<c:out value="${defaultParameter}"/>" class="btn_type02">목록</a>
	</div>



 