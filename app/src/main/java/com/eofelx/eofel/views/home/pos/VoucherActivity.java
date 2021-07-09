package com.eofelx.eofel.views.home.pos;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.eofelx.eofel.databinding.ActivityVoucherBinding;

public class VoucherActivity extends AppCompatActivity {

    ActivityVoucherBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityVoucherBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
    }
}