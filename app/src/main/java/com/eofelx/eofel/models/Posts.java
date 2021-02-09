package com.eofelx.eofel.models;

public class Posts {

    private String id;
    private String url;
    private String date;
    private String title;
    private String mediaFeatured;
    private String content;
    private String excerpt;
    private String category;
    private String replies;

    public Posts(String id, String url, String date, String title, String mediaFeatured, String content, String excerpt, String category, String replies) {
        this.id = id;
        this.url = url;
        this.date = date;
        this.title = title;
        this.mediaFeatured = mediaFeatured;
        this.content = content;
        this.excerpt = excerpt;
        this.category = category;
        this.replies = replies;
    }

    public Posts() {}

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
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

    public String getMediaFeatured() {
        return mediaFeatured;
    }

    public void setMediaFeatured(String media_featured) {
        this.mediaFeatured = media_featured;
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

    public void setCategory(String category) {
        this.category = category;
    }

    public String getCategory() {
        return category;
    }

    public void setReplies(String replies) {
        this.replies = replies;
    }

    public String getReplies() {
        return replies;
    }

    public static class Builder {

        private String id;
        private String url;
        private String date;
        private String title;
        private String mediaFeatured;
        private String content;
        private String excerpt;


        public Builder setId(String id) {
            this.id = id;
            return this;
        }

        public Builder setUrl(String url) {
            this.url = url;
            return this;
        }

        public Builder setTitle(String title) {
            this.title = title;
            return this;
        }

        public Builder setContent(String content) {
            this.content = content;
            return this;
        }

        public Builder setExcerpt(String excerpt) {
            this.excerpt = excerpt;
            return this;
        }

        public Posts build() {
            Posts content = new Posts();
            content.id = this.id;
            content.url = this.url;
            content.title = this.title;
            content.content = this.content;
            content.excerpt = this.excerpt;
            return content;
        }
    }
}
