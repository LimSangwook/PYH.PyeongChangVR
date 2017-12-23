package com.module.forum.dto;

import java.util.Date;

import com.common.base.BaseForm;

public class SpeakerDto extends BaseForm{
	
	private static final long serialVersionUID = -397981400437291686L;
	
	// 연사일련번호
	private String speaker_key;
	// 포럼일련번호
	private String forum_key;
	// 분과구분코드
	private String kind;
	private String kind_name;
	// 분과추가명
	private String speaker_kind_detail;
	// 이름
	private String name;
	// 국가
	private String country;
	// 소속
	private String belong;
	// 주제
	private String subject;
	// 내용
	private String content;
	// 프로필사진
	private String profile_photo;
	// 분과내 순서
	private String order_level;
	// 상태
	private String status;
	// 등록일시
	private Date reg_date;	
	
	
	public String getKind_name() {
		return kind_name;
	}
	public void setKind_name(String kind_name) {
		this.kind_name = kind_name;
	}
	public String getOrder_level() {
		return order_level;
	}
	public void setOrder_level(String order_level) {
		this.order_level = order_level;
	}
	public String getSpeaker_key() {
		return speaker_key;
	}
	public void setSpeaker_key(String speaker_key) {
		this.speaker_key = speaker_key;
	}
	public String getForum_key() {
		return forum_key;
	}
	public void setForum_key(String forum_key) {
		this.forum_key = forum_key;
	}
	public String getKind() {
		return kind;
	}
	public void setKind(String kind) {
		this.kind = kind;
	}
	public String getSpeaker_kind_detail() {
		return speaker_kind_detail;
	}
	public void setSpeaker_kind_detail(String speaker_kind_detail) {
		this.speaker_kind_detail = speaker_kind_detail;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public String getBelong() {
		return belong;
	}
	public void setBelong(String belong) {
		this.belong = belong;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getProfile_photo() {
		return profile_photo;
	}
	public void setProfile_photo(String profile_photo) {
		this.profile_photo = profile_photo;
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