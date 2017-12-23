<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/comm/taglib.jsp"%>

<script type="text/javascript">
var act = "${theForm.act}";
$(function(){
	// 등록/수정
	$('.btnSave').on('click',function(){
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
	<form:form id="deleteForm" commandName="theForm" action="linkSave.do">
		<form:hidden path="act" id="deleteAct" value="delete"/>
		<form:hidden path="vr_style_link_id"/>
	</form:form>

	<form:form commandName="theForm" action="linkSave.do" enctype="multipart/form-data">
	<form:hidden path="act"/>
	<form:hidden path="vr_style_link_id"/>
		<table class="table_form">
			<caption>콘텐츠 등록</caption>
			<colgroup>
				<col style="width: 20%;" />
				<col style="width: 80%;" />
			</colgroup>
			<tbody>
				<tr>
					<th scope="row">제목</th>
					<td><input type="text" name="title" class="wid300"
						title="제목 입력" value="${theForm.title}"></td>
				</tr>
				<tr>
					<th scope="row">링크이미지</th>
                    <c:choose>
                        <c:when test="${theForm.act eq 'update'}">
                            <td><a href="${CTX}/assets/admin/vr/img/${theForm.path_image}" target="_blank" >${theForm.path_image}</a></td>
                        </c:when>
                        <c:otherwise>
        					<td><input type="file" name="link_file" title="링크이미지 첨부"></td>
                        </c:otherwise>
                    </c:choose>
				</tr>
				<tr>
					<th scope="row">over 사이즈</th>
					<td><input type="text" name="over_size" class="wid100"
						title="over 사이즈 입력" value="${theForm.over_size}"></td>
				</tr>
				<tr>
					<th scope="row">out 사이즈</th>
					<td><input type="text" name="out_size" class="wid100"
						title="out 사이즈 입력" value="${theForm.out_size}"></td>
				</tr>
			</tbody>
		</table>
		<!-- //table_basic -->
		<div class="btn_wrap float_right">
			<c:choose>
			<c:when test="${theForm.act eq 'update'}">
				<a href="linkList.do" class="btn white">취소</a>
				<a href="#" class="btn danger btnDelete">삭제</a>
				<a href="#" class="btn primary btnSave">수정</a>
			</c:when>
			<c:otherwise>
				<a href="linkList.do" class="btn ">목록</a>
				<a href="#" class="btn primary btnSave">등록</a>
			</c:otherwise>
			</c:choose>		
		</div>
			
	</form:form>
</fieldset>


