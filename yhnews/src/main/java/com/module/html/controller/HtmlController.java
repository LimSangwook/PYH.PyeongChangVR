package com.module.html.controller;

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
import com.module.board.dto.BoardConfigDto;
import com.module.html.dto.HtmlDto;
import com.module.html.service.HtmlService;
import com.module.menu.dto.MenuDto;

@Controller
public class HtmlController extends CommonWebUtils{
	private Logger log = LoggerFactory.getLogger(getClass());

	@Resource(name = "messageSourceAccessor")
	private MessageSourceAccessor message;
	
	@Autowired
	private HtmlService htmlService;
	
	/**
	 * 콘텐츠 내용
	 * @param request
	 * @param html 
	 * @return ModelAndView
	 * @throws Exception
	 */	
	@RequestMapping({"siteManage/**/html","front/**/html"})
	public ModelAndView html(HttpServletRequest request) throws Exception{
		if(log.isDebugEnabled())log.debug("[START] " + this.getClass().getName() + ".html()");
		
		ModelAndView mav = new ModelAndView();
		try {			
			String siteGubun = (String)request.getAttribute("siteGubun");
			mav.setViewName(siteGubun+"/html/htmlPage");
			if(request.getAttribute("currMenuInfo") != null){
				MenuDto menu = (MenuDto)request.getAttribute("currMenuInfo");
				mav.addObject("result", htmlService.getHtmlInfo(menu.getPage_seq()));	
			}			
		} catch (Exception e) {
			if(log.isWarnEnabled())log.warn(e.getMessage());
		}		
		
		if(log.isDebugEnabled())log.debug("[END] " + this.getClass().getName() + ".html()");
		return mav;
	}
	
	/**
	 * 콘텐츠 목록
	 * @param request
	 * @param html 
	 * @return ModelAndView
	 * @throws Exception
	 */	
	@RequestMapping("siteManage/**/htmlList")
	public ModelAndView htmlList(HttpServletRequest request, HtmlDto html) throws Exception{
		if(log.isDebugEnabled())log.debug("[START] " + this.getClass().getName() + ".htmlList()");
		
		ModelAndView mav = new ModelAndView("siteManage/html/htmlList");
		try {
			mav.addObject("result", htmlService.getHtmlList(html));
			mav.addObject("theForm", html);
		} catch (Exception e) {
			if(log.isWarnEnabled())log.warn(e.getMessage());
		}		
		
		if(log.isDebugEnabled())log.debug("[END] " + this.getClass().getName() + ".htmlList()");
		return mav;
	}
	
	/**
	 * 콘텐츠 등록/수정폼
	 * @param request
	 * @param html 
	 * @return ModelAndView
	 * @throws Exception
	 */	
	@RequestMapping("siteManage/**/htmlForm")
	public ModelAndView htmlForm(HttpServletRequest request, HtmlDto html) throws Exception{
		if(log.isDebugEnabled())log.debug("[START] " + this.getClass().getName() + ".htmlForm()");
		
		ModelAndView mav = new ModelAndView("siteManage/html/htmlForm");
		try {
			// 첨부파일속성정보
			BoardConfigDto boardConfig = new BoardConfigDto();
			boardConfig.setEditor_use_yn("Y");
			boardConfig.setFile_count_limit(999);
			boardConfig.setFile_size_limit(999);
			boardConfig.setFile_ext_limit("jpeg|jpg|png|gif");
			
			if(StringUtils.isNotBlank(html.getPage_seq())){
				HtmlDto result = htmlService.getHtmlInfo(html.getPage_seq());
				if(result != null){
					html = result;
					html.setAct("update");
				} else {
					throw new Exception("Data Not Found!!!");
				}
			}
			mav.addObject("theForm", html);
			mav.addObject("boardConfig", boardConfig);
		} catch (Exception e) {
			if(log.isWarnEnabled())log.warn(e.getMessage());
			redirectView(mav, message.getMessage("ERROR.NO.DATA"), "htmlList.do");
		}		
		
		if(log.isDebugEnabled())log.debug("[END] " + this.getClass().getName() + ".htmlForm()");
		return mav;
	}
	
	/**
	 * 콘텐츠 등록/수정
	 * @param request
	 * @param boardDto 
	 * @return
	 * @throws Exception
	 */	
	@RequestMapping(value="siteManage/**/htmlSave",method=RequestMethod.POST)
	public ModelAndView htmlSave(HttpServletRequest request, HtmlDto html) throws Exception{
		if(log.isDebugEnabled())log.debug("[START] " + this.getClass().getName() + ".htmlSave()");
		
		ModelAndView mav = new ModelAndView();		
		String returnPage = "htmlList.do";
		
		try {
			if(StringUtils.equals("insert", html.getAct())){
				htmlService.insertHtmlInfo(html);
			} else if(StringUtils.equals("update", html.getAct())){				
				returnPage = "htmlForm.do?page_seq="+html.getPage_seq();				
				htmlService.updateHtmlInfo(html);
			} else if(StringUtils.equals("delete", html.getAct())){				
				htmlService.deleteHtmlInfo(html);
			}
			redirectView(mav, "", returnPage);
		} catch (Exception e) {			
			if(log.isWarnEnabled())log.warn(e.getMessage());
			redirectView(mav, message.getMessage("ERROR.ACCESS.FAIL"), "htmlList.do");
		}		
		
		if(log.isDebugEnabled())log.debug("[END] " + this.getClass().getName() + ".htmlSave()");
		return mav;
	}
	
	/**
	 * 콘텐츠 목록 Json
	 * @param request
	 * @param BoardConfigDto 
	 * @return ModelAndView
	 * @throws Exception
	 */	
	@RequestMapping(value="siteManage/**/htmlListJson",method=RequestMethod.POST)
	public ModelAndView htmlListJson(HttpServletRequest request, HtmlDto html) throws Exception{
		if(log.isDebugEnabled())log.debug("[START] " + this.getClass().getName() + ".htmlListJson()");
		
		ModelAndView mav = new ModelAndView("jsonViewer");
		try {
			mav.addObject("RESULT_DATA", htmlService.getHtmlList(html));
		} catch (Exception e) {
			if(log.isWarnEnabled())log.warn(e.getMessage());
		}		
		
		if(log.isDebugEnabled())log.debug("[END] " + this.getClass().getName() + ".htmlListJson()");
		return mav;
	}
	
	/**
	 * 포털 > 오시는길
	 * @param request
	 * @param BoardConfigDto 
	 * @return ModelAndView
	 * @throws Exception
	 */	
	@RequestMapping("portal/**/roadmap")
	public ModelAndView roadMap(HttpServletRequest request, HtmlDto html) throws Exception{
		if(log.isDebugEnabled())log.debug("[START] " + this.getClass().getName() + ".roadMap()");
		
		ModelAndView mav = new ModelAndView("portal/etc/roadmap");
		
		if(log.isDebugEnabled())log.debug("[END] " + this.getClass().getName() + ".roadMap()");
		return mav;
	}
	
	/**
	 * 포럼 > 오시는길
	 * @param request
	 * @param BoardConfigDto 
	 * @return ModelAndView
	 * @throws Exception
	 */	
	@RequestMapping("forum/**/roadmap")
	public ModelAndView roadMapForum(HttpServletRequest request, HtmlDto html) throws Exception{
		if(log.isDebugEnabled())log.debug("[START] " + this.getClass().getName() + ".roadMapForum()");
		
		ModelAndView mav = new ModelAndView("forum/etc/roadmapForum");
		
		if(log.isDebugEnabled())log.debug("[END] " + this.getClass().getName() + ".roadMapForum()");
		return mav;
	}
}
