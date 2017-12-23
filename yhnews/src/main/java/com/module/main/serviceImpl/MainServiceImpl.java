package com.module.main.serviceImpl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.common.dao.CommonDao;
import com.common.util.JProperties;
import com.module.main.service.MainService;

@Service("mainService")
public class MainServiceImpl implements MainService{

	@Autowired
	private CommonDao commonDao;
	
	@Override
	public long getMemberTotalCount() throws Exception {
		return (long)commonDao.queryForObject("COMMON.getMemberTotalCount");
	}
	
	@Override
	public long getPostTotalCount() throws Exception {
		return (long)commonDao.queryForObject("COMMON.getPostTotalCount");
	}
	
	@Override
	public long getPostReplyTotalCount() throws Exception {
		return (long)commonDao.queryForObject("COMMON.getPostReplyTotalCount");
	}
	
	@Override
	public long getDataBaseTotalUseSize() throws Exception {
		
		return (long)commonDao.queryForObject("COMMON.getDataBaseUseSize",JProperties.getString("DB.TABLE.SCHEMA"));
	}
	
	@Override
	public long getFileTotalUseSize() throws Exception {
		return (long)commonDao.queryForObject("COMMON.getFileTotalUseSize");
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Map<String, String>> getCommonCodeList(String parentCode) throws Exception {
		return (List<Map<String, String>>)commonDao.queryForObjectList("COMMON.getCommonCodeList", parentCode);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Map<String, String>> getSiteServiceLangList() throws Exception {
		return (List<Map<String, String>>)commonDao.queryForObjectList("COMMON.getSiteServiceLangList");
	}
}
