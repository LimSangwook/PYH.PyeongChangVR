package com.common.util;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Enumeration;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.util.WebUtils;

import com.module.user.dto.UserDto;

public class CommonWebUtils {
		
	/**
	 * 처리내용 셋팅
	 * @param mav
	 * @param message
	 * @param forward
	 * @return
	 */
	public void redirectView(ModelAndView mav, String message, String forward) {		
		redirectView(mav, message, forward, null);
	}
	
	/**
	 * 처리내용 셋팅(컨펌 후 이동)
	 * @param mav
	 * @param message
	 * @param forward
	 * @return
	 */
	public void redirectView(ModelAndView mav, String message, String forward, String confirmForward) {		
		mav.setViewName("common.message");		
		mav.addObject("Msg", message);
		mav.addObject("Forward", forward);
		mav.addObject("ConfirmForward", confirmForward);
	}
	
	/**
	 * setForward ViewName 세팅
	 * @param request
	 * @param mav
	 * @return
	 */
	public void setForward(HttpServletRequest request, ModelAndView mav) {
		if(request.getAttribute("viewName") != null){
			mav.setViewName((String)request.getAttribute("viewName"));	
		}		
	}
	
	public static String getParameter(HttpServletRequest request, String startWith, String ignoreParameter) throws UnsupportedEncodingException {
		StringBuffer result = new StringBuffer();
		
		String[] iParams = StringUtils.split(ignoreParameter, "|");
		@SuppressWarnings("rawtypes")
		Enumeration names = request.getParameterNames();
		while(names.hasMoreElements()) {
			String name = (String) names.nextElement();
			if(!ArrayUtils.contains(iParams, name)) {
				String value = (String) request.getParameter(name);
				if(StringUtils.isNotBlank(value)) {
					if(StringUtils.isBlank(result.toString())) {
						result.append(startWith);
						result.append(name).append("=").append(URLEncoder.encode(CommonUtils.utf8(value), "UTF-8"));
					} else {
						result.append("&").append(name).append("=").append(URLEncoder.encode(CommonUtils.utf8(value), "UTF-8"));
					}
				}
			}
		}		
		return result.toString();
	}
	
	public static String getReferer(HttpServletRequest request) {
		String referer = request.getHeader("referer");
		if(referer == null) {
			referer = "/";
		}
		return referer;
	}	
	
	public static UserDto getSession(HttpServletRequest request) {
		return (UserDto) WebUtils.getSessionAttribute(request, JProperties.getString("SYSTEM.LOGIN.SESSION"));
	}
	
	public static void setSession(HttpServletRequest request, UserDto user) {		
		WebUtils.setSessionAttribute(request, JProperties.getString("SYSTEM.LOGIN.SESSION"), user);
	}
	
	public static String getSiteLangSession(HttpServletRequest request) {
		return (String) WebUtils.getSessionAttribute(request, "SITE_ACTIVE_LANG");
	}
	
	public static void setSiteLangSession(HttpServletRequest request, String lang) {		
		WebUtils.setSessionAttribute(request, "SITE_ACTIVE_LANG", lang);
	}
	
	public static String getToken(HttpServletRequest request, String tokenKey) {
		return (String) WebUtils.getSessionAttribute(request, tokenKey);
	}
	
	public static void setToken(HttpServletRequest request, String tokenKey, String token) {			    
		WebUtils.setSessionAttribute(request, tokenKey, token);
	}
	
	public static String getCookie(HttpServletRequest request, String key){
		Cookie[] cookies = request.getCookies();
		String cName = "";
		if(cookies != null){	         
	        for(int i=0; i < cookies.length; i++){
	            Cookie c = cookies[i];	             
	            // 저장된 쿠키 이름을 가져온다
	            cName = c.getName();             
	            if(cName.equals(key)){
	            	return c.getValue();
	            }
	        }
	    }
		return null;
	}
	
	public static void setCookie(HttpServletRequest request, HttpServletResponse response, String key, String val, int day){
		try {
			Cookie cookie = new Cookie(key, val);
			cookie.setMaxAge(60*60*24*day);
			cookie.setPath("/");
			response.addCookie(cookie);
		} catch (Exception e) {
			e.printStackTrace();
		}		
	}
	
	public static void removeAllCookie(HttpServletRequest request, HttpServletResponse response){
		removeCookie(request, response, "");
	}

	public static void removeCookie(HttpServletRequest request, HttpServletResponse response, String key){		
		Cookie[] cookies = request.getCookies();		
		for(int i=0; i < cookies.length; i++){				
			cookies[i].setMaxAge(0);
			cookies[i].setPath("/");
			if(key.equals(cookies[i].getName())){
				response.addCookie(cookies[i]);	
			}
        }				
	}
	
	public static String requestReplace (String paramValue, String gubun) {
        String result = "";
        
        if (paramValue != null) {
        	
        	paramValue = paramValue.replaceAll("<", "&lt;").replaceAll(">", "&gt;");

        	paramValue = paramValue.replaceAll("\\*", "");
        	paramValue = paramValue.replaceAll("\\?", "");
        	paramValue = paramValue.replaceAll("\\[", "");
        	paramValue = paramValue.replaceAll("\\{", "");
        	paramValue = paramValue.replaceAll("\\(", "");
        	paramValue = paramValue.replaceAll("\\)", "");
        	paramValue = paramValue.replaceAll("\\^", "");
        	paramValue = paramValue.replaceAll("\\$", "");
        	paramValue = paramValue.replaceAll("'", "");
        	paramValue = paramValue.replaceAll("@", "");
        	paramValue = paramValue.replaceAll("%", "");
        	paramValue = paramValue.replaceAll(";", "");
        	paramValue = paramValue.replaceAll(":", "");
        	paramValue = paramValue.replaceAll("-", "");
        	paramValue = paramValue.replaceAll("#", "");
        	paramValue = paramValue.replaceAll("--", "");
        	paramValue = paramValue.replaceAll("-", "");
        	paramValue = paramValue.replaceAll(",", "");
        	
        	if(gubun != "encodeData"){
        		paramValue = paramValue.replaceAll("\\+", "");
        		paramValue = paramValue.replaceAll("/", "");
            paramValue = paramValue.replaceAll("=", "");
        	}
        	
        	result = paramValue;
            
        }
        return result;
  }
    public String upload(HttpServletRequest request, MultipartFile file, String attachPath) {
        return upload(request, file, attachPath, "");
    }

    public String upload(HttpServletRequest request, MultipartFile file, String attachPath, String foreignKey) {
        String uploadPath = request.getSession().getServletContext().getRealPath(attachPath);
        String fileName = file.getOriginalFilename();

        if(StringUtils.isNotBlank(foreignKey)) {
            uploadPath = uploadPath + File.separator + foreignKey;
        }

        File attachFile = new File(uploadPath + File.separator + fileName);
        if(!attachFile.exists()) {
            attachFile.getParentFile().mkdirs();
        }

        try {
            file.transferTo(attachFile);
        } catch (IllegalStateException | IOException e) {
            e.printStackTrace();
        }
        return attachFile.getPath();
    }
}