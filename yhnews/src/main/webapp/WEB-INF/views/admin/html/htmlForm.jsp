<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/comm/taglib.jsp"%>

<!-- SmartEditor 사용시 include head-->
<jsp:include page="/WEB-INF/views/comm/editor_head.jsp"/>

<script type="text/javascript">
var act = "${theForm.act}";
// 콘텐츠 삽입
/* function pasteImage(imageHtml){
	oEditors.getById["html"].exec("PASTE_HTML", [imageHtml]);
} */

$(function(){	
	// 등록/수정
	$('.btnSave').on('click',function(){
		if(formValidate()){
			oEditors.getById["html"].exec("UPDATE_CONTENTS_FIELD", []);
			if($('#html').val() == "<p>&nbsp;</p>" || $('#content').val() == ""){alert("내용을 입력해 주시기 바랍니다.");return;}
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
	
	// 삭제
	$(".btnDelete").on("click",function(){
		if(confirm('삭제하시겠습니까?')){
			$('#deleteForm').submit();
		}
	});
	
	// 미리보기
	$('.btnPreview').on('click',function(e){
		e.preventDefault();
		oEditors.getById["html"].exec("UPDATE_CONTENTS_FIELD", []);
		$('#htmlViewArea').html($('#html').val());
		// 미리보기 팝업
		regist_view('previewPop');
	});
});
</script>

<fieldset>
<legend></legend>

<!-- delete form start -->
<form:form id="deleteForm" commandName="theForm" action="htmlSave.do">
<form:hidden path="act" id="deleteAct" value="delete"/>
<form:hidden path="page_seq"/>
</form:form>

<form:form commandName="theForm" action="htmlSave.do">
<form:hidden path="act"/>
<form:hidden path="page_seq"/>

	<table class="table_form">
		<caption>콘텐츠 정보</caption>
		<colgroup>
			<col style="width:20%;" />
			<col style="width:80%;" />
		</colgroup>
		<tbody>
			<tr>
				<th scope="row">상태</th>
				<td>
					<form:select path="status" title="상태" isNullCheck="true">
						<form:option value="" label="선택"/>
						<form:option value="Y" label="사용"/>
						<form:option value="N" label="사용안함"/>
					</form:select>
				</td>
			</tr>
			<tr>
				<th scope="row">제목</th>
				<td><form:input path="title" title="제목" cssClass="col75" isNullCheck="true" maxlength="100"/></td>
			</tr>
			<tr>
				<th scope="row">콘텐츠 내용</th>
				<td>
				<form:textarea path="html" rows="30" cssClass="col75"/>				
		        <c:set var="editorFieldName" value="html" scope="request"/>
				<jsp:include page="/WEB-INF/views/comm/editor.jsp"/>				
				</td>
			</tr>
		</tbody>
	</table>
</form:form>

	<!-- 첨부파일 영역 -->
	<%-- <c:set var="masterImageBtnUse" value="N" scope="request"/>
	<jsp:include page="/WEB-INF/views/comm/attach_form.jsp"/> --%>
	
	<div class="btn_wrap textAlign_center">
		<a href="htmlList.do" class="btn white">취소</a>
		<a href="#" class="btn success btnPreview">미리보기</a>
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

<!-- 미리보기 팝업 -->
<div id="previewPop" class="group_regist popup_wrap" style="display:block; width: 60%">
	<div class="popup_header">미리보기</div>
	<a class="ico ico_close">창닫기</a>
	<div class="popup_body" id="htmlViewArea">
		
	</div>
</div>
<!-- 미리보기 팝업 -->