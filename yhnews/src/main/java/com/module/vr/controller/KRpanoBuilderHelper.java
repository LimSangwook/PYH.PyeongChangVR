package com.module.vr.controller;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.MessageSourceAccessor;

import com.krpano.build.KRpanoBuilder;
import com.krpano.build.model.VRGroupModel;
import com.krpano.build.model.VRHotSpotModel;
import com.krpano.build.model.VRSceneModel;
import com.krpano.build.model.VRTourModel;
import com.module.vr.dto.VrSiteContentDto;
import com.module.vr.dto.VrSiteContentSpotDto;
import com.module.vr.dto.VrSiteDto;
import com.module.vr.dto.VrSiteGroupDto;
import com.module.vr.service.VrSiteContentService;
import com.module.vr.service.VrSiteGroupService;
import com.module.vr.service.VrSiteService;

public class KRpanoBuilderHelper {
	private Logger log = LoggerFactory.getLogger(getClass());
	@Resource(name = "messageSourceAccessor")
	private static MessageSourceAccessor message;

	private static VrSiteService vrSiteService;
	private static VrSiteContentService vrSiteContentService;
	private static VrSiteGroupService vrSiteGroupService;

	public static KRpanoBuilder CreateKRpanoBuilder(String vr_site_id, VrSiteService vrSiteService, VrSiteContentService vrSiteContentService, VrSiteGroupService vrSiteGroupService) throws Exception {
        KRpanoBuilderHelper.vrSiteService = vrSiteService;
        KRpanoBuilderHelper.vrSiteContentService = vrSiteContentService;
        KRpanoBuilderHelper.vrSiteGroupService = vrSiteGroupService;
		String title = getVRSiteName(vr_site_id);
		VRTourModel vrModel = new VRTourModel(title);
		setGroupInfo(vrModel, vr_site_id);
		setSceneInfo(vrModel, vr_site_id);

		return new KRpanoBuilder(vrModel);
	}

	private static String getVRSiteName(String vr_site_id) throws Exception {
		VrSiteDto vrSite = new VrSiteDto();
		vrSite.setVr_site_id(vr_site_id);
		vrSite = vrSiteService.getVRSite(vrSite);
		return vrSite.getTitle();
	}

	private static void setGroupInfo(VRTourModel vrModel, String vr_site_id) throws Exception {
		VrSiteDto vrSite = new VrSiteDto();
		vrSite.setVr_site_id(vr_site_id);
		List<VrSiteGroupDto> vrGroupList = vrSiteGroupService.getVrSiteGroupList(vrSite);
		for (VrSiteGroupDto groupInfo : vrGroupList) {
			VRGroupModel group = createVRGroupModel(groupInfo);
			vrModel.addGroup(group);
		}
	}

	private static void setSceneInfo(VRTourModel vrModel, String vr_site_id) throws Exception {
		VrSiteContentDto vrContent = new VrSiteContentDto();
		vrContent.setVr_site_id(vr_site_id);

		List<VrSiteContentDto> contentList = vrSiteContentService.getVRSiteContentList(vrContent);
		for (VrSiteContentDto aContent : contentList) {
			VRSceneModel sceneModel = getVRScene(aContent);
			vrModel.addScene(sceneModel);
		}
	}

	private static VRGroupModel createVRGroupModel(VrSiteGroupDto groupInfo) {
		VRGroupModel model = new VRGroupModel();
		model.setName(groupInfo.getVr_site_group_id());
		model.setTitle(groupInfo.getName());
		model.setDescription(groupInfo.getDescription());
		return model;
	}

	private static VRSceneModel getVRScene(VrSiteContentDto aContent) {
		VRSceneModel scene = new VRSceneModel();
		scene.setName(aContent.getVr_site_content_id());
		scene.setTitle(aContent.getName());
		scene.setGroupName(Integer.toString(aContent.getVr_site_group_id()));
		scene.setPanoramaFilePath(aContent.getPath_panorama_image());
		List<VrSiteContentSpotDto> contentSpotList = new ArrayList<VrSiteContentSpotDto>();
		for (VrSiteContentSpotDto aHotspot : contentSpotList) {
			VRHotSpotModel vrHotspot = getVRHotspot(aHotspot);
			scene.addHotspot(vrHotspot);
		}
		return scene;
	}

	private static VRHotSpotModel getVRHotspot(VrSiteContentSpotDto aHotspot) {
		double ath = aHotspot.getPos_x();
		double atv = aHotspot.getPos_y();
		String linkedSceneName =  aHotspot.getVr_site_content_id();

		VRHotSpotModel vrHotspot = new VRHotSpotModel(ath, atv, linkedSceneName);

		return vrHotspot;
	}
}
