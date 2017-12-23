package com.module.vr.controller;

import java.io.File;

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
import com.module.vr.dto.VrStyleMusicDto;
import com.module.vr.service.VrStyleService;

@Controller
@RequestMapping({"/siteManage/vrmanager/style/music"})
public class StyleMusicController extends CommonWebUtils {
	private Logger log = LoggerFactory.getLogger(getClass());
	@Resource(name = "messageSourceAccessor")
	private MessageSourceAccessor message;

	@Autowired
	private VrStyleService vrStyleService;

	// ######   MUSIC LIST   #####
	@RequestMapping("/musicList")
	public ModelAndView musicList(HttpServletRequest request, VrStyleMusicDto vrStyle) throws Exception{
		if(log.isDebugEnabled())log.debug("[START] " + this.getClass().getName() + ".musicList()");

		ModelAndView mav = new ModelAndView("siteManage/vr/style/musicList");
		try {
			mav.addObject("result", vrStyleService.getVrStyleMusicList(vrStyle));
			mav.addObject("theForm", vrStyle);
		} catch (Exception e) {
			if(log.isWarnEnabled())log.warn(e.getMessage());
		}

		if(log.isDebugEnabled())log.debug("[END] " + this.getClass().getName() + ".musicList()");
		return mav;
	}

	// ######   MUSIC FORM   #####
	@RequestMapping("/musicForm")
	public ModelAndView musicForm(HttpServletRequest request, VrStyleMusicDto vrStyle) throws Exception{
		if(log.isDebugEnabled())log.debug("[START] " + this.getClass().getName() + ".musicForm()");
		ModelAndView mav = new ModelAndView("siteManage/vr/style/musicForm");

		try {
			if(StringUtils.isNotBlank(vrStyle.getVr_style_music_id())){
			    VrStyleMusicDto result = vrStyleService.getVrStyleMusicForm(vrStyle);
	            if(result != null){
	                vrStyle = (VrStyleMusicDto)result.clone();
	                File attachFile = new File(vrStyle.getPath_file());
	                vrStyle.setPath_file(attachFile.getName());
	                vrStyle.setAct("update");
	            } else {
	                throw new Exception("Not Found VR_Site..");
	            }
			}

			mav.addObject("theForm", vrStyle);

		} catch (Exception e) {
			if(log.isWarnEnabled())log.warn(e.getMessage());
			redirectView(mav, message.getMessage("ERROR.NO.DATA"), "musicList.do");
		}

		if(log.isDebugEnabled())log.debug("[END] " + this.getClass().getName() + ".musicForm()");
		return mav;
	}

	@RequestMapping(method=RequestMethod.POST, value="/musicSave")
    public ModelAndView musicSave(HttpServletRequest request, VrStyleMusicDto vrStyleMusicDto) throws Exception{
        if(log.isDebugEnabled())log.debug("[START] " + this.getClass().getName() + ".musicSave()");

        ModelAndView mav = new ModelAndView();
        String returnPage = "musicList.do";

        try {
            if(StringUtils.equals("insert", vrStyleMusicDto.getAct())){
                String filePath = upload(request, vrStyleMusicDto.getMusic_file(), "/assets/admin/vr/music");
                vrStyleMusicDto.setPath_file(filePath);
                vrStyleService.insertVrStyleMusic(vrStyleMusicDto);
            } else if(StringUtils.equals("update", vrStyleMusicDto.getAct())){
                vrStyleService.updateVrStyleMusic(vrStyleMusicDto);
            } else if(StringUtils.equals("delete", vrStyleMusicDto.getAct())){
                vrStyleService.deleteVrStyleMusic(vrStyleMusicDto);
            }
            redirectView(mav, "", returnPage);
        } catch (Exception e) {
            if(log.isWarnEnabled())log.warn(e.getMessage());
            redirectView(mav, message.getMessage("ERROR.ACCESS.FAIL"), "musicForm.do");
        }

        if(log.isDebugEnabled())log.debug("[END] " + this.getClass().getName() + ".musicSave()");
        return mav;
    }
}
