package com.module.vr.serviceImpl;

import java.io.File;
import java.util.List;

import org.apache.commons.io.FilenameUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.common.dao.CommonDao;
import com.module.vr.dto.VrSiteContentDto;
import com.module.vr.service.VrSiteContentService;

@Service("VrSiteContentService")
public class VrSiteContentServiceImpl implements VrSiteContentService {
	private Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	private CommonDao commonDao;

	@SuppressWarnings("unchecked")
	@Override
	public List<VrSiteContentDto> getVRSiteContentList(VrSiteContentDto content) throws Exception {
	    List<VrSiteContentDto> result = (List<VrSiteContentDto>)commonDao.queryForObjectList("VRSITE.getVRSiteContentList", content);
	    for(VrSiteContentDto vrSiteContent : result) {
	        File panoramaFile = new File(vrSiteContent.getPath_panorama_image());
	        String thumbFilePath = panoramaFile.getParent() + "/vtour/panos/" + FilenameUtils.getBaseName(panoramaFile.getName()) + ".tiles/thumb.jpg";
	        File thumbFile = new File(thumbFilePath);
	        if (thumbFile.exists() == true) {
	            vrSiteContent.setPath_panorama_image_ui("/vrContents/adminContents/" + vrSiteContent.getVr_site_id() + "/vtour/panos/" + FilenameUtils.getBaseName(panoramaFile.getName()) + ".tiles/thumb.jpg");
	        } else {
	            vrSiteContent.setPath_panorama_image_ui("");
	        }
            vrSiteContent.setPanorama_image_name(panoramaFile.getName());
	    }
		return result;
	}

    @Override
	public VrSiteContentDto getVrSiteContent(VrSiteContentDto vrSiteContent) {
		VrSiteContentDto  result = (VrSiteContentDto)commonDao.queryForObject("VRSITE.getVRSiteContent", vrSiteContent);
		return result;
	}

    @Override
    public void insertVrSiteContent(VrSiteContentDto vrSiteContent) {
        commonDao.insert("VRSITE.insertVrSiteContent", vrSiteContent);
    }

    @Override
    public void updateVrSiteContent(VrSiteContentDto vrSiteContent) {
        commonDao.insert("VRSITE.updateVrSiteContent", vrSiteContent);
    }

    @Override
    public void deleteVrSiteContent(VrSiteContentDto vrSiteContent) {
        commonDao.insert("VRSITE.deleteVrSiteContent", vrSiteContent);
    }
}
