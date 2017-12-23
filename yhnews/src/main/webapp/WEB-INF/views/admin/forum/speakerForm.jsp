<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/comm/taglib.jsp"%>

<!-- SmartEditor 사용시 include head-->
<jsp:include page="/WEB-INF/views/comm/editor_head.jsp"/>

<script type="text/javascript">
var act = "${theForm.act}";
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
	
	// 데이터 삭제시
	$('.btnDelete').on('click',function(){
		if(confirm('정말로 삭제하시겠습니까?')){
			$('#deleteForm').submit();
		}
	});
	
	// 등록/수정
	$('.btnSave').on('click',function(){
		if(formValidate()){	
			
			oEditors.getById["content"].exec("UPDATE_CONTENTS_FIELD", []);			
			
			if(act == 'update'){
				if(!confirm('수정하시겠습니까?')){
					return;
				}
			} else {
				if(!confirm('등록하시겠습니까?')){
					return;
				}
			}			
			$('#theForm').submit();
		}
	});
});
</script>



<form:form id="deleteForm" commandName="theForm" action="forumSpeakerSave.do">
<input type="hidden" name="act" value="delete"/>
<form:hidden path="forum_key"/>
<form:hidden path="speaker_key"/>
</form:form>
				
<form:form commandName="theForm" action="forumSpeakerSave.do">
<form:hidden path="act"/>
<form:hidden path="forum_key"/>
<form:hidden path="speaker_key"/>

<!-- 지난포럼/박람회 관리 탭 -->
<jsp:include page="/WEB-INF/views/admin/forum/incForumTab.jsp"/>

<h3 class="tit-h3">${forumInfo.event_name}</h3>

<table class="table_form">
	<caption>연사정보</caption>
	<colgroup>
		<col style="width:20%;" />
		<col style="width:auto;" />
		<col style="width:20%;" />
		<col style="width:auto;" />
	</colgroup>
	<tbody>
		<tr>
			<th scope="row">연사자 사진</th>
			<td colspan="3">
				<span id="imagePreview">
					<c:choose>
					<c:when test="${!empty theForm.profile_photo}">
						<img id="profile_photoPreview" src="${uploadDefaultUrl}/${uploadImgThumbDir}${theForm.profile_photo}" alt="썸네일이미지">
					</c:when>
					<c:otherwise>
						<img id="profile_photoPreview" src="/assets/plugin/fileupload/img/noimage2.png" alt="썸네일이미지">
					</c:otherwise>
					</c:choose>
				</span>
				<form:hidden path="profile_photo" value="${theForm.profile_photo}"/>
				<p id="btnPhotoAdd_profile_photo" style="<c:if test="${!empty theForm.profile_photo}">display:none;</c:if>"><a href="/attachPhotoPop.do?imageArea=profile_photo" class="btn inverse btn_xsmall" onclick="popup(this.href, 'photoUpload', '385', '450', 'no', 'no', 3); return false;">이미지 첨부</a></p>
				<p id="btnPhotoDel_profile_photo" style="<c:if test="${empty theForm.profile_photo}">display:none;</c:if>"><a href="#" class="btn inverse btn_xsmall btnPhotoDel" onclick="deletePhoto('profile_photo'); return false;">이미지 삭제</a></p>
			</td>			
		</tr>
		<tr>
			<th scope="row">노출상태</th>
			<td>
				<form:select path="status" title="노출상태" isNullCheck="true">						
					<form:option value="Y" label="공개"/>
					<form:option value="N" label="비공개"/>					
				</form:select>
			</td>
			<th scope="row">노출순서</th>
			<td colspan="3"><form:input path="order_level" title="노출순서" cssClass="wid100 numberOnly" isNullCheck="true" isNumberCheck="true" maxlength="2"/></td>
		</tr>
		<tr>
			<th scope="row">분과구분</th>
			<td>
				<form:select path="kind" title="분과구분">
					<form:option value="" label="선택"/>						
					<form:options items="${speakerKindList}" itemValue="kind" itemLabel="kind_name"/>					
				</form:select>
			</td>
			<th scope="row">분과세부명</th>
			<td><form:input path="speaker_kind_detail" title="분과세부명" cssClass="wid100" maxlength="10"/></td>
		</tr>		
		<tr>
			<th scope="row">연사자명</th>
			<td><form:input path="name" title="연사자명" cssClass="wid100" isNullCheck="true" maxlength="25"/></td>
			<th scope="row">국가</th>
			<td><form:input path="country" title="국가" cssClass="wid100" isNullCheck="true" maxlength="20"/></td>
		</tr>
		<tr>
			<th scope="row">소속</th>
			<td colspan="3">
				<form:input path="belong" title="소속" cssClass="col100" isNullCheck="true" maxlength="200"/>
				<!-- <p><em>※ 소속이 여러개인 경우 ','로 구분을 하시기 바랍니다.</em></p> -->
			</td>
		</tr>
		<tr>
			<th scope="row">주제</th>
			<td colspan="3"><form:input path="subject" title="주제" cssClass="col100" isNullCheck="true" maxlength="100"/></td>
		</tr>
		<tr>			
			<th scope="row">내용</th>
			<td colspan="3">
				<form:textarea path="content" rows="30" cssClass="col75"/>
	        	<c:set var="editorFieldName" value="content" scope="request"/>
				<jsp:include page="/WEB-INF/views/comm/editor.jsp"/>
			</td>
		</tr>	
	</tbody>
</table>

</form:form>

<div class="btn_wrap textAlign_center">
	<a href="forumSpeakerList.do?forum_key=${forumInfo.forum_key}" class="btn white">목록</a>
	<c:choose>
	<c:when test="${theForm.act eq 'update'}">
		<a href="#" class="btn danger btnDelete">삭제</a>
		<a href="#" class="btn primary btnSave">수정</a>
	</c:when>
	<c:otherwise>
		<a href="#" class="btn primary btnSave">등록</a>
	</c:otherwise>
	</c:choose>
</div>
