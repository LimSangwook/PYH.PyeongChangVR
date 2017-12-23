<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/comm/taglib.jsp"%>

<section class="mementoDetail">
  <div class="mementoDetail_img">
	<div class="bigSize"><img src="${uploadDefaultUrl}/${uploadImgEditorDir}${result.master_image}" alt="<c:out value="${result.title}"/>" title="<c:out value="${result.title}"/>"></div>
	<ul class="mementoDetail_thumb">		
		<c:set var="noImageCnt" value="0"/>
		<c:forEach var="data" items="${fileList}" varStatus="i">
		<c:set var="noImageCnt" value="${noImageCnt + 1}"/>
		<c:set var="masterImage" value="${data.file_path}${data.save_file_name}"/>		 
			<li>
				<a href="${uploadDefaultUrl}/${uploadImgEditorDir}${data.file_path}${data.save_file_name}">
				<img src="${uploadDefaultUrl}/${uploadImgThumbDir}${data.file_path}${data.save_file_name}" alt="<c:out value="${result.title }"/>" title="<c:out value="${result.title }"/>">
				</a>
				<c:if test="${result.master_image eq masterImage}">
					<span class="on">선택</span>
				</c:if>
			</li>		
		</c:forEach>
		<c:forEach begin="1" end="${4 - noImageCnt}">
			<li><img src="/assets/plugin/fileupload/img/noimage2.png" alt=""></li>
		</c:forEach>
	  </ul>
  </div>
  <div class="table">
		<div class="tbl_header">
			<div class="view_subject"><c:out value="${result.title}"/></div>
		</div>
	
		<table class="tbl_view" summary="기념품 상세보기">
		<caption>기념품 목록의 제목에 해당하는 상세정보를 보여주는 테이블입니다</caption>
		<colgroup>
			<col width="22%">
			<col width="*">
		</colgroup>
		<tbody>
			<tr>
				<th>번호</th>
				<td>No. ${result.board_key}</td>
			</tr>
			<tr>
				<th>소재지</th>
				<td><c:out value="${result.add_field1}"/></td>
			</tr>
			<tr>
				<th>판매가격</th>
				<td><c:out value="${result.add_field2}"/></td>
			</tr>
			<tr>
				<th>판매수량</th>
				<td><span class="dimmedTxt"><c:out value="${result.add_field3}"/></span></td>
			</tr>
			<tr>
				<th>판매기간</th>
				<td><span class="dimmedTxt"><c:out value="${result.start_day}"/> ~ <c:out value="${result.end_day}"/></span></td>
			</tr>
		</tbody>
	</table>

		<div class="btnArea">
			<button type="button" class="dimmed" onClick="location.href='boardList.do?page=${theForm.page}${defaultParameter}'">목록</button>
			<button type="button" onClick="var newWindow = window.open('about:blank');newWindow.location.href='${result.link_address}'">바로가기</button>
		</div>
	</div>
  <div class="descriptionArea">
	<div class="descriptionTit"><span>상세내용</span></div>
	<div class="description">
		<c:out value="${result.content}" escapeXml="false"/>
	</div>
  </div>
</section>