<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/comm/taglib.jsp"%>


<section class="table">
	<div class="tbl_header">
		<div class="memento_search">
			<form:form commandName="theForm" action="boardList.do" method="get">
				<fieldset>
					<legend>기념품 검색</legend>
					<form:select path="search_type" title="검색구분 선택">
					<form:option value="1" label="상품명"/>
					<form:option value="2" label="상품설명"/>
					</form:select>
					<form:input path="search_keyword" title="검색어 입력"/>
					<button type="submit">검색</button>
				</fieldset>
			</form:form>
		</div>
		<div class="memento_num">총 <span><fmt:formatNumber value="${theForm.total_count}"/></span>개의 글이 있습니다.</div>
	</div>
	
	<div class="memento_list">
		<c:forEach var="data" items="${result}" varStatus="i">
			<c:set var="num" value="${theForm.total_count - ((theForm.page - 1) * theForm.pageSize) - (i.count - 1)}" />
			<c:set var="imageUrl">
			<c:choose>
			<c:when test="${!empty data.master_image}">${uploadDefaultUrl}/${uploadImgThumbDir}${data.master_image}</c:when>
			<c:otherwise>/assets/plugin/fileupload/img/noimage2.png</c:otherwise>
			</c:choose>
			</c:set>
			
			<div class="memento <c:if test="${i.count % 2 eq 0}">borderRight</c:if>">
				<div class="thumb"><img src="${imageUrl}" alt="<c:out value="${data.title}"/>"></div>
				<dl>
					<dt>
						<span class="mementoNo">No.${data.board_key}</span>
						<span class="mementoTit"><c:out value="${data.title}"/></span>
						<span class="bar"></span>
					</dt>
					<dd>
						<table class="memento_info">
							<colgroup>
								<col width="20%">
								<col width="*">
							</colgroup>
							<tr>
								<th>소재지</th>
								<td><c:out value="${data.add_field1}"/></td>
							</tr>
							<tr>
								<th>판매가격</th>
								<td><c:out value="${data.add_field2}"/></td>
							</tr>
							<tr>
								<th>판매수량</th>
								<td><c:out value="${data.add_field3}"/></td>
							</tr>
							<tr>
								<th>판매기간</th>
								<td><c:out value="${data.start_day}"/> ~ <c:out value="${data.end_day}"/></td>
							</tr>
						</table>
					</dd>
				</dl>
				<button class="btn_down" onClick="location.href='boardView.do?board_key=${data.board_key}${defaultParameter}'">상세보기</button>
			</div>
		</c:forEach>
		
	</div>
  
	<!-- 페이지 -->
	<div class="pagenate_wrap">
		<ul class="pagenation">
			<c:out value="${pageNavigation}" escapeXml="false"/>
		</ul>
	</div>
	
	<!-- 글쓰기 버튼제어 -->
	<jsp:include page="/WEB-INF/views/front/board/include/incBoardWriteBtn.jsp"/>
	
	
</section>