package com.module.vr.dto;

import java.util.Date;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.common.base.BaseForm;

public class VrSiteContentDto extends BaseForm {
    private static final long serialVersionUID = 9125773593123636059L;

    private String vr_site_content_id;
    private String vr_site_id;
    private String name;
    private Integer expose_order;
    private Integer vr_site_group_id;
    private String vr_style_music_id;
    private String path_panorama_image;
    private String path_panorama_image_ui;
    private String panorama_image_name;
    private String status;
    private Date mod_date;

    public String getPath_panorama_image_ui() {
        return path_panorama_image_ui;
    }

    public void setPath_panorama_image_ui(String path_panorama_image_ui) {
        this.path_panorama_image_ui = path_panorama_image_ui;
    }

    private List<VrSiteContentSpotDto> spotList;

    private MultipartFile panorama_file;

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

    public String getVr_site_content_id() {
        return vr_site_content_id;
    }

    public void setVr_site_content_id(String vr_site_content_id) {
        this.vr_site_content_id = vr_site_content_id;
    }

    public String getVr_site_id() {
        return vr_site_id;
    }

    public void setVr_site_id(String vr_site_id) {
        this.vr_site_id = vr_site_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getExpose_order() {
        return expose_order;
    }

    public void setExpose_order(Integer expose_order) {
        this.expose_order = expose_order;
    }

    public Integer getVr_site_group_id() {
        return vr_site_group_id;
    }

    public void setVr_site_group_id(Integer vr_site_group_id) {
        this.vr_site_group_id = vr_site_group_id;
    }

    public String getVr_style_music_id() {
        return vr_style_music_id;
    }

    public void setVr_style_music_id(String vr_style_music_id) {
        this.vr_style_music_id = vr_style_music_id;
    }

    public String getPath_panorama_image() {
        return path_panorama_image;
    }

    public void setPath_panorama_image(String path_panorama_image) {
        this.path_panorama_image = path_panorama_image;
    }

    public String getPanorama_image_name() {
        return panorama_image_name;
    }

    public void setPanorama_image_name(String panorama_image_name) {
        this.panorama_image_name = panorama_image_name;
    }

    public List<VrSiteContentSpotDto> getSpotList() {
        return spotList;
    }

    public void setSpotList(List<VrSiteContentSpotDto> spotList) {
        this.spotList = spotList;
    }

    public MultipartFile getPanorama_file() {
        return panorama_file;
    }

    public void setPanorama_file(MultipartFile panorama_file) {
        this.panorama_file = panorama_file;
    }

    public static long getSerialversionuid() {
        return serialVersionUID;
    }
}
