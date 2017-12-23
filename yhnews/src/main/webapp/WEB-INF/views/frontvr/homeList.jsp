<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/comm/taglib.jsp"%>

<!DOCTYPE HTML>
<html lang="ko">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge, chrome=1">
<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0, user-scalable=no, target-densitydpi=medium-dpi"/>
<title>평창 VR360</title>
<link rel="shortcut icon" href="http://img.yonhapnews.co.kr/basic/svc/favicon.ico">
<link rel="stylesheet" href="/assets/vrFront/css/font.css">
<link rel="stylesheet" href="/assets/vrFront/css/default.css">
<link rel="stylesheet" href="/assets/vrFront/css/layout.css">
<link rel="stylesheet" href="/assets/vrFront/css/vr.css">
<link rel="stylesheet" href="/assets/vrFront/css/slick.css"><!-- 1206 추가 -->
<script src="/assets/vrFront/js/jquery-1.12.4.js"></script>
<script src="/assets/vrFront/js/modernizr.custom.js"></script>
<script src="/assets/vrFront/js/slick.min.js" charset="utf-8"></script><!-- 1206 추가 -->
</head>

<body class="page-home" data-nav="home">
<div class="wrap">
		<div id="skip-nav">
		<a href="http://www.yonhapnews.co.kr/">연합뉴스</a>
		<a href="#container">본문 바로가기</a>
		<a href="#nav">메뉴 바로가기</a>
	</div>
	<!-- header -->
	<header id="header" class="header header-type01">
				<div class="wrap-header-link">
			<ul class="header-out-link">
				<li class="link-yna"><a href="//www.yonhapnews.co.kr/">연합뉴스 홈페이지</a></li>
				<li class="link-home"><a href="//www.yonhapnews.co.kr/pyeongchang2018/index.html">평창동계올림픽 주관통신사 연합뉴스, 2018 평창동계올림픽</a></li>
			</ul>
		</div>
		<div class="wrap-header-option">
			<!--
			<div class="lang-menu-wrap">
				<div id="langMenu" class="lang-menu">
					<button class="txt"><span>Language</span><i></i></button>
					<ul>
						<li class="ko"><a href="" title="Korean">한국어</a></li>
						<li class="en"><a href="" title="English">English</a></li>
						<li class="zh cg"><a href="">中文简体</a></li>
						<li class="ja"><a href="" title="Japanese">日本語</a></li>
						<li class="ar"><a href="" title="Arabic">عربي</a></li>
						<li class="es"><a href="" title="Spanish">Español</a></li>
						<li class="fre"><a href="" title="French">Français</a></li>
					</ul>
				</div>
			</div>-->
			<button type="button" class="btn-srch-open on"><span>단어 검색</span></button>
			<button type="button" class="btn-srch-close"><span>검색 종료</span></button>
			<button type="button" class="btn-all-menu"><span class="txt">전체 열기</span><i class="line01"></i><i class="line02"></i><i class="line03"></i></button>
		</div>
		<menu class="all-menu close">
			<div id="menuScroll" class="menu-scroll">
				<div class="scroller">
					<div class="top">
						<strong class="nav-home"><a href="/front/vr/homevr/mainList.do">홈</a></strong>
					</div>
					<div class="menu">
						<div>
							<ul class="list01">
								<li class="nav-vr"><a href="/front/vr/360vr/mainList.do">평창 VR360</a></li>
								<li class="nav-popular"><a href="/front/vr/bestvr/mainList.do">인기 VR 컨텐츠</a></li>
								<li class="nav-latest"><a href="/front/vr/livevr/mainList.do">실시간 VR 컨텐츠</a></li>
                            </ul>
						</div>
						<div>
							<ul class="list01">
								<li class="nav-about"><a href="/assets/vrFront/html/about.html">서비스 소개</a></li>
								<li class="nav-notice"><a href="/assets/vrFront/html/notice.html">공지사항</a></li>
								<li class="nav-faq"><a href="/assets/vrFront/html/faq.html">자주묻는 질문</a></li>
							</ul>
						</div>
						<div>
							<!-- 로그인 후 -->
							<div>
								<strong class="tit01">마이페이지</strong>
								<ul class="list01">
									<li class="nav-mycontents"><a href="mypage_contents.html">컨텐츠 관리</a></li>
									<li class="nav-logout"><a href="#none">로그아웃</a></li>
								</ul>
							</div>
							<!-- 로그인 전
							<div>
								<strong class="tit01">회원서비스</strong>
								<ul class="list01">
									<li class="nav-login"><a href="login.html">SNS 로그인</a></li>
								</ul>
							</div> -->
						</div>
					</div>
					<div class="bottom">
						<div><!--
							<strong>Language <i>:</i></strong>
							<ul class="list02 lang-list">
								<li class="en"><a href="" title="English">English</a></li>
								<li class="zh cg"><a href="">中文简体</a></li>
								<li class="zh cb"><a href="" title="Chines(CB)">中文繁体</a></li>
								<li class="ja"><a href="" title="Japanese">日本語</a></li>
								<li class="ar"><a href="" title="Arabic">عربي</a></li>
								<li class="es"><a href="" title="Spanish">Español</a></li>
								<li class="fre"><a href="" title="French">Français</a></li>
							</ul>-->
							<p class="copy-w">Copyrights Yonhap News Agency. All Rights Reserved.</p>
							<p class="copy-m">(C) Yonhapnews</p>
						</div>
					</div>
				</div>
			</div>
		</menu>
		<div id="search" class="search-area close">
			<form action="//www.yonhapnews.co.kr/home09/7091000000.html?query=" id="totalSearchForm" onsubmit="return searchClick.search();">
				<fieldset>
					<legend class="blind">검색 영역</legend>
					<div class="area">
						<label for="srchForm">검색어를 입력하세요</label>
						<input type="text" id="srchForm" name="query" class="text" style="IME-MODE:active">
						<button type="submit" id="btnSrch" class="btn-srch"><span>검색</span></button>
					</div>
				</fieldset>
			</form>
		</div>
	</header>
	<!-- //header -->

	<!-- container -->
	<div id="container" class="container">

		<section class=" home_visual">

			<div class="slider-for">
				<div class="visual_div" style="background-image:url(/assets/vrFront/images/visual_1.png);">
					<div class="visual_des">
						<img src="/assets/vrFront/images/ico_std_1.png" alt="">
						<h1>알펜시아 바이애슬론 센터</h1>
						<p>위치 : 강원도 평창군 대관령면 솔봉로 325<br>경기 : 바이애슬론</p>
						<a href="#">VR 보기</a>
					</div>
				</div>
				<div class="visual_div" style="background-image:url(/assets/vrFront/images/visual_1.png);">
					<div class="visual_des">
						<img src="/assets/vrFront/images/ico_std_2.png" alt="">
						<h1>알펜시아 크로스컨트리 센터</h1>
						<p>위치 : 강원도 평창군 대관령면 솔봉로 325<br>경기 : 크로스컨트리, 스키노르딕 복합</p>
						<a href="#">VR 보기</a>
					</div>
				</div>
				<div class="visual_div" style="background-image:url(/assets/vrFront/images/visual_1.png);">
					<div class="visual_des">
						<img src="/assets/vrFront/images/ico_std_3.png" alt="">
						<h1>알펜시아 스키점프 센터</h1>
						<p>위치 : 강원도 평창군 대관령면 솔봉로 325<br>경기 : 노르딕 복합, 스키점프, 스노보드</p>
						<a href="#">VR 보기</a>
					</div>
				</div>
				<div class="visual_div" style="background-image:url(/assets/vrFront/images/visual_1.png);">
					<div class="visual_des">
						<img src="/assets/vrFront/images/ico_std_4.png" alt="">
						<h1>올림픽 슬라이딩 센터</h1>
						<p>위치 : 강원도 평창군 대관령면 솔봉로 325<br>경기 : 봅슬레이, 루지, 스켈레톤</p>
						<a href="#">VR 보기</a>
					</div>
				</div>
				<div class="visual_div" style="background-image:url(/assets/vrFront/images/visual_1.png);">
					<div class="visual_des">
						<img src="/assets/vrFront/images/ico_std_5.png" alt="">
						<h1>휘닉스 스노 경기장</h1>
						<p>위치 : 강원도 평창군 대관령면 솔봉로 325<br>경기 : 프리스타일 스키, 스노보드</p>
						<a href="#">VR 보기</a>
					</div>
				</div>
				<div class="visual_div" style="background-image:url(/assets/vrFront/images/visual_1.png);">
					<div class="visual_des">
						<img src="/assets/vrFront/images/ico_std_6.png" alt="">
						<h1>정선 알파인 경기장</h1>
						<p>위치 : 강원도 평창군 대관령면 솔봉로 325<br>경기 : 알파인 스키</p>
						<a href="#">VR 보기</a>
					</div>
				</div>
				<div class="visual_div" style="background-image:url(/assets/vrFront/images/visual_1.png);">
					<div class="visual_des">
						<img src="/assets/vrFront/images/ico_std_7.png" alt="">
						<h1>용평 알파인 경기장</h1>
						<p>위치 : 강원도 평창군 대관령면 솔봉로 325<br>경기 : 알파인 스키</p>
						<a href="#">VR 보기</a>
					</div>
				</div>
				<div class="visual_div" style="background-image:url(/assets/vrFront/images/visual_1.png);">
					<div class="visual_des">
						<img src="/assets/vrFront/images/ico_std_8.png" alt="">
						<h1>관동 하키 센터</h1>
						<p>위치 : 강원도 평창군 대관령면 솔봉로 325<br>경기 : 아이스 하키</p>
						<a href="#">VR 보기</a>
					</div>
				</div>
				<div class="visual_div" style="background-image:url(/assets/vrFront/images/visual_1.png);">
					<div class="visual_des">
						<img src="/assets/vrFront/images/ico_std_9.png" alt="">
						<h1>강릉 컬링 센터</h1>
						<p>위치 : 강원도 평창군 대관령면 솔봉로 325<br>경기 : 컬링</p>
						<a href="#">VR 보기</a>
					</div>
				</div>
				<div class="visual_div" style="background-image:url(/assets/vrFront/images/visual_1.png);">
					<div class="visual_des">
						<img src="/assets/vrFront/images/ico_std_10.png" alt="">
						<h1>강릉 하키 센터</h1>
						<p>위치 : 강원도 평창군 대관령면 솔봉로 325<br>경기 : 아이스 하키</p>
						<a href="#">VR 보기</a>
					</div>
				</div>
				<div class="visual_div" style="background-image:url(/assets/vrFront/images/visual_1.png);">
					<div class="visual_des">
						<img src="/assets/vrFront/images/ico_std_11.png" alt="">
						<h1>강릉 아이스 아레나</h1>
						<p>위치 : 강원도 평창군 대관령면 솔봉로 325<br>경기 : 피겨 스케이팅, 쇼트트랙, 스피드 스케이팅</p>
						<a href="#">VR 보기</a>
					</div>
				</div>
				<div class="visual_div" style="background-image:url(/assets/vrFront/images/visual_1.png);">
					<div class="visual_des">
						<img src="/assets/vrFront/images/ico_std_12.png" alt="">
						<h1>강릉 스피드 스케이팅 경기장</h1>
						<p>위치 : 강원도 평창군 대관령면 솔봉로 325<br>경기 : 스피드 스케이팅</p>
						<a href="#">VR 보기</a>
					</div>
				</div>
			</div>
			<div class="slider-nav">
				<div class="nav_div">
					<img src="/assets/vrFront/images/thumb_01.jpg" alt="" class="visual_img">
					<div class="nav_over"><p><span>알펜시아 바이애슬론 센터</span></p></div>
				</div>
				<div class="nav_div">
					<img src="/assets/vrFront/images/thumb_02.jpg" alt="" class="visual_img">
					<div class="nav_over"><p><span>알펜시아 크로스컨트리 센터</span></p></div>
				</div>
				<div class="nav_div">
					<img src="/assets/vrFront/images/thumb_03.jpg" alt="" class="visual_img">
					<div class="nav_over"><p><span>알펜시아 스키점프 센터</span></p></div>
				</div>
				<div class="nav_div">
					<img src="/assets/vrFront/images/thumb_04.jpg" alt="" class="visual_img">
					<div class="nav_over"><p><span>올림픽 슬라이딩 센터</span></p></div>
				</div>
				<div class="nav_div">
					<img src="/assets/vrFront/images/thumb_05.jpg" alt="" class="visual_img">
					<div class="nav_over"><p><span>휘닉스 스노 경기장</span></p></div>
				</div>
				<div class="nav_div">
					<img src="/assets/vrFront/images/thumb_06.jpg" alt="" class="visual_img">
					<div class="nav_over"><p><span>정선 알파인 경기장</span></p></div>
				</div>
				<div class="nav_div">
					<img src="/assets/vrFront/images/thumb_07.jpg" alt="" class="visual_img">
					<div class="nav_over"><p><span>용평 알파인 경기장</span></p></div>
				</div>
				<div class="nav_div">
					<img src="/assets/vrFront/images/thumb_08.jpg" alt="" class="visual_img">
					<div class="nav_over"><p><span>관동 하키 센터</span></p></div>
				</div>
				<div class="nav_div">
					<img src="/assets/vrFront/images/thumb_09.jpg" alt="" class="visual_img">
					<div class="nav_over"><p><span>강릉 컬링 센터</span></p></div>
				</div>
				<div class="nav_div">
					<img src="/assets/vrFront/images/thumb_10.jpg" alt="" class="visual_img">
					<div class="nav_over"><p><span>강릉 하키 센터</span></p></div>
				</div>
				<div class="nav_div">
					<img src="/assets/vrFront/images/thumb_11.jpg" alt="" class="visual_img">
					<div class="nav_over"><p><span>강릉 아이스 아레나</span></p></div>
				</div>
				<div class="nav_div">
					<img src="/assets/vrFront/images/thumb_12.jpg" alt="" class="visual_img">
					<div class="nav_over"><p><span>강릉 스피드 스케이팅 경기장</span></p></div>
				</div>
			</div>
			<div class="slider_btn">
				<button type="button" class="slick-arrow slick-prev"></button>
				<button type="button" class="slick-arrow slick-next"></button>
			</div>
			<script type="text/javascript">
				$('.slider-for').slick({
					slidesToShow: 1,
					slidesToScroll: 1,
					arrows: false,
					dots: false,
					fade: true,
					autoplaySpeed: 4000,
					speed: 1000,
					cssEase: 'linear',
					asNavFor: '.slider-nav'
				});
				$('.slider-nav').slick({
					slidesToShow: 7,
					slidesToScroll: 1,
					asNavFor: '.slider-for',
					speed: 600,
					arrows: false,
					dots: false,
					infinite: true,
					autoplaySpeed: 4000,
					speed: 1000,
					centerMode: true,
					touchMove:false,
					focusOnSelect: true,
					responsive:[
						{
						  breakpoint: 1200,
						  settings: {
						  slidesToShow: 5,
						  slidesToScroll: 1}
						},
						{
						  breakpoint: 768,
						  settings: {
						  slidesToShow: 1,
						  slidesToScroll: 1}
						}
					]
				});
				// slider btn
				$('.slick-prev').on('click', function(){
					   $(".slider-for").slick('slickPrev')
				});
				$('.slick-next').on('click', function(){
					   $(".slider-for").slick('slickNext');
				});
			</script>
		</section><!-- //home_visual -->

		<section class="section-vr">
		<!-- ========================== 내용 시작 ========================= -->



		<div class="home_section section_popular">

			<div class="home_listbox">
				<div class="title_wrap"><h1>인기 VR</h1><p>등록된 VR 중 인기있는 영상모음입니다.</p></div>
				<div class="vr_list vrlist_type01">

					<div class="item">
						<div class="item_wrap">
							<div class="thumb">
								<img src="/assets/vrFront/images/thumb_01.jpg" alt="사진" class="def">
								<div class="dark"></div>
							</div>
							<span class="mark" style="background-image:url(/assets/vrFront/images/ico_std_1.png);">알펜시아 바이애슬론 센터</span>
							<h4 class="tit_con">알펜시아 바이애슬론 센터 입구</h4>
							<div class="author">
								<p class="author_writer"><span class="name">홍길동</span><span class="date">2017-10-24</span></p>
								<p class="author_good"><a href="#" class="ico ico_heart">좋아요</a> <span class="good_num">244</span></p>
							</div>
							<div class="btn_wrap">
								<a href="contents_view.html"><span class="ico ico_view"></span><br>상세보기</a>
								<a href="#"><span class="ico ico_vr"></span><br>VR보기</a>
							</div>
						</div>
					</div><!-- //item -->

					<div class="item">
						<div class="item_wrap">
							<div class="thumb">
								<img src="/assets/vrFront/images/thumb_01.jpg" alt="사진" class="def">
								<div class="dark"></div>
							</div>
							<span class="mark" style="background-image:url(/assets/vrFront/images/ico_std_1.png);">알펜시아 바이애슬론 센터</span>
							<h4 class="tit_con">알펜시아 바이애슬론 센터 입구</h4>
							<div class="author">
								<p class="author_writer"><span class="name">홍길동</span><span class="date">2017-10-24</span></p>
								<p class="author_good"><a href="#" class="ico ico_heart">좋아요</a> <span class="good_num">244</span></p>
							</div>
							<div class="btn_wrap">
								<a href="contents_view.html"><span class="ico ico_view"></span><br>상세보기</a>
								<a href="#"><span class="ico ico_vr"></span><br>VR보기</a>
							</div>
						</div>
					</div><!-- //item -->

					<div class="item">
						<div class="item_wrap">
							<div class="thumb">
								<img src="/assets/vrFront/images/thumb_01.jpg" alt="사진" class="def">
								<div class="dark"></div>
							</div>
							<span class="mark" style="background-image:url(/assets/vrFront/images/ico_std_1.png);">알펜시아 바이애슬론 센터</span>
							<h4 class="tit_con">알펜시아 바이애슬론 센터 입구</h4>
							<div class="author">
								<p class="author_writer"><span class="name">홍길동</span><span class="date">2017-10-24</span></p>
								<p class="author_good"><a href="#" class="ico ico_heart">좋아요</a> <span class="good_num">244</span></p>
							</div>
							<div class="btn_wrap">
								<a href="contents_view.html"><span class="ico ico_view"></span><br>상세보기</a>
								<a href="#"><span class="ico ico_vr"></span><br>VR보기</a>
							</div>
						</div>
					</div><!-- //item -->

					<div class="item">
						<div class="item_wrap">
							<div class="thumb">
								<img src="/assets/vrFront/images/thumb_01.jpg" alt="사진" class="def">
								<div class="dark"></div>
							</div>
							<span class="mark" style="background-image:url(/assets/vrFront/images/ico_std_1.png);">알펜시아 바이애슬론 센터</span>
							<h4 class="tit_con">알펜시아 바이애슬론 센터 입구</h4>
							<div class="author">
								<p class="author_writer"><span class="name">홍길동</span><span class="date">2017-10-24</span></p>
								<p class="author_good"><a href="#" class="ico ico_heart">좋아요</a> <span class="good_num">244</span></p>
							</div>
							<div class="btn_wrap">
								<a href="contents_view.html"><span class="ico ico_view"></span><br>상세보기</a>
								<a href="#"><span class="ico ico_vr"></span><br>VR보기</a>
							</div>
						</div>
					</div><!-- //item -->

					<div class="item">
						<div class="item_wrap">
							<div class="thumb">
								<img src="/assets/vrFront/images/thumb_01.jpg" alt="사진" class="def">
								<div class="dark"></div>
							</div>
							<span class="mark" style="background-image:url(/assets/vrFront/images/ico_std_1.png);">알펜시아 바이애슬론 센터</span>
							<h4 class="tit_con">알펜시아 바이애슬론 센터 입구</h4>
							<div class="author">
								<p class="author_writer"><span class="name">홍길동</span><span class="date">2017-10-24</span></p>
								<p class="author_good"><a href="#" class="ico ico_heart">좋아요</a> <span class="good_num">244</span></p>
							</div>
							<div class="btn_wrap">
								<a href="contents_view.html"><span class="ico ico_view"></span><br>상세보기</a>
								<a href="#"><span class="ico ico_vr"></span><br>VR보기</a>
							</div>
						</div>
					</div><!-- //item -->

					<div class="item">
						<div class="item_wrap">
							<div class="thumb">
								<img src="/assets/vrFront/images/thumb_01.jpg" alt="사진" class="def">
								<div class="dark"></div>
							</div>
							<span class="mark" style="background-image:url(/assets/vrFront/images/ico_std_1.png);">알펜시아 바이애슬론 센터</span>
							<h4 class="tit_con">알펜시아 바이애슬론 센터 입구</h4>
							<div class="author">
								<p class="author_good"><a href="#" class="ico ico_heart">좋아요</a> <span class="good_num">244</span></p>
								<p class="author_writer"><span class="name">홍길동</span><span class="date">2017-10-24</span></p>
							</div>
							<div class="btn_wrap">
								<a href="contents_view.html"><span class="ico ico_view"></span><br>상세보기</a>
								<a href="#"><span class="ico ico_vr"></span><br>VR보기</a>
							</div>
						</div>
					</div><!-- //item -->

				</div><!-- //vr-list -->
			</div><!-- //home_listbox -->
		</div><!-- //home_section -->

		<div class="home_section section_latest">

			<div class="home_listbox">
				<div class="title_wrap"><h1>실시간 VR</h1><p>최근 등록된 영상모음입니다.</p></div>
				<div class="vr_list vrlist_type01">

					<div class="item">
						<div class="item_wrap">
							<div class="thumb">
								<img src="/assets/vrFront/images/thumb_01.jpg" alt="사진" class="def">
								<div class="dark"></div>
							</div>
							<span class="mark" style="background-image:url(/assets/vrFront/images/ico_std_1.png);">알펜시아 바이애슬론 센터</span>
							<h4 class="tit_con">알펜시아 바이애슬론 센터 입구</h4>
							<div class="author">
								<p class="author_writer"><span class="name">홍길동</span><span class="date">2017-10-24</span></p>
								<p class="author_good"><a href="#" class="ico ico_heart">좋아요</a> <span class="good_num">244</span></p>
							</div>
							<div class="btn_wrap">
								<a href="contents_view.html"><span class="ico ico_view"></span><br>상세보기</a>
								<a href="#"><span class="ico ico_vr"></span><br>VR보기</a>
							</div>
						</div>
					</div><!-- //item -->

					<div class="item">
						<div class="item_wrap">
							<div class="thumb">
								<img src="/assets/vrFront/images/thumb_01.jpg" alt="사진" class="def">
								<div class="dark"></div>
							</div>
							<span class="mark" style="background-image:url(/assets/vrFront/images/ico_std_1.png);">알펜시아 바이애슬론 센터</span>
							<h4 class="tit_con">알펜시아 바이애슬론 센터 입구</h4>
							<div class="author">
								<p class="author_writer"><span class="name">홍길동</span><span class="date">2017-10-24</span></p>
								<p class="author_good"><a href="#" class="ico ico_heart">좋아요</a> <span class="good_num">244</span></p>
							</div>
							<div class="btn_wrap">
								<a href="contents_view.html"><span class="ico ico_view"></span><br>상세보기</a>
								<a href="#"><span class="ico ico_vr"></span><br>VR보기</a>
							</div>
						</div>
					</div><!-- //item -->

					<div class="item">
						<div class="item_wrap">
							<div class="thumb">
								<img src="/assets/vrFront/images/thumb_01.jpg" alt="사진" class="def">
								<div class="dark"></div>
							</div>
							<span class="mark" style="background-image:url(/assets/vrFront/images/ico_std_1.png);">알펜시아 바이애슬론 센터</span>
							<h4 class="tit_con">알펜시아 바이애슬론 센터 입구</h4>
							<div class="author">
								<p class="author_writer"><span class="name">홍길동</span><span class="date">2017-10-24</span></p>
								<p class="author_good"><a href="#" class="ico ico_heart">좋아요</a> <span class="good_num">244</span></p>
							</div>
							<div class="btn_wrap">
								<a href="contents_view.html"><span class="ico ico_view"></span><br>상세보기</a>
								<a href="#"><span class="ico ico_vr"></span><br>VR보기</a>
							</div>
						</div>
					</div><!-- //item -->

					<div class="item">
						<div class="item_wrap">
							<div class="thumb">
								<img src="/assets/vrFront/images/thumb_01.jpg" alt="사진" class="def">
								<div class="dark"></div>
							</div>
							<span class="mark" style="background-image:url(/assets/vrFront/images/ico_std_1.png);">알펜시아 바이애슬론 센터</span>
							<h4 class="tit_con">알펜시아 바이애슬론 센터 입구</h4>
							<div class="author">
								<p class="author_writer"><span class="name">홍길동</span><span class="date">2017-10-24</span></p>
								<p class="author_good"><a href="#" class="ico ico_heart">좋아요</a> <span class="good_num">244</span></p>
							</div>
							<div class="btn_wrap">
								<a href="contents_view.html"><span class="ico ico_view"></span><br>상세보기</a>
								<a href="#"><span class="ico ico_vr"></span><br>VR보기</a>
							</div>
						</div>
					</div><!-- //item -->

					<div class="item">
						<div class="item_wrap">
							<div class="thumb">
								<img src="/assets/vrFront/images/thumb_01.jpg" alt="사진" class="def">
								<div class="dark"></div>
							</div>
							<span class="mark" style="background-image:url(/assets/vrFront/images/ico_std_1.png);">알펜시아 바이애슬론 센터</span>
							<h4 class="tit_con">알펜시아 바이애슬론 센터 입구</h4>
							<div class="author">
								<p class="author_writer"><span class="name">홍길동</span><span class="date">2017-10-24</span></p>
								<p class="author_good"><a href="#" class="ico ico_heart">좋아요</a> <span class="good_num">244</span></p>
							</div>
							<div class="btn_wrap">
								<a href="contents_view.html"><span class="ico ico_view"></span><br>상세보기</a>
								<a href="#"><span class="ico ico_vr"></span><br>VR보기</a>
							</div>
						</div>
					</div><!-- //item -->

					<div class="item">
						<div class="item_wrap">
							<div class="thumb">
								<img src="/assets/vrFront/images/thumb_01.jpg" alt="사진" class="def">
								<div class="dark"></div>
							</div>
							<span class="mark" style="background-image:url(/assets/vrFront/images/ico_std_1.png);">알펜시아 바이애슬론 센터</span>
							<h4 class="tit_con">알펜시아 바이애슬론 센터 입구</h4>
							<div class="author">
								<p class="author_writer"><span class="name">홍길동</span><span class="date">2017-10-24</span></p>
								<p class="author_good"><a href="#" class="ico ico_heart">좋아요</a> <span class="good_num">244</span></p>
							</div>
							<div class="btn_wrap">
								<a href="contents_view.html"><span class="ico ico_view"></span><br>상세보기</a>
								<a href="#"><span class="ico ico_vr"></span><br>VR보기</a>
							</div>
						</div>
					</div><!-- //item -->

				</div><!-- //vr-list -->
			</div><!-- //home_listbox -->
		</div><!-- //home_section -->

		<div class="home_section section_stadium">
			<div class="home_listbox">
				<div class="title_wrap"><h1>경기장 VR</h1><p>경기장별로 보실 수 있는 영상입니다.</p></div>
				<div class="vr_list vrlist_type01">
                <c:forEach var="data" items="${siteResult}" varStatus="i">
                    <div class="item">
                        <div class="item_wrap">
                            <div class="thumb">
                                <img src="${CTX}/vrContents/adminContents/${data.path_image}" alt="사진" class="def">
                                <div class="dark"></div>
                            </div>
                            <span class="mark" style="background-image:url(${CTX}/vrContents/adminContents/${data.path_icon});"></span>
                            <h4 class="tit_con center">${data.title}</h4>
                            <div class="btn_wrap">
                                <a href="${CTX}/vrContents/adminContents/${data.vr_site_id}/vtour/tour.html"><span class="ico ico_vr"></span><br>VR보기</a>
                            </div>
                        </div>
                    </div><!-- //item -->
                </c:forEach>
				</div><!-- //vr-list -->
			</div><!-- //home_listbox -->
		</div><!-- //home_section -->


		<!-- ========================== 내용 종료 ========================= -->
		</section><!-- //section-vr -->
	</div>
	<!-- //container -->

	<!-- footer -->
	<div class="wrap-footer footer-type01">
		<footer id="footer" class="footer">
			<button class="btn-scroll-top"><span>SCROLL</span><i></i></button>
						<nav class="foot-nav02">
				<div class="logo-link">
					<div class="foot-out-link02">
						<span class="link-yna"><a href="//www.yonhapnews.co.kr/">연합뉴스 홈페이지</a></span>
					</div>
					<p class="txt">연합뉴스는 평창동계올림픽 주관통신사 입니다</p>
					<!--<ul class="sns-link">
						<li class="fb"><a href="https://www.facebook.com/yonhap" target="_blank" title="새창">연합뉴스 페이스북 바로가기</a></li>
						<li class="tw"><a href="https://twitter.com/yonhaptweet" target="_blank" title="새창">연합뉴스 트위터 바로가기</a></li>
						<li class="ggp"><a href="https://plus.google.com/+yonhapnews" target="_blank" title="새창">연합뉴스 구글플러스 바로가기</a></li>
					</ul>-->
				</div>
				<div>
					<ul class="list01">
						<li class="nav-vr"><a href="/frontvr/360vr/mainList.do">평창 VR360</a></li>
								<li class="nav-popular"><a href="/frontvr/bestvr/mainList.do">인기 VR 컨텐츠</a></li>
								<li class="nav-latest"><a href="/frontvr/livevr/mainList.do">실시간 VR 컨텐츠</a></li>
					</ul>
				</div>
				<div>
					<ul class="list01">
						<li class="nav-about"><a href="/assets/vrFront/html/about.html">서비스 소개</a></li>
						<li class="nav-notice"><a href="/assets/vrFront/html/notice.html">공지사항</a></li>
						<li class="nav-faq"><a href="/assets/vrFront/html/faq.html">자주묻는 질문</a></li>
					</ul>
				</div>
				<div>
					<!-- 로그인 후 -->
					<strong class="tit01">마이페이지</strong>
					<ul class="list01">
						<li class="nav-mycontents"><a href="mypage_contents.html">컨텐츠 관리</a></li>
						<li class="nav-logout"><a href="#none">로그아웃</a></li>
					</ul>
					<!-- 로그인 전
					<strong class="tit01">회원서비스</strong>
					<ul class="list01">
						<li class="nav-login"><a href="login.html">SNS 로그인</a></li>
					</ul> -->
				</div>
			</nav>
			<div class="foot-link-wrap">
				<ul class="foot-link">
					<li><a href="//www.yonhapnews.co.kr/aboutus/index.html">회사소개</a></li>
					<li><a href="//www.yonhapnews.co.kr/policy/7902000000.html">저작권규약</a></li>
					<li><a href="//app.yonhapnews.co.kr/bbs/user/list.jsp">수용자권익위원회</a></li>
					<li><a href="//www.yonhapnews.co.kr/policy/7904000000.html">고충처리</a></li>
					<li><a href="//www.yonhapnews.co.kr/policy/7906410000.html">이용약관</a></li>
					<li><a href="//www.yonhapnews.co.kr/policy/7906010000.html"><strong>개인정보처리방침</strong></a></li>
					<li><a href="//app.yonhapnews.co.kr/bbs/reader/list.jsp">독자게시판</a></li>
					<li><a href="//app.yonhapnews.co.kr/YNA/Basic/ArticleOffer/YIBW_addArticleOffer2.aspx"><strong>기사제보</strong></a></li>
				</ul>
				<div class="addr-info">
					<address>연합뉴스 서울시 종로구 율곡로2길 25 | 등록번호  문화, 나00009  | 등록일자 1980.12.29  | 발행일자 1980.12.29 | Tel. 02-398-3114</address>
				</div>
				<ul class="copy">
					<li><strong>(C) Yonhapnews</strong></li>
					<li class="copy-in">[대표이사] 박노황</li>
					<li class="copy-in">[편집인] 조복래</li>
				</ul>
			</div>
		</footer>
	</div>
	<!-- //footer -->
</div>

<script src="/assets/vrFront/js/common.js"></script>
</body>
</html>