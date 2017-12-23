package com.module.vr.service;

import java.util.List;

import com.module.vr.dto.VrSiteContentDto;

public interface VrSiteContentService {
    public List<VrSiteContentDto> getVRSiteContentList(VrSiteContentDto content) throws Exception;

    public VrSiteContentDto getVrSiteContent(VrSiteContentDto vrSiteContent);

    public void insertVrSiteContent(VrSiteContentDto vrSiteContent);

    public void updateVrSiteContent(VrSiteContentDto vrSiteContent);

    public void deleteVrSiteContent(VrSiteContentDto vrSiteContent);
}
