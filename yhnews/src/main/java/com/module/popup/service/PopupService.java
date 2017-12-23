package com.module.popup.service;

import java.util.List;

import com.module.popup.dto.PopupDto;

public interface PopupService {

	/**
	 * 팝업 목록을 조회한다.
	 * 
	 * @param PopupDto <code>popup</code>
	 * @return List<PopupDto>
	 * */
	public List<PopupDto> getPopupList(PopupDto popup) throws Exception;
	
	/**
	 * 팝업 정보를 조회한다.
	 * 
	 * @param PopupDto <code>popup</code>
	 * @return PopupDto
	 * */
	public PopupDto getPopupInfo(PopupDto popup) throws Exception;
	
	/**
	 * 팝업 정보를 등록한다.
	 * 
	 * @param PopupDto <code>popup</code>
	 * */
	public void insertPopupInfo(PopupDto popup) throws Exception;
	
	/**
	 * 팝업 정보를 수정한다.
	 * 
	 * @param PopupDto <code>popup</code>
	 * */
	public void updatePopupInfo(PopupDto popup) throws Exception;
	
	/**
	 * 팝업 정보를 삭제한다.
	 * 
	 * @param PopupDto <code>popup</code>
	 * */
	public void deletePopupInfo(PopupDto popup) throws Exception;
	
	/**
	 * 사용자 노출 팝업 목록을 조회한다.
	 * 
	 * @param PopupDto <code>popup</code>
	 * @return List<PopupDto>
	 * */
	public List<PopupDto> getMainActivePopupList() throws Exception;
}
