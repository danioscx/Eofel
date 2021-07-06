package com.eofelx.eofel.views.home.pos;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import com.eofelx.eofel.R;
import com.eofelx.eofel.adapters.Adapter;
import com.eofelx.eofel.adapters.MartAdapter;
import com.eofelx.eofel.adapters.PagerAdapter;
import com.eofelx.eofel.models.BaseModel;
import com.eofelx.eofel.models.MartModel;
import com.eofelx.eofel.views.BaseViews;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class StoreObject extends BaseViews {

    public static final String title = "title";
    public static final String object = "object";
    @Nullable
    @org.jetbrains.annotations.Nullable
    @Override
    public View onCreateView(@NonNull @NotNull LayoutInflater inflater, @Nullable @org.jetbrains.annotations.Nullable ViewGroup container, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.view_pos_object, container, false);
    }

    @Override
    public void onViewCreated(@NonNull @NotNull View view, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Bundle bundle = getArguments();
        assert bundle != null;
        ArrayList<MartModel> models = (ArrayList<MartModel>) bundle.getSerializable(object);
        RecyclerView recyclerView = view.findViewById(R.id.recycler_view);
        LinearLayoutManager manager = new LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(manager);
        recyclerView.setAdapter(new MartAdapter(models, new Adapter.OnClickListener() {
            @Override
            public <T extends BaseModel> void onClick(T click) {
                //Toast.makeText(getApplicationContext(), ((MartModel)click).getPrice(), Toast.LENGTH_SHORT).show();
            }
        }));
        Toast.makeText(requireContext(), "Ini adalah " + Integer.toString(bundle.getInt(title)), Toast.LENGTH_SHORT).show();
    }
}
