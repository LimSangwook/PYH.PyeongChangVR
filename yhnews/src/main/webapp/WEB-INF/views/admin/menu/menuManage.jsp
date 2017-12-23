<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/comm/taglib.jsp"%>

<script type="text/javascript">
$(function(){
	var act = "${theForm.act}";
	// 등록/수정
	$('.btnSave').on('click',function(e){
		e.preventDefault();
		if(formValidate()){
			if($('#menu_kind').val() == 'board'){
				if(!$('#board_id').val().isNvl()){
					alert('선택된 게시판이 없습니다.');
					return;	
				}
				if(!$('#link_address').val().isNvl()){
					alert('연결주소를 입력해 주시기 바랍니다.\n\nex) /대메뉴코드/중메뉴코드/소메뉴코드/boardList.do');
					return;
				}
				
			} else if($('#menu_kind').val() == 'html'){
				if(!$('#page_seq').val().isNvl()){
					alert('선택된 콘텐츠가 없습니다.');
					return;	
				}
				if(!$('#link_address').val().isNvl()){
					alert('연결주소를 입력해 주시기 바랍니다.\n\nex) /대메뉴코드/중메뉴코드/소메뉴코드/html.do');
					return;
				}
			}
			
			if(act == 'updateMenu'){
				if(!confirm('수정하시겠습니까?')){
					return;
				}
			} else {
				if($('#isMenuDoubleCheck').val() != 'Y'){
					alert("메뉴코드 중복 확인을 하시기 바랍니다.");
					return;
				}
				if(!confirm('등록하시겠습니까?')){
					return;
				}
			}
			
			$('#theForm').submit();
		}
	});
	
	// 대메뉴 추가버튼 클릭시
	$('.btnAddDepth1').on('click',function(){
		if($('#systemGubun').val().isNvl()){
			$('#menuListFormAct').val('insertMenu');
			$('#orderStep').val('1');
			$('#menuDepth1').val('');
			$('#menuDepth2').val('');
			$('#menuDepth3').val('');
			$("#menuListForm").submit();
		} else {
			alert('대메뉴 시스템을 선택해 주시기 바랍니다.');
		}
	});
	
	// 중메뉴 추가버튼 클릭시
	$('.btnAddDepth2').on('click',function(){
		if($('#menuDepth1').val().isNvl()){
			$('#menuListFormAct').val('insertMenu');
			$('#orderStep').val('2');			
			$('#menuDepth2').val('');
			$('#menuDepth3').val('');
			$("#menuListForm").submit();
		} else {
			alert('상위 메뉴를 선택해 주시기 바랍니다.');
		}
	});
	
	// 소메뉴 추가버튼 클릭시
	$('.btnAddDepth3').on('click',function(){
		if($('#menuDepth2').val().isNvl()){
			$('#menuListFormAct').val('insertMenu');
			$('#orderStep').val('3');			
			$('#menuDepth3').val('');
			$("#menuListForm").submit();
		} else {
			alert('상위 메뉴를 선택해 주시기 바랍니다.');
		}
	});
	
	// 메뉴코드 변경시
	$("#menuCode").on("change",function(){
		$("#isMenuDoubleCheck").val("N");
	});
	
	// 메뉴코드 중복확인
	$(".btnMenuCodeDoubleCheck").on("click",function(e){
		e.preventDefault();
		var menuCode = $('#menuCode').val();
		if(menuCode.isNvl()){
			if(!menuCode.isValidCode()){
				return;
			}
			var params = {menu_code:menuCode}
			ajaxCall(params,'menuCodeCheck.do');
			ajaxRes.success(function(result){				
				if(result.RESULT_CODE == 'SUCCESS'){					
					alert('사용가능한 메뉴코드 입니다.');
					$('#isMenuDoubleCheck').val('Y');
				} else {
					alert('이미 사용중인 메뉴코드 입니다.');
					$('#menuCode').val('');
					$('#isMenuDoubleCheck').val('N');
				}						
			});
		} else {
			alert('메뉴코드를 입력해 주시기 바랍니다.');	
			$('#menuCode').focus();
		}
	});
	
	// 메뉴순서변경
	$('.btnMenuSort').on('click',function(){
		if(confirm('메뉴 순서를 변경하시겠습니까?')){
			$('#menuListForm').attr('action','menuSortUpdate.do');
			$('#menuListForm').submit();
		}
	});
	
	// 메뉴삭제
	$('.btnDelete').on('click',function(){
		if(confirm('정말로 삭제하시겠습니까?')){
			$('#menuDeleteFormAct').val('deleteMenu');
			$("#menuDeleteForm").attr("action","menuSave.do");
			$('#menuDeleteForm').submit();
		}
	});
	
	// 메뉴타입변경시
	$('#menu_kind').on('change',function(){
		if($(this).val() == 'menu'){
			$('.boardFindBtn').hide();
			$('.contentsFindBtn').hide();
		} else if($(this).val() == 'html'){			
			$('.contentsFindBtn').show();
			$('.boardFindBtn').hide();
		} else if($(this).val() == 'board'){
			$('.boardFindBtn').show();
			$('.contentsFindBtn').hide();
		}
		$('#board_id').val('');
		$('#page_seq').val('');
		$('#linkNameTxt').text('');
	});
	
	// 게시판 검색 팝업
	$('.boardFindBtn').on('click',function(e){
		e.preventDefault();
		
		ajaxCall(null,'boardConfigListJson.do');
		ajaxRes.success(function(result){
			
			var html=[],h=-1;
			$(result.RESULT_DATA).each(function(index, data) {							
            	if(data != null) {	                    		
            		html[++h] = '<tr>';
            		html[++h] = ' <td><a href="#" class="btn btn_xsmall boardChoiceBtn" boardId="'+data.board_id+'" boardName="'+data.board_name+'">선택</a></td>';
            		html[++h] = ' <td>'+data.board_name+'</td>';
            		html[++h] = ' <td>'+data.board_type+'</td>';
            		html[++h] = ' <td>'+data.status+'</td>';
            		html[++h] = '</tr>';
            	}            	
            });			
			$('#boardConfigListArea').html(html.join(''));
			// 팝업열기
			regist_view('boardConfigPop');									
		});		
	});
	
	// 게시판선택
	$(document).on('click','.boardChoiceBtn',function(e){
		 e.preventDefault();
		 $('#board_id').val($(this).attr('boardId'));
		 $('#linkNameTxt').text($(this).attr('boardName'));
		 // 팝업닫기
		 regist_close('boardConfigPop');
	 });
	
	// 콘텐츠 검색 팝업
	$('.contentsFindBtn').on('click',function(e){
		e.preventDefault();	
		
		ajaxCall(null,'htmlListJson.do');
		ajaxRes.success(function(result){
			
			var html=[],h=-1;			
			$(result.RESULT_DATA).each(function(index, data) {
				
            	if(data != null) {	                    		
            		html[++h] = '<tr>';
            		html[++h] = ' <td><a href="#" class="btn btn_xsmall htmlChoiceBtn" pageSeq="'+data.page_seq+'" pageTitle="'+data.title+'">선택</a></td>';
            		html[++h] = ' <td class="textAlign_left">'+data.titleCrop+'</td>';
            		html[++h] = ' <td>'+data.status+'</td>';
            		html[++h] = '</tr>';
            	}            	
            });
			
			$('#contentsListArea').html(html.join(''));
			// 팝업열기
			regist_view('contentsHtmlPop');									
		});
	});
	
	// HTML선택
	$(document).on('click','.htmlChoiceBtn',function(e){
		 e.preventDefault();
		 $('#page_seq').val($(this).attr('pageSeq'));
		 $('#linkNameTxt').text($(this).attr('pageTitle'));
		 // 팝업닫기
		 regist_close('contentsHtmlPop');
	 });
	
	// 카테고리 jQuery UI sortable for the todo list
	$(".todo-list").sortable({
	  placeholder: "sort-highlight",
	  handle: ".handle",
	  forcePlaceholderSize: true,
	  zIndex: 999999
	});
	
	// 메뉴클릭
	$('.menuClick').on('click',function(e){
		e.preventDefault();
		window.location.href = $(this).attr('href')
				+ "&depth1AreaScroll="+$('#depth1Area').scrollTop()
				+ "&depth2AreaScroll="+$('#depth2Area').scrollTop()
				+ "&depth3AreaScroll="+$('#depth3Area').scrollTop();		
	});
	
	// 대메뉴 이전스크롤 이동    
    $('#depth1Area').scrollTop("${param.depth1AreaScroll}");
 	// 중메뉴 이전스크롤 이동
    $('#depth2Area').scrollTop("${param.depth2AreaScroll}");
 	// 소메뉴 이전스크롤 이동
    $('#depth3Area').scrollTop("${param.depth3AreaScroll}");
});
</script>

<ul class="tabMenu tablist menu_5">
	<li><a href="menuManage.do?system_gubun=admin" <c:if test="${theForm.system_gubun eq 'admin'}">class="active"</c:if>><span>관리자 메뉴</span></a></li>
	<c:forEach var="data" items="${siteServiceLangList}" varStatus="i">
		<li><a href="menuManage.do?system_gubun=${data.lang_code}" <c:if test="${theForm.system_gubun eq data.lang_code}">class="active"</c:if>><span>사용자 메뉴(${data.lang_name})</span></a></li>
	</c:forEach>
</ul>

<form:form id="menuDeleteForm" commandName="theForm">
<form:hidden path="act" id="menuDeleteFormAct"/>
<form:hidden path="menu_code" id="menuDeleteCode"/>
<form:hidden path="depth1" id="menuDeleteDepth1"/>
<form:hidden path="depth2" id="menuDeleteDepth2"/>
<form:hidden path="depth3" id="menuDeleteDepth3"/>
<form:hidden path="system_gubun"/>

<input type="hidden" name="depth1AreaScroll" value="${param.depth1AreaScroll}"/>
<input type="hidden" name="depth2AreaScroll" value="${param.depth2AreaScroll}"/>
<input type="hidden" name="depth3AreaScroll" value="${param.depth3AreaScroll}"/>

</form:form>

<form:form id="menuListForm" commandName="theForm" class="form-horizontal" action="menuManage.do">
<form:hidden path="act" id="menuListFormAct"/>
<form:hidden path="order_step" id="orderStep"/>
<form:hidden path="depth1" id="menuDepth1"/>
<form:hidden path="depth2" id="menuDepth2"/>
<form:hidden path="depth3" id="menuDepth3"/>
<form:hidden path="system_gubun" id="systemGubun"/>		

<input type="hidden" name="depth1AreaScroll" value="${param.depth1AreaScroll}"/>
<input type="hidden" name="depth2AreaScroll" value="${param.depth2AreaScroll}"/>
<input type="hidden" name="depth3AreaScroll" value="${param.depth3AreaScroll}"/>

<div class="col-wrap clearfix">
	<div class="col-item col33">
		<div class="item-style1" >
			<h1 class="label_info">대메뉴</h1>
			<ul id="depth1Area" class="list_style_1 todo-list" style="height:300px; overflow: auto;">
				<c:forEach var="data" items="${menuDepth1List}">				
				<li <c:if test="${data.menu_code eq  theForm.depth1}">class="current"</c:if>>
					<input type="hidden" name="menu_code_arr" value="${data.menu_code}"/>
					<input type="hidden" name="menu_step_arr" value="${data.order_step}"/>
					<span class="ico ico_mouse_sort handle"></span>
					<a class="menuClick" href="menuManage.do?menu_code=<c:out value="${data.menu_code}"/>&depth1=<c:out value="${data.menu_code}"/>&system_gubun=<c:out value="${theForm.system_gubun}"/>"><c:out value="${data.menu_name}"/> / <c:out value="${data.menu_code}"/></a>					
					<c:choose>
					<c:when test="${data.status eq 'Y'}"><span class="btn primary btn_xsmall">사용</span></c:when>
					<c:otherwise><span class="btn danger btn_xsmall">미사용</span></c:otherwise>
					</c:choose>					 
				</li>
				</c:forEach>
			</ul>
		</div>
		<div class="btn_wrap">
			<a href="#" class="btn float_left com-mt10 btnMenuSort">순서변경</a>
			<a href="#" class="btn primary float_right com-mt10 btnAddDepth1">대메뉴 추가</a>
		</div>				
	</div>
	<div class="col-item col33">
		<div class="item-style1">
			<h1 class="label_info">중메뉴</h1>
			<ul id="depth2Area" class="list_style_1 todo-list" style="height:300px; overflow: auto;">
				<c:forEach var="data" items="${menuDepth2List}">
				<li <c:if test="${data.menu_code eq  theForm.depth2}">class="current"</c:if>>
					<input type="hidden" name="menu_code_arr" value="${data.menu_code}"/>
					<input type="hidden" name="menu_step_arr" value="${data.order_step}"/>
					<span class="ico ico_mouse_sort handle"></span>
					<a class="menuClick" href="menuManage.do?menu_code=<c:out value="${data.menu_code}"/>&depth1=<c:out value="${theForm.depth1}"/>&depth2=<c:out value="${data.menu_code}"/>&system_gubun=<c:out value="${theForm.system_gubun}"/>"><c:out value="${data.menu_name}"/></a> / <c:out value="${data.menu_code}"/>
					<c:choose>
					<c:when test="${data.status eq 'Y'}"><span class="btn primary btn_xsmall">사용</span></c:when>
					<c:otherwise><span class="btn danger btn_xsmall">미사용</span></c:otherwise>
					</c:choose>
				</li>
				</c:forEach>
			</ul>
		</div>
		<div class="btn_wrap">
			<a href="#" class="btn float_left com-mt10 btnMenuSort">순서변경</a>
			<a href="#" class="btn primary float_right com-mt10 btnAddDepth2">중메뉴 추가</a>
		</div>
	</div>
	<div class="col-item col33">
		<div class="item-style1">
			<h1 class="label_info">소메뉴</h1>
			<ul id="depth3Area" class="list_style_1 todo-list" style="height:300px; overflow: auto;">
				<c:forEach var="data" items="${menuDepth3List}">
				<li <c:if test="${data.menu_code eq  theForm.depth3}">class="current"</c:if>>
					<input type="hidden" name="menu_code_arr" value="${data.menu_code}"/>
					<input type="hidden" name="menu_step_arr" value="${data.order_step}"/>
					<span class="ico ico_mouse_sort handle"></span>
					<a class="menuClick" href="menuManage.do?menu_code=<c:out value="${data.menu_code}"/>&depth1=<c:out value="${theForm.depth1}"/>&depth2=<c:out value="${theForm.depth2}"/>&depth3=<c:out value="${data.menu_code}"/>&system_gubun=<c:out value="${theForm.system_gubun}"/>"><c:out value="${data.menu_name}"/></a> / <c:out value="${data.menu_code}"/>
					<c:choose>
					<c:when test="${data.status eq 'Y'}"><span class="btn primary btn_xsmall">사용</span></c:when>
					<c:otherwise><span class="btn danger btn_xsmall">미사용</span></c:otherwise>
					</c:choose>
				</li>
				</c:forEach>
			</ul>
		</div>
		<div class="btn_wrap">
			<a href="#" class="btn float_left com-mt10 btnMenuSort">순서변경</a>
			<a href="#" class="btn primary float_right com-mt10 btnAddDepth3">소메뉴 추가</a>
		</div>
	</div>	
</div>
</form:form>

<c:if test="${theForm.act eq 'insertMenu' || theForm.act eq 'updateMenu' || !empty theForm.menu_code}">

<h3 class="tit-h3">메뉴 등록/수정</h3>

<form:form commandName="theForm" action="menuSave.do">
<form:hidden path="act"/>
<form:hidden path="depth1"/>
<form:hidden path="depth2"/>
<form:hidden path="depth3"/>
<form:hidden path="order_step"/>
<form:hidden path="system_gubun"/>
<form:hidden path="board_id"/>
<form:hidden path="page_seq"/>

<input type="hidden" id="isMenuDoubleCheck"/>

<input type="hidden" name="depth1AreaScroll" value="${param.depth1AreaScroll}"/>
<input type="hidden" name="depth2AreaScroll" value="${param.depth2AreaScroll}"/>
<input type="hidden" name="depth3AreaScroll" value="${param.depth3AreaScroll}"/>

	<table class="table_form">
		<caption>사용자 정보</caption>
		<colgroup>
			<col style="width:20%;" />
			<col style="width:80%;" />
		</colgroup>
		<tbody>
			<tr>
				<th scope="row">사용여부</th>
				<td>
					<form:select path="status" title="사용여부" isNullCheck="true">						
						<form:option value="Y" label="사용"/>
						<form:option value="N" label="미사용"/>
					</form:select>
				</td>
			</tr>
			<tr>
				<th scope="row">메뉴코드</th>
				<td>
					<c:choose>
					<c:when test="${empty theForm.menu_code}">
						<p><form:input path="menu_code" id="menuCode" title="메뉴코드" cssClass="col16" isNullCheck="true" maxlength="20"/> <a href="#" class="btn btn_small btnMenuCodeDoubleCheck">중복확인</a></p>
					</c:when>
					<c:otherwise>
						<form:hidden path="menu_code"/>
						<c:out value="${theForm.menu_code}"/>					
					</c:otherwise>
					</c:choose>
				</td>
			</tr>			
			<tr>
				<th scope="row">메뉴명</th>
				<td><form:input path="menu_name" title="메뉴명" cssClass="col16" isNullCheck="true" maxlength="20"/></td>
			</tr>
			<tr>
				<th scope="row">메뉴타입</th>
				<td>
					<form:select path="menu_kind" title="메뉴타입" isNullCheck="true">						
						<form:option value="menu"  label="일반메뉴"/>
						<form:option value="html" label="HTML콘텐츠"/>
						<form:option value="board" label="게시판연동"/>
					</form:select>
					<span class="btn btn_xsmall success" id="linkNameTxt">${theForm.board_name}${theForm.html_title}</span>
					<a href="#" class="btn btn_xsmall boardFindBtn" style="<c:if test="${theForm.menu_kind ne 'board'}">display:none;</c:if>">게시판 검색</a>
					<a href="#" class="btn btn_xsmall contentsFindBtn" style="<c:if test="${theForm.menu_kind ne 'html'}">display:none;</c:if>">콘텐츠 검색</a>
				</td>
			</tr>
			<tr>
				<th scope="row">연결주소</th>
				<td>
					<form:input path="link_address" title="연결주소" cssClass="col50" maxlength="100"/>
					<ul class="list_style_2">
						<li>일반메뉴 : /대메뉴코드/중메뉴코드/소메뉴코드/*.do</li>
						<li>HTML콘텐츠연동시 : /대메뉴코드/중메뉴코드/소메뉴코드/html.do</li>
						<li>게시판연동시 : /대메뉴코드/중메뉴코드/소메뉴코드/boardList.do</li>
						<li>새창열림사용시 : http:// or https://를 포함한 전체주소 입력</li>
					</ul>
				</td>
			</tr>
			<tr>
				<th scope="row">새창열림여부</th>
				<td>
					<form:select path="is_new_window" title="메뉴타입" isNullCheck="true">						
						<form:option value="N" label="현재창열림"/>
						<form:option value="Y" label="새창열림"/>
					</form:select>
				</td>
			</tr>
			<tr>
				<th scope="row">메뉴접근레벨${menu_auth_level }</th>
				<td>
					<!-- 일반회원은 제외시킨다 -->				
					<c:forEach var="data" items="${userAuthLevelCodeList}" varStatus="i">
						<c:if test="${data.code > 1}">
							<c:set var="authChecked">
								<c:choose>
								<c:when test="${fn:indexOf(theForm.menu_auth_level,data.code) != -1 and !empty theForm.menu_auth_level}">checked</c:when>
								<c:otherwise></c:otherwise>
								</c:choose>
							</c:set>
							<input type="checkbox" name="menu_auth_arr" id="menu_auth_arr${i.count}" value="${data.code}" ${authChecked}/> <label for="menu_auth_arr${i.count}">${data.code_name}</label>
						</c:if>												
					</c:forEach>
				</td>
			</tr>			
		</tbody>
	</table>
	
</form:form>

<div class="btn_wrap textAlign_center">
	<a href="menuManage.do" class="btn white">취소</a>
	<c:choose>
    <c:when test="${theForm.act eq 'insertMenu'}">
    	<a href="#" class="btn primary btnSave">등록</a>
    </c:when>
    <c:otherwise>
	    <a href="#" class="btn danger btnDelete">삭제</a>
	    <a href="#" class="btn primary btnSave">수정</a>
    </c:otherwise>
    </c:choose>	
</div>

<!-- 게시판 검색 팝업 -->
<div id="boardConfigPop" class="group_regist popup_wrap" style="display:block;">
	<div class="popup_header">게시판 검색</div>
	<a class="ico ico_close">창닫기</a>
	<div class="popup_body">

		<fieldset>
		<legend></legend>
		
		<table class="table_basic">
			<caption>테이블</caption>
			<colgroup>		
				<col style="width:auto;" />
				<col style="width:auto;" />
				<col style="width:auto;" />
				<col style="width:auto;" />
			</colgroup>
			<thead>
				<tr>
					<th scope="col">선택</th>
					<th scope="col">게시판명</th>
					<th scope="col">게시판타입</th>
					<th scope="col">상태</th>
				</tr>
			</thead>
			<tbody id="boardConfigListArea">
				
			</tbody>
		</table>
		
		<div class="btn_wrap clearfix textAlign_center com-mt10">
			<a href="/siteManage/board/config/boardConfigList.do" class="btn primary" target="_blank">게시판 관리 바로가기</a>
		</div>
		
		</fieldset>
	</div>
</div>
<!-- // 게시판 검색 팝업 -->

<!-- 콘텐츠 검색 팝업 -->
<div id="contentsHtmlPop" class="group_regist popup_wrap" style="display:block;">
	<div class="popup_header">콘텐츠 검색</div>
	<a class="ico ico_close">창닫기</a>
	<div class="popup_body">

		<fieldset>
		<legend></legend>
		
		<table class="table_basic">
			<caption>테이블</caption>
			<colgroup>		
				<col style="width:auto;" />
				<col style="width:auto;" />
				<col style="width:auto;" />
				<col style="width:auto;" />
			</colgroup>
			<thead>
				<tr>
					<th scope="col">선택</th>
					<th scope="col">콘텐츠명</th>
					<th scope="col">상태</th>
				</tr>
			</thead>
			<tbody id="contentsListArea">
				
			</tbody>
		</table>
		
		<div class="btn_wrap clearfix textAlign_center com-mt10">
			<a href="/siteManage/contents/contManage/htmlList.do" class="btn primary" target="_blank">콘텐츠 관리 바로가기</a>
		</div>
		
		</fieldset>
	</div>
</div>
<!-- 콘텐츠 검색 팝업 -->
</c:if>