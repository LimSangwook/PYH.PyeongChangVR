var main = {
	init : function(){
		
	},
	params : {
		pageSize : 4
	,	noticeBoardId : ""
	,	noticeViewAddress : ""
	,	newsLetterBoardId : ""
	,	pressBoardId : ""
	,	referenceBoardId : ""
	},
	// 공지사항목록
	noticeList : function(){
		var params = {board_id : this.params.noticeBoardId, pageSize : this.params.pageSize}
		ajaxCall(params,'/front/boardListJson.do');
		ajaxRes.success(function(result){
			var html=[], h=-1;
			if(result.RESULT_CODE == 'SUCCESS'){
				$(result.result).each(function(i,data){
					html[++h] = '<li><a href="'+main.params.noticeViewAddress+'?board_key='+data.board_key+'">'+data.title+'</br>'+data.displayRegDate+'</a></li>';					
				});
				// 공지사항목록
				if(h > -1){
					$('#noticeDataArea').html(html.join(''));	
				} else {
					$('#noticeDataArea').html('<li>데이터가 없습니다.</li>');
				}									
			}						
		});
	},
	// 메인 하단 공지사항 목록
	noticeListFoot : function(){
		var params = {board_id : this.params.noticeBoardId, pageSize : this.params.pageSize}
		ajaxCall(params,'/front/boardListJson.do');
		ajaxRes.success(function(result){
			var html=[], h=-1;
			if(result.RESULT_CODE == 'SUCCESS'){
				$(result.result).each(function(i,data){
					html[++h] = '<tr><th><b>25</b><p>17.09</p></th><td><a href="'+main.params.noticeViewAddress+'?board_key='+data.board_key+'">'+data.title+'<span class="new_ico">N</span><span class="day_sec">'+data.displayRegDate+'</span></a></td>';					
				});
				// 공지사항목록
				if(h > -1){
					$('#service_notice').html(html.join(''));	
				} else {
					$('#service_notice').html('<li>데이터가 없습니다.</li>');
				}									
			}						
		});
	},
	// 메인 하단 자료실 목록
	referenceList : function(){
		var params = {board_id : this.params.referenceBoardId, pageSize : this.params.pageSize}
		ajaxCall(params,'/front/boardListJson.do');
		ajaxRes.success(function(result){
			var html=[], h=-1;
			if(result.RESULT_CODE == 'SUCCESS'){
				$(result.result).each(function(i,data){
					html[++h] = '<tr><th><b>25</b><p>17.09</p></th><td><a href="'+main.params.referenceViewAddress+'?board_key='+data.board_key+'">'+data.title+'<span class="new_ico">N</span><span class="day_sec">'+data.displayRegDate+'</span></a></td>';					
				});
				// 공지사항목록
				if(h > -1){
					$('#service_reference').html(html.join(''));	
				} else {
					$('#service_reference').html('<li>데이터가 없습니다.</li>');
				}									
			}						
		});
	},
	// 뉴스레터
	newsLetterList : function(){
		var params = {board_id : this.params.newsLetterBoardId, pageSize : '3'}
		ajaxCall(params,'/front/boardListJson.do');
		ajaxRes.success(function(result){
			var html=[], h=-1;			
			if(result.RESULT_CODE == 'SUCCESS'){
				// 첫번째 글 이미지 포함
				$(result.result).each(function(i,data){
					if(i == 0){
						html[++h] = '<div class="special">';
						html[++h] = '	<span><img src="/assets/front/images/main/maIn_newsletter.png" alt="" width="74" height="96"></span>';
						html[++h] = '	<dl>';
						html[++h] = '	<dt><a href="/front/notify/newsletter/boardView.do?board_key='+data.board_key+'">'+data.title+'</a></dt>';
						html[++h] = '	<dd>'+data.summary+'</dd>';
						html[++h] = '	<dd class="date">'+data.displayRegDate+'</dd>';
						html[++h] = '	</dl>';
						html[++h] = '</div>';
					}									
				});
				html[++h] = '<ul>';
				$(result.result).each(function(i,data){
					if(i > 0){
						html[++h] = '<li><span class="subject"><a href="/front/notify/newsletter/boardView.do?board_key='+data.board_key+'">'+data.title+'</a></span><span class="date">'+data.displayRegDate+'</span></li>';
					}									
				});
				html[++h] = '</ul>';
				// 뉴스레터목록
				if(result.result.length > 0){
					$('#newsLetterDataArea').append(html.join(''));	
				} else {
					$('#newsLetterDataArea').append('<li>데이터가 없습니다.</li>');
				}									
			}						
		});
	},
	// 보도자료
	pressBoardList : function(){
		var params = {board_id : this.params.pressBoardId, pageSize : '3'}
		ajaxCall(params,'/front/boardListJson.do');
		ajaxRes.success(function(result){
			var html=[], h=-1;			
			if(result.RESULT_CODE == 'SUCCESS'){
				// 첫번째 글 이미지 포함
				$(result.result).each(function(i,data){
					if(i == 0){
						html[++h] = '<div class="special">';
						html[++h] = '	<span><img src="/assets/forum/images/main/maIn_bodo.png" alt="" width="74" height="96"></span>';
						html[++h] = '	<dl>';
						html[++h] = '	<dt><a href="/forum/notifyForum/press/boardView.do?board_key='+data.board_key+'">'+data.title+'</a></dt>';
						html[++h] = '	<dd>'+data.summary+'</dd>';
						html[++h] = '	<dd class="date">'+data.displayRegDate+'</dd>';
						html[++h] = '	</dl>';
						html[++h] = '</div>';
					}									
				});
				html[++h] = '<ul>';
				$(result.result).each(function(i,data){
					if(i > 0){
						html[++h] = '<li><span class="subject"><a href="/forum/notifyForum/press/boardView.do?board_key='+data.board_key+'">'+data.title+'</a></span><span class="date">'+data.displayRegDate+'</span></li>';
					}									
				});
				html[++h] = '</ul>';
				// 뉴스레터목록
				if(result.result.length > 0){
					$('#pressBoardDataArea').append(html.join(''));	
				} else {
					$('#pressBoardDataArea').append('<li>데이터가 없습니다.</li>');
				}									
			}						
		});
	}
};