package com.eofelx.eofel.views.home;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.eofelx.eofel.R;
import com.eofelx.eofel.adapters.CommentAdapter;
import com.eofelx.eofel.adapters.PostAdapter;
import com.eofelx.eofel.models.Comments;
import com.eofelx.eofel.models.Posts;
import com.eofelx.eofel.utils.Query;
import com.eofelx.eofel.views.BaseViews;
import com.eofelx.eofel.views.HomeViews;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class PostViews extends BaseViews implements BaseViews.OnBackPress {


    TextView title;
    WebView contents;

    RecyclerView recyclerViewCategory;
    RecyclerView recyclerViewReplies;
    EditText editText;

    LinearLayoutManager manager;

    RequestQueue queue;

   AdView adView;

   private CommentCallback callback;


   public interface CommentCallback {
       void onResult(List<Comments> comments);
   }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.view_posts, container, false);
    }

    @SuppressLint("SetJavaScriptEnabled")
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        AppCompatActivity activity = (AppCompatActivity) requireActivity();// getActivity();
        Objects.requireNonNull(activity.getSupportActionBar()).setDisplayHomeAsUpEnabled(true);

        editText = view.findViewById(R.id.reply);


        adView = view.findViewById(R.id.ad_view);

        MobileAds.initialize(getContext(), initializationStatus -> {
        });

        AdRequest adRequest = new AdRequest.Builder().build();
        adView.loadAd(adRequest);

        title = view.findViewById(R.id.title_post);
        contents = view.findViewById(R.id.content_html);
        queue = Volley.newRequestQueue(requireContext().getApplicationContext());
        manager = new LinearLayoutManager(requireContext());
        if (getArguments() != null) {
            title.setText(getArguments().getString("title"));
            contents.getSettings().setJavaScriptEnabled(true);
            contents.loadDataWithBaseURL(null, getArguments().getString("content"), "text/html", "UTF-8", null);
            contents.setWebViewClient(new WebViewClient(){
                @Override
                public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
                    //view.loadUrl(request.getUrl().toString());
                    return false;
                }
            });
            contents.setOnKeyListener((v, keyCode, event) -> {
                if (event.getAction() == KeyEvent.ACTION_DOWN) {
                    if (keyCode == KeyEvent.KEYCODE_BACK) {
                        if (contents.canGoBack()) {
                            contents.goBack();
                        } else {
                            requireActivity().getSupportFragmentManager().popBackStack();
                        }
                        return true;
                    }
                }
                return false;
            });
            recyclerViewCategory = view.findViewById(R.id.recycler_view_category);
            recyclerViewReplies = view.findViewById(R.id.recycler_view_comments);
            addCategory();
            addComments(callback);
        }
    }




    private void addComments(CommentCallback callback) {
        List<Comments> comments = new ArrayList<>();
        String repliesUrl = getArguments().getString("replies") + "&order=asc&per_page=100";
        JsonArrayRequest arrayRequest = new JsonArrayRequest(Request.Method.GET,
                repliesUrl, null, response -> {
                    if (response.length() != 0) {
                        Comments comment = new Comments();
                        for (int i = 0; i < response.length(); i++) {
                            try {
                                JSONObject object = response.getJSONObject(i);
                                comment.setId(object.getString("id"));
                                comment.setDate(object.getString("date"));
                                comment.setAuthorName(object.getString("author_name"));
                                comment.setAuthorUrl(object.getString("author_url"));
                                JSONObject content = object.getJSONObject("content");
                                for (int j = 0; j < content.length() ; j++) {
                                    comment.setContent(content.getString("rendered"));
                                }
                                JSONObject avatar = object.getJSONObject("author_avatar_urls");
                                for (int j = 0; j < avatar.length(); j++) {
                                    comment.setAvatar(avatar.getString("96"));
                                }
                                comment.setParent(object.getString("parent"));
                                comment.setPost(object.getString("post"));

                                comments.add(new Comments(
                                        comment.getId(),
                                        comment.getAuthorName(),
                                        comment.getAuthorUrl(),
                                        comment.getDate(),
                                        comment.getContent(),
                                        comment.getParent(),
                                        comment.getAvatar(),
                                        comment.getPost()
                                ));
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }

                        }
                        callback.onResult(comments);
                        recyclerViewReplies.setAdapter(new CommentAdapter(comments, commentClick -> {

                        }, (commentField, editText) -> {
                            RequestQueue requestQueue = Volley.newRequestQueue(getContext());
                            if (editText.getText().toString().equals("")) {
                                Toast.makeText(getContext(), "Please insert comment ", Toast.LENGTH_SHORT).show();
                            } else {
                                JSONObject object = new JSONObject();
                                try {
                                    object.put("author_name", "dani");
                                    object.put("author_email", "danixml31@gmail.com");
                                    object.put("content", editText.getText().toString());
                                    object.put("post", commentField.getPost());
                                    object.put("parent", commentField.getId());
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                                JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST,
                                        Query.get().putComments(), object, response1 -> {
                                    PostViews postViews = new PostViews();
                                    Bundle bundle = new Bundle();
                                    bundle.putString("id", getArguments().getString("id"));
                                    bundle.putString("title", getArguments().getString("title"));
                                    bundle.putString("content", getArguments().getString("content"));
                                    bundle.putString("category", getArguments().getString("category"));
                                    bundle.putString("replies", getArguments().getString("replies"));
                                    postViews.setArguments(bundle);
                                    getActivity().getSupportFragmentManager()
                                            .beginTransaction()
                                            .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                                            .replace(R.id.main_fragment, postViews, postViews.getClass().getSimpleName())
                                            .commit();
                                }, error -> System.out.println(error.getMessage())) {
                                    @NonNull
                                    @Override
                                    public Map<String, String> getHeaders() {
                                        Map<String, String> params = new HashMap<>();
                                        params.put("Content-Type", "application/json");
                                        return params;
                                    }
                                };
                                request.setShouldCache(false);
                                requestQueue.add(request);
                            }
                        }));
                        manager.setOrientation(RecyclerView.VERTICAL);
                        recyclerViewReplies.setLayoutManager(manager);
                    } else {
                        System.out.println("no values");
                    }
                }, error -> {

        });
        arrayRequest.setShouldCache(false);
        queue.add(arrayRequest);
    }

    private void addCategory() {
        String[] category = getArguments().getString("category").split(",");
        String id = category[0].replaceAll("[^a-zA-Z0-9]", "");
        List<Posts> posts = new ArrayList<>();
        Posts content = new Posts();
        JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET,
                Query.get().getAllCategories(id), null, response -> {
            try {
                for (int x = 0; x < response.length(); x++) {
                    JSONObject object = response.getJSONObject(x);

                    content.setId(object.getString("id"));
                    content.setUrl(object.getString("link"));
                    content.setDate(object.getString("date"));
                    content.setMediaFeatured(object.getString("featured_media"));

                    JSONObject title = object.getJSONObject("title");
                    for (int i = 0; i < title.length(); i++) {
                        content.setTitle(title.getString("rendered"));
                    }

                    JSONObject excerpt = object.getJSONObject("excerpt");
                    for (int i = 0; i < excerpt.length() ; i++) {
                        content.setExcerpt(excerpt.getString("rendered"));
                    }

                    JSONObject cont = object.getJSONObject("content");
                    for (int i = 0; i < cont.length(); i++) {
                        content.setContent(cont.getString("rendered"));
                    }

                    content.setCategory(object.getString("categories"));

                    content.setReplies(Query.get().getCommentsUrls(object.getString("id")));

                    posts.add(new Posts(
                            content.getId(),
                            content.getUrl(),
                            content.getDate(),
                            content.getTitle(),
                            content.getMediaFeatured(),
                            content.getContent(),
                            content.getExcerpt(),
                            content.getCategory(),
                            content.getReplies()
                    ));
                }
                recyclerViewCategory.setAdapter(new PostAdapter(posts, post -> {
                    PostViews postViews = new PostViews();
                    Bundle bundle = new Bundle();
                    bundle.putString("id", post.getId());
                    bundle.putString("title", post.getTitle());
                    bundle.putString("content", post.getContent());
                    bundle.putString("category", post.getCategory());
                    bundle.putString("replies", post.getReplies());
                    postViews.setArguments(bundle);
                    getFragmentManager().beginTransaction()
                            .replace(R.id.main_fragment, postViews)
                            .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                            .addToBackStack(HomeViews.class.getSimpleName())
                            .commit();
                }));
                LinearLayoutManager manager = new LinearLayoutManager(getContext());
                manager.setOrientation(RecyclerView.VERTICAL);
                recyclerViewCategory.setLayoutManager(manager);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }, error -> Log.e("Error request: ", error.getMessage()));

        queue.add(request);
    }

    @Override
    public void backPress() {
        requireActivity().getSupportFragmentManager().popBackStack();
    }
}
