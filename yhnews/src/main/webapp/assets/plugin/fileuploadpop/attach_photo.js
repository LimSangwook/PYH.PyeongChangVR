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
	//alert(oFileInfo);
	html[++h] = '<li><a href="/upload'+filePath+saveFileName+'" class="file-name" target="_blank">'+realFileName+'</a>';
	html[++h] = '<span class="ico ico_close delFile" style="cursor:pointer;">삭제</span></li>';
					
	// 첨부파일리스트
	jQuery("#fileUploadList").html(html.join(""));
	// 이미지 미리보기
	jQuery("#imagePreview").html("<img src='/upload/cont"+imageUrl+"' width='238' height='174' />");
	// 첨부파일저장Hidden데이타
	//jQuery("#hiddenSaveFileData").append('<input type="text" name="photoFile" value="'+jQuery.trim(oFileInfo)+'"/>');
	jQuery('#photoFile').val(jQuery.trim(oFileInfo));
	// 첨부파일 감추기	
	jQuery('#photoUploadForm').hide();
} 

jQuery(function(){	
	var oFileUploader = new jindo.FileUploader(jindo.$("uploadInputBox"),{
		sUrl  : '/uploadPhoto.do',	//샘플 URL입니다.
        sCallback : location.href.replace(/\/[^\/]*$/, '') ,//+ '/callback.html',	//업로드 이후에 iframe이 redirect될 콜백페이지의 주소
    	sFiletype : "*.jpg;*.png;*.bmp;*.gif",						//허용할 파일의 형식. ex) "*", "*.*", "*.jpg", 구분자(;)	
    	sMsgNotAllowedExt : 'JPG, GIF, PNG, BMP 확장자만 가능합니다',	//허용할 파일의 형식이 아닌경우에 띄워주는 경고창의 문구
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
	
	jQuery(document).on('click', '.delFile',function(){		
		jQuery("#imagePreview").html('<img src="/assets/plugin/fileupload/img/noimage2.png" alt="썸네일이미지">');
		//jQuery("#hiddenSaveFileData").html('');
		jQuery('#photoFile').val('');
		jQuery('#real_file_name').val('');
		jQuery('#save_file_name').val('');
		jQuery('#file_path').val('');
		jQuery("#fileUploadList").html('');
		// 첨부파일 보이기
		jQuery('#photoUploadForm').show();
	});
});