package com.module.museum.dto;

import java.io.Serializable;
import java.util.Date;

public class MuseumAuthMapDto implements Serializable{
	
	private static final long serialVersionUID = -3111956671466261026L;
	
	// 관리자 아이디
	private String user_id;
	// 박물관번호
	private String museum_no;
	// 권한상태
	private String auth_status;
	// 등록일시
	private Date reg_date;
	
	private String museum_name;
	
	
	public String getMuseum_name() {
		return museum_name;
	}
	public void setMuseum_name(String museum_name) {
		this.museum_name = museum_name;
	}
	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}	
	public String getMuseum_no() {
		return museum_no;
	}
	public void setMuseum_no(String museum_no) {
		this.museum_no = museum_no;
	}
	public String getAuth_status() {
		return auth_status;
	}
	public void setAuth_status(String auth_status) {
		this.auth_status = auth_status;
	}
	public Date getReg_date() {
		return reg_date;
	}
	public void setReg_date(Date reg_date) {
		this.reg_date = reg_date;
	}
}