<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/comm/taglib.jsp"%>

<c:set var="GLOBAL_USER_NAME" value="${sessionScope.YHNEWS_LOGIN_SESSION_KEY.user_name}" scope="application"/>
<c:set var="GLOBAL_USER_ID" value="${sessionScope.YHNEWS_LOGIN_SESSION_KEY.user_id}" scope="application"/>
<c:set var="GLOBAL_USER_AUTH" value="${sessionScope.YHNEWS_LOGIN_SESSION_KEY.auth_level}" scope="application"/>
<c:set var="GLOBAL_USER_MUSEUM_NO" value="${sessionScope.YHNEWS_LOGIN_SESSION_KEY.museum_no}" scope="application"/>
<c:set var="CTX" value="${pageContext.request.contextPath}" scope="application"/>

<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<title>연합뉴스 360 라이브러리 관리자페이지</title>

<!-- 마크업 전달 속성 -->
<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0, user-scalable=no" />
<meta name="format-detection" content="telephone=no, email=no, address=no">
<meta http-equiv="X-UA-Compatible" content="IE=edge">

<link rel="stylesheet" href="${CTX}/assets/admin/css/style.css">
<link rel="stylesheet" href="${CTX}/assets/admin/css/media.css">
<link rel="stylesheet" href="${CTX}/assets/admin/css/custom.css">
<link rel="stylesheet" href="${CTX}/assets/plugin/jquery/jquery-ui.css">

<!--[if lt IE 9]>
<script src="${CTX}/assets/admin/js/html5.js"></script>
<script src="${CTX}/assets/admin/js/css3-mediaqueries.js"></script>
<![endif]-->

<script src="${CTX}/assets/admin/js/jquery-1.12.4.min.js" type="text/javascript"></script>
<script src="${CTX}/assets/plugin/jquery/jquery-ui.min.js" type="text/javascript"></script>
<script src="${CTX}/assets/admin/js/common.js" type="text/javascript"></script>
	
<script src="${CTX}/assets/plugin/comm/common.js" type="text/javascript"></script>
<script src="${CTX}/assets/plugin/validate/form.validate.js?v=8" type="text/javascript"></script>
<script src="${CTX}/assets/plugin/jquery/jquery.number.js" type="text/javascript"></script>


<script type="text/javascript">

var ctx = "${CTX}"; 
//업로드서버경로
var uploadDefaultUrl = "${uploadDefaultUrl}"; 
//섬네일 이미지 기본폴더명
var uploadImgThumbDir = "${uploadImgThumbDir}";
//에디터 이미지 기본폴더명
var uploadImgEditorDir = "${uploadImgEditorDir}";
//로그인아이디
var GLOBAL_USER_ID = "${GLOBAL_USER_ID}";
//ajaxRes
var ajaxRes;

function ajaxCall(data, url){
	ajaxRes = jQuery.ajax({
		data:data,
		url : url,
		type:"post",
		dataType:"json",
		beforeSend: function(){
			// beforeSend
		},
		error: function(e) {
			alert("일시적인 오류가 발생하였습니다.");
		},		
		complete: function(){
			// complete
		}
	});
}

function fnLogout(){
	window.location.href = ctx+"/siteManage/logout.do";
}

function regist_view(formAreaId) {
	jQuery('.blind_black').show();
	jQuery('.blind_black').animate({opacity:0.6}, 500);
	jQuery('#'+formAreaId).show();
	jQuery('#'+formAreaId).animate({opacity:1}, 500);
}

function regist_close(formAreaId) {
	jQuery('#'+formAreaId).animate({opacity:0}, 500, function(){
		jQuery('#'+formAreaId).hide();
	});
	jQuery('.blind_black').animate({opacity:0}, 500, function(){
		jQuery('.blind_black').hide();
	});
}
</script>