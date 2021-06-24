package com.eofelx.eofel.adapters;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.eofelx.eofel.R;
import com.eofelx.eofel.models.WalletModel;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class WalletAdapter extends RecyclerView.Adapter<WalletAdapter.ViewHolder> {

    Adapter.OnClickListener listener;
    List<WalletModel> models;

    public WalletAdapter(List<WalletModel> models, Adapter.OnClickListener listener) {
        this.models = models;
        this.listener = listener;
    }

    @NonNull
    @NotNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.wallet_adapter_history, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull WalletAdapter.ViewHolder holder, int position) {
        holder.bind(models.get(position), listener);
    }

    @Override
    public int getItemCount() {
        return models.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        TextView date, value, status;

        public ViewHolder(@NonNull @NotNull View itemView) {
            super(itemView);
            date = itemView.findViewById(R.id.history_date);
            value = itemView.findViewById(R.id.history_value);
            status = itemView.findViewById(R.id.history_status);
        }

        void bind(WalletModel model, Adapter.OnClickListener listener) {
            date.setText(model.getDate());
            value.setText(model.getValue());
            status.setText(model.getStatus());
            if (model.getStatus().equals("error")) {
                status.setTextColor(Color.RED);
            }
            itemView.setOnClickListener(v -> listener.onClick(model));
        }
    }
}
