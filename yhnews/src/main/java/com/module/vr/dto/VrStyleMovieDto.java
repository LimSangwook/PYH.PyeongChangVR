package com.module.vr.dto;

import java.util.Date;

import com.common.base.BaseForm;

public class VrStyleMovieDto extends BaseForm {
	private static final long serialVersionUID = -8699098936496787125L;

	private String vr_style_movie_id;
	private String title;
	private String vr_site_id;
	private String vr_site_title;
	private String url;
	private String status;
	private String mod_only_date;
	private Date mod_date;

	public String getMod_only_date() {
		return mod_only_date;
	}

	public void setMod_only_date(String mod_only_date) {
		this.mod_only_date = mod_only_date;
	}

	public String getVr_site_title() {
		return vr_site_title;
	}

	public void setVr_site_title(String vr_site_title) {
		this.vr_site_title = vr_site_title;
	}

	public String getVr_style_movie_id() {
		return vr_style_movie_id;
	}

	public void setVr_style_movie_id(String vr_style_movie_id) {
		this.vr_style_movie_id = vr_style_movie_id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getVr_site_id() {
		return vr_site_id;
	}

	public void setVr_site_id(String vr_site_id) {
		this.vr_site_id = vr_site_id;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Date getMod_date() {
		return mod_date;
	}

	public void setMod_date(Date mod_date) {
		this.mod_date = mod_date;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}
