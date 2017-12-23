<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
String referer = request.getHeader("REFERER");
if(referer == null || referer == "") referer = "/";
%>
<!DOCTYPE html>
<html lang="ko">
<head>
	<meta http-equiv="Content-Type" content="text/html;charset=utf-8" />
	<meta http-equiv="X-UA-Compatible" content="IE=Edge" />
	<title>404 Error</title>
	<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
	<style>
		html, body {height:100%; margin:0; padding:0;}
		body {background:#0d8581}
		.img {width:520px; height:350px; position:absolute; left:50%; top:50%; margin:-200px 0 0 -275px;}
		@media screen and (max-width: 560px) {
			.img {width:100%; display:table-cell; position:relative; left:auto; top:auto; margin:0; padding:20px; vertical-align:middle;}
			.img img {width:100%;}
		}
	</style>
</head>
<body>

<div class="img"><a href="<%=referer%>"><img src="/assets/forum/images/common/error_404.png"></a></div>

</body>
</html>