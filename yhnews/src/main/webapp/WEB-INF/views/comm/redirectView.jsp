<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<title>${siteName}</title>
<script type="text/javascript">
	var msg = "${Msg}";
	var forward = "${Forward}";
	var confirmForward = "${ConfirmForward}";
	msg = msg.replace(/\|/gi, "\n");

	// 일반 알림창
	if(confirmForward == undefined || confirmForward == null || confirmForward == '') {
		if(msg != ""){
			alert(msg);
		}
		if(forward != ""){
			location.href=forward;
		} else {
			//document.referrer ? history.back() : location.href="/";
		}
	// 확인 알림창	
	} else {
		if(msg != ""){
			if(confirm(msg)){
				location.href=confirmForward;
			} else {
				if(forward != ""){
					location.href=forward;
				}		
			}	
		}		
	}	
</script>
</head>
<body>

</body>
</html>