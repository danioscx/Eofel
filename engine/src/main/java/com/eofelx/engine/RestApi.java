package com.eofelx.engine;

import android.annotation.SuppressLint;
import android.content.Context;

import androidx.annotation.NonNull;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

public class RestApi {


    @SuppressLint("StaticFieldLeak")
    private static RestApi instance;

    @SuppressLint("StaticFieldLeak")
    private static Context context;


    private RequestQueue requestQueue;

    public RestApi(Context context) {
        RestApi.context = context;
    }

    public static synchronized RestApi getInstance() {
        if (instance == null) {
            instance = new RestApi(context);
        }
        return instance;
    }

    public RequestQueue getRequestQueue() {
        if (requestQueue == null) {
            requestQueue = Volley.newRequestQueue(context.getApplicationContext());
        }
        return requestQueue;
    }

    public <T> void addRequestQueue(Request<T> request) {
        getRequestQueue().add(request);
    }

    public static class Create {

        private final String siteName;
        private String restApi;
        private String endPoint;
        private String replies;

        public Create(String siteName) {
            this.siteName = siteName;
        }

        public Create get() {
            restApi = "/wp-json/wp/v2";
            return this;
        }

        /**
         *
         * @param perPage Maximum number of items to be returned in result set
         * @return Create.class
         */
        public Create posts(String perPage) {
            endPoint = "/posts/?per_page=" + perPage;
            return this;
        }

        /**
         *
         * @return items to be returned in result set: default 10 please use post(String perPage)
         */
        public Create posts() {
            endPoint = "/posts";
            return this;
        }

        public Create replies() {
            endPoint = "/comments?post=";
            return this;
        }

        @NonNull
        @Override
        public String toString() {
            if (restApi == null) {
                return "<Error request builder> ";
            }
           return siteName + restApi + endPoint;
        }
    }

}
