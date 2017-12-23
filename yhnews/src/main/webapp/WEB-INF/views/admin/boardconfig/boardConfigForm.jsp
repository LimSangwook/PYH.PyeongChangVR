<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/comm/taglib.jsp"%>

<!-- SmartEditor 사용시 include head-->
<jsp:include page="/WEB-INF/views/comm/editor_head.jsp"/>

<script type="text/javascript">

$(function(){
	var act = "${theForm.act}";	
	var cateUseYn = "${theForm.cate_use_yn}";
	var fileCountLimit = "${theForm.file_count_limit}";
	// 등록/수정
	$('.btnSave').on('click',function(){
		if(formValidate()){
			
			oEditors.getById["top_content"].exec("UPDATE_CONTENTS_FIELD", []);	
			oEditors.getById["bottom_content"].exec("UPDATE_CONTENTS_FIELD", []);
			if($('#top_content').val() == "<p>&nbsp;</p>") $('#top_content').val("");
			if($('#bottom_content').val() == "<p>&nbsp;</p>") $('#bottom_content').val("");
			
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
	
	// 카테고리 기능여부
	$("input[name='cate_use_yn']").on("click",function(){
		if($(this).val() == 'N'){
			$("input[name='category_sort_arr']").attr('disabled',true);
			$("input[name='category_name_arr']").attr('disabled',true);			
			$(".cateAddBtn, .cateRemoveBtn").hide();
		} else {
			$("input[name='category_sort_arr']").attr('disabled',false);
			$("input[name='category_name_arr']").attr('disabled',false);
			$(".cateAddBtn, .cateRemoveBtn").show();
		}
	});	
	 
	if(cateUseYn == 'N'){
		$("input[name='category_sort_arr']").attr('disabled',true);
		$("input[name='category_name_arr']").attr('disabled',true);			
		$(".cateAddBtn, .cateRemoveBtn").hide();
	}
	
	// 카테고리 추가
	$(".cateAddBtn").on("click",function(e){
		e.preventDefault();
		var html = '<li><input type="hidden" name="category_id_arr" value="-"/><input type="text" name="category_sort_arr" title="노출 순서" class="col16" isNullCheck="true" maxlength="2"/> / <input type="text" name="category_name_arr" title="카테고리 이름" class="col50" isNullCheck="true" maxlength="20"/> <a href="#" class="btn btn_small white cateRemoveBtn">삭제</a></li>';
		$('#cateArea').append(html);
	});
	
	// 카테고리 삭제
	$(document).on("click",".cateRemoveBtn",function(e){
		e.preventDefault();
		//if(confirm('해당 카테고리를 삭제하시겠습니까?')){
			var remove = $(this).parent().find("input[name='category_id_arr']").val();
			if(remove != "-"){
				$("#removeData").append("<input type='hidden' name='remove' value='"+remove+"'/>");	
			}
			$(this).parent().remove();
		//}
	});	
	
	// 첨부파일 수량제한
	$("#file_count_limit").on("change",function(){
		if($(this).val() == '0'){
			$("input[name='file_ext_limit']").attr('disabled',true);			
		} else {
			$("input[name='file_ext_limit']").attr('disabled',false);			
		}
	});
	
	if(fileCountLimit == '0'){
		$("input[name='file_ext_limit']").attr('disabled',true);
	}
	
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

<!-- delete form start -->
<form:form id="deleteForm" commandName="theForm" action="boardConfigSave.do">
<form:hidden path="act" id="deleteAct" value="delete"/>
<form:hidden path="board_id"/>
</form:form>

<form:form commandName="theForm" action="boardConfigSave.do">
<form:hidden path="act"/>
<form:hidden path="comment_use_yn" value="N"/>
<form:hidden path="board_id"/>
<span id="removeData"></span>

	<h3 class="tit-h3">기본설정</h3>
	<table class="table_form">
		<caption>게시판 정보</caption>
		<colgroup>
			<col style="width:20%;" />
			<col style="width:80%;" />
		</colgroup>
		<tbody>
			<tr>
				<th scope="row">게시판 상태</th>
				<td>
					<form:select path="status" title="게시판 상태" isNullCheck="true">
						<form:option value="" label="선택"/>
						<form:option value="Y" label="사용"/>
						<form:option value="N" label="미사용"/>
					</form:select>
				</td>
			</tr>
			<tr>
				<th scope="row">게시판 종류</th>
				<td>
					<form:select path="board_type" title="게시판 종류" isNullCheck="true">
						<form:option value="" label="선택"/>
						<c:forEach var="data" items="${boardTypeCodeList}">
						<form:option value="${data.code}" label="${data.code_name}"/>
						</c:forEach>
					</form:select>					
				</td>
			</tr>			
			<tr>
				<th scope="row">게시판 이름</th>
				<td><form:input path="board_name" title="게시판 이름" cssClass="col16" isNullCheck="true" maxlength="20"/></td>
			</tr>
			<tr>
				<th scope="row">공지글 기능</th>
				<td>
					<form:radiobutton path="notice_use_yn" id="notice_use_y" title="공지글기능" value="Y" isNullCheck="true"/> <label for="notice_use_y">사용함</label>
					<form:radiobutton path="notice_use_yn" id="notice_use_n" title="공지글기능" value="N" isNullCheck="true"/> <label for="notice_use_n">사용안함</label>
				</td>
			</tr>
			<%-- <tr>
				<th scope="row">댓글 기능</th>
				<td>
					<form:radiobutton path="comment_use_yn" id="comment_use_y" title="댓글기능" value="Y" isNullCheck="true"/> <label for="comment_use_y">사용함</label>
					<form:radiobutton path="comment_use_yn" id="comment_use_n" title="댓글기능" value="N" isNullCheck="true"/> <label for="comment_use_n">사용안함</label>
				</td>
			</tr> --%>
			<tr>
				<th scope="row">답글 기능</th>
				<td>
					<form:radiobutton path="reply_use_yn" id="reply_use_y" title="답글기능" value="Y" isNullCheck="true"/> <label for="reply_use_y">사용함</label>
					<form:radiobutton path="reply_use_yn" id="reply_use_n" title="답글기능" value="N" isNullCheck="true"/> <label for="reply_use_n">사용안함</label>
				</td>
			</tr>
			<tr>
				<th scope="row">비밀글 기능</th>
				<td>
					<form:radiobutton path="secret_use_yn" id="secret_use_y" title="비밀글기능" value="Y" isNullCheck="true"/> <label for="secret_use_y">사용함</label>
					<form:radiobutton path="secret_use_yn" id="secret_use_n" title="비밀글기능" value="N" isNullCheck="true"/> <label for="secret_use_n">사용안함</label>
				</td>
			</tr>
			<tr>
				<th scope="row">편집기(에디터)</th>
				<td>
					<form:radiobutton path="editor_use_yn" id="editor_use_y" title="편집기(에디터)" value="Y" isNullCheck="true"/> <label for="editor_use_y">사용함</label>
					<form:radiobutton path="editor_use_yn" id="editor_use_n" title="편집기(에디터)" value="N" isNullCheck="true"/> <label for="editor_use_n">사용안함</label>
				</td>
			</tr>			
		</tbody>
	</table>

	<h3 class="tit-h3">카테고리 설정</h3>
	<table class="table_form">
		<caption>게시판 정보</caption>
		<colgroup>
			<col style="width:20%;" />
			<col style="width:80%;" />
		</colgroup>
		<tbody>
			<tr>
				<th scope="row">카테고리 기능</th>
				<td>
					<form:radiobutton path="cate_use_yn" id="cate_use_y" title="카테고리기능" value="Y" isNullCheck="true"/> <label for="cate_use_y">사용함</label>
					<form:radiobutton path="cate_use_yn" id="cate_use_n" title="카테고리기능" value="N" isNullCheck="true"/> <label for="cate_use_n">사용안함</label>
				</td>
			</tr>			
			<tr>
				<th scope="row">카테고리 목록</th>
				<td>
					<div class="col-item col50" style="padding-left: 0">
					<div class="item-style1">
						<h1 class="label_primary">노출 순서 / 카테고리 이름</h1>
						<ul class="list_style_2" id="cateArea">
						<c:choose>
						<c:when test="${!empty categoryList}">							
							<c:forEach var="cate" items="${categoryList}" varStatus="i">
								<li>
									<form:hidden path="category_id_arr" value="${cate.cate_id}"/>
									<form:input path="category_sort_arr" title="노출 순서" value="${cate.order_level}" cssClass="col16" isNullCheck="true" maxlength="2"/> /
									<form:input path="category_name_arr" title="카테고리 이름" value="${cate.cate_name}" cssClass="col50" isNullCheck="true" maxlength="20"/>									
									<c:choose>
									<c:when test="${i.index eq 0}"><a href="#" class="btn btn_small cateAddBtn">추가</a></c:when>
									<c:otherwise><a href="#" class="btn btn_small white cateRemoveBtn">삭제</a></c:otherwise>
									</c:choose>									
								</li>
							</c:forEach>
						</c:when>
						<c:otherwise>
							<li><form:hidden path="category_id_arr" value="-"/><form:input path="category_sort_arr" title="노출 순서" cssClass="col16" isNullCheck="true" maxlength="2"/> / <form:input path="category_name_arr" title="카테고리 이름" cssClass="col50" isNullCheck="true" maxlength="20"/> <a href="#" class="btn btn_small cateAddBtn">추가</a></li>
							<li><form:hidden path="category_id_arr" value="-"/><form:input path="category_sort_arr" title="노출 순서" cssClass="col16" isNullCheck="true" maxlength="2"/> / <form:input path="category_name_arr" title="카테고리 이름" cssClass="col50" isNullCheck="true" maxlength="20"/> <a href="#" class="btn btn_small white cateRemoveBtn">삭제</a></li>
						</c:otherwise>
						</c:choose>
						</ul>
					</div>
					</div>
				</td>
			</tr>
		</tbody>
	</table>
	
	<h3 class="tit-h3">페이지 설정</h3>
	<table class="table_form">
		<caption>게시판 정보</caption>
		<colgroup>
			<col style="width:20%;" />
			<col style="width:80%;" />
		</colgroup>
		<tbody>
			<tr>
				<th scope="row">페이지 목록 사이즈</th>
				<td>
					<form:select path="page_list_size" title="페이지 목록 사이즈" isNullCheck="true">
						<form:option value="" label="선택"/>
						<form:option value="5" label="5개"/>
						<form:option value="8" label="8개"/>
						<form:option value="10" label="10개"/>
						<form:option value="12" label="12개"/>
						<form:option value="20" label="20개"/>
						<form:option value="50" label="50개"/>
					</form:select>
				</td>
			</tr>
			<tr>
				<th scope="row">페이지 블럭 사이즈</th>
				<td>
					<form:select path="page_block_size" title="페이지 블럭 사이즈" isNullCheck="true">
						<form:option value="" label="선택"/>
						<form:option value="5" label="5개"/>
						<form:option value="10" label="10개"/>
					</form:select>
				</td>
			</tr>
		</tbody>
	</table>
	
	<h3 class="tit-h3">파일 설정</h3>
	<table class="table_form">
		<caption>게시판 정보</caption>
		<colgroup>
			<col style="width:20%;" />
			<col style="width:80%;" />
		</colgroup>
		<tbody>
			<tr>
				<th scope="row">첨부파일 수량 제한</th>
				<td>
					<form:select path="file_count_limit" title="첨부파일 수량 제한" isNullCheck="true">
						<form:option value="0" label="사용안함"/>
						<form:option value="1" label="1개"/>
						<form:option value="2" label="2개"/>
						<form:option value="3" label="3개"/>
						<form:option value="4" label="4개"/>
						<form:option value="5" label="5개"/>
						<form:option value="999" label="제한없음"/>
					</form:select>
				</td>
			</tr>
			<tr>
				<th scope="row">파일업로드 사이즈 제한</th>
				<td>
					<form:select path="file_size_limit" title="페이지 블럭 사이즈" isNullCheck="true">
						<form:option value="5" label="5MB"/>
						<form:option value="10" label="10MB"/>
						<form:option value="50" label="50MB"/>
						<form:option value="100" label="100MB"/>
						<form:option value="999" label="제한없음"/>
					</form:select>
				</td>
			</tr>
			<tr>
				<th scope="row">파일확장자 제한</th>
				<td>
					<form:input path="file_ext_limit" title="파일확장자 제한" cssClass="col50" isNullCheck="true" maxlength="100"/>
					<p>파일 확장자 "|" 구분, ex) doc|ppt|xls|hwp|pdf|txt|docx|pptx|xlsx|zip|gif|jpg|jpeg|bmp|png</p>
				</td>
			</tr>
		</tbody>
	</table>
	
	<h3 class="tit-h3">상/하단 내용설정</h3>
	<table class="table_form">
		<caption>게시판 정보</caption>
		<colgroup>
			<col style="width:20%;" />
			<col style="width:80%;" />
		</colgroup>
		<tbody>
			<tr>
				<th scope="row">상단내용</th>
				<td>					
					<textarea name="top_content" id="top_content" rows="30" class="col75">${theForm.top_content}</textarea>
					<c:set var="editorFieldName" value="top_content" scope="request"/>
					<jsp:include page="/WEB-INF/views/comm/editor.jsp"/>
					<p><em>※ 사용자 게시판 "상단"에 노출되는 내용입니다. (필수내용 아님)</em></p>
				</td>
			</tr>
			<tr>
				<th scope="row">하단내용</th>
				<td>
					<textarea name="bottom_content" id="bottom_content" rows="30" class="col75">${theForm.bottom_content}</textarea>
					<c:set var="editorFieldName" value="bottom_content" scope="request"/>
					<jsp:include page="/WEB-INF/views/comm/editor.jsp"/>
					<p><em>※ 사용자 게시판 "하단"에 노출되는 내용입니다. (필수내용 아님)</em></p>
				</td>
			</tr>
		</tbody>
	</table>
	
	<h3 class="tit-h3">권한 설정</h3>
	<table class="table_form">
		<caption>게시판 정보</caption>
		<colgroup>
			<col style="width:20%;" />
			<col style="width:80%;" />
		</colgroup>
		<tbody>
			<tr>
				<th scope="row">글작성 권한</th>
				<td>					
					<form:select path="write_limit" title="글작성 권한" isNullCheck="true">
						<form:option value="" label="선택"/>
						<c:forEach var="data" items="${userAuthLevelCodeList}">
						<form:option value="${data.code}" label="${data.code_name}"/>
						</c:forEach>
					</form:select>
				</td>
			</tr>
			<tr>
				<th scope="row">답글작성 권한</th>
				<td>
					<form:select path="reply_limit" title="답글작성 권한" isNullCheck="true">
						<form:option value="" label="선택"/>
						<c:forEach var="data" items="${userAuthLevelCodeList}">
						<form:option value="${data.code}" label="${data.code_name}"/>
						</c:forEach>
					</form:select>
				</td>
			</tr>
		</tbody>
	</table>
	
		
	<div class="btn_wrap textAlign_center">
		<a href="boardConfigList.do" class="btn white">취소</a>
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
</form:form>

</fieldset>


