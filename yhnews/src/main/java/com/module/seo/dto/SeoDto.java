package com.module.seo.dto;

import com.common.base.BaseForm;

public class SeoDto extends BaseForm{
	
	private static final long serialVersionUID = 5416121343488068379L;

	private String seo_key;				// 일련번호	
	private String homepage;			// 홈페이지주소
	private String title;					// 사이트제목
	private String content;				// 사이트내용
	private String keyword;			// 사이트키워드
	private String email;				// 공식이메일
	private String company_name;	// 운영기관
	private String tel;					// 전화번호
	private String fax;					// 팩스번호
	private String address;				// 주소
	private String open_day;			// 홈페이지개설일 yyyy-MM-dd
	
	public String getSeo_key() {
		return seo_key;
	}
	public void setSeo_key(String seo_key) {
		this.seo_key = seo_key;
	}
	public String getHomepage() {
		return homepage;
	}
	public void setHomepage(String homepage) {
		this.homepage = homepage;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getKeyword() {
		return keyword;
	}
	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getCompany_name() {
		return company_name;
	}
	public void setCompany_name(String company_name) {
		this.company_name = company_name;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public String getFax() {
		return fax;
	}
	public void setFax(String fax) {
		this.fax = fax;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getOpen_day() {
		return open_day;
	}
	public void setOpen_day(String open_day) {
		this.open_day = open_day;
	}
}