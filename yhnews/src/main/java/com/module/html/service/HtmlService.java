package com.module.html.service;

import java.util.List;

import com.module.html.dto.HtmlDto;

public interface HtmlService {

	/**
	 * 콘텐츠 목록을 조회한다  
	 * @param html <code>HtmlDto</code>
	 * @return List<HtmlDto>
	 * */
	public List<HtmlDto> getHtmlList(HtmlDto html) throws Exception;
	
	/**
	 * 콘텐츠 정보를 조회한다  
	 * @param html <code>HtmlDto</code>
	 * @return HtmlDto
	 * */
	public HtmlDto getHtmlInfo(String pageSeq) throws Exception;
	
	/**
	 * 콘텐츠 정보를 등록한다  
	 * @param html <code>HtmlDto</code>
	 * */
	public  void insertHtmlInfo(HtmlDto html) throws Exception;
	
	/**
	 * 콘텐츠 정보를 수정한다  
	 * @param html <code>HtmlDto</code>
	 * */
	public  void updateHtmlInfo(HtmlDto html) throws Exception;
	
	/**
	 * 콘텐츠 정보를 삭제한다  
	 * @param html <code>HtmlDto</code>
	 * */
	public  void deleteHtmlInfo(HtmlDto html) throws Exception;
}
