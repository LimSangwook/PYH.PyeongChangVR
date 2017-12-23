package com.module.banner.serviceImpl;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.common.dao.CommonDao;
import com.module.banner.dto.BannerDto;
import com.module.banner.service.BannerService;

@Service("bannerService")
public class BannerServiceImpl implements BannerService{

	@Autowired
	private CommonDao commonDao;
	
	@Override
	public void insertBannerInfo(BannerDto banner) throws Exception {
		int bannerKey = (Integer)commonDao.insert("BANNER.insertBannerInfo", banner);
		banner.setBanner_key(String.valueOf(bannerKey));
		
		// 파일정보
		updateFileInfo(banner);
	}
	
	@Override
	public void updateBannerInfo(BannerDto banner) throws Exception {
		commonDao.update("BANNER.updateBannerInfo", banner);
		// 파일정보
		updateFileInfo(banner);
	}
	
	@Override
	public void deleteBannerInfo(BannerDto banner) throws Exception {
		commonDao.update("BANNER.deleteBannerInfo", banner);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<BannerDto> getBannerList(BannerDto banner) throws Exception {
		List<BannerDto> result = null;
		int totalCount = (Integer)commonDao.queryForObject("BANNER.getBannerTotalCount", banner);
		if(totalCount > 0){
			banner.setTotal_count(totalCount);
			result = (List<BannerDto>)commonDao.queryForObjectList("BANNER.getBannerList", banner);
		}
		return result;
	}
	
	@Override
	public BannerDto getBannerInfo(BannerDto banner) throws Exception {
		return (BannerDto)commonDao.queryForObject("BANNER.getBannerInfo", banner);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<BannerDto> getMainBannerList(String areaCode) throws Exception {
		return (List<BannerDto>)commonDao.queryForObjectList("BANNER.getMainBannerList", areaCode);
	}
	
	// 파일정보 처리
	private void updateFileInfo(BannerDto banner) {
		if(StringUtils.isNotBlank(banner.getPhotoFile())){
			String[] fileArr = StringUtils.split(banner.getPhotoFile(),"|");
			/**
			 * file[0] = real_file_name
			 * file[1] = save_file_name
			 * file[2] = file_path
			 * file[3] = file_size
			 * file[4] = image_width_size
			 * file[5] = image_height_size
			 * file[6] = file_ext
			 * */			
			banner.setReal_file_name(fileArr[0]);
			banner.setSave_file_name(fileArr[1]);
			banner.setFile_path(fileArr[2]);
			commonDao.update("BANNER.updateBannerFileInfo", banner);
		}		
	}
}