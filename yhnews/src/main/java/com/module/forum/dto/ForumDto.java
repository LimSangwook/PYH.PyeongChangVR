package com.module.forum.dto;

import java.util.Date;

import com.common.base.BaseForm;

public class ForumDto extends BaseForm{
	
	private static final long serialVersionUID = 8698486905931897878L;
	
	// 포럼일련번호
	private String forum_key;
	// 행사명
	private String event_name;
	// 회차
	private String turn_num;
	// 행사년도
	private String event_year;
	// 기간
	private String period;
	// 장소
	private String location;
	// 주최
	private String host_name;
	// 주관
	private String organize_name;
	// 대표이미지
	private String master_image;
	// 정렬순서
	private String order_level;
	// 상태 - 진행중 : 1, 종료(지난포럼/박람회) : 2, 비공개 : 3, 삭제 : 4 
	private String status;
	// 게시글 조회 시작일
	private String board_search_start_day;
	// 게시글 조회 종료일
	private String board_search_end_day;
	// 등록일시
	private Date reg_date;
	
	/**
	 * 포럼상세내용
	 * */
	// 개요 및 일정
	private String content1;
	// 포럼 프로그램
	private String content2;
	// 전시장 소개
	private String content3;
	// 문화공연행사
	private String content4;	
	
	public String getContent1() {
		return content1;
	}
	public void setContent1(String content1) {
		this.content1 = content1;
	}
	public String getContent2() {
		return content2;
	}
	public void setContent2(String content2) {
		this.content2 = content2;
	}
	public String getContent3() {
		return content3;
	}
	public void setContent3(String content3) {
		this.content3 = content3;
	}
	public String getContent4() {
		return content4;
	}
	public void setContent4(String content4) {
		this.content4 = content4;
	}
	public String getForum_key() {
		return forum_key;
	}
	public void setForum_key(String forum_key) {
		this.forum_key = forum_key;
	}
	public String getEvent_name() {
		return event_name;
	}
	public void setEvent_name(String event_name) {
		this.event_name = event_name;
	}
	public String getTurn_num() {
		return turn_num;
	}
	public void setTurn_num(String turn_num) {
		this.turn_num = turn_num;
	}
	public String getEvent_year() {
		return event_year;
	}
	public void setEvent_year(String event_year) {
		this.event_year = event_year;
	}
	public String getPeriod() {
		return period;
	}
	public void setPeriod(String period) {
		this.period = period;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public String getHost_name() {
		return host_name;
	}
	public void setHost_name(String host_name) {
		this.host_name = host_name;
	}
	public String getOrganize_name() {
		return organize_name;
	}
	public void setOrganize_name(String organize_name) {
		this.organize_name = organize_name;
	}
	public String getMaster_image() {
		return master_image;
	}
	public void setMaster_image(String master_image) {
		this.master_image = master_image;
	}
	public String getOrder_level() {
		return order_level;
	}
	public void setOrder_level(String order_level) {
		this.order_level = order_level;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getBoard_search_start_day() {
		return board_search_start_day;
	}
	public void setBoard_search_start_day(String board_search_start_day) {
		this.board_search_start_day = board_search_start_day;
	}
	public String getBoard_search_end_day() {
		return board_search_end_day;
	}
	public void setBoard_search_end_day(String board_search_end_day) {
		this.board_search_end_day = board_search_end_day;
	}
	public Date getReg_date() {
		return reg_date;
	}
	public void setReg_date(Date reg_date) {
		this.reg_date = reg_date;
	}
}