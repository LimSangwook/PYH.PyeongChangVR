<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/comm/taglib.jsp"%>

<!-- 카카오톡 sdk-->
<script src="//developers.kakao.com/sdk/js/kakao.min.js"></script>
<!-- SNS -->
<script src="/assets/script/front.sns.facebook.js" type="text/javascript"></script>
<script src="/assets/script/front.sns.kakao.js" type="text/javascript"></script>

<script>
var AUTH_TOKEN = '';
// 네이버 콜백정보
function fnNaverResultCallback(result, token){
	var obj = $.parseJSON(result);	
	/* console.log($.parseJSON(result));	
	console.log(obj.response.email);
	console.log(obj.response.name);
	console.log(obj.response.nickname);
	console.log(token); */
	// 로그인 처리
	fnSnsLoginProc(obj.response.email, obj.response.name, 'naver', token);
}

// 로그인 토큰생성 (카카오톡, 페이스북)
function fnGenerateToken(snsType){
	ajaxCall(null,'/generateTokenJson.do');
	ajaxRes.success(function(result){
		
		AUTH_TOKEN = result.RESULT_DATA;
		
		if(snsType == 'faceb'){
			// 페이스북로그인
			facebookLogin();
		} else if(snsType == 'kakao'){
			// 카카오로그인
			kakaoLogin();
		}
		
	});
}

// SNS 로그인 성공시 세션가공
var redirectUrl = "${param.redirectUrl}";
function fnSnsLoginProc(id, name, snsType, token){
	var params = {user_id:id, user_name:name, sns_type:snsType, token:token};
	ajaxCall(params,'/snsLoginProc.do');
	ajaxRes.success(function(result){
		if(result.RESULT_CODE == 'SUCCESS'){
			//alert('로그인 성공!!!');
			
			location.href= redirectUrl?redirectUrl:"/";
		} else {
			alert('비정상 접근입니다.');
		}											
	});
}
</script>

<section class="section-guide01 guide-type01 sub-visual05">
	<div>
		<h1>SNS 로그인</h1>
	</div>
</section>
<section class="section-vr">
<!-- ========================== 내용 시작 ========================= -->

<div class="login_wrap">
	<div class="login_guide">
		VR 사진을 게시하시려면 로그인이 필요합니다.<br>
		SNS로그인 해주시기 바랍니다.
	</div>
	<a href="javascript:fnGenerateToken('faceb');" class="login_btn fb"><span class="sns_ico"></span> 페이스북으로 로그인하기</a>
	<a href="javascript:fnGenerateToken('kakao');" class="login_btn ct"><span class="sns_ico"></span> 카카오톡으로 로그인하기</a>
	<a href="https://nid.naver.com/oauth2.0/authorize?client_id=<spring:eval expression="@config['NAVER.CLIENT.ID']"/>&response_type=code&redirect_uri=<spring:eval expression="@config['SITE.DOMAIN']"/>/naverAuthCallback.do&state=1236548795464" onclick="popup(this.href,'naverLoginPop','700','550','no','yes','3'); return false;" class="login_btn nv"><span class="sns_ico"></span> 네이버로 로그인하기</a>
	
</div>


<!-- ========================== 내용 종료 ========================= -->
</section><!-- //section-vr -->



<%-- <a href="javascript:fnGenerateToken('faceb');">페이스북 로그인</a><br/>
<a href="javascript:fnGenerateToken('kakao');">카카오톡 로그인</a><br/>
<a href="https://nid.naver.com/oauth2.0/authorize?client_id=<spring:eval expression="@config['NAVER.CLIENT.ID']"/>&response_type=code&redirect_uri=<spring:eval expression="@config['SITE.DOMAIN']"/>/naverAuthCallback.do&state=1236548795464" onclick="popup(this.href,'naverLoginPop','700','550','no','yes','3'); return false;">네이버 로그인</a> --%>

