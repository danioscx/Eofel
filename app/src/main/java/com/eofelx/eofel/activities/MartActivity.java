package com.eofelx.eofel.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import com.eofelx.eofel.R;
import com.eofelx.eofel.adapters.Adapter;
import com.eofelx.eofel.adapters.MartAdapter;
import com.eofelx.eofel.models.BaseModel;
import com.eofelx.eofel.models.MartModel;
import com.google.android.material.appbar.MaterialToolbar;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class MartActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mart);
        MaterialToolbar toolbar = findViewById(R.id.mart_toolbar);
        setSupportActionBar(toolbar);

        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);

        List<MartModel> models = new ArrayList<>();
        models.add(new MartModel(R.raw.one, "Buah Naga", "Rp 12.000"));
        models.add(new MartModel(R.raw.two, "Dummy", "Rp 17.500"));
        models.add(new MartModel(R.raw.slider1, "Buah Jelas", "Rp 10.000"));
        models.add(new MartModel(R.raw.three, "Buah Sehat", "Rp 25.000"));

        RecyclerView recyclerView = findViewById(R.id.mart_recycler);
        LinearLayoutManager manager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(manager);
        recyclerView.setAdapter(new MartAdapter(models, new Adapter.OnClickListener() {
            @Override
            public <T extends BaseModel> void onClick(T click) {
                Toast.makeText(getApplicationContext(), ((MartModel)click).getPrice(), Toast.LENGTH_SHORT).show();
            }
        }));
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