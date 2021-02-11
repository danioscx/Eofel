package com.eofelx.eofel.utils;

public class Query {


    private static Query instance;


    private final String MAIN_URL = "https://eofel33.000webhostapp.com/wp-json/wp/v2/";

    private String postId;
    private String author;
    private String authorName;
    private String authorEmail;
    private String content;
    private String parent;



    public Query() {
    }

    public static Query get() {
        if (instance == null) {
            instance = new Query();
        }
        return instance;
    }

    public String getAllPosts(int query_page) {
        return queryPost(query_page);
    }

    protected String queryPost(int query_page) {
        String POSTS = "posts/";
        String PAGE = "?per_page=";
        return MAIN_URL + POSTS + PAGE + query_page;
    }

    public String getMedia(String id) {
        return queryMedia(id);
    }

    protected String queryMedia(String id) {
        String MEDIA = "media/";
        return MAIN_URL + MEDIA + id;
    }

    public String getAllCategories(String id) {
        return queryCategory(id);
    }

    protected String queryCategory(String id) {
        String POSTS = "posts/";
        String PAGE = "?per_page=5";
        String CATEGORY = "&categories=";
        return MAIN_URL + POSTS + PAGE + CATEGORY +id;
    }

    public String getCommentsUrls(String id) {
        String REPLIES = "comments?post=";
        return MAIN_URL + REPLIES + id;
    }

    public String getQuerySearch(String query) {
        String POSTS = "posts/";
        String SEARCH = "?search=";
        return MAIN_URL + POSTS + SEARCH + query;
    }

    public String putComments() {
        String Comments = "comments";
        return MAIN_URL + Comments;
    }

    public static class Comments {

        private String postId;
        private String author;
        private String authorName;
        private String authorEmail;
        private String content;
        private String parent;

        public Comments postId(String id) {
            this.postId = id;
            return this;
        }

        public Comments author(String author) {
            this.author = author;
            return this;
        }

        public Comments authorName(String authorName) {
            this.authorName = authorName;
            return this;
        }

        public Comments authorEmail(String authorEmail) {
            this.authorEmail = authorEmail;
            return this;
        }

        public Comments content(String content) {
            this.content = content;
            return this;
        }

        public Comments parent(String parent) {
            this.parent = parent;
            return this;
        }

        public Query build() {
            Query query = new Query();
            query.postId = this.postId;
            query.author = this.author;
            query.authorName = this.authorName;
            query.authorEmail = this.authorEmail;
            query.content = this.content;
            query.parent = parent;
            return query;
        }
    }

    @Override
    public String toString() {
        return MAIN_URL  + "comments?" +
                "author_name=" + authorName +
                "&author_email=" + authorEmail +
                "&content=" + content +
                "&parent=" + parent +
                "&post=" + postId ;
    }
}
