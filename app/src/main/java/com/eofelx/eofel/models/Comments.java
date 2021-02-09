package com.eofelx.eofel.models;

public class Comments {

    private String id;
    private String authorName;
    private String authorUrl;
    private String date;
    private String content;
    private String avatar;

    //default constructor
    public Comments() {

    }

    public Comments(String id, String authorName, String authorUrl, String date, String content, String avatar) {
        this.id = id;
        this.authorName = authorName;
        this.authorUrl = authorUrl;
        this.date = date;
        this.content = content;
        this.avatar = avatar;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    public String getAuthorUrl() {
        return authorUrl;
    }

    public void setAuthorUrl(String authorUrl) {
        this.authorUrl = authorUrl;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getAvatar() {
        return avatar;
    }
}
