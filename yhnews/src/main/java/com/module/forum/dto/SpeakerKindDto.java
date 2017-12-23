package com.module.forum.dto;

import java.io.Serializable;

public class SpeakerKindDto implements Serializable{
		
	private static final long serialVersionUID = -7346092033342442939L;
	
	// 분과구분코드
	private String kind;
	// 분과구분명
	private String kind_name;
	
	public String getKind() {
		return kind;
	}
	public void setKind(String kind) {
		this.kind = kind;
	}
	public String getKind_name() {
		return kind_name;
	}
	public void setKind_name(String kind_name) {
		this.kind_name = kind_name;
	}
}