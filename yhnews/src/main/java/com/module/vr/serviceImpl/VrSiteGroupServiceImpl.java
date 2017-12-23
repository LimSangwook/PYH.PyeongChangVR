package com.module.vr.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.common.dao.CommonDao;
import com.module.vr.dto.VrSiteDto;
import com.module.vr.dto.VrSiteGroupDto;
import com.module.vr.service.VrSiteGroupService;

@Service("VrSiteGroupService")
public class VrSiteGroupServiceImpl implements VrSiteGroupService {

    @Autowired
    private CommonDao commonDao;

    @Override
    public List<VrSiteGroupDto> getVrSiteGroupList(VrSiteDto vrSite) {
        List<VrSiteGroupDto> result = (List<VrSiteGroupDto>) commonDao.queryForObjectList("VRSITE.getVRSiteGroupList",
                vrSite);
        return result;
    }

    @Override
    public void insertVrSiteGroup(VrSiteGroupDto vrSiteGroupDto) {
        commonDao.insert("VRSITE.insertVrSiteGroup", vrSiteGroupDto);
    }

    @Override
    public void updateVrSiteGroup(VrSiteGroupDto vrSiteGroupDto) {
        commonDao.update("VRSITE.updateVrSiteGroup", vrSiteGroupDto);
    }

    @Override
    public void deleteVrSiteGroup(VrSiteGroupDto vrSiteGroupDto) {
        commonDao.delete("VRSITE.deleteVrSiteGroup", vrSiteGroupDto);
    }

}
