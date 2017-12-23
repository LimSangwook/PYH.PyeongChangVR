package com.module.vr.controller;

import java.io.File;

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
import org.springframework.web.servlet.ModelAndView;

import com.common.util.CommonWebUtils;
import com.module.vr.dto.VrStyleLinkDto;
import com.module.vr.service.VrStyleService;

@Controller
@RequestMapping("/siteManage/vrmanager/style/link")
public class StyleLinkController extends CommonWebUtils {
	private Logger log = LoggerFactory.getLogger(getClass());
	@Resource(name = "messageSourceAccessor")
	private MessageSourceAccessor message;

	@Autowired
	private VrStyleService vrStyleService;

	// ######   LINK LIST   #####
	@RequestMapping("/linkList")
	public ModelAndView linkList(HttpServletRequest request, VrStyleLinkDto vrStyle) throws Exception{
		if(log.isDebugEnabled())log.debug("[START] " + this.getClass().getName() + ".linkList()");

		ModelAndView mav = new ModelAndView("siteManage/vr/style/linkList");

		try {
			mav.addObject("result", vrStyleService.getVrStyleLinkList(vrStyle));
			mav.addObject("theForm", vrStyle);
		} catch (Exception e) {
			if(log.isWarnEnabled())log.warn(e.getMessage());
		}

		if(log.isDebugEnabled())log.debug("[END] " + this.getClass().getName() + ".linkList()");
		return mav;
	}

	// ######   LINK FORM   #####
	@RequestMapping("/linkForm")
	public ModelAndView linkForm(HttpServletRequest request, VrStyleLinkDto vrStyle) throws Exception{
		if(log.isDebugEnabled())log.debug("[START] " + this.getClass().getName() + ".linkForm()");
		ModelAndView mav = new ModelAndView("siteManage/vr/style/linkForm");

		try {
			if(StringUtils.isNotBlank(vrStyle.getVr_style_link_id())){
				VrStyleLinkDto styleLinkDto = vrStyleService.getVrStyleLinkForm(vrStyle);
				if(styleLinkDto != null){
					vrStyle = (VrStyleLinkDto)styleLinkDto.clone();
					File attachFile = new File(vrStyle.getPath_image());
					vrStyle.setPath_image(attachFile.getName());
					vrStyle.setAct("update");
				} else {
					throw new Exception("Not Found style link..");
				}
			}
			mav.addObject("theForm", vrStyle);
		} catch (Exception e) {
			if(log.isWarnEnabled())log.warn(e.getMessage());
			redirectView(mav, message.getMessage("ERROR.NO.DATA"), "linkList.do");
		}

		if(log.isDebugEnabled())log.debug("[END] " + this.getClass().getName() + ".linkForm()");
		return mav;
	}

	@RequestMapping(method=RequestMethod.POST, value="/linkSave")
	public ModelAndView linkSave(HttpServletRequest request, VrStyleLinkDto vrStyleLinkDto) throws Exception{
		if(log.isDebugEnabled())log.debug("[START] " + this.getClass().getName() + ".linkSave()");

		ModelAndView mav = new ModelAndView();
		String returnPage = "linkList.do";

		try {
			if(StringUtils.equals("insert", vrStyleLinkDto.getAct())){

			    String pathImage = upload(request, vrStyleLinkDto.getLink_file(), "/assets/admin/vr/img");
			    vrStyleLinkDto.setPath_image(pathImage);
				vrStyleService.insertVrStyleLink(vrStyleLinkDto);
			} else if(StringUtils.equals("update", vrStyleLinkDto.getAct())){
				vrStyleService.updateVrStyleLink(vrStyleLinkDto);
			} else if(StringUtils.equals("delete", vrStyleLinkDto.getAct())){
				vrStyleService.deleteVrStyleLink(vrStyleLinkDto);
			}
			redirectView(mav, "", returnPage);
		} catch (Exception e) {
			if(log.isWarnEnabled())log.warn(e.getMessage());
			redirectView(mav, message.getMessage("ERROR.ACCESS.FAIL"), "linkForm.do");
		}

		if(log.isDebugEnabled())log.debug("[END] " + this.getClass().getName() + ".linkSave()");
		return mav;
	}
}
