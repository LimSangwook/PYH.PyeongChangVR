<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/comm/taglib.jsp"%>

<!-- SmartEditor 사용시 include head-->
<jsp:include page="/WEB-INF/views/comm/editor_head.jsp"/>

<script type="text/javascript">
var act = "${forumInfo.act}";
function setPhotoInfo(imageArea, oFileInfo){
	var array = oFileInfo.split("|");
	var saveFileName 		= array[1];
	var filePath 				= array[2];
	var imageUrl				= filePath+saveFileName;
	// 이미지 메타정보
	$('#'+imageArea).val(imageUrl);
	// 미리보기 이미지
	$('#'+imageArea+'Preview').attr('src','/upload/thumb'+imageUrl);
	// 사진첨부 버튼 감추기
	$('#btnPhotoAdd_'+imageArea).hide();
	// 삭제 버튼 보이기
	$('#btnPhotoDel_'+imageArea).show();
} 

function deletePhoto(imageArea){
	$('#'+imageArea).val('');
	// 미리보기 이미지
	$('#'+imageArea+'Preview').attr('src','/assets/plugin/fileupload/img/noimage2.png');
	// 사진첨부 버튼 보이기
	$('#btnPhotoAdd_'+imageArea).show();
	// 삭제 버튼 감추기
	$('#btnPhotoDel_'+imageArea).hide();
}

$(function(){	
	// jquery 달력 세팅
	setCalendar("yy-mm-dd");
	
	// 데이터 삭제시
	$('.btnDelete').on('click',function(){
		if(confirm('정말로 삭제하시겠습니까?')){
			$('#deleteForm').submit();
		}
	});
	
	// 등록/수정
	$('.btnSave').on('click',function(){
		if(formValidate()){	
			
			oEditors.getById["content1"].exec("UPDATE_CONTENTS_FIELD", []);
			oEditors.getById["content2"].exec("UPDATE_CONTENTS_FIELD", []);
			oEditors.getById["content3"].exec("UPDATE_CONTENTS_FIELD", []);
			oEditors.getById["content4"].exec("UPDATE_CONTENTS_FIELD", []);
			
			if($('#act').val() == 'update'){
				if(!confirm('수정하시겠습니까?')){
					return;
				}
			} else {
				if(!confirm('등록하시겠습니까?')){
					return;
				}
			}			
			$('#forumInfo').submit();
		}
	});
});
</script>

<form:form id="deleteForm" commandName="forumInfo" action="forumSave.do">
<input type="hidden" name="act" value="delete"/>
<form:hidden path="forum_key"/>
</form:form>
				
<form:form commandName="forumInfo" action="forumSave.do">
<form:hidden path="act"/>
<form:hidden path="forum_key"/>

<!-- 지난포럼/박람회 관리 탭 -->
<jsp:include page="/WEB-INF/views/admin/forum/incForumTab.jsp"/>

<h3 class="tit-h3">${forumInfo.event_name}</h3>

<table class="table_form">
	<caption>포럼/박물관정보</caption>
	<colgroup>
		<col style="width:20%;" />
		<col style="width:80%;" />
	</colgroup>
	<tbody>	
		<tr>
			<th scope="row">노출상태</th>
			<td>
				<form:select path="status" title="노출상채" isNullCheck="true">						
					<form:option value="3" label="비공개"/>
					<form:option value="2" label="지난박람회"/>
					<form:option value="1" label="진행중"/>
				</form:select>
			</td>
		</tr>
		<tr>
			<th scope="row">노출순서</th>
			<td>
				<form:input path="order_level" title="노출순서" cssClass="wid100 numberOnly" isNullCheck="true" isNumberCheck="true" maxlength="2"/>
			</td>
		</tr>
		<tr>
			<th scope="row">행사회차</th>
			<td><form:input path="turn_num" title="행사회차" cssClass="wid100" isNullCheck="true" maxlength="5"/></td>
		</tr>
		<tr>
			<th scope="row">행사년도</th>
			<td><form:input path="event_year" title="행사년도" cssClass="wid100 numberOnly" isNullCheck="true" isNumberCheck="true" maxlength="4"/></td>
		</tr>
		<tr>
			<th scope="row">행사명</th>
			<td><form:input path="event_name" title="행사명" cssClass="col25" isNullCheck="true" maxlength="20"/></td>
		</tr>
		<tr>
			<th scope="row">기간</th>
			<td><form:input path="period" title="기간" cssClass="col50" isNullCheck="true" maxlength="25"/></td>
		</tr>
		<tr>
			<th scope="row">장소</th>
			<td><form:input path="location" title="장소" cssClass="col50" isNullCheck="true" maxlength="25"/></td>
		</tr>
		<tr>
			<th scope="row">주최</th>
			<td><form:input path="host_name" title="주최" cssClass="col50" isNullCheck="true" maxlength="25"/></td>
		</tr>
		<tr>
			<th scope="row">주관</th>
			<td><form:input path="organize_name" title="주관" cssClass="col50" isNullCheck="true" maxlength="25"/></td>
		</tr>
		<tr>
			<th scope="row">행사 이미지</th>
			<td>
				<span id="imagePreview">
					<c:choose>
					<c:when test="${!empty forumInfo.master_image}">
						<img id="master_imagePreview" src="${uploadDefaultUrl}/${uploadImgThumbDir}${forumInfo.master_image}" alt="썸네일이미지">
					</c:when>
					<c:otherwise>
						<img id="master_imagePreview" src="/assets/plugin/fileupload/img/noimage2.png" alt="썸네일이미지">
					</c:otherwise>
					</c:choose>
				</span>
				<form:hidden path="master_image" value="${forumInfo.master_image}"/>
				<p id="btnPhotoAdd_master_image" style="<c:if test="${!empty forumInfo.master_image}">display:none;</c:if>"><a href="/attachPhotoPop.do?imageArea=master_image" class="btn inverse btn_xsmall" onclick="popup(this.href, 'photoUpload', '385', '450', 'no', 'no', 3); return false;">이미지 첨부</a></p>
				<p id="btnPhotoDel_master_image" style="<c:if test="${empty forumInfo.master_image}">display:none;</c:if>"><a href="#" class="btn inverse btn_xsmall btnPhotoDel" onclick="deletePhoto('master_image'); return false;">이미지 삭제</a></p>
			</td>
		</tr>	
		<tr>			
			<th scope="row">게시글 조회기간</th>
			<td>
				<form:input path="board_search_start_day" title="조회시작일" cssClass="col16 inputCal wid100" isNullCheck="true" maxlength="10" readonly="true"/> ~
				<form:input path="board_search_end_day" title="조회종료일" cssClass="col16 inputCal wid100" isNullCheck="true" maxlength="10" readonly="true"/>
				<p><em>※ 커뮤니티 게시글 조회기간 설정</em></p>
				<p><em>※ 공지사항, 보도자료, 포토게시판 게시글 조회 기준입니다.</em></p>
			</td>
		</tr>	
	</tbody>
</table>

<h3 class="tit-h3">포럼/박람회 세부정보</h3>
<table class="table_form">
	<caption>포럼/박람회 세부정보</caption>
	<colgroup>
		<col style="width:20%;" />
		<col style="width:80%;" />
	</colgroup>
	<tbody>	
		<tr>
			<th scope="row">개요 및 일정</th>
			<td>
				<form:textarea path="content1" rows="30" cssClass="col75"/>
	        	<c:set var="editorFieldName" value="content1" scope="request"/>
				<jsp:include page="/WEB-INF/views/comm/editor.jsp"/>
			</td>
		</tr>
		
		<tr>
			<th scope="row">포럼 프로그램</th>
			<td>
				<form:textarea path="content2" rows="30" cssClass="col75"/>
	        	<c:set var="editorFieldName" value="content2" scope="request"/>
				<jsp:include page="/WEB-INF/views/comm/editor.jsp"/>
			</td>
		</tr>
		
		<tr>
			<th scope="row">전시장 소개</th>
			<td>
				<form:textarea path="content3" rows="30" cssClass="col75"/>
	        	<c:set var="editorFieldName" value="content3" scope="request"/>
				<jsp:include page="/WEB-INF/views/comm/editor.jsp"/>
			</td>
		</tr>
		
		<tr>
			<th scope="row">문화공연행사</th>
			<td>
				<form:textarea path="content4" rows="30" cssClass="col75"/>
	        	<c:set var="editorFieldName" value="content4" scope="request"/>
				<jsp:include page="/WEB-INF/views/comm/editor.jsp"/>
			</td>
		</tr>
	</tbody>
</table>

</form:form>

<div class="btn_wrap textAlign_center">
	<a href="forumList.do${defaultParameter}" class="btn white">목록</a>
	<c:choose>
	<c:when test="${forumInfo.act eq 'update'}">
		<a href="#" class="btn danger btnDelete">삭제</a>
		<a href="#" class="btn primary btnSave">수정</a>
	</c:when>
	<c:otherwise>
		<a href="#" class="btn primary btnSave">등록</a>
	</c:otherwise>
	</c:choose>
</div>