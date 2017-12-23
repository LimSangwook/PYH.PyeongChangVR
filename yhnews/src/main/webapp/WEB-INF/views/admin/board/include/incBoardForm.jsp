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
	
	$('#video_type').on('change',function(){		
		if($(this).val() == '1'){
			$('#link_address').prop('disabled',true);
		} else {
			$('#link_address').prop('disabled',false);
		}
	});
	if($('#video_type').val() == '1'){
		$('#link_address').prop('disabled',true);
	}
	// jquery 달력 세팅 (오늘날짜 기준)
	setMinDateCalendar("yy-mm-dd");	
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
			<!--// 카테고리목록 -->
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
			<!--// 내부공지글여부 -->
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
			
			<c:choose>
			<c:when test="${boardConfig.board_type eq 'goods'}">
			<!-- 기념품(상품) 추가필드 -->
				<tr>
					<th scope="row">상품명</th>
					<td><form:input path="title" title="상품명" cssClass="col25" isNullCheck="true" maxlength="100"/></td>
				</tr>
				<tr>
					<th scope="row">소재지</th>
					<td><form:input path="add_field1" title="소재지" cssClass="col25" isNullCheck="true" maxlength="100"/></td>
				</tr>
				<tr>
					<th scope="row">판매가격</th>
					<td><form:input path="add_field2" title="판매가격" cssClass="col25" isNullCheck="true" maxlength="100"/></td>
				</tr>
				<tr>
					<th scope="row">판매수량</th>
					<td><form:input path="add_field3" title="판매수량" cssClass="col25" isNullCheck="true" maxlength="100"/></td>
				</tr>
				<tr>
					<th scope="row">판매URL</th>
					<td>
						<form:input path="link_address" title="판매URL" cssClass="col50" isNullCheck="true" maxlength="100"/>
						<p><em>※ http:// 또는 https://를 포함한 전체 주소를 입력해 주시기 바랍니다.</em></p>
					</td>
				</tr>
				<tr>			
					<th scope="row">판매기간</th>
					<td>
						<form:input path="start_day" title="판매시작일" cssClass="col16 inputCal wid100" isNullCheck="true" maxlength="10" readonly="true"/> ~
						<form:input path="end_day" title="판매종료일" cssClass="col16 inputCal wid100" isNullCheck="true" maxlength="10" readonly="true"/>
					</td>
				</tr>
			</c:when>
			<c:otherwise>
			
				<tr>
					<th scope="row">제목</th>
					<td><form:input path="title" title="제목" cssClass="col75" isNullCheck="true" maxlength="100"/></td>
				</tr>
				
				<c:if test="${menuCode eq 'stampManage'}">
				<tr>
					<th scope="row">SNS URL</th>
					<td><form:input path="link_address" title="SNS URL" cssClass="col100" isNullCheck="true" maxlength="100"/></td>
				</tr>
				</c:if>
				
			</c:otherwise>			
			</c:choose>			
			
			
			<c:if test="${boardConfig.board_type eq 'schedule'}">
			<tr>			
			<th scope="row">행사기간</th>
				<td>
					<form:input path="start_day" title="행사시작일" cssClass="col16 inputCal wid100" isNullCheck="true" maxlength="10" readonly="true"/> ~
					<form:input path="end_day" title="행사종료일" cssClass="col16 inputCal wid100" isNullCheck="true" maxlength="10" readonly="true"/>
				</td>
			</tr>
			</c:if>
			
			<c:if test="${boardConfig.board_type eq 'newsletter'}">
			<!-- 뉴스레터 추가 필드 -->
			<tr>
				<th scope="row">한줄설명</th>
				<td><form:input path="summary" title="한줄설명" cssClass="col75" isNullCheck="true" maxlength="100"/></td>
			</tr>		
			<!--// 뉴스레터 추가 필드 -->	
			</c:if>
			
			<c:if test="${boardConfig.board_type eq 'video'}">
			<!-- 영상게시판 추가 필드 -->
			<tr>
				<th scope="row">영상타입</th>
				<td>
					<form:select path="video_type" title="영상타입" isNullCheck="true">
						<form:option value="1" label="VR"/>
						<form:option value="2" label="Youtube"/>
					</form:select>					
				</td>
			</tr>
			<tr>
				<th scope="row">영상URL</th>
				<td>
					<form:input path="link_address" title="영상URL" cssClass="col75" isNullCheck="true" maxlength="100"/>
					<p><em>※ VR 타입은 "*.mp4" 확장자만 지원합니다.(하단 영상을 업로드 바랍니다.)</em></p>
					<p><em>※ Youtube 타입은 "영상URL"을 입력해 주시기 바랍니다.(소스코드 제외)</em></p>					
				</td>
			</tr>
			<!--// 영상게시판 추가 필드 -->
			</c:if>
			
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
			
			<c:if test="${theForm.is_secret eq 'Y'}">
			<!-- 비밀글 추가 필드 -->
			<tr>
				<th scope="row">비밀글</th>
				<td>
					<c:choose>
					<c:when test="${theForm.act eq 'reply'}">
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
			<!--// 비밀글 추가 필드 -->
			</c:if>
			
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