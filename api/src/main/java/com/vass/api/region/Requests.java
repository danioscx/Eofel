package com.vass.api.region;

import android.content.Context;
import android.util.Log;
import android.widget.AutoCompleteTextView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.vass.api.R;
import com.vass.api.adapter.AutoCompleteTextViewAdapter;
import com.vass.api.model.Model;
import com.vass.api.model.Region;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Requests<T> {

    public static final String TAG = "Request";

    RequestQueue queue;
    Context context;
    String url;

    Map<String, String> params;
    JSONObject object;

    public Requests<T> insert(Context context, String url) {
        queue = Volley.newRequestQueue(context);
        this.context = context;
        this.url = url;
        return this;
    }

    public Requests<T> header(Map<String, String> params, JSONObject object) {
        this.params = params;
        this.object = object;
        return this;
    }

    public void commit() {
        JsonObjectRequest request = new JsonObjectRequest(
                Request.Method.POST,
                url,
                object,
                response -> {
                    try {
                        Log.i(TAG, response.getString("status"));
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                },
                System.out::println
        ) {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                return params;
            }
        };

        queue.add(request);
    }

    public void applyInto(List<T> result) {

    }

    public interface ItemClickListener {
        void onClick(String position);
        void getText(String name);
    }

    public Requests<T> get(Context context, String url) {
        queue = Volley.newRequestQueue(context);
        this.context = context;
        this.url = url;
        return this;
    }

    public void apply(AutoCompleteTextView textView, ItemClickListener listener) {
        JsonArrayRequest request = new JsonArrayRequest(
                com.android.volley.Request.Method.GET, url, null,
                response -> {
                    List<? extends Model> list;
                    List<Region> regions = new ArrayList<>();
                    for (int i = 0; i < response.length(); i++) {
                        try {
                            JSONObject object = response.getJSONObject(i);
                            regions.add(new Region(
                                    object.getString("id"),
                                    object.getString("name")
                            ));
                            list = regions;

                            AutoCompleteTextViewAdapter<? extends Model> adapter = new AutoCompleteTextViewAdapter<>(context, R.layout.support_simple_spinner_dropdown_item, list);
                            textView.setAdapter(adapter);
                            List<? extends Model> finalList = list;
                            textView.setOnItemClickListener((parent, view, position, id) -> {
                                listener.onClick(finalList.get(position).getId());
                                listener.getText(finalList.get(position).getName());
                            });
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, System.out::println);
        queue.add(request);
    }

}
