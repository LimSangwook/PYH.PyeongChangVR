package com.module.vr.dto;

import java.util.Date;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.common.base.BaseForm;

public class VrStyleGalleryDto extends BaseForm {

	private static final long serialVersionUID = 8205629521511661157L;

	private String vr_style_gallery_id;
	private String title;
	private String vr_site_id;
	private String vr_site_title;
	private String mod_only_date;
	private String status;
	private Date mod_date;
	private List<MultipartFile> gallery_file;
	private List<VrStyleGalleryImageDto> imgList;

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

	public String getVr_style_gallery_id() {
		return vr_style_gallery_id;
	}

	public void setVr_style_gallery_id(String vr_style_gallery_id) {
		this.vr_style_gallery_id = vr_style_gallery_id;
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

	public List<MultipartFile> getGallery_file() {
		return gallery_file;
	}

	public void setGallery_file(List<MultipartFile> gallery_file) {
		this.gallery_file = gallery_file;
	}

	public List<VrStyleGalleryImageDto> getImgList() {
		return imgList;
	}

	public void setImgList(List<VrStyleGalleryImageDto> imgList) {
		this.imgList = imgList;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
