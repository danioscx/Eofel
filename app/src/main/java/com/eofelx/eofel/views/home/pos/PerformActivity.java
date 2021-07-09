package com.eofelx.eofel.views.home.pos;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.eofelx.eofel.databinding.ActivityPerformBinding;

public class PerformActivity extends AppCompatActivity {

    ActivityPerformBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityPerformBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
    }
}