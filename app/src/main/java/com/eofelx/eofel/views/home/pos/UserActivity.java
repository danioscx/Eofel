package com.eofelx.eofel.views.home.pos;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.eofelx.eofel.databinding.ActivityUserBinding;

public class UserActivity extends AppCompatActivity {

    ActivityUserBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityUserBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
    }
}