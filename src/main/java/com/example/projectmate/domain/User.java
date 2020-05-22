package com.example.projectmate.domain;

import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

public class User {
    private int user_id;
    private String student_number;
    private String is_identify;
    private String username;
    private String role;
    private String major;
    private String college;
    private String gender;
    private String wechat_openid;
    private String introduction;
    private String avatarUrl;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date register_time;

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getStudent_number() {
        return student_number;
    }

    public void setStudent_number(String student_number) {
        this.student_number = student_number;
    }

    public String getIs_identify() {
        return is_identify;
    }

    public void setIs_identify(String is_identify) {
        this.is_identify = is_identify;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    public String getCollege() {
        return college;
    }

    public void setCollege(String college) {
        this.college = college;
    }

    public String getWechat_openid() {
        return wechat_openid;
    }

    public void setWechat_openid(String wechat_openid) {
        this.wechat_openid = wechat_openid;
    }

    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }

    public Date getRegister_time() {
        return register_time;
    }

    public void setRegister_time(Date register_time) {
        this.register_time = register_time;
    }

    @Override
    public String toString() {
        return "User{" +
                "user_id=" + user_id +
                ", student_number=" + student_number +
                ", is_identify=" + is_identify +
                ", username='" + username + '\'' +
                ", gender='" + gender + '\'' +
                ", role='" + role + '\'' +
                ", major='" + major + '\'' +
                ", college='" + college + '\'' +
                ", avatarUrl='" + avatarUrl + '\'' +
                ", wechat_openid='" + wechat_openid + '\'' +
                ", introduction='" + introduction + '\'' +
                ", register_time=" + register_time +
                '}';
    }
}
