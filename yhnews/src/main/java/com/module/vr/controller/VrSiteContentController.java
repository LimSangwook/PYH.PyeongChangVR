package com.module.vr.controller;

import java.io.File;
import java.util.List;

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
import com.module.vr.dto.VrSiteContentDto;
import com.module.vr.dto.VrSiteContentSpotDto;
import com.module.vr.dto.VrSiteDto;
import com.module.vr.dto.VrStyleGalleryDto;
import com.module.vr.dto.VrStyleLinkDto;
import com.module.vr.dto.VrStyleMovieDto;
import com.module.vr.dto.VrStyleMusicDto;
import com.module.vr.service.VrSiteContentService;
import com.module.vr.service.VrSiteContentSpotService;
import com.module.vr.service.VrSiteGroupService;
import com.module.vr.service.VrSiteService;
import com.module.vr.service.VrStyleService;

@Controller
@RequestMapping("/siteManage/vrmanager/vrcontents")
public class VrSiteContentController extends CommonWebUtils {
    private Logger log = LoggerFactory.getLogger(getClass());

    @Resource(name = "messageSourceAccessor")
    private MessageSourceAccessor message;

    @Autowired
    private VrSiteService vrSiteService;
    @Autowired
    private VrSiteGroupService vrSiteGroupService;
    @Autowired
    private VrSiteContentService vrSiteContentService;
    @Autowired
    private VrSiteContentSpotService vrSiteContentSpotService;
    @Autowired
    private VrStyleService vrStyleService;

    // ###### CONTENT LIST #####
    @RequestMapping("/contentList")
    public ModelAndView vrContentList(HttpServletRequest request, VrSiteContentDto vrSiteContent) throws Exception {
        if (log.isDebugEnabled()) {
            log.debug("[START] " + this.getClass().getName() + ".vrContentList()");
        }

        ModelAndView mav = new ModelAndView("siteManage/vr/content/contentList");

        try {
            VrSiteDto vrSiteDto = new VrSiteDto();
            vrSiteDto.setVr_site_id(vrSiteContent.getVr_site_id());
            mav.addObject("vrSite", vrSiteService.getVRSite(vrSiteDto));
            mav.addObject("vrSiteContentList", vrSiteContentService.getVRSiteContentList(vrSiteContent));
            mav.addObject("theForm", vrSiteContent);
        } catch (Exception e) {
            if (log.isWarnEnabled())
                log.warn(e.getMessage());
        }

        if (log.isDebugEnabled()) {
            log.debug("[END] " + this.getClass().getName() + ".vrContentList()");
        }
        return mav;
    }

    // ###### CONTENT FORM #####
    @RequestMapping("/contentForm")
    public ModelAndView vrContentForm(HttpServletRequest request, VrSiteContentDto vrSiteContent) throws Exception {
        if (log.isDebugEnabled()) {
            log.debug("[START] " + this.getClass().getName() + ".vrContentForm()");
        }

        ModelAndView mav = new ModelAndView("siteManage/vr/content/contentForm");

        try {
            if (StringUtils.isNotBlank(vrSiteContent.getVr_site_content_id())) {
                VrSiteContentDto result = vrSiteContentService.getVrSiteContent(vrSiteContent);
                if (result != null) {
                    vrSiteContent = (VrSiteContentDto) result.clone();

                    File panoramaFile = new File(vrSiteContent.getPath_panorama_image());
                    vrSiteContent.setPath_panorama_image("/vrContents/adminContents/" + vrSiteContent.getVr_site_id() + "/" + panoramaFile.getName());
                    vrSiteContent.setPanorama_image_name(panoramaFile.getName());

                    VrSiteContentSpotDto vrSiteContentSpotDto = new VrSiteContentSpotDto();
                    vrSiteContentSpotDto.setVr_site_content_id(vrSiteContent.getVr_site_content_id());
                    mav.addObject("spotList", vrSiteContentSpotService.getVrSiteContentSpotList(vrSiteContentSpotDto));
                    vrSiteContent.setAct("update");
                } else {
                    throw new Exception("Not Found VR_SiteContent..");
                }
                mav.addObject("result", result);
            }
            mav.addObject("theForm", vrSiteContent);

            VrSiteDto vrDto = new VrSiteDto();
            vrDto.setVr_site_id(vrSiteContent.getVr_site_id());
            mav.addObject("groupList", vrSiteGroupService.getVrSiteGroupList(vrDto));
            mav.addObject("siteList", vrSiteService.getVrSiteList(new VrSiteDto()));
            mav.addObject("siteContentList", vrSiteContentService.getVRSiteContentList(vrSiteContent));
            mav.addObject("styleMusicList", vrStyleService.getVrStyleMusicList(new VrStyleMusicDto()));
            mav.addObject("styleLinkList", vrStyleService.getVrStyleLinkList(new VrStyleLinkDto()));
            mav.addObject("styleMovieList", vrStyleService.getVrStyleMovieList(new VrStyleMovieDto()));
            mav.addObject("styleGalleryList", vrStyleService.getVrStyleGalleryList(new VrStyleGalleryDto()));
        } catch (Exception e) {
            if (log.isWarnEnabled())
                log.warn(e.getMessage());
            redirectView(mav, message.getMessage("ERROR.NO.DATA"), "contentForm.do");
        }

        if (log.isDebugEnabled()) {
            log.debug("[END] " + this.getClass().getName() + ".contentForm()");
        }
        return mav;
    }

    // ###### CONTENT SAVE #####
    @RequestMapping(method = RequestMethod.POST, value = "/contentSave")
    public ModelAndView vrSiteContentSave(HttpServletRequest request, VrSiteContentDto vrSiteContentDto)
            throws Exception {
        if (log.isDebugEnabled()) {
            log.debug("[START] " + this.getClass().getName() + ".vrSiteSave()");
        }

        ModelAndView mav = new ModelAndView();
        String returnPage = "contentList.do?vr_site_id=" + vrSiteContentDto.getVr_site_id();

        try {
            if (StringUtils.equals("insert", vrSiteContentDto.getAct())) {
                String panoramaPath = upload(request, vrSiteContentDto.getPanorama_file(), "/vrContents/adminContents", vrSiteContentDto.getVr_site_id());
                vrSiteContentDto.setPath_panorama_image(panoramaPath);
                vrSiteContentService.insertVrSiteContent(vrSiteContentDto);
            } else if (StringUtils.equals("update", vrSiteContentDto.getAct())) {
                vrSiteContentService.updateVrSiteContent(vrSiteContentDto);
                if(vrSiteContentDto.getSpotList() != null) {
                    List<VrSiteContentSpotDto> spotList = vrSiteContentDto.getSpotList();
                    for (VrSiteContentSpotDto vrSiteContentSpotDto : spotList) {
                        vrSiteContentSpotDto.setVr_site_content_id(vrSiteContentDto.getVr_site_content_id());
                        if(vrSiteContentSpotDto.getCrud_type().equals("I")) {
                            vrSiteContentSpotService.insertVrSiteContentSpot(vrSiteContentSpotDto);
                        } else if(vrSiteContentSpotDto.getCrud_type().equals("U")) {
                            vrSiteContentSpotService.updateVrSiteContentSpot(vrSiteContentSpotDto);
                        } else if(vrSiteContentSpotDto.getCrud_type().equals("D")) {
                            vrSiteContentSpotService.deleteVrSiteContentSpot(vrSiteContentSpotDto);
                        }
                    }
                }
            } else if (StringUtils.equals("delete", vrSiteContentDto.getAct())) {
                List<VrSiteContentSpotDto> spotList = vrSiteContentDto.getSpotList();
                for (VrSiteContentSpotDto vrSiteContentSpotDto : spotList) {
                    vrSiteContentSpotDto.setVr_site_content_id(vrSiteContentDto.getVr_site_content_id());
                    vrSiteContentSpotService.deleteVrSiteContentSpot(vrSiteContentSpotDto);
                }
                vrSiteContentService.deleteVrSiteContent(vrSiteContentDto);
            }
            redirectView(mav, "", returnPage);
        } catch (Exception e) {
            if (log.isWarnEnabled())
                log.warn(e.getMessage());
            redirectView(mav, message.getMessage("ERROR.ACCESS.FAIL"), "contentForm.do?vr_site_content_id=" + vrSiteContentDto.getVr_site_content_id());
        }

        if (log.isDebugEnabled()) {
            log.debug("[END] " + this.getClass().getName() + ".vrSiteSave()");
        }
        return mav;
    }

    @RequestMapping(method=RequestMethod.POST, value="/vrStyleList")
    public ModelAndView vrStyleList(HttpServletRequest request, VrSiteContentSpotDto vrSiteContentSpotDto) throws Exception{
        if(log.isDebugEnabled())log.debug("[START] " + this.getClass().getName() + ".boardConfigListJson()");

        ModelAndView mav = new ModelAndView("jsonViewer");
        try {
            if(vrSiteContentSpotDto.getVr_style_type().equals("link")) {
                mav.addObject("RESULT_DATA", vrStyleService.getVrStyleLinkList(new VrStyleLinkDto()));
            } else if (vrSiteContentSpotDto.getVr_style_type().equals("gallery")) {
                mav.addObject("RESULT_DATA", vrStyleService.getVrStyleGalleryList(new VrStyleGalleryDto()));
            } else if (vrSiteContentSpotDto.getVr_style_type().equals("movie")) {
                mav.addObject("RESULT_DATA", vrStyleService.getVrStyleMovieList(new VrStyleMovieDto()));
            }
            mav.addObject("RESULT_CODE","SUCCESS");
        } catch (Exception e) {
            if(log.isWarnEnabled())log.warn(e.toString());
        }

        if(log.isDebugEnabled())log.debug("[END] " + this.getClass().getName() + ".boardConfigListJson()");
        return mav;
    }
}
