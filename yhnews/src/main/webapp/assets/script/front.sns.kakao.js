$(function(){
	//카카오 앱의 JavaScript 키 설정
	Kakao.init(snsKakaoApiKey);
	//카카오 로그인 상태
	//fnKakaoLoginState();	
});
// 카카오 로그인
function kakaoLogin() {
	// 로그인 창을 띄웁니다.
	Kakao.Auth.login({
	  success: function(authObj) {
	    //alert(JSON.stringify(authObj));
	    // SNS 로그인 타입지정
	    //setCookie("lastSnsLoginType", "kakao", expdate);
		  
		fnKakaoLoginState();
	  },
	  fail: function(err) {
	    //alert(JSON.stringify(err));
	  }
	});
}

// 로그인 상태
function fnKakaoLoginState(){
	// 로그인 정보
    Kakao.API.request({
        url: '/v1/user/me',
        success: function(res) {
          //alert(JSON.stringify(res));          
          fnSnsLoginProc(res.id, res.properties.nickname, 'kakao', AUTH_TOKEN);
          //alert(">"+res.id+','+res.properties.nickname);
        },
        fail: function(error) {
          	//alert(JSON.stringify(error));
         	// 로그아웃
        	/*if(GLOBAL_USER_SNS_TYPE == "kakao"){
        		$('#snsLogoutForm').submit();
        	}*/
        }
      });
}

// 카카오스토리 공유
function shareStory() {	
	Kakao.Story.share({
    	url: $('#snsLinkUrl').val(),    			
    	text: $('#postTitle').val()
  	});
}

//카카오 링크 공유
function shareKakaoLink() {	
	if($('#postImage').val().isNvl()){
		Kakao.Link.createTalkLinkButton({
			  container: '#kakao-link-btn',
		      label: $('#postTitle').val(),
		      image: {
		        src: $('#postImage').val(),
		        width: '300',
		        height: '200'
		      },
		      webLink  : {
		        text: '바로가기',
		        url : $('#snsLinkUrl').val()
		      }
		    });
	} else {
		Kakao.Link.createTalkLinkButton({
			  container: '#kakao-link-btn',
		      label: $('#postTitle').val(),		      
		      webLink  : {
		        text: '바로가기',
		        url : $('#snsLinkUrl').val()
		      }
		    });
	}
	
}