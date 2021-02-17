package com.eofelx.engine.models;

public class Comments {

    private String id;
    private String post;
    private String parent;
    private String authorName;
    private String date;
    private String content;
    private String authorAvatar;
    private String email;

    public Comments() {

    }

    public Comments withId(String id) {
        this.id = id;
        return this;
    }

    public Comments withPost(String post) {
        this.post = post;
        return this;
    }

    public Comments withParent(String parent) {
        this.parent = parent;
        return this;
    }

    public Comments withAuthorName(String authorName) {
        this.authorName = authorName;
        return this;
    }

    public Comments withDate(String date) {
        this.date = date;
        return this;
    }

    public Comments withContent(String content) {
        this.content = content;
        return this;
    }

    public Comments withAuthorAvatar(String authorAvatar) {
        this.authorAvatar = authorAvatar;
        return this;
    }

    public Comments withEmail(String email) {
        this.email = email;
        return this;
    }

    public Comments build() {
        Comments comments = new Comments();
        comments.authorName = this.authorName;
        comments.content = this.content;
        comments.post = this.post;
        comments.email = this.email;
        comments.authorAvatar = this.authorAvatar;
        comments.id = this.id;
        comments.parent = this.parent;
        comments.date = this.date;
        return comments;
    }

    public String getId() {
        return id;
    }

    public String getPost() {
        return post;
    }

    public String getParent() {
        return parent;
    }

    public String getAuthorName() {
        return authorName;
    }

    public String getDate() {
        return date;
    }

    public String getContent() {
        return content;
    }

    public String getAuthorAvatar() {
        return authorAvatar;
    }

    public String getEmail() {
        return email;
    }
}
