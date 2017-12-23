package com.module.popup.controller;

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
import com.module.popup.dto.PopupDto;
import com.module.popup.service.PopupService;

@Controller
@RequestMapping({"/siteManage/**/popup*","/notifyPopup"})
public class PopupController extends CommonWebUtils{

	private Logger log = LoggerFactory.getLogger(getClass());
	
	@Resource(name = "messageSourceAccessor")
	private MessageSourceAccessor message;
	
	@Autowired
	private PopupService popupService;
	
	/**
	 * 팝업 목록
	 * @param request
	 * @param PopupDto 
	 * @return ModelAndView
	 * @throws Exception
	 */	
	@RequestMapping
	public ModelAndView popupList(HttpServletRequest request, PopupDto popup) throws Exception{
		if(log.isDebugEnabled())log.debug("[START] " + this.getClass().getName() + ".popupList()");
		
		ModelAndView mav = new ModelAndView("siteManage/popup/popupList");
		
		try {
			mav.addObject("result", popupService.getPopupList(popup));			
			mav.addObject("theForm", popup);			
			mav.addObject("defaultParameter", getParameter(request,"&","popup_key"));
			mav.addObject("pageNavigation", PagingUtil.printPageNavi(popup, getParameter(request,"popupList.do?","popup_key|page")));
		} catch (Exception e) {
			if(log.isWarnEnabled())log.warn(e.getMessage());
		}		
		
		if(log.isDebugEnabled())log.debug("[END] " + this.getClass().getName() + ".popupList()");
		return mav;
	}
	
	/**
	 * 팝업 등록/수정폼
	 * @param request
	 * @param PopupDto 
	 * @return ModelAndView
	 * @throws Exception
	 */	
	@RequestMapping
	public ModelAndView popupForm(HttpServletRequest request, PopupDto popup) throws Exception{
		if(log.isDebugEnabled())log.debug("[START] " + this.getClass().getName() + ".popupForm()");
		
		ModelAndView mav = new ModelAndView("siteManage/popup/popupForm");
		
		try {
			if(StringUtils.isNotBlank(popup.getPopup_key())){
				PopupDto result = popupService.getPopupInfo(popup);
				if(result != null){
					result.setAct("update");
					popup = (PopupDto)result.clone();
				} else {
					throw new Exception("popup Data Not Found..");
				}
			}			
			mav.addObject("theForm", popup);			
			
		} catch (Exception e) {
			if(log.isWarnEnabled())log.warn(e.getMessage());
			redirectView(mav, message.getMessage("ERROR.NO.DATA"), "popupList.do");
		}
		
		if(log.isDebugEnabled())log.debug("[END] " + this.getClass().getName() + ".popupForm()");
		return mav;
	}
	
	/**
	 * 팝업 등록/수정
	 * @param request
	 * @param boardDto 
	 * @return
	 * @throws Exception
	 */	
	@RequestMapping(method=RequestMethod.POST)
	public ModelAndView popupSave(HttpServletRequest request, PopupDto popup) throws Exception{
		if(log.isDebugEnabled())log.debug("[START] " + this.getClass().getName() + ".popupSave()");
		
		ModelAndView mav = new ModelAndView();		
		String returnPage = "popupList.do";
		
		try {
			if(StringUtils.equals("insert", popup.getAct())){
				popupService.insertPopupInfo(popup);
			} else if(StringUtils.equals("update", popup.getAct())){				
				returnPage = "popupForm.do?popup_key="+popup.getPopup_key();				
				popupService.updatePopupInfo(popup);
			} else if(StringUtils.equals("delete", popup.getAct())){				
				popupService.deletePopupInfo(popup);
			}
			redirectView(mav, "", returnPage);
		} catch (Exception e) {			
			if(log.isWarnEnabled())log.warn(e.getMessage());
			redirectView(mav, message.getMessage("ERROR.ACCESS.FAIL"), "popupList.do");
		}		
		
		if(log.isDebugEnabled())log.debug("[END] " + this.getClass().getName() + ".popupSave()");
		return mav;
	}
	
	/**
	 * 메인 노출 팝업 정보
	 * @param request
	 * @param PopupDto 
	 * @return ModelAndView
	 * @throws Exception
	 */	
	@RequestMapping(method=RequestMethod.POST)
	public ModelAndView notifyPopup(HttpServletRequest request, PopupDto popup) throws Exception{
		if(log.isDebugEnabled())log.debug("[START] " + this.getClass().getName() + ".notifyPopup()");
		
		ModelAndView mav = new ModelAndView("empty/comm/popup");
		
		try {
			
			mav.addObject("popupInfo", popupService.getPopupInfo(popup));			
			
		} catch (Exception e) {
			if(log.isWarnEnabled())log.warn(e.getMessage());			
		}
		
		if(log.isDebugEnabled())log.debug("[END] " + this.getClass().getName() + ".notifyPopup()");
		return mav;
	}
}
