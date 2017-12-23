package com.module.html.dto;

import java.util.Date;

import com.common.base.BaseForm;
import com.common.util.CommonUtils;

public class HtmlDto extends BaseForm{
	
	private static final long serialVersionUID = -6345792481796193886L;

	private String page_seq;
	private String title;
	private String html;
	private String status;
	private Date reg_date;
	
	public String getTitleCrop(){
		return CommonUtils.crop(title, 100, "...", "UTF-8");
	}
	public String getPage_seq() {
		return page_seq;
	}
	public void setPage_seq(String page_seq) {
		this.page_seq = page_seq;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getHtml() {
		return html;
	}
	public void setHtml(String html) {
		this.html = html;
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