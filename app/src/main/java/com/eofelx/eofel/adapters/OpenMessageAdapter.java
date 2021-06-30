package com.eofelx.eofel.adapters;

import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.eofelx.eofel.R;
import com.eofelx.eofel.models.MessageModelData;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class OpenMessageAdapter extends RecyclerView.Adapter<OpenMessageAdapter.ViewHolder> {

    Adapter.OnClickListener listener;
    List<MessageModelData> modelData;

    public OpenMessageAdapter(List<MessageModelData> modelData, Adapter.OnClickListener listener) {
        this.modelData = modelData;
        this.listener = listener;
    }


    @NonNull
    @NotNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.adapter_message_view, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull OpenMessageAdapter.ViewHolder holder, int position) {
        holder.bind(modelData.get(position), listener);
    }

    @Override
    public int getItemCount() {
        return modelData.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{

        LinearLayout layout;
        TextView message, date;

        public ViewHolder(@NonNull @NotNull View itemView) {
            super(itemView);
            date = itemView.findViewById(R.id.message_from_time);
            message = itemView.findViewById(R.id.message_from);
            layout = itemView.findViewById(R.id.main_content);
        }

        void bind(MessageModelData data, Adapter.OnClickListener listener) {
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.WRAP_CONTENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT
            );
            if (data.getType() == 1) {
                layout.setBackground(ContextCompat.getDrawable(itemView.getContext(), R.drawable.message));
                params.gravity = Gravity.START;
            } if (data.getType() == 0) {
                layout.setBackground(ContextCompat.getDrawable(itemView.getContext(), R.drawable.message_me));
                params.gravity = Gravity.END;
            }
            layout.setLayoutParams(params);
            date.setText(data.getDate());
            message.setText(data.getMessage());
            itemView.setOnClickListener(v -> listener.onClick(data));
        }
    }
}
