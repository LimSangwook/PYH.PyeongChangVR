package com.module.vr.service;

import java.util.List;

import com.module.vr.dto.VrSiteDto;

public interface VrSiteService {
	/**
	 * 사용자 목록을 조회한다.
	 *
	 * @param UserDto <code>vrSiteDto</code>
	 * @return List<UserDto>
	 * */
	public List<VrSiteDto> getVrSiteList(VrSiteDto vrSiteDto) throws Exception;

	/**
	 * 사용자 상세정보를 조회한다.
	 *
	 * @param UserDto <code>vrSiteDto</code>
	 * @return UserDto
	 * */
	public VrSiteDto getVRSite(VrSiteDto vrSiteDto) throws Exception;

	/**
	 * 사용자 정보를 등록한다.
	 *
	 * @param UserDto <code>vrSiteDto</code>
	 * */
	public void insertVrSite(VrSiteDto vrSiteDto) throws Exception;

	/**
	 * 사용자 정보를 수정한다.
	 *
	 * @param UserDto <code>vrSiteDto</code>
	 * */
	public void updateVrSite(VrSiteDto vrSiteDto) throws Exception;

	/**
	 * 내 정보를 수정한다.
	 *
	 * @param UserDto <code>vrSiteDto</code>
	 * */

	public void deleteVrSite(VrSiteDto vrSiteDto) throws Exception;
}
