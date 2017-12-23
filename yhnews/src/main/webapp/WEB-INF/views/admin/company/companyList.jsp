<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/comm/taglib.jsp"%>

<h3 class="tit-h3">참여업체목록</h3>

<fieldset>
	<legend></legend>

		<form:form commandName="theForm" action="companyList.do" method="get">		
			<div class="search_wrap">
				<form:select path="search_column">
					<form:option value="" label="선택"/>
					<form:option value="1" label="업체명"/>				
					<form:option value="2" label="대표자"/>
					<form:option value="3" label="대표번호"/>
				</form:select>
				<form:input path="search_keyword" title="검색어 입력" cssClass="col25"/>
				<button type="submit" class="btn inverse btn_small"><span class="ico ico_search"></span>검색</button>
			</div><!-- //search_wrap -->
		</form:form>

		<table class="table_basic">
			<caption>테이블</caption>
			<colgroup>
				<col style="width:auto;">
				<col style="width:auto;">
				<col style="width:auto;">
				<col style="width:auto;">
				<col style="width:auto;">
				<col style="width:auto;">				
				<col style="width:auto;">
			</colgroup>
			<thead>
				<tr>
					<th scope="col">번호</th>
					<th scope="col">회사로고</th>
					<th scope="col">업체명</th>
					<th scope="col">대표자</th>
					<th scope="col">산업분야</th>
					<th scope="col">대표번호</th>
					<th scope="col">노출상태</th>
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
								<a href="companyForm.do?company_key=${data.company_key}"><img src="${uploadDefaultUrl}/${uploadImgThumbDir}${data.master_image}"/></a>
							</c:when>
							<c:otherwise>
								<a href="companyForm.do?company_key=${data.company_key}"><img src="/assets/plugin/fileupload/img/noimage2.png"/></a>
							</c:otherwise>
							</c:choose>
						</td>
						<td><a href="companyForm.do?company_key=${data.company_key}"><c:out value="${data.company_name}"/></a></td>
						<td><c:out value="${data.ceo_name}"/></td>
						<td><c:out value="${data.business_kind_name}"/></td>
						<td><c:out value="${data.master_phone}"/></td>						
						<td>
							<c:choose>
							<c:when test="${data.status eq 'Y'}"><span class="label primary">공개</span></c:when>
							<c:otherwise><span class="label inverse">비공개</span></c:otherwise>
							</c:choose>
						</td>
					</tr>
				</c:forEach>
				</c:when>
				<c:otherwise>
					<tr><td colspan="7"><spring:message code="MSG.NO.DATA"/></td></tr>
				</c:otherwise>
				</c:choose>
				
			</tbody>
		</table>

		 <div class="clearfix">
			<div class="pagenate_wrap float_left">
				<ul class="pagenation">
					<c:out value="${pageNavigation}" escapeXml="false"/>
				</ul>
			</div>

			<div class="btn_wrap float_right">
				<a href="companyForm.do" class="btn primary">등록</a>
			</div>
		</div>	
</fieldset>
