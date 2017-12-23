package com.module.parti.service;

import java.util.List;

import com.module.parti.dto.PartiCompanyDto;

public interface PartiCompanyService {

	/**
	 * 참여업체 등록
	 * */
	public void insertCompanyInfo(PartiCompanyDto company) throws Exception;
	
	/**
	 * 참여업체 수정
	 * */
	public void updateCompanyInfo(PartiCompanyDto company) throws Exception;
	
	/**
	 * 참여업체 삭제
	 * */
	public void deleteCompanyInfo(PartiCompanyDto company) throws Exception;
	
	/**
	 * 참여업체 목록
	 * */
	public List<PartiCompanyDto> getCompanyList(PartiCompanyDto company) throws Exception;
	
	/**
	 * 참여업체 정보
	 * */
	public PartiCompanyDto getCompanyInfo(String companyKey) throws Exception;
	
}
