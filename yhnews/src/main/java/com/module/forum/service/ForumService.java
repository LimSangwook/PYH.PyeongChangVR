package com.module.forum.service;

import java.util.List;

import com.module.forum.dto.ForumDto;
import com.module.forum.dto.SpeakerDto;
import com.module.forum.dto.SpeakerKindDto;

public interface ForumService {

	/**
	 * 포럼/박람회 정보등록
	 * */
	public void insertForumInfo(ForumDto forum) throws Exception;
	
	/**
	 * 포럼/박람회 정보수정
	 * */
	public void updateForumInfo(ForumDto forum) throws Exception;
	
	/**
	 * 포럼/박람회 정보삭제
	 * */
	public void deleteForumInfo(ForumDto forum) throws Exception;
	
	/**
	 * 포럼/박람회 상세조회
	 * */
	public ForumDto getForumInfo(String forumKey) throws Exception;
	
	/**
	 * 현재 진행중인 포럼/박람회 조회
	 * */
	public ForumDto getForumActiveInfo() throws Exception;
	
	/**
	 * 포럼/박람회 목록조회
	 * search_type 전체 : 1, 지난포럼 : 2
	 * */
	public List<ForumDto> getForumList(ForumDto forum) throws Exception;
	
	/**
	 * 연사 정보등록
	 * */
	public void insertSpeakerInfo(SpeakerDto speaker) throws Exception;
	
	/**
	 * 연사 정보수정
	 * */
	public void updateSpeakerInfo(SpeakerDto speaker) throws Exception;
	
	/**
	 * 연사 정보삭제
	 * */
	public void deleteSpeakerInfo(SpeakerDto speaker) throws Exception;
	
	/**
	 * 연사 상세정보
	 * */
	public SpeakerDto getSpeakerInfo(SpeakerDto speaker) throws Exception;
	
	/**
	 * 연사 목록
	 * */
	public List<SpeakerDto> getSpeakerList(SpeakerDto speaker) throws Exception;
	
	/**
	 * 연사 구분(분과) 목록
	 * */
	public List<SpeakerKindDto> getSpeakerKindList() throws Exception;
}
