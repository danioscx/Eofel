package com.eofelx.eofel.views.home;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.LinearLayout;

import com.eofelx.eofel.R;
import com.eofelx.eofel.adapters.Adapter;
import com.eofelx.eofel.adapters.PosAdapter;
import com.eofelx.eofel.models.BaseModel;
import com.eofelx.eofel.models.UserModel;
import com.eofelx.eofel.views.home.pos.StoreFront;
import com.google.android.material.appbar.MaterialToolbar;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class PosActivity extends AppCompatActivity {

    LinearLayout etalase;

    RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pos);
        MaterialToolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);

        etalase = (LinearLayout) findViewById(R.id.etalase);
        etalase.setOnClickListener(v -> startActivity(new Intent(getApplicationContext(), StoreFront.class)));

        recyclerView = findViewById(R.id.recycler_view);
        genView();


    }

    private void genView() {
        List<UserModel> models = new ArrayList<>();
        models.add(new UserModel(
                R.raw.slider3,
                "Ahmad Ikhsan"
        ));
        models.add(new UserModel(
                R.raw.slider2,
                "Upin ipin"
        ));
        models.add(new UserModel(
                R.raw.slider1,
                "Tabuti"
        ));

        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false));
        recyclerView.setAdapter(new PosAdapter(models, new Adapter.OnClickListener() {
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