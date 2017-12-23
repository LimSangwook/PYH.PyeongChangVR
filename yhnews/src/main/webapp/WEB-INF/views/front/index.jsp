<%@page import="java.io.InputStreamReader"%>
<%@page import="java.io.BufferedReader"%>
<%@page import="java.text.DecimalFormat"%>
<%@page import="java.util.*"%>
<%@ page import="java.net.URL" %>
<%@ page import="java.net.URLConnection" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/comm/taglib.jsp"%>

	<section class=" home_visual">

		<div class="slider-for">
			<div class="visual_div" style="background-image:url(/assets/comn/img/visual_1.png);">
				<div class="visual_des">
					<img src="/assets/comn/img/ico_std_1.png" alt="">
					<h1>알펜시아 바이애슬론 센터</h1>
					<p>위치 : 강원도 평창군 대관령면 솔봉로 325<br>경기 : 바이애슬론</p>
					<a href="#">VR 보기</a>
				</div>
			</div>
			<div class="visual_div" style="background-image:url(/assets/comn/img/visual_1.png);">
				<div class="visual_des">
					<img src="/assets/comn/img/ico_std_2.png" alt="">
					<h1>알펜시아 크로스컨트리 센터</h1>
					<p>위치 : 강원도 평창군 대관령면 솔봉로 325<br>경기 : 크로스컨트리, 스키노르딕 복합</p>
					<a href="#">VR 보기</a>
				</div>
			</div>
			<div class="visual_div" style="background-image:url(/assets/comn/img/visual_1.png);">
				<div class="visual_des">
					<img src="/assets/comn/img/ico_std_3.png" alt="">
					<h1>알펜시아 스키점프 센터</h1>
					<p>위치 : 강원도 평창군 대관령면 솔봉로 325<br>경기 : 노르딕 복합, 스키점프, 스노보드</p>
					<a href="#">VR 보기</a>
				</div>
			</div>
			<div class="visual_div" style="background-image:url(/assets/comn/img/visual_1.png);">
				<div class="visual_des">
					<img src="/assets/comn/img/ico_std_4.png" alt="">
					<h1>올림픽 슬라이딩 센터</h1>
					<p>위치 : 강원도 평창군 대관령면 솔봉로 325<br>경기 : 봅슬레이, 루지, 스켈레톤</p>
					<a href="#">VR 보기</a>
				</div>
			</div>
			<div class="visual_div" style="background-image:url(/assets/comn/img/visual_1.png);">
				<div class="visual_des">
					<img src="/assets/comn/img/ico_std_5.png" alt="">
					<h1>휘닉스 스노 경기장</h1>
					<p>위치 : 강원도 평창군 대관령면 솔봉로 325<br>경기 : 프리스타일 스키, 스노보드</p>
					<a href="#">VR 보기</a>
				</div>
			</div>
			<div class="visual_div" style="background-image:url(/assets/comn/img/visual_1.png);">
				<div class="visual_des">
					<img src="/assets/comn/img/ico_std_6.png" alt="">
					<h1>정선 알파인 경기장</h1>
					<p>위치 : 강원도 평창군 대관령면 솔봉로 325<br>경기 : 알파인 스키</p>
					<a href="#">VR 보기</a>
				</div>
			</div>
			<div class="visual_div" style="background-image:url(/assets/comn/img/visual_1.png);">
				<div class="visual_des">
					<img src="/assets/comn/img/ico_std_7.png" alt="">
					<h1>용평 알파인 경기장</h1>
					<p>위치 : 강원도 평창군 대관령면 솔봉로 325<br>경기 : 알파인 스키</p>
					<a href="#">VR 보기</a>
				</div>
			</div>
			<div class="visual_div" style="background-image:url(/assets/comn/img/visual_1.png);">
				<div class="visual_des">
					<img src="/assets/comn/img/ico_std_8.png" alt="">
					<h1>관동 하키 센터</h1>
					<p>위치 : 강원도 평창군 대관령면 솔봉로 325<br>경기 : 아이스 하키</p>
					<a href="#">VR 보기</a>
				</div>
			</div>
			<div class="visual_div" style="background-image:url(/assets/comn/img/visual_1.png);">
				<div class="visual_des">
					<img src="/assets/comn/img/ico_std_9.png" alt="">
					<h1>강릉 컬링 센터</h1>
					<p>위치 : 강원도 평창군 대관령면 솔봉로 325<br>경기 : 컬링</p>
					<a href="#">VR 보기</a>
				</div>
			</div>
			<div class="visual_div" style="background-image:url(/assets/comn/img/visual_1.png);">
				<div class="visual_des">
					<img src="/assets/comn/img/ico_std_10.png" alt="">
					<h1>강릉 하키 센터</h1>
					<p>위치 : 강원도 평창군 대관령면 솔봉로 325<br>경기 : 아이스 하키</p>
					<a href="#">VR 보기</a>
				</div>
			</div>
			<div class="visual_div" style="background-image:url(/assets/comn/img/visual_1.png);">
				<div class="visual_des">
					<img src="/assets/comn/img/ico_std_11.png" alt="">
					<h1>강릉 아이스 아레나</h1>
					<p>위치 : 강원도 평창군 대관령면 솔봉로 325<br>경기 : 피겨 스케이팅, 쇼트트랙, 스피드 스케이팅</p>
					<a href="#">VR 보기</a>
				</div>
			</div>
			<div class="visual_div" style="background-image:url(/assets/comn/img/visual_1.png);">
				<div class="visual_des">
					<img src="/assets/comn/img/ico_std_12.png" alt="">
					<h1>강릉 스피드 스케이팅 경기장</h1>
					<p>위치 : 강원도 평창군 대관령면 솔봉로 325<br>경기 : 스피드 스케이팅</p>
					<a href="#">VR 보기</a>
				</div>
			</div>
		</div>
		<div class="slider-nav">
			<div class="nav_div">
				<img src="/assets/comn/img/thumb_01.jpg" alt="" class="visual_img">
				<div class="nav_over"><p><span>알펜시아 바이애슬론 센터</span></p></div>
			</div>
			<div class="nav_div">
				<img src="/assets/comn/img/thumb_02.jpg" alt="" class="visual_img">
				<div class="nav_over"><p><span>알펜시아 크로스컨트리 센터</span></p></div>
			</div>
			<div class="nav_div">
				<img src="/assets/comn/img/thumb_03.jpg" alt="" class="visual_img">
				<div class="nav_over"><p><span>알펜시아 스키점프 센터</span></p></div>
			</div>
			<div class="nav_div">
				<img src="/assets/comn/img/thumb_04.jpg" alt="" class="visual_img">
				<div class="nav_over"><p><span>올림픽 슬라이딩 센터</span></p></div>
			</div>
			<div class="nav_div">
				<img src="/assets/comn/img/thumb_05.jpg" alt="" class="visual_img">
				<div class="nav_over"><p><span>휘닉스 스노 경기장</span></p></div>
			</div>
			<div class="nav_div">
				<img src="/assets/comn/img/thumb_06.jpg" alt="" class="visual_img">
				<div class="nav_over"><p><span>정선 알파인 경기장</span></p></div>
			</div>
			<div class="nav_div">
				<img src="/assets/comn/img/thumb_07.jpg" alt="" class="visual_img">
				<div class="nav_over"><p><span>용평 알파인 경기장</span></p></div>
			</div>
			<div class="nav_div">
				<img src="/assets/comn/img/thumb_08.jpg" alt="" class="visual_img">
				<div class="nav_over"><p><span>관동 하키 센터</span></p></div>
			</div>
			<div class="nav_div">
				<img src="/assets/comn/img/thumb_09.jpg" alt="" class="visual_img">
				<div class="nav_over"><p><span>강릉 컬링 센터</span></p></div>
			</div>
			<div class="nav_div">
				<img src="/assets/comn/img/thumb_10.jpg" alt="" class="visual_img">
				<div class="nav_over"><p><span>강릉 하키 센터</span></p></div>
			</div>
			<div class="nav_div">
				<img src="/assets/comn/img/thumb_11.jpg" alt="" class="visual_img">
				<div class="nav_over"><p><span>강릉 아이스 아레나</span></p></div>
			</div>
			<div class="nav_div">
				<img src="/assets/comn/img/thumb_12.jpg" alt="" class="visual_img">
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
							<img src="/assets/comn/img/thumb_01.jpg" alt="사진" class="def">
							<div class="dark"></div>
						</div>
						<span class="mark" style="background-image:url(/assets/comn/img/ico_std_1.png);">알펜시아 바이애슬론 센터</span>
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
							<img src="/assets/comn/img/thumb_01.jpg" alt="사진" class="def">
							<div class="dark"></div>
						</div>
						<span class="mark" style="background-image:url(/assets/comn/img/ico_std_1.png);">알펜시아 바이애슬론 센터</span>
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
							<img src="/assets/comn/img/thumb_01.jpg" alt="사진" class="def">
							<div class="dark"></div>
						</div>
						<span class="mark" style="background-image:url(/assets/comn/img/ico_std_1.png);">알펜시아 바이애슬론 센터</span>
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
							<img src="/assets/comn/img/thumb_01.jpg" alt="사진" class="def">
							<div class="dark"></div>
						</div>
						<span class="mark" style="background-image:url(/assets/comn/img/ico_std_1.png);">알펜시아 바이애슬론 센터</span>
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
							<img src="/assets/comn/img/thumb_01.jpg" alt="사진" class="def">
							<div class="dark"></div>
						</div>
						<span class="mark" style="background-image:url(/assets/comn/img/ico_std_1.png);">알펜시아 바이애슬론 센터</span>
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
							<img src="/assets/comn/img/thumb_01.jpg" alt="사진" class="def">
							<div class="dark"></div>
						</div>
						<span class="mark" style="background-image:url(/assets/comn/img/ico_std_1.png);">알펜시아 바이애슬론 센터</span>
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
							<img src="/assets/comn/img/thumb_01.jpg" alt="사진" class="def">
							<div class="dark"></div>
						</div>
						<span class="mark" style="background-image:url(/assets/comn/img/ico_std_1.png);">알펜시아 바이애슬론 센터</span>
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
							<img src="/assets/comn/img/thumb_01.jpg" alt="사진" class="def">
							<div class="dark"></div>
						</div>
						<span class="mark" style="background-image:url(/assets/comn/img/ico_std_1.png);">알펜시아 바이애슬론 센터</span>
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
							<img src="/assets/comn/img/thumb_01.jpg" alt="사진" class="def">
							<div class="dark"></div>
						</div>
						<span class="mark" style="background-image:url(/assets/comn/img/ico_std_1.png);">알펜시아 바이애슬론 센터</span>
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
							<img src="/assets/comn/img/thumb_01.jpg" alt="사진" class="def">
							<div class="dark"></div>
						</div>
						<span class="mark" style="background-image:url(/assets/comn/img/ico_std_1.png);">알펜시아 바이애슬론 센터</span>
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
							<img src="/assets/comn/img/thumb_01.jpg" alt="사진" class="def">
							<div class="dark"></div>
						</div>
						<span class="mark" style="background-image:url(/assets/comn/img/ico_std_1.png);">알펜시아 바이애슬론 센터</span>
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
							<img src="/assets/comn/img/thumb_01.jpg" alt="사진" class="def">
							<div class="dark"></div>
						</div>
						<span class="mark" style="background-image:url(/assets/comn/img/ico_std_1.png);">알펜시아 바이애슬론 센터</span>
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

				<div class="item">
					<div class="item_wrap">
						<div class="thumb">
							<img src="/assets/comn/img/thumb_01.jpg" alt="사진" class="def">
							<div class="dark"></div>
						</div>
						<span class="mark" style="background-image:url(/assets/comn/img/ico_std_1.png);">알펜시아 바이애슬론 센터</span>
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
							<img src="/assets/comn/img/thumb_01.jpg" alt="사진" class="def">
							<div class="dark"></div>
						</div>
						<span class="mark" style="background-image:url(/assets/comn/img/ico_std_1.png);">알펜시아 바이애슬론 센터</span>
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
							<img src="/assets/comn/img/thumb_01.jpg" alt="사진" class="def">
							<div class="dark"></div>
						</div>
						<span class="mark" style="background-image:url(/assets/comn/img/ico_std_1.png);">알펜시아 바이애슬론 센터</span>
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
							<img src="/assets/comn/img/thumb_01.jpg" alt="사진" class="def">
							<div class="dark"></div>
						</div>
						<span class="mark" style="background-image:url(/assets/comn/img/ico_std_1.png);">알펜시아 바이애슬론 센터</span>
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
							<img src="/assets/comn/img/thumb_01.jpg" alt="사진" class="def">
							<div class="dark"></div>
						</div>
						<span class="mark" style="background-image:url(/assets/comn/img/ico_std_1.png);">알펜시아 바이애슬론 센터</span>
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
							<img src="/assets/comn/img/thumb_01.jpg" alt="사진" class="def">
							<div class="dark"></div>
						</div>
						<span class="mark" style="background-image:url(/assets/comn/img/ico_std_1.png);">알펜시아 바이애슬론 센터</span>
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


	<!-- ========================== 내용 종료 ========================= -->
	</section><!-- //section-vr -->


	<!-- 메인 공통 팝업 처리 -->
	<jsp:include page="/WEB-INF/views/template/incFrontPopup.jsp" />
	<!-- 메인 공통 팝업 처리 -->