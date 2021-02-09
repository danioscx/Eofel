package com.eofelx.eofel.adapters;

import android.os.Build;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;
import com.eofelx.eofel.R;
import com.eofelx.eofel.models.Posts;
import com.eofelx.eofel.utils.Query;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

public class PostAdapter extends RecyclerView.Adapter<PostAdapter.ViewHolder> {

    public static final String TAG = "PostAdapter";

    List<Posts> posts;
    Adapter.OnPostsClickListener listener;


    public PostAdapter(List<Posts> posts, Adapter.OnPostsClickListener listener) {
        this.posts = posts;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_posts, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.bind(posts.get(position), listener);
    }

    @Override
    public int getItemCount() {
        return posts.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        TextView title;
        TextView excerpt;
        ImageView imageView;

        RequestQueue queue;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.title);
            excerpt = itemView.findViewById(R.id.excerpt);
            imageView = itemView.findViewById(R.id.featured_images);

            queue = Volley.newRequestQueue(itemView.getContext().getApplicationContext());
        }

        void bind(Posts posts, Adapter.OnPostsClickListener listener) {
            title.setText(posts.getTitle());
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                excerpt.setText(Html.fromHtml(posts.getExcerpt().substring(0, 120) + "...", Html.FROM_HTML_MODE_COMPACT));
            } else {
                excerpt.setText(Html.fromHtml(posts.getExcerpt().substring(0, 120) + "..."));
            }
            if (posts.getMediaFeatured().equals("0")) {

            } else {
                JsonObjectRequest arrayRequest = new JsonObjectRequest(Request.Method.GET,
                        Query.get().getMedia(posts.getMediaFeatured()), null, response -> {
                            try {
                                JSONObject guid = response.getJSONObject("guid");
                                for (int i =0; i < guid.length(); i++) {
                                    Glide.with(itemView.getContext())
                                            .load(guid.getString("rendered"))
                                            .placeholder(R.drawable.ic_loading)
                                            .centerCrop()
                                            .into(imageView);
                                }

                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }, error -> Log.e(TAG, error.getMessage()));
                queue.add(arrayRequest);
            }
            itemView.setOnClickListener(v -> listener.onClick(posts));
        }
    }
}
