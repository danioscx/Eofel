package com.eofelx.eofel.adapters;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.eofelx.eofel.views.home.pos.PosObject;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class PagerAdapter extends FragmentStateAdapter {

    List<String> list;
    public PagerAdapter(FragmentManager manager, Lifecycle lifecycle, List<String> list) {
        super(manager, lifecycle);
        this.list = list;
    }
    @NonNull
    @NotNull
    @Override
    public Fragment createFragment(int position) {
        PosObject object = new PosObject();
        Bundle bundle = new Bundle();
        bundle.putInt(PosObject.title, position);
        object.setArguments(bundle);
        return object;
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}
