<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/comm/taglib.jsp"%>

<!-- 개인정보수정화면 -->
<script type="text/javascript">
jQuery(function(){
	jQuery('.myPage').on('click',function(){
		ajaxCall(null,'userInfoJson.do');
		ajaxRes.success(function(result){			
			//console.log(result)
			if(result.RESULT_CODE == 'SUCCESS'){
				var obj = result.RESULT_DATA;
				jQuery('#myName').val(obj.user_name);
				jQuery('#myEmail').val(obj.email);
				jQuery('#myPhone1').val(obj.phoneArr[0]);
				jQuery('#myPhone2').val(obj.phoneArr[1]);
				jQuery('#myPhone3').val(obj.phoneArr[2]);
				// 팝업열기
				regist_view('myPagePop');
			}											
		});		
	});
	
	// 내정보수정
	jQuery('.myInfoSave').on('click',function(){		
		if(!inputValidate('myName'))return;
		if(!inputValidate('myPasswd'))return;
		if(!inputValidate('myPasswdConfirm'))return;
		if(!inputValidate('myEmail'))return;
		if(!inputValidate('myPhone1'))return;
		if(!inputValidate('myPhone2'))return;
		if(!inputValidate('myPhone3'))return;
		
		if(confirm('변경하시겠습니까?')){
			var params = {
				user_name : jQuery('#myName').val()	,
				passwd : jQuery('#myPasswd').val() ,
				email : jQuery('#myEmail').val() , 
				phone1 : jQuery('#myPhone1').val() ,
				phone2 : jQuery('#myPhone2').val() ,
				phone3 : jQuery('#myPhone3').val()
			};
			ajaxCall(params,'userMyInfoSave.do');
			ajaxRes.success(function(result){
				if(result.RESULT_CODE == 'SUCCESS'){
					jQuery('#myInfoArea').text(jQuery('#myName').val()+'('+GLOBAL_USER_ID+')');
					alert('사용자 정보가 변경되었습니다.');
					// 팝업닫기
					regist_close('myPagePop');
				}
			});	
		}	
	});
	
	// 비밀번호 변경
	jQuery('#myPwChange').on('change',function(){		
		if(jQuery(this).is(":checked")){
			jQuery('input[type=password]').attr('disabled',false);	
		} else {
			jQuery('input[type=password]').attr('disabled',true);
		}
	});
});
</script>

<footer>
	<div class="copy_wrap">
		<p>서울시 종로구 율곡로2길 25 / Tel. 02-398-3114</p>
		<p>Copyright © 연합뉴스 All Rights Reserved.</p>
	</div><!-- //copy_wrap -->
</footer>

<div id="myPagePop" class="popup_wrap">
	<div class="popup_header">마이페이지</div>
	<a class="ico ico_close">창닫기</a>
	<div class="popup_body">

		<fieldset>
		<legend></legend>
		
		<table class="table_form">
		<caption>사용자 정보</caption>
		<colgroup>
			<col style="width:20%;" />
			<col style="width:80%;" />
		</colgroup>
		<tbody>
		<tr>
			<th scope="row">아이디</th>
			<td><c:out value="${GLOBAL_USER_ID}"/></td>
		</tr>
		<tr>
			<th scope="row">이름</th>
			<td><input type="text" id="myName" title="이름" class="col100" isNullCheck="true" maxlength="100"/></td>
		</tr>
		<tr>
			<th scope="row">비밀번호</th>
			<td>
				<input type="password" id="myPasswd" title="비밀번호" class="col100" isNullCheck="true" equalsTarget="myPasswdConfirm" maxlength="20" disabled="disabled"/>
				<input type="checkbox" id="myPwChange"/> <label for="myPwChange">비밀번호 변경</label>
			</td>
		</tr>
		<tr>
			<th scope="row">비밀번호확인</th>
			<td><input type="password" id="myPasswdConfirm" title="비밀번호확인" class="col100" isNullCheck="true" maxlength="20" disabled="disabled"/></td>
		</tr>
		<tr>
			<th scope="row">이메일</th>
			<td><input type="text" id="myEmail" title="이메일" class="col100" isNullCheck="true" isEmailCheck="true" maxlength="100"/></td>
		</tr>
		<tr>
			<th scope="row">전화번호</th>
			<td>
				<input type="text" id="myPhone1" title="전화번호" class="col16 numberOnly" isNullCheck="true" isNumberCheck="true" maxlength="4"/> -
				<input type="text" id="myPhone2" title="전화번호" class="col16 numberOnly" isNullCheck="true" isNumberCheck="true" maxlength="4"/> - 
				<input type="text" id="myPhone3" title="전화번호" class="col16 numberOnly" isNullCheck="true" isNumberCheck="true" maxlength="4"/>
			</td>
		</tr>	
		</tbody>
		</table>
		
		<div class="btn_wrap clearfix textAlign_center com-mt10">			
			<a href="#" class="btn primary myInfoSave">변경하기</a>
		</div>
		
		</fieldset>
	</div>
</div>
<!--// 개인정보수정화면 -->