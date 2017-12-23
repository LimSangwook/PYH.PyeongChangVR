package com.module.banner.service;

import java.util.List;

import com.module.banner.dto.BannerDto;

public interface BannerService {
	
	/**
	 * 배너 목록을 조회한다.
	 * 
	 * @param BannerDto <code>banner</code>
	 * @return List<BannerDto>
	 * */
	public List<BannerDto> getBannerList(BannerDto banner) throws Exception;
	
	/**
	 * 배너 정보를 조회한다.
	 * 
	 * @param BannerDto <code>banner</code>
	 * @return BannerDto
	 * */
	public BannerDto getBannerInfo(BannerDto banner) throws Exception;
	
	/**
	 * 배너 정보를 등록한다.
	 * 
	 * @param BannerDto <code>banner</code>
	 * */
	public void insertBannerInfo(BannerDto banner) throws Exception;
	
	/**
	 * 배너 정보를 수정한다.
	 * 
	 * @param BannerDto <code>banner</code>
	 * */
	public void updateBannerInfo(BannerDto banner) throws Exception;
	
	/**
	 * 배너 정보를 삭제한다.
	 * 
	 * @param BannerDto <code>banner</code>
	 * */
	public void deleteBannerInfo(BannerDto banner) throws Exception;
	
	/**
	 * 특정 위치 배너 노출목록
	 * */
	public List<BannerDto> getMainBannerList(String areaCode) throws Exception;
}
