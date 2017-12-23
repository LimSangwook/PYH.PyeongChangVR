/*
*	Class: fileUploader
*	Use: Upload multiple files using jquery
*	Author: John Laniba (http://pixelcone.com)
*	Version: 1.3
*/

(function($) {
	$.fileUploader = {version: '1.3', count: 0};
	$.fn.fileUploader = function(config){
		
		config = $.extend({}, {
			autoUpload: true,
			limit: $("#file_limit_cnt").val(),
			thumb_width_size: $("#thumb_width_size").val(),
			thumb_height_size: $("#thumb_height_size").val(),
			fileMaxSize : $("#file_max_size").val(),
			buttonUpload: '#px-submit',
			buttonClear: '#px-clear',
			buttonDelete: '#px-delete',
			buttonMaster: '#px-master',
			selectFileLabel: '<img src="/assets/plugin/fileupload/img/btn_attach.png" alt="파일찾기"/>',
			allowedExtension: $("#permission").val(),
			allowedImageExtension: 'jpg|jpeg|gif|png',
			timeInterval: [1, 2, 4, 2, 1, 5], //Mock percentage for iframe upload
			percentageInterval: [10, 20, 30, 40, 60, 80],
			
			//Callbacks
			onValidationError: null,	//trigger if file is invalid
			onFileChange: function(){},
			onFileRemove: function(){},
			beforeUpload: function(){}, //trigger after the submit button is click: before upload
			beforeEachUpload: function(){}, //callback before each file has been uploaded ::: returns each Form
			afterUpload: function(){},
			afterEachUpload: function(){} //callback after each file has been uploaded
			
		}, config);
		
		$.fileUploader.count++;
		
		//Multiple instance of a FOrm Container
		var pxUploadForm = 'px-form-' + $.fileUploader.count,		
		pxWidget = 'px-widget-' + $.fileUploader.count,
		pxButton = 'px-button-' + $.fileUploader.count,
		wrapper = ' \
			<div id="'+ pxWidget +'" class="px-widget ui-widget" style="height:0px; float:right;margin-right:85px;"> \
				<div class="ui-helper-clearfix"> \
					<div id="'+ pxUploadForm +'-input" class="px-form-input"></div> \
					<div id="'+ pxButton +'" class="px-buttons"></div> \
				</div> \
				<div id="'+ pxUploadForm +'"></div> \
			</div> \
		',
		pxUploadForm = '#' + pxUploadForm,
		pxUploadFormInput = pxUploadForm + '-input',
		pxButton = '#' + pxButton,
		pxWidget = '#' + pxWidget,
		buttonClearId = null,
	
		itr = 1, //index/itr of file
		isLimit = (config.limit)? true : false,
		limit = parseInt(config.limit),
		fileMaxSize = parseInt(config.fileMaxSize),
		
		e = this, //set e as this
		selector = $(this).selector,
		buttonM = pxButton + ' input, '+ pxButton +' button'; //Accept button as input and as button
		isFile = false, //this is use to hide other inputs in a form
		progress = 0, //percentage of the upload,
		totalForm = 0,
		jqxhr = null, //return object from jquery.ajax,
		timeInterval = config.timeInterval,
		percentageInterval = config.percentageInterval,
		pcount = 0, //progress count to set interval,
		progressTime = null,
		stopUpload = false; //Stop all upload
		
		if (window.FormData) {
			var isHtml5 = true;
		} else {
			var isHtml5 = false;
		}
		
		//Wrap all function that is accessable within the plugin
		var px = {
			
			//Initialize and format data
			init: function(){
				px.form = $(e).parents('form');
				
				//prepend wrapper markup
				px.form.before(wrapper);
				
				//Wrap input button
				$(e).wrap('<div class="px-input-button ui-button ui-widget" />');
				px.form.children('.px-input-button').prepend(config.selectFileLabel);
				px.form.find(config.buttonUpload).appendTo(pxButton);px.form.hide();
				this.printForm();				
				
				// 파일목록 css
				$(document).on('click','.file-name',function(){
					var obj = $(this).parent();
					$(obj).siblings().css("background","");			
					$(obj).siblings().attr("check","false");				
					$(obj).css("background-color","#eee");			
					$(obj).attr("check","true");
					imagePreview($(obj).attr("imageUrl"));
				});
				
				/**
				 * 파일삭제
				 * */
				$(document).on('click','.delFile', function(e){
					e.preventDefault();
					obj = $(this).parent();
					var id = $(obj).attr("id");
					var file_seq = $(obj).attr("file_seq");
					imageUrl = $(obj).attr("imageUrl");				
										
					$(obj).remove();
					$("#"+id+"_input").remove();
					$("#imagePreview").html('<img src="/assets/plugin/fileupload/img/noimage2.png" alt="썸네일이미지">');					
										
					// 첨부파일삭제			
					if(file_seq.isNvl()){
						$("#hiddenRemoveFileData").append('<input type="hidden" name="remove" value="'+file_seq+'"/>');	
					}					
					
					if(imageUrl == $("#master_image").val()) {
						$("#master_image").val("");
					}
					limit++;					
				});	
				
				/**
				 * 대표이미지선택
				 * */				
				$(document).on('click','.masterImageSet',function(){				
					var flag = false;					
					$("#fileUploadList li").each(function() {
						if($(this).attr("check") == "true") {												
							imageUrl = $(this).attr("imageUrl");
							if(px.validateImageFileName(imageUrl) == -1) {
								alert("이미지 파일만 선택가능합니다.");
							} else {
								imageUrl = imageUrl.replace("/data","");
								$("#master_image").val(imageUrl);
								$(this).siblings().find('span').text("");
								$(this).find('span').text("[대표이미지]");
							}							
							flag = true;
						}				
					});
					if(!flag)alert("파일을 선택해 주세요.");					
				});
				
				/**
				 * 에디터영역 이미지 삽입
				 * */				
				$(document).on('click','.editorImageSet',function(){				
					var flag = false;
					$("#fileUploadList li").each(function() {
						if($(this).attr("check") == "true") {												
							imageUrl = $(this).attr("imageUrl");														
							if(px.validateImageFileName(imageUrl) == -1) {
								alert("이미지 파일만 선택가능합니다.");						
							} else {
								style = "";
								width = $(this).find(".imageWidthSize").val();
								/*if(width != ""){
									if(width <= 700){
										style = "width:"+width+"px";	
									} else {
										style = "width:700px";
									}									
								}*/
								//height = $(this).find(".imageHeightSize").val();								
								imageUrl = imageUrl.replace("/data","");
								var imgSrc = '<p><img src="'+uploadDefaultUrl+'/'+uploadImgEditorDir+imageUrl+'" alt="" style="'+style+'"/></p></br/>';
								pasteImage(imgSrc); // 함수위치 /WEB-INF/view/common/editor_head.jsp
								
								formId = $(this).attr("id");
								
								// 본문삽입 이미지는 파일목록 제외... 
								$(this).remove();								
								$("#"+formId+"_input").remove();
								$("#imagePreview").html('<img src="/assets/plugin/fileupload/img/noimage2.png" alt="썸네일이미지">');
								
							}
							flag = true;
						}				
					});
					if(!flag)alert("파일을 선택해 주세요.");					
				});
			},
			
			//Clone, format and append form
			printForm: function(){
				
				var formId = 'pxupload' + itr,
				iframeId = formId + '_frame';
				
				$('<iframe name="'+ iframeId +'"></iframe>').attr({
					id: iframeId,
					src: 'about:blank',
					style: 'display:none'
				}).prependTo(pxUploadFormInput);
				
				px.form.clone().attr({
					id: formId,
					target: iframeId
				}).prependTo(pxUploadFormInput).show();
				
				//Show only the file input
				px.showInputFile( '#'+formId );
				
				//This is not good but i left no choice because live function is not working on IE
				$(selector).change(function() {
					if (isHtml5) {
						html5Change(this);
					} else {
						uploadChange($(this));
					}
				});
			},
			
			//Show only the file input
			showInputFile: function(form) {
				$(pxUploadFormInput).find(form).children().each(function(){
					isFile = $(this).is(':file');
					if (!isFile && $(this).find(':file').length == 0) {
						$(this).hide();
					}
				});
			},
			//Hide file input and show other data
			hideInputFile: function($form) {
				$form.children().each(function(){
					isFile = $(this).is(':file');
					if (isFile || $(this).find(':file').length > 0) {
						$(this).hide();
					} else {
						$(this).show();
					}
				});
			},
			
			//Validate file
			getFileName: function(file) {
				
				if (file.indexOf('/') > -1){
					file = file.substring(file.lastIndexOf('/') + 1);
				} else if (file.indexOf('\\') > -1){
					file = file.substring(file.lastIndexOf('\\') + 1);
				}
				
				return file;
			},
			
			validateFileName: function(filename) {
				var extensions = new RegExp(config.allowedExtension + '$', 'i');
				if (extensions.test(filename)){
					return filename;
				} else {
					return -1;
				}
			},
			
			validateImageFileName: function(filename) {
				var extensions = new RegExp(config.allowedImageExtension + '$', 'i');
				if (extensions.test(filename)){
					return filename;
				} else {
					return -1;
				}
			},
			
			getFileSize: function(file) {
				var fileSize = 0;
				if (file.size > 1024 * 1024) {
					fileSize = (Math.round(file.size * 100 / (1024 * 1024)) / 100).toString() + 'MB';
				} else {
					fileSize = (Math.round(file.size * 100 / 1024) / 100).toString() + 'KB';
				}
				return fileSize;
			},
			
			//clear form data
			clearFormData: function(form) {
				$(form).find(':input').each(function() {
					if (this.type == 'file') {
						$(this).val('');
					}
				});
			}
		}
		
		//initialize
		px.init();
		
		/*
		*	Plugin Events/Method
		*/
		
		/*
		* Html5 file change
		*/
		function html5Change($this) {
			$.each( $this.files, function(index, file){
				uploadChange(file);
			});
			
			afterUploadChange();
		}
		
		/*
		*	Html5 Drag and Drop
		*/
		$.event.props.push('dataTransfer');
		$(pxWidget).bind( 'dragenter dragover', false)
		.bind( 'drop', function( e ) {
			e.stopPropagation();
			e.preventDefault();
			
			html5Change(e.dataTransfer);
			
		});
		
		/*
		*	On Change of upload file
		*/
		function uploadChange($this) {
			
			var $form = $(pxUploadFormInput + ' #pxupload'+ itr);
					
			//validate file
			var filename = (isHtml5)? $this.name : px.getFileName( $this.val() );
			if ( px.validateFileName(filename) == -1 ){
				if ($.isFunction(config.onValidationError)) {
					config.onValidationError($this);
				} else {
					objReplace = /[|]/gi;
					alert (config.allowedExtension.replace(objReplace,", ") + ' 확장자 파일만 등록 가능합니다.');
				}
				$form.find(':file').val('').blur();
				return false;
			}
			//console.debug($this);
			//Limit
			if (limit <= 0) {
				//Your message about exceeding limit
				alert("첨부파일 수량은 최대"+$("#file_total_cnt").val()+"개 입니다.");				
				$form.find(':file').val('').blur();				
				return false;
			}
			
			var file_max_size = fileMaxSize * 1024 * 1024;
			//alert(file_max_size+", "+$this.size);
			if(file_max_size < $this.size && fileMaxSize != 999){
				alert("첨부파일 용량은 최대"+getFileSizeTrans(file_max_size)+" 입니다...");				
				$form.find(':file').val('').blur();				
				return false;
			}
			
			limit = limit - 1;
			
			//remove disabled attr
			//$(buttonM).removeAttr('disabled');
			
			//remove upload text after uploaded
			$('.upload-data', pxUploadForm).each(function() {
				if ( $(this).find('form').length <= 0 ) {
					$(this).remove();
				}
			});
			
			//append size of the file after filename
			if (isHtml5) {
				filename += ' (' + px.getFileSize($this) + ')';
			}
			
			$(pxUploadForm).append(
					$('<div style="display:none;">').attr({
						'class': 'upload-data pending ui-widget-content ui-corner-all',
						id: 'pxupload'+ itr +'_text'
					})
					.data('formId', 'pxupload'+ itr)
					.append(' \
						<span class="filename">'+ filename +'</span> \
						<div class="progress ui-helper-clearfix"> \
							<div class="progressBar" id="progressBar_'+ itr +'"></div> \
							<div class="percentage">0%</div> \
						</div> \
						<div class="status">Pending...</div> \
					')
				);
			
			
			//Store input in form
			$form.data('input', $this);
			
			$form.appendTo(pxUploadForm + ' #pxupload'+ itr +'_text');
			
			//hide the input file
			px.hideInputFile( $form );
			
			//increment for printing form
			itr++;
			
			//print form
			px.printForm();
			
			//Callback on file Changed
			config.onFileChange($this, $form);
			
			if (!isHtml5) {
				afterUploadChange();
			}
		}
		
		/*
		*	After upload change triggers autoupload
		*/
		function afterUploadChange() {
			
			if (config.autoUpload) {
				
				//Display Cancel Button
				toogleCancel(true);
				
				stopUpload = false;
				//Queue and process upload
				uploadQueue();
			}
		}
		
		/*
		*	Queue Upload and send each form to process upload
		*/
		function uploadQueue() {
			
			//stop all upload
			if (stopUpload) {
				return;
			}
			
			totalForm = $(pxUploadForm + ' form').parent('.upload-data').get().length;
			if (totalForm > 0) {
				pendingUpload = $(pxUploadForm + ' form').parent('.upload-data').get(0);
				$form = $(pendingUpload).children('form');
				
				//before upload
				beforeEachUpload( $form );
				
				if (isHtml5) {
					//Upload Using Html5 api
					html5Upload( $form );
				} else {
					
					//upload using iframe
					iframeUpload( $form );
				}
					
			} else {
				config.afterUpload(pxUploadForm);
				
				//Revert Button to clear
				toogleCancel(false);
			}
		}
		
		/*
		*	Process form Upload
		*/
		function html5Upload($form) {
			var file = $form.data('input');
			if (file) {
				var fd = new FormData();
				fd.append($form.find(selector).attr('name'), file);
				//get other form input and append to formData
				$form.find(':input').each(function() {
					if (this.type != 'file') {
						fd.append($(this).attr('name'), $(this).val());
					}
				});
				$("#imagePreview").html('<img src="/assets/plugin/fileupload/img/noimage2.png" alt="썸네일이미지">');
				$("#imageLoadingArea").show();
				$(".fileUploadLoading").show();
				
				//Upload using jQuery AJAX
				jqxhr = $.ajax({
					url: $form.attr('action'),
					data: fd,
					cache: false,
					contentType: false,
					processData: false,
					type: 'POST',
					xhr: function() {
						var req = $.ajaxSettings.xhr();
						if (req) {							
							req.upload.addEventListener('progress',function(ev){
								//Display progress Percentage
								//console.log(ev.loaded);
								progress = Math.round(ev.loaded * 100 / ev.total);
								if(progress < 100){
									$('#uploadPercent').text(progress.toString() + '%');	
								} else {
									$('#uploadPercent').text('로딩중');
								}								
							}, false);
						}			
						return req;
					}
				})
				.success(function(data) {
					//console.log(data);
					data = data.replace("<div id='fileInfo'>","");
					data = data.replace("</div>","");					
					afterEachUpload($form.attr('id'), data );
				})
				.error(function(jqXHR, textStatus, errorThrown) {					
					afterEachUpload($form.attr('id'), null, textStatus, errorThrown );
				})
				.complete(function(jqXHR, textStatus) {		
					$("#imageLoadingArea").hide();
					$(".fileUploadLoading").hide();
					uploadQueue();
				});
			}
			
			$form.remove();
		}
		
		/*
		*	Iframe Upload Process
		*/
		function iframeUpload($form) {
			$("#imagePreview").html('<img src="/assets/plugin/fileupload/img/noimage2.png" alt="썸네일이미지">');
			$("#imageLoadingArea").show();
			$(".fileUploadLoading").show();
			pcount = 0;
			dummyProgress();
			$form.submit();
			
			var id = pxWidget + ' #' + $form.attr('id');
			$(id +'_frame').load(function(){
				
				data = $(this).contents().find('#fileInfo').html();
				afterEachUpload($form.attr('id'), data);

				$("#imageLoadingArea").hide();
				$(".fileUploadLoading").hide();
				
				clearTimeout ( progressTime );
				progress = 100;
				$('#uploadPercent').text(progress.toString() + '%');
				
				uploadQueue();
				
			});
		}
		
		/*
		*	Show the progress bar to the user
		*/
		function dummyProgress() {
			
			if (percentageInterval[pcount]) {
				progress = percentageInterval[pcount] + Math.floor( Math.random() * 5 + 1 );				
				$('#uploadPercent').text(progress.toString() + '%');
			}
			
			if (timeInterval[pcount]) {
				progressTime = setTimeout(function(){
					dummyProgress();
				}, timeInterval[pcount] * 1000);
			}
			
			pcount++;
		}
		
		/*
		*	before Upload
		*/
		function beforeAllUpload() {
			//trigger before upload callback
			$continue = config.beforeUpload(e, pxButton);
			if ($continue === false) {			
				return false;
			}
			
			//Show Cancle Button
			toogleCancel(true);
			
			//process and queue upload
			uploadQueue();
		}
		
		/*
		* Before Each file is uploaded
		*/
		function beforeEachUpload($form) {
			
			config.beforeEachUpload($form);
			
		}
		
		/**
		 * 첨부파일정보 
		 * */
		function setFileInfo(formId, data) {
			//console.debug("data : "+data);
			//alert(formId);
			if(data != null) {
				array = data.split("|");
				var html=[],h=-1;
				var realFileName 		= array[0]; 
				var saveFileName 		= array[1];
				var filePath 				= array[2];				 
				var fileSize 				= array[3];
				var imageWidthSize 	= array[4];
				var imageHeightSize 	= array[5];
				var imageType 			= array[6];				
				var imageUrl				= filePath+saveFileName;
								
				html[++h] = '<li id="'+formId+'" file_seq="" imageUrl="'+imageUrl+'"><a href="javascript:;" class="file-name" >'+realFileName+' ('+getFileSizeTrans(fileSize)+') <span></span></a>';
				// 이미지 파일인 경우
				/*if( px.validateImageFileName(imageUrl) != -1) {
					//html[++h] = '가로사이즈 : <input type="text" value="'+imageWidthSize+'" class="imageWidthSize" style="width:40px"/>px / 세로사이즈 : <input type="text" class="imageWidthSize" value="'+imageHeightSize+'" style="width:40px"/>px';	
					html[++h] = '가로사이즈  : <input type="text" value="'+imageWidthSize+'" class="imageWidthSize" style="width:40px" maxlength="3"/>px(최대/700px)';
				}*/
				html[++h] = '<a href="#" class="btn small delFile" style="cursor:pointer;">삭제</a></li>';
								
				// 첨부파일리스트
				$("#fileUploadList").append(html.join(""));				
				// 첨부파일저장Hidden데이타
				$("#hiddenSaveFileData").append('<input type="hidden" id="'+formId+'_input" name="file" value="'+$.trim(data)+'"/>');
				// 이미지미리보기
				imagePreview(imageUrl);
				// 파일목록css
				//fileListStyleReset();
			}
		}
		
		/**
		 * 이미지미리보기
		 * */
		function imagePreview(imageUrl) {
			if( px.validateImageFileName(imageUrl) == -1) {
				$("#imagePreview").html('<img src="/assets/plugin/fileupload/img/noimage2.png" alt="썸네일이미지">');
			} else {	
				if(imageUrl.indexOf('dir_') != -1){
					imageUrl = imageUrl.replace('/origin/','/large/large_');
					$("#imagePreview").html("<img src='/upload/"+imageUrl+"' width='120' height='70'/>");
				} else {
					$("#imagePreview").html("<img src='/upload/thumb"+imageUrl+"' width='120' height='70'/>");	
				}
			}
		}
		
		/**
		 * 파일크기가져오기
		 * */
		function getFileSizeTrans(fileSize) {			
			if (fileSize > 1024 * 1024) {
				fileSize = (Math.round(fileSize * 100 / (1024 * 1024)) / 100).toString() + 'MB';
			} else {
				fileSize = (Math.round(fileSize * 100 / 1024) / 100).toString() + 'KB';
			}
			return fileSize;
		}
		
		/*
		* After Each file is uploaded
		*/
		function afterEachUpload(formId, data, status, errorThrown) {
						
			fileData = data;			
			fileId = formId;
			
			if (data) {
				data = $('<div>').append(data);
				status = $(data).find('#status').text();
			}
			
			formId = pxWidget + ' #' + formId;
			$uploadData = $(formId + '_text');
			
			if (status == 'success'){
				$uploadData.removeClass('uploading').addClass('success');
				$uploadData.children('.status').html( $(data).find('#message').text() );
				
			} else if (status == 'error'){
				
				$uploadData.removeClass('uploading').addClass('error');
				
				//if client side error other display error from backend
				if (errorThrown) {
					$uploadData.children('.status').html( errorThrown );
				} else {
					$uploadData.children('.status').html( $(data).find('#message').text() );
				}
				
			} else if (status == 'abort') {
				
				$uploadData.removeClass('uploading').addClass('cancel');
				
				$uploadData.children('.status').html( 'Cancelled' );
			}
			
			$uploadData.find('.cancel').removeClass('cancel').addClass('delete').attr('title', 'Delete');
			
			//hide progress bar
			
			// 주석 $uploadData.find('.progress').fadeOut();
			
			//trigger after each upload
			config.afterEachUpload(data, status, $uploadData);
			
			$(formId).remove();
			$(formId + '_frame').remove();
			
			// 파일정보셋팅
			if(fileData == undefined || fileData == null || fileData == ''){
				alert('일시적인 오류가 발생했습니다.');					
			} else {
				if(fileData.indexOf("ERROR |") == -1) {
					setFileInfo(fileId, fileData);
				} else {
					alert(fileData);
					limit++;
				}
			}
		}
		
		/*
		*	Toggle Cancel/Delete button
		*/
		function toogleCancel(cancel) {
			
			if (cancel) {
				//store button clear id
				buttonClearId = $(config.buttonClear, pxButton).attr('id');
				//Cancel Button
				$(config.buttonClear, pxButton).attr({ id: 'px-cancel', title: 'Cancel' });
			} else {
				//Clear button
				$('#px-cancel', pxButton).attr({ id: buttonClearId, title: 'Clear' });
			}
		}
		
		/*
		*	Onlick submit button: start upload
		*/
		$(config.buttonUpload, pxButton).click(function(){
			
			stopUpload = false;
			
			beforeAllUpload();
		});
		
		/*
		* Individual Upload
		*/
		//$('.upload', pxUploadForm).live('click', function(){
		$(pxUploadForm).on('click','.upload',function(){
			
			$form = $(this).parents('.upload-data').children('form');
			if ($form.length > 0) {
				
				//Show Cancle Button
				toogleCancel(true);
								
				//before upload
				
				beforeEachUpload( $form );
				
				if (isHtml5) {
					//Upload Using Html5 api
					html5Upload( $form );
				} else {
					
					//upload using iframe
					iframeUpload( $form );
				}
				
				stopUpload = true;
			}
		});		
		
		
		return this;
	}
})(jQuery);