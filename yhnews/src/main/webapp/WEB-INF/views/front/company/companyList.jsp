<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/comm/taglib.jsp"%>

<div class="bbs_wrap">

	<!-- 검색 -->
	<fieldset>
	<legend>게시물 검색</legend>
	
	<form:form commandName="theForm" action="companyList.do" method="get">		
		<div class="search_wrap">
			<form:select path="search_column">
				<form:option value="" label="선택"/>
				<form:option value="1" label="업체명"/>				
				<form:option value="2" label="대표자"/>
				<form:option value="3" label="대표번호"/>
			</form:select>
			<form:input path="search_keyword" title="검색어 입력" cssClass="srh_word"/>
			<button type="submit" class="btn inverse btn_small"><span class="ico ico_search"></span>검색</button>
		</div><!-- //search_wrap -->
	</form:form>
	</fieldset>

	<div class="guide_total">
		총 <strong><fmt:formatNumber value="${theForm.total_count}"/></strong>개의 업체가 있습니다.
	</div>

	<table class="table_bbs">
		<caption>리스트</caption>
		<colgroup class="mobile_hidden">
			<col style="width:auto;" />
			<col style="width:240px;" />
			<col style="width:auto;" />
			<col style="width:auto;" />
			<col style="width:auto;" />
			<col style="width:auto;" />
		</colgroup>
		<thead>
			<tr>
				<th scope="col">번호</th>
				<th scope="col">회사로고</th>
				<th scope="col">업체명</th>
				<th scope="col" class="mobile_hidden">대표자</th>
				<th scope="col" class="mobile_hidden">산업분야</th>
				<th scope="col" class="mobile_hidden">연락처</th> 
			</tr>
		</thead>
		<tbody>
			<c:choose>
			<c:when test="${!empty result}">
				<c:forEach var="data" items="${result}" varStatus="i">
				<c:set var="num" value="${theForm.total_count - ((theForm.page - 1) * theForm.pageSize) - (i.count - 1)}" />
					<tr>
						<td>${num}</td>
						<td>
							<c:choose>
							<c:when test="${!empty data.master_image}">
								<p class="co_logo">
									<a href="companyDetail.do?company_key=${data.company_key}"><img src="${uploadDefaultUrl}/${uploadImgThumbDir}${data.master_image}"/></a>
								</p>
							</c:when>
							<c:otherwise>
								<p class="co_logo noImg"></p>
							</c:otherwise>
							</c:choose>							
						</td>
						<td><a href="companyDetail.do?company_key=${data.company_key}"><c:out value="${data.company_name}"/></a></td>
						<td class="mobile_hidden"><c:out value="${data.ceo_name}"/></td>
						<td class="mobile_hidden"><c:out value="${data.business_kind_name}"/></td>
						<td class="mobile_hidden"><c:out value="${data.master_phone}"/></td>
					</tr>
				</c:forEach>
			</c:when>
			<c:otherwise>
			<tr>
				<td colspan="6" class="none"><spring:message code="MSG.NO.DATA"/></td>
			</tr>
			</c:otherwise>
			</c:choose>
			
		</tbody>
	</table>

	<div class="pagenate_wrap">
	<ul class="pagenation">
		<c:out value="${pageNavigation}" escapeXml="false"/>
	</ul>
	</div>

</div><!-- //bbs_wrap -->

<script>
$( document ).ready(function() {
	//게시판 이미지 100%
	var coImg = $('.co_logo img');
	var coWidth = $('.co_logo').width();
	coImg.each(function (i) {
		if (coImg.eq(i).height() > 50){
			coImg.eq(i).css("height", "100%");

			if (coImg.eq(i).width() > coWidth){
				coImg.eq(i).css("width", "100%");
				coImg.eq(i).css("height", "auto");
			}else{
				coImg.eq(i).css("width", "auto");
			}
		}
	});
});
</script>