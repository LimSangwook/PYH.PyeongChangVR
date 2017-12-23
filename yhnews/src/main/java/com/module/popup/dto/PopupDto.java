package com.module.popup.dto;

import java.util.Date;

import com.common.base.BaseForm;

public class PopupDto extends BaseForm{
	
	private static final long serialVersionUID = -538107565006277019L;

	private String popup_key;			// 팝업고유번호
	private String kind;					// 팝업유형 - 윈도우팝업 : 1, 임베디드팝업 : 2 
	private String title;					// 팝업제목
	private String link_address;		// 링크연결주소
	private String is_new_window;	// 새창열림여부
	private String width_size;			// 팝업가로사이즈
	private String height_size;		// 팝업세로사이즈
	private String top_margin;		// 상단마진
	private String left_margin;		// 외쪽마진
	private String start_show_date;	// 노출시작일시 yyyy-MM-dd
	private String end_show_date;	// 노출종료일시 yyyy-MM-dd
	private String real_file_name;	// 실제파일명
	private String save_file_name;	// 저장파일명
	private String file_path;			// 파일경로
	private String status;				// 상태
	private Date reg_date;			// 등록일시	
	
	private String is_show_yn;		
	
	public String getKind() {
		return kind;
	}
	public void setKind(String kind) {
		this.kind = kind;
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
	public String getPopup_key() {
		return popup_key;
	}
	public void setPopup_key(String popup_key) {
		this.popup_key = popup_key;
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
	public String getWidth_size() {
		return width_size;
	}
	public void setWidth_size(String width_size) {
		this.width_size = width_size;
	}
	public String getHeight_size() {
		return height_size;
	}
	public void setHeight_size(String height_size) {
		this.height_size = height_size;
	}
	public String getTop_margin() {
		return top_margin;
	}
	public void setTop_margin(String top_margin) {
		this.top_margin = top_margin;
	}
	public String getLeft_margin() {
		return left_margin;
	}
	public void setLeft_margin(String left_margin) {
		this.left_margin = left_margin;
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