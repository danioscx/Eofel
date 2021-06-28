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
import com.eofelx.eofel.models.MartModel;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class MartAdapter extends RecyclerView.Adapter<MartAdapter.ViewHolder> {


    List<MartModel> models;
    Adapter.OnClickListener listener;

    public MartAdapter(List<MartModel> models, Adapter.OnClickListener listener) {
        this.models = models;
        this.listener = listener;
    }

    @NonNull
    @NotNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.mart_adapter, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull MartAdapter.ViewHolder holder, int position) {
        holder.bind(models.get(position), listener);
    }

    @Override
    public int getItemCount() {
        return models.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {


        ImageView imageView;
        TextView title, price;
        ImageView mPopup;
        public ViewHolder(@NonNull @NotNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.mart_adapter_image);
            title = itemView.findViewById(R.id.mart_adapter_title);
            price = itemView.findViewById(R.id.mart_adapter_price);
            mPopup = itemView.findViewById(R.id.mart_menu);
        }

        void bind(MartModel model, Adapter.OnClickListener listener) {

            Glide.with(itemView)
                    .load(model.getId())
                    .into(imageView);
            if (model.getTitle().length() > 30) {
                title.setText(model.getTitle().substring(0, 30));
            } else
                title.setText(model.getTitle());
            price.setText(model.getPrice());

            mPopup.setOnClickListener(v -> {
                PopupMenu menu = new PopupMenu(itemView.getContext(), mPopup);
                menu.inflate(R.menu.mart_menu);

                menu.show();
            });



            itemView.setOnClickListener(v -> listener.onClick(model));

        }
    }
}
