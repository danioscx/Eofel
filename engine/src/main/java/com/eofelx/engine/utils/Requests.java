package com.eofelx.engine.utils;



import androidx.annotation.NonNull;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.eofelx.engine.RestApi;
import com.eofelx.engine.models.Comments;
import com.eofelx.engine.models.Posts;
import com.eofelx.engine.models.Users;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Requests {

    private static Requests instance;
    private static String siteName;

    //Default constructor
    public Requests(String siteName) {
        Requests.siteName = siteName;
    }

    public static Requests getInstance() {
        if (null == instance)
            instance = new Requests(siteName);
        return instance;
    }

    /*
     *  interface ini sudah memiliki model sendiri tinggal apabila di implementasi
     *  dia bisa langsung berkomikasi dengan adapter seperti RecyclerView atau ListView
     *
     */
    public interface OnRequestPostResponse {
        void onResponse(List<Posts> posts);
    }

    public interface OnPutRequestResponse {
        void onResponse(JSONObject object);
    }

    /*
     * Interface ini tidak memiliki default model sebelum melakukan komikasi dengan adapter user harus membuat model
     * sendiri
     */

    public interface OnRequestResponse {
        void onResponse(JSONArray response);
    }

    public interface OnResponseComment {
        void onResponse(JSONObject object);
        void onError(String error);
    }

    public void setOnRequestPostListener(OnRequestPostResponse requestResponse) {
        List<Posts> posts = new ArrayList<>();
        String url = new RestApi.Create(siteName).get().posts().toString();
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

                            mPost.setReplies(new RestApi.Create(siteName).get().replies().toString() + mPost.getId());

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

    public void setOnRequestPostListener(OnRequestResponse requestResponse) {
        String url = new RestApi.Create(siteName).get().posts().toString();
        JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET,
                url, null, requestResponse::onResponse,
                error -> System.out.println(error.getMessage())
        );

        RestApi.getInstance().addRequestQueue(request);
    }

    public void setOnRequestCommentListener(OnRequestResponse requestResponse) {
        String url = new RestApi.Create(siteName).get().replies().toString();
        JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET,
                url, null, requestResponse::onResponse,
                error -> System.out.println(error.getMessage())
        );
        RestApi.getInstance().addRequestQueue(request);
    }

    public void addNewComments(Comments comments, OnResponseComment responseComment) {
        JSONObject object = new JSONObject();
        try {
            object.put("author_name", comments.getAuthorName());
            object.put("author_email", comments.getEmail());
            object.put("content", comments.getContent());
            object.put("post", comments.getPost());
            object.put("parent", comments.getParent());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST,
                new RestApi.Create(siteName).addComments().toString(), object, responseComment::onResponse,
                error -> responseComment.onError(error.getMessage())) {
            @NonNull
            @Override
            public Map<String, String> getHeaders() {
                Map<String, String> params = new HashMap<>();
                params.put("Content-Type", "application/json");
                return params;
            }
        };

        RestApi.getInstance().addRequestQueue(request);
    }

    public void userLogIn(Users users, OnPutRequestResponse requestResponse){
        JSONObject object = new JSONObject();
        try {
            object.put("username", users.getUsername());
            object.put("password", users.getPassword());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST,
                new RestApi.Create(siteName).userLogIn().toString(),
                object, requestResponse::onResponse,
                Throwable::printStackTrace) {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("Content-Type", "application/json; charset=utf-8");
                return params;
            }
        };
        RestApi.getInstance().addRequestQueue(request);
    }

    public void userSignUp(Users users, OnPutRequestResponse requestResponse) {
        JSONObject object = new JSONObject();
        try {
            object.put("username", users.getUsername());
            object.put("email", users.getEmail());
            object.put("password", users.getPassword());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST,
                new RestApi.Create(siteName).get().userSignUp().toString(), object,
                requestResponse::onResponse,
                Throwable::printStackTrace) {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("Content-Type", "application/json; charset=utf-8");
                return params;
            }
        };
        RestApi.getInstance().addRequestQueue(request);
    }


}
