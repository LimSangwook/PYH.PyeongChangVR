package com.module.parti.dto;

import org.apache.commons.lang.StringUtils;

import com.common.base.BaseForm;

public class PartiCompanyDto extends BaseForm{
	
	private static final long serialVersionUID = -7751385786269979875L;
	
	// 참여업체번호
	private String company_key;
	// 회사명
	private String company_name;
	// 산업분야
	private String business_kind_name;
	// 대표자
	private String ceo_name;
	// 대표번호
	private String master_phone;
	private String master_phone1;
	private String master_phone2;
	private String master_phone3;	
	// 대표이메일
	private String master_email;
	private String master_email_id;
	private String master_email_domain;
	
	// 담당자
	private String contact_name; 
	// 이메일
	private String email;
	private String email_id;
	private String email_domain;
	// 연락처
	private String phone;
	private String phone1;
	private String phone2;
	private String phone3;
	// 팩스번호
	private String fax;	 
	private String fax1;
	private String fax2;
	private String fax3;
	// 홈페이지주소
	private String home_page;
	
	// 우편번호
	private String zip_code;
	// 기본주소
	private String address1;
	// 상세주소
	private String address2;
	
	private String status;
	
	// 기업상세설명
	private String content;
	// 로고이미지
	private String master_image;
	
	public String[] getEmailArr(){
		return StringUtils.split(email, "@");
	}
	public String[] getMasterEmailArr(){
		return StringUtils.split(master_email, "@");
	}
	public String[] getPhoneArr(){
		return StringUtils.split(phone, "-");
	}
	public String[] getMasterPhoneArr(){
		return StringUtils.split(master_phone, "-");
	}
	public String[] getFaxArr(){
		return StringUtils.split(fax, "-");
	}
	
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getCompany_key() {
		return company_key;
	}
	public void setCompany_key(String company_key) {
		this.company_key = company_key;
	}
	public String getCompany_name() {
		return company_name;
	}
	public void setCompany_name(String company_name) {
		this.company_name = company_name;
	}
	public String getBusiness_kind_name() {
		return business_kind_name;
	}
	public void setBusiness_kind_name(String business_kind_name) {
		this.business_kind_name = business_kind_name;
	}
	public String getCeo_name() {
		return ceo_name;
	}
	public void setCeo_name(String ceo_name) {
		this.ceo_name = ceo_name;
	}
	public String getMaster_phone() {
		return master_phone;
	}
	public void setMaster_phone(String master_phone) {
		this.master_phone = master_phone;
	}
	public String getMaster_phone1() {
		return master_phone1;
	}
	public void setMaster_phone1(String master_phone1) {
		this.master_phone1 = master_phone1;
	}
	public String getMaster_phone2() {
		return master_phone2;
	}
	public void setMaster_phone2(String master_phone2) {
		this.master_phone2 = master_phone2;
	}
	public String getMaster_phone3() {
		return master_phone3;
	}
	public void setMaster_phone3(String master_phone3) {
		this.master_phone3 = master_phone3;
	}
	public String getMaster_email() {
		return master_email;
	}
	public void setMaster_email(String master_email) {
		this.master_email = master_email;
	}
	public String getMaster_email_id() {
		return master_email_id;
	}
	public void setMaster_email_id(String master_email_id) {
		this.master_email_id = master_email_id;
	}
	public String getMaster_email_domain() {
		return master_email_domain;
	}
	public void setMaster_email_domain(String master_email_domain) {
		this.master_email_domain = master_email_domain;
	}
	public String getContact_name() {
		return contact_name;
	}
	public void setContact_name(String contact_name) {
		this.contact_name = contact_name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
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
	public String getFax() {
		return fax;
	}
	public void setFax(String fax) {
		this.fax = fax;
	}
	public String getFax1() {
		return fax1;
	}
	public void setFax1(String fax1) {
		this.fax1 = fax1;
	}
	public String getFax2() {
		return fax2;
	}
	public void setFax2(String fax2) {
		this.fax2 = fax2;
	}
	public String getFax3() {
		return fax3;
	}
	public void setFax3(String fax3) {
		this.fax3 = fax3;
	}
	public String getHome_page() {
		return home_page;
	}
	public void setHome_page(String home_page) {
		this.home_page = home_page;
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
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getMaster_image() {
		return master_image;
	}
	public void setMaster_image(String master_image) {
		this.master_image = master_image;
	}
}
