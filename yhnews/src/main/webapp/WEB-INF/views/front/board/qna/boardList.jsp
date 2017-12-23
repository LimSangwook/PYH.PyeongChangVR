<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/comm/taglib.jsp"%>

<div class="bbs_wrap">

<!-- 검색 -->
<jsp:include page="/WEB-INF/views/front/board/include/incBoardSearch.jsp"/>

<c:set var="colspan" value="5"/>
<table class="table_bbs">
	<caption>리스트</caption>
	<colgroup>
		<col style="width:auto;" />
		<col style="width:auto;" />
		<col style="width:auto;"  class="mobile_hidden"/>
		<col style="width:auto;" />
		<c:if test="${boardConfig.file_count_limit > 0}">
		<!-- 첨부파일 -->
		<c:set var="colspan" value="${colspan +1}"/>
		<col style="width:auto;" class="mobile_hidden" />		
		</c:if>		
		<col style="width:auto;"  class="mobile_hidden"/>
	</colgroup>
	<thead>
		<tr>
			<th scope="col">번호</th>
			<th scope="col">제목</th>
			<th scope="col" class="mobile_hidden">작성자</th>
			<th scope="col">작성일</th>
			<c:if test="${boardConfig.file_count_limit > 0}">
			<th scope="col" class="mobile_hidden">첨부</th>
			</c:if>
			<th scope="col" class="mobile_hidden">처리상태</th>
		</tr>
	</thead>
	<tbody>
		<c:forEach var="data" items="${noticeList}" varStatus="i">
		<tr>
			<td><strong>공지</strong></td>
			<td class="subject">
				<a href="boardView.do?board_key=${data.board_key}${defaultParameter}">
				<c:out value="${util:crop(data.title,100,'...','UTF-8')}"/>				
				</a>
				<c:if test="${data.is_new_post eq 'Y'}"><span class="ico ico_new">새글</span></c:if>
				<c:if test="${data.is_secret eq 'Y'}"><span class="ico ico_rock">잠금글</span></c:if>
			</td>			
            <td class="mobile_hidden"><c:out value="${data.secretWriter}"/></td>            
			<td><fmt:formatDate value="${data.reg_date}" pattern="yyyy.MM.dd"/></td>
			<c:if test="${boardConfig.file_count_limit > 0}">	   
            <td class="mobile_hidden">
            	<c:if test="${data.attach_cnt > 0}">
            	<span class="ico ico_file">파일</span>
            	</c:if>
            </td> 
            </c:if>
			<td class="mobile_hidden">
				<c:choose>
				<c:when test="${data.order_step > 0}"><span class="state state_default">답변완료</span></c:when>
				<c:otherwise><span class="state state_ing">문의접수</span></c:otherwise>
				</c:choose>
			</td>
		</tr>
		</c:forEach>
		
		<c:choose>
		<c:when test="${!empty result}">
		<c:forEach var="data" items="${result}" varStatus="i">
		<c:set var="num" value="${theForm.total_count - ((theForm.page - 1) * theForm.pageSize) - (i.count - 1)}" />
			<tr>
				<td>${num}</td>
				<td class="subject">													
					<a href="boardView.do?board_key=${data.board_key}${defaultParameter}">
					<c:if test="${data.order_step > 0}">└</c:if>
					<c:out value="${util:crop(data.title,100,'...','UTF-8')}"/>
					</a>
					<c:if test="${data.is_new_post eq 'Y'}"><span class="ico ico_new">새글</span> </c:if>
					<c:if test="${data.is_secret eq 'Y'}"><span class="ico ico_rock">잠금글</span></c:if>
				</td>
	            <td class="mobile_hidden"><c:out value="${data.secretWriter}"/></td>	            	   
				<td><fmt:formatDate value="${data.reg_date}" pattern="yyyy.MM.dd"/></td>
				<c:if test="${boardConfig.file_count_limit > 0}">	   
	            <td class="mobile_hidden">
	            	<c:if test="${data.attach_cnt > 0}">
	            		<span class="ico ico_file">파일</span>
	            	</c:if>
	            </td> 
	            </c:if>
				<td class="mobile_hidden">
					<c:choose>
					<c:when test="${data.order_step > 0}"><span class="state state_default">답변완료</span></c:when>
					<c:otherwise><span class="state state_ing">문의접수</span></c:otherwise>
					</c:choose>
				</td>				
			</tr>
		</c:forEach>		
		</c:when>
		<c:otherwise>
			<tr><td colspan="${colspan}" class="none"><spring:message code="MSG.NO.DATA"/></td></tr>
		</c:otherwise>
		</c:choose>
		
		
	</tbody>
</table>

<!-- 페이지 -->
<div class="pagenate_wrap">
	<ul class="pagenation">
		<c:out value="${pageNavigation}" escapeXml="false"/>
	</ul>
</div>

<!-- 글쓰기 버튼제어 -->
<jsp:include page="/WEB-INF/views/front/board/include/incBoardWriteBtn.jsp"/>

</div>