package com.module.user.dto;

import java.util.Date;

import org.apache.commons.lang.StringUtils;

import com.common.base.BaseForm;

public class UserDto extends BaseForm{
	
	private static final long serialVersionUID = 9125783187915636059L;
		
	private String user_id;
	private String user_name;
	private String passwd;
	private String email;
	private String email_id;
	private String email_domain;
	
	private String phone;
	private String phone1;
	private String phone2;
	private String phone3;
	
	private String auth_level;
	private String status;	
	private String reg_id;
	private Date reg_date;
	
	private String sns_type;
	private String franchise_key;	
	
	// 문자수신 유무
	private String is_sms_use;
	// 이메일 수신 유무
	private String is_email_use;
	
	// 생년월일 - yyyyMMdd
	private String birth_day;
	// 본인인증 중복가입여부 확인 값 (64byte)
	private String di_key;	
	
	// 약관동의1
	private String is_agree1;
	// 약관동의2
	private String is_agree2;
	// 우편번호
	private String zip_code;
	// 기본주소
	private String address1;
	// 상세주소
	private String address2;
	// 본인인증수단
	private String identity_type;

	/**
	 * 박물관 권한매핑 파라미터
	 * */
	// 박물관권한상태
	private String[] museumAuthStatusArr;
	// 박물관번호
	private String[] museumNoArr;	
	
	// 브랜치 관리자 대표 박물관 번호지정
	private String museum_no;	
	
	public String getMuseum_no() {
		return museum_no;
	}
	public void setMuseum_no(String museum_no) {
		this.museum_no = museum_no;
	}
	public String[] getMuseumAuthStatusArr() {
		return museumAuthStatusArr;
	}
	public void setMuseumAuthStatusArr(String[] museumAuthStatusArr) {
		this.museumAuthStatusArr = museumAuthStatusArr;
	}
	public String[] getMuseumNoArr() {
		return museumNoArr;
	}
	public void setMuseumNoArr(String[] museumNoArr) {
		this.museumNoArr = museumNoArr;
	}
	public String getIs_sms_use() {
		return StringUtils.defaultIfBlank(is_sms_use,"Y");
	}
	public void setIs_sms_use(String is_sms_use) {
		this.is_sms_use = is_sms_use;
	}
	public String getIs_email_use() {
		return StringUtils.defaultIfBlank(is_email_use,"Y");
	}
	public void setIs_email_use(String is_email_use) {
		this.is_email_use = is_email_use;
	}
	public String[] getEmailArr(){
		return StringUtils.split(email, "@");
	}
	public String[] getPhoneArr(){
		return StringUtils.split(phone, "-");
	}
	public String getDisplayBirthDay(){
		return StringUtils.substring(birth_day, 0, 4)+"-"+StringUtils.substring(birth_day, 4, 6)+"-"+StringUtils.substring(birth_day, 6);
	}
	public String getEmail_id() {
		return email_id;
	}
	public void setEmail_id(String email_id) {
		this.email_id = email_id;
	}
	public String getEmail_domain() {
		return email_domain;
	}
	public void setEmail_domain(String email_domain) {
		this.email_domain = email_domain;
	}
	public String getIs_agree1() {
		return is_agree1;
	}
	public void setIs_agree1(String is_agree1) {
		this.is_agree1 = is_agree1;
	}
	public String getIs_agree2() {
		return is_agree2;
	}
	public void setIs_agree2(String is_agree2) {
		this.is_agree2 = is_agree2;
	}
	public String getZip_code() {
		return zip_code;
	}
	public void setZip_code(String zip_code) {
		this.zip_code = zip_code;
	}
	public String getAddress1() {
		return address1;
	}
	public void setAddress1(String address1) {
		this.address1 = address1;
	}
	public String getAddress2() {
		return address2;
	}
	public void setAddress2(String address2) {
		this.address2 = address2;
	}
	public String getIdentity_type() {
		return identity_type;
	}
	public void setIdentity_type(String identity_type) {
		this.identity_type = identity_type;
	}
	public String getBirth_day() {
		return birth_day;
	}
	public void setBirth_day(String birth_day) {
		this.birth_day = birth_day;
	}
	public String getDi_key() {
		return di_key;
	}
	public void setDi_key(String di_key) {
		this.di_key = di_key;
	}
	public String getFranchise_key() {
		return franchise_key;
	}
	public void setFranchise_key(String franchise_key) {
		this.franchise_key = franchise_key;
	}	
	public String getSns_type() {
		return sns_type;
	}
	public void setSns_type(String sns_type) {
		this.sns_type = sns_type;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getPhone1() {
		return phone1;
	}
	public void setPhone1(String phone1) {
		this.phone1 = phone1;
	}
	public String getPhone2() {
		return phone2;
	}
	public void setPhone2(String phone2) {
		this.phone2 = phone2;
	}
	public String getPhone3() {
		return phone3;
	}
	public void setPhone3(String phone3) {
		this.phone3 = phone3;
	}
	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	public String getUser_name() {
		return user_name;
	}
	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}	
	public String getPasswd() {
		return passwd;
	}
	public void setPasswd(String passwd) {
		this.passwd = passwd;
	}
	public String getAuth_level() {
		return auth_level;
	}
	public void setAuth_level(String auth_level) {
		this.auth_level = auth_level;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getReg_id() {
		return reg_id;
	}
	public void setReg_id(String reg_id) {
		this.reg_id = reg_id;
	}
	public Date getReg_date() {
		return reg_date;
	}
	public void setReg_date(Date reg_date) {
		this.reg_date = reg_date;
	}
}