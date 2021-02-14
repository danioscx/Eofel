package com.eofelx.engine.utils;

import androidx.annotation.NonNull;

import com.android.volley.Request;
import com.android.volley.toolbox.JsonArrayRequest;
import com.eofelx.engine.RestApi;
import com.eofelx.engine.models.Posts;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class RequestPost {

    /*
     *  interface ini sudah memiliki model sendiri tinggal apabila di implementasi
     *  dia bisa langsung berkomikasi dengan adapter seperti RecyclerView atau ListView
     *
     */
    public interface OnRequestResponseModel {
        void onResponse(List<Posts> posts);
    }

    /*
     * Interface ini tidak memiliki default model sebelum melakukan komikasi dengan adapter user harus membuat model
     * sendiri
     */

    public interface OnRequestResponse {
        void onResponse(JSONArray response);
    }

    public RequestPost() {

    }


    public void getPosts(OnRequestResponseModel requestResponse) {
        List<Posts> posts = new ArrayList<>();
        String url = new RestApi.Create("https://eofel33.000webhostapp.com").get().posts().toString();
        JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET,
                url, null, response -> {
                    Posts mPost = new Posts();
                    try {

                        for (int x = 0; x < response.length(); x++) {
                            JSONObject object = response.getJSONObject(x);

                            mPost.setId(object.getString("id"));
                            mPost.setAuthor(object.getString("link"));
                            mPost.setDate(object.getString("date"));
                            mPost.setFeaturedMedia(object.getString("featured_media"));

                            JSONObject title = object.getJSONObject("title");
                            for (int i = 0; i < title.length(); i++) {
                                mPost.setTitle(title.getString("rendered"));
                            }

                            JSONObject excerpt = object.getJSONObject("excerpt");
                            for (int i = 0; i < excerpt.length(); i++) {
                                mPost.setExcerpt(excerpt.getString("rendered"));
                            }

                            JSONObject cont = object.getJSONObject("content");
                            for (int i = 0; i < cont.length(); i++) {
                                mPost.setContent(cont.getString("rendered"));
                            }


                            mPost.setCategory(object.getString("categories"));

                            mPost.setReplies(new RestApi.Create("https://eofel33.000webhostapp.com").get().replies().toString() + mPost.getId());
                            System.out.println(new RestApi.Create("https://eofel33.000webhostapp.com").get().replies().toString() + mPost.getId());

                            posts.add(new Posts(
                                    mPost.getId(),
                                    mPost.getDate(),
                                    mPost.getTitle(),
                                    mPost.getAuthor(),
                                    mPost.getFeaturedMedia(),
                                    mPost.getContent(),
                                    mPost.getExcerpt(),
                                    mPost.getCategory(),
                                    mPost.getReplies()
                            ));
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                    requestResponse.onResponse(posts);


                }, error -> System.out.println(error.getMessage()));
        RestApi.getInstance().addRequestQueue(request);
    }

    public void getPosts(OnRequestResponse requestResponse) {
        List<Posts> posts = new ArrayList<>();
        String url = new RestApi.Create("https://eofel33.000webhostapp.com").get().posts().toString();
        JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET,
                url, null, requestResponse::onResponse,
                error -> System.out.println(error.getMessage())
        );

        RestApi.getInstance().addRequestQueue(request);
    }

}
