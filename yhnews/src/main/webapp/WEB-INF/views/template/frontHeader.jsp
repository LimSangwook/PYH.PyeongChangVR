<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/comm/taglib.jsp"%>

<c:set var="GLOBAL_USER_NAME" value="${sessionScope.YHNEWS_LOGIN_SESSION_KEY.user_name}" scope="application"/>
<c:set var="GLOBAL_USER_ID" value="${sessionScope.YHNEWS_LOGIN_SESSION_KEY.user_id}" scope="application"/>
<c:set var="GLOBAL_USER_AUTH" value="${sessionScope.YHNEWS_LOGIN_SESSION_KEY.auth_level}" scope="application"/>
<c:set var="CTX" value="${pageContext.request.contextPath}" scope="application"/>

<c:set var="GLOBAL_SITE_TITLE">
<c:forEach var="data" items="${menuLocationList}" varStatus="i">
	<c:if test="${i.index > 0}">&gt;</c:if>
	${data.menu_name}			
</c:forEach>
</c:set>

	<meta http-equiv="Content-Type" content="text/html;charset=utf-8" />
	<meta http-equiv="X-UA-Compatible" content="IE=edge, chrome=1">
	<title><c:out value="${GOBAL_SITE_SEO_INFO.title}"/></title>
	<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0, user-scalable=no, target-densitydpi=medium-dpi">
	
	<meta name='description' content='<c:out value="${GOBAL_SITE_SEO_INFO.content}"/>'>
	<meta name='keywords' content='<c:out value="${GOBAL_SITE_SEO_INFO.keyword}"/>'>
	
	<meta property="og:type" content="website">
	<meta property="og:title" content="<c:out value="${GOBAL_SITE_SEO_INFO.title}"/>"><!-- SEO정보 : 사이트제목 -->
	<meta property="og:description" content="<c:out value="${GOBAL_SITE_SEO_INFO.content}"/>"><!-- SEO정보 : 사이트내용 -->
		
	<c:if test="${linkAddress eq 'index'}">
		<meta property="og:image" content="<c:out value="${GOBAL_SITE_SEO_INFO.homepage}"/>/assets/front/images/common/logo_portal.gif"><!-- SEO정보 : 로고URL 추가요청 -->
		<meta property="og:url" content="<c:out value="${GOBAL_SITE_SEO_INFO.homepage}"/>"><!-- SEO정보 : 홈페이지주소 -->
	</c:if>	

	<!-- 네이버 노출 선호URL -->
	<link rel="canonical" href="<c:out value="${GOBAL_SITE_SEO_INFO.homepage}"/>/front/library/museumIntroduction/html.do"><!-- SEO정보 : 선호URL 추가요청 -->

	<!-- 구글 검색 노출 관련 부분 -->
	<meta name="google-site-verification" content="nJOC39OHvp-_569aJpQSFrsXkpfRadVV-HZybGojGeI" />
	<meta name="google-site-verification" content="L_pD5Id6Q7biIXX_eiD9zZsjoLpKa3vPeP5mM_KJjHE" />
	
	
	<link rel="stylesheet" href="/assets/comn/css/style.css">
	<link rel="shortcut icon" href="http://img.yonhapnews.co.kr/basic/svc/favicon.ico">
	<link rel="stylesheet" href="/assets/comn/css/font.css">
	<link rel="stylesheet" href="/assets/comn/css/default.css">
	<link rel="stylesheet" href="/assets/comn/css/layout.css">
	<link rel="stylesheet" href="/assets/comn/css/vr.css">
	<link rel="stylesheet" href="/assets/comn/css/slick.css"><!-- 1206 추가 -->
	
	<script src="/assets/comn/js/jquery-1.12.4.min.js" type="text/javascript"></script>
	<script src="/assets/comn/js/jquery-1.12.4.js" type="text/javascript"></script>
	<script src="/assets/comn/js/modernizr.custom.js" type="text/javascript"></script>
	<script src="/assets/comn/js/slick.min.js" charset="utf-8" type="text/javascript"></script>	
	<script src="/assets/comn/js/common.js" type="text/javascript"></script>
	
	<script src="/assets/plugin/jquery/jquery.number.js"></script>
	<script src="/assets/plugin/comm/common.js" type="text/javascript"></script>
	<script src="/assets/plugin/validate/form.validate.js" type="text/javascript"></script>
	<!-- Print Plugin-->
	<script src="/assets/plugin/print/jquery.print.js"></script>	



<script type="text/javascript">
//로그인아이디
var GLOBAL_USER_ID = "${GLOBAL_USER_ID}";
var ctx = "${CTX}";
//업로드서버경로
var uploadDefaultUrl = "${uploadDefaultUrl}"; 
//섬네일 이미지 기본폴더명
var uploadImgThumbDir = "${uploadImgThumbDir}";
//에디터 이미지 기본폴더명
var uploadImgEditorDir = "${uploadImgEditorDir}";
//sns 페이스북 API 키
var snsFacebookApiKey = "${snsFacebookApiKey}";
// sns 카카오톡 API 키
var snsKakaoApiKey ="${snsKakaoApiKey}";
// 모바일접근여부
var isMobileAccess = "${isMobileAccess}";

// 로그아웃
function fnLogout(){	
	window.location.href = ctx+"/front/logout.do";
}

//ajaxRes
var ajaxRes;
function ajaxCall(data, url){
	ajaxRes = $.ajax({
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
jQuery(function(){
	// 프린트
	jQuery('.btnPrint').on('click', function(e) {
		e.preventDefault();
		jQuery("#printArea").print();
  	});
});
</script>