<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/comm/taglib.jsp"%>

<!-- SmartEditor 사용시 include head-->
<jsp:include page="/WEB-INF/views/comm/editor_head.jsp"/>

<script type="text/javascript">
$(function(){
	var act = "${theForm.act}";
	var isEditor = "${boardConfig.editor_use_yn}";
	// 등록/수정
	$('.btnSave').on('click',function(){
		if(formValidate()){
			if(isEditor == "Y"){
				oEditors.getById["content"].exec("UPDATE_CONTENTS_FIELD", []);
				if($('#content').val() == "<p>&nbsp;</p>" || $('#content').val() == ""){alert("내용을 입력해 주시기 바랍니다.");return;}
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
			$('#theForm').submit();
		}
	});
	
	// 삭제
	$(".btnDelete").on("click",function(){
		if(confirm('삭제하시겠습니까?')){
			$('#deleteForm').submit();
		}
	});
});
</script>

<fieldset>
<legend></legend>

<form:form commandName="theForm" action="boardSave.do">
<form:hidden path="act"/>
<form:hidden path="board_key"/>
<form:hidden path="master_image"/>

<div id="hiddenSaveFileData"></div>
<div id="hiddenRemoveFileData"></div>
	      
	<table class="table_form">
		<caption>게시글 정보</caption>
		<colgroup>
			<col style="width:20%;" />
			<col style="width:80%;" />
		</colgroup>
		<tbody>
			<c:if test="${boardConfig.cate_use_yn eq 'Y' and !empty categoryList}">
		    <!-- 카테고리목록 -->
			<tr>
				<th scope="row">카테고리</th>
				<td>
					<form:select path="cate_id" items="${categoryList}" cssClass="form-control" itemLabel="cate_name" itemValue="cate_id" title="카테고리"/>
				</td>
			</tr>
			</c:if>
			<c:if test="${boardConfig.notice_use_yn eq 'Y'}">
			<!-- 내부공지글여부 -->
			<tr>
				<th scope="row">내부공지글</th>
				<td>
					<form:select path="is_notice" title="상태" isNullCheck="true">
						<form:option value="N" label="사용안함"/>
						<form:option value="Y" label="사용함"/>
					</form:select>
				</td>
			</tr>
			</c:if>
			<tr>
				<th scope="row">게시상태</th>
				<td>
					<form:select path="status" title="게시상태" isNullCheck="true">
						<form:option value="Y" label="정상"/>
						<form:option value="N" label="차단"/>
					</form:select>
				</td>
			</tr>
			<tr>
				<th scope="row">제목</th>
				<td><form:input path="title" title="제목" cssClass="col75" isNullCheck="true" maxlength="100"/></td>
			</tr>
			<c:choose>
			<c:when test="${empty theForm.req_content}">
			<tr>
				<th scope="row">내용</th>
				<td>
				<form:textarea path="content" rows="30" cssClass="col75"/>
				<c:if test="${boardConfig.editor_use_yn eq 'Y'}">
		        	<!-- SmartEditor 사용시 include content-->
		        	<c:set var="editorFieldName" value="content" scope="request"/>
					<jsp:include page="/WEB-INF/views/comm/editor.jsp"/>
					<!--// SmartEditor 사용시 include content-->
	        	</c:if>
				</td>
			</tr>
			</c:when>
			<c:otherwise>
			<tr>
				<th scope="row">문의</th>
				<td><c:out value="${theForm.req_content}" escapeXml="false"/></td>
			</tr>
			<tr>
				<th scope="row">답변</th>
				<td>
				<form:textarea path="content" rows="30" cssClass="col75"/>
				<c:if test="${boardConfig.editor_use_yn eq 'Y'}">
		        	<!-- SmartEditor 사용시 include content-->
		        	<c:set var="editorFieldName" value="content" scope="request"/>
					<jsp:include page="/WEB-INF/views/comm/editor.jsp"/>
					<!--// SmartEditor 사용시 include content-->
	        	</c:if>
				</td>
			</tr>			
			</c:otherwise>
			</c:choose>
			<tr>
				<th scope="row">작성자</th>
				<td>
					<c:set var="reg_name"><c:if test="${theForm.act ne 'update' }">${GLOBAL_USER_NAME}</c:if></c:set>
					<form:input path="reg_name" title="작성자" value="${reg_name}" cssClass="col25" isNullCheck="true" maxlength="10"/>
				</td>
			</tr>
			<tr>
				<th scope="row">비밀글</th>
				<td>
					<c:choose>
					<c:when test="${theForm.act eq 'reply' and theForm.is_secret eq 'Y'}">
					<!-- 답글 & 본문이 비밀글인 경우 -->
					<input type="hidden" name="is_secret" value="Y"/>	
					<em>※ 비공개 글입니다.</em>				
					</c:when>
					<c:otherwise>
					<input type="checkbox" name="is_secret" id="is_secret" title="비밀글" value="Y" <c:if test="${theForm.is_secret eq 'Y'}">checked</c:if>/> <label for="is_secret">비밀글</label>
					<em>※ 비공개로 하시면 관리자와 작성자만 확인이 가능합니다</em>
					</c:otherwise>
					</c:choose>
				</td>
			</tr>
		</tbody>
	</table>
</form:form>
</fieldset>
	
	<c:if test="${boardConfig.file_count_limit > 0}">
	<!-- 첨부파일 영역 -->
	<jsp:include page="/WEB-INF/views/comm/attach_form.jsp"/>	
	</c:if>
	
	<div class="btn_wrap textAlign_center">
		<a href="boardList.do<c:out value="${defaultParameter}"/>" class="btn white">취소</a>
		<c:choose>
		<c:when test="${theForm.act eq 'update'}">			
			<a href="#" class="btn primary btnSave">수정</a>
		</c:when>
		<c:otherwise>
			<a href="#" class="btn primary btnSave">등록</a>
		</c:otherwise>
		</c:choose>		
	</div>


