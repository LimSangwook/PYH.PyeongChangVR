function setPhotoInfo(oFileInfo){
	var html=[],h=-1;
	var array = oFileInfo.split("|");	
	var realFileName 			= array[0]; 
	var saveFileName 		= array[1];
	var filePath 				= array[2];				 
	var fileSize 				= array[3];
	var imageWidthSize 		= array[4];
	var imageHeightSize 	= array[5];
	var imageType 			= array[6];				
	var imageUrl				= filePath+saveFileName;
	
	html[++h] = '<li>'+realFileName+' <span class="ico ico_close delFile" style="cursor:pointer;" id="'+saveFileName.replace('.','')+'" file_seq="">삭제</span></li>';
	var fileInput = $('<input type="hidden" name="file" id="input_'+saveFileName.replace('.','')+'" value="'+jQuery.trim(oFileInfo)+'">');
	
	// 첨부파일리스트
	jQuery("#fileUploadList").append(html.join(""));	
	// 첨부파일저장Hidden데이타
	jQuery("#hiddenSaveFileData").append(fileInput);
	/*jQuery("#hiddenSaveFileData").append(
		    jQuery('<input>', {
		        type: 'text',
		        id : 'input_'+saveFileName,
		        name : 'file',
		        value: jQuery.trim(oFileInfo)
		    })
		);*/
} 

jQuery(function(){	
	var oFileUploader = new jindo.FileUploader(jindo.$("uploadInputBox"),{
		sUrl  : '/uploadPhoto.do',	//샘플 URL입니다.
        sCallback : location.href.replace(/\/[^\/]*$/, '') ,//+ '/callback.html',	//업로드 이후에 iframe이 redirect될 콜백페이지의 주소
    	sFiletype : "*.jpg;*.png;*.bmp;*.gif;*.pdf;*.xls;*.xlsx;*.doc;*.docx;*.ppt",						//허용할 파일의 형식. ex) "*", "*.*", "*.jpg", 구분자(;)	
    	sMsgNotAllowedExt : 'JPG, GIF, PNG, BMP, PDF, XLS, DOC, PPT 확장자만 가능합니다',	//허용할 파일의 형식이 아닌경우에 띄워주는 경고창의 문구
    	bAutoUpload : true,									 	//파일이 선택됨과 동시에 자동으로 업로드를 수행할지 여부 (upload 메소드 수행)
    	bAutoReset : true 											// 업로드한 직후에 파일폼을 리셋 시킬지 여부 (reset 메소드 수행)
    }).attach({
    	select : function(oCustomEvent) {	    		
			// 선택된 파일의 형식이 허용되는 경우만 처리 
    		if(oCustomEvent.bAllowed === true){
	    		
	    	}else{		    		
	    		oFileUploader.reset();
	    	}
    	},
    	success : function(oCustomEvent) {	    		
    		var aResult = []; 
    		aResult[0] = oCustomEvent.htResult;	    		
    		oFileUploader.reset();	    		
    	},
    	error : function(oCustomEvent) {	    		
    		alert(oCustomEvent.htResult.errstr);
    	}
    });
	
	// 첨부파일삭제
	jQuery(document).on('click', '.delFile',function(){		
		var id = jQuery(this).attr("id");
		var file_seq = jQuery(this).attr("file_seq");
		//console.log(jQuery("input[name='file']").val());
		//console.log(jQuery("input[id='input_"+id+"']").val());
		//jQuery("input[id='input_"+id+"']").remove();		
		jQuery("#input_"+id).remove();
		jQuery(this).parent().remove();
		if(file_seq != ''){
			jQuery("#hiddenRemoveFileData").append('<input type="hidden" name="remove" value="'+file_seq+'"/>');	
		}		
	});
});