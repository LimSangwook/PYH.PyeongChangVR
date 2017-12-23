<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/comm/taglib.jsp"%>


<!-- 게시판 상단내용 -->
<jsp:include page="/WEB-INF/views/front/board/include/incBoardTopCont.jsp"/>

<section class="section-vr">

<!-- 검색 -->
<jsp:include page="/WEB-INF/views/front/board/include/incBoardSearch.jsp"/>

<div class="bbs_list">

	<ul class="faq_list">
		<c:choose>
			<c:when test="${!empty result}">
				<c:forEach var="data" items="${result}" varStatus="i">
				<c:set var="num" value="${theForm.total_count - ((theForm.page - 1) * theForm.pageSize) - (i.count - 1)}" />
				<li>
					<p class="q"><a href="#none"><span class="ico_q">Q ${num}. </span><c:out value="${data.title}"/></a></p>
					<div class="a" >
						<span class="ico_a">A.</span>
						<p><c:out value="${data.content}" escapeXml="false"/></p>
					</div>
				</li>
				</c:forEach>
			</c:when>
			<c:otherwise>
			<li class="none">
				<p class="q"><spring:message code="MSG.NO.DATA"/></p>
			</li>	
			</c:otherwise>
		</c:choose>
	</ul>
</div>
<!-- 페이지 -->
<div class="pagenate_wrap">
	<ul class="pagenation">
		<c:out value="${pageNavigation}" escapeXml="false"/>
	</ul>

</section><!-- //section-vr -->


<script>
(function(){
	$(".faq_list .q a").click(function(){
		if($(this).parents("li").find(".a").is(":hidden")){

			$(".faq_list .a").stop().slideUp(300);
			$(".faq_list li").removeClass("on");

			$(this).parents("li").find(".a").stop().slideDown(300);
			$(this).parents("li").addClass("on");
		} else {
			$(this).parents("li").find(".a").stop().slideUp(300);
			$(this).parents("li").removeClass("on");
		}
	});
})();
</script>