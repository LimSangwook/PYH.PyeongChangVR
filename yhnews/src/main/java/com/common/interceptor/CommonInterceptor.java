package com.common.interceptor;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.common.util.CommonFileUtils;
import com.common.util.CommonJsonUtils;
import com.common.util.CommonUtils;
import com.common.util.CommonWebUtils;
import com.common.util.JProperties;
import com.module.banner.service.BannerService;
import com.module.board.dto.BoardConfigDto;
import com.module.board.service.BoardService;
import com.module.main.service.MainService;
import com.module.menu.dto.MenuDto;
import com.module.menu.service.MenuService;
import com.module.museum.dto.MuseumAuthMapDto;
import com.module.museum.dto.MuseumDto;
import com.module.museum.dto.MuseumMenuContDto;
import com.module.museum.service.MuseumService;
import com.module.seo.service.SeoService;
import com.module.user.dto.UserDto;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@Controller("commonInterceptor")
public class CommonInterceptor extends HandlerInterceptorAdapter {
	
	private Logger log = LoggerFactory.getLogger(getClass());
	
	@Resource(name = "messageSourceAccessor")
	private MessageSourceAccessor message;
	
	@Autowired
	private MenuService menuService;
	
	@Autowired
	private BoardService boardService;	
	
	@Autowired
	private MainService mainService;
	
	@Autowired
	private MuseumService museumService;
	
	@Autowired
	private SeoService seoService;
	
	@Autowired
	private BannerService bannerService;
		
	private boolean getMuseumAdminLeftMenuList(HttpServletRequest request, HttpServletResponse response) throws Exception{
		boolean museumAccessCheck = false;
		Map<String, Object> result = new HashMap<String, Object>();
		// 최고 관리자가 아닌경우 박물관 권한체크
		UserDto sessionInfo = CommonWebUtils.getSession(request);		
		// 박물관 권한목록
		List<MuseumAuthMapDto> authList = museumService.getMuseumAuthMapList(sessionInfo.getUser_id());
		// 전체 박물관 목록
		List<MuseumDto> museumList = museumService.getMuseumList(null);
		
		// 접근 박물관 메뉴 목록 지정
		for (MuseumDto museum : museumList) {
			// 홈페이지관리자 8 && 슈퍼관리자 9 는 박물관 전체목록 노출 
			if(Integer.valueOf(sessionInfo.getAuth_level()) > 7){
				museum.setIsAuthCheck("Y");
				museumAccessCheck = true;
			} else {				
				for (MuseumAuthMapDto auth : authList) {
					if(StringUtils.equals(museum.getMuseum_no(), auth.getMuseum_no())){
						museum.setIsAuthCheck("Y");
					}
				}	
			}			
		}
		// 접근 박물관 권한 체크
		if(StringUtils.isNotBlank(request.getParameter("museum_no"))){			
			for (MuseumDto museum : museumList) {
				if(StringUtils.equals("Y", museum.getIsAuthCheck()) && StringUtils.equals(museum.getMuseum_no(), request.getParameter("museum_no"))){
					museumAccessCheck = true;
					break;
				}
			}			
		}
		if(!museumAccessCheck){
			CommonUtils.printOut(response, CommonUtils.fnAlertAndLocation(message.getMessage("ERROR.ACCESS.AUTH"), CommonWebUtils.getReferer(request)));
			return false;
		}
		
		result.put("museumList", museumList);		
		result.put("museumMenuList", museumService.getMuseumMenuContList(null));
		request.setAttribute("museumAdminLeftMenu", result);
		return museumAccessCheck;
	}
	
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception	{
		if(log.isDebugEnabled())log.debug("===================== [CommonInterceptor START] " + this.getClass().getName());
				
		String ctx = request.getContextPath();
		String reqUri = request.getRequestURI();		
		String[] reqUriArr = StringUtils.split(reqUri, "/");
		
		String siteGubun = "";
		String menuCode = "";
		String linkAddress = "";
		String linkType = "";
		String depth1Menu = "";
		String temp = "";
		
		if(reqUriArr != null){
			siteGubun = reqUriArr[0];
			if(reqUriArr.length > 1){
				depth1Menu = reqUriArr[1];
			}
		}	
		
		temp = StringUtils.replace(reqUri,StringUtils.substring(reqUri,reqUri.lastIndexOf("/")), "");	
		menuCode = StringUtils.replace(StringUtils.substring(temp, temp.lastIndexOf("/")),"/","");
		linkAddress = StringUtils.substring(reqUri, reqUri.lastIndexOf("/")+1);
		linkAddress = StringUtils.substring(linkAddress, 0, linkAddress.lastIndexOf("."));		
		linkType = StringUtils.substring(reqUri, reqUri.lastIndexOf("/")+1);
		linkType = StringUtils.substring(linkType, 0, linkType.lastIndexOf("."));
		
		if(log.isDebugEnabled()) {
			log.debug("reqUri : "+reqUri);
			log.debug("temp : "+temp);
			log.debug("menuCode : "+menuCode);
			log.debug("linkAddress : "+linkAddress);
			log.debug("siteGubun : "+siteGubun);
			log.debug("depth1Menu : "+depth1Menu);	
		}
		
		// 사이트 언어 세션지정
		/*if(StringUtils.isBlank(CommonWebUtils.getSiteLangSession(request)) || StringUtils.isNotBlank(siteLanguage)){
			CommonWebUtils.setSiteLangSession(request, StringUtils.defaultIfBlank(siteLanguage,siteGubun));	
		}*/		
		request.setAttribute("siteLanguage", siteGubun);
		
		// Json 호출이 아닌 경우....
		if(!StringUtils.contains(reqUri, "Json")){
			
			// 사이트 서비스 언어 목록
			List<Map<String, String>> siteServiceLangList = mainService.getSiteServiceLangList();
			request.setAttribute("siteServiceLangList", siteServiceLangList);
			
			/**
			 * 관리자 페이지가 아닌경우 공통 데이터 
			 * */
			if(!StringUtils.equals("siteManage", siteGubun)){				
				// 영월박물관 리스트 목록 (배너관리데이터 > 하단영역 고정)
				request.setAttribute("mainMuseumList", bannerService.getMainBannerList("MUSEUM"));	
				// 유관기관 바로가기 목록 (배너관리데이터 > 하단영역 고정)
				request.setAttribute("mainFamilyList", bannerService.getMainBannerList("FAMILY_SITE"));
			}
			
			
			// 현재 메뉴
			MenuDto menuDto = new MenuDto(); 
			menuDto.setMenu_code(menuCode);
			MenuDto currMenuInfo = menuService.getMenuInfo(menuDto);
			
			String accessMenuCode =null;
			String museumNo = StringUtils.defaultIfBlank(request.getParameter("museum_no"),"");
			String museumMenu = StringUtils.defaultIfBlank(request.getParameter("menu_code"),"");
			
			if(currMenuInfo != null || (StringUtils.isNotBlank(museumNo) && StringUtils.isNotBlank(museumMenu))){
								
				if(currMenuInfo != null){
					accessMenuCode = currMenuInfo.getMenu_code();
				} else {
					currMenuInfo = new MenuDto();
				}
				
				// 박물관 게시판 연동일때..
				if(StringUtils.isNotBlank(museumNo) && StringUtils.isNotBlank(museumMenu)){
					MuseumMenuContDto museum = new MuseumMenuContDto();
					museum.setMuseum_no(museumNo);
					museum.setMenu_code(museumMenu);
					MuseumMenuContDto result = museumService.getMuseumMenuContInfo(museum);
					request.setAttribute("frontMuseumContentInfo", result);
					// 박물관 매핑된 게시판 아이디 세팅
					currMenuInfo.setBoard_id(result.getBoard_id());
				}
				
				// 게시판 연동여부
				if(StringUtils.isNotBlank(currMenuInfo.getBoard_id())){
					// 게시판 속성정보
					BoardConfigDto boardConfigParams = new BoardConfigDto();
					boardConfigParams.setBoard_id(currMenuInfo.getBoard_id());
					BoardConfigDto boardConfigInfo = boardService.getBoardConfigInfo(boardConfigParams);				
					if(boardConfigInfo != null){
						request.setAttribute("viewName", siteGubun+"/board/"+boardConfigInfo.getBoard_type()+"/"+linkType);
						request.setAttribute("boardConfigInfo", boardConfigInfo);	
					}else{
						// 게시판정보가없습니다.
						CommonUtils.printOut(response, CommonUtils.fnAlertAndLocation(message.getMessage("ERROR.BOARDCONFIG.FAIL"), CommonWebUtils.getReferer(request)));
						return false;
					}
				}
			// 등록안된 메뉴 접근제어	
			} else {
				
			}
			
			// 메뉴접근 로그적재 =========================			
			insertMenuAccessLog(request, reqUri, accessMenuCode);
			
			// 메뉴목록 가공
			JSONObject result = null;
			JSONArray jsonArray = null;
			Map<String,Object> menuParams = new HashMap<String, Object>();
					
			List<MenuDto> step1MenuList = new ArrayList<>();
			List<MenuDto> step2MenuList = new ArrayList<>();
			List<MenuDto> step3MenuList = new ArrayList<>();
			
			MenuDto depth1MenuInfo = new MenuDto(); 
			MenuDto depth2MenuInfo = new MenuDto();
			MenuDto depth3MenuInfo = new MenuDto();
			
			menuParams.put("cache", "N");											// cache:Y, file:N
			menuParams.put("deploy", "1440");										// 데이타 재생성 시간(분) 24시간(1440), 0:DB연결
			menuParams.put("key", "SITE");											// 캐시 KEY
			menuParams.put("UPLOAD_DIR_JSON", CommonFileUtils.getTarget(request).concat("/").concat(JProperties.getString("FILE.JSON.DIR")));
			result = CommonJsonUtils.getCommonDeployJsonList(menuService, "getMenuStepList", "MENU_LIST", menuParams);
			if(StringUtils.equals("SUCCESS",result.getString("RESULT_CODE"))) {
	    		jsonArray = JSONArray.fromObject(result.get("RESULT_DATA"));
	    		
	    		if(jsonArray != null && !jsonArray.isEmpty()) {
	    			@SuppressWarnings("unchecked")
					Iterator<JSONObject> iterator = jsonArray.iterator();
		    		MenuDto menu = null;
					while(iterator.hasNext()) {
						JSONObject value = iterator.next();					
						menu = new MenuDto();
						menu.setMenu_code(value.getString("menu_code"));
						menu.setParent_menu_code(value.getString("parent_menu_code"));
						menu.setMenu_name(value.getString("menu_name"));					
						menu.setLink_address(value.getString("link_address"));					
						menu.setIs_new_window(value.getString("is_new_window"));					
						menu.setOrder_step(value.getInt("order_step"));
						menu.setBoard_id(value.getString("board_id"));
						menu.setMenu_auth_level(value.getString("menu_auth_level"));
						if(menu.getOrder_step() == 1){
							step1MenuList.add(menu);
						} else if(menu.getOrder_step() == 2){
							step2MenuList.add(menu);
						} else if(menu.getOrder_step() == 3){
							step3MenuList.add(menu);
						}
					}
					// 자식메뉴 트리구조 형태로 가공
					for (MenuDto step1 : step1MenuList) {
						if(StringUtils.equals(step1.getMenu_code(), menuCode)){						
							depth1MenuInfo = step1;
						}
						
						for (MenuDto step2 : step2MenuList) {
							if(StringUtils.equals(step1.getMenu_code(), step2.getParent_menu_code())){
								step1.getChildren_menu_list().add(step2);
															
								if(StringUtils.equals(step2.getMenu_code(), menuCode)){
									depth1MenuInfo = step1;								
									depth2MenuInfo = step2;
								}
								
								for (MenuDto step3 : step3MenuList) {								
									if(StringUtils.equals(step2.getMenu_code(), step3.getParent_menu_code())){
										step2.getChildren_menu_list().add(step3);
																			
										if(StringUtils.equals(step3.getMenu_code(), menuCode)){
											depth1MenuInfo = step1;
											depth2MenuInfo = step2;										
											depth3MenuInfo = step3;
										}
									}
								}
							}
						}
					}
					request.setAttribute("leftMenuList", step1MenuList);
	    		}
			}
			// 메뉴위치
			List<MenuDto> menuLocationList = new ArrayList<>();
			if(StringUtils.isNotBlank(depth1MenuInfo.getMenu_code()))menuLocationList.add(depth1MenuInfo);
			if(StringUtils.isNotBlank(depth2MenuInfo.getMenu_code()))menuLocationList.add(depth2MenuInfo);
			if(StringUtils.isNotBlank(depth3MenuInfo.getMenu_code()))menuLocationList.add(depth3MenuInfo);
			request.setAttribute("menuLocationList", menuLocationList);
			
			request.setAttribute("depth1MenuInfo", depth1MenuInfo);
			request.setAttribute("depth2MenuInfo", depth2MenuInfo);
			request.setAttribute("depth3MenuInfo", depth3MenuInfo);		
					
			request.setAttribute("currMenuInfo", currMenuInfo);			
			request.setAttribute("menuCode", menuCode);
			request.setAttribute("linkAddress", linkAddress);
			request.setAttribute("ctx", ctx);
			
			// 관리자 박물관 관리 메뉴일때 박물관 목록 및 메뉴 호출 및 권한체크
			if(StringUtils.equals("museumManage", depth1MenuInfo.getMenu_code())){
				if(!getMuseumAdminLeftMenuList(request, response)){
					return false;
				}
			}
			// 사이트 SEO 정보 조회
			request.setAttribute("GOBAL_SITE_SEO_INFO", seoService.getSeoInfo());
			// 이미지 서버 도메인
			request.setAttribute("imageServerUrl", JProperties.getString("IMAGE.SERVER.URL"));
			// 이미지 기본 폴더경로
			request.setAttribute("uploadDefaultUrl", JProperties.getString("IMAGE.SERVER.URL")+"/"+JProperties.getString("FILE.DEFAULT.DIR"));
			// 섬네일 기본 폴더명
			request.setAttribute("uploadImgThumbDir", JProperties.getString("FILE.THUMB.DIR"));
			// 에디터 이미지 기본 폴더명
			request.setAttribute("uploadImgEditorDir", JProperties.getString("FILE.CONT.DIR"));
			// SNS 페이스북 API_KEY
			request.setAttribute("snsFacebookApiKey", JProperties.getString("SNS.FACEBOOK.API.KEY"));
			// SNS 카카오톡 API_KEY
			request.setAttribute("snsKakaoApiKey", JProperties.getString("SNS.KAKAO.API.KEY"));
			// requestURL
			request.setAttribute("requestURL", request.getRequestURL());
		}
		
		// 서버 도메인
		request.setAttribute("siteDomain", JProperties.getString("SITE.DOMAIN"));
		// 사이트 구분
		request.setAttribute("siteGubun", siteGubun);
		
		// 디바이스 체크
		String userAgent = request.getHeader("user-agent");
		String[] browser = {"iPhone", "iPod", "IEMobile", "Mobile", "lgtelecom", "PPC"};		
		boolean isMobileAccess = false; 
		for(int i=0; i<browser.length; i++) {
			if(StringUtils.contains(userAgent, browser[i])) {
				isMobileAccess = true;
				break;
			}
		}
		// 모바일 접근여부
		request.setAttribute("isMobileAccess", isMobileAccess);

		if(log.isDebugEnabled())log.debug("===================== [CommonInterceptor END] " + this.getClass().getName());
		return true;
	}
	
	/**
	 * 메뉴접근로그적재
	 * */
	private void insertMenuAccessLog(HttpServletRequest request, String url, String menuCode) {
		
		try {
			
			Map<String, String> params = new HashMap<String, String>();
			
			params.put("access_address", url);
			params.put("menu_code", menuCode);
			params.put("referer", CommonWebUtils.getReferer(request));			
			params.put("parameter", CommonWebUtils.getParameter(request, "", ""));
			params.put("client_ip", CommonUtils.getClientIpAddress(request));
			
			@SuppressWarnings("rawtypes")
			Enumeration headers = request.getHeaderNames();
			String headerName = "";
			StringBuilder sb = new StringBuilder(1024);
			while(headers.hasMoreElements()){
				headerName = (String)headers.nextElement();
				sb.append(headerName).append(" : ").append(request.getHeader(headerName)).append("\n");
				//if(log.isDebugEnabled())log.debug("@@@@@@@@ headerName : "+headerName+", "+request.getHeader(headerName));					
			}
			params.put("header", sb.toString());
			
			// 접근로그 적재
			menuService.insertMenuAccessLog(params);
			
			} catch (Exception e) {
			if(log.isDebugEnabled())log.debug(e.getMessage());
		}		
	}
}