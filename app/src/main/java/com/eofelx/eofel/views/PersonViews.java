package com.eofelx.eofel.views;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.eofelx.eofel.R;
import com.eofelx.eofel.activities.SignUpActivity;
import com.eofelx.eofel.activities.WelcomeActivity;
import com.eofelx.eofel.databinding.ViewPersonBinding;
import com.eofelx.eofel.utils.Preferences;

public class PersonViews extends BaseViews {

    ViewPersonBinding binding;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = ViewPersonBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if (Preferences.getStatus(requireContext())) {
            binding.exit.setVisibility(View.VISIBLE);
            binding.exit.setOnClickListener(v -> {
                Preferences.clear(requireContext());
                startActivity(new Intent(requireContext(), WelcomeActivity.class));
            });
        } else {
            binding.exit.setVisibility(View.GONE);
            binding.user.setOnClickListener(v -> startActivity(new Intent(requireActivity(), WelcomeActivity.class)));
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        binding = null;
    }
}
