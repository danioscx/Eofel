package com.eofelx.eofel.views.home.mart;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SwitchCompat;

import com.eofelx.eofel.R;
import com.google.android.material.appbar.MaterialToolbar;

public class MartEditContent extends AppCompatActivity {

    LinearLayout productImage, productDetail, productDesc;
    SwitchCompat compat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mart_edit_content);

        MaterialToolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        productImage = findViewById(R.id.image_product);
        productDetail = findViewById(R.id.detail_product_click);
        productDesc = findViewById(R.id.product_description);

        compat = findViewById(R.id.status_product);
        compat.setChecked(true);
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