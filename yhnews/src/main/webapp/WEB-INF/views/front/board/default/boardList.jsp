<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/comm/taglib.jsp"%>

<!-- 게시판 상단내용 -->
<jsp:include page="/WEB-INF/views/front/board/include/incBoardTopCont.jsp"/>

<section class="section-vr">

<!-- 검색 -->
<jsp:include page="/WEB-INF/views/front/board/include/incBoardSearch.jsp"/>
<div class="bbs_list">
	<c:set var="colspan" value="3"/>
	<table summary="공지사항 목록">
		<caption>공지사항 목록</caption>
		<colgroup>
			<col style="width:10%" />
			<col style="width:auto;" />
			<col style="width:15%"/>
		</colgroup>
		<thead>
			<tr>
				<th scope="col">번호</th>
				<th scope="col">제목</th>
				<th scope="col">작성일</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="data" items="${noticeList}" varStatus="i">
			<tr class="notice">
				<td><span class="ico ico_notice">공지</span></td>
				<td class="subject">
					<a href="boardView.do?board_key=${data.board_key}${defaultParameter}">
					<c:out value="${util:crop(data.title,100,'...','UTF-8')}"/>
					<c:if test="${data.is_new_post eq 'Y'}"><span class="ico ico_new">새글</span></c:if>
					<c:if test="${data.is_secret eq 'Y'}"><span class="ico ico_rock">잠금글</span></c:if>
					</a>
				</td>			      
				<td><fmt:formatDate value="${data.reg_date}" pattern="yyyy-MM-dd"/></td>
			</tr>
			</c:forEach>
			
			<c:choose>
			<c:when test="${!empty result}">
			<c:forEach var="data" items="${result}" varStatus="i">
			<c:set var="num" value="${theForm.total_count - ((theForm.page - 1) * theForm.pageSize) - (i.count - 1)}" />
			<!-- orderByNumAsc ${((theForm.page - 1) * theForm.pageSize) + i.count } -->
				<tr>
					<td>${num}</td>
					<td class="subject">
						<c:if test="${data.order_step eq 1}"><span class="organ_depth depth2"></span></c:if>
						<a href="boardView.do?board_key=${data.board_key}${defaultParameter}">
						<c:out value="${util:crop(data.title,100,'...','UTF-8')}"/>					
						<c:if test="${data.is_new_post eq 'Y'}"><span class="ico ico_new">새글</span> </c:if>
						</a>
					</td>            	   
					<td><fmt:formatDate value="${data.reg_date}" pattern="yyyy-MM-dd"/></td>			
				</tr>
			</c:forEach>		
			</c:when>
			<c:otherwise>
				<tr><td colspan="${colspan}">
				<div class="noData">
				<p id="text-nodata"><spring:message code="MSG.NO.DATA"/></p>
				</div>
				</td></tr>
			</c:otherwise>
			</c:choose>
		</tbody>
	</table>
</div><!-- //bbs_list -->
<!-- 페이지 -->
<div class="pagenate_wrap">
	<ul class="pagenation">
		<c:out value="${pageNavigation}" escapeXml="false"/>
	</ul>
</div>
</section><!-- //section-vr -->
<!-- 글쓰기 버튼제어 -->
<!--jsp:include page="/WEB-INF/views/front/board/include/incBoardWriteBtn.jsp"/-->

</div>

<!-- 게시판 하단내용 -->
<jsp:include page="/WEB-INF/views/front/board/include/incBoardBottomCont.jsp"/>