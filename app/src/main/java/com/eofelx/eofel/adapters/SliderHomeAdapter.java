package com.eofelx.eofel.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.eofelx.eofel.R;
import com.eofelx.eofel.models.SliderItem;
import com.smarteist.autoimageslider.SliderViewAdapter;

import java.util.List;

public class SliderHomeAdapter extends SliderViewAdapter<SliderHomeAdapter.Holder> {

    public interface OnItemClickListener {
        void onClick(SliderItem item);
    }

    List<SliderItem> items;
    OnItemClickListener listener;

    public SliderHomeAdapter(List<SliderItem> items, OnItemClickListener listener) {
        this.items = items;
        this.listener = listener;
    }
    @Override
    public SliderHomeAdapter.Holder onCreateViewHolder(ViewGroup parent) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.adapter_slider, parent, false);
        return new Holder(view);
    }

    @Override
    public void onBindViewHolder(SliderHomeAdapter.Holder viewHolder, int position) {
        viewHolder.bind(items.get(position), listener);
    }

    @Override
    public int getCount() {
        return items.size();
    }

    static class Holder extends SliderViewAdapter.ViewHolder {

        ImageView imageView;
        public Holder(View itemView) {
            super(itemView);

            imageView = itemView.findViewById(R.id.image_adapter);
        }

        void bind(SliderItem item, OnItemClickListener listener) {
            Glide.with(itemView.getContext())
                    .load(item.getId())
                    .fitCenter()
                    .into(imageView);
            itemView.setOnClickListener(v -> listener.onClick(item));
        }
    }
}
