<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/comm/taglib.jsp"%>

<h3 class="tit-h3">포럼/박람회 목록</h3>

<table class="table_basic">
	<caption>테이블</caption>
	<colgroup>
		<col style="width:10%;">
		<col style="width:auto;">
		<col style="width:20%;">
	</colgroup>
	<tbody>		
		<c:choose>
		<c:when test="${!empty result}">
			<c:forEach var="data" items="${result}">
				<tr>
					<td>
						<c:choose>
						<c:when test="${!empty data.master_image}">
							<img src="${uploadDefaultUrl}/${uploadImgThumbDir}${data.master_image}"/>
						</c:when>
						<c:otherwise>
							<img src="/assets/plugin/fileupload/img/noimage2.png"/>
						</c:otherwise>
						</c:choose>
					</td>
					<td class="textAlign_left">
					<c:out value="${data.event_name}"/>
					<c:choose>
					<c:when test="${data.status eq '1'}"><span class="label primary">진행중</span></c:when>
					<c:when test="${data.status eq '2'}"><span class="label success">지난박람회</span></c:when>
					<c:when test="${data.status eq '3'}"><span class="label inverse">비공개</span></c:when>
					</c:choose>
					<br /><br />					
					<strong>기&nbsp;&nbsp;&nbsp;간</strong> : <c:out value="${data.period}"/><br />
					<strong>장&nbsp;&nbsp;&nbsp;소</strong> : <c:out value="${data.location}"/><br />
					<strong>주&nbsp;&nbsp;&nbsp;최</strong> : <c:out value="${data.host_name}"/><br />
					<strong>주&nbsp;&nbsp;&nbsp;관</strong> : <c:out value="${data.organize_name}"/><br />
					<strong>회차 / 년도</strong> : <c:out value="${data.turn_num}"/> / <c:out value="${data.event_year}"/><br />
					<span class="label">노출순위 ${data.order_level}</span>					
					</td>
					<td>
						<a href="forumForm.do?forum_key=${data.forum_key}" class="btn primary btn_small">자세히 보기</a>
					</td>
				</tr>
			</c:forEach>
		</c:when>
		<c:otherwise>
			<tr>
				<td colspan="2"><spring:message code="MSG.NO.DATA"/></td>
			</tr>
		</c:otherwise>
		</c:choose>		
	</tbody>
</table>

<div class="btn_wrap float_right">
	<a href="forumForm.do" class="btn primary">등록</a>
</div>