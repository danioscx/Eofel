package com.eofelx.eofel.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.eofelx.eofel.R;
import com.eofelx.eofel.models.UserModel;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class PosAdapter extends RecyclerView.Adapter<PosAdapter.ViewHolder> {


    Adapter.OnClickListener listener;
    List<UserModel> models;

    public PosAdapter(List<UserModel> models, Adapter.OnClickListener listener) {
        this.models = models;
        this.listener = listener;
    }

    @NonNull
    @NotNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.adapter_pos, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull PosAdapter.ViewHolder holder, int position) {
        holder.bind(models.get(position), listener);
    }

    @Override
    public int getItemCount() {
        return models.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        ImageView imageView;
        TextView textView;
        public ViewHolder(@NonNull @NotNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.name);
            imageView = itemView.findViewById(R.id.avatar);
        }

        void bind(UserModel model, Adapter.OnClickListener listener) {
            textView.setText(model.getName());
            Glide.with(itemView.getContext())
                    .load(model.getId())
                    .into(imageView);
            itemView.setOnClickListener(v -> listener.onClick(model));
        }
    }
}
