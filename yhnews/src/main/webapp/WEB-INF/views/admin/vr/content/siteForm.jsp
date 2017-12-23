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
	
	if(act == 'update'){
		$('input[type=password]').attr('disabled',true);
	}	
	
});

function appendRow(val) {
	var table_id = $("#"+val);
	var count = table_id.find('li').length;
	if (val == "group"){
		var html = "<li>";
		    html += '<input type="hidden" id="crud_type" name="list['+count+'].crud_type" value="I" >';
            html += "<label for='"+val+"_name_"+count+"'>그룹명</label><input id='"+val+"_name_"+count+"' type='text' name='list["+count+"].name' class='col16'>";
            html += "<label for='"+val+"_desc_"+count+"'>그룹설명</label><input id='"+val+"_desc_"+count+"' type='text' name='list["+count+"].description' class='col45'>";
            html += "</li>";
	};
	table_id.append(html);
}
function deleteRow(val) {
	var table_id = $("#"+val);
	var count = table_id.find('li').length;
	if (count == 1){
		alert('하나 이상 등록하셔야합니다.');
	} else {
		var table_id = $("#"+val + " li:last");
		table_id.find("#crud_type").val("D");
		table_id.hide();
	}
}
</script>
<!-- delete form start -->
<form:form id="deleteForm" commandName="theForm" action="siteSave.do">
<form:hidden path="act" id="deleteAct" value="delete"/>
<form:hidden path="vr_site_id"/>
</form:form>

<form:form commandName="theForm" action="siteSave.do" enctype="multipart/form-data">
<form:hidden path="act"/>
			<div class="col-wrap">
				<div class="col-item col25">
					<div class="photoArea">
						<p class="member_photo">
    						<c:choose>
        						<c:when test="${theForm.act eq 'update'}">
        							<img src="${CTX}/vrContents/adminContents/${theForm.path_image}" >
        						</c:when>
                                <c:otherwise>
                                    <img src="/assets/admin/img/noPhoto.png" alt="노이미지">
                                	<td><input type="file" name="image_file" title="링크이미지 첨부"></td>
                                </c:otherwise>
                            </c:choose>
                        </p>
						<p class="textAlign_center">
						</p>
					</div>
				</div><!-- //col-item -->
				<div class="col-item col75">

					<table class="table_form">
						<caption>테이블</caption>
						<colgroup>
							<col style="width:15%">
							<col style="width:85%">
						</colgroup>
						<tbody>
							<tr>
								<th>고유번호</th>
								<td><input type="text" name="vr_site_id" class="wid300" title="제목 입력" value="${theForm.vr_site_id}"></td>
							</tr>
							<tr>
								<th>경기장 이름</th>
								<td><input type="text" name="title" class="wid300" title="제목 입력" value="${theForm.title}"></td>
							</tr>
							<tr>
								<th>경기장아이콘</th>
                                    <c:choose>
			                        <c:when test="${theForm.act eq 'update'}">
			                            <td><img style="background-color:powderblue;" src="${CTX}/vrContents/adminContents/${theForm.path_icon}" ></td>
			                        </c:when>
			                        <c:otherwise>
										<td><input type="file" name="icon_file" ><br><em>이이콘 크기 : 90px X 90px</em></td>
			                        </c:otherwise>
			                        </c:choose>
							</tr>
							<tr>
								<th>그룹설정</th>
								<td>
									<div class="float_right plusbtn_wrap">
										<a href="javascript:appendRow('group');" class="ico ico_plus">추가</a>
										<a href="javascript:deleteRow('group');" class="ico ico_minus">삭제</a>
									</div>
									<ul class="group_list" id="group">
										<c:forEach var="groupItem" items="${group}" varStatus="i">
										<li>
                                            <input type="hidden" id="crud_type" name="list[${i.index}].crud_type" value="U" />
                                            <input type="hidden" name="list[${i.index}].vr_site_group_id" value="${groupItem.vr_site_group_id}" />
											<label for="group_name_${i.index+1}">그룹명</label><input id="group_name_${i.index+1}" type="text" name="list[${i.index}].name" class="col16" value="${groupItem.name}">
											<label for="group_desc_${i.index+1}">그룹설명</label><input id="group_desc_${i.index+1}" type="text" name="list[${i.index}].description" class="col45" value="${groupItem.description}">
										</li>
										</c:forEach>
									</ul>
								</td>
							</tr>
						</tbody>
					</table>

				</div><!-- //col-item -->
			</div><!-- //col-wrap -->

			<div class="btn_wrap float_right">
				<c:choose>
				<c:when test="${theForm.act eq 'update'}">
                    <a href="createSiteVR.do?vr_site_id=${theForm.vr_site_id}" class="btn primary btnCreateVR">VRTour생성</a>
					<a href="siteList.do" class="btn white">취소</a>
					<a href="#" class="btn danger btnDelete">삭제</a>
					<a href="#" class="btn primary btnSave">수정</a>
				</c:when>
				<c:otherwise>
					<a href="siteList.do" class="btn ">목록</a>
					<a href="#" class="btn primary btnSave">등록</a>
				</c:otherwise>
				</c:choose>		
			</div>
</form:form>

