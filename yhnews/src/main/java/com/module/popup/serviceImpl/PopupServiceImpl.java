package com.module.popup.serviceImpl;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.common.dao.CommonDao;
import com.module.popup.dto.PopupDto;
import com.module.popup.service.PopupService;

@Service("popupService")
public class PopupServiceImpl implements PopupService{

	@Autowired
	private CommonDao commonDao;
	
	@Override
	public void insertPopupInfo(PopupDto popup) throws Exception {
		int popupKey = (Integer)commonDao.insert("POPUP.insertPopupInfo", popup);
		popup.setPopup_key(String.valueOf(popupKey));
		
		// 파일정보
		updateFileInfo(popup);
	}
	
	@Override
	public void updatePopupInfo(PopupDto popup) throws Exception {
		commonDao.update("POPUP.updatePopupInfo", popup);
		// 파일정보
		updateFileInfo(popup);
	}
	
	@Override
	public void deletePopupInfo(PopupDto popup) throws Exception {
		commonDao.update("POPUP.deletePopupInfo", popup);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<PopupDto> getPopupList(PopupDto popup) throws Exception {
		List<PopupDto> result = null;
		int totalCount = (Integer)commonDao.queryForObject("POPUP.getPopupTotalCount", popup);
		if(totalCount > 0){
			popup.setTotal_count(totalCount);
			result = (List<PopupDto>)commonDao.queryForObjectList("POPUP.getPopupList", popup);
		}
		return result;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<PopupDto> getMainActivePopupList() throws Exception {
		return (List<PopupDto>)commonDao.queryForObjectList("POPUP.getMainPopupList");
	}
	
	@Override
	public PopupDto getPopupInfo(PopupDto popup) throws Exception {
		return (PopupDto)commonDao.queryForObject("POPUP.getPopupInfo", popup);
	}
	
	// 파일정보 처리
	private void updateFileInfo(PopupDto popup) {
		if(StringUtils.isNotBlank(popup.getPhotoFile())){
			String[] fileArr = StringUtils.split(popup.getPhotoFile(),"|");
			/**
			 * file[0] = real_file_name
			 * file[1] = save_file_name
			 * file[2] = file_path
			 * file[3] = file_size
			 * file[4] = image_width_size
			 * file[5] = image_height_size
			 * file[6] = file_ext
			 * */			
			popup.setReal_file_name(fileArr[0]);
			popup.setSave_file_name(fileArr[1]);
			popup.setFile_path(fileArr[2]);
			commonDao.update("POPUP.updatePopupFileInfo", popup);
		}		
	}
}