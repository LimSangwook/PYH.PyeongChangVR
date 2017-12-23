<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/comm/taglib.jsp"%>

<!-- 지난포럼/박람회 관리 탭 -->
<jsp:include page="/WEB-INF/views/admin/forum/incForumTab.jsp"/>

<h3 class="tit-h3">${forumInfo.event_name}</h3>

<fieldset>
	<legend></legend>
	

		<form:form commandName="theForm" action="forumSpeakerList.do" method="get">
		<form:hidden path="forum_key"/>
			<div class="search_wrap">			
				<form:select path="kind">
				<form:option value="" label="분과구분"/>
				<form:options items="${speakerKindList}" itemValue="kind" itemLabel="kind_name"/>
				</form:select>
				<form:select path="search_column">
					<form:option value="1" label="연사자명"/>				
				</form:select>
				<form:input path="search_keyword" title="검색어 입력" cssClass="col25"/>
				<button type="submit" class="btn inverse btn_small"><span class="ico ico_search"></span>검색</button>
			</div><!-- //search_wrap -->
		</form:form>

		<table class="table_basic">
			<caption>연사 정보 테이블</caption>
			<colgroup>
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
					<th scope="col">연사자명</th>
					<th scope="col">구분</th>
					<th scope="col">국가</th>
					<th scope="col">주제</th>
					<th scope="col">상태</th>					
				</tr>
			</thead>
			<tbody>
				<c:choose>
				<c:when test="${!empty result}">
				<c:forEach var="data" items="${result}" varStatus="i">
				<c:set var="num" value="${theForm.total_count - ((theForm.page - 1) * theForm.pageSize) - (i.count - 1)}" />
					<tr>
						<td>${num}</td>
						<td><a href="forumSpeakerForm.do?speaker_key=${data.speaker_key}&forum_key=${data.forum_key}"><c:out value="${data.name}"/></a></td>
						<td><c:out value="${data.kind_name}"/><c:out value="${data.speaker_kind_detail}"/></td>
						<td><c:out value="${data.country}"/></td>
						<td><c:out value="${data.subject}"/></td>
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
					<tr><td colspan="6"><spring:message code="MSG.NO.DATA"/></td></tr>
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
				<a href="forumSpeakerForm.do?forum_key=${forumInfo.forum_key}" class="btn primary">등록</a>
			</div>
		</div>	
</fieldset>