<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/comm/taglib.jsp"%>

<!-- ImageUploader  -->
<script type="text/javascript" src="/assets/plugin/fileuploadpop/jindo.min.js" charset="utf-8"></script>
<script type="text/javascript" src="/assets/plugin/fileuploadpop/jindo.fileuploader.js" charset="utf-8"></script>
<script type="text/javascript" src="/assets/plugin/fileuploadpop/attach_photo.js" charset="utf-8"></script>
<!--// ImageUploader  -->

<script type="text/javascript">
var act = "${theForm.act}";

jQuery(function(){	
	// jquery 달력 세팅 (오늘날짜 기준)
	setMinDateCalendar("yy-mm-dd");	
	// 등록/수정
	jQuery('.btnSave').on('click',function(){
		if(formValidate()){			
			if(jQuery('#link_address').val().isNvl() && jQuery('#link_address').val().indexOf('http://') == -1 && jQuery('#link_address').val().indexOf('https://') == -1){
				alert('http:// 또는 https://를 포함한 전체 주소를 입력해 주시기 바랍니다.');
				jQuery('#link_address').focus();
				return;
			}
			if(act == 'update'){
				if(!confirm('수정하시겠습니까?')){
					return;
				}
			} else {
				if(!confirm('등록하시겠습니까?')){
					return;
				}
			}			
			jQuery('#theForm').submit();
		}
	});	
	// 삭제
	jQuery(".btnDelete").on("click",function(){
		if(confirm('삭제하시겠습니까?')){
			jQuery('#deleteForm').submit();
		}
	});
});
</script>

<fieldset>
<legend></legend>

<!-- delete form start -->
<form:form id="deleteForm" commandName="theForm" action="popupSave.do">
<form:hidden path="act" id="deleteAct" value="delete"/>
<form:hidden path="popup_key"/>
</form:form>

<form:form commandName="theForm" action="popupSave.do">
<form:hidden path="act"/>
<form:hidden path="popup_key"/>
<form:hidden path="real_file_name"/>
<form:hidden path="save_file_name"/>
<form:hidden path="file_path"/>				
<input type="hidden" name="photoFile" id="photoFile"/>
	
<table class="table_form">
	<caption>팝업 정보</caption>
	<colgroup>
		<col style="width:20%;" />
		<col style="width:80%;" />
	</colgroup>
	<tbody>	
		<tr>
			<th scope="row">상태</th>
			<td>
				<form:select path="status" title="상태" isNullCheck="true">						
					<form:option value="Y" label="사용"/>
					<form:option value="N" label="사용안함"/>
				</form:select>
			</td>
		</tr>
		<tr>
			<th scope="row">팝업유형</th>
			<td>
				<form:select path="kind" title="팝업유형" isNullCheck="true">						
					<form:option value="1" label="윈도우창"/>
					<form:option value="2" label="임베디드"/>
				</form:select>
			</td>
		</tr>
		<tr>
			<th scope="row">팝업제목</th>
			<td><form:input path="title" title="팝업제목" cssClass="col50" isNullCheck="true" maxlength="100"/></td>
		</tr>
		<tr>
			<th scope="row">새창열림여부</th>
			<td>
				<form:select path="is_new_window" title="새창열림여부" isNullCheck="true">						
					<form:option value="N" label="현재창열림"/>
					<form:option value="Y" label="새창열림"/>
				</form:select>
			</td>
		</tr>
		<tr>
			<th scope="row">연결주소</th>
			<td>
				<form:input path="link_address" title="연결주소" cssClass="col50" maxlength="100"/>
				<p>※ http:// 또는 https://를 포함한 전체 주소를 입력해 주시기 바랍니다.</p>
			</td>
		</tr>
		<tr>
			<th scope="row">팝업가로사이즈</th>
			<td><form:input path="width_size" title="팝업가로사이즈" cssClass="col16 numberOnly" isNullCheck="true" isNumberCheck="true" maxlength="3"/> px</td>
		</tr>
		<tr>
			<th scope="row">팝업세로사이즈</th>
			<td><form:input path="height_size" title="팝업세로사이즈" cssClass="col16 numberOnly" isNullCheck="true" isNumberCheck="true" maxlength="3"/> px</td>
		</tr>
		<tr>
			<th scope="row">상단위치여백</th>
			<td><form:input path="top_margin" title="상단위치여백" cssClass="col16 numberOnly" isNullCheck="true" isNumberCheck="true" maxlength="3"/> px</td>
		</tr>
		<tr>
			<th scope="row">왼쪽위치여백</th>
			<td><form:input path="left_margin" title="왼쪽위치여백" cssClass="col16 numberOnly" isNullCheck="true" isNumberCheck="true" maxlength="3"/> px</td>
		</tr>
		<tr>
			<th scope="row">노출기간</th>
			<td>
				<p>
					<form:input path="start_show_date" title="노출시작기간" cssClass="col16 inputCal" isNullCheck="true" maxlength="10" readonly="true"/> ~
					<form:input path="end_show_date" title="노출종료기간" cssClass="col16 inputCal" isNullCheck="true" maxlength="10" readonly="true"/>
					
				</p>
			</td>
		</tr>
	</tbody>
</table>
</form:form>

<form name="fileForm" method="post" enctype="multipart/form-data">
<table class="table_form">
	<caption>이미지 정보</caption>
	<colgroup>
		<col style="width:20%;" />
		<col style="width:80%;" />
	</colgroup>
	<tbody>
	<tr>
		<th scope="row">이미지</th>
		<td>				
			<div id="imagePreview">
				<c:choose>
				<c:when test="${!empty theForm.save_file_name}">
					<img src="/upload/cont${theForm.file_path}${theForm.save_file_name}" alt="" width='238' height='174' >
					<c:set var="display" value="display:none;"/>
				</c:when>
				<c:otherwise>
					<img src="/assets/plugin/fileupload/img/noimage2.png" alt="썸네일이미지">
				</c:otherwise>
				</c:choose>
				
			</div>
			<ul class="list_style_2" id="fileUploadList">
				<c:if test="${!empty theForm.save_file_name}">
				<li><a href="/upload${theForm.file_path}${theForm.save_file_name}" class="file-name" target="_blank">${theForm.real_file_name}</a>
					<span class="ico ico_close delFile" style="cursor:pointer;">삭제</span>
				</li>
				</c:if>				
			</ul>
			<div id="photoUploadForm" style="${display}">				
				<input type="file" class="upload" id="uploadInputBox" name="Filedata">
				<p><strong>10MB</strong>이하의 이미지 파일만 등록할 수 있습니다.<br>(JPG, GIF, PNG, BMP)</p>				
			</div>					
		</td>
	</tr>
	</tbody>
</table>
</form>

<div class="btn_wrap textAlign_center">
	<a href="popupList.do" class="btn white">취소</a>
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

</fieldset>