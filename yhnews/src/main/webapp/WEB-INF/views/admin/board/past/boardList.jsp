<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/comm/taglib.jsp"%>

<!-- 지난포럼/박람회 관리 탭 -->
<jsp:include page="/WEB-INF/views/admin/forum/incForumTab.jsp"/>

<h3 class="tit-h3">${forumInfo.event_name}</h3>

<div class="bbs_wrap">

<!-- 검색 -->
<fieldset>
<legend>게시물 검색</legend>
<form:form commandName="theForm" action="boardPastCommunityList.do" method="get">
<input type="hidden" name="forum_key" value="${param.forum_key}"/>
<input type="hidden" name="contTab" value="${param.contTab}"/>
	<div class="search_wrap">
		<form:select path="search_type" title="검색구분 선택">
		<form:option value="" label="선택"/>
		<form:option value="1" label="제목"/>
		<form:option value="2" label="내용"/>
		<form:option value="3" label="작성자"/>
		</form:select>
		<form:input path="search_keyword" title="검색어 입력" cssClass="srh_word"/>
		<button type="submit" class="btn inverse btn_small"><span class="ico ico_search"></span>검색</button>
	</div>
</form:form>
</fieldset>

<div class="guide_total">
	총 <strong><fmt:formatNumber value="${theForm.total_count}"/></strong>개의 글이 있습니다.
</div>

<table class="table_bbs">
	<caption>리스트</caption>
	<colgroup>
		<col style="width:auto;" />
		<col style="width:auto;" />
		<col style="width:auto;" />
		<col style="width:auto;"  class="mobile_hidden"/>
		<col style="width:auto;" />
		<col style="width:auto;" class="mobile_hidden" />
		<col style="width:auto;" class="mobile_hidden"/>
	</colgroup>
	<thead>
		<tr>
			<th scope="col">번호</th>
			<th scope="col">구분</th>
			<th scope="col">제목</th>
			<th scope="col" class="mobile_hidden">작성자</th>
			<th scope="col">작성일</th>
			<th scope="col" class="mobile_hidden">첨부</th>
			<th scope="col" class="mobile_hidden">조회</th>
		</tr>
	</thead>
	<tbody>
		<c:choose>
		<c:when test="${!empty result}">
		<c:forEach var="data" items="${result}" varStatus="i">
		<c:set var="num" value="${theForm.total_count - ((theForm.page - 1) * theForm.pageSize) - (i.count - 1)}" />		
			<tr>
				<td>${num}</td>
				<td>${data.board_name}</td>
				<td class="subject">
					<c:if test="${data.order_step eq 1}"><span class="organ_depth depth2"></span></c:if>
					<a href="boardPastCommunityView.do?board_key=${data.board_key}${defaultParameter}">
					<c:out value="${util:crop(data.title,100,'...','UTF-8')}"/>					
					<c:if test="${data.is_new_post eq 'Y'}"><span class="ico ico_new">새글</span> </c:if>
					</a>
				</td>
	            <td class="mobile_hidden"><c:out value="${data.secretWriter}"/></td>	            	   
				<td><fmt:formatDate value="${data.reg_date}" pattern="yyyy.MM.dd"/></td>
	            <td class="mobile_hidden">
	            	<c:if test="${data.attach_cnt > 0}">
	            		<span class="ico ico_file">파일</span>
	            	</c:if>
	            </td>
				<td class="mobile_hidden"><fmt:formatNumber value="${data.read_cnt }"/></td>				
			</tr>
		</c:forEach>		
		</c:when>
		<c:otherwise>
			<tr><td colspan="7" class="none"><spring:message code="MSG.NO.DATA"/></td></tr>
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

</div>

