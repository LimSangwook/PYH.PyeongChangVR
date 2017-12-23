<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/comm/taglib.jsp"%>

<script type="text/javascript">
var act = "${theForm.act}";

jQuery(function(){
	// jquery 달력 세팅
	//setCalendar("yy-mm-dd");	
	// 등록/수정
	jQuery('.btnSave').on('click',function(){
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
			jQuery('#theForm').submit();
		}
	});
});
</script>

<fieldset>
<legend></legend>

<form:form commandName="theForm" action="seoSave.do">
<form:hidden path="act"/>
<form:hidden path="seo_key"/>
	
<table class="table_form">
	<caption>SEO 정보</caption>
	<colgroup>
		<col style="width:20%;" />
		<col style="width:80%;" />
	</colgroup>
	<tbody>
		<tr>
			<th scope="row">홈페이지주소</th>
			<td><form:input path="homepage" title="홈페이지주소" cssClass="col50" isNullCheck="true" maxlength="100"/></td>
		</tr>
		<tr>
			<th scope="row">사이트제목</th>
			<td><form:input path="title" title="사이트제목" cssClass="col50" isNullCheck="true" maxlength="100"/></td>
		</tr>
		<tr>
			<th scope="row">사이트내용</th>
			<td><form:input path="content" title="사이트내용" cssClass="col50" maxlength="100"/></td>
		</tr>
		<tr>
			<th scope="row">사이트키워드</th>
			<td>
				<form:input path="keyword" title="사이트키워드" cssClass="col50" maxlength="100"/>
				<p>키워드 구분은 ','입니다. ex) 박물관,포럼</p>
			</td>
		</tr>
		<tr>
			<th scope="row">공식이메일</th>
			<td><form:input path="email" title="공식이메일" cssClass="col50" maxlength="100"/></td>
		</tr>
		<tr>
			<th scope="row">운영기관</th>
			<td><form:input path="company_name" title="운영기관" cssClass="col50" maxlength="100"/></td>
		</tr>
		<tr>
			<th scope="row">전화번호</th>
			<td>
				<form:input path="tel" title="전화번호" cssClass="col50" maxlength="100"/>
				<p>ex) 000-0000-0000</p>
			</td>
		</tr>
		<tr>
			<th scope="row">팩스번호</th>
			<td>
				<form:input path="fax" title="팩스번호" cssClass="col50" maxlength="100"/>
				<p>ex) 000-0000-0000</p>
			</td>
		</tr>
		<tr>
			<th scope="row">주소</th>
			<td><form:input path="address" title="주소" cssClass="col50" maxlength="100"/></td>
		</tr>
		<tr>
			<th scope="row">개설일</th>
			<td>
				<p>
					<form:input path="open_day" title="개설일" cssClass="col16 inputCal" maxlength="10" readonly="true"/>				
				</p>
			</td>
		</tr>
	</tbody>
</table>
</form:form>

<div class="btn_wrap textAlign_center">	
	<c:choose>
	<c:when test="${theForm.act eq 'update'}">		
		<a href="#" class="btn primary btnSave">수정</a>
	</c:when>
	<c:otherwise>
		<a href="#" class="btn primary btnSave">등록</a>
	</c:otherwise>
	</c:choose>
</div>

</fieldset>