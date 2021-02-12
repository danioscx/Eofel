package com.eofelx.eofel.adapters;

import android.content.Context;
import android.os.Build;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;
import com.eofelx.eofel.R;
import com.eofelx.eofel.activities.RootActivity;
import com.eofelx.eofel.models.Comments;
import com.eofelx.eofel.utils.Query;
import com.eofelx.eofel.views.home.PostViews;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;


import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CommentAdapter extends RecyclerView.Adapter<CommentAdapter.ViewHolder> {

    List<Comments> comments;
    Adapter.OnCommentsClickListener adapter;

    public CommentAdapter(List<Comments> comments, Adapter.OnCommentsClickListener clicked) {
        this.comments = comments;
        this.adapter = clicked;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_comments, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.bind(comments.get(position), adapter);
        for (int i = 0; i < comments.size() ; i++) {
            if (comments.get(i).getId().equals(comments.get(position).getParent())) {
                holder.layout.setPadding(120, 0, 0, 0);
            }
        }
        System.out.println("TEST TEST" + getItemId(position));

    }

    @Override
    public int getItemCount() {
        return comments.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        ImageView avatar;
        TextView title, date, content;
        TextInputLayout editText;
        TextInputEditText inputEditText;
        LinearLayout layout;

        RequestQueue queue;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            avatar = itemView.findViewById(R.id.author_avatar);
            title = itemView.findViewById(R.id.author_name);
            date = itemView.findViewById(R.id.date_comment);
            content = itemView.findViewById(R.id.content_comment);
            editText = itemView.findViewById(R.id.reply);
            inputEditText = itemView.findViewById(R.id.reply_values);
            layout = itemView.findViewById(R.id.layout_comments);
            queue = Volley.newRequestQueue(itemView.getContext());
        }

        void bind(@NonNull Comments comments, Adapter.OnCommentsClickListener adapter) {
            Glide.with(itemView.getContext())
                    .load(comments.getAvatar())
                    .centerCrop()
                    .into(avatar);
            title.setText(comments.getAuthorName());
            date.setText(comments.getDate());
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                content.setText(Html.fromHtml(comments.getContent(), Html.FROM_HTML_MODE_COMPACT));
            } else {
                content.setText(Html.fromHtml(comments.getContent()));
            }

            inputEditText.setOnFocusChangeListener((v, hasFocus) -> {
                if (!hasFocus) {
                    editText.setVisibility(View.GONE);
                }
            });


            itemView.setOnClickListener(v -> {
                InputMethodManager inputMethodManager = (InputMethodManager) v.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
                adapter.onClick(comments);
                if (editText.getVisibility() == View.GONE) {
                    editText.setVisibility(View.VISIBLE);
                    inputEditText.requestFocus();
                    System.out.println(getAdapterPosition() -1);

                    inputMethodManager.showSoftInput(inputEditText, InputMethodManager.SHOW_IMPLICIT);
                    editText.setEndIconOnClickListener(view -> {
                        if (inputEditText.getText().toString().equals("")) {
                            Toast.makeText(v.getContext(), "Please provide ", Toast.LENGTH_SHORT).show();
                        } else {
                            JSONObject object = new JSONObject();
                            try {
                                object.put("author_name", "dani");
                                object.put("author_email", "danixml31@gmail.com");
                                object.put("content", inputEditText.getText().toString());
                                object.put("post", comments.getPost());
                                object.put("parent", comments.getId());
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                            JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST,
                                    Query.get().putComments(), object, response -> {
                                        RootActivity activity = (RootActivity) view.getContext();
                                        PostViews postViews = new PostViews();
                                        activity.getSupportFragmentManager()
                                                .beginTransaction()
                                                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                                                .replace(R.id.main_fragment, postViews, postViews.getClass().getSimpleName())
                                                .commit();
                                        System.out.println(response);
                                    }, error -> System.out.println(error.getMessage())) {
                                @Override
                                public Map<String, String> getHeaders() {
                                    Map<String, String> params = new HashMap<>();
                                    params.put("Content-Type", "application/json");
                                    return params;
                                }

                            };
                            queue.add(request);

                        }
                    });
                } else {
                    editText.setVisibility(View.GONE);
                    inputMethodManager.hideSoftInputFromWindow(inputEditText.getWindowToken(), 0);
                }
            });
        }
    }
}
