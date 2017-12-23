// JavaScript Document
$(window).on("scroll", function(){
	var h=$(window).scrollTop();

	//서브메뉴(snb) 스크롤시 고정
	if(h > 342){
		$(".snb").addClass("fixed");
	}else{
		$(".snb").removeClass("fixed");
	}
});

$(function(){

	//gnb메뉴 슬라이딩
	var viewSize=$(".wrapper").width();

	if(viewSize > 1199){
		$(".gnbWrap").on("mouseover focusin", function(){
			$(this).stop().animate({height:"447px"}, 300);
			$(this).css("border-bottom","1px solid #ccc");
		});
		$(".gnbWrap").on("mouseleave focusout", function(){
			$(this).stop().animate({height:"98px"}, 300);
			$(this).css("border-bottom","none");
		});

		//행사안내 롤링
		var scheduleList = $('.scheduleList ul').bxSlider({
		  auto: true,
		  speed: 6000,
		  controls: false,
		  pager: false,
		  moveSlides: 1,
		  minSlides: 1,
		  maxSlides: 1,   // 슬라이드되는 이미지 갯수
		  slideWidth: 900,		// 슬라이드되는 이미지 너비
		  slideMargin:0,		// 슬라이드되는 이미지 사이 여백
		  autoHover: true
		});

	}else{

		$(".btnGnb a").on("click focusin", function(e){
			e.preventDefault();
			$(".mobileWrap").slideDown("slow");
		});
		$(".btnGnb_close>a").on("click focusin", function(e){
			e.preventDefault();
			$(".mobileWrap").css("display","none");
		});

		$(".gnbMain_m:not(:last-child)").click(function(e){
			e.preventDefault();
			$(this).next(".gnbSub_m").slideToggle();
		});

		//행사안내 롤링
		var scheduleList = $('.scheduleList ul').bxSlider({
		  auto: true,
		  speed: 3000,
		  controls: false,
		  pager: false,
		  moveSlides: 1,
		  minSlides: 1,
		  maxSlides: 1,   // 슬라이드되는 이미지 갯수
		  slideWidth: 480,		// 슬라이드되는 이미지 너비
		  slideMargin:0,		// 슬라이드되는 이미지 사이 여백
		  autoHover: true
		});

		//모바일에서 글쓰기 테이블 변경
		$(".tbl_write_pc").hide();
		$(".tbl_write_mobile").show();
	}

	// 바로가기 이미지 롤오버
	$(".goLink a").mouseover(function(){
		var Img = $(this).find("img").attr("src").replace("_off.","_on.");
		$(this).find("img").attr("src", Img);
	});
	$(".goLink a").mouseleave(function(){
		var Img = $(this).find("img").attr("src").replace("_on.","_off.");
		$(this).find("img").attr("src", Img);
	});

	//메인 비주얼 롤링
	var visualList = $('.visualListArea ul').bxSlider({
	  auto: true,
	  controls: true,
	  pager: true,
	  moveSlides: 1,
	  minSlides: 5,
	  maxSlides: 5,
	  slideWidth: 298,
	  slideMargin:50,
	  autoHover: true,
		onSlideAfter:function($slideElement, oldIndex, newIndex) {
			$(".visualListArea ul li.l" + oldIndex).removeClass("on");
			$(".visualListArea ul li.l" + newIndex).addClass("on");
		},
	  infiniteLoop: true
	});


	$(".visualListArea .visual").each(function(j){
		var visualBg=$(".visualListArea .visual").eq(j).attr("data-bg");
		$(".visualListArea .visual").eq(j).css("background-image","url("+visualBg+")");
		$(".visualListArea .visual").eq(j).addClass("on");
	});

	//메인비주얼 롤링시 가운데 배경이미지 변경
	var count=7;
	var total=$(".visualListArea li").length;

	//$(".visualListArea li").eq(count).find(".visual").addClass("on");
	//$(".visualListArea li").eq(count).find(".visual").css('background-image','url(/assets/forum/images/main/bg_visualList_03.jpg)');

	//var interval=setInterval(slider, 4000);

	function slider(){
		if(count<total){
			count++;
		}else{
			count=7;
		}

		$(".visualListArea .visual").each(function(){
			if($(this).hasClass("on")){
				$(this).removeClass("on");
				$(this).css("background-image","url('/assets/forum/images/main/bg_visual_list.png')");
			}
		});
		$(".visualListArea li").eq(count).find(".visual").addClass("on");
		visualBg=$(".visualListArea li").eq(count).find(".visual").attr("data-bg");
		$(".visualListArea li").eq(count).find(".visual").css("background-image","url("+visualBg+")");

	}

	$(".visualListArea li").hover(function(){
		//clearInterval(interval);
	},
	function(){
		//interval=setInterval(slider, 4000);
	});

	//관련사이트 롤링
	var refSite = $('.refList ul').bxSlider({
	  auto: true,
	  controls: false,
	  pager: false,
	  moveSlides: 1,
	  minSlides: 2,
	  maxSlides: 5,   // 슬라이드되는 이미지 갯수
	  slideWidth: 200,		// 슬라이드되는 이미지 너비
	  slideMargin:0,		// 슬라이드되는 이미지 사이 여백
	  autoHover: true
	});

	//비주얼 컨트롤버튼
	$(".visualWrap .controller .prev").on("click focusin", function(){
		visualList.goToPrevSlide();
	});
	$(".visualWrap .controller .stop").on("click focusin", function(){
		if ($(this).hasClass('play')){
			visualList.startAuto();
			$(this).removeClass('play');
		}else{
			visualList.stopAuto();
			$(this).addClass('play');
		}
	});
	$(".visualWrap .controller .next").on("click focusin", function(){
		visualList.goToNextSlide();
	});

	//행사안내 컨트롤버튼
	$(".schedule .controller .prev").on("click focusin", function(){
		scheduleList.goToPrevSlide();
	});
	$(".schedule .controller .stop").on("click focusin", function(){
		if ($(this).hasClass('play')){
			scheduleList.startAuto();
			$(this).removeClass('play');
		}else{
			scheduleList.stopAuto();
			$(this).addClass('play');
		}
	});
	$(".schedule .controller .next").on("click focusin", function(){
		scheduleList.goToNextSlide();
	});

	//관련사이트 컨트롤버튼
	$(".refSite .controller .prev").on("click focusin", function(){
		refSite.goToPrevSlide();
	});
	$(".refSite .controller .stop").on("click focusin", function(){
		if ($(this).hasClass('play')){
			refSite.startAuto();
			$(this).removeClass('play');
		}else{
			refSite.stopAuto();
			$(this).addClass('play');
		}
	});
	$(".refSite .controller .next").on("click focusin", function(){
		refSite.goToNextSlide();
	});

	// 영월 박물관 리스트 보기
	$(".listMusium p").on("click focusin", function(){
		$(".listMusium ul").toggleClass("show") ;
	})

	// 유관기관 바로가기 보기
	$(".listOrgan p").on("click focusin", function(){
		$(".listOrgan ul").toggleClass("show");
	})

	// 탭메뉴
	$.QueryString = (function(a) {
		
		if (a == "") return {};
		var b = {};
		
		a = a[0].split('&');
		//alert(a.length);
		
		if(a.length == 1){
			for (var i = 0; i < a.length; ++i)
			{
				var p=a[i].split('=');
				
				if (p.length != 2) continue;
				b[p[0]] = decodeURIComponent(p[1].replace(/\+/g, " "));			
				
			}
			
			return b;			
		}else if(a.length == 2){
			for (var i = 0; i < a.length; ++i)
			{
				var p=a[i].split('=');
				
				if (p.length != 2) continue;
				b[p[0]] = decodeURIComponent(p[1].replace(/\+/g, " "));			
				//alert(decodeURIComponent(p[1].replace(/\+/g, " ")));
				
			}
			
			return b;
		}		
		
	})(window.location.search.substr(1).split('?'))
	
	var num = $.QueryString["num"];
	
	if (num == null){
		num = 0;
	};	

	var tabBtn = $('.tab_title li > a')
	var tabCon = $('.tab_content')

	tabBtn.each(function(j){
		//alert('a');
		if( tabBtn.eq(j).hasClass('on') ){
			num = j;
		}
	});

	tabCon.hide();
	tabCon.eq(num).show();
	tabBtn.removeClass('on');
	tabBtn.eq(num).addClass('on');

	tabBtn.click(function(){
		var num = tabBtn.index(this);
		tabCon.each(function(i){
			if(num == i){
				tabCon.hide();
				tabCon.eq(i).show();
				tabBtn.removeClass('on');
				tabBtn.eq(i).addClass('on');
				num = i;
			};
		});
	});

	var faqRoll = function(){
		var q = $('.question').find('a');
		$(q).click(function () {
			if ($(this).parent().next().css('display') == 'none'){
			$(this).parent().next().show(400)
			} else if ($(this).parent().css('display') == 'block'){
			$(this).parent().next().hide(400)
			}
		})
	}
	faqRoll()
});
