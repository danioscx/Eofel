package com.eofelx.eofel.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.eofelx.eofel.R;
import com.eofelx.eofel.models.MessageModel;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class MessageAdapter extends RecyclerView.Adapter<MessageAdapter.ViewHolder> {

    Adapter.OnClickListener listener;
    List<MessageModel> models;

    public MessageAdapter(List<MessageModel> models, Adapter.OnClickListener listener) {
        this.models = models;
        this.listener = listener;
    }
    @NonNull
    @NotNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.adapter_message, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull MessageAdapter.ViewHolder holder, int position) {
        holder.bind(models.get(position), listener);
    }

    @Override
    public int getItemCount() {
        return models.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView, edit;
        TextView pengirim, isi;

        public ViewHolder(@NonNull @NotNull View itemView) {
            super(itemView);
            isi = itemView.findViewById(R.id.isi_pesan);
            edit = itemView.findViewById(R.id.edit_message);
            pengirim = itemView.findViewById(R.id.nama_pengirim);
            imageView = itemView.findViewById(R.id.author_avatar);
        }

        void bind(MessageModel model, Adapter.OnClickListener listener) {
            if (model.getMessages().length() > 30) {
                isi.setText(model.getMessages().substring(0, 30));
            } else {
                isi.setText(model.getMessages());
            }

            edit.setOnClickListener(v -> {
                PopupMenu menu = new PopupMenu(itemView.getContext(), edit);
                menu.inflate(R.menu.mart_short);

                menu.show();
            });
            pengirim.setText(model.getName());
            Glide.with(imageView.getContext())
                    .load(model.getId())
                    .into(imageView);
            itemView.setOnClickListener(v -> listener.onClick(model));
        }
    }
}
