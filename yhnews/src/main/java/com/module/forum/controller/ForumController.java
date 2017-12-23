package com.module.forum.controller;

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
import com.module.forum.dto.ForumDto;
import com.module.forum.dto.SpeakerDto;
import com.module.forum.service.ForumService;

@Controller
@RequestMapping({"/siteManage/**/forum*","/forum/**/forum*"})
public class ForumController extends CommonWebUtils{

	private Logger log = LoggerFactory.getLogger(getClass());
	
	@Resource(name = "messageSourceAccessor")
	private MessageSourceAccessor message;
	
	@Autowired
	private ForumService forumService;
	
	/**
	 * 포펌/박람회 정보 등록/수정폼
	 * @param request
	 * @param ForumDto 
	 * @return ModelAndView
	 * @throws Exception
	 */	
	@RequestMapping("/siteManage/**/forumForm")
	public ModelAndView forumForm(HttpServletRequest request, ForumDto forum) throws Exception{
		if(log.isDebugEnabled())log.debug("[START] " + this.getClass().getName() + ".forumForm()");
		
		ModelAndView mav = new ModelAndView("siteManage/forum/forumForm");
		
		try {
			if(StringUtils.isNotBlank(forum.getForum_key())){
				ForumDto result = (ForumDto)forumService.getForumInfo(forum.getForum_key());
				if(result != null){
					result.setAct("update");
					forum = (ForumDto)result.clone();
				} else {
					throw new Exception("ERROR.NO.DATA");
				}
			}
			
			mav.addObject("forumInfo", forum);			
			
		} catch (Exception e) {
			if(log.isDebugEnabled())log.debug(e.toString());
			redirectView(mav, message.getMessage(e.getMessage(), message.getMessage("ERROR.ACCESS.FAIL")), getReferer(request));
		}		
		
		if(log.isDebugEnabled())log.debug("[END] " + this.getClass().getName() + ".forumForm()");
		return mav;
	}
	
	/**
	 * 포펌/박람회 정보
	 * @param request
	 * @param ForumDto 
	 * @return ModelAndView
	 * @throws Exception
	 */	
	@RequestMapping("/forum/**/forumDetail")
	public ModelAndView forumDetail(HttpServletRequest request, ForumDto forum) throws Exception{
		if(log.isDebugEnabled())log.debug("[START] " + this.getClass().getName() + ".forumDetail()");
		
		ModelAndView mav = new ModelAndView("forum/forum/forumDetail");
		
		try {
						
			ForumDto result = (ForumDto)forumService.getForumInfo(forum.getForum_key());
			if(result == null)throw new Exception("ERROR.NO.DATA");
			mav.addObject("forumInfo", result);			
			
		} catch (Exception e) {
			if(log.isDebugEnabled())log.debug(e.toString());
			redirectView(mav, message.getMessage(e.getMessage(), message.getMessage("ERROR.ACCESS.FAIL")), getReferer(request));
		}		
		
		if(log.isDebugEnabled())log.debug("[END] " + this.getClass().getName() + ".forumDetail()");
		return mav;
	}
	
	/**
	 * 포펌/박람회 정보 등록/수정/삭제
	 * @param request
	 * @param ForumDto 
	 * @return ModelAndView
	 * @throws Exception
	 */	
	@RequestMapping(value="/siteManage/**/forumSave", method=RequestMethod.POST)
	public ModelAndView forumSave(HttpServletRequest request, ForumDto forum) throws Exception{
		if(log.isDebugEnabled())log.debug("[START] " + this.getClass().getName() + ".forumSave()");
		
		ModelAndView mav = new ModelAndView();
		String returnUrl = "";
		try {
			if(StringUtils.equals("insert", forum.getAct())){
				returnUrl = "forumList.do";
				forumService.insertForumInfo(forum);	
			} else if(StringUtils.equals("update", forum.getAct())){
				returnUrl = "forumForm.do?forum_key="+forum.getForum_key();
				forumService.updateForumInfo(forum);
			} else if(StringUtils.equals("delete", forum.getAct())){
				returnUrl = "forumList.do";
				forumService.deleteForumInfo(forum);
			}
			
			redirectView(mav, "", returnUrl);

		} catch (Exception e) {
			if(log.isDebugEnabled())log.debug(e.toString());
			redirectView(mav, message.getMessage(e.getMessage(), message.getMessage("ERROR.ACCESS.FAIL")), getReferer(request));
		}		
		
		if(log.isDebugEnabled())log.debug("[END] " + this.getClass().getName() + ".forumSave()");
		return mav;
	}
	
	/**
	 * 포펌/박람회 목록 
	 * @param request
	 * @param ForumDto 
	 * @return ModelAndView
	 * @throws Exception
	 */	
	@RequestMapping
	public ModelAndView forumList(HttpServletRequest request, ForumDto forum) throws Exception{
		if(log.isDebugEnabled())log.debug("[START] " + this.getClass().getName() + ".forumList()");
		
		ModelAndView mav = new ModelAndView();
		
		try {			
			
			String siteGubun = (String)request.getAttribute("siteGubun");	
			if(StringUtils.equals("siteManage", siteGubun)){
				// 전체 포럼 조회
				forum.setSearch_type("1");
			} else {
				// 지난 포럼/박람회 조회
				forum.setSearch_type("2");
			}			
			mav.addObject("result", forumService.getForumList(forum));
			mav.addObject("theForm", forum);			
			mav.setViewName(siteGubun+"/forum/forumList");
			
		} catch (Exception e) {
			if(log.isDebugEnabled())log.debug(e.toString());
			redirectView(mav, message.getMessage(e.getMessage(), message.getMessage("ERROR.ACCESS.FAIL")), getReferer(request));
		}		
		
		if(log.isDebugEnabled())log.debug("[END] " + this.getClass().getName() + ".forumList()");
		return mav;
	}
	
	/**
	 * 연사정보 등록/수정 폼
	 * @param request
	 * @param ForumDto 
	 * @return ModelAndView
	 * @throws Exception
	 */	
	@RequestMapping
	public ModelAndView forumSpeakerForm(HttpServletRequest request, SpeakerDto speaker) throws Exception{
		if(log.isDebugEnabled())log.debug("[START] " + this.getClass().getName() + ".forumSpeakerForm()");
		
		ModelAndView mav = new ModelAndView("siteManage/forum/speakerForm");
		
		try {
			if(StringUtils.isBlank(speaker.getForum_key()))throw new Exception("ERROR.ACCESS.FAIL");
			if(StringUtils.isNotBlank(speaker.getSpeaker_key()) && StringUtils.isNotBlank(speaker.getForum_key())){
				SpeakerDto result = (SpeakerDto)forumService.getSpeakerInfo(speaker);
				if(result != null){
					result.setAct("update");
					speaker = (SpeakerDto)result.clone();
				} else {
					throw new Exception("ERROR.NO.DATA");
				}
			}
			// 포럼/박람회 기본정보			
			mav.addObject("forumInfo", forumService.getForumInfo(speaker.getForum_key()));
			// 연사구분목록
			mav.addObject("speakerKindList", forumService.getSpeakerKindList());
			mav.addObject("theForm", speaker);			
			
		} catch (Exception e) {
			if(log.isDebugEnabled())log.debug(e.toString());
			redirectView(mav, message.getMessage(e.getMessage(), message.getMessage("ERROR.ACCESS.FAIL")), getReferer(request));
		}		
		
		if(log.isDebugEnabled())log.debug("[END] " + this.getClass().getName() + ".forumSpeakerForm()");
		return mav;
	}
	
	/**
	 * 연사자 등록/수정
	 * @param request
	 * @param ForumDto 
	 * @return ModelAndView
	 * @throws Exception
	 */	
	@RequestMapping(value="/siteManage/**/forumSpeakerSave", method=RequestMethod.POST)
	public ModelAndView forumSpeakerSave(HttpServletRequest request, SpeakerDto speaker) throws Exception{
		if(log.isDebugEnabled())log.debug("[START] " + this.getClass().getName() + ".forumSpeakerSave()");
		
		ModelAndView mav = new ModelAndView();
		String returnUrl = "";
		try {
			
			if(StringUtils.equals("insert", speaker.getAct())){
				returnUrl = "forumSpeakerList.do?forum_key="+speaker.getForum_key();
				forumService.insertSpeakerInfo(speaker);	
			} else if(StringUtils.equals("update", speaker.getAct())){
				returnUrl = "forumSpeakerForm.do?forum_key="+speaker.getForum_key()+"&speaker_key="+speaker.getSpeaker_key();
				forumService.updateSpeakerInfo(speaker);
			} else if(StringUtils.equals("delete", speaker.getAct())){
				returnUrl = "forumSpeakerList.do?forum_key="+speaker.getForum_key();
				forumService.deleteSpeakerInfo(speaker);
			}
			
			redirectView(mav, "", returnUrl);

		} catch (Exception e) {
			if(log.isDebugEnabled())log.debug(e.toString());
			redirectView(mav, message.getMessage(e.getMessage(), message.getMessage("ERROR.ACCESS.FAIL")), getReferer(request));
		}		
		
		if(log.isDebugEnabled())log.debug("[END] " + this.getClass().getName() + ".forumSpeakerSave()");
		return mav;
	}
	
	/**
	 * 연사 목록 
	 * @param request
	 * @param ForumDto 
	 * @return ModelAndView
	 * @throws Exception
	 */	
	@RequestMapping
	public ModelAndView forumSpeakerList(HttpServletRequest request, SpeakerDto speaker) throws Exception{
		if(log.isDebugEnabled())log.debug("[START] " + this.getClass().getName() + ".forumSpeakerList()");
		
		ModelAndView mav = new ModelAndView();
		
		try {
			String siteGubun = (String)request.getAttribute("siteGubun");
			if(StringUtils.isBlank(speaker.getForum_key()))throw new Exception("ERROR.ACCESS.FAIL");
			// 연사목록			
			if(StringUtils.equals("siteManage", siteGubun)){
				speaker.setSearch_type("1");
			} else {
				speaker.setSearch_type("2");
			}
			mav.addObject("result", forumService.getSpeakerList(speaker));
			mav.addObject("pageNavigation", PagingUtil.printPageNavi(speaker, getParameter(request,"forumSpeakerList.do?","speaker_key|page"),siteGubun));
			// 포럼/박람회 기본정보			
			mav.addObject("forumInfo", forumService.getForumInfo(speaker.getForum_key()));
			// 연사구분목록
			mav.addObject("speakerKindList", forumService.getSpeakerKindList());
			mav.addObject("theForm", speaker);
			mav.setViewName(siteGubun+"/forum/speakerList");
			
		} catch (Exception e) {
			if(log.isDebugEnabled())log.debug(e.toString());
			redirectView(mav, message.getMessage(e.getMessage(), message.getMessage("ERROR.ACCESS.FAIL")), getReferer(request));
		}		
		
		if(log.isDebugEnabled())log.debug("[END] " + this.getClass().getName() + ".forumSpeakerList()");
		return mav;
	}
	
	/**
	 * 연사 상세보기 
	 * @param request
	 * @param ForumDto 
	 * @return ModelAndView
	 * @throws Exception
	 */	
	@RequestMapping("/forum/**/forumSpeakerDetail")
	public ModelAndView forumSpeakerDetail(HttpServletRequest request, SpeakerDto speaker) throws Exception{
		if(log.isDebugEnabled())log.debug("[START] " + this.getClass().getName() + ".forumSpeakerDetail()");
		
		ModelAndView mav = new ModelAndView("forum/forum/speakerDetail");
		
		try {
						
			SpeakerDto result = (SpeakerDto)forumService.getSpeakerInfo(speaker);
			if(result == null)throw new Exception("ERROR.NO.DATA");
			// 포럼/박람회 기본정보			
			mav.addObject("forumInfo", forumService.getForumInfo(speaker.getForum_key()));
			// 연사상세정보
			mav.addObject("speakerInfo", result);			
			
		} catch (Exception e) {
			if(log.isDebugEnabled())log.debug(e.toString());
			redirectView(mav, message.getMessage(e.getMessage(), message.getMessage("ERROR.ACCESS.FAIL")), getReferer(request));
		}		
		
		if(log.isDebugEnabled())log.debug("[END] " + this.getClass().getName() + ".forumSpeakerDetail()");
		return mav;
	}
	
	/**
	 * 현재 포펌/박람회 정보
	 * @param request
	 * @param ForumDto 
	 * @return ModelAndView
	 * @throws Exception
	 */	
	@RequestMapping("/forum/**/forumCurrDetail")
	public ModelAndView forumCurrDetail(HttpServletRequest request, SpeakerDto speaker) throws Exception{
		if(log.isDebugEnabled())log.debug("[START] " + this.getClass().getName() + ".forumCurrDetail()");
		
		ModelAndView mav = new ModelAndView("forum/forum/forumCurrDetail");
		
		try {
			String siteGubun = (String)request.getAttribute("siteGubun");
			// 현재 진행중인 포럼 정보 조회
			ForumDto result = (ForumDto)forumService.getForumActiveInfo();
			// 연사소개 일때~
			if(result != null && StringUtils.equals("4", request.getParameter("contTab"))){
				speaker.setSearch_type("2");
				speaker.setForum_key(result.getForum_key());
				mav.addObject("result", forumService.getSpeakerList(speaker));
				mav.addObject("pageNavigation", PagingUtil.printPageNavi(speaker, getParameter(request,"forumCurrDetail.do?","speaker_key|forum_key|page"),siteGubun));
				// 연사구분목록
				mav.addObject("speakerKindList", forumService.getSpeakerKindList());
				mav.addObject("theForm", speaker);
				
				// 연사상세보기
				if(StringUtils.isNotBlank(speaker.getSpeaker_key())){
					mav.addObject("speakerInfo",forumService.getSpeakerInfo(speaker));
				}
			}
			mav.addObject("forumInfo", result);			
			
		} catch (Exception e) {
			if(log.isDebugEnabled())log.debug(e.toString());
			redirectView(mav, message.getMessage(e.getMessage(), message.getMessage("ERROR.ACCESS.FAIL")), getReferer(request));
		}		
		
		if(log.isDebugEnabled())log.debug("[END] " + this.getClass().getName() + ".forumDetail()");
		return mav;
	}
}
