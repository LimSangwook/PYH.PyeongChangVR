package com.module.vr.serviceImpl;

import java.io.File;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.common.dao.CommonDao;
import com.module.vr.dto.VrSiteDto;
import com.module.vr.service.VrSiteService;

@Service("VrSiteService")
public class VrSiteServiceImpl implements VrSiteService {
	@Autowired
	private CommonDao commonDao;

	@SuppressWarnings("unchecked")
	@Override
	public List<VrSiteDto> getVrSiteList(VrSiteDto vrSite) throws Exception {
		List<VrSiteDto> result = null;
		int totalCount = (Integer)commonDao.queryForObject("VRSITE.getVRSiteTotalCount");

		if(totalCount > 0){
			result = (List<VrSiteDto>)commonDao.queryForObjectList("VRSITE.getVRSiteList", vrSite);
			for(VrSiteDto dto : result) {
                File iconFile = new File(dto.getPath_icon());
                File imgFile = new File(dto.getPath_image());
                dto.setPath_image(dto.getVr_site_id() + "/" + imgFile.getName());
                dto.setPath_icon(dto.getVr_site_id() + "/" + iconFile.getName());
			}
			vrSite.setTotal_count(totalCount);
		}
		return result;
	}

	@SuppressWarnings("unchecked")
	@Override
	public VrSiteDto getVRSite(VrSiteDto vrSite) throws Exception {
		VrSiteDto result = null;
		List<VrSiteDto> lists = (List<VrSiteDto>)commonDao.queryForObjectList("VRSITE.getVRSite", vrSite);
		if (lists.size() > 0)
			result = lists.get(0);
		return result;
	}

	@Override
	public void
	insertVrSite(VrSiteDto vrSite) throws Exception {
	    commonDao.insert("VRSITE.insertVRSite", vrSite);
	}

	@Override
	public void updateVrSite(VrSiteDto vrSite) throws Exception {
		commonDao.insert("VRSITE.updateVRSite", vrSite);
	}

	@Override
	public void deleteVrSite(VrSiteDto vrSite) throws Exception {
		commonDao.update("VRSITE.deleteVRSite", vrSite);
	}
}
