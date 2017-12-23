/**
 * jQuery Validate (Import jquery-1.10.2.min.js) 
 * @author Lee JongHwan
 * @since 2014.10.28
 * @param formId  
 */
 
// numberOnly 숫자만 입력
jQuery(document).on("keyup", ".numberOnly", function() {jQuery(this).val( jQuery(this).val().replace(/[^0-9]/gi,"") );});
// formValidate 폼체크
function formValidate(){
	var successFlag=true;	
	var isValid;
	var name;
	var title;				// input title
	var type;				// input 타입
	var isNullCheck;		// 체크여부
	var isDisabled;
	var val;
	var equalsTarget;	// 비교대상아이디	
	var isPasswdCheck; // 비밀번호체크
	
	try {
		
		jQuery("form input, form select, form radio, form chekbox, form textarea").each(function(){
			
			obj 				= jQuery(this);
			name 			= obj.attr("name");
			id	 				= obj.attr("id");
			title 				= obj.attr("title");
			type 				= obj.prop("type");
			isNullCheck 		= obj.attr("isNullCheck");
			isEmailCheck	= obj.attr("isEmailCheck");
			isNumberCheck	= obj.attr("isNumberCheck");
			isPasswdCheck	= obj.attr("isPasswdCheck");
			equalsTarget 	= obj.attr("equalsTarget");
			isDisabled 		= obj.is(":disabled");			
			val 				= jQuery.trim(obj.val());
			successFlag 		= true;
			
			if(isNullCheck == "true" && isDisabled == false) {
				isValid = false;
				
				// radio || checkbox
				if(type == "radio" || type == "checkbox"){
					jQuery("input:"+type+"[name="+name+"]").each(function(i) {
						if(this.checked) {
							isValid = true;
							return false; //break
						}
					});
				} else {					
					if(val != undefined && val != null && val != '') {		
						isValid = true;
					}
				}
				
				// 메시지 알림				
				if(!isValid){
					if(type =="text" || type =="password" || type == "textarea"){
						alert(title + "을(를) 입력해 주시기 바랍니다.");
					}else{
						alert(title + "을(를) 선택해 주시기 바랍니다.");
					}					
					obj.focus();					
					successFlag = false;
					return false;
				}
				
				// equalsTarget 비교 값 확인				
				if(equalsTarget) {
					if(val != jQuery("#"+equalsTarget).val()){
						alert(title + "은(는) " + jQuery("#"+equalsTarget).attr("title") +"과(와) 일치하지 않습니다");
						obj.focus();
						successFlag = false;
						return false;
					}
				}
				// 이메일 형식 확인
				if(isEmailCheck){
					if(!val.match(/^(\S+)@(\S+)\.(\S+)/g)) {
						alert("올바른 이메일 형식이 아닙니다.");
						obj.focus();
						successFlag = false;
						return false;
					}
				} 
				// 숫자만입력
				if(isNumberCheck){
					if(isNaN(val)) {
						alert(title + "은(는) 숫자만 입력해 주시기 바랍니다.");
						obj.focus();
						successFlag = false;
						return false;
					}
				}
				// 영문숫자 조합 6,20
				if(isPasswdCheck){					
					var regex = /^.*(?=.{6,20})(?=.*[0-9])(?=.*[a-zA-Z]).*$/;
					if(!regex.test(val)) {
						alert(title + "은(는) 영문 숫자 조합 6자리 이상 입력하시기 바랍니다.");
						successFlag = false;
						return false;
					}
				}
			}		
		});	
		
	} catch(err) {
		alert(err);
		return successFlag;
	}	
	return successFlag;
}

// 단일 입력폼 체크
function inputValidate(id){
	var successFlag=false;	
	var isValid;
	var name;
	var title;				// input title
	var type;				// input 타입
	var isNullCheck;		// 체크여부
	var isDisabled;
	var val;
	var equalsTarget;	// 비교대상아이디	
	
	try {
		
		obj 				= jQuery('#'+id);
		name 			= obj.attr("name");
		id	 				= obj.attr("id");
		title 				= obj.attr("title");
		type 				= obj.prop("type");
		isNullCheck 		= obj.attr("isNullCheck");
		isEmailCheck	= obj.attr("isEmailCheck");
		isNumberCheck	= obj.attr("isNumberCheck");
		equalsTarget 	= obj.attr("equalsTarget");
		isDisabled 		= obj.is(":disabled");
		val 				= jQuery.trim(obj.val());
		successFlag 		= true;
		
		if(isNullCheck == "true" && isDisabled == false) {
			isValid = false;
			
			// radio || checkbox
			if(type == "radio" || type == "checkbox"){
				jQuery("input:"+type+"[name="+name+"]").each(function(i) {
					if(this.checked) {
						isValid = true;
						return false; //break
					}
				});
			} else {					
				if(val != undefined && val != null && val != '') {		
					isValid = true;
				}
			}
			
			// 메시지 알림				
			if(!isValid){
				if(type =="text" || type =="password" || type == "textarea"){
					alert(title + "을(를) 입력해 주시기 바랍니다.");
				}else{
					alert(title + "을(를) 선택해 주시기 바랍니다.");
				}					
				obj.focus();					
				successFlag = false;
				return false;
			}
			
			// equalsTarget 비교 값 확인				
			if(equalsTarget) {
				if(val != jQuery("#"+equalsTarget).val()){
					alert(title + "은(는) " + jQuery("#"+equalsTarget).attr("title") +"과(와) 일치하지 않습니다");
					obj.focus();
					successFlag = false;
					return false;
				}
			}
			// 이메일 형식 확인
			if(isEmailCheck){
				if(!val.match(/^(\S+)@(\S+)\.(\S+)/g)) {
					alert("올바른 이메일 형식이 아닙니다.");
					obj.focus();
					successFlag = false;
					return false;
				}
			} 
			// 숫자만입력
			if(isNumberCheck){
				if(isNaN(val)) {
					alert(title + "은(는) 숫자만 입력해 주시기 바랍니다.");
					obj.focus();
					successFlag = false;
					return false;
				}
			}
		}
		
	} catch(err) {
		alert(err);
		return successFlag;
	}	
	return successFlag;
}
