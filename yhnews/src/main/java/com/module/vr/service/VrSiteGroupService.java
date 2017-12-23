package com.module.vr.service;

import java.util.List;

import com.module.vr.dto.VrSiteDto;
import com.module.vr.dto.VrSiteGroupDto;

public interface VrSiteGroupService {
	/**
	 * VR 사이트 그룹 목록을 조회한다.
	 *
	 * @param vrSiteGroupDtoDto <code>vrSiteGroupDto</code>
	 * @return List<vrSiteGroupDtoDto>
	 * */
	public List<VrSiteGroupDto> getVrSiteGroupList(VrSiteDto vrSiteDto);

	/**
	 * VR 사이트 그룹 정보를 등록한다.
	 *
	 * @param vrSiteGroupDtoDto <code>vrSiteGroupDto</code>
	 * */
	public void insertVrSiteGroup(VrSiteGroupDto vrSiteGroupDto);

	/**
	 * VR 사이트 그룹 정보를 수정한다.
	 *
	 * @param vrSiteGroupDtoDto <code>vrSiteGroupDto</code>
	 * */
	public void updateVrSiteGroup(VrSiteGroupDto vrSiteGroupDto);

	/**
	 * VR 사이트  그룹 정보를 삭제한다.
	 *
	 * @param vrSiteGroupDtoDto <code>vrSiteGroupDto</code>
	 * */

	public void deleteVrSiteGroup(VrSiteGroupDto vrSiteGroupDto);
}
