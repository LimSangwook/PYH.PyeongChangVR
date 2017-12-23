package com.module.user.controller;

import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.common.util.CommonApiUtils;
import com.common.util.CommonUtils;
import com.common.util.CommonWebUtils;
import com.common.util.JProperties;
import com.common.util.StringEncrypter;

import com.module.museum.dto.MuseumAuthMapDto;
import com.module.museum.service.MuseumService;
import com.module.user.dto.UserDto;
import com.module.user.service.UserService;

import net.sf.json.JSONObject;

@Controller
public class LoginController extends CommonWebUtils {

	private Logger log = LoggerFactory.getLogger(getClass());
	
	@Resource(name = "messageSourceAccessor")
	private MessageSourceAccessor message;
	
	@Autowired
	private Properties config;
	
	@Autowired
	private UserService userService;
		
	@Autowired
	private MuseumService museumService;
	
	/**
	 * 관리자 로그인 화면
	 * @param request
	 * @param user 
	 * @return
	 * @throws Exception
	 */	
	@RequestMapping({"siteManage/login"})
	public ModelAndView adminLogin(HttpServletRequest request, UserDto user) throws Exception{
		if(log.isDebugEnabled())log.debug("[START] " + this.getClass().getName() + ".adminLogin()");
		
		ModelAndView mav = new ModelAndView("empty/admin/login/login");
		
		user.setUser_id(CommonWebUtils.getCookie(request, "siteManageLoginId"));
		mav.addObject("theForm", user);
		
		if(log.isDebugEnabled())log.debug("[END] " + this.getClass().getName() + ".adminLogin()");
		return mav;
	}
	
	/**
	 * 사용자 로그인 화면
	 * @param request
	 * @param user 
	 * @return
	 * @throws Exception
	 */	
	@RequestMapping("front/login/login")
	public ModelAndView frontLogin(HttpServletRequest request, UserDto user) throws Exception{
		if(log.isDebugEnabled())log.debug("[START] " + this.getClass().getName() + ".frontLogin()");
		
		ModelAndView mav = new ModelAndView("front/login/login");
				
		mav.addObject("theForm", user);
		
		if(log.isDebugEnabled())log.debug("[END] " + this.getClass().getName() + ".frontLogin()");
		return mav;
	}
	
	/**
	 * 관리자 로그인
	 * @param request
	 * @param user 
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="siteManage/loginProc",method=RequestMethod.POST)
	public ModelAndView adminLoginProc(HttpServletRequest request, HttpServletResponse response, UserDto user) throws Exception{
		if(log.isDebugEnabled())log.debug("[START] " + this.getClass().getName() + ".adminLoginProc()");
		
		ModelAndView mav = new ModelAndView();
				
		try {
			if(StringUtils.isNotBlank(user.getUser_id()) && StringUtils.isNotBlank(user.getPasswd())){
				StringEncrypter se = new StringEncrypter(config.getProperty("SITE.PW.KEY"), config.getProperty("SITE.PW.INITIALVECTOR"));
				user.setPasswd(se.encrypt(user.getPasswd()));
				
				UserDto result = userService.getAdminLoginInfo(user);
				String redirectUrl = "/siteManage/basicset/basic/basicInfo/basicInfo.do";
				if(result != null){
					String mUserMuseumNo = "";
					
					// 1) 로그인 실패 카운트 쿠키 초기화
					removeCookie(request, response, "loginFailCnt_"+user.getUser_id());
					
					// 2) 브랜치 관리자는 박물관 권한 체크
					if(StringUtils.equals("7", result.getAuth_level())){
						List<MuseumAuthMapDto> authList = museumService.getMuseumAuthMapList(result.getUser_id());
						boolean museumAuthCheck = false;
						for (MuseumAuthMapDto data : authList) {
							if(StringUtils.equals(user.getUser_id(), data.getUser_id())){
								museumAuthCheck = true;
								mUserMuseumNo = data.getMuseum_no();
								result.setMuseum_no(mUserMuseumNo);
							}
							if(museumAuthCheck)break;
						}
						if(!museumAuthCheck)throw new Exception("ERROR.ACCESS.AUTH");
						redirectUrl = "/siteManage/museumManage/museumForm.do?museum_no="+mUserMuseumNo;
					}
					
					// 3) 아이디 기억하기 (쿠키생성)
					if(StringUtils.equals("Y", request.getParameter("isAdminIdSave"))){
						setCookie(request, response, "siteManageLoginId", result.getUser_id(), 30);
					} else {
						removeCookie(request, response, "siteManageLoginId");
					}					
					
					// 4) 세션정보 등록
					setSession(request, result);
					
					// 5) 관리자 메인화면 이동
					redirectView(mav, "", redirectUrl);
					
				} else {					
					// 동일계정 로그인 5회 실패시 계정잠김 처리
					if(!userService.userIdDoubleCheck(request, user)){
						int loginFailCnt = Integer.valueOf(StringUtils.defaultIfBlank(getCookie(request, "loginFailCnt_"+user.getUser_id()),"0"));						
						if(loginFailCnt >= 4){
							// 계정 차단 처리하기~~~~~~~
							userService.updateUserIdBlock(user);
							throw new Exception("ERROR.LOGIN.PW.LIMIT");
						// 로그인 실패 시 실패 카운트 증가
						} else {
							setCookie(request, response, "loginFailCnt_"+user.getUser_id(), String.valueOf(++loginFailCnt), 30); // 일단 한달 저장
							redirectView(mav, message.getMessage("ERROR.LOGIN.PW.FAIL", new String[] {String.valueOf(loginFailCnt)}), "login.do");
						}						
						if(log.isDebugEnabled())log.debug("@@@@@ loginFailCnt : "+user.getUser_id()+" ["+loginFailCnt+"]");
					} else {
						throw new Exception("Not found User");
					}
				}
			} else {
				throw new Exception("Access Fail");
			}
		} catch (Exception e) {
			if(log.isWarnEnabled())log.warn(e.getMessage());
			redirectView(mav, message.getMessage(e.getMessage(),message.getMessage("ERROR.LOGIN.FAIL")), "login.do");
		}
		
		if(log.isDebugEnabled())log.debug("[END] " + this.getClass().getName() + ".adminLoginProc()");
		return mav;
	}
	
	/**
	 * 관리자 로그 아웃
	 * @param request  
	 * @return mav
	 * @throws Exception
	 */	
	@RequestMapping("siteManage/logout")
	public ModelAndView adminLogout(HttpServletRequest request, HttpServletResponse response) throws Exception {
		if(log.isDebugEnabled())log.debug("[START] " + this.getClass().getName() + ".logout()");
		
		ModelAndView mav = new ModelAndView();		
		
		// 로그인 페이지 이동
		redirectView(mav, "", "/siteManage/login.do");		
		// 세션 삭제
		setSession(request, null);
		
		if(log.isDebugEnabled())log.debug("[END] " + this.getClass().getName() + ".logout()");
		return mav;
	}
	
	/**
	 *  사용자 로그 아웃
	 * @param request  
	 * @return mav
	 * @throws Exception
	 */	
	@RequestMapping("front/logout.do")
	public ModelAndView frontLogout(HttpServletRequest request, HttpServletResponse response) throws Exception {
		if(log.isDebugEnabled())log.debug("[START] " + this.getClass().getName() + ".frontLogout()");
		
		ModelAndView mav = new ModelAndView();		
		
		// 로그인 페이지 이동
		redirectView(mav, "", "/front/index.do");		
		// 세션 삭제
		setSession(request, null);
		
		if(log.isDebugEnabled())log.debug("[END] " + this.getClass().getName() + ".frontLogout()");
		return mav;
	}
	
	/**
	 * 토큰생성
	 * @param request 
	 * @return ModelAndView
	 * @throws Exception
	 */
	@RequestMapping(value="generateTokenJson",method=RequestMethod.POST)
	public ModelAndView generateTokenJson(HttpServletRequest request) throws Exception{
		if(log.isDebugEnabled())log.debug("[START] " + this.getClass().getName() + ".generateTokenJson()");
				
		ModelAndView mav = new ModelAndView("jsonViewer");
				
		// 로그인 인증 토큰 생성
		String token = CommonUtils.getSecureRandom();
		setToken(request, config.getProperty("LOGIN.TOKEN.KEY"), token);		
		mav.addObject("RESULT_DATA",token);
		
		log.debug("token : "+token);
		
		if(log.isDebugEnabled())log.debug("[END] " + this.getClass().getName() + ".generateTokenJson()");
		return mav;
	}
	
	/**
	 * sns 로그인처리
	 * @param request
	 * @param user 
	 * @return ModelAndView
	 * @throws Exception
	 */
	@RequestMapping(value="snsLoginProc",method=RequestMethod.POST)
	public ModelAndView snsLoginProc(HttpServletRequest request, UserDto user) throws Exception{
		if(log.isDebugEnabled())log.debug("[START] " + this.getClass().getName() + ".snsLoginProc()");
				
		ModelAndView mav = new ModelAndView("jsonViewer");
		
		try {
			// 로그인 토큰 값 비교
			if(!StringUtils.equals(getToken(request, config.getProperty("LOGIN.TOKEN.KEY")), user.getToken()) || StringUtils.isBlank(user.getToken())){
				throw new Exception("Login Token Auth Fail...");
			}
			
			if(StringUtils.equals("faceb", user.getSns_type()) 
					|| StringUtils.equals("kakao", user.getSns_type())
					|| StringUtils.equals("naver", user.getSns_type())){
				
				if(StringUtils.isNotBlank(user.getUser_id()) && StringUtils.isNotBlank(user.getUser_name())){
					
					// SNS 로그인 정보 등록
					StringEncrypter se = new StringEncrypter(JProperties.getString("SITE.PW.KEY"), JProperties.getString("SITE.PW.INITIALVECTOR"));
					user.setUser_id(se.encrypt(user.getUser_id()));
					
					userService.insertSnsLoginInfo(user.getUser_id(), user.getSns_type(), user.getUser_name());
					
					UserDto sessionInfo = new UserDto();
					sessionInfo.setUser_id(user.getUser_id());
					sessionInfo.setUser_name(user.getUser_name());
					sessionInfo.setSns_type(user.getSns_type());
					sessionInfo.setAuth_level("1"); // 일반사용자 권한
					
					
					// 로그인 정보 세션저장
					setSession(request, sessionInfo);
					// 로그인 인증토큰 초기화
					setToken(request, user.getUser_id(), null);
					
					log.debug("SNS Login : "+sessionInfo.getUser_id()+", "+sessionInfo.getUser_name()+", "+sessionInfo.getSns_type());
					
					mav.addObject("RESULT_CODE","SUCCESS");
					
				} else {
					throw new Exception("Sns Login Validator Fail...");
				}				
			} else {
				throw new Exception("SnsType Login Fail...");
			}			
		} catch (Exception e) {
			if(log.isWarnEnabled())log.warn(e.getMessage());
			mav.addObject("RESULT_CODE","FAIL");
		}
		
		if(log.isDebugEnabled())log.debug("[END] " + this.getClass().getName() + ".snsLoginProc()");
		return mav;
	}
	
	/**
	 * 네이버 콜백 페이지
	 * @param request
	 * @param response
	 * @param params
	 * @throws Exception
	 */	
	@RequestMapping("naverAuthCallback")
	public void naverAuthCallback(HttpServletRequest request, HttpServletResponse response, @RequestParam Map<String, String> params) throws Exception {
		if(log.isDebugEnabled())log.debug("[START] " + this.getClass().getName() + ".naverAuthCallback()");
			
		try {
			String code = params.get("code");
			String state = params.get("state");
			String error = params.get("error");
			//String errorDescription = params.get("error_description");		
			
			if(StringUtils.isNotBlank(code) && StringUtils.isEmpty(error)){			
				log.debug("로그인성공");
				// 접근 토큰 발급 요청
				String result = CommonApiUtils.getUrlReader("https://nid.naver.com/oauth2.0/token?grant_type=authorization_code&client_id="+config.getProperty("NAVER.CLIENT.ID")+"&client_secret="+config.getProperty("NAVER.CLIENT.SECRET")+"&code="+code+"&state="+state,null);
				JSONObject jsonData = JSONObject.fromObject(result);
				
				log.debug("★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★");			
				log.debug("result : "+result);
				log.debug("token : "+jsonData.getString("access_token"));
				log.debug("★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★");
				
				result = CommonApiUtils.getUrlReader("https://openapi.naver.com/v1/nid/me",jsonData.getString("token_type")+" "+jsonData.getString("access_token"));
				jsonData = JSONObject.fromObject(result);
				JSONObject profile = JSONObject.fromObject(jsonData.getString("response"));
				
				log.debug("★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★");			
				log.debug("profile : "+result);
				log.debug("email : "+profile.getString("email"));
				log.debug("name : "+profile.getString("name"));
				log.debug("nickname : "+profile.getString("nickname"));
				log.debug("★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★");
				
				// 로그인 인증 토큰 생성
				String token = CommonUtils.getSecureRandom();
				setToken(request, config.getProperty("LOGIN.TOKEN.KEY"), token);
				
				CommonUtils.printOut(response, CommonUtils.fnJavascriptFunction("opener.fnNaverResultCallback('"+result+"','"+token+"');self.close();"));
			} else {			
				log.debug("사용자 취소");
				CommonUtils.printOut(response, CommonUtils.fnJavascriptFunction("self.close();"));
			}
		} catch (Exception e) {
			if(log.isWarnEnabled())log.warn(e.getMessage());
			CommonUtils.printOut(response, CommonUtils.fnAlertAndjavascriptFunction(message.getMessage("ERROR.AUTH.FAIL"),"self.close();"));
		}
		
		if(log.isDebugEnabled())log.debug("[END] " + this.getClass().getName() + ".naverAuthCallback()");
	}
}
