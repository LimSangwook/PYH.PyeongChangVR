var cal = {
		init : function(){
			
			if(this.params.searchYear.isNvl() && this.params.searchMonth.isNvl()){
				this.calendar(this.params.searchYear, this.params.searchMonth);
			} else {
				var objnow = new Date();
				this.calendar(objnow.getFullYear(), objnow.getMonth());	
			}
			
		},
		params : {
			arrweekDay : new Array("일", "월", "화", "수", "목", "금", "토")
		,	arrLstMonth : new Array("01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12")
		,	arrLstDay : new Array(31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31)
		,	boardId : ""
		,	linkAddress : ""
		,	museumNo : ""
		,	menuCode : ""
		,	searchYear : ""
		,	searchMonth : ""
		},
		calendar : function(year, month){			
			var nYear = year;
			var nMonth = month;			
			var objnow = new Date();
			
			if (((nYear % 4 == 0) && (nYear % 100 != 0)) || (nYear % 400 == 0)) {
				cal.params.arrLstDay[1] = 29;
			} else {
				cal.params.arrLstDay[1] = 28;
			}
			
			var ojbMonth = objnow.getMonth();	
			var ndate = objnow.getDate();
			nMonth = parseInt(nMonth);
			if (nMonth > 11){
				nMonth = 0;
				nYear = nYear + 1;
			}else if (nMonth < 0){
				nMonth = 11;
				nYear = nYear - 1;
			};
				
			var firstTmp = new Date(nYear, nMonth, 1);
			var nFstDay = firstTmp.getDay() + 1;
			var strHtml = '';	
			//var today = (new Date()).yyyymmdd();
			
			strHtml += '<div class="calendar_month">';
			strHtml += '<a href="#" class="ico ico_prev" onclick="cal.calendar('+nYear+',' + (nMonth - 1) +'); return false;">이전달</a>';
			strHtml += '<p>'+nYear+'년 '+cal.params.arrLstMonth[nMonth]+'월</p>';
			strHtml += '<a href="#" class="ico ico_next" onclick="cal.calendar('+nYear+',' + (nMonth + 1) +'); return false;">다음달</a>';
			strHtml += '</div>';
			
			var params = {board_id : this.params.boardId, search_year : nYear, search_month : cal.params.arrLstMonth[nMonth]}
			ajaxCall(params,'boardScheduleListJson.do');
			ajaxRes.success(function(result){
				
				if(result.RESULT_CODE == 'SUCCESS'){
					//console.log(result.RESULT_DATA);
					strHtml += '<table class="table_calendar"><caption>달력</caption>';
					strHtml += '<colgroup><col width="14.28%"><col width="14.28%"><col width="14.28%"><col width="14.28%"><col width="14.28%"><col width="14.28%"><col width="14.28%"></colgroup>';
					strHtml += '<thead><tr>';
					for (var i = 0; i < 7; i++){
						strHtml += '<th class="' + (i == 0 ? "sun" : (i == 6 ? "sat" : "")) + '">' + cal.params.arrweekDay[i] + '</th>';
					}
					strHtml += '</tr></thead>';
					var ndays = 1;
					var strnbsp = 1;
				 	var nDate = '';
					for (var i = 1; i <= Math.ceil((cal.params.arrLstDay[nMonth] + nFstDay - 1) / 7); ++i) {
						strHtml += '<tr>';

						for (var j = 1; j <= 7; j++) {
							if (ndays <= cal.params.arrLstDay[nMonth]) {
								if (strnbsp < nFstDay) {
									strHtml += '<td class=\"none\">&nbsp;</td>';
									strnbsp++;
								} else {
									var className = '';
									//오늘날짜 체크
									if (ndays == ndate && ojbMonth == nMonth) {
										className += 'today ';
									} 
									//일요일, 토요일 체크
									if(j == 1) { 
										className += 'sun';
									} else if(j == 7) { 
										className += 'sat';
									}							
									calMonth = nMonth+1;						
									nDate = nYear+""+(calMonth<10?"0"+calMonth:calMonth)+""+(ndays<10?"0"+ndays:ndays);
									//console.log(nYear+""+calMonth+""+ndays +", "+today+","+nDate)
									
									strHtml += '<td class="'+ className +'"><span>' + ndays + '</span>';									
									
									$(result.RESULT_DATA).each(function(i,data){
										//console.log(nDate+', '+data.start_day.replace(/-/g,'')+', '+data.end_day.replace(/-/g,''))
										if(parseInt(nDate) >= parseInt(data.start_day.replace(/-/g,'')) && parseInt(nDate) <= parseInt(data.end_day.replace(/-/g,''))){
											strHtml += '<a href="'+cal.params.linkAddress+'?board_key='+data.board_key+'&museum_no='+cal.params.museumNo+'&menu_code='+cal.params.menuCode+'&searchYear='+nYear+'&searchMonth='+nMonth+'">'+data.title+'</a>';														
										}
									});
										
									strHtml += '</td>';
									
									ndays++;
								}
							} else { 
								strHtml += '<td class="none">&nbsp;</td>';
							}
						};
						strHtml += '</tr>';
					};
					strHtml += '</table>';
					$('#calenda').html(strHtml);
					
				}						
			});
			
		}
		
};