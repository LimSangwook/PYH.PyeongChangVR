String.prototype.isNvl = function() {	
	if(this == undefined || this == null || this == '') {		
		return false;
	}
	return true;
};

/**
 * yyyymmdd 형태로 포매팅된 날짜 반환
 * */
Date.prototype.yyyymmdd = function() {
    var yyyy = this.getFullYear().toString();
    var mm = (this.getMonth() + 1).toString();
    var dd = this.getDate().toString();
    return yyyy + (mm[1] ? mm : '0'+mm[0]) + (dd[1] ? dd : '0'+dd[0]);
};

/**
 * 영문만인지 확인
 * @param val
 * @return boolean
 * */
String.prototype.isalpha = function () {
	if (this.search(/[^A-Za-z]/) == -1)
		return true;
	else
		return false;
};

/**
 * 숫자만인지 확인
 * @param val
 * @return boolean
 * */
String.prototype.isNumber = function () {
	if (this.search(/[^0-9]/) == -1)
		return true;
	else
		return false;
};

/**
 * 아이디유효성확인
 * @param val
 * @return boolean
 * */
String.prototype.isValidId = function () {
	if (!/^[a-zA-Z0-9]{5,12}$/.test(this)) {
		alert("5~12자의 영문 또는 숫자만 사용할 수 있습니다.");
		return false;
	}
	return true;
};

/**
 * 코드유효성확인
 * @param val
 * @return boolean
 * */
String.prototype.isValidCode = function () {
	if (!/^[a-zA-Z0-9]{3,20}$/.test(this)) {
		alert("3~20자의 영문 또는 숫자만 사용할 수 있습니다.");
		return false;
	}
	return true;
};

$.nl2br = function(str){
    return str.replace(/(\r\n|\n\r|\r|\n)/g, "<br/>");
};

$.br2nl = function(str){
    return str.replace(/<br>/g, "\r");
};


/** 팝업 위치Center, ScrollBar No*/
function WindowOpenCenter(Url, popName, popwidth, popheight){
	var LeftPosition = (screen.width) ? (screen.width-popwidth)/2 : 0;
	var TopPosition = (screen.height) ? (screen.height-popheight)/2 : 0;
	var settings = 'height='+popheight+',width='+popwidth+',top='+TopPosition+',left='+LeftPosition+',status=no,toolbar=no,menubar=no,location=no,fullscreen=no,resizable=no,scrollbars=no';
	return win = window.open(Url,popName,settings);
}

/** 팝업 위치Center, ScrollBar Yes*/
function WindowOpenCenterSizable(Url, popName, popwidth, popheight){
	var LeftPosition = (screen.width) ? (screen.width-popwidth)/2 : 0;
	var TopPosition = (screen.height) ? (screen.height-popheight)/2 : 0;
	var settings = 'height='+popheight+',width='+popwidth+',top='+TopPosition+',left='+LeftPosition+',status=no,toolbar=no,menubar=no,location=no,fullscreen=no,resizable=yes,scrollbars=yes';
	return win = window.open(Url,popName,settings);
}

/** 팝업 위치Nomargin, ScrollBar No*/
function WindowOpenNomargin(Url, popName, popwidth, popheight){	
	return win = window.open(Url, popName, "width="+popwidth+",height="+popheight+",scrollbars=no,toolbar=no,left=0,top=0");
}

/** 팝업 위치Nomargin, ScrollBar Yes*/
function WindowOpenNomarginSizable(Url, popName, popwidth, popheight){	
	return win = window.open(Url, popName, "width="+popwidth+",height="+popheight+",scrollbars=yes,location=no,toolbar=no,left=0,top=0");
}

/** 팝업 위치Nomargin, ScrollBar No*/
function WindowOpenCustom(Url, popName, popwidth, popheight, popleft, poptop){	
	return win = window.open(Url, popName, "width="+popwidth+",height="+popheight+",scrollbars=no,toolbar=no,left="+popleft+",top="+poptop);
}

//팝업	u - 주소, n - 이름, w - 넓이, h - 높이, s - 스크롤여부(yes, no), r - 창크기조절여부(yes, no), m - (1:일반, 2:위쪽모서리, 3:정중앙)
function popup(u, n, w, h, s, r, m) {
    var o;
    var lP = screen.availWidth;
    var tP = screen.availHeight;
    var p  = "";
	
	if(s==undefined) s = "no";
	if(m==undefined) m = 1;
	
    if(m==2) //- 위쪽모서리
        p = ",left=0,top=0";
    else if(m==3) //- 정중앙
        p = ",left=" + ((lP - w) / 2) + ",top=" + ((tP - h) / 2);
    
    o = window.open(u,n,"status=yes,toolbar=no,location=no,scrollbars=" + s + ",resizable="+r+",width="+w+",height="+h + p);
    o.focus();
    return o;
}

/**
 * jquery 달력셋팅
 * */
function setCalendar(format){
	var $datepicker = jQuery(".inputCal");
	if($datepicker.size() > 0) {
		$datepicker.datepicker(
			{
				changeYear: true,
				dateFormat: format, //yy-mm-dd //yymmdd
				gotoCurrent: true,
				duration: 'fast',
				nextText: '다음달',
				prevText: '이전달',
				yearRange: '1950:2020',
				showOn: "button",
				dayNames: ['일요일', '월요일', '화요일', '수요일', '목요일', '금요일', '토요일'],
				dayNamesMin: ['일', '월', '화', '수', '목', '금', '토'],
				monthNames: ['1월', '2월', '3월', '4월', '5월', '6월', '7월', '8월', '9월', '10월', '11월', '12월'],
				buttonText: "달력",
				buttonImage: "/assets/admin/img/calendar.gif",
				buttonImageOnly: true
			}
		);
		jQuery(".ui-datepicker-trigger").css({"margin-left":"5px","cursor":"pointer"});
		//var $imgEl = $(".ui-datepicker-trigger").css("margin-left","6px");
		//$imgEl.addClass('fa fa-calendar');
	}
}

function setMinDateCalendar(format){
	var $datepicker = jQuery(".inputCal");
	if($datepicker.size() > 0) {
		$datepicker.datepicker(
			{
				changeYear: true,
				dateFormat: format, //yy-mm-dd //yymmdd
				gotoCurrent: true,
				duration: 'fast',
				nextText: '다음달',
				prevText: '이전달',
				yearRange: '1950:2020',
				showOn: "button",
				dayNames: ['일요일', '월요일', '화요일', '수요일', '목요일', '금요일', '토요일'],
				dayNamesMin: ['일', '월', '화', '수', '목', '금', '토'],
				monthNames: ['1월', '2월', '3월', '4월', '5월', '6월', '7월', '8월', '9월', '10월', '11월', '12월'],
				buttonText: "달력",
				buttonImage: "/assets/admin/img/calendar2.png",
				buttonImageOnly: true,
				minDate: new Date()
			}
		);
		jQuery(".ui-datepicker-trigger").css({"margin-left":"5px","cursor":"pointer"});
		//var $imgEl = $(".ui-datepicker-trigger").css("margin-left","6px");
		//$imgEl.addClass('fa fa-calendar');
	}
}

/**
 * 글자수 자르기
 * */
function fnTextCrop(obj, maxLength, tail){
	if(obj){
		obj.on('keyup',function(){
			if(obj.val().length >= maxLength){
				obj.val(obj.val().substr(0,maxLength-5)+tail);
			}
		});
	}
}

function setCookie (name, value, expires) {
	document.cookie = name + "=" + escape (value) + "; path=/; expires=" + expires.toGMTString();
}

// 쿠키 값 확인 
function getCookie(Name) {
	var search = Name + "=";
  	if (document.cookie.length > 0) { // 쿠키가 설정되어 있다면
    	offset = document.cookie.indexOf(search);
    	if (offset != -1) { // 쿠키가 존재하면
      		offset += search.length;
      		// set index of beginning of value
      		end = document.cookie.indexOf(";", offset);
      		// 쿠키 값의 마지막 위치 인덱스 번호 설정
      		if (end == -1)
        		end = document.cookie.length;
      		return unescape(document.cookie.substring(offset, end));
		}
	}
  return "";
}

// 시작일 종료일 유효성 확인
function fnCalendarStartNEndDataCheck(sid, eid, msg){
	var flag = true;
	var startDate = $( "#"+sid ).val();
    var startDateArr = startDate.split('-');
      
    var endDate = $( "#"+eid ).val();
    var endDateArr = endDate.split('-');
              
    var startDateCompare = new Date(startDateArr[0], startDateArr[1], startDateArr[2]);
    var endDateCompare = new Date(endDateArr[0], endDateArr[1], endDateArr[2]);
      
    if(startDateCompare.getTime() > endDateCompare.getTime()) {
          
        alert(msg+"는(은) 시작일 보다 종료일이 크거나 같아야 합니다.");
          
        flag = false;
    }
    return flag;
}

function fnPreparingMsg(){
	alert('준비중입니다.');return;
}
/*if( /Android|webOS|iPhone|iPad|iPod|BlackBerry/i.test(navigator.userAgent) ) {
	  
}*/