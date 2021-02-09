package com.eofelx.eofel.adapters;

import android.os.Build;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.eofelx.eofel.R;
import com.eofelx.eofel.models.Comments;


import java.util.List;

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
    }

    @Override
    public int getItemCount() {
        return comments.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        ImageView avatar;
        TextView title, date, content;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            avatar = itemView.findViewById(R.id.author_avatar);
            title = itemView.findViewById(R.id.author_name);
            date = itemView.findViewById(R.id.date_comment);
            content = itemView.findViewById(R.id.content_comment);
        }

        void bind(Comments comments, Adapter.OnCommentsClickListener adapter) {
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
            itemView.setOnClickListener(v -> adapter.onClick(comments));
        }
    }
}
