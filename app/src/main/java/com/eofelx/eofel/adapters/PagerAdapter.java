package com.eofelx.eofel.adapters;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.eofelx.eofel.R;
import com.eofelx.eofel.models.MartModel;
import com.eofelx.eofel.views.home.pos.StoreObject;

import org.jetbrains.annotations.NotNull;

import java.io.Serializable;
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
        StoreObject object = new StoreObject();
        Bundle bundle = new Bundle();
        ArrayList<MartModel> models = new ArrayList<>();
        models.add(new MartModel(R.raw.one, "Buah Naga", "Rp 12.000"));
        models.add(new MartModel(R.raw.two, "Dummy", "Rp 17.500"));
        models.add(new MartModel(R.raw.slider1, "Buah Jelas", "Rp 10.000"));
        models.add(new MartModel(R.raw.three, "Buah Sehat", "Rp 25.000"));
        bundle.putSerializable(StoreObject.object, (Serializable) models);
        bundle.putInt(StoreObject.title, position);
        object.setArguments(bundle);
        return object;
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}
