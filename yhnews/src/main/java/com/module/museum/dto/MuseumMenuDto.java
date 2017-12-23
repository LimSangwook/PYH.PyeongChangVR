package com.module.museum.dto;

import java.io.Serializable;

public class MuseumMenuDto implements Serializable{
	
	private static final long serialVersionUID = 4905487589304870941L;
	
	// 박물관메뉴코드
	private String menu_code;
	// 박물관메뉴명
	private String menu_name;
	// 메뉴유형 - 일반Html : html, 공지사항 : boardNotice, 행사일정 : boardSchedule
	private String kind;
	// 정렬순서
	private int order_level;
	
	public String getMenu_code() {
		return menu_code;
	}
	public void setMenu_code(String menu_code) {
		this.menu_code = menu_code;
	}
	public String getMenu_name() {
		return menu_name;
	}
	public void setMenu_name(String menu_name) {
		this.menu_name = menu_name;
	}
	public String getKind() {
		return kind;
	}
	public void setKind(String kind) {
		this.kind = kind;
	}
	public int getOrder_level() {
		return order_level;
	}
	public void setOrder_level(int order_level) {
		this.order_level = order_level;
	}
}
