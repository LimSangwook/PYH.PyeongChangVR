<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/comm/taglib.jsp"%>

<script type="text/javascript">
nhn.husky.EZCreator.createInIFrame({
	oAppRef: oEditors,
	elPlaceHolder: "${editorFieldName}",
	sSkinURI: "/assets/seditor/SmartEditor2Skin.html", // 사진업로드 버튼 활성화	
	htParams : {
		bUseToolbar : true,				// 툴바 사용 여부 (true:사용/ false:사용하지 않음)
		bUseVerticalResizer : true,		// 입력창 크기 조절바 사용 여부 (true:사용/ false:사용하지 않음)
		bUseModeChanger : true,			// 모드 탭(Editor | HTML | TEXT) 사용 여부 (true:사용/ false:사용하지 않음)			
		aAdditionalFontList : aAdditionalFontSet,		// 추가 글꼴 목록
		fOnBeforeUnload : function(){
			//alert("완료!");
			
		}
	}, //boolean
	fOnAppLoad : function(){
		var sDefaultFont = '나눔고딕';
		var nFontSize = 12;
		oEditors.getById["${editorFieldName}"].setDefaultFont(sDefaultFont, nFontSize);
		
		//setDefaultFont();
	},
	fCreator: "createSEditor2"
});
</script>