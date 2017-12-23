package com.module.main.controller;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.common.util.CommonUtils;
import com.common.util.CommonWebUtils;
import com.module.banner.service.BannerService;
import com.module.main.service.MainService;
import com.module.museum.dto.MuseumDto;
import com.module.museum.service.MuseumService;
import com.module.popup.dto.PopupDto;
import com.module.popup.service.PopupService;
import com.module.seo.dto.SeoDto;
import com.module.seo.service.SeoService;

@Controller
public class MainController extends CommonWebUtils{

	@Resource(name = "messageSourceAccessor")
	private MessageSourceAccessor message;
	
	private Logger log = LoggerFactory.getLogger(getClass());
	
	/*@Value("#{config['SITE.NAME']}")
	String siteName;*/
	
	@Autowired
	private MainService mainService;
	
	@Autowired
	private SeoService seoService;
	
	@Autowired
	private BannerService bannerService;
	
	@Autowired
	private PopupService popupService;

	
	
	
	/**
	 * 평창 VR 메인
	 * */
	@RequestMapping("front/index")
	public ModelAndView forumIndex(HttpServletRequest request) throws Exception{
		if(log.isDebugEnabled())log.debug("[START] " + this.getClass().getName() + ".front_index()");
		
		ModelAndView mav = new ModelAndView("front.main.layout");
		
		try {			
			// 팝업목록			
			List<PopupDto> popupList = popupService.getMainActivePopupList();
			for (PopupDto data : popupList) {
				if(StringUtils.isNotBlank(getCookie(request, "DISABLED_POPUP_"+data.getPopup_key()))){
					data.setIs_show_yn("N");
				}	
			}
			mav.addObject("mainPopupList", popupList);
			
			/* 현재 상중하단 사진은 가져오기만 하고 뿌리지않음 */
			// 메인 상단비주얼
			mav.addObject("mainTopVisual", bannerService.getMainBannerList("MAIN_TOP"));
			// 메인 중앙비주얼
			mav.addObject("mainMidVisual", bannerService.getMainBannerList("MAIN_MID"));
			// 메인 하단비주얼
			mav.addObject("mainBotVisual", bannerService.getMainBannerList("MAIN_BOT"));

			
		} catch (Exception e) {
			if(log.isWarnEnabled())log.warn(e.toString());
		}
		
		if(log.isDebugEnabled())log.debug("[END] " + this.getClass().getName() + ".front_index()");
		return mav;
	}
	

	
	/**
	 * 관리자 메인
	 * */
	@RequestMapping("/siteManage/index")
	public ModelAndView adminMain() throws Exception{
		if(log.isDebugEnabled())log.debug("[START] " + this.getClass().getName() + ".adminMain()");
		
		ModelAndView mav = new ModelAndView();
		
		try {			
			String currDate = CommonUtils.getCurrentDateTime("yyyy년MM월dd일");
			mav.addObject("currDate", currDate);
			mav.addObject("currWeek", CommonUtils.getWeekName(Integer.parseInt(StringUtils.substring(currDate, 0, 4)), Integer.parseInt(StringUtils.substring(currDate, 5, 7)), Integer.parseInt(StringUtils.substring(currDate, 8, 10))));
						
		} catch (Exception e) {
			if(log.isWarnEnabled())log.warn(e.getMessage());
		}
		
		if(log.isDebugEnabled())log.debug("[END] " + this.getClass().getName() + ".adminMain()");
		return mav;
	}
	
	/**
	 * 사이트 기본정보
	 * @param request
	 * @param boardDto 
	 * @return ModelAndView
	 * @throws Exception
	 */	
	@RequestMapping("/siteManage/**/basicInfo")
	public ModelAndView basicInfo(HttpServletRequest request) throws Exception{
		if(log.isDebugEnabled())log.debug("[START] " + this.getClass().getName() + ".basicInfo()");
		
		ModelAndView mav = new ModelAndView("siteManage/basic/basicInfo");
		try {
			long fileSize =  mainService.getFileTotalUseSize();
			long dbSize = mainService.getDataBaseTotalUseSize();
			
			// 개설일
			SeoDto seoInfo = seoService.getSeoInfo();
			mav.addObject("openDay", seoInfo.getOpen_day());
			// 회원수
			//mav.addObject("memberTotalCount", mainService.getMemberTotalCount());
			// 작성글수
			mav.addObject("postTotalCount", mainService.getPostTotalCount());
			// 답변글수
			mav.addObject("replyTotalCount", mainService.getPostReplyTotalCount());
			// 총사용량
			mav.addObject("totalUseSize", CommonUtils.convertFileSize(fileSize+dbSize));
			// 파일사용량			
			mav.addObject("fileUseSize", CommonUtils.convertFileSize(fileSize));
			// DB사용량			
			mav.addObject("dataBaseUseSize", CommonUtils.convertFileSize(dbSize));			
			// SMS 잔여포인트
			//mav.addObject("smsPoint", mainService.getSmsPoint());
			
		} catch (Exception e) {
			if(log.isWarnEnabled())log.warn(e.getMessage());
		}		
		
		if(log.isDebugEnabled())log.debug("[END] " + this.getClass().getName() + ".basicInfo()");
		return mav;
	}
}
