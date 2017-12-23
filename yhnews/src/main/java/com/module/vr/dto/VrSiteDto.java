package com.module.vr.dto;

import java.util.Date;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.common.base.BaseForm;

public class VrSiteDto extends BaseForm {
    public VrSiteDto() {
        this.setPageSize(12);
    }

    private static final long serialVersionUID = 9125783187123636059L;

    private String vr_site_id;
    private String title;
    private String path_icon;
    private String path_image;
    private String status;
    private Date mod_date;

    private MultipartFile image_file;
    private MultipartFile icon_file;

    private List<VrSiteGroupDto> list;

    public String getVr_site_id() {
        return vr_site_id;
    }

    public void setVr_site_id(String vr_site_id) {
        this.vr_site_id = vr_site_id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPath_icon() {
        return path_icon;
    }

    public void setPath_icon(String path_icon) {
        this.path_icon = path_icon;
    }

    public String getPath_image() {
        return path_image;
    }

    public void setPath_image(String path_image) {
        this.path_image = path_image;
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

    public MultipartFile getImage_file() {
        return image_file;
    }

    public void setImage_file(MultipartFile image_file) {
        this.image_file = image_file;
    }

    public MultipartFile getIcon_file() {
        return icon_file;
    }

    public void setIcon_file(MultipartFile icon_file) {
        this.icon_file = icon_file;
    }

    public List<VrSiteGroupDto> getList() {
        return list;
    }

    public void setList(List<VrSiteGroupDto> list) {
        this.list = list;
    }

    public static long getSerialversionuid() {
        return serialVersionUID;
    }

}
