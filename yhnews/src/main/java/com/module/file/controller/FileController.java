package com.module.file.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import com.common.util.CommonFileUtils;
import com.common.util.CommonUtils;
import com.module.file.dto.FileDto;
import com.module.file.service.FileService;

@Controller
public class FileController {

	private Logger log = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private FileService fileService;


	
	/**
	 * 이미지 업로드 팝업
	 * @param request
	 * @param boardDto 
	 * @return
	 * @throws Exception
	 */	
	@RequestMapping("attachPhotoPop")
	public ModelAndView attachPhotoPop(HttpServletRequest request) throws Exception{
		if(log.isDebugEnabled())log.debug("[START] " + this.getClass().getName() + ".attachPhotoPop()");
		
		ModelAndView mav = new ModelAndView("empty/comm/attach_photo");
		
		if(log.isDebugEnabled())log.debug("[END] " + this.getClass().getName() + ".attachPhotoPop()");
		return mav;
	}
	
	/**
	 * 사진 파일 업로드
	 * @param params
	 * @param request 
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("uploadPhoto")
	public void uploadPhoto(HttpServletRequest request, HttpServletResponse response) throws Exception{
		if(log.isDebugEnabled())log.debug("[START] " + this.getClass().getName() + ".uploadPhoto()");
		
		try{
			List<Map<String,Object>> fileList = CommonFileUtils.uploadFiles((MultipartHttpServletRequest)request);
			
			StringBuilder sb = new StringBuilder(512);
			for (Map<String, Object> file : fileList) {
				sb.append(file.get("real_file_name")+"|");
				sb.append(file.get("save_file_name")+"|");
				sb.append(file.get("file_path")+"|");
				sb.append(file.get("file_size")+"|");
				sb.append(file.get("image_width_size")+"|");
				sb.append(file.get("image_height_size")+"|");
				sb.append(file.get("file_ext"));
			}
			
		    CommonUtils.printOut(response, "<script>parent.setPhotoInfo('"+sb.toString()+"');</script>");
				        
		}catch(Exception e) {
			e.printStackTrace();
			log.error(e.getMessage());
			CommonUtils.printOut(response, "ERROR | "+e.getMessage());
		}
		
		if(log.isDebugEnabled())log.debug("[END] " + this.getClass().getName() + ".uploadPhoto()");
	}
	
	/**
	 * 공통 파일 업로드
	 * @param params
	 * @param request 
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("upload")
	public void upload(HttpServletRequest request, HttpServletResponse response) throws Exception{
		if(log.isDebugEnabled())log.debug("[START] " + this.getClass().getName() + ".upload()");
		
		try{
			List<Map<String,Object>> fileList = CommonFileUtils.uploadFiles((MultipartHttpServletRequest)request);
			
			StringBuilder sb = new StringBuilder(512);
			for (Map<String, Object> file : fileList) {
				sb.append(file.get("real_file_name")+"|");
				sb.append(file.get("save_file_name")+"|");
				sb.append(file.get("file_path")+"|");
				sb.append(file.get("file_size")+"|");
				sb.append(file.get("image_width_size")+"|");
				sb.append(file.get("image_height_size")+"|");
				sb.append(file.get("file_ext"));
			}
			
		    CommonUtils.printOut(response, "<div id='fileInfo'>"+sb.toString()+"</div>");
				        
		}catch(Exception e) {
			e.printStackTrace();
			log.error(e.getMessage());
			CommonUtils.printOut(response, "ERROR | "+e.getMessage());
		}
		
		if(log.isDebugEnabled())log.debug("[END] " + this.getClass().getName() + ".upload()");
	}
	
	/**
	 * 게시판 파일 다운로드
	 * @param params
	 * @param request 
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("downLoad")
	public void download(FileDto file, HttpServletRequest request, HttpServletResponse response) throws Exception{
		if(log.isDebugEnabled())log.debug("[START] " + this.getClass().getName() + ".download()");
		
		try {
			
			file.setSave_file_name(CommonUtils.utf8(file.getSave_file_name()));
			FileDto fileInfo = fileService.getFileInfo(file);
			if(fileInfo != null) {
				//fileInfo.setReal_file_name(CommonUtils.utf8(fileInfo.getReal_file_name()));
				CommonFileUtils.download(request, response, fileInfo.getFile_path(), fileInfo.getSave_file_name(), fileInfo.getReal_file_name());	
			} else {
				throw new NullPointerException("Not Found File Data.");	
			}
			// 파일다운로드 카운트 증가
			fileService.updateFileDownCount(fileInfo);
			
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e.getMessage());			
			CommonUtils.printOut(response, CommonUtils.fnAlertAndjavascriptFunction("존재하지 않거나 삭제된 파일입니다.", "history.back(-1);"));
		}
		if(log.isDebugEnabled())log.debug("[END] " + this.getClass().getName() + ".download()");
	}
	
}