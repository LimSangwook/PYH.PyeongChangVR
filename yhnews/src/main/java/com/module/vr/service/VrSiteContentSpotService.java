package com.module.vr.service;

import java.util.List;

import com.module.vr.dto.VrSiteContentSpotDto;

public interface VrSiteContentSpotService {
    public List<VrSiteContentSpotDto> getVrSiteContentSpotList(VrSiteContentSpotDto vrSiteContentSpotDto);

    public void insertVrSiteContentSpot(VrSiteContentSpotDto vrSiteContentSpotDto);

    public void updateVrSiteContentSpot(VrSiteContentSpotDto vrSiteContentSpotDto);

    public void deleteVrSiteContentSpot(VrSiteContentSpotDto vrSiteContentSpotDto);

}
