package com.module.vr.controller;

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
import com.module.vr.dto.VrSiteDto;
import com.module.vr.dto.VrStyleMovieDto;
import com.module.vr.service.VrSiteService;
import com.module.vr.service.VrStyleService;

@Controller
@RequestMapping("/siteManage/vrmanager/style/movie/")
public class StyleMovieController extends CommonWebUtils {
	private Logger log = LoggerFactory.getLogger(getClass());
	@Resource(name = "messageSourceAccessor")
	private MessageSourceAccessor message;

	@Autowired
	private VrSiteService vrSiteService;
	@Autowired
	private VrStyleService vrStyleService;

	// ######   MOVIE LIST   #####
	@RequestMapping("/movieList")
	public ModelAndView movieList(HttpServletRequest request, VrStyleMovieDto vrStyleMovieDto) throws Exception{
		if(log.isDebugEnabled())log.debug("[START] " + this.getClass().getName() + ".movieList()");

		ModelAndView mav = new ModelAndView("siteManage/vr/style/movieList");

		try {
			mav.addObject("result", vrStyleService.getVrStyleMovieList(vrStyleMovieDto));
			mav.addObject("theForm", vrStyleMovieDto);
//			mav.addObject("pageNavigation", PagingUtil.printPageNavi(vrStyleMovieDto, getParameter(request,"movieListList.do?","vr_style_movie_id|page")));
		} catch (Exception e) {
			if(log.isWarnEnabled())log.warn(e.getMessage());
		}

		if(log.isDebugEnabled())log.debug("[END] " + this.getClass().getName() + ".movieList()");
		return mav;
	}


	// ######   MOVIE FORM   #####
	@RequestMapping("/movieForm")
	public ModelAndView movieForm(HttpServletRequest request, VrStyleMovieDto vrStyle) throws Exception{
		if(log.isDebugEnabled())log.debug("[START] " + this.getClass().getName() + ".movieForm()");
		ModelAndView mav = new ModelAndView("siteManage/vr/style/movieForm");

		try {
			if(StringUtils.isNotBlank(vrStyle.getVr_style_movie_id())){
				VrStyleMovieDto result = vrStyleService.getVrStyleMovieForm(vrStyle);
				if(result != null){
				    vrStyle = (VrStyleMovieDto)result.clone();
				    vrStyle.setAct("update");
				} else {
				    throw new Exception("Not Found VR_Site..");
				}
			}
			mav.addObject("siteList", vrSiteService.getVrSiteList(new VrSiteDto()));
			mav.addObject("theForm", vrStyle);

		} catch (Exception e) {
			if(log.isWarnEnabled())log.warn(e.getMessage());
			redirectView(mav, message.getMessage("ERROR.NO.DATA"), "movieList.do");
		}

		if(log.isDebugEnabled())log.debug("[END] " + this.getClass().getName() + ".movieForm()");
		return mav;
	}

	@RequestMapping(method=RequestMethod.POST, value="/movieSave")
    public ModelAndView movieSave(HttpServletRequest request, VrStyleMovieDto vrStyleMovieDto) throws Exception{
        if(log.isDebugEnabled())log.debug("[START] " + this.getClass().getName() + ".movieSave()");

        ModelAndView mav = new ModelAndView();
        String returnPage = "movieList.do";

        try {
            if(StringUtils.equals("insert", vrStyleMovieDto.getAct())){
                vrStyleService.insertVrStyleMovie(vrStyleMovieDto);
            } else if(StringUtils.equals("update", vrStyleMovieDto.getAct())){
                vrStyleService.updateVrStyleMovie(vrStyleMovieDto);
            } else if(StringUtils.equals("delete", vrStyleMovieDto.getAct())){
                vrStyleService.deleteVrStyleMovie(vrStyleMovieDto);
            }
            redirectView(mav, "", returnPage);
        } catch (Exception e) {
            if(log.isWarnEnabled())log.warn(e.getMessage());
            redirectView(mav, message.getMessage("ERROR.ACCESS.FAIL"), "movieForm.do");
        }

        if(log.isDebugEnabled())log.debug("[END] " + this.getClass().getName() + ".movieSave()");
        return mav;
    }
}
