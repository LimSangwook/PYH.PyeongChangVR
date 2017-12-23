function statusChangeCallback(response) {
    //console.log('statusChangeCallback');
    //console.log(response);    
    if (response.status === 'connected') {
    	//setFacebookLogin();	
    } else if (response.status === 'not_authorized') {
    	
      //console.log('Please log into this app.');      
    } else {
    	//alert(GLOBAL_USER_SNS_TYPE);
    	// 로그아웃
    	/*if(GLOBAL_USER_SNS_TYPE == "faceb"){
    		$('#snsLogoutForm').submit();	
    	}*/
    }
  }
  
 function checkLoginState() {
   FB.getLoginStatus(function(response) {
     statusChangeCallback(response);
   });
 }
 
 window.fbAsyncInit = function() {
    FB.init({
      appId      : snsFacebookApiKey,
      xfbml      : true,
      version    : 'v2.7'
    });
    
    FB.getLoginStatus(function(response) {
	    statusChangeCallback(response);
  	});
 };
	  
(function(d, s, id){
    var js, fjs = d.getElementsByTagName(s)[0];
    if (d.getElementById(id)) {return;}
    js = d.createElement(s); js.id = id;
    js.src = "//connect.facebook.net/ko_KR/sdk.js";
    fjs.parentNode.insertBefore(js, fjs);
  }(document, 'script', 'facebook-jssdk')); 

function setFacebookLogin() {
    //console.log('Welcome!  Fetching your information.... ');
    FB.api('/me', /*{fields: 'email'},*/ function(response) {
      //console.log(JSON.stringify(response));
      //console.log('Successful login for: ' + response.name);
      //console.log('Thanks for logging in,'+response.name);
      
      //fnSnsLoginProc(response.id, response.name, 'faceb');
    	//console.log(JSON.stringify(response));
    	//alert(response.id+', '+response.name);
    	
    	// 로그인 처리
    	fnSnsLoginProc(response.id, response.name, 'faceb', AUTH_TOKEN);
    });
  }

function facebookLogin() {
 // FB.login(function(){}, {scope: 'publish_actions'});
	FB.login(function(response) {
	    if (response.authResponse) {
	    	// SNS 로그인 타입지정
		    //setCookie("lastSnsLoginType", "faceb", expdate);
	    	setFacebookLogin();
	     /*console.log('Welcome!  Fetching your information.... ');
	     FB.api('/me', function(response) {
	       console.log('Good to see you, ' + response.name + '.');
	     });*/
	    } else {
	     //console.log('FaceBook 로그인 취소');
	    }
	}, 
	{scope: 'email,public_profile'}
	//{scope: 'email'}
	);
}

function facebookFeed(){	
	FB.ui(
		{
			method: 'feed',
			name: $('#postTitle').val(),
			link: $('#snsLinkUrl').val(),
			picture: $('#postImage').val(),
			caption: '', 
			description: ''
		}, function(response) {
			//console.log(response);
			if(response == null){
				//alert("취소되었습니다.");
			} else {
				if(response.post_id != undefined) {
					//alert("등록되었습니다.");
				} else {
					//alert("취소되었습니다.");
				}	
			}
		});	
}