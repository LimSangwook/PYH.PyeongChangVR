<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/comm/taglib.jsp"%>

<!-- 카테고리-->
<jsp:include page="/WEB-INF/views/admin/board/include/incBoardCate.jsp"/>
<!--// 카테고리 -->

<!-- 검색 -->
<fieldset>
<legend></legend>
<form:form commandName="theForm" action="boardList.do" method="get">
	<div class="search_wrap">
		<form:select path="search_type" title="검색구분 선택">
		<form:option value="" label="선택"/>
		<form:option value="1" label="제목"/>
		<form:option value="2" label="내용"/>
		<form:option value="3" label="작성자"/>
		</form:select>
		<form:input path="search_keyword" title="검색어 입력" cssClass="col25"/>
		<button type="submit" class="btn inverse btn_small"><span class="ico ico_search"></span>검색</button>
	</div>
</form:form>
</fieldset>

<c:set var="orderType">
<c:choose>
<c:when test="${theForm.order_type eq 'D'}">A</c:when>
<c:otherwise>D</c:otherwise>
</c:choose>
</c:set>

<c:set var="colspan" value="6"/>
<table class="table_basic">
	<caption>테이블</caption>
	<colgroup>		
		<col style="width:100px;" />
		<c:if test="${boardConfig.cate_use_yn eq 'Y'}">
		<!-- 카테고리 -->
		<col style="width:100px;" />
		<c:set var="colspan" value="${colspan +1}"/>
		</c:if>
		<col style="width:auto;" />
		<col style="width:100px;" />
		<col style="width:150px;" />
		<c:if test="${boardConfig.file_count_limit > 0}">
		<!-- 첨부파일 -->
		<c:set var="colspan" value="${colspan +1}"/>
		<col style="width:100px;" />		
		</c:if>
		<col style="width:100px;" />		
		<col style="width:100px;" />
	</colgroup>
	<thead>
		<tr>
			<th scope="col">번호 <a href="boardList.do?order_column=1&order_type=${orderType}${defaultParameter}" class="ico ico_sort">정렬</a></th>
			<c:if test="${boardConfig.cate_use_yn eq 'Y'}">
			<th scope="col">구분 <a href="boardList.do?order_column=4&order_type=${orderType}${defaultParameter}" class="ico ico_sort">정렬</a></th>
			</c:if>
			<th scope="col">제목 <a href="boardList.do?order_column=2&order_type=${orderType}${defaultParameter}" class="ico ico_sort">정렬</a></th>
			<th scope="col">조회수 <a href="boardList.do?order_column=3&order_type=${orderType}${defaultParameter}" class="ico ico_sort">정렬</a></th>			
			<th scope="col">작성자</th>
			<c:if test="${boardConfig.file_count_limit > 0}">
			<th scope="col">첨부</th>
			</c:if>
			<th scope="col">등록일자</th>			
			<th scope="col">상태</th>
		</tr>
	</thead>
	<tbody>
		
		<c:forEach var="data" items="${noticeList}" varStatus="i">
		<tr>
			<td><span class="label warning">공지</span></td>
			<c:if test="${boardConfig.cate_use_yn eq 'Y'}">
			<td><c:out value="${data.cate_name}"/></td>
			</c:if>
			<td class="textAlign_left">
				<a href="boardView.do?board_key=${data.board_key}${defaultParameter}">
				<c:out value="${util:crop(data.title,100,'...','UTF-8')}"/>
				<c:if test="${data.is_new_post eq 'Y'}"><span class="ico ico_newpost">N</span></c:if>
				</a>
			</td>
			<td><fmt:formatNumber value="${data.read_cnt }"/></td>
            <td><c:out value="${data.reg_name}"/></td>
            <c:if test="${boardConfig.file_count_limit > 0}">	   
            <td>
            	<c:if test="${data.attach_cnt > 0}">
            	<span class="ico ico_file">파일</span>
            	</c:if>
            </td> 
            </c:if>
			<td><fmt:formatDate value="${data.reg_date}" pattern="yyyy.MM.dd"/></td>
			<td>
				<c:choose>
				<c:when test="${data.status eq 'Y'}">
					<span class="btn btn_xsmall primary">정상</span>
				</c:when>
				<c:when test="${data.status eq 'N'}">
					<span class="btn btn_xsmall success">차단</span>
				</c:when>
				</c:choose>
			</td>
		</tr>
		</c:forEach>
		
		
		<c:choose>
		<c:when test="${!empty result}">
		<c:forEach var="data" items="${result}" varStatus="i">
		<c:set var="num" value="${theForm.total_count - ((theForm.page - 1) * theForm.pageSize) - (i.count - 1)}" />
		<!-- orderByNumAsc ${((theForm.page - 1) * theForm.pageSize) + i.count } -->
			<tr>
				<td>${num}</td>
				<c:if test="${boardConfig.cate_use_yn eq 'Y'}">
				<td><c:out value="${data.cate_name}"/></td>
				</c:if>
				<td class="textAlign_left">
					<c:if test="${data.order_step eq 1}"><span class="organ_depth depth2"></span></c:if>
					<a href="boardView.do?board_key=${data.board_key}${defaultParameter}">
					<c:out value="${util:crop(data.title,100,'...','UTF-8')}"/>
					<c:if test="${data.comment_cnt > 0}"><span class="text-muted">[${data.comment_cnt}]</span></c:if>
					<c:if test="${data.is_new_post eq 'Y'}"><span class="ico ico_newpost">N</span></c:if>
					</a>
				</td>				
				<td><fmt:formatNumber value="${data.read_cnt }"/></td>
	            <td><c:out value="${data.reg_name}"/></td>
	            <c:if test="${boardConfig.file_count_limit > 0}">	   
	            <td>
	            	<c:if test="${data.attach_cnt > 0}">
	            	<span class="ico ico_file">파일</span>
	            	</c:if>
	            </td> 
	            </c:if>	   
				<td><fmt:formatDate value="${data.reg_date}" pattern="yyyy.MM.dd"/></td>
				<td>
					<c:choose>
					<c:when test="${data.status eq 'Y'}">
						<span class="btn btn_xsmall primary">정상</span>
					</c:when>
					<c:when test="${data.status eq 'N'}">
						<span class="btn btn_xsmall success">차단</span>
					</c:when>
					</c:choose>
				</td>
			</tr>
		</c:forEach>		
		</c:when>
		<c:otherwise>
			<tr><td colspan="${colspan}"><spring:message code="MSG.NO.DATA"/></td></tr>
		</c:otherwise>
		</c:choose>
	</tbody>
</table><!-- //table_basic -->

<!-- 페이지 -->
<div class="pagenate_wrap float_left">
	<ul class="pagenation">
		<c:out value="${pageNavigation}" escapeXml="false"/>
	</ul>
</div><!-- //pagenate_wrap -->

<div class="btn_wrap float_right">
	<a href="boardForm.do?cate_id=${theForm.cate_id}" class="btn primary">등록</a>
</div>