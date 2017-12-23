<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="ko">
<head>
<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0, user-scalable=no" />
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<meta http-equiv="Content-Script-Type" content="text/javascript">
<meta http-equiv="Content-Style-Type" content="text/css">
<title>이미지 첨부하기</title>
<style type="text/css">

/* Common */
body,p,h1,h2,h3,h4,h5,h6,ul,ol,li,dl,dt,dd,table,th,td,form,fieldset,legend,input,textarea,button,select{margin:0;padding:0}
body,input,textarea,select,button,table{font-family:'돋움',Dotum,Helvetica,sans-serif;font-size:12px}
img,fieldset{border:0}
ul,ol{list-style:none}
em,address{font-style:normal}
a{text-decoration:none}
a:hover,a:active,a:focus{text-decoration:underline}

/* Contents */
.blind{visibility:hidden;position:absolute;line-height:0}
#pop_wrap{width:383px}
#pop_header{height:26px;padding:14px 0 0 20px;border-bottom:1px solid #ededeb;background:#f4f4f3}
.pop_container{padding:11px 20px 0}
#pop_footer{margin:21px 20px 0;padding:10px 0 16px;border-top:1px solid #e5e5e5;text-align:center}
h1{color:#333;font-size:14px;letter-spacing:-1px}
.btn_area{word-spacing:2px}
.pop_container .drag_area{overflow:hidden;overflow-y:auto;position:relative;width:341px;height:129px;margin-top:4px;border:1px solid #eceff2}
.pop_container .drag_area .bg{display:block;position:absolute;top:0;left:0;width:341px;height:129px;background:#fdfdfd url(/assets/seditor/sample/photo_uploader/img/bg_drag_image.png) 0 0 no-repeat}
.pop_container .nobg{background:none}
.pop_container .bar{color:#e0e0e0}
.pop_container .lst_type li{overflow:hidden;position:relative;padding:7px 0 6px 8px;border-bottom:1px solid #f4f4f4;vertical-align:top}
.pop_container :root .lst_type li{padding:6px 0 5px 8px}
.pop_container .lst_type li span{float:left;color:#222}
.pop_container .lst_type li em{float:right;margin-top:1px;padding-right:22px;color:#a1a1a1;font-size:11px}
.pop_container .lst_type li a{position:absolute;top:6px;right:5px}
.pop_container .dsc{margin-top:6px;color:#666;line-height:18px}
.pop_container .dsc_v1{margin-top:12px}
.pop_container .dsc em{color:#13b72a}
.pop_container2{padding:46px 60px 20px}
.pop_container2 .dsc{margin-top:6px;color:#666;line-height:18px}
.pop_container2 .dsc strong{color:#13b72a}
.upload{margin:0 4px 0 0;_margin:0;padding:6px 0 4px 6px;border:solid 1px #d5d5d5;color:#a1a1a1;font-size:12px;border-right-color:#efefef;border-bottom-color:#efefef;length:300px;}
:root  .upload{padding:6px 0 2px 6px;}
</style>

<script src="/assets/admin/js/jquery-1.12.4.min.js" type="text/javascript"></script>

<!-- ImageUploader  -->
<script type="text/javascript" src="/assets/plugin/fileuploadpop/jindo.min.js" charset="utf-8"></script>
<script type="text/javascript" src="/assets/plugin/fileuploadpop/jindo.fileuploader.js" charset="utf-8"></script>
<script type="text/javascript" src="/assets/plugin/fileuploadpop/attach_photo.js" charset="utf-8"></script>
<!--// ImageUploader  -->

<script>
jQuery(function(){
	var imageArea = '${param.imageArea}';
	var isEditor = '${param.isEditor}';
	jQuery('#btn_confirm').on('click',function(){
		if(jQuery('#photoFile').val() != ''){
			if(isEditor == 'Y'){
				opener.parent.pasteEditorImageCustom(imageArea, jQuery('#photoFile').val());
			} else {
				opener.setPhotoInfo(imageArea, jQuery('#photoFile').val());	
			}
		}
		self.close();
	});
});
</script>
</head>
<body>
<input type="hidden" id="photoFile"/>
<div id="pop_wrap">
	<!-- header -->
    <div id="pop_header">
        <h1>이미지 첨부하기</h1>
    </div>
    <!-- //header -->
    <!-- container -->
    
	<div id="pop_container2" class="pop_container2">
    	<!-- content -->
    	<div id="imagePreview">
			<img src="/assets/plugin/fileupload/img/noimage2.png" width="238" height="174" alt="썸네일이미지">	
		</div>
			
		<form id="editor_upimage" name="editor_upimage"  method="post" enctype="multipart/form-data">
        <div id="pop_content2">
			<input type="file" class="upload" id="uploadInputBox" name="Filedata">
            <p class="dsc" id="info"><strong>10MB</strong>이하의 이미지 파일만 등록할 수 있습니다.<br>(JPG, GIF, PNG, BMP)</p>
        </div>
		</form>
        <!-- //content -->
    </div>

    <!-- //container -->
    <!-- footer -->
    <div id="pop_footer">
	    <div class="btn_area">
            <a href="#"><img src="/assets/seditor/sample/photo_uploader/img/btn_confirm2.png" width="49" height="28" alt="확인" id="btn_confirm"></a>
            <a href="#"><img src="/assets/seditor/sample/photo_uploader/img/btn_cancel.png" width="48" height="28" alt="취소" onclick="self.close()"></a>
        </div>
    </div>
    <!-- //footer -->
</div>

</body>
</html>