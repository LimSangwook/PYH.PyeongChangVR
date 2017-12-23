package com.module.menu.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.common.util.CommonCache;
import com.common.util.CommonFileUtils;
import com.common.util.CommonWebUtils;
import com.common.util.JProperties;
import com.module.board.dto.BoardConfigDto;
import com.module.board.service.BoardService;
import com.module.html.dto.HtmlDto;
import com.module.html.service.HtmlService;
import com.module.main.service.MainService;
import com.module.menu.dto.MenuDto;
import com.module.menu.service.MenuService;

import org.springframework.context.support.MessageSourceAccessor;

@Controller
@RequestMapping("/siteManage/**/menu*")
public class MenuController extends CommonWebUtils{
	private Logger log = LoggerFactory.getLogger(getClass());

	@Resource(name = "messageSourceAccessor")
	private MessageSourceAccessor message;
	
	@Autowired
	private MainService mainService;
	
	@Autowired
	private MenuService menuService;
	
	@Autowired
	private BoardService boardService;
	
	@Autowired
	private HtmlService htmlService;
	
	/**
	 * 메뉴관리
	 * @param request
	 * @param  
	 * @return
	 * @throws Exception
	 */	
	@RequestMapping
	public ModelAndView menuManage(HttpServletRequest request, MenuDto menu) throws Exception{
		if(log.isDebugEnabled())log.debug("[START] " + this.getClass().getName() + ".menuManage()");
		
		ModelAndView mav = new ModelAndView("siteManage/menu/menuManage");
		try {
			// 관리자 메뉴 기본 세팅
			menu.setSystem_gubun(StringUtils.defaultIfBlank(menu.getSystem_gubun(), "admin"));
			// 사용자 권한 목록
			mav.addObject("userAuthLevelCodeList", mainService.getCommonCodeList("USER_AUTH_LEVEL"));
			// 사이트 언어코드 목록
			//mav.addObject("langKindCodeList", mainService.getCommonCodeList("SITE_SRV_LANG"));
			
			mav.addObject("menuDepth1List", menuService.getMenuList(menu.getSystem_gubun()));
			mav.addObject("menuDepth2List", menuService.getMenuList(menu.getDepth1()));
			mav.addObject("menuDepth3List", menuService.getMenuList(menu.getDepth2()));
						
			if(StringUtils.isNotBlank(menu.getMenu_code())){
				MenuDto result = menuService.getMenuInfo(menu);
				if(result != null){
					
					menu.setMenu_code(result.getMenu_code());
					menu.setMenu_name(result.getMenu_name());
					menu.setParent_menu_code(result.getParent_menu_code());
					menu.setOrder_step(result.getOrder_step());
					menu.setLink_address(result.getLink_address());
					menu.setIs_new_window(result.getIs_new_window());					
					menu.setBoard_id(result.getBoard_id());
					menu.setPage_seq(result.getPage_seq());
					menu.setMenu_kind(result.getMenu_kind());
					menu.setStatus(result.getStatus());
					menu.setMenu_auth_level(result.getMenu_auth_level());
					if(StringUtils.isNotBlank(menu.getBoard_id())){
						BoardConfigDto params = new BoardConfigDto();
						params.setBoard_id(menu.getBoard_id());
						BoardConfigDto boardConfig = boardService.getBoardConfigInfo(params);
						if(boardConfig != null){
							menu.setBoard_name(boardConfig.getBoard_name());	
						}
					}
					if(StringUtils.isNotBlank(menu.getPage_seq())){
						HtmlDto htmlInfo = htmlService.getHtmlInfo(menu.getPage_seq());
						if(htmlInfo != null){
							menu.setHtml_title(htmlInfo.getTitle());	
						}						
					}
					menu.setAct("updateMenu");
				}
			}
			mav.addObject("theForm", menu);
		} catch (Exception e) {
			if(log.isWarnEnabled())log.warn(e.toString());
		}		
		
		if(log.isDebugEnabled())log.debug("[END] " + this.getClass().getName() + ".menuManage()");
		return mav;
	}
	
	/**
	 * 메뉴정보 등록/수정
	 * @param request
	 * @param boardDto 
	 * @return
	 * @throws Exception
	 */	
	@RequestMapping(value="menuSave", method=RequestMethod.POST)
	public ModelAndView menuSave(HttpServletRequest request, MenuDto menu) throws Exception{
		if(log.isDebugEnabled())log.debug("[START] " + this.getClass().getName() + ".menuSave()");
		
		ModelAndView mav = new ModelAndView();		
		
		String depth1AreaScroll = StringUtils.defaultIfBlank(request.getParameter("depth1AreaScroll"),"");
		String depth2AreaScroll = StringUtils.defaultIfBlank(request.getParameter("depth2AreaScroll"),"");
		String depth3AreaScroll = StringUtils.defaultIfBlank(request.getParameter("depth3AreaScroll"),"");
		
		// 리턴페이지 파라메터 가공
		StringBuilder returnPage = new StringBuilder(256);
		returnPage.append("menuManage.do");
		returnPage.append("?depth1=");returnPage.append(menu.getDepth1());
		returnPage.append("&depth2=");returnPage.append(menu.getDepth2());
		returnPage.append("&depth3=");returnPage.append(menu.getDepth3());
		returnPage.append("&system_gubun=");returnPage.append(menu.getSystem_gubun());
		returnPage.append("&depth1AreaScroll=");returnPage.append(depth1AreaScroll);
		returnPage.append("&depth2AreaScroll=");returnPage.append(depth2AreaScroll);
		returnPage.append("&depth3AreaScroll=");returnPage.append(depth3AreaScroll);
		
		try {
			
			if(StringUtils.equals("insertMenu", menu.getAct())){				
				returnPage.append("&menu_code=");returnPage.append(menu.getMenu_code());				
				menuService.insertMenuInfo(menu);				
			} else if(StringUtils.equals("updateMenu", menu.getAct())){				
				returnPage.append("&menu_code=");returnPage.append(menu.getMenu_code());
				menuService.updateMenuInfo(menu);				
			} else if(StringUtils.equals("deleteMenu", menu.getAct())){
				menuService.deleteMenuInfo(menu);
			}
			
			// 캐시삭제
			CommonCache.getInstance().clearCache();
			// 파일삭제
			CommonFileUtils.removeDIR(CommonFileUtils.getTarget(request).concat("/").concat(JProperties.getString("FILE.JSON.DIR")));
						
			redirectView(mav, "", returnPage.toString());
		} catch (Exception e) {
			if(log.isWarnEnabled())log.warn(e.getMessage());
		}
		
		if(log.isDebugEnabled())log.debug("[END] " + this.getClass().getName() + ".menuSave()");
		return mav;
	}
	
	/**
	 * 메뉴정보 순서 변경
	 * @param request
	 * @param boardDto 
	 * @return
	 * @throws Exception
	 */	
	@RequestMapping(value="menuSortUpdate",method=RequestMethod.POST)
	public ModelAndView menuSortUpdate(HttpServletRequest request, MenuDto menu) throws Exception{
		if(log.isDebugEnabled())log.debug("[START] " + this.getClass().getName() + ".menuSortUpdate()");
		
		ModelAndView mav = new ModelAndView();		
		String returnPage = "menuManage.do?depth1="+menu.getDepth1()+
								"&depth2="+menu.getDepth2()+"&depth3="+menu.getDepth3()+
								"&system_gubun="+menu.getSystem_gubun();
		
		try {
			menuService.updateMenuSort(menu);
			// 캐시삭제
			CommonCache.getInstance().clearCache();
			// 파일삭제
			CommonFileUtils.removeDIR(CommonFileUtils.getTarget(request).concat("/").concat(JProperties.getString("FILE.JSON.DIR")));
			
			redirectView(mav, "", returnPage);
		} catch (Exception e) {
			if(log.isWarnEnabled())log.warn(e.getMessage());
		}
		
		if(log.isDebugEnabled())log.debug("[END] " + this.getClass().getName() + ".menuSortUpdate()");
		return mav;
	}
	
	/**
	 * 메뉴코드 중복확인
	 * @param request
	 * @param boardDto 
	 * @return
	 * @throws Exception
	 */	
	@RequestMapping(value="menuCodeCheck",method=RequestMethod.POST)
	public ModelAndView menuCodeCheck(HttpServletRequest request, MenuDto menu) throws Exception{
		if(log.isDebugEnabled())log.debug("[START] " + this.getClass().getName() + ".menuCodeCheck()");
		
		ModelAndView mav = new ModelAndView("jsonViewer");
		
		try {
			if(menuService.menuCodeDoubleCheck(menu) && StringUtils.isNotBlank(menu.getMenu_code())){
				mav.addObject("RESULT_CODE","SUCCESS");	
			} else {
				mav.addObject("RESULT_CODE","FAIL");
			}
		} catch (Exception e) {
			if(log.isWarnEnabled())log.warn(e.getMessage());
			mav.addObject("RESULT_CODE","FAIL");
		}
		
		if(log.isDebugEnabled())log.debug("[END] " + this.getClass().getName() + ".menuCodeCheck()");
		return mav;
	}
}