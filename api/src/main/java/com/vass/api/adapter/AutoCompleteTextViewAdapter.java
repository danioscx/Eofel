package com.vass.api.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.vass.api.R;
import com.vass.api.model.Model;

import java.util.List;

public class  AutoCompleteTextViewAdapter<T> extends ArrayAdapter<T> {


    Context context;
    List<T> provinces;

    int resource;

    public AutoCompleteTextViewAdapter(@NonNull Context context, int resource, List<T> provinces) {
        super(context, resource, provinces);
        this.context = context;
        this.resource = resource;
        this.provinces = provinces;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(context)
                    .inflate(resource, parent, false);
        }
        TextView textView = convertView.findViewById(android.R.id.text1);
        List<? extends Model> models;
        models = (List<? extends Model>) provinces;
        textView.setText(models.get(position).getName());

        return convertView;

    }

}
