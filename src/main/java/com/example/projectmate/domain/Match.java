package com.example.projectmate.domain;

import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

public class Match {
    private int match_id;
    private int publisher_id;
    private String match_name;
    private String publisher_name;
    private String introduction;
    private String category;
    private String join_method;
    private String appendix_url;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date deadline;

    public int getMatch_id() {
        return match_id;
    }

    public void setMatch_id(int match_id) {
        this.match_id = match_id;
    }

    public String getMatch_name() {
        return match_name;
    }

    public void setMatch_name(String match_name) {
        this.match_name = match_name;
    }

    public int getPublisher_id() {
        return publisher_id;
    }

    public void setPublisher_id(int publisher_id) {
        this.publisher_id = publisher_id;
    }

    public String getPublisher_name() {
        return publisher_name;
    }

    public void setPublisher_name(String publisher_name) {
        this.publisher_name = publisher_name;
    }

    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getJoin_method() {
        return join_method;
    }

    public void setJoin_method(String join_method) {
        this.join_method = join_method;
    }

    public String getAppendix_url() {
        return appendix_url;
    }

    public void setAppendix_url(String appendix_url) {
        this.appendix_url = appendix_url;
    }

    public Date getDeadline() {
        return deadline;
    }

    public void setDeadline(Date deadline) {
        this.deadline = deadline;
    }

    @Override
    public String toString() {
        return "Match{" +
                "match_id=" + match_id +
                ", match_name='" + match_name + '\'' +
                ", publisher_id=" + publisher_id +
                ", publisher_name='" + publisher_name + '\'' +
                ", introduction='" + introduction + '\'' +
                ", category='" + category + '\'' +
                ", join_method='" + join_method + '\'' +
                ", appendix_url='" + appendix_url + '\'' +
                ", deadline=" + deadline +
                '}';
    }
}
