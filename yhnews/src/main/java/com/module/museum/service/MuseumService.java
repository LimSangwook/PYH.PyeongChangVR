package com.module.museum.service;

import java.util.List;

import com.module.museum.dto.MuseumAuthMapDto;
import com.module.museum.dto.MuseumDto;
import com.module.museum.dto.MuseumMenuContDto;
import com.module.museum.dto.MuseumMenuDto;

public interface MuseumService {

	/**
	 * 박물관 기본정보 생성 & 메뉴 & 게시판생성(공지사항, 스케쥴)
	 * */
	public void insertMuseumInfo(MuseumDto museum) throws Exception;
	
	/**
	 * 박물관 기본정보 수정
	 * */
	public void updateMuseumInfo(MuseumDto museum) throws Exception;
	
	/**
	 * 박물관 페이지 내용 수정
	 * */
	public void updateMuseumPageInfo(MuseumMenuContDto museum) throws Exception;
	
	/**
	 * 박물관 기본정보 조회
	 * */
	public MuseumDto getMuseumInfo(String museumNo) throws Exception;
	
	/**
	 * 박물관 기본정보 조회 (사용중인 박물관)
	 * */
	public MuseumDto getMuseumActiveInfo(String museumNo) throws Exception;
	
	/**
	 * 박물관 메뉴 내용 조회
	 * */
	public MuseumMenuContDto getMuseumMenuContInfo(MuseumMenuContDto museum) throws Exception;
	
	/**
	 * 박물관 목록
	 * */
	public List<MuseumDto> getMuseumList(String status) throws Exception;
	
	/**
	 * 박물관 메뉴내용 목록
	 * */
	public List<MuseumMenuDto> getMuseumMenuContList(String museumNo) throws Exception;
	
	/**
	 * 박물관 접근권한 매핑목록
	 * */
	public List<MuseumAuthMapDto> getMuseumAuthMapList(String userId) throws Exception;
	
	/**
	 * 박물관 도메인 목록
	 * */
	public List<MuseumDto> getMuseumDomainList() throws Exception;
}
