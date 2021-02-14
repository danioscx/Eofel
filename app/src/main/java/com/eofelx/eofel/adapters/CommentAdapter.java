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

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.eofelx.eofel.R;
import com.eofelx.eofel.models.Comments;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;



import java.util.List;

public class CommentAdapter extends RecyclerView.Adapter<CommentAdapter.ViewHolder> {

    List<Comments> comments;
    Adapter.OnCommentsClickListener adapter;
    Adapter.OnEndIconClickListener listener;

    public CommentAdapter(List<Comments> comments, Adapter.OnCommentsClickListener clicked, Adapter.OnEndIconClickListener listener) {
        this.comments = comments;
        this.adapter = clicked;
        this.listener = listener;
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
        holder.bind(comments.get(position), adapter, listener);
        for (int i = 0; i < comments.size() ; i++) {
            if (comments.get(i).getId().equals(comments.get(position).getParent())) {
                holder.layout.setPadding(120, 0, 0, 0);
            }
        }

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


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            avatar = itemView.findViewById(R.id.author_avatar);
            title = itemView.findViewById(R.id.author_name);
            date = itemView.findViewById(R.id.date_comment);
            content = itemView.findViewById(R.id.content_comment);
            editText = itemView.findViewById(R.id.reply);
            inputEditText = itemView.findViewById(R.id.reply_values);
            layout = itemView.findViewById(R.id.layout_comments);
        }

        void bind(@NonNull Comments comments, Adapter.OnCommentsClickListener adapter, Adapter.OnEndIconClickListener listener) {
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
                    editText.setEndIconOnClickListener(view -> listener.onClick(comments, inputEditText));
                } else {
                    editText.setVisibility(View.GONE);
                    inputMethodManager.hideSoftInputFromWindow(inputEditText.getWindowToken(), 0);
                }
            });
        }
    }
}
