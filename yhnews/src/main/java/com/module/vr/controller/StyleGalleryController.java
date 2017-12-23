package com.module.vr.controller;

import java.io.File;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.common.util.CommonWebUtils;
import com.module.vr.dto.VrSiteDto;
import com.module.vr.dto.VrStyleGalleryDto;
import com.module.vr.dto.VrStyleGalleryImageDto;
import com.module.vr.service.VrSiteService;
import com.module.vr.service.VrStyleService;

@Controller
@RequestMapping({"/siteManage/vrmanager/style/gallery"})
public class StyleGalleryController extends CommonWebUtils {
	private Logger log = LoggerFactory.getLogger(getClass());
	@Resource(name = "messageSourceAccessor")
	private MessageSourceAccessor message;

	@Autowired
	private VrSiteService vrSiteService;
	@Autowired
	private VrStyleService vrStyleService;

	// ######   GALLERY LIST   #####
	@RequestMapping("/galleryList")
	public ModelAndView galleryList(HttpServletRequest request, VrStyleGalleryDto vrStyle) throws Exception{
		if(log.isDebugEnabled())log.debug("[START] " + this.getClass().getName() + ".galleryList()");

		ModelAndView mav = new ModelAndView("siteManage/vr/style/galleryList");
		try {
			mav.addObject("result", vrStyleService.getVrStyleGalleryList(vrStyle));
			mav.addObject("theForm", vrStyle);
		} catch (Exception e) {
			if(log.isWarnEnabled())log.warn(e.getMessage());
		}

		if(log.isDebugEnabled())log.debug("[END] " + this.getClass().getName() + ".galleryList()");
		return mav;
	}

	// ######   GALLERY FORM   #####
	@RequestMapping("/galleryForm")
	public ModelAndView galleryForm(HttpServletRequest request, VrStyleGalleryDto vrStyleGalleryDto) throws Exception{
		if(log.isDebugEnabled())log.debug("[START] " + this.getClass().getName() + ".galleryForm()");
		ModelAndView mav = new ModelAndView("siteManage/vr/style/galleryForm");

		try {
			if(StringUtils.isNotBlank(vrStyleGalleryDto.getVr_style_gallery_id())){
			    VrStyleGalleryDto result = vrStyleService.getVrStyleGalleryForm(vrStyleGalleryDto);
	            if(result != null){
	                vrStyleGalleryDto = (VrStyleGalleryDto)result.clone();
	                vrStyleGalleryDto.setAct("update");

	                VrStyleGalleryImageDto vrStyleGalleryImageDto = new VrStyleGalleryImageDto();
	                vrStyleGalleryImageDto.setVr_style_gallery_id(vrStyleGalleryDto.getVr_style_gallery_id());
	                List<VrStyleGalleryImageDto> vrStyleGalleryImage = vrStyleService.getVrStyleGalleryImage(vrStyleGalleryImageDto);
	                for (VrStyleGalleryImageDto imageDto : vrStyleGalleryImage) {
	                	File attachFile = new File(imageDto.getPath_image());
	                	imageDto.setPath_image(attachFile.getName());
					}
	                mav.addObject("imgList", vrStyleGalleryImage);
	            } else {
	                throw new Exception("Not Found VR_Site..");
	            }
			}
			mav.addObject("siteList", vrSiteService.getVrSiteList(new VrSiteDto()));
			mav.addObject("theForm", vrStyleGalleryDto);

		} catch (Exception e) {
			if(log.isWarnEnabled())log.warn(e.getMessage());
			redirectView(mav, message.getMessage("ERROR.NO.DATA"), "galleryList.do");
		}

		if(log.isDebugEnabled())log.debug("[END] " + this.getClass().getName() + ".galleryForm()");
		return mav;
	}

	@RequestMapping(method=RequestMethod.POST, value="/gallerySave")
	public ModelAndView gallerySave(HttpServletRequest request, VrStyleGalleryDto vrStyleGalleryDto) throws Exception{
		if(log.isDebugEnabled())log.debug("[START] " + this.getClass().getName() + ".gallerySave()");

		ModelAndView mav = new ModelAndView();
		String returnPage = "galleryList.do";

		try {
			if(StringUtils.equals("insert", vrStyleGalleryDto.getAct())){
				String insertId = vrStyleService.insertVrStyleGallery(vrStyleGalleryDto);
				List<MultipartFile> gallerys = vrStyleGalleryDto.getGallery_file();
				for (MultipartFile gallery : gallerys) {
					String pathImage = upload(request, gallery, "/assets/admin/vr/gallery", vrStyleGalleryDto.getVr_style_gallery_id());
					VrStyleGalleryImageDto galleryImageDto = new VrStyleGalleryImageDto();
					galleryImageDto.setVr_style_gallery_id(insertId);
					galleryImageDto.setPath_image(pathImage);
					vrStyleService.insertVrStyleGalleryImage(galleryImageDto);
				}
			} else if(StringUtils.equals("update", vrStyleGalleryDto.getAct())){
				vrStyleService.updateVrStyleGallery(vrStyleGalleryDto);
			} else if(StringUtils.equals("delete", vrStyleGalleryDto.getAct())){
				vrStyleService.deleteVrStyleGallery(vrStyleGalleryDto);
			}
			redirectView(mav, "", returnPage);
		} catch (Exception e) {
			if(log.isWarnEnabled())log.warn(e.getMessage());
			redirectView(mav, message.getMessage("ERROR.ACCESS.FAIL"), "galleryForm.do");
		}

		if(log.isDebugEnabled())log.debug("[END] " + this.getClass().getName() + ".gallerySave()");
		return mav;
	}
}
