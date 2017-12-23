package com.common.interceptor;

import java.util.List;

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

import com.common.util.CommonUtils;
import com.common.util.CommonWebUtils;
import com.common.util.JProperties;
import com.module.menu.service.MenuService;
import com.module.museum.dto.MuseumDto;
import com.module.museum.service.MuseumService;
import com.module.user.dto.UserDto;

@Controller("authCheckInterceptor")
public class AuthCheckInterceptor extends HandlerInterceptorAdapter {

	private Logger log = LoggerFactory.getLogger(getClass());
	
	@Resource(name = "messageSourceAccessor")
	private MessageSourceAccessor message;
	
	@Autowired
	private MenuService menuService;
	
	@Autowired
	private MuseumService museumService;
	
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception	{
		if(log.isDebugEnabled())log.debug("===================== [AuthCheckInterceptor START] " + this.getClass().getName());
		
		String ctx = request.getContextPath();
		String reqUri = request.getRequestURI();
		UserDto sessionInfo = CommonWebUtils.getSession(request);
		String siteDomain = JProperties.getString("SITE.DOMAIN");
		String frontDomain = JProperties.getString("FRONT.SITE.DOMAIN");
				
		String accessDomain = request.getServerName();
		
		log.debug("accessDomain : "+accessDomain+", request.getServletPath() : "+request.getServletPath());
		
		// 외부도메인 접근 시 해당 브랜치 박물관 바로가기 - 단, 메인화면 접근시...
		if(!StringUtils.contains(siteDomain, accessDomain) && StringUtils.equals("/front/index.do",request.getServletPath().toString())){
			String redirectURL = "";	
			
			// 포럼 사이트 이동
			if(StringUtils.contains(frontDomain, accessDomain)){
				redirectURL = "http://".concat(accessDomain).concat("/front/index.do");				
			}
			
			if(StringUtils.isNotBlank(redirectURL)){
				CommonUtils.printOut(response, CommonUtils.fnlocation(redirectURL));			
				return false;
			}
		}
		
		// 사이트 도메인 체크 리다이렉트		
		if(StringUtils.contains(siteDomain, "www")){
			if(!StringUtils.contains((String)request.getRequestURL().toString(),"http://www")){
				String requestURL = request.getRequestURL().toString();
				requestURL = StringUtils.replace(requestURL, "http://", "http://www.");
				requestURL = StringUtils.replace(requestURL, "co.kr", "com");
				CommonUtils.printOut(response, CommonUtils.fnlocation(requestURL+CommonWebUtils.getParameter(request, "?", "")));			
				return false;
			}
		}
				
		// ★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★
		//												◀ 권한 체크 영역 ▶
		// ★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★
		// 관리자 페이지 세션 확인
		if(sessionInfo == null && reqUri.indexOf("/siteManage/") != -1 && reqUri.indexOf("/login") == -1) {			
			CommonUtils.printOut(response, CommonUtils.fnAlertAndLocation(message.getMessage("ERROR.SESSION.FAIL"), ctx+"/siteManage/login.do"));			
			return false;
		}
		// 회원전용 서비스 세션 확인
		if(sessionInfo == null && StringUtils.contains(reqUri, "/boardForm.do")){
			CommonUtils.printOut(response, CommonUtils.fnConfirmAlertAndLocation("로그인이 필요한 서비스입니다.\\n\\n로그인 페이지로 이동 하시겠습니까?", "/front/login/login.do?redirectUrl="+request.getRequestURL()+CommonWebUtils.getParameter(request, "?", ""), CommonWebUtils.getReferer(request)));			
			return false;
		}
		
		// 일반회원 관리자 접근 시 인증 후 로그인시 프론트 페이지 이동
		if(sessionInfo != null && reqUri.indexOf("/siteManage/") != -1 && StringUtils.equals("1", sessionInfo.getAuth_level())){
			CommonUtils.printOut(response, CommonUtils.fnAlertAndLocation(message.getMessage("ERROR.ACCESS.AUTH"), ctx+"/front/index.do"));
			return false;
		}
		
		// 일반회원 로그인 후 로그인 페이지 이동시 리다이렉트 처리
		if(sessionInfo != null && reqUri.indexOf("/front/") != -1 && reqUri.indexOf("/login") != -1 && (reqUri.indexOf("/logout") == -1) && StringUtils.equals("1", sessionInfo.getAuth_level())) {			
			CommonUtils.printOut(response, CommonUtils.fnlocation(ctx+"/front/index.do"));			
			return false;
		}
				
		// 관리자 페이지 로그인 후 로그인 페이지 이동시 리다이렉트 처리
		if(sessionInfo != null && reqUri.indexOf("/siteManage/") != -1 && reqUri.indexOf("/login") != -1 && (reqUri.indexOf("/logout") == -1) && !StringUtils.equals("1", sessionInfo.getAuth_level())) {			
			CommonUtils.printOut(response, CommonUtils.fnlocation(ctx+"/siteManage/basic/basicInfo/basicInfo.do"));			
			return false;
		}
		

		// ★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★
		
	    
		if(log.isDebugEnabled())log.debug("===================== [AuthCheckInterceptor START] " + this.getClass().getName());
		return true;
	}
}