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
import com.eofelx.eofel.models.SpecialModel;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class SpecialAdapter extends RecyclerView.Adapter<SpecialAdapter.ViewHolder> {

    private final List<SpecialModel> models;
    private final Adapter.OnClickListener listener;

    public SpecialAdapter(List<SpecialModel> models, Adapter.OnClickListener listener) {
        this.models = models;
        this.listener = listener;
    }

    @NonNull
    @NotNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.adapter_special, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull SpecialAdapter.ViewHolder holder, int position) {
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
            imageView = itemView.findViewById(R.id.special_image);
            textView = itemView.findViewById(R.id.title_special);
        }

        void bind(SpecialModel model, Adapter.OnClickListener listener) {
            Glide.with(itemView)
                    .load(model.getId())
                    .into(imageView);
            textView.setText(model.getTitle().substring(0, 35));
            itemView.setOnClickListener(v -> listener.onClick(model));
        }
    }
}
