<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/comm/taglib.jsp"%>

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
					<strong class="nav-home"><a href="/front/index.do">홈</a></strong>
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
							<li class="nav-about"><a href="/front/information/html.do">서비스 소개</a></li>
							<li class="nav-notice"><a href="/front/notice/boardList.do">공지사항</a></li>
							<li class="nav-faq"><a href="/front/faq/boardList.do">자주묻는 질문</a></li>
						</ul>
					</div>
					<div>
					<c:choose>
           			<c:when test="${empty GLOBAL_USER_ID}">
           				<div>
							<strong class="tit01">회원서비스</strong>
							<ul class="list01">
								<li class="nav-login"><a href="/front/login/login.do">SNS 로그인</a></li>
							</ul>
						</div>
           			</c:when>
           			<c:otherwise>
           				<div>
							<strong class="tit01">마이페이지</strong>
							<ul class="list01">
								<li class="nav-mycontents"><a href="mypage_contents.html">컨텐츠 관리</a></li>
								<li class="nav-logout"><a href="/front/logout.do">로그아웃</a></li>
							</ul>
						</div>
           			</c:otherwise>
           			</c:choose>
					
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