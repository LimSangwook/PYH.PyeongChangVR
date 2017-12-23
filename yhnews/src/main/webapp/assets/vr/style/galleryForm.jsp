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
	<form:form id="deleteForm" commandName="theForm" action="gallerySave.do">
		<form:hidden path="act" id="deleteAct" value="delete"/>
		<form:hidden path="vr_style_gallery_id"/>
	</form:form>

	<form:form commandName="theForm" action="gallerySave.do" enctype="multipart/form-data">
	<form:hidden path="act"/>

	<table class="table_form">
    	<caption>콘텐츠 등록</caption>
    	<colgroup>
    			<col style="width:20%;" />
    			<col style="width:80%;" />
    	</colgroup>
    	<tbody>
    		<tr>
    			<th scope="row">제목</th>
    			<td><input type="text" name="title" class="wid300" title="제목 입력" value="${theForm.title}"></td>
    		</tr>
    		<tr>
    			<th scope="row">경기장</th>
    			<td>
    				<select name="vr_site_id" title="구분 선택">
    					<option value='-1'>경기장 선택</option>
    					<c:forEach var="data" items="${siteList}" varStatus="i">
    						<option value='${data.vr_site_id}' <c:if test="${data.vr_site_id eq theForm.vr_site_id}"><c:out value="selected"/></c:if>>${data.title}</option>
    					</c:forEach>
    				</select>
    			</td>
    		</tr>
    		<tr>
    			<th scope="row">사진</th>
				<td>
					<div class="float_right plusbtn_wrap">
						<a href="javascript:appendRow('gallery');" class="ico ico_plus">추가</a>
						<a href="javascript:deleteRow('gallery');" class="ico ico_minus">삭제</a>
					</div>
					<ul class="group_list" id="gallery">
						<c:forEach var="img" items="${imgList}" varStatus="i">
						<li>
							<c:url var="imgUrl" value="/assets/admin/vr/gallery/${img.path_image}" ></c:url>
							<img src="${imgUrl}" >
							<a href="${imgUrl}" target="_blank" >${img.path_image}</a>
						</li>
						</c:forEach>
					</ul>
				</td>
    		</tr>
    	</tbody>
    </table><!-- //table_basic -->

    <div class="btn_wrap float_right">
    	<a href="galleryList.do" class="btn ">목록</a> 
    	<a href="#" class="btn primary btnSave">등록</a>
    </div>
</form:form>
</fieldset>
<script type="text/javascript">
    function appendRow(val) {
        var table_id = $("#"+val);
        var count = table_id.find('li').length +1;
        if (val == "gallery"){
            var html = "<li>";
                  html += "<input type='file' id='gallery_"+count+"' name='gallery_file'>";
                  html += "</li>";
        };
        table_id.append(html);
    }
    function deleteRow(val) {
        var table_id = $("#"+val);
        var count = table_id.find('li').length;
        if (count == 1){
            alert('하나 이상 등록하셔야합니다.');
        }else{
            var table_id = $("#"+val + " li:last");
            table_id.remove();
        }
    }
</script>