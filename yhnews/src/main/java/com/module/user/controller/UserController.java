package com.module.user.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.common.util.CommonUtils;
import com.common.util.CommonWebUtils;
import com.common.util.PagingUtil;

import com.module.main.service.MainService;
import com.module.museum.service.MuseumService;
import com.module.user.dto.UserDto;
import com.module.user.service.UserService;

@Controller
@RequestMapping({"/siteManage/**/user*"})
public class UserController extends CommonWebUtils{

	private Logger log = LoggerFactory.getLogger(getClass());
	
	@Resource(name = "messageSourceAccessor")
	private MessageSourceAccessor message;

	@Autowired
	private MainService mainService;
	
	@Autowired
	private UserService userService;
		
	@Autowired
	private MuseumService museumService;
	
	/**
	 * 사용자 목록
	 * @param request
	 * @param boardDto 
	 * @return
	 * @throws Exception
	 */	
	@RequestMapping
	public ModelAndView userList(HttpServletRequest request, UserDto user) throws Exception{
		if(log.isDebugEnabled())log.debug("[START] " + this.getClass().getName() + ".userList()");
		
		ModelAndView mav = new ModelAndView("siteManage/user/userList");
		
		try {
			user.setSearch_type(StringUtils.equals("adminManage", (String)request.getAttribute("menuCode"))?"admin":"member");
			mav.addObject("result", userService.getUserList(user));			
			mav.addObject("theForm", user);			
			mav.addObject("userAuthLevelCodeList", mainService.getCommonCodeList("USER_AUTH_LEVEL"));						
			mav.addObject("defaultParameter", getParameter(request,"&","user_id|order_type|order_column"));
			mav.addObject("pageNavigation", PagingUtil.printPageNavi(user, getParameter(request,"userList.do?","user_id|page")));
			
		} catch (Exception e) {
			if(log.isWarnEnabled())log.warn(e.getMessage());
		}		
		
		if(log.isDebugEnabled())log.debug("[END] " + this.getClass().getName() + ".userList()");
		return mav;
	}
	
	/**
	 * 사용자 등록/수정 폼
	 * @param request
	 * @param boardDto 
	 * @return
	 * @throws Exception
	 */	
	@RequestMapping
	public ModelAndView userForm(HttpServletRequest request, UserDto user) throws Exception{
		if(log.isDebugEnabled())log.debug("[START] " + this.getClass().getName() + ".userForm()");
		
		ModelAndView mav = new ModelAndView("siteManage/user/userForm");
		
		try {			
			// 상품관리자 바로 등록시 이름 인코딩 변경
			if(StringUtils.isNotBlank(user.getUser_name()))user.setUser_name(CommonUtils.utf8(user.getUser_name()));
			
			if(StringUtils.isNotBlank(user.getUser_id())){
				UserDto result = userService.getUserInfo(user);
				if(result != null){
					user = (UserDto)result.clone();
					user.setAct("update");
					
					// 박물관 접근권한 매핑 목록
					//mav.addObject("museumAuthMapList", museumService.getMuseumAuthMapList(user.getUser_id()));
				} else {
					throw new Exception("Not Found User..");
				}
			}			
			// 사용자 구분 : 관리자 / admin , 회원 / member 
			user.setSearch_type(StringUtils.equals("adminManage", (String)request.getAttribute("menuCode"))?"admin":"member");
			// 사용자 권한 목록
			mav.addObject("userAuthLevelCodeList", mainService.getCommonCodeList("USER_AUTH_LEVEL"));			
			
			mav.addObject("theForm", user);
			mav.addObject("defaultParameter", getParameter(request,"?","user_id"));
		} catch (Exception e) {
			if(log.isWarnEnabled())log.warn(e.getMessage());
			redirectView(mav, message.getMessage("ERROR.NO.DATA"), "userList.do");
		}		
		
		if(log.isDebugEnabled())log.debug("[END] " + this.getClass().getName() + ".userForm()");
		return mav;
	}
	
	/**
	 * 사용자 등록/수정
	 * @param request
	 * @param boardDto 
	 * @return
	 * @throws Exception
	 */	
	@RequestMapping(method=RequestMethod.POST)
	public ModelAndView userSave(HttpServletRequest request, UserDto user) throws Exception{
		if(log.isDebugEnabled())log.debug("[START] " + this.getClass().getName() + ".userSave()");
		
		ModelAndView mav = new ModelAndView();		
		String returnPage = "userList.do";
		
		try {
			if(StringUtils.equals("insert", user.getAct())){
				userService.insertUserInfo(user);
			} else if(StringUtils.equals("update", user.getAct())){				
				returnPage = "userForm.do?user_id="+user.getUser_id();				
				userService.updateUserInfo(user);
			} else if(StringUtils.equals("delete", user.getAct())){				
				userService.deleteUserInfo(user);
			}
			redirectView(mav, "", returnPage);
		} catch (Exception e) {			
			if(log.isWarnEnabled())log.warn(e.getMessage());
			redirectView(mav, message.getMessage("ERROR.ACCESS.FAIL"), "userList.do");
		}		
		
		if(log.isDebugEnabled())log.debug("[END] " + this.getClass().getName() + ".userSave()");
		return mav;
	}
	
	/**
	 * 사용자 아이디 중복확인
	 * @param request
	 * @param boardDto 
	 * @return
	 * @throws Exception
	 */	
	@RequestMapping(method=RequestMethod.POST)
	public ModelAndView userIdCheck(HttpServletRequest request, UserDto user) throws Exception{
		if(log.isDebugEnabled())log.debug("[START] " + this.getClass().getName() + ".userIdCheck()");
		
		ModelAndView mav = new ModelAndView("jsonViewer");
		
		try {
			if(userService.userIdDoubleCheck(request, user)){
				mav.addObject("RESULT_CODE","SUCCESS");	
			} else {
				mav.addObject("RESULT_CODE","FAIL");
			}
		} catch (Exception e) {
			mav.addObject("RESULT_CODE","FAIL");
			if(log.isWarnEnabled())log.warn(e.getMessage());
		}
		
		if(log.isDebugEnabled())log.debug("[END] " + this.getClass().getName() + ".userIdCheck()");
		return mav;
	}
	
	/**
	 * 내 정보 가져오기
	 * @param request
	 * @param boardDto 
	 * @return
	 * @throws Exception
	 */	
	@RequestMapping(method=RequestMethod.POST)
	public ModelAndView userInfoJson(HttpServletRequest request) throws Exception{
		if(log.isDebugEnabled())log.debug("[START] " + this.getClass().getName() + ".userInfoJson()");
		
		ModelAndView mav = new ModelAndView("jsonViewer");
		
		try {
			
			UserDto sessionInfo = getSession(request);
			mav.addObject("RESULT_CODE","SUCCESS");
			mav.addObject("RESULT_DATA",userService.getUserInfo(sessionInfo));
			
		} catch (Exception e) {
			mav.addObject("RESULT_CODE","FAIL");
			if(log.isWarnEnabled())log.warn(e.getMessage());
		}
		
		if(log.isDebugEnabled())log.debug("[END] " + this.getClass().getName() + ".userInfoJson()");
		return mav;
	}
	
	/**
	 * 내 정보 수정
	 * @param request
	 * @param boardDto 
	 * @return
	 * @throws Exception
	 */	
	@RequestMapping(method=RequestMethod.POST)
	public ModelAndView userMyInfoSave(HttpServletRequest request, UserDto user) throws Exception{
		if(log.isDebugEnabled())log.debug("[START] " + this.getClass().getName() + ".userMyInfoSave()");
		
		ModelAndView mav = new ModelAndView("jsonViewer");
		
		try {
			// 세션정보 조회
			UserDto sessionInfo = getSession(request);
			user.setUser_id(sessionInfo.getUser_id());
			
			// 내정보 수정
			userService.updateMyInfo(user);
			// 세션등록
			setSession(request, userService.getUserInfo(sessionInfo));
			
			mav.addObject("RESULT_CODE","SUCCESS");
		} catch (Exception e) {
			if(log.isWarnEnabled())log.warn(e.getMessage());
			mav.addObject("RESULT_CODE","FAIL");
		}		
		
		if(log.isDebugEnabled())log.debug("[END] " + this.getClass().getName() + ".userMyInfoSave()");
		return mav;
	}
	
	/**
	 * 관리자 찾기 팝업
	 * @param request
	 * @param boardDto 
	 * @return
	 * @throws Exception
	 */	
	@RequestMapping
	public ModelAndView userFindListPop(HttpServletRequest request, UserDto user) throws Exception{
		if(log.isDebugEnabled())log.debug("[START] " + this.getClass().getName() + ".userFindListPop()");
		
		ModelAndView mav = new ModelAndView("empty/admin/user/userFindListPop");
		
		try {
			user.setSearch_type("admin");
			mav.addObject("result", userService.getUserList(user));			
			mav.addObject("theForm", user);			
			mav.addObject("userAuthLevelCodeList", mainService.getCommonCodeList("USER_AUTH_LEVEL"));						
			mav.addObject("defaultParameter", getParameter(request,"&","user_id|order_type|order_column"));
			mav.addObject("pageNavigation", PagingUtil.printPageNavi(user, getParameter(request,"userFindListPop.do?","user_id|page")));
			
		} catch (Exception e) {
			if(log.isWarnEnabled())log.warn(e.getMessage());
		}		
		
		if(log.isDebugEnabled())log.debug("[END] " + this.getClass().getName() + ".userFindListPop()");
		return mav;
	}
}
