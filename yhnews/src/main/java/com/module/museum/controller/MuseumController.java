package com.module.museum.controller;

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
import com.module.museum.dto.MuseumDto;
import com.module.museum.dto.MuseumMenuContDto;
import com.module.museum.service.MuseumService;
import com.module.user.dto.UserDto;

@Controller
@RequestMapping({"/siteManage/**/museum*"})
public class MuseumController extends CommonWebUtils{

	private Logger log = LoggerFactory.getLogger(getClass());
	
	@Resource(name = "messageSourceAccessor")
	private MessageSourceAccessor message;
	
	@Autowired
	private MuseumService museumService;	
	
	
	/**
	 * 박물관 등록/수정폼
	 * @param request
	 * @param MuseumDto 
	 * @return ModelAndView
	 * @throws Exception
	 */	
	@RequestMapping
	public ModelAndView museumForm(HttpServletRequest request, MuseumDto museum) throws Exception{
		if(log.isDebugEnabled())log.debug("[START] " + this.getClass().getName() + ".museumForm()");
		
		ModelAndView mav = new ModelAndView("siteManage/museum/museumForm");
		
		try {
			if(StringUtils.isNotBlank(museum.getMuseum_no())){
				MuseumDto result = (MuseumDto)museumService.getMuseumInfo(museum.getMuseum_no());
				if(result != null){
					result.setAct("update");
					museum = (MuseumDto)result.clone();
				} else {
					throw new Exception("ERROR.NO.DATA");
				}
			}
			
			mav.addObject("theForm", museum);			
			
		} catch (Exception e) {
			if(log.isDebugEnabled())log.debug(e.toString());
			redirectView(mav, message.getMessage(e.getMessage(), message.getMessage("ERROR.ACCESS.FAIL")), getReferer(request));
		}		
		
		if(log.isDebugEnabled())log.debug("[END] " + this.getClass().getName() + ".museumForm()");
		return mav;
	}
	
	/**
	 * 박물관 페이지 내용 수정폼
	 * @param request
	 * @param MuseumDto 
	 * @return ModelAndView
	 * @throws Exception
	 */	
	@RequestMapping
	public ModelAndView museumPageEdit(HttpServletRequest request, MuseumMenuContDto museum) throws Exception{
		if(log.isDebugEnabled())log.debug("[START] " + this.getClass().getName() + ".museumPageEdit()");
		
		ModelAndView mav = new ModelAndView("siteManage/museum/museumPageEdit");
		
		try {
			MuseumMenuContDto result = (MuseumMenuContDto)museumService.getMuseumMenuContInfo(museum);
			if(result == null) throw new Exception("ERROR.NO.DATA");				
			mav.addObject("theForm", result);
			
			if(StringUtils.equals("roadmap",museum.getMenu_code())){
				mav.addObject("museumResult", museumService.getMuseumInfo(museum.getMuseum_no()));
			}
			
		} catch (Exception e) {
			if(log.isDebugEnabled())log.debug(e.toString());
			redirectView(mav, message.getMessage(e.getMessage(), message.getMessage("ERROR.ACCESS.FAIL")), getReferer(request));
		}		
		
		if(log.isDebugEnabled())log.debug("[END] " + this.getClass().getName() + ".museumForm()");
		return mav;
	}
	
	/**
	 * 박물관 페이지 내용 수정
	 * @param request
	 * @param MuseumDto 
	 * @return ModelAndView
	 * @throws Exception
	 */	
	@RequestMapping(method=RequestMethod.POST)
	public ModelAndView museumPageSave(HttpServletRequest request, MuseumMenuContDto museum) throws Exception{
		if(log.isDebugEnabled())log.debug("[START] " + this.getClass().getName() + ".museumPageSave()");
		
		ModelAndView mav = new ModelAndView();
		
		try {				
			museumService.updateMuseumPageInfo(museum);
			redirectView(mav, "", "museumPageEdit.do?museum_no="+museum.getMuseum_no()+"&menu_code="+museum.getMenu_code());
		} catch (Exception e) {
			if(log.isDebugEnabled())log.debug(e.toString());
			redirectView(mav, message.getMessage("ERROR.ACCESS.FAIL"), "museumPageEdit.do?museum_no="+museum.getMuseum_no()+"&menu_code="+museum.getMenu_code());
		}		
		
		if(log.isDebugEnabled())log.debug("[END] " + this.getClass().getName() + ".museumPageSave()");
		return mav;
	}
	
	/**
	 * 박물관 생성/수정
	 * @param request
	 * @param MuseumDto 
	 * @return ModelAndView
	 * @throws Exception
	 */	
	@RequestMapping(method=RequestMethod.POST)
	public ModelAndView museumSave(HttpServletRequest request, MuseumDto museum) throws Exception{
		if(log.isDebugEnabled())log.debug("[START] " + this.getClass().getName() + ".museumSave()");
		
		ModelAndView mav = new ModelAndView();
		
		try {
				
			UserDto sessionInfo = getSession(request);
			if(StringUtils.equals("insert", museum.getAct())){
				if(!StringUtils.equals("9", sessionInfo.getAuth_level())) throw new Exception("ERROR.ACCESS.FAIL");
				museumService.insertMuseumInfo(museum);
			} else {
				museumService.updateMuseumInfo(museum);
			}
			redirectView(mav, "", "museumForm.do?museum_no="+museum.getMuseum_no());
		} catch (Exception e) {
			if(log.isDebugEnabled())log.debug(e.toString());
			redirectView(mav, message.getMessage("ERROR.ACCESS.FAIL"), "museumList.do");
		}		
		
		if(log.isDebugEnabled())log.debug("[END] " + this.getClass().getName() + ".museumSave()");
		return mav;
	}
	
	/**
	 * 박물관 목록조회 json
	 * @param request
	 * @param boardDto 
	 * @return
	 * @throws Exception
	 */	
	@RequestMapping(method=RequestMethod.POST)
	public ModelAndView museumListJson(HttpServletRequest request) throws Exception{
		if(log.isDebugEnabled())log.debug("[START] " + this.getClass().getName() + ".museumListJson()");
		
		ModelAndView mav = new ModelAndView("jsonViewer");
		
		try {
			
			mav.addObject("RESULT_CODE","SUCCESS");
			mav.addObject("RESULT_DATA",museumService.getMuseumList(null));
			
		} catch (Exception e) {
			mav.addObject("RESULT_CODE","FAIL");
			if(log.isWarnEnabled())log.warn(e.getMessage());
		}
		
		if(log.isDebugEnabled())log.debug("[END] " + this.getClass().getName() + ".museumListJson()");
		return mav;
	}
}
