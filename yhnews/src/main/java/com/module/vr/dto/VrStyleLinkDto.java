package com.module.vr.dto;

import java.util.Date;

import org.springframework.web.multipart.MultipartFile;

import com.common.base.BaseForm;

public class VrStyleLinkDto extends BaseForm {

    private static final long serialVersionUID = 585665124383530776L;

    private String vr_style_link_id;
    private String title;
    private MultipartFile link_file;
    private String path_image;
    private Double over_size;
    private Double out_size;
    private String status;
    private String mod_only_date;
    private Date mod_date;

    public String getVr_style_link_id() {
        return vr_style_link_id;
    }

    public void setVr_style_link_id(String vr_style_link_id) {
        this.vr_style_link_id = vr_style_link_id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public MultipartFile getLink_file() {
        return link_file;
    }

    public void setLink_file(MultipartFile link_file) {
        this.link_file = link_file;
    }

    public String getPath_image() {
        return path_image;
    }

    public void setPath_image(String path_image) {
        this.path_image = path_image;
    }

    public Double getOver_size() {
        return over_size;
    }

    public void setOver_size(Double over_size) {
        this.over_size = over_size;
    }

    public Double getOut_size() {
        return out_size;
    }

    public void setOut_size(Double out_size) {
        this.out_size = out_size;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMod_only_date() {
        return mod_only_date;
    }

    public void setMod_only_date(String mod_only_date) {
        this.mod_only_date = mod_only_date;
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
