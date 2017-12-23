package com.module.vr.dto;

import com.common.base.BaseForm;

public class VrSiteGroupDto extends BaseForm {
    private static final long serialVersionUID = 9125712312323636059L;

    private String vr_site_group_id;
    private String vr_site_id;
    private String name;
    private String description;
    private String crud_type;

    public String getVr_site_group_id() {
        return vr_site_group_id;
    }

    public void setVr_site_group_id(String vr_site_group_id) {
        this.vr_site_group_id = vr_site_group_id;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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
