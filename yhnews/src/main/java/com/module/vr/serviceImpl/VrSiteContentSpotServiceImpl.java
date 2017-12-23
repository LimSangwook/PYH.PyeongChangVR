package com.module.vr.serviceImpl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.common.dao.CommonDao;
import com.module.vr.dto.VrSiteContentSpotDto;
import com.module.vr.service.VrSiteContentSpotService;

@Service("VrSiteContentSpotService")
public class VrSiteContentSpotServiceImpl implements VrSiteContentSpotService {
    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private CommonDao commonDao;

    @Override
    public List<VrSiteContentSpotDto> getVrSiteContentSpotList(VrSiteContentSpotDto vrSiteContentSpotDto) {
        List<VrSiteContentSpotDto> result = (List<VrSiteContentSpotDto>)commonDao.queryForObjectList("VRSITE.getVrSiteContentSpotList", vrSiteContentSpotDto);
        return result;
    }

    @Override
    public void insertVrSiteContentSpot(VrSiteContentSpotDto vrSiteContentSpotDto) {
        commonDao.insert("VRSITE.insertVrSiteContentSpot", vrSiteContentSpotDto);
    }

    @Override
    public void updateVrSiteContentSpot(VrSiteContentSpotDto vrSiteContentSpotDto) {
        commonDao.insert("VRSITE.updateVrSiteContentSpot", vrSiteContentSpotDto);
    }

    @Override
    public void deleteVrSiteContentSpot(VrSiteContentSpotDto vrSiteContentSpotDto) {
        commonDao.insert("VRSITE.deleteVrSiteContentSpot", vrSiteContentSpotDto);
    }

}
