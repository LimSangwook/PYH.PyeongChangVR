package com.module.user.serviceImpl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.common.dao.CommonDao;
import com.common.util.CommonWebUtils;
import com.common.util.JProperties;
import com.common.util.StringEncrypter;
import com.module.museum.dto.MuseumAuthMapDto;
import com.module.user.dto.UserDto;
import com.module.user.service.UserService;

@Service("userService")
public class UserServiceImpl implements UserService {

	@Autowired
	private CommonDao commonDao;
		
	private final String[] ID_REGIST_LIMIT_LIST = {"admin","administrator","manager","manage"};
	
	@SuppressWarnings("unchecked")
	@Override
	public List<UserDto> getUserList(UserDto user) throws Exception {		
		List<UserDto> result = null;
		int totalCount = (Integer)commonDao.queryForObject("USER.getUserTotalCount", user);
		if(totalCount > 0){
			result = (List<UserDto>)commonDao.queryForObjectList("USER.getUserList", user);
			StringEncrypter se = new StringEncrypter(JProperties.getString("SITE.PW.KEY"), JProperties.getString("SITE.PW.INITIALVECTOR"));
			for (UserDto data : result) {
				if(StringUtils.isNotBlank(data.getEmail())){
					data.setEmail(se.decrypt(data.getEmail()));
					data.setPhone(se.decrypt(data.getPhone()));
				}
			}
			user.setTotal_count(totalCount);
		}
		return result;
	}
	
	@Override
	public boolean userIdDoubleCheck(HttpServletRequest request, UserDto user) throws Exception {
		int result = (Integer)commonDao.queryForObject("USER.userIdDoubleCheck", user);
		UserDto sessionInfo = (UserDto)CommonWebUtils.getSession(request);
		// 일반 사용자 아이디 중복확인 시... 사용 불가 아이디 체크...
		if(sessionInfo == null){
			for (String id : ID_REGIST_LIMIT_LIST) {
				if(StringUtils.contains(user.getUser_id(), id)){					
					return false;
				}
			}
		}
		return result > 0 ? false:true;
	}
	
	@Override
	public void insertUserInfo(UserDto user) throws Exception {
		user.setUser_id(user.getUser_id().toLowerCase());
		setUserEncrypt(user);
		commonDao.insert("USER.insertUserInfo", user);
		// 박물관권한매핑
		museumAuthMap(user);
	}
	
	@Override
	public void insertMemberJoin(UserDto user) throws Exception {
		user.setUser_id(user.getUser_id().toLowerCase());
		user.setAuth_level("1");
		user.setStatus("Y");
		
		setUserEncrypt(user);
		commonDao.insert("USER.insertUserInfo", user);
		commonDao.insert("USER.insertUserIdentityInfo", user);
	}
	
	@Override
	public void updateUserInfo(UserDto user) throws Exception {
		setUserEncrypt(user);
		commonDao.update("USER.updateUserInfo", user);
		// 박물관권한매핑
		museumAuthMap(user);
	}
	
	@Override
	public void updateMyInfo(UserDto user) throws Exception {
		setUserEncrypt(user);
		commonDao.update("USER.updateMyInfo", user);
		commonDao.update("USER.updateUserIdentityInfo", user);
	}
	
	@Override
	public UserDto getUserInfo(UserDto user) throws Exception {
		UserDto result = (UserDto)commonDao.queryForObject("USER.getUserInfo", user);
		UserDto userIdentity = (UserDto)commonDao.queryForObject("USER.getUserIdentityInfo", user);
		if(userIdentity != null){
			result.setIs_agree1(userIdentity.getIs_agree1());
			result.setIs_agree2(userIdentity.getIs_agree2());
			result.setBirth_day(userIdentity.getBirth_day());
			result.setZip_code(userIdentity.getZip_code());
			result.setAddress1(userIdentity.getAddress1());
			result.setAddress2(userIdentity.getAddress2());
		}
		setUserDecrypt(result);
		return result;
	}
	
	@Override
	public void deleteUserInfo(UserDto user) throws Exception {
		commonDao.update("USER.deleteUserInfo", user);		
	}
	
	@Override
	public UserDto getAdminLoginInfo(UserDto user) throws Exception {
		return (UserDto)commonDao.queryForObject("USER.getAdminLoginInfo", user);
	}
	
	@Override
	public UserDto getMemberLoginInfo(UserDto user) throws Exception {
		return (UserDto)commonDao.queryForObject("USER.getMemberLoginInfo", user);
	}
	
	@Override
	public void updateUserIdBlock(UserDto user) throws Exception {
		commonDao.update("USER.updateUserIdBlock", user);		
	}
	
	@Override
	public boolean isMemberJoin(String di_key) throws Exception {		
		return (Integer)commonDao.queryForObject("USER.isMemberJoin", di_key) > 0 ? true:false;
	}
	
	@Override
	public UserDto getMemberFindInfo(String userId, String diKey, String identityType) throws Exception {
		Map<String, String> params = new HashMap<>();
		params.put("user_id", userId);
		params.put("di_key", diKey);
		params.put("identity_type", identityType);
		return (UserDto)commonDao.queryForObject("USER.getMemberFindInfo", params);
	}
	
	@Override
	public void updateMemberPw(UserDto user) throws Exception {
		setUserEncrypt(user);
		commonDao.update("USER.updateMemberImsiPw", user);
		
	}
	
	@Override
	public void insertSnsLoginInfo(String snsLoginId, String snsType, String nickName) throws Exception {
		Map<String, String> params = new HashMap<>();
		params.put("sns_login_id", snsLoginId);
		params.put("sns_type", snsType);
		params.put("nick_name", nickName);
		
		commonDao.insert("USER.snsLoginInfoSave", params);
	}
	
	// 박물관 권한매핑
	private void museumAuthMap(UserDto user) throws Exception {
		if(user.getMuseumNoArr() != null){
			List<MuseumAuthMapDto> list = new ArrayList<>();
			MuseumAuthMapDto map;
			int i=0;
			for(String data : user.getMuseumNoArr()){
				map = new MuseumAuthMapDto();
				map.setUser_id(user.getUser_id());
				map.setMuseum_no(data);
				map.setAuth_status(user.getMuseumAuthStatusArr()[i]);
				list.add(map);
				i++;				
			}
			commonDao.batchInsert("MUSEUM.insertMuseumAuthMap", list);
		}
	}
	
	// 사용자 정보 암호화
	private void setUserEncrypt(UserDto user) throws Exception {
		StringEncrypter se = new StringEncrypter(JProperties.getString("SITE.PW.KEY"), JProperties.getString("SITE.PW.INITIALVECTOR"));
		if(StringUtils.isNotBlank(user.getPasswd())){
			user.setPasswd(se.encrypt(user.getPasswd()));
		}
		if(StringUtils.isNotBlank(user.getPhone1()) && StringUtils.isNotBlank(user.getPhone2()) && StringUtils.isNotBlank(user.getPhone3())){
			user.setPhone(se.encrypt(user.getPhone1()+"-"+user.getPhone2()+"-"+user.getPhone3()));
		}
		if(StringUtils.isNotBlank(user.getEmail())){
			user.setEmail(se.encrypt(user.getEmail()));
		}
		if(StringUtils.isNotBlank(user.getEmail_id()) && StringUtils.isNotBlank(user.getEmail_domain())){
			user.setEmail(se.encrypt(user.getEmail_id()+"@"+user.getEmail_domain()));
		}
		if(StringUtils.isNotBlank(user.getZip_code())){
			user.setZip_code(se.encrypt(user.getZip_code()));
		}
		if(StringUtils.isNotBlank(user.getAddress1())){
			user.setAddress1(se.encrypt(user.getAddress1()));
		}
		if(StringUtils.isNotBlank(user.getAddress2())){
			user.setAddress2(se.encrypt(user.getAddress2()));
		}
		if(StringUtils.isNotBlank(user.getBirth_day())){
			user.setBirth_day(se.encrypt(user.getBirth_day()));
		}
	}
	
	// 사용자 정보 복호화
	private void setUserDecrypt(UserDto user) throws Exception {
		if(user != null){
			StringEncrypter se = new StringEncrypter(JProperties.getString("SITE.PW.KEY"), JProperties.getString("SITE.PW.INITIALVECTOR"));		
			if(StringUtils.isNotBlank(user.getPhone())){
				user.setPhone(se.decrypt(user.getPhone()));
			}
			if(StringUtils.isNotBlank(user.getEmail())){
				user.setEmail(se.decrypt(user.getEmail()));
			}
			if(StringUtils.isNotBlank(user.getBirth_day())){
				user.setBirth_day(se.decrypt(user.getBirth_day()));
			}
			if(StringUtils.isNotBlank(user.getZip_code())){
				user.setZip_code(se.decrypt(user.getZip_code()));
			}
			if(StringUtils.isNotBlank(user.getAddress1())){
				user.setAddress1(se.decrypt(user.getAddress1()));
			}
			if(StringUtils.isNotBlank(user.getAddress2())){
				user.setAddress2(se.decrypt(user.getAddress2()));
			}
		}		
	}
}