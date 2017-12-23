package com.module.html.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.common.dao.CommonDao;
import com.module.html.dto.HtmlDto;
import com.module.html.service.HtmlService;

@Service("htmlService")
public class HtmlServiceImpl implements HtmlService{

	@Autowired
	private CommonDao commonDao;
	
	@Override
	public void insertHtmlInfo(HtmlDto html) throws Exception {
		commonDao.insert("HTML.insertHtmlInfo", html);
	}
	
	@Override
	public void updateHtmlInfo(HtmlDto html) throws Exception {
		commonDao.update("HTML.updateHtmlInfo", html);
	}
	
	@Override
	public void deleteHtmlInfo(HtmlDto html) throws Exception {
		commonDao.update("HTML.deleteHtmlInfo", html);		
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<HtmlDto> getHtmlList(HtmlDto html) throws Exception {
		return (List<HtmlDto>)commonDao.queryForObjectList("HTML.getHtmlList", html);
	}
	
	@Override
	public HtmlDto getHtmlInfo(String pageSeq) throws Exception {		
		return (HtmlDto)commonDao.queryForObject("HTML.getHtmlInfo", pageSeq);
	}	
}
