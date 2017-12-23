package com.module.forum.dto;

import java.io.Serializable;

public class ForumContDto implements Serializable{
	
	private static final long serialVersionUID = -6671598713575229015L;
	
	// 포럼/박람회 번호
	private String forum_key;
	// 개요 및 일정
	private String content1;
	// 포럼 프로그램
	private String content2;
	// 전시장 소개
	private String content3;
	// 문화공연행사
	private String content4;
	
	public String getForum_key() {
		return forum_key;
	}
	public void setForum_key(String forum_key) {
		this.forum_key = forum_key;
	}
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
}