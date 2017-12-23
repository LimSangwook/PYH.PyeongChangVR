package com.module.museum.dto;

import java.util.Date;

import org.apache.commons.lang.StringUtils;

import com.common.base.BaseForm;

public class MuseumDto extends BaseForm{
	
	private static final long serialVersionUID = 7655379955369154047L;
	
	// 박물관번호
	private String museum_no;
	// 박물관유형 공립 : 1, 사립 : 2
	private String kind;
	// 박물관명
	private String museum_name;
	// 박물관설명
	private String cont_expl;
	// 휴관일
	private String closed_day;
	// 대표번호
	private String phone;
	// 팩스번호
	private String fax;
	// 우편번호
	private String zip_code;
	// 주소
	private String address1;
	// 상세주소
	private String address2;
	// 메인이미지
	private String main_image;
	// 서브이미지
	private String sub_image;
	// 상태 사용 : Y, 미사용 : N, 삭제 : D
	private String status;
	// 등록일시
	private Date reg_date;
	
	// 박물관 접근권한확인 필드
	private String isAuthCheck;	
	
	private String link_domain;
		
	public String getLink_domain() {
		return link_domain;
	}
	public void setLink_domain(String link_domain) {
		this.link_domain = link_domain;
	}
	public String getDisplayMuseumKind(){
		return StringUtils.equals("1", kind)?"공립":"사립";
	}	
	public String getIsAuthCheck() {
		return isAuthCheck;
	}
	public void setIsAuthCheck(String isAuthCheck) {
		this.isAuthCheck = isAuthCheck;
	}
	public String getMuseum_no() {
		return museum_no;
	}
	public void setMuseum_no(String museum_no) {
		this.museum_no = museum_no;
	}
	public String getKind() {
		return kind;
	}
	public void setKind(String kind) {
		this.kind = kind;
	}
	public String getMuseum_name() {
		return museum_name;
	}
	public void setMuseum_name(String museum_name) {
		this.museum_name = museum_name;
	}
	public String getCont_expl() {
		return cont_expl;
	}
	public void setCont_expl(String cont_expl) {
		this.cont_expl = cont_expl;
	}
	public String getClosed_day() {
		return closed_day;
	}
	public void setClosed_day(String closed_day) {
		this.closed_day = closed_day;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getFax() {
		return fax;
	}
	public void setFax(String fax) {
		this.fax = fax;
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
	public String getMain_image() {
		return main_image;
	}
	public void setMain_image(String main_image) {
		this.main_image = main_image;
	}
	public String getSub_image() {
		return sub_image;
	}
	public void setSub_image(String sub_image) {
		this.sub_image = sub_image;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Date getReg_date() {
		return reg_date;
	}
	public void setReg_date(Date reg_date) {
		this.reg_date = reg_date;
	}
}
