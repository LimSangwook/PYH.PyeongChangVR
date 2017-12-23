package com.module.user.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.module.user.dto.UserDto;

public interface UserService {
	
	/**
	 * 아이디 중복확인
	 * 
	 * @param UserDto <code>user</code>
	 * @return boolean
	 * */
	public boolean userIdDoubleCheck(HttpServletRequest request, UserDto user) throws Exception;
	
	/**
	 * 사용자 목록을 조회한다.
	 * 
	 * @param UserDto <code>user</code>
	 * @return List<UserDto>
	 * */
	public List<UserDto> getUserList(UserDto user) throws Exception;
	
	/**
	 * 사용자 상세정보를 조회한다.
	 * 
	 * @param UserDto <code>user</code>
	 * @return UserDto
	 * */
	public UserDto getUserInfo(UserDto user) throws Exception;
	
	/**
	 * 사용자 정보를 등록한다.
	 * 
	 * @param UserDto <code>user</code>
	 * */
	public void insertUserInfo(UserDto user) throws Exception;
	
	/**
	 * 사용자 정보를 수정한다.
	 * 
	 * @param UserDto <code>user</code>
	 * */
	public void updateUserInfo(UserDto user) throws Exception;
	
	/**
	 * 내 정보를 수정한다.
	 * 
	 * @param UserDto <code>user</code>
	 * */
	public void updateMyInfo(UserDto user) throws Exception;
	
	/**
	 * 사용자 정보를 삭제한다.
	 * 
	 * @param UserDto <code>user</code>
	 * */
	public void deleteUserInfo(UserDto user) throws Exception;
	
	/**
	 * 관리자 로그인
	 * 
	 * @param UserDto <code>user</code>
	 * @return UserDto
	 * */
	public UserDto getAdminLoginInfo(UserDto user) throws Exception;
	
	/**
	 * 사용자 로그인
	 * 
	 * @param UserDto <code>user</code>
	 * @return UserDto
	 * */
	public UserDto getMemberLoginInfo(UserDto user) throws Exception;
	
	/**
	 * 사용자 계정을 차단합니다.
	 * @param UserDto <code>user</code>
	 * */
	public void updateUserIdBlock(UserDto user) throws Exception;
	
	/**
	 * 회원가입여부 확인
	 * - DI 회원중복확인 값으로 비교
	 * @param String <code>di_key</code>
	 * */
	public boolean isMemberJoin(String di_key) throws Exception;
	
	/**
	 * 신규 회원가입 
	 * @param UserDto <code>user</code>
	 * */
	public void insertMemberJoin(UserDto user) throws Exception;
	
	/**
	 * 회원 계정 찾기
	 * @param UserDto <code>user</code>
	 * */
	public UserDto getMemberFindInfo(String userId, String diKey, String identityType) throws Exception;
	
	/**
	 * 회원 계정 찾기
	 * @param UserDto <code>user</code>
	 * */
	public void updateMemberPw(UserDto user) throws Exception;
	
	/**
	 * SNS 로그인 정보 등록
	 * */
	public void insertSnsLoginInfo(String snsLoginId, String snsType, String nickName) throws Exception; 
}
