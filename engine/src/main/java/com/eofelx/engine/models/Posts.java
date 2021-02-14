package com.eofelx.engine.models;

public class Posts {

    private String id;
    private String date;
    private String title;
    private String content;
    private String excerpt;
    private String author;
    private String featuredMedia;
    private String category;
    private String replies;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getExcerpt() {
        return excerpt;
    }

    public void setExcerpt(String excerpt) {
        this.excerpt = excerpt;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getFeaturedMedia() {
        return featuredMedia;
    }

    public void setFeaturedMedia(String featuredMedia) {
        this.featuredMedia = featuredMedia;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getReplies() {
        return replies;
    }

    public void setReplies(String replies) {
        this.replies = replies;
    }

    public Posts() {

    }

    public Posts(String id, String date, String title, String content, String excerpt, String author, String featuredMedia, String category, String replies) {
        this.id = id;
        this.date = date;
        this.title = title;
        this.content = content;
        this.excerpt = excerpt;
        this.author = author;
        this.featuredMedia = featuredMedia;
        this.category = category;
        this.replies = replies;
    }
}
