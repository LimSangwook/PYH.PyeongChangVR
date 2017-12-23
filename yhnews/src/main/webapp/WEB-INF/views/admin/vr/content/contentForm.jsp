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
    addOnChange();
});

var linkListHtml = "";
<c:if test="${!empty styleLinkList}">
	<c:forEach var="styleLink" items="${styleLinkList}">
		linkListHtml += '<option value="'+'${styleLink.vr_style_link_id}'+'">'+'${styleLink.title}'+'</option>';
	</c:forEach>
</c:if>

var spotListHtml = "";
<c:forEach var="siteContent" items="${siteContentList}">
	spotListHtml += '<option value="'+'${siteContent.vr_site_content_id}'+'">'+'${siteContent.name}'+'</option>';
</c:forEach>

function appendRow(val) {
	var table_id = $("#"+val);
	var count = table_id.find('tbody tr').length;

	if (val == "spot"){
		var html = "<tr>";
			  html += "<td>"+count+"<input type='hidden' id='crud_type' name='spotList["+count+"].crud_type' value='I' ></td>";
			  html += "<td><input type='text' name='spotList["+count+"].name' class='col100'></td>";
			  html += "<td>";
			  html += "<select class='vr_style_type' name='spotList["+count+"].vr_style_type'><option value='link'>링크</option><option value='gallery'>사진</option><option value='movie'>동영상</option></select>";
			  html += "<select name='spotList["+count+"].vr_style_id'>"+linkListHtml+"</select>";
			  html += "</td>";
			  html += "<td><input type='text' name='spotList["+count+"].pos_x' placeholder='x 좌표' class='wid50'>&nbsp;&nbsp;<input type='text' name='spotList["+count+"].pos_y' placeholder='y 좌표' class='wid50'></td>";
			  html += "<td><select name='spotList["+count+"].link_content_id'>"+spotListHtml+"</select></td>";
			  html += "</tr>";
	};

	table_id.append(html);
	addOnChange();
}
function deleteRow(val) {
	var table_id = $("#"+val + " tr:last");
	table_id.find("#crud_type").val("D");
	table_id.hide();
}
function addOnChange(){
    $(".vr_style_type").off("change");
    $(".vr_style_type").on("change", function(){
        var vrStyleType = $(this).val();
        var styleId = $(this).next();
        var params = {vr_style_type : vrStyleType};
        ajaxCall(params, "vrStyleList.do");
        ajaxRes.success(function(result){
            var html = [], h = 0;
			if(result.RESULT_CODE == 'SUCCESS'){
			    $(result.RESULT_DATA).each(function(i, data) {
			        var id = "";
			        if(vrStyleType == "link") {
			            id = data.vr_style_link_id;
			        } else if (vrStyleType == "gallery") {
			            id = data.vr_style_gallery_id;
			        } else if (vrStyleType == "movie") {
			            id = data.vr_style_movie_id;
			        }
                    html[h++] = '<option value="'+id+'">'; 
                    html[h++] = data.title; 
                    html[h++] = '</option>'; 
			    });
			}
			if(html.length == 0) {
			    html[0] = "<option>not found!</option>";
			}
			styleId.html(html.join(' '));
        });
    });
}
</script>

<!-- delete form start -->
<form:form id="deleteForm" commandName="theForm" action="contentSave.do">
<form:hidden path="act" id="deleteAct" value="delete"/>
<form:hidden path="vr_site_content_id"/>
</form:form>

<form:form commandName="theForm" action="contentSave.do" enctype="multipart/form-data">
<form:hidden path="vr_site_id"/>
<form:hidden path="act"/>
    <h4 class="tit-h4">기본정보</h4>
    <table class="table_form">
    	<caption>테이블</caption>
    	<colgroup>
    		<col style="width:15%">
    		<col style="width:35%">
    		<col style="width:15%">
    		<col style="width:35%">
    	</colgroup>
    	<tbody>
<!--     		<tr> -->
<!--     			<th>경기장</th> -->
<!--     			<td colspan="3"> -->
<!--     				<select> -->
<%--     				<c:forEach var="data" items="${siteList}" varStatus="i"> --%>
<%--     					<option value='${data.vr_site_id}' <c:if test="${data.vr_site_id eq theForm.vr_site_id}"><c:out value="selected"/></c:if>>${data.title}</option> --%>
<%--     				</c:forEach> --%>
<!--     				</select> -->
<!--     			</td> -->
<!--     		</tr> -->
    		<tr>
    			<th>Content ID</th>
    			<td colspan="3">
                    <c:choose>
                    <c:when test="${empty theForm.vr_site_content_id}">
    					<input type="text" name="vr_site_content_id" class="wid100" title="제목 입력" value="${theForm.vr_site_content_id}">
    					<a href="#" class="btn btn_small" >중복검사</a>
                    </c:when>
                    <c:otherwise>
                        <c:out value="${theForm.vr_site_content_id}"/>
    					<input type="hidden" name="vr_site_content_id" value="${theForm.vr_site_content_id}">
                    </c:otherwise>
                    </c:choose>
    			</td>
    		</tr>
    		<tr>
    			<th>VR 제목</th>
    			<td><input type="text" name="name" class="col100" title="제목 입력" value="${theForm.name}"></td>
    			<th>노출 순서</th>
    			<td>
    				<select name="expose_order">
    					<option value="1" <c:if test="${theForm.expose_order eq 1}"><c:out value="selected"/></c:if>>1</option>
    					<option value="2" <c:if test="${theForm.expose_order eq 2}"><c:out value="selected"/></c:if>>2</option>
    					<option value="3" <c:if test="${theForm.expose_order eq 3}"><c:out value="selected"/></c:if>>3</option>
    				</select>
    			</td>
    		</tr>
    		<tr>
    			<th>그룹 ID</th>
    			<td>
    				<select name="vr_site_group_id">
    					<c:forEach var="data" items="${groupList}" varStatus="i">
    						<option value="${data.vr_site_group_id}" <c:if test="${data.vr_site_group_id eq theForm.vr_site_group_id}"><c:out value="selected"/></c:if>> ${data.name}</option>
    					</c:forEach>
    				</select>
    			</td>
    			<th>배경음악</th>
    			<td>
    				<select name="vr_style_music_id">
    				<c:forEach var="data" items="${styleMusicList}" varStatus="i">
    						<option value="${data.vr_style_music_id}" <c:if test="${data.vr_style_music_id eq theForm.vr_style_music_id}"><c:out value="selected"/></c:if>> ${data.title}</option>
    					</c:forEach>
    				</select>
    			</td>
    		</tr>
    		<tr>
    			<th>파노라마 이미지</th>
    			<td colspan="3">
                    <c:choose>
                    <c:when test="${empty theForm.path_panorama_image}">
                    <input type="file" name="panorama_file" class="" title="제목 입력">
                    </c:when>
                    <c:otherwise>
                    <a href="${CTX}${theForm.path_panorama_image}">${theForm.panorama_image_name }</a>
                    </c:otherwise>
                    </c:choose>
                </td>
    		</tr>
    	</tbody>
    </table>
    
    <!-- 스팟정보는 최초등록시 보이지 않고 수정모드일때 보여줌. -->
    <c:if test="${theForm.act eq 'update'}">
    <div class="section">
    	<div class="clearfix com-pt40">
    		<h4 class="tit-h4 float_left">스팟정보</h4>
    		<div class="float_right plusbtn_wrap">
    			<a href="javascript:appendRow('spot');" class="ico ico_plus">추가</a>
    			<a href="javascript:deleteRow('spot');" class="ico ico_minus">삭제</a>
    		</div>
    	</div>
    	<table class="table_basic " id="spot">
    		<caption>테이블</caption>
    		<colgroup>
    			<col style="width:8%;" />
    			<col style="width:auto;" />
    			<col style="width:30%;" />
    			<col style="width:20%;" />
    			<col style="width:20%;" />
    		</colgroup>
    		<thead>
    			<tr>
    				<th scope="col">번호</th>
    				<th scope="col">명칭</th>
    				<th scope="col">아이콘스타일</th>
    				<th scope="col">위치</th>
    				<th scope="col">링크</th>
    			</tr>
    		</thead>
    		<tbody>
                <c:choose>
                <c:when test="${empty spotList}">
    			<tr>
    				<td>1<input type='hidden' id="crud_type" name='spotList[0].crud_type' value='I'></td>
    				<td><input type="text" name=spotList[0].name class="col100"></td>
    				<td>
    					<select class='vr_style_type' name="spotList[0].vr_style_type"><option value='link'>링크</option><option value='gallery'>사진</option><option value='movie'>동영상</option></select>
    					<select name="spotList[0].vr_style_id">
                            <c:forEach var="linkList" items="${styleLinkList}">
                            <option value="${linkList.vr_style_link_id}">${linkList.title}</option>
                            </c:forEach>
                        </select>
    				</td>
    				<td><input type="text" name="spotList[0].pos_x" placeholder="x 좌표" class="wid50">&nbsp;&nbsp;<input type="text" name="spotList[0].pos_y" placeholder="y 좌표" class="wid50"></td>
    				<td>
    					<select name="spotList[0].link_content_id">
                        <c:forEach var="siteContent" items="${siteContentList}">
                            <option value="${siteContent.vr_site_content_id}">${siteContent.name}</option>
                        </c:forEach>
                        </select>
                        
    				</td>
    			</tr>
                </c:when>
                <c:otherwise>
                <c:forEach var="spotList" items="${spotList}" varStatus="i">
    			<tr>
    				<td>
                        ${i.index + 1}
                        <input type='hidden' id="crud_type" name='spotList[${i.index}].crud_type' value='U'>
                        <input type='hidden' id="vr_site_content_spot_id" name='spotList[${i.index}].vr_site_content_spot_id' value='${spotList.vr_site_content_spot_id}'>
                    </td>
    				<td><input type="text" name="spotList[${i.index}].name" class="col100" value="${spotList.name}"></td>
    				<td>
    					<select class='vr_style_type' name="spotList[${i.index}].vr_style_type">
                            <option value='link' <c:if test="${spotList.vr_style_type eq 'link'}"><c:out value="selected"/></c:if>>링크</option>
                            <option value='gallery' <c:if test="${spotList.vr_style_type eq 'gallery'}"><c:out value="selected"/></c:if>>사진</option>
                            <option value='movie' <c:if test="${spotList.vr_style_type eq 'movie'}"><c:out value="selected"/></c:if>>동영상</option>
                        </select>
    					<select name="spotList[${i.index}].vr_style_id">
                            <c:choose>
                            <c:when test="${spotList.vr_style_type eq 'link'}">
                                <c:forEach var="styleList" items="${styleLinkList}">
                                    <option value="${styleList.vr_style_link_id}" <c:if test="${spotList.vr_style_id eq styleList.vr_style_link_id}"><c:out value="selected"/></c:if>>${styleList.title}</option>
                                </c:forEach>
                            </c:when>
                            <c:when test="${spotList.vr_style_type eq 'gallery'}">
                                <c:forEach var="styleList" items="${styleGalleryList}">
                                    <option value="${styleList.vr_style_gallery_id}" <c:if test="${spotList.vr_style_id eq styleList.vr_style_gallery_id}"><c:out value="selected"/></c:if>>${styleList.title}</option>
                                </c:forEach>
                            </c:when>
                            <c:when test="${spotList.vr_style_type eq 'movie'}">
                                <c:forEach var="styleList" items="${styleMovieList}">
                                    <option value="${styleList.vr_style_movie_id}" <c:if test="${spotList.vr_style_id eq styleList.vr_style_movie_id}"><c:out value="selected"/></c:if>>${styleList.title}</option>
                                </c:forEach>
                            </c:when>
                            </c:choose>
                        </select>
    				</td>
    				<td>
                        <input type="text" name="spotList[${i.index}].pos_x" placeholder="x 좌표" class="wid50" value="${spotList.pos_x}">
                        &nbsp;&nbsp;<input type="text" name="spotList[${i.index}].pos_y" placeholder="y 좌표" class="wid50" value="${spotList.pos_y}">
                    </td>
    				<td>
    					<select name="spotList[${i.index}].link_content_id">
                        <c:forEach var="siteContent" items="${siteContentList}">
                            <option value="${siteContent.vr_site_content_id}" <c:if test="${spotList.link_content_id eq siteContent.vr_site_content_id}"> <c:out value="selected" /></c:if> >${siteContent.name}</option>
                        </c:forEach>
                        </select>
    				</td>
    			</tr>
                </c:forEach>
                </c:otherwise>
                </c:choose>
    		</tbody>
    	</table>
    </div>
    </c:if>
    
    <div class="btn_wrap float_right">
        <c:choose>
        <c:when test="${theForm.act eq 'update'}">
            <a href="contentList.do?vr_site_id=${theForm.vr_site_id}" class="btn white">취소</a>
            <a href="#" class="btn danger btnDelete">삭제</a>
            <a href="#" class="btn primary btnSave">수정</a>
        </c:when>
        <c:otherwise>
            <a href="contentList.do?vr_site_id=${theForm.vr_site_id}" class="btn ">목록</a>
            <a href="#" class="btn primary btnSave">등록</a>
        </c:otherwise>
        </c:choose>     
    </div>
</form:form>

