<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/comm/taglib.jsp"%>

<!-- SmartEditor 사용시 include head-->
<jsp:include page="/WEB-INF/views/comm/editor_head.jsp"/>

<script type="text/javascript">
$(function(){
	var act = "${theForm.act}";
	var isEditor = "${boardConfig.editor_use_yn}";
	var isMobileAccess = "${isMobileAccess}";	
	// 등록/수정
	$('.btnSave').on('click',function(){
		
		if(isEditor == "Y" && isMobileAccess == "false"){
			oEditors.getById["content"].exec("UPDATE_CONTENTS_FIELD", []);
			if($('#content').val() == "<p>&nbsp;</p>" || $('#content').val() == ""){alert("내용을 입력해 주시기 바랍니다.");return;}
		}
		
		if(formValidate()){
			
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


            
	<div class="bbs_wrap">	
	
		<fieldset>
		<legend>글쓰기</legend>
		<form:form commandName="theForm" action="boardSave.do">
		<form:hidden path="act"/>
		<form:hidden path="status" value="Y"/>
		<form:hidden path="board_key"/>
		<form:hidden path="master_image"/>
		
		<div id="hiddenSaveFileData"></div>
		<div id="hiddenRemoveFileData"></div>
	
			<table class="table_form">
				<caption>글쓰기</caption>
				<colgroup>
					<col style="width:15%;" />
					<col style="width:85%;" />
				</colgroup>
				<tbody>
					<c:if test="${boardConfig.cate_use_yn eq 'Y' and !empty categoryList}">
				    <!-- 카테고리목록 -->
					<tr>
						<th scope="row">문의종류</th>
						<td>
							<form:select path="cate_id" items="${categoryList}" cssClass="form-control" itemLabel="cate_name" itemValue="cate_id" title="카테고리"/>
						</td>
					</tr>
					</c:if>
					<tr>
						<th>제목</th>
						<td><form:input path="title" title="제목" cssClass="col100" isNullCheck="true" maxlength="100"/></td>
					</tr>
					
					<c:if test="${boardConfig.board_type eq 'qna'}">
					<%-- <tr>
						<th>전화</th>
						<td>
							<form:input path="phone1" title="전화번호" cssClass="input_20 numberOnly" value="${theForm.phoneArr[0]}" isNullCheck="true" isNumberCheck="true" maxlength="4"/> -
							<form:input path="phone2" title="전화번호" cssClass="input_20 numberOnly" value="${theForm.phoneArr[1]}" isNullCheck="true" isNumberCheck="true" maxlength="4"/> - 
							<form:input path="phone3" title="전화번호" cssClass="input_20 numberOnly" value="${theForm.phoneArr[2]}" isNullCheck="true" isNumberCheck="true" maxlength="4"/>						
						</td>
					</tr>
					<tr>
						<th>이메일</th>
						<td>
							<form:input path="email_id" title="이메일 아이디" cssClass="input_20" value="${theForm.emailArr[0]}" isNullCheck="true" maxlength="30"/> @
							<form:input path="email_domain" title="이메일 도메인" cssClass="input_20" value="${theForm.emailArr[1]}" isNullCheck="true" maxlength="20"/>							
							<select id="emailDomainBox">
							<option value="">직접입력</option>				
							<c:forEach var="data" items="${emailDomainList}">
								<option value="${data.code}">${data.code_name}</option>					
							</c:forEach>
							</select>
						</td>
					</tr> --%>
					</c:if>
					
					<c:if test="${boardConfig.secret_use_yn eq 'Y'}">
					<tr>
						<th>비밀글</th>
						<td>
							<input type="checkbox" name="is_secret" id="is_secret" value="Y" <c:if test="${theForm.is_secret eq 'Y'}">checked</c:if>> <label for="is_secret">비공개</label>&nbsp;&nbsp;&nbsp;
							<em class="info_txt">※ 비공개로 하시면 관리자와 작성자만 확인이 가능합니다</em>
						</td>
					</tr>
					</c:if>	
					<c:if test="${menuCode eq 'stampTour'}">
					<tr>
						<th>SNS URL</th>
						<td><form:input path="link_address" title="SNS URL" cssClass="col100" isNullCheck="true" maxlength="100"/></td>
					</tr>
					</c:if>							
					<tr>
						<th>작성자</th>
						<td>${GLOBAL_USER_NAME}</td>
					</tr>
					<tr>
						<td class="context" colspan="2">
						<!-- 에디터 위치 -->
						<form:textarea path="content" rows="15" title="내용" isNullCheck="true" cssClass="col100"/>
						<c:if test="${boardConfig.editor_use_yn eq 'Y' and !isMobileAccess}">
			        	<!-- SmartEditor 사용시 include content-->
			        	<c:set var="editorFieldName" value="content" scope="request"/>
						<jsp:include page="/WEB-INF/views/comm/editor.jsp"/>
						<!--// SmartEditor 사용시 include content-->
		        	</c:if>
						</td>
					</tr>
				</tbody>
			</table>
			
		</form:form>
			
		<c:if test="${boardConfig.file_count_limit > 0}">
		<!-- 첨부파일 영역 -->
		<jsp:include page="/WEB-INF/views/comm/attach_front_form.jsp"/>	
		</c:if>
	
		<div class="btn_wrap text_right">
			<a href="boardList.do" class="btn btn_basic">취소</a>
			<c:choose>
			<c:when test="${theForm.act eq 'update'}">			
				<button type="button" class="btn btn_basic btnSave">수정</button>
			</c:when>
			<c:otherwise>
				<button type="button" class="btn btn_basic btnSave">작성</button>
			</c:otherwise>
			</c:choose>
		</div>
		
		</fieldset>
	
	</div>

	