<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/comm/taglib.jsp"%>

<div class="pastforum_view">
	<div class="pf_logo">
		<c:choose>
		<c:when test="${!empty result.master_image}">
			<img src="${uploadDefaultUrl}/${uploadImgThumbDir}${result.master_image}" alt="<c:out value="${result.company_name}"/>"/>
		</c:when>
		<c:otherwise>
			<img src="/assets/plugin/fileupload/img/noimage2.png"/>
		</c:otherwise>
		</c:choose>
		<p><c:out value="${result.company_name}"/></p>
	</div>
	<div class="pf_summary">
		<ul>
			<li><span class="title">대표</span> <c:out value="${result.ceo_name}"/></li>
			<li><span class="title">주소</span> <c:out value="${result.address1}"/></li>
			<li><span class="title">전화</span> <c:out value="${result.master_phone}"/></li>
			<li><span class="title">팩스</span> <c:out value="${result.fax}"/></li>
			<li><span class="title">이메일</span> <a href="mailto:<c:out value="${result.master_email}"/>"><c:out value="${result.master_email}"/></a></li>
			<li><span class="title">홈페이지</span> <a href="<c:out value="${result.home_page}"/>" target="_blank" title="새창열림"><c:out value="${result.home_page}"/></a></li>
		</ul>
	</div>
</div><!-- //pastforum_view -->
<div class="pf_contents">
	<c:out value="${result.content}" escapeXml="false"/>
</div>
<div class="btn_wrap textAlign_right">
	<a href="companyList.do" class="btn">목록</a>
</div>

<script>
$( document ).ready(function() {
	//게시판 이미지 100%
	var coImg = $('.pf_logo img');
	var coWidth = $('.pf_logo').width();
	coImg.each(function (i) {

		if (coImg.eq(i).width() > coWidth){
			coImg.eq(i).css("width", "100%");
		}else{
			coImg.eq(i).css("width", "auto");
		}
	});
});
</script>