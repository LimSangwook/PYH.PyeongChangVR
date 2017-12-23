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
    <form:form id="deleteForm" commandName="theForm" action="musicSave.do">
        <form:hidden path="act" id="deleteAct" value="delete"/>
        <form:hidden path="vr_style_music_id"/>
    </form:form>
    
    <form:form commandName="theForm" action="musicSave.do" enctype="multipart/form-data">
	<form:hidden path="act"/>
	<form:hidden path="vr_style_music_id"/>
	<table class="table_form">
    	<caption>콘텐츠 등록</caption>
    	<colgroup>
    			<col style="width:20%;" />
    			<col style="width:30%;" />
    			<col style="width:20%;" />
    			<col style="width:30%;" />
    	</colgroup>
    	<tbody>
    		<tr>
    			<th scope="row">제목</th>
    			<td colspan="3"><input type="text" name="title" class="wid300" title="제목 입력" value="${theForm.title}"></td>
    		</tr>
    		<tr>
    			<th scope="row">파일</th>
                <c:choose>
                        <c:when test="${theForm.act eq 'update'}">
                            <td colspan="3"><a href="${CTX}/assets/admin/vr/music/${theForm.path_file}" target="_blank" >${theForm.path_file}</a></td>
                        </c:when>
                        <c:otherwise>
                			<td colspan="3"><input type="file" name="music_file" ></td>
                        </c:otherwise>
                    </c:choose>
    		</tr>
    		<tr>
    			<th scope="row">볼륨</th>
    			<td><input type="text" name="volume" class="wid100" title="볼륨 입력" value="${theForm.volume}"></td>
    		</tr>
    		<tr>
    			<th scope="row">자동재생</th>
    			<td>
    				<input type="radio" name="auto_play" id="bgm_auto1" value="Y" <c:if test="${theForm.auto_play eq 'Y'}"><c:out value="checked"/></c:if>> <label for="bgm_auto1">자동으로 재생</label>&nbsp;&nbsp;&nbsp;
    				<input type="radio" name="auto_play" id="bgm_auto2" value="N" <c:if test="${theForm.auto_play eq 'N'}"><c:out value="checked"/></c:if>> <label for="bgm_auto2">수동으로 재생</label>
    			</td>
    			<th scope="row">반복</th>
    			<td>
    				<input type="radio" name="repeat_play" id="bgm_loop1" value="Y" <c:if test="${theForm.repeat_play eq 'Y'}"><c:out value="checked"/></c:if>> <label for="bgm_loop1">반복 재생</label>&nbsp;&nbsp;&nbsp;
    				<input type="radio" name="repeat_play" id="bgm_loop2" value="N" <c:if test="${theForm.repeat_play eq 'N'}"><c:out value="checked"/></c:if>> <label for="bgm_loop2">한번만 재생</label>
    			</td>
    		</tr>
    		<tr>
    			<th scope="row">hfov</th>
    			<td><input type="text" name="hfov" class="wid100" title="hfov 입력" value="${theForm.hfov}"></td>
    			<th scope="row">yaw</th>
    			<td><input type="text" name="yaw" class="wid100" title="yaw 입력" value="${theForm.yaw}"></td>
    		</tr>
    		<tr>
    			<th scope="row">pitch</th>
    			<td><input type="text" name="pitch" class="wid100" title="pitch 입력" value="${theForm.pitch}"></td>
    			<th scope="row">roll</th>
    			<td><input type="text" name="roll" class="wid100" title="roll 입력" value="${theForm.roll}"></td>
    		</tr>
    	</tbody>
    </table><!-- //table_basic -->

    <div class="btn_wrap float_right">
		<c:choose>
		<c:when test="${theForm.act eq 'update'}">
			<a href="musicList.do" class="btn white">취소</a>
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
