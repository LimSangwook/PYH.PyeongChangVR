package com.module.banner.controller;

import java.util.List;
import java.util.Map;

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
import com.common.util.PagingUtil;
import com.module.banner.dto.BannerDto;
import com.module.banner.service.BannerService;
import com.module.main.service.MainService;

@Controller
@RequestMapping("/siteManage/**/banner*")
public class BannerController extends CommonWebUtils{

	private Logger log = LoggerFactory.getLogger(getClass());
	
	@Resource(name = "messageSourceAccessor")
	private MessageSourceAccessor message;
	
	@Autowired
	private MainService mainService;
	
	@Autowired
	private BannerService bannerService;
		
	/**
	 * 배너 목록
	 * @param request
	 * @param BannerDto 
	 * @return ModelAndView
	 * @throws Exception
	 */	
	@RequestMapping
	public ModelAndView bannerList(HttpServletRequest request, BannerDto banner) throws Exception{
		if(log.isDebugEnabled())log.debug("[START] " + this.getClass().getName() + ".bannerList()");
		
		ModelAndView mav = new ModelAndView("siteManage/banner/bannerList");
		
		try {
			List<Map<String,String>> bannerAreaCodeList = mainService.getCommonCodeList("BANNER_AREA");
			if(!bannerAreaCodeList.isEmpty() && StringUtils.isBlank(banner.getArea_code())){
				Map<String, String> data = bannerAreaCodeList.get(0);
				banner.setArea_code(data.get("code"));
			}
			// 배너존목록
			mav.addObject("bannerAreaCodeList", bannerAreaCodeList);			
			mav.addObject("result", bannerService.getBannerList(banner));			
			mav.addObject("theForm", banner);			
			mav.addObject("defaultParameter", getParameter(request,"&","banner_key|area_code"));
			mav.addObject("pageNavigation", PagingUtil.printPageNavi(banner, getParameter(request,"bannerList.do?","banner_key|page")));
			
		} catch (Exception e) {
			if(log.isWarnEnabled())log.warn(e.toString());
		}		
		
		if(log.isDebugEnabled())log.debug("[END] " + this.getClass().getName() + ".bannerList()");
		return mav;
	}
	
	/**
	 * 배너 등록/수정폼
	 * @param request
	 * @param BannerDto 
	 * @return ModelAndView
	 * @throws Exception
	 */	
	@RequestMapping
	public ModelAndView bannerForm(HttpServletRequest request, BannerDto banner) throws Exception{
		if(log.isDebugEnabled())log.debug("[START] " + this.getClass().getName() + ".bannerForm()");
		
		ModelAndView mav = new ModelAndView("siteManage/banner/bannerForm");
		
		try {
			if(StringUtils.isBlank(banner.getArea_code())){
				throw new Exception("bannerAreacode Validate Exception");
			}
			// 배너존목록
			mav.addObject("bannerAreaCodeList", mainService.getCommonCodeList("BANNER_AREA"));
			if(StringUtils.isNotBlank(banner.getBanner_key())){
				BannerDto result = bannerService.getBannerInfo(banner);
				if(result != null){
					result.setAct("update");
					banner = (BannerDto)result.clone();
				} else {
					throw new Exception("banner Not Found..");
				}
			}			
			mav.addObject("theForm", banner);			
			mav.addObject("defaultParameter", getParameter(request,"?","banner_key"));
		} catch (Exception e) {
			if(log.isWarnEnabled())log.warn(e.toString());
			redirectView(mav, message.getMessage("ERROR.NO.DATA"), "bannerList.do");
		}
		
		if(log.isDebugEnabled())log.debug("[END] " + this.getClass().getName() + ".bannerForm()");
		return mav;
	}
	
	/**
	 * 배너 등록/수정
	 * @param request
	 * @param boardDto 
	 * @return
	 * @throws Exception
	 */	
	@RequestMapping(method=RequestMethod.POST)
	public ModelAndView bannerSave(HttpServletRequest request, BannerDto banner) throws Exception{
		if(log.isDebugEnabled())log.debug("[START] " + this.getClass().getName() + ".bannerSave()");
		
		ModelAndView mav = new ModelAndView();		
		String returnPage = "bannerList.do?area_code="+banner.getArea_code();
		
		try {
			if(StringUtils.equals("insert", banner.getAct())){
				bannerService.insertBannerInfo(banner);
			} else if(StringUtils.equals("update", banner.getAct())){				
				returnPage = "bannerForm.do?banner_key="+banner.getBanner_key()+"&area_code="+banner.getArea_code();				
				bannerService.updateBannerInfo(banner);
			} else if(StringUtils.equals("delete", banner.getAct())){				
				bannerService.deleteBannerInfo(banner);
			}
			redirectView(mav, "", returnPage);
		} catch (Exception e) {			
			if(log.isWarnEnabled())log.warn(e.toString());
			redirectView(mav, message.getMessage("ERROR.ACCESS.FAIL"), "bannerList.do");
		}		
		
		if(log.isDebugEnabled())log.debug("[END] " + this.getClass().getName() + ".bannerSave()");
		return mav;
	}
}
