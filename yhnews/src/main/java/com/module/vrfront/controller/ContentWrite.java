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
public class ContentWrite extends CommonWebUtils {
	private Logger log = LoggerFactory.getLogger(getClass());

	@Resource(name = "messageSourceAccessor")
	private MessageSourceAccessor message;

	// ######   VR360 LIST   #####
	@RequestMapping("/front/vr/myContents/mainList")
	public ModelAndView myContent(HttpServletRequest request, VrSiteDto vrSite) throws Exception{
		if(log.isDebugEnabled())log.debug("[START] " + this.getClass().getName() + ".vr360List()");

		ModelAndView mav = new ModelAndView("empty/frontvr/myContents/mainList");

		if(log.isDebugEnabled())log.debug("[END] " + this.getClass().getName() + ".vr360List()");
		return mav;
	}
}
