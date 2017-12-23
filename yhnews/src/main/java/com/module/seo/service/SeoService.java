package com.module.seo.service;

import com.module.seo.dto.SeoDto;

public interface SeoService {

	/**
	 *SEO 정보 조회
	 *
	 *@return SeoDto 
	 * */
	public SeoDto getSeoInfo() throws Exception;
	
	/**
	 *SEO 정보 등록
	 *
	 *@param seo <p>SeoDto</p> 
	 * */
	public void insertSeoInfo(SeoDto seo) throws Exception;
	
	/**
	 *SEO 정보 수정
	 *
	 *@param seo <p>SeoDto</p> 
	 * */
	public void updateSeoInfo(SeoDto seo) throws Exception;
}
