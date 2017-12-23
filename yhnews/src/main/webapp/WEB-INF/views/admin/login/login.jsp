<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/comm/taglib.jsp"%>

<!DOCTYPE html>
<html lang="ko">
<head>
	<jsp:include page="/WEB-INF/views/template/adminHeader.jsp" flush="true"/>	
</head>
<body>
<div class="wrapper login">
	<div class="login_wrap">
		<div class="login_header">
			<h1><img src="/assets/admin/img/login_logo.png" alt="연합뉴스 360 라이브러리"><span>관리자페이지</span></h1>
			<a href="/" class="banner_home">
				<span class="ico_home"></span>
				<span class="txt">연합뉴스 360 라이브러리<br>홈페이지 바로가기</span>
			</a>
		</div>
		<div class="login_body">
			<div class="login_form">
				<fieldset>
					<legend>관리자로그인</legend>
					
					<form:form commandName="theForm" action="loginProc.do">

						<h2>LOGIN</h2>
						<ul>
							<li><form:input path="user_id" name="admin_id" class="" placeholder="아이디" title="아이디 입력"/></li>
							<li><input type="password" name="passwd" class="p_textbox" placeholder="비밀번호" title="비밀번호 입력"></li>
						</ul>
						<p><input type="checkbox" name="id_save"> 아이디 기억하기</p>
						<p><button class="btn_ok" style="cursor: pointer;">LOGIN</button></p>
						<p class="info_text"><span class="ico ico_info"></span>관리자 사이트는 연합뉴스 360 라이브러리에서 <br>운영하고 있습니다.</p>
						
					</form:form>

				</fieldset>
			</div>
		</div>
		<div class="login_footer">
			<div class="copy_wrap">
				<p>서울시 종로구 율곡로2길 25 / Tel. 02-398-3114</p>
				<p>Copyright © 연합뉴스 All Rights Reserved.</p>
			</div><!-- //copy_wrap -->
		</div>
	</div>
</div>

</body>
</html><!-- //하단부분 -->