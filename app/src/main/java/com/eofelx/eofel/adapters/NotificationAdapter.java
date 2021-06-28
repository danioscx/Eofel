package com.eofelx.eofel.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.eofelx.eofel.R;
import com.eofelx.eofel.models.NotificationModel;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class NotificationAdapter extends RecyclerView.Adapter<NotificationAdapter.ViewHolder> {


    List<NotificationModel> models;
    Adapter.OnClickListener listener;

    public NotificationAdapter(List<NotificationModel> models, Adapter.OnClickListener listener) {
        this.models = models;
        this.listener = listener;
    }
    @NonNull
    @NotNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.notification_adapter, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull NotificationAdapter.ViewHolder holder, int position) {
        holder.bind(models.get(position), listener);
    }

    @Override
    public int getItemCount() {
        return models.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        TextView date, title, description;

        public ViewHolder(@NonNull @NotNull View itemView) {
            super(itemView);
            date = itemView.findViewById(R.id.notif_adapter_date);
            title = itemView.findViewById(R.id.notif_adapter_title);
            description = itemView.findViewById(R.id.notif_adapter_desc);
        }

        void bind(NotificationModel model, Adapter.OnClickListener listener) {
            date.setText(model.getDate());
            if (model.getTitle().length() > 40) {
                title.setText(model.getTitle().substring(0, 40));
            } else {
                title.setText(model.getTitle());
            }
            if (model.getDescription().length() > 60){
                description.setText(model.getDescription().substring(0, 60));
            } else {
                description.setText(model.getDescription());
            }

            itemView.setOnClickListener(v -> listener.onClick(model));
        }
    }
}
