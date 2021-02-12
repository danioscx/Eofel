package com.eofelx.eofel.views;

import android.content.res.Configuration;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.eofelx.eofel.R;
import com.eofelx.eofel.adapters.PostAdapter;
import com.eofelx.eofel.models.Posts;
import com.eofelx.eofel.utils.Query;
import com.eofelx.eofel.views.home.PostViews;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class HomeViews extends BaseViews implements BaseViews.OnBackPress {


    private RequestQueue queue;


    RecyclerView recyclerView;
    LinearLayout linearLayout;

    private Posts content;



    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.view_home, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        AppCompatActivity activity = (AppCompatActivity) requireActivity();// getActivity();
        Objects.requireNonNull(activity.getSupportActionBar()).setDisplayHomeAsUpEnabled(false);
        queue = Volley.newRequestQueue(requireContext().getApplicationContext());
        recyclerView = view.findViewById(R.id.home_recycler_view);
        linearLayout = view.findViewById(R.id.layout_loading);
        content = new Posts();

        //initial ads
        getAllPost(40);
    }

    public void getAllPost(int page) {
        List<Posts> posts = new ArrayList<>();
        JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET,
                Query.get().getAllPosts(page), null, response -> {
                    linearLayout.setVisibility(View.GONE);
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

                            content.setReplies(Query.get().getCommentsUrls(content.getId()));

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
                        System.out.println(posts.size());
                        recyclerView.setAdapter(new PostAdapter(posts, post -> {
                            System.out.println("Content: " +post.getId());
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
                        int resources = getContext().getResources().getConfiguration().orientation;
                        if (resources == Configuration.ORIENTATION_LANDSCAPE) {
                            GridLayoutManager layoutManager = new GridLayoutManager(getContext(), 2);
                            recyclerView.setLayoutManager(layoutManager);
                        } else if (resources == Configuration.ORIENTATION_PORTRAIT) {
                            LinearLayoutManager manager = new LinearLayoutManager(getContext().getApplicationContext());
                            manager.setOrientation(RecyclerView.VERTICAL);
                            recyclerView.setLayoutManager(manager);
                        }
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
