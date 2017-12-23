<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/comm/taglib.jsp"%>

<c:choose>
<c:when test="${boardConfig.file_count_limit eq 0}">
<c:set var="fileLimitCnt" value="0"/>
</c:when>
<c:otherwise>
<c:set var="fileLimitCnt" value="${boardConfig.file_count_limit - fn:length(fileList)}"/>
</c:otherwise>
</c:choose>

<style>
.btn-ty3 {display:inline-block; font-weight:bold; letter-spacing:-1px; padding:0 20px; text-align:center; vertical-align:7px; height:26px; line-height:24px; border:1px solid #c2c2c2; background:#d8d8d8;}
.addflie-list-area {}
.addflie-list-area .add-flie-list {position:relative; padding-left:120px;}
.addflie-list-area .add-flie-list .thum {position:absolute; width:122px; height:122px;  border:1px solid #ccc; z-index:2; top:0; left:0;}
.addflie-list-area .add-flie-list ul {overflow-y:scroll; border:1px solid #d8d8d8; height:164px;  margin-left: 20px; padding:2px 0 0 0;}
.addflie-list-area .add-flie-list ul li {padding:6px 10px; border:1px solid #eee; margin-top:-1px;}
.addflie-list-area .add-flie-list ul li a.file-name {padding:0 0 0 0; height:18px; line-height:18px;  white-space:nowrap;}
/* .addflie-list-area .add-flie-list ul li:hover,
.addflie-list-area .add-flie-list ul li.on {background:#DDD; color:#fff; text-decoration:none;} */
.addflie-list-area .add-flie-list ul li a.btn-delete {position:absolute; right:5px; top:0;}
.addflie-list-area .add-flie-list ul li a.btn {float:right; font-size:11px; line-height:18px}


.addflie-list-area .file-input-box {float:left; margin-top:10px; position:relative;}
.addflie-list-area .btns a {margin-top:10px;}
.file-input-textbox {}
.file-input-box {position:absolute; right:0; top:0; width:90px; height:26px; overflow:hidden;}
ul,li {list-style:none;padding:0}
/* .file-input-button {cursor:pointer; border:none; width:90px; height:26px; position:absolute; top:0; background:url('../../front/images/btn/btn-find.gif') 0 0 no-repeat;} */
.file-input-hidden {cursor:pointer; font-size:45px; position:absolute; right:0; top:0; opacity:0; filter:alpha(opacity=0); -ms-filter:"alpha(opacity=0)"; -khtml-opacity:0; -moz-opacity:0;}

.mgt30 {margin-top:20px}
.fileUploadLoading {display:none; width:64px;height:64px;/* position: absolute; */ z-index:9999;padding:30px 0 0 30px;}
.thum img {width:120px; height:120px;border:0 !important;}
#px-widget-1 {
height:0px; float:left !important;margin-left:20px !important;margin-top: -30px;
}
</style>
<link rel="stylesheet" href="/assets/plugin/fileupload/css/fileUploader.css" />
<script type="text/javascript" src="/assets/plugin/fileupload/js/jquery.fileUploader.js?v=1"></script>

<table class="table_form">
<caption>게시글 정보</caption>
<colgroup>
	<col style="width:15%;" />
	<col style="width:85%;" />
</colgroup>
<tbody>
<tr>
	<th>첨부파일</th>
	<td>
		<div class="addflie-list-area">	
			<div class="add-flie-list">	
				<span class="thum" id="imagePreview">
					<img src="/assets/plugin/fileupload/img/noimage2.png" alt="썸네일이미지">		
				</span>
				<div id='imageLoadingArea' style='position:absolute; z-index:9000; display:none; background-color:#fff; left:0px; top:0px;opacity:0.7; width:122px;height:122px'>
					<div class="fileUploadLoading"><img src="/assets/plugin/fileupload/img/ajax-file-loader.gif"/><div style="margin-left:8px; margin-top:-40px; width:50px; font-weight:bold; font-size:11px; color:#EF8313;text-align: center;" id="uploadPercent">0%</div></div>
				</div>
				<ul id="fileUploadList" class="fileList">
					<c:forEach var="data" items="${fileList}">
						<c:set var="masterImage" value="${data.file_path}${data.save_file_name}"/>
						<li file_seq="${data.file_key }" imageUrl="${data.file_path}${data.save_file_name}"><a href="javascript:;" class="file-name" >${data.real_file_name} <span><c:if test="${masterImage eq theForm.master_image}">[대표이미지]</c:if></span></a><a href="#" class="btn small delFile" >삭제</a></li>
					</c:forEach>			
				</ul>
			</div>
			<div class="btns">	
					
				<form action="/upload.do" method="post" enctype="multipart/form-data">
					<input type="hidden" id="file_total_cnt" name="file_total_cnt" value="${boardConfig.file_count_limit}"/>
					<input type="hidden" id="file_limit_cnt" value="${fileLimitCnt}"/>
					<input type="hidden" id="file_max_size" name="file_max_size" value="${boardConfig.file_size_limit}"/>
					<input type="hidden" id="permission" name="permission" value="${boardConfig.file_ext_limit}"/>
					<input type="file" name="filename" class="fileUpload" multiple>
				</form>
						
				<script type="text/javascript">
					jQuery(function($){
						$('.fileUpload').fileUploader();
						if (navigator.userAgent.indexOf("MSIE 10") > 0 || navigator.userAgent.indexOf("MSIE 9") > 0) {
				       		$(".fileUpload").bind('mousedown',function(event) {
				         		$(this).trigger('click');
				       		});
				      	}
						$(".fileUpload").css('cursor','pointer');
						$('.ui-widget').css('height:0px; float:left;margin-left:85px;');
					});
				</script>
				
			</div>
		</div>		
	</td>
</tr>
</tbody>
</table>
