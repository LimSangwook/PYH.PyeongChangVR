package com.module.main.service;

import java.util.List;
import java.util.Map;

public interface MainService {

	/**
	 * DB 사용랑조회
	 * @return long
	 * */
	public long getDataBaseTotalUseSize() throws Exception;
	
	/**
	 * 파일 사용랑조회
	 * @return long
	 * */
	public long getFileTotalUseSize() throws Exception;
		
	/**
	 * 총 회원 수
	 * @return long
	 * */
	public long getMemberTotalCount() throws Exception;
	
	/**
	 * 총 게시글 수
	 * @return long
	 * */
	public long getPostTotalCount() throws Exception;
	
	/**
	 * 총 답변글 수
	 * @return long
	 * */
	public long getPostReplyTotalCount() throws Exception;
	
	/**
	 * 코드 목록을 조회한다. 
	 * @param Map <code>params</code>
	 * @return List<MenuDto>
	 * */
	public List<Map<String,String>> getCommonCodeList(String parentCode) throws Exception;
	
	/**
	 * 사이트 서비스 언어 코드 목록을 조회한다. 
	 * @param Map <code>params</code>
	 * @return List<MenuDto>
	 * */
	public List<Map<String,String>> getSiteServiceLangList() throws Exception;
}
