package com.module.forum.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.common.dao.CommonDao;
import com.module.forum.dto.ForumDto;
import com.module.forum.dto.SpeakerDto;
import com.module.forum.dto.SpeakerKindDto;
import com.module.forum.service.ForumService;

@Service("forumService")
public class ForumServiceImpl implements ForumService{

	@Autowired
	private CommonDao commonDao;
	
	@Override
	public void insertForumInfo(ForumDto forum) throws Exception {
		// 포럼/박람회 기본정보 등록
		int forumKey = (Integer)commonDao.insert("FORUM.insertForumInfo", forum);
		forum.setForum_key(String.valueOf(forumKey));
		
		// 상세정보 등록 (개요 및 일정, 포럼프로그램, 전시장 소개, 문화공연행사)
		commonDao.insert("FORUM.insertForumCont", forum);
	}
	
	@Override
	public void updateForumInfo(ForumDto forum) throws Exception {
		// 포럼/박람회 기본정보 변경
		commonDao.update("FORUM.updateForumInfo", forum);
		// 상세정보 등록 (개요 및 일정, 포럼프로그램, 전시장 소개, 문화공연행사)
		commonDao.insert("FORUM.insertForumCont", forum);	
	}
	
	@Override
	public void deleteForumInfo(ForumDto forum) throws Exception {
		commonDao.update("FORUM.deleteForumInfo", forum);
	}	
	
	@Override
	public ForumDto getForumInfo(String forumKey) throws Exception {
		return (ForumDto)commonDao.queryForObject("FORUM.getForumInfo", forumKey);
	}
	
	@Override
	public ForumDto getForumActiveInfo() throws Exception {
		return (ForumDto)commonDao.queryForObject("FORUM.getForumActiveInfo");
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<ForumDto> getForumList(ForumDto forum) throws Exception {		
		return (List<ForumDto>)commonDao.queryForObjectList("FORUM.getForumList", forum);
	}
	
	@Override
	public SpeakerDto getSpeakerInfo(SpeakerDto speaker) throws Exception {		
		return (SpeakerDto)commonDao.queryForObject("FORUM.getSpeakerInfo", speaker);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<SpeakerDto> getSpeakerList(SpeakerDto speaker) throws Exception {
		List<SpeakerDto> result = null;
		int totalCount = (Integer)commonDao.queryForObject("FORUM.getSpeakerTotalCount", speaker);
		if(totalCount > 0){
			speaker.setTotal_count(totalCount);
			result = (List<SpeakerDto>)commonDao.queryForObjectList("FORUM.getSpeakerList", speaker);
		}
		return result;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<SpeakerKindDto> getSpeakerKindList() throws Exception {
		return (List<SpeakerKindDto>)commonDao.queryForObjectList("FORUM.getSpeakerKindList");
	}
	
	@Override
	public void insertSpeakerInfo(SpeakerDto speaker) throws Exception {
		commonDao.insert("FORUM.insertSpeakerInfo", speaker);
	}
	
	@Override
	public void updateSpeakerInfo(SpeakerDto speaker) throws Exception {
		commonDao.insert("FORUM.updateSpeakerInfo", speaker);		
	}
	
	@Override
	public void deleteSpeakerInfo(SpeakerDto speaker) throws Exception {
		commonDao.insert("FORUM.deleteSpeakerInfo", speaker);		
	}	
}