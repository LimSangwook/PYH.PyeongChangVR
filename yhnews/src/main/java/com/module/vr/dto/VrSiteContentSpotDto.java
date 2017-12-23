package com.module.vr.dto;

import com.common.base.BaseForm;

public class VrSiteContentSpotDto extends BaseForm {
    private static final long serialVersionUID = 9125773111123636059L;

    private String vr_site_content_spot_id;
    private String vr_site_content_id;
    private String name;
    private String vr_style_type;
    private String vr_style_id;
    private int pos_x;
    private int pos_y;
    private String link_content_id;
    private String crud_type;

    public String getVr_site_content_spot_id() {
        return vr_site_content_spot_id;
    }

    public void setVr_site_content_spot_id(String vr_site_content_spot_id) {
        this.vr_site_content_spot_id = vr_site_content_spot_id;
    }

    public String getVr_site_content_id() {
        return vr_site_content_id;
    }

    public void setVr_site_content_id(String vr_site_content_id) {
        this.vr_site_content_id = vr_site_content_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getVr_style_type() {
        return vr_style_type;
    }

    public void setVr_style_type(String vr_style_type) {
        this.vr_style_type = vr_style_type;
    }

    public String getVr_style_id() {
        return vr_style_id;
    }

    public void setVr_style_id(String vr_style_id) {
        this.vr_style_id = vr_style_id;
    }

    public int getPos_x() {
        return pos_x;
    }

    public void setPos_x(int pos_x) {
        this.pos_x = pos_x;
    }

    public int getPos_y() {
        return pos_y;
    }

    public void setPos_y(int pos_y) {
        this.pos_y = pos_y;
    }

    public String getLink_content_id() {
        return link_content_id;
    }

    public void setLink_content_id(String link_content_id) {
        this.link_content_id = link_content_id;
    }

    public String getCrud_type() {
        return crud_type;
    }

    public void setCrud_type(String crud_type) {
        this.crud_type = crud_type;
    }

    public static long getSerialversionuid() {
        return serialVersionUID;
    }

}
