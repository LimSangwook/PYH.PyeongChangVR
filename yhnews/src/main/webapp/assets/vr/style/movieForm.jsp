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
    <form:form id="deleteForm" commandName="theForm" action="movieSave.do">
        <form:hidden path="act" id="deleteAct" value="delete"/>
        <form:hidden path="vr_style_movie_id"/>
    </form:form>
    <form:form commandName="theForm" action="movieSave.do">
    <form:hidden path="act"/>
    <form:hidden path="vr_style_movie_id"/>
    
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
    			<th scope="row">동영상 url</th>
    			<td><input type="text" name="url" class="col100" title="유투브 url 입력" value="${theForm.url}"></td>
    		</tr>
    	</tbody>
    </table><!-- //table_basic -->
    
    <div class="btn_wrap float_right">
    	<a href="movieList.do" class="btn">목록</a> 
    	<a href="#" class="btn primary btnSave">등록</a>
    </div>

    </form:form>
</fieldset>

