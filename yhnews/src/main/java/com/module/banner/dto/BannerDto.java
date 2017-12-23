package com.module.banner.dto;

import java.util.Date;

import com.common.base.BaseForm;

public class BannerDto extends BaseForm{
	
	private static final long serialVersionUID = -538107565006277019L;

	private String banner_key;		// 고유번호
	private String title;					// 제목
	private String content;				// 내용
	private String area_code;			// 배너존코드(위치)
	private String type1;				// 구분값1
	private String link_address;		// 링크연결주소
	private String is_new_window;	// 새창열림여부
	private String order_level;			// 노출순서	
	private String start_show_date;	// 노출시작일시 yyyy-MM-dd
	private String end_show_date;	// 노출종료일시 yyyy-MM-dd
	private String real_file_name;	// 실제파일명
	private String save_file_name;	// 저장파일명
	private String file_path;			// 파일경로
	private String status;				// 상태
	private Date reg_date;				// 등록일시	
	
	private String is_show_yn;	
	
		
	public String getType1() {
		return type1;
	}
	public void setType1(String type1) {
		this.type1 = type1;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getArea_code() {
		return area_code;
	}
	public void setArea_code(String area_code) {
		this.area_code = area_code;
	}
	public String getBanner_key() {
		return banner_key;
	}
	public void setBanner_key(String banner_key) {
		this.banner_key = banner_key;
	}
	public String getOrder_level() {
		return order_level;
	}
	public void setOrder_level(String order_level) {
		this.order_level = order_level;
	}
	public String getIs_show_yn() {
		return is_show_yn;
	}
	public void setIs_show_yn(String is_show_yn) {
		this.is_show_yn = is_show_yn;
	}
	public String getReal_file_name() {
		return real_file_name;
	}
	public void setReal_file_name(String real_file_name) {
		this.real_file_name = real_file_name;
	}
	public String getSave_file_name() {
		return save_file_name;
	}
	public void setSave_file_name(String save_file_name) {
		this.save_file_name = save_file_name;
	}
	public String getFile_path() {
		return file_path;
	}
	public void setFile_path(String file_path) {
		this.file_path = file_path;
	}	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getLink_address() {
		return link_address;
	}
	public void setLink_address(String link_address) {
		this.link_address = link_address;
	}
	public String getIs_new_window() {
		return is_new_window;
	}
	public void setIs_new_window(String is_new_window) {
		this.is_new_window = is_new_window;
	}	
	public String getStart_show_date() {
		return start_show_date;
	}
	public void setStart_show_date(String start_show_date) {
		this.start_show_date = start_show_date;
	}
	public String getEnd_show_date() {
		return end_show_date;
	}
	public void setEnd_show_date(String end_show_date) {
		this.end_show_date = end_show_date;
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