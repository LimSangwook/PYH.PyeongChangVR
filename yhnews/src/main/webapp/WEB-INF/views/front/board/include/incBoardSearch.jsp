<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/comm/taglib.jsp"%>

<!-- 검색 -->
<fieldset>
<legend>게시물 검색</legend>
<form:form commandName="theForm" action="boardList.do" method="get">
	<div class="search_wrap search_type02">
		<form:select path="search_type"  name="select_std" id="select_std" title="구분선택" class="inp_type01 ">
		<form:option value="" label="선택"/>
		<form:option value="1" label="제목"/>
		<form:option value="2" label="내용"/>
		<form:option value="3" label="작성자"/>
		</form:select>
		<form:input path="search_keyword" title="검색어 입력" class="inp_type01 col25"/>
		<button type="submit" class="btn_type01 btn_srch"><span class="ico ico_search"></span>검색</button>
	</div>
</form:form>
</fieldset>

<div class="guide_total">
	총 <strong><fmt:formatNumber value="${theForm.total_count}"/></strong>개의 글이 있습니다.
</div>