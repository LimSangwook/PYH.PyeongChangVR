package com.module.menu.serviceImpl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.common.dao.CommonDao;
import com.module.menu.dto.MenuDto;
import com.module.menu.service.MenuService;

@Service("menuService")
public class MenuServiceImpl implements MenuService{

	@Autowired
	private CommonDao commonDao;
	
	@Override
	public void insertMenuInfo(MenuDto menu) throws Exception {		
		if(menu.getOrder_step() == 1){
			menu.setParent_menu_code(menu.getSystem_gubun());
			menu.setMenu_location_code(menu.getMenu_code());
		} else if(menu.getOrder_step() == 2){
			menu.setParent_menu_code(menu.getDepth1());
			menu.setMenu_location_code(menu.getDepth1()+"_"+menu.getMenu_code());
		} else if(menu.getOrder_step() == 3){
			menu.setParent_menu_code(menu.getDepth2());
			menu.setMenu_location_code(menu.getDepth1()+"_"+menu.getDepth2()+"_"+menu.getMenu_code());
		}
		// 다음 정렬순서를 가져온다.
		int nextOrderLevel = (Integer)commonDao.queryForObject("MENU.getMenuNextOrderLevel", menu);
		menu.setOrder_level(nextOrderLevel);
		menu.setMenu_auth_level(menu.getMenuAuthJoin());
		
		// 메뉴정보 등록
		commonDao.insert("MENU.insertMenuInfo", menu);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<MenuDto> getMenuList(String parentMenuCode) throws Exception {
		return (List<MenuDto>)commonDao.queryForObjectList("MENU.getMenuList", parentMenuCode);
	}	
	
	@Override
	public MenuDto getMenuInfo(MenuDto menu) throws Exception {
		return (MenuDto)commonDao.queryForObject("MENU.getMenuInfo", menu);
	}
	
	@Override
	public void updateMenuInfo(MenuDto menu) throws Exception {
		menu.setMenu_auth_level(menu.getMenuAuthJoin());
		commonDao.update("MENU.updateMenuInfo", menu);		
	}
	
	@Override
	public void deleteMenuInfo(MenuDto menu) throws Exception {
		commonDao.update("MENU.deleteMenuInfo", menu);		
	}
	
	@Override
	public boolean menuCodeDoubleCheck(MenuDto menu) throws Exception {		
		int result = (Integer)commonDao.queryForObject("MENU.menuCodeDoubleCheck", menu);
		return result > 0 ? false:true;
	}
	
	@Override
	public void updateMenuSort(MenuDto menu) throws Exception {
		if(menu.getMenu_code_arr() != null){
			MenuDto data = null;
			List<MenuDto> list = new ArrayList<>();
			int i=0;
			int orderLevel = 0;
			int currentStep = 1;
			for (String menuCode : menu.getMenu_code_arr()) {
				if(currentStep != Integer.valueOf(menu.getMenu_step_arr().get(i))){
					orderLevel = 0;
				}
				data = new MenuDto();
				data.setMenu_code(menuCode);
				data.setOrder_level(++orderLevel);
				list.add(data);
				
				currentStep = Integer.valueOf(menu.getMenu_step_arr().get(i));
				i++;
			}
			commonDao.batchUpdate("MENU.updateMenuSort", list);	
		}		
	}
		
	@SuppressWarnings("unchecked")
	@Override
	public List<MenuDto> getMenuStepList(HashMap<String, Object> params) throws Exception {		
		return (List<MenuDto>)commonDao.queryForObjectList("MENU.getMenuStepList", params);
	}
	
	@Override
	public boolean getMenuAuthCheck(String reqUrl, String userAuthLevel) throws Exception {
		Map<String, String> params = new HashMap<>();
		params.put("req_url", reqUrl);
		params.put("auth_level", userAuthLevel);		
		return (Integer)commonDao.queryForObject("MENU.getMenuAuthCheck", params) > 0 ? true : false;
	}
	
	@Override
	public void insertMenuAccessLog(Map<String, String> params) throws Exception {
		commonDao.insert("LOGS.insertMenuAccessLog", params);		
	}
}