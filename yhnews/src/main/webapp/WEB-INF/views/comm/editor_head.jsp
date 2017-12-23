<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<script type="text/javascript" src="/assets/seditor/js/HuskyEZCreator.js" charset="utf-8"></script>
<script type="text/javascript">
//추가 글꼴 목록
var aAdditionalFontSet = [["NanumGothic", "나눔고딕"],["NanumMyeongjo", "나눔명조"]];
var oEditors = [];
function pasteImage(imageHtml){
	oEditors.getById["content"].exec("PASTE_HTML", [imageHtml]);
}
function pasteEditorImageCustom(imageArea, oFileInfo){	
	var array = oFileInfo.split("|");	
	var realFileName 			= array[0];
	var saveFileName 		= array[1];
	var filePath 				= array[2];
	var imageUrl				= filePath+saveFileName;
	// uploadImgEditorDir
	var imageHtml = '<p><img src="'+uploadDefaultUrl+'/'+uploadImgEditorDir+imageUrl+'" title="'+realFileName+'"/></p>';	
	oEditors.getById[imageArea].exec("PASTE_HTML", [imageHtml]);
}
</script>