package com.module.seo.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.common.util.CommonWebUtils;
import com.module.seo.dto.SeoDto;
import com.module.seo.service.SeoService;

@Controller
@RequestMapping("siteManage/**/seo*")
public class SeoController extends CommonWebUtils{

private Logger log = LoggerFactory.getLogger(getClass());
	
	@Resource(name = "messageSourceAccessor")
	private MessageSourceAccessor message;
	
	@Autowired
	private SeoService seoService;
	
	/**
	 * SEO정보 등록/수정 폼
	 * @param request
	 * @param boardDto 
	 * @return
	 * @throws Exception
	 */	
	@RequestMapping
	public ModelAndView seoForm(HttpServletRequest request, SeoDto seo) throws Exception{
		if(log.isDebugEnabled())log.debug("[START] " + this.getClass().getName() + ".seoForm()");
		
		ModelAndView mav = new ModelAndView("siteManage/seo/seoForm");
		
		try {			
			SeoDto result = seoService.getSeoInfo();
			if(result != null){
				seo = (SeoDto)result.clone();
				seo.setAct("update");
			}
			mav.addObject("theForm", seo);
			
		} catch (Exception e) {
			if(log.isWarnEnabled())log.warn(e.getMessage());
		}		
		
		if(log.isDebugEnabled())log.debug("[END] " + this.getClass().getName() + ".seoForm()");
		return mav;
	}
	
	/**
	 * SEO정보 등록/수정
	 * @param request
	 * @param boardDto 
	 * @return
	 * @throws Exception
	 */	
	@RequestMapping(method=RequestMethod.POST)
	public ModelAndView seoSave(HttpServletRequest request, SeoDto seo) throws Exception{
		if(log.isDebugEnabled())log.debug("[START] " + this.getClass().getName() + ".seoSave()");
		
		ModelAndView mav = new ModelAndView();
		
		try {			
			SeoDto result = seoService.getSeoInfo();
			if(result == null){
				seoService.insertSeoInfo(seo);
			} else {
				seoService.updateSeoInfo(seo);
			}
			redirectView(mav, "", "seoForm.do");
			
		} catch (Exception e) {
			if(log.isWarnEnabled())log.warn(e.getMessage());
		}		
		
		if(log.isDebugEnabled())log.debug("[END] " + this.getClass().getName() + ".seoSave()");
		return mav;
	}
}