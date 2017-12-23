<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/comm/taglib.jsp"%>

<script type="text/javascript">
$(document).ready(function(){
	var popKey,popKind,popTitle,popLinkAddress,popIsNewWindow
	,popWidthSize,popHeightSize,popTopMargin,popLeftMargin
	,popSaveFileName,popFilePath;

	$('input[name=popKey]').each(function(i){
		popKey = $(this).val();
		popKind = $('input[name=popKind]').eq(i).val();
		popTitle = $('input[name=popTitle]').eq(i).val();
		popLinkAddress = $('input[name=popLinkAddress]').eq(i).val();
		popIsNewWindow = $('input[name=popIsNewWindow]').eq(i).val();
		popWidthSize = $('input[name=popWidthSize]').eq(i).val();
		popHeightSize = $('input[name=popHeightSize]').eq(i).val();
		popTopMargin = $('input[name=popTopMargin]').eq(i).val();
		popLeftMargin = $('input[name=popLeftMargin]').eq(i).val();
		popSaveFileName = $('input[name=popSaveFileName]').eq(i).val();
		popFilePath = $('input[name=popFilePath]').eq(i).val();
		
		// 윈도우창 팝업
		if(popKind == '1'){				
			WindowOpenCustom('','MAIN_POPUP_'+popKey,popWidthSize,parseInt(popHeightSize)+30,popLeftMargin,popTopMargin);
			$('#popKey').val(popKey);
			$('#mainPopupForm').attr('target','MAIN_POPUP_'+popKey);
			$('#mainPopupForm').submit();
		// 임베디드 팝업
		} else {			
		
			popStyle = '';
			if(popLinkAddress.isNvl()){
				popStyle = 'style="cursor:pointer;"';
			}
			
			popHtml = '';
			popHtml += '<div id="divPopup_'+popKey+'" class="popupLayer" style="position:relative; z-index:5; width:'+popWidthSize+'px; height:'+popHeightSize+'px; left:'+popLeftMargin+'px; top:'+popTopMargin+'px;">';
			popHtml += '	<div class="popupLayer_con popupArea" linkAddress="'+popLinkAddress+'" isNewWindow="'+popIsNewWindow+'" '+popStyle+'>';
			if(popSaveFileName.isNvl()){
				popHtml += '		<img src="'+uploadDefaultUrl+popFilePath+popSaveFileName+'" alt="'+popTitle+'">';	
			} else {
				popHtml += popTitle;
			}			
			popHtml += '	</div>';
			popHtml += '	<div class="todayopen">';
			popHtml += '		<input type="checkbox" id="popupToday"> <label for="popupToday">오늘하루 열지않음</label>';
			popHtml += '		<a href="#close" popKey="'+popKey+'" class="btnDivPopClose"><span class="ico ico_close"></span>창닫기</a>';
			popHtml += '	</div>';
			popHtml += '</div>';			
			
			$('body').append(popHtml);
		}
	});
	
	$('.popupArea').on('click',function(){
		linkAddress = $(this).attr('linkAddress');
		isNewWindow = $(this).attr('isNewWindow');
	
		if(linkAddress.isNvl()){
			if(isNewWindow == 'Y'){
				window.open(linkAddress, '_blank');
			} else {
				location.href = linkAddress;
			}
		}
	});
	
	// 창닫기
	$(document).on('click','.btnDivPopClose',function(e){
		e.preventDefault();
		var popKey = $(this).attr('popKey');
		if($(this).parent().find('input').is(':checked')){
			var expire = new Date();
	        expire.setDate(expire.getDate() + 1);
	        setCookie('DISABLED_POPUP_'+popKey,'Y',expire);	
		}
		$('#divPopup_'+popKey).remove();		
	});
	
});
</script>

<form id="mainPopupForm" action="/notifyPopup.do" method="post">
<input type="hidden" name="popup_key" id="popKey"/> 
</form>

<c:forEach var="data" items="${mainPopupList}">
<c:if test="${data.is_show_yn eq 'Y'}">
<input type="hidden" name="popKey" value="${data.popup_key}"/>
<input type="hidden" name="popKind" value="${data.kind}"/>
<input type="hidden" name="popTitle" value="${data.title}"/>
<input type="hidden" name="popLinkAddress" value="${data.link_address}"/>
<input type="hidden" name="popIsNewWindow" value="${data.is_new_window}"/>
<input type="hidden" name="popWidthSize" value="${data.width_size}"/>
<input type="hidden" name="popHeightSize" value="${data.height_size}"/>
<input type="hidden" name="popTopMargin" value="${data.top_margin}"/>
<input type="hidden" name="popLeftMargin" value="${data.left_margin}"/>
<input type="hidden" name="popSaveFileName" value="${data.save_file_name}"/>
<input type="hidden" name="popFilePath" value="${data.file_path}"/>
</c:if>
</c:forEach>