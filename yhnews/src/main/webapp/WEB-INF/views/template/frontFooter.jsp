<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/comm/taglib.jsp"%>

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

<!-- 운영계 구글 애널리틱스 스크립트 적용 -->
<jsp:include page="/WEB-INF/views/template/incGoogleAnalytics.jsp"/>