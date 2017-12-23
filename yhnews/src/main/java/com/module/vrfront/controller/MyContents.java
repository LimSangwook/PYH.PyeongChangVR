package com.module.vrfront.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.common.util.CommonWebUtils;
import com.module.vr.dto.VrSiteDto;

@Controller
public class MyContents extends CommonWebUtils {
	private Logger log = LoggerFactory.getLogger(getClass());

	@Resource(name = "messageSourceAccessor")
	private MessageSourceAccessor message;

	// ######   VR360 LIST   #####
	@RequestMapping("/front/vr/contentWrite")
	public ModelAndView contentWrite(HttpServletRequest request, VrSiteDto vrSite) throws Exception{
		if(log.isDebugEnabled())log.debug("[START] " + this.getClass().getName() + ".contentWrite()");

		ModelAndView mav = new ModelAndView("frontvr/form/content_write");

		if(log.isDebugEnabled())log.debug("[END] " + this.getClass().getName() + ".contentWrite()");
		return mav;
	}
}
