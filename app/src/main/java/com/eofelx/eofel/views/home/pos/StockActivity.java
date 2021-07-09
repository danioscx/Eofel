package com.eofelx.eofel.views.home.pos;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.eofelx.eofel.databinding.ActivityStockBinding;

public class StockActivity extends AppCompatActivity {

    ActivityStockBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityStockBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
    }
}