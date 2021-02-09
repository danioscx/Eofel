package com.eofelx.eofel.utils;

public class Query {


    private static Query instance;


    private final String MAIN_URL = "https://eofel33.000webhostapp.com/wp-json/wp/v2/";


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


}
