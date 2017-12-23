<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/comm/taglib.jsp"%>

<!-- 다음 주소찾기 팝업 http://postcode.map.daum.net/guide -->
<script src="http://dmaps.daum.net/map_js_init/postcode.v2.js"></script>

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
	
	var addrPoptTemeObj = {	   
	   searchBgColor: "#1071c8", //검색창 배경색	   
	   queryTextColor: "#FFFFFF" //검색창 글자색
	};
	// 주소찾기
	jQuery(".btnFindAddress").on("click",function(e){
		e.preventDefault();
		new daum.Postcode({
			theme: addrPoptTemeObj ,
	        oncomplete: function(data) {	            
	            jQuery("#zip_code").val(data.zonecode);
	            jQuery("#address1").val(data.address);
	            jQuery("#address2").focus();
	        }
	    }).open();
	});
	
	// 이메일 주소 도메인선택
	jQuery('#emailDomainBox').on('change',function(){
		if(!jQuery(this).val().isNvl()){
			jQuery('#email_domain').val('');
			jQuery('#email_domain').focus();
		} else {
			jQuery('#email_domain').val(jQuery(this).val());
		}
	});
	// 대표 이메일 주소 도메인선택
	jQuery('#masterEmailDomainBox').on('change',function(){
		if(!jQuery(this).val().isNvl()){
			jQuery('#master_email_domain').val('');
			jQuery('#master_email_domain').focus();
		} else {
			jQuery('#master_email_domain').val(jQuery(this).val());
		}
	});
	
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



<form:form id="deleteForm" commandName="theForm" action="companySave.do">
<input type="hidden" name="act" value="delete"/>
<form:hidden path="company_key"/>
</form:form>
				
<form:form commandName="theForm" action="companySave.do">
<form:hidden path="act"/>
<form:hidden path="company_key"/>

<h3 class="tit-h3">참여업체정보</h3>

<table class="table_form">
	<caption>참여업체정보</caption>
	<colgroup>
		<col style="width:20%;" />
		<col style="width:auto;" />
	</colgroup>
	<tbody>
		
		<tr>
			<th scope="row">노출상태</th>
			<td>
				<form:select path="status" title="노출상태" isNullCheck="true">						
					<form:option value="Y" label="공개"/>
					<form:option value="N" label="비공개"/>					
				</form:select>
			</td>
		</tr>
		<tr>
			<th scope="row">업체명</th>
			<td><form:input path="company_name" title="업체명" cssClass="col50" isNullCheck="true" maxlength="20"/></td>
		</tr>
		<tr>
			<th scope="row">산업분야</th>
			<td>
				<form:input path="business_kind_name" title="산업분야" cssClass="col50" isNullCheck="true" maxlength="20"/>
			</td>
		</tr>
		<tr>
			<th scope="row">대표자</th>
			<td>
				<form:input path="ceo_name" title="대표자" cssClass="wid100" isNullCheck="true" maxlength="4"/>
			</td>
		</tr>		
		<tr>
			<th scope="row">대표 이메일</th>
			<td>
				<form:input path="master_email_id" title="이메일주소 아이디" cssClass="col16" value="${theForm.masterEmailArr[0]}" isNullCheck="true" maxlength="50"/> @ 
				<form:input path="master_email_domain" title="이메일주소 도메인" cssClass="col16" value="${theForm.masterEmailArr[1]}" isNullCheck="true" maxlength="50"/>
				<select id="masterEmailDomainBox">
				<option value="">직접입력</option>				
				<c:forEach var="data" items="${emailDomainList}">
					<option value="${data.code}">${data.code_name}</option>					
				</c:forEach>
				</select>
			</td>
		</tr>
		<tr>
			<th scope="row">대표번호</th>
			<td>
				<form:input path="master_phone1" title="대표번호" cssClass="col16 numberOnly wid100" value="${theForm.masterPhoneArr[0]}" isNullCheck="true" maxlength="4"/> - 
				<form:input path="master_phone2" title="대표번호" cssClass="col16 numberOnly wid100" value="${theForm.masterPhoneArr[1]}" isNullCheck="true" maxlength="4"/> -
				<form:input path="master_phone3" title="대표번호" cssClass="col16 numberOnly wid100" value="${theForm.masterPhoneArr[2]}" isNullCheck="true" maxlength="4"/>
			</td>
		</tr>		
		<tr>
			<th scope="row">팩스</th>
			<td>
				<form:input path="fax1" title="팩스" cssClass="col16 numberOnly wid100" value="${theForm.faxArr[0]}" maxlength="4"/> - 
				<form:input path="fax2" title="팩스" cssClass="col16 numberOnly wid100" value="${theForm.faxArr[1]}" maxlength="4"/> -
				<form:input path="fax3" title="패스" cssClass="col16 numberOnly wid100" value="${theForm.faxArr[2]}" maxlength="4"/>
			</td>
		</tr>
		
		<tr>
			<th scope="row">주소</th>
			<td>
				<form:hidden path="zip_code"/>
				<form:input path="address1" title="기본주소" cssClass="col50" placeholder="기본주소" isNullCheck="true" maxlength="100" readonly="true"/><a href="#" class="btn btn_small btnFindAddress">주소찾기</a>
				<form:input path="address2" title="상세주소" cssClass="col50" placeholder="상세주소" maxlength="100"/>
			</td>
		</tr>
		
		<tr>
			<th scope="row">담당자명</th>
			<td><form:input path="contact_name" title="담당자명" cssClass="wid100" isNullCheck="true" maxlength="5"/></td>
		</tr>
		<tr>
			<th scope="row">담당자연락처</th>
			<td>
				<form:input path="phone1" title="연락처" cssClass="col16 numberOnly wid100" value="${theForm.phoneArr[0]}" isNullCheck="true" maxlength="4"/> - 
				<form:input path="phone2" title="연락처" cssClass="col16 numberOnly wid100" value="${theForm.phoneArr[1]}" isNullCheck="true" maxlength="4"/> -
				<form:input path="phone3" title="연락처" cssClass="col16 numberOnly wid100" value="${theForm.phoneArr[2]}" isNullCheck="true" maxlength="4"/>
			</td>
		</tr>
		<tr>
			<th scope="row">담당자이메일</th>
			<td>
				<form:input path="email_id" title="이메일주소 아이디" cssClass="col16" value="${theForm.emailArr[0]}" isNullCheck="true" maxlength="50"/> @ 
				<form:input path="email_domain" title="이메일주소 도메인" cssClass="col16" value="${theForm.emailArr[1]}" isNullCheck="true" maxlength="50"/>
				<select id="emailDomainBox">
				<option value="">직접입력</option>				
				<c:forEach var="data" items="${emailDomainList}">
					<option value="${data.code}">${data.code_name}</option>					
				</c:forEach>
				</select>
			</td>
		</tr>
		
		<tr>
			<th scope="row">업체 로고</th>
			<td colspan="3">
				<span id="imagePreview">
					<c:choose>
					<c:when test="${!empty theForm.master_image}">
						<img id="master_imagePreview" src="${uploadDefaultUrl}/${uploadImgThumbDir}${theForm.master_image}" alt="썸네일이미지">
					</c:when>
					<c:otherwise>
						<img id="master_imagePreview" src="/assets/plugin/fileupload/img/noimage2.png" alt="썸네일이미지">
					</c:otherwise>
					</c:choose>
				</span>
				<form:hidden path="master_image" value="${theForm.master_image}"/>
				<p id="btnPhotoAdd_master_image" style="<c:if test="${!empty theForm.master_image}">display:none;</c:if>"><a href="/attachPhotoPop.do?imageArea=master_image" class="btn inverse btn_xsmall" onclick="popup(this.href, 'photoUpload', '385', '450', 'no', 'no', 3); return false;">이미지 첨부</a></p>
				<p id="btnPhotoDel_master_image" style="<c:if test="${empty theForm.master_image}">display:none;</c:if>"><a href="#" class="btn inverse btn_xsmall btnPhotoDel" onclick="deletePhoto('master_image'); return false;">이미지 삭제</a></p>
			</td>			
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
	<a href="companyList.do" class="btn white">목록</a>
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