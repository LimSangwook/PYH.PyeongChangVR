<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/comm/taglib.jsp"%>

<!-- 카카오톡 sdk-->
<script src="//developers.kakao.com/sdk/js/kakao.min.js"></script>
<!-- SNS -->
<script src="/assets/script/front.sns.facebook.js" type="text/javascript"></script>
<script src="/assets/script/front.sns.kakao.js" type="text/javascript"></script>

<script type="text/javascript">
$(function(){
	// 카카오스토리
	$('.ico_ks').on('click',function(e){
		e.preventDefault();
		shareStory();
	});
	// 페이스북
	$('.ico_fb').on('click',function(e){
		e.preventDefault();
		facebookFeed();
	});
	// 카카오 링크
	if(isMobileAccess == "true"){
		shareKakaoLink();	
	}
});
</script>
<input type="hidden" id="snsLinkUrl" value="${requestURL}?board_key=${result.board_key}"/>
<input type="hidden" id="postTitle" value="<c:out value="${result.title}"/>"/>

<c:choose>
<c:when test="${!empty result.master_image}">
<input type="hidden" id="postImage" value="${uploadDefaultUrl}/${uploadImgThumbDir}${result.master_image}"/>
</c:when>
<c:otherwise>
<input type="hidden" id="postImage" value=""/>
</c:otherwise>
</c:choose>

<a href="#" class="ico_sns ico_fb">페이스북</a>
<!-- <a href="#" class="ico_sns ico_tw">트위터</a> -->
<a href="#" class="ico_sns ico_ks">카카오스토리</a>
<c:if test="${isMobileAccess}">
<a href="#" class="ico_sns ico_kakao" id="kakao-link-btn">카카오톡</a>
</c:if>

