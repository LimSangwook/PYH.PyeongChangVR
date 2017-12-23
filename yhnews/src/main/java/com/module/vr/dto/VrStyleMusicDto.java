package com.module.vr.dto;

import java.util.Date;

import org.springframework.web.multipart.MultipartFile;

import com.common.base.BaseForm;

public class VrStyleMusicDto extends BaseForm {

    private static final long serialVersionUID = -3744093058251952276L;

    private String vr_style_music_id;
    private String title;
    private String path_file;
    private Integer volume;
    private String auto_play;
    private String repeat_play;
    private Double hfov;
    private Double roll;
    private Double pitch;
    private Double yaw;
    private String status;
    private String mod_only_date;
    private Date mod_date;

    private MultipartFile music_file;

    public String getMod_only_date() {
        return mod_only_date;
    }

    public void setMod_only_date(String mod_only_date) {
        this.mod_only_date = mod_only_date;
    }

    public String getVr_style_music_id() {
        return vr_style_music_id;
    }

    public void setVr_style_music_id(String vr_style_music_id) {
        this.vr_style_music_id = vr_style_music_id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPath_file() {
        return path_file;
    }

    public void setPath_file(String path_file) {
        this.path_file = path_file;
    }

    public Integer getVolume() {
        return volume;
    }

    public void setVolume(Integer volume) {
        this.volume = volume;
    }

    public String getAuto_play() {
        return auto_play;
    }

    public void setAuto_play(String auto_play) {
        this.auto_play = auto_play;
    }

    public String getRepeat_play() {
        return repeat_play;
    }

    public void setRepeat_play(String repeat_play) {
        this.repeat_play = repeat_play;
    }

    public Double getHfov() {
        return hfov;
    }

    public void setHfov(Double hfov) {
        this.hfov = hfov;
    }

    public Double getRoll() {
        return roll;
    }

    public void setRoll(Double roll) {
        this.roll = roll;
    }

    public Double getPitch() {
        return pitch;
    }

    public void setPitch(Double pitch) {
        this.pitch = pitch;
    }

    public Double getYaw() {
        return yaw;
    }

    public void setYaw(Double yaw) {
        this.yaw = yaw;
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

    public MultipartFile getMusic_file() {
        return music_file;
    }

    public void setMusic_file(MultipartFile music_file) {
        this.music_file = music_file;
    }

    public static long getSerialversionuid() {
        return serialVersionUID;
    }

}
