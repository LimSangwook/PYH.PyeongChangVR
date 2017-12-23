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

<div class="guide_total">
	총 <strong><fmt:formatNumber value="${theForm.total_count}"/></strong>개의 글이 있습니다.
</div>
						
<div class="newsletter_wrap">
	<div class="newsletter_list">
	
	<c:forEach var="data" items="${result}" varStatus="i">
	<c:set var="num" value="${theForm.total_count - ((theForm.page - 1) * theForm.pageSize) - (i.count - 1)}" />
	
		<c:set var="imageUrl">
		<c:choose>
		<c:when test="${!empty data.master_image}">${uploadDefaultUrl}/${uploadImgThumbDir}${data.master_image}</c:when>
		<c:otherwise>/assets/plugin/fileupload/img/noimage2.png</c:otherwise>
		</c:choose>
		</c:set>
		
		<div class="item">			
				<div class="thumb">
					<img src="${imageUrl}" alt="<c:out value="${data.title}"/>">
				</div>
				<a href="boardView.do?board_key=${data.board_key}${defaultParameter}">
				<h4><c:out value="${data.title}"/></h4>		
				<p class="context"><c:out value="${data.start_day}"/> ~ <c:out value="${data.end_day}"/></p>
				<p class="text">
					<span class="no">No.${num}</span>
					<span class="date"><c:out value="${data.add_field1}"/></span>
					<span class="date"><fmt:formatDate value="${data.reg_date}" pattern="yyyy.MM.dd"/></span>
				</p>
			</a>
		</div>
	</c:forEach>

	</div>
</div>
<!-- 페이지 -->
<div class="pagenate_wrap float_left">
	<ul class="pagenation">
		<c:out value="${pageNavigation}" escapeXml="false"/>
	</ul>
</div><!-- //pagenate_wrap -->

<div class="btn_wrap float_right">
	<a href="boardForm.do?cate_id=${theForm.cate_id}" class="btn primary">등록</a>
</div>