<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/comm/taglib.jsp"%>

<!DOCTYPE html>
<html lang="ko">
<head>
	<meta charset="UTF-8">
	<meta http-equiv="Content-Type" content="text/html;charset=utf-8" />
	<meta http-equiv="X-UA-Compatible" content="IE=Edge" />
	<title>팝업 - <c:out value=" ${popupInfo.title}"/></title>
	<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">	
	<link rel="stylesheet" type="text/css" href="/assets/forum/css/reset.css">
    <link rel="stylesheet" type="text/css" href="/assets/forum/css/common.css">
	<script type="text/javascript" src="/assets/forum/js/jquery-1.12.4.min.js"></script>
	<script src="/assets/plugin/comm/common.js" type="text/javascript"></script>
	
	<script type="text/javascript">
	$(function(){
		popupKey = $('#popupArea').attr('popupKey');
		linkAddress = $('#popupArea').attr('linkAddress');
		isNewWindow = $('#popupArea').attr('isNewWindow');
		if(linkAddress.isNvl()){
			$('#popupArea').css('cursor','pointer');	
		}
		
		$('#popupArea').on('click',function(){
			if(linkAddress.isNvl()){
				if(isNewWindow == 'Y'){
					window.open(linkAddress, '_blank');
				} else {
					opener.location.href = linkAddress;
				}
				self.close();
			}
		});
		
		// 오늘하루 그만보기
		$('#closePopup').on('click',function(e){
			e.preventDefault();
			if($('#popupCheck').is(':checked')){
				 var expire = new Date();
		         expire.setDate(expire.getDate() + 1);
		         setCookie('DISABLED_POPUP_'+popupKey,'Y',expire);
			}
			self.close();
		});
	});		
		
	</script>	
</head>
<body>

<c:if test="${!empty popupInfo}">
<div class="popupLayer pop_window" >

	<div class="popupLayer_con" id="popupArea" popupKey="${popupInfo.popup_key}" linkAddress="${popupInfo.link_address}" isNewWindow="${popupInfo.is_new_window}">
	<c:choose>
	<c:when test="${!empty popupInfo.save_file_name}">
		<img src="${uploadDefaultUrl}/${uploadImgEditorDir}${popupInfo.file_path}${popupInfo.save_file_name}" title="<c:out value=" ${popupInfo.title}"/>"/>
	</c:when>
	<c:otherwise>
		<c:out value=" ${popupInfo.title}"/>
	</c:otherwise>
	</c:choose>
	</div>

	<div class="todayopen">
		<input type="checkbox" id="popupCheck"/> <label for="popupCheck">하루동안 이 창을 열지 않음</label>
		<a href="#" id="closePopup"><span class="ico ico_close"></span>닫기</a>
	</div>
	
</div>
</c:if>
</body>
</html>