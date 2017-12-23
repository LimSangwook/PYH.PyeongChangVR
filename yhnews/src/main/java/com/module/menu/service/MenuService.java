package com.module.menu.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.module.menu.dto.MenuDto;

public interface MenuService {

	/**
	 * 메뉴정보 목록을 조회한다.
	 * 
	 * @param String <code>parentMenuCode</code>
	 * @return List<MenuDto>
	 * */
	public List<MenuDto> getMenuList(String parentMenuCode) throws Exception;
	
	/**
	 * 메뉴 상세정보를 조회한다.
	 * 
	 * @param MenuDto <code>menu</code>
	 * @return MenuDto
	 * */
	public MenuDto getMenuInfo(MenuDto menu) throws Exception;
	
	/**
	 * 메뉴정보 등록
	 * 
	 * @param MenuDto <code>menu</code>
	 * */
	public void insertMenuInfo(MenuDto menu) throws Exception;
	
	/**
	 * 메뉴정보 수정
	 * 
	 * @param MenuDto <code>menu</code>
	 * */
	public void updateMenuInfo(MenuDto menu) throws Exception;
	
	/**
	 * 메뉴정보 삭제
	 * 
	 * @param MenuDto <code>menu</code>
	 * */
	public void deleteMenuInfo(MenuDto menu) throws Exception;
	
	/**
	 * 메뉴코드 중복확인
	 * 
	 * @param MenuDto <code>menu</code>
	 * @return boolean
	 * */
	public boolean menuCodeDoubleCheck(MenuDto menu) throws Exception;
	
	/**
	 * 메뉴 순서 변경
	 * 
	 * @param MenuDto <code>menu</code>
	 * */
	public void updateMenuSort(MenuDto menu) throws Exception;
	
	/**
	 * 해당 깊이 메뉴 목록을 조회한다.
	 * 
	 * @param Map <code>params</code>
	 * @return List<MenuDto>
	 * */	
	public List<MenuDto> getMenuStepList(HashMap<String, Object> params) throws Exception;
	
	/**
	 * 메뉴 접근 권한체크
	 * 
	 * @param String <code>reqUrl</code>
	 * @param String <code>userAuthLevel</code>
	 * @return boolean
	 * */	
	public boolean getMenuAuthCheck(String reqUrl, String userAuthLevel) throws Exception;
	
	/**
	 * 메뉴 접근 로그를 적재한다. 
	 * @param Map <code>params</code>
	 * */
	public void insertMenuAccessLog(Map<String, String> params) throws Exception;
}
