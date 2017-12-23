package com.module.vrfront.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.common.util.CommonWebUtils;
import com.module.vr.dto.VrSiteDto;
import com.module.vr.service.VrSiteService;

@Controller
public class VR360Controller extends CommonWebUtils {
	private Logger log = LoggerFactory.getLogger(getClass());

	@Resource(name = "messageSourceAccessor")
	private MessageSourceAccessor message;
	@Autowired
    private VrSiteService vrSiteService;
	// ######   VR360 LIST   #####
	@RequestMapping("/front/vr/360vr/mainList")
	public ModelAndView vr360List(HttpServletRequest request, VrSiteDto vrSite) throws Exception{
		if(log.isDebugEnabled())log.debug("[START] " + this.getClass().getName() + ".vr360List()");

		ModelAndView mav = new ModelAndView("frontvr/360/mainList");
		try {
            mav.addObject("result", vrSiteService.getVrSiteList(vrSite));
            mav.addObject("theForm", vrSite);
        } catch (Exception e) {
            if(log.isWarnEnabled())log.warn(e.getMessage());
        }

		if(log.isDebugEnabled())log.debug("[END] " + this.getClass().getName() + ".vr360List()");
		return mav;
	}
}
