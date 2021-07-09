package com.eofelx.eofel.views.home.pos;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.eofelx.eofel.databinding.ActivityDiscountsBinding;

public class DiscountActivity extends AppCompatActivity {

    ActivityDiscountsBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityDiscountsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
    }
}