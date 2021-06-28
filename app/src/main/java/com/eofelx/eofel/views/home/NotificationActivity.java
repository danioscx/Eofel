package com.eofelx.eofel.views.home;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.MenuItem;

import com.eofelx.eofel.R;
import com.eofelx.eofel.adapters.Adapter;
import com.eofelx.eofel.adapters.NotificationAdapter;
import com.eofelx.eofel.models.BaseModel;
import com.eofelx.eofel.models.NotificationModel;
import com.google.android.material.appbar.MaterialToolbar;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class NotificationActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification);
        MaterialToolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);

        //validation data
        recyclerView = findViewById(R.id.recycler_view_notif);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);
        List<NotificationModel> models = new ArrayList<>();
        models.add(new NotificationModel("Ada yang baru dari vazz", getResources().getString(R.string.special_title), "22/01/2020"));
        models.add(new NotificationModel("Ada yang baru dari vazz", getResources().getString(R.string.special_title), "22/01/2020"));
        models.add(new NotificationModel("Ada yang baru dari vazz", getResources().getString(R.string.special_title), "22/01/2020"));
        models.add(new NotificationModel("Ada yang baru dari vazz", getResources().getString(R.string.special_title), "22/01/2020"));
        models.add(new NotificationModel("Ada yang baru dari vazz", getResources().getString(R.string.special_title), "22/01/2020"));
        recyclerView.setAdapter(new NotificationAdapter(models, new Adapter.OnClickListener() {
            @Override
            public <T extends BaseModel> void onClick(T click) {

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