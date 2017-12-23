<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/comm/taglib.jsp"%>

<ul class="tabMenu menu_${fn:length(bannerAreaCodeList)}">
	<c:forEach var="data" items="${bannerAreaCodeList}">
	<li><a href="bannerList.do?area_code=${data.code}" <c:if test="${data.code eq theForm.area_code}">class="active"</c:if>><span>${data.code_name}</span></a></li>
	</c:forEach>
</ul>

<table class="table_basic">
	<caption>테이블</caption>
	<colgroup>		
		<col style="width:auto;" />
		<col style="width:auto;" />
		<col style="width:auto;" />
		<col style="width:auto;" />
		<col style="width:auto;" />
		<col style="width:auto;" />
		<col style="width:auto;" />
	</colgroup>
	<thead>
		<tr>
			<th scope="col">번호</th>
			<th scope="col">제목</th>
			<th scope="col">노출순서</th>
			<th scope="col">노출기간</th>
			<th scope="col">기간상태</th>
			<th scope="col">사용상태</th>
			<th scope="col">등록일자</th>
		</tr>
	</thead>
	<tbody>
		<c:choose>
		<c:when test="${!empty result}">
		<c:forEach var="data" items="${result}" varStatus="i">
		<c:set var="num" value="${theForm.total_count - ((theForm.page - 1) * theForm.pageSize) - (i.count - 1)}" />
			<tr>
				<td>${num}</td>
				<td class="textAlign_left">
					<c:if test="${data.area_code eq 'MUSEUM'}">
					<c:choose>
					<c:when test="${data.type1 eq '1'}"><span class="label primary">공립</span></c:when>
					<c:otherwise><span class="label success">사립</span></c:otherwise>
					</c:choose>
					</c:if>
					<a href="bannerForm.do?banner_key=<c:out value="${data.banner_key}"/>&area_code=${data.area_code}${defaultParameter}"><c:out value="${util:crop(data.title,100,'...','UTF-8')}"/></a>
				</td>
				<td><c:out value="${data.order_level}"/></td>
				<td><c:out value="${data.start_show_date}"/> ~ <c:out value="${data.end_show_date}"/></td>
				<td>
					<c:choose>
					<c:when test="${data.is_show_yn eq 'Y'}">
						<span class="btn btn_xsmall primary">노출</span>
					</c:when>
					<c:when test="${data.is_show_yn eq 'N'}">
						<span class="btn btn_xsmall danger">비노출</span>
					</c:when>
					</c:choose>
				</td>
				<td>
					<c:choose>
					<c:when test="${data.status eq 'Y'}">
						<span class="btn btn_xsmall primary">사용</span>
					</c:when>
					<c:when test="${data.status eq 'N'}">
						<span class="btn btn_xsmall danger">미사용</span>
					</c:when>
					</c:choose>
				</td>
				<td><fmt:formatDate value="${data.reg_date}" pattern="yyyy.MM.dd"/></td>
			</tr>
		</c:forEach>		
		</c:when>
		<c:otherwise>
			<tr><td colspan="7"><spring:message code="MSG.NO.DATA"/></td></tr>
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
	<a href="bannerForm.do?area_code=${theForm.area_code}" class="btn primary">등록</a>
</div>