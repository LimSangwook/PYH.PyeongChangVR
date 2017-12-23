package com.module.museum.dto;

import java.io.Serializable;

public class MuseumMenuContDto implements Serializable{
		
	private static final long serialVersionUID = -447908040701742451L;
	
	// 박물관번호
	private String museum_no;
	// 박물관메뉴코드
	private String menu_code;
	// 게시판아이디
	private String board_id;
	// 내용
	private String content;
	
	// 메뉴명
	private String menu_name;
	
	
	public String getMenu_name() {
		return menu_name;
	}
	public void setMenu_name(String menu_name) {
		this.menu_name = menu_name;
	}
	public String getMuseum_no() {
		return museum_no;
	}
	public void setMuseum_no(String museum_no) {
		this.museum_no = museum_no;
	}
	public String getMenu_code() {
		return menu_code;
	}
	public void setMenu_code(String menu_code) {
		this.menu_code = menu_code;
	}
	public String getBoard_id() {
		return board_id;
	}
	public void setBoard_id(String board_id) {
		this.board_id = board_id;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
}
