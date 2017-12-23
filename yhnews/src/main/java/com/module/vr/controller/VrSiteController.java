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
import com.krpano.build.KRpanoBuildManager;
import com.krpano.build.KRpanoBuilder;
import com.module.vr.dto.VrSiteDto;
import com.module.vr.dto.VrSiteGroupDto;
import com.module.vr.service.VrSiteContentService;
import com.module.vr.service.VrSiteGroupService;
import com.module.vr.service.VrSiteService;

@Controller
@RequestMapping("/siteManage/vrmanager/vrcontents")
public class VrSiteController extends CommonWebUtils {
	private Logger log = LoggerFactory.getLogger(getClass());

	@Resource(name = "messageSourceAccessor")
	private MessageSourceAccessor message;

	@Autowired
	private VrSiteService vrSiteService;
	@Autowired
	private VrSiteGroupService vrSiteGroupService;
    @Autowired
    private VrSiteContentService vrSiteContentService;
	// ######   SITE LIST   #####
	@RequestMapping("/siteList")
	public ModelAndView vrSiteList(HttpServletRequest request, VrSiteDto vrSite) throws Exception{
		if(log.isDebugEnabled())log.debug("[START] " + this.getClass().getName() + ".vrSiteList()");

		ModelAndView mav = new ModelAndView("siteManage/vr/content/siteList");

		try {
			mav.addObject("result", vrSiteService.getVrSiteList(vrSite));
			mav.addObject("theForm", vrSite);
		} catch (Exception e) {
			if(log.isWarnEnabled())log.warn(e.getMessage());
		}

		if(log.isDebugEnabled())log.debug("[END] " + this.getClass().getName() + ".vrSiteList()");
		return mav;
	}

	// ######   SITE FORM   #####
	@RequestMapping("/siteForm")
	public ModelAndView vrSiteForm(HttpServletRequest request, VrSiteDto vrSite) throws Exception{
		if(log.isDebugEnabled())log.debug("[START] " + this.getClass().getName() + ".siteForm()");

		ModelAndView mav = new ModelAndView("siteManage/vr/content/siteForm");

		try {
			if(StringUtils.isNotBlank(vrSite.getVr_site_id())){
				VrSiteDto result = vrSiteService.getVRSite(vrSite);
				if(result != null){
					vrSite = (VrSiteDto)result.clone();
					File iconFile = new File(vrSite.getPath_icon());
					File imgFile = new File(vrSite.getPath_image());
					vrSite.setPath_image(vrSite.getVr_site_id() + "/" + imgFile.getName());
					vrSite.setPath_icon(vrSite.getVr_site_id() + "/" + iconFile.getName());
					vrSite.setAct("update");
				} else {
					throw new Exception("Not Found VR_Site..");
				}
				mav.addObject("group", vrSiteGroupService.getVrSiteGroupList(vrSite));
				mav.addObject("result", result);
			}
			mav.addObject("theForm", vrSite);
			mav.addObject("defaultParameter", getParameter(request,"?",""));
		} catch (Exception e) {
			if(log.isWarnEnabled())log.warn(e.getMessage());
			redirectView(mav, message.getMessage("ERROR.NO.DATA"), "siteList.do");
		}

		if(log.isDebugEnabled())log.debug("[END] " + this.getClass().getName() + ".siteForm()");
		return mav;
	}

	// ######   SITE SAVE   #####
	@RequestMapping(method=RequestMethod.POST, value="/siteSave")
	public ModelAndView vrSiteSave(HttpServletRequest request, VrSiteDto vrSite) throws Exception{
		if(log.isDebugEnabled())log.debug("[START] " + this.getClass().getName() + ".vrSiteSave()");

		ModelAndView mav = new ModelAndView();
		String returnPage = "siteList.do";

		try {
			if(StringUtils.equals("insert", vrSite.getAct())){
			    if(vrSite.getImage_file() != null) {
			        String imgPath = upload(request, vrSite.getImage_file(), "/vrContents/adminContents", vrSite.getVr_site_id());
			        vrSite.setPath_image(imgPath);
			    }
			    if(vrSite.getIcon_file() != null) {
			        String iconPath = upload(request, vrSite.getIcon_file(), "/vrContents/adminContents", vrSite.getVr_site_id());
			        vrSite.setPath_icon(iconPath);
			    }
				vrSiteService.insertVrSite(vrSite);
				crudSiteGroup(vrSite);
			} else if(StringUtils.equals("update", vrSite.getAct())){
				vrSiteService.updateVrSite(vrSite);
				crudSiteGroup(vrSite);
			} else if(StringUtils.equals("delete", vrSite.getAct())){
			    if(vrSite.getList() != null && vrSite.getList().size() != 0) {
		            List<VrSiteGroupDto> vrSiteGroupList = vrSite.getList();
		            for (VrSiteGroupDto vrSiteGroupDto : vrSiteGroupList) {
		                vrSiteGroupDto.setVr_site_id(vrSite.getVr_site_id());
	                    vrSiteGroupService.deleteVrSiteGroup(vrSiteGroupDto);
		            }
		        }
				vrSiteService.deleteVrSite(vrSite);
			}
			redirectView(mav, "", returnPage);
		} catch (Exception e) {
			if(log.isWarnEnabled())log.warn(e.getMessage());
			redirectView(mav, message.getMessage("ERROR.ACCESS.FAIL"), "siteForm.do?vr_site_id=" + vrSite.getVr_site_id());
		}

		if(log.isDebugEnabled())log.debug("[END] " + this.getClass().getName() + ".vrSiteSave()");
		return mav;
	}

	private void crudSiteGroup(VrSiteDto vrSite){
	    if(vrSite.getList() != null && vrSite.getList().size() != 0) {
            List<VrSiteGroupDto> vrSiteGroupList = vrSite.getList();
            for (VrSiteGroupDto vrSiteGroupDto : vrSiteGroupList) {
                vrSiteGroupDto.setVr_site_id(vrSite.getVr_site_id());
                if(vrSiteGroupDto.getCrud_type().equals("I")) {
                    vrSiteGroupService.insertVrSiteGroup(vrSiteGroupDto);
                } else if(vrSiteGroupDto.getCrud_type().equals("U")) {
                    vrSiteGroupService.updateVrSiteGroup(vrSiteGroupDto);
                } else if(vrSiteGroupDto.getCrud_type().equals("D")) {
                    vrSiteGroupService.deleteVrSiteGroup(vrSiteGroupDto);
                }
            }
        }
	}


    // ######   Create SITE(Stadium) Contents   #####
    @RequestMapping(method=RequestMethod.GET, value="/createSiteVR")
    public ModelAndView createVRContents(HttpServletRequest request, VrSiteDto vrSite) throws Exception{
        if(log.isDebugEnabled())log.debug("[START] " + this.getClass().getName() + ".createVRContents()");
        ModelAndView mav = new ModelAndView("/siteManage/vrmanager/vrcontents/contentList.do?vr_site_id="+vrSite.getVr_site_id());

        System.out.println("### Create KRpano VR Contents (Stadium)###");
        KRpanoBuildManager km = KRpanoBuildManager.getInstance();

        KRpanoBuilder builder = KRpanoBuilderHelper.CreateKRpanoBuilder(vrSite.getVr_site_id(), vrSiteService, vrSiteContentService, vrSiteGroupService);
        km.addAndStartBuild(vrSite.getVr_site_id(), builder);

        if(log.isDebugEnabled())log.debug("[END] " + this.getClass().getName() + ".createVRContents()");
        redirectView(mav, "REDIRECT", "/siteManage/vrmanager/vrcontents/contentList.do?vr_site_id=" + vrSite.getVr_site_id());
        return mav;
    }

}
