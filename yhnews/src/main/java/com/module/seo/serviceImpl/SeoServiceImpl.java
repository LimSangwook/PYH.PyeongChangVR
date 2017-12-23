package com.module.seo.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.common.dao.CommonDao;
import com.module.seo.dto.SeoDto;
import com.module.seo.service.SeoService;

@Service("seoService")
public class SeoServiceImpl implements SeoService{

	@Autowired
	private CommonDao commonDao;
		
	@Override
	public SeoDto getSeoInfo() throws Exception {
		return (SeoDto)commonDao.queryForObject("SEO.getSeoInfo");
	}
	
	@Override
	public void insertSeoInfo(SeoDto seo) throws Exception {
		int seoKey = (Integer)commonDao.insert("SEO.insertSeoInfo", seo);
		seo.setSeo_key(String.valueOf(seoKey));
	}
	
	@Override
	public void updateSeoInfo(SeoDto seo) throws Exception {
		commonDao.update("SEO.updateSeoInfo", seo);		
	}
}