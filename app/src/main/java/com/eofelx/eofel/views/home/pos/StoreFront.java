package com.eofelx.eofel.views.home.pos;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Bundle;
import android.view.MenuItem;

import com.eofelx.eofel.R;
import com.eofelx.eofel.adapters.PagerAdapter;
import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class StoreFront extends AppCompatActivity {

    List<String> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_store_front);
        MaterialToolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);

        list = new ArrayList<>();
        list.add("Durian");
        list.add("Daging");
        list.add("Semangka");
        list.add("Sayur");
        list.add("Semangka");
        PagerAdapter adapter = new PagerAdapter(getSupportFragmentManager(), getLifecycle(), list);
        ViewPager2 pager2 = findViewById(R.id.view_pager);
        TabLayout tabLayout = findViewById(R.id.tab_layout);
        pager2.setAdapter(adapter);

        new TabLayoutMediator(tabLayout, pager2, (tab, position) -> tab.setText(list.get(position))).attach();
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
            return true;
        }
        return false;
    }
}