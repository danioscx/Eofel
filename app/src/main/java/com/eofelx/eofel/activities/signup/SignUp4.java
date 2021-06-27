package com.eofelx.eofel.activities.signup;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;

import com.eofelx.eofel.R;
import com.eofelx.eofel.views.BaseViews;

import org.jetbrains.annotations.NotNull;

public class SignUp4 extends BaseViews implements View.OnClickListener {


    TextView food, mart;
    Button button;

    @Nullable
    @org.jetbrains.annotations.Nullable
    @Override
    public View onCreateView(@NonNull @NotNull LayoutInflater inflater, @Nullable @org.jetbrains.annotations.Nullable ViewGroup container, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.view_sign_up_4, container, false);
    }

    @Override
    public void onViewCreated(@NonNull @NotNull View view, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        button = view.findViewById(R.id.lanjut);
        food = view.findViewById(R.id.food);
        mart = view.findViewById(R.id.mart);

        button.setOnClickListener(this);
        food.setOnClickListener(this);
        mart.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v == button) {
            if (!food.isSelected() && !mart.isSelected()) {
                Toast.makeText(requireContext(), "Pilih salah satu!", Toast.LENGTH_SHORT).show();
            }
            boolean selected = food.isSelected() == mart.isSelected();
        }
        if (v == food) {
            food.setSelected(true);
            mart.setSelected(false);
            food.setBackground(ContextCompat.getDrawable(requireContext(), R.drawable.ic_rectangle_18));
            food.setTextColor(Color.WHITE);
            mart.setBackground(null);
            mart.setTextColor(Color.BLACK);
        }
        if (v == mart) {
            mart.setSelected(true);
            food.setSelected(false);
            mart.setBackground(ContextCompat.getDrawable(requireContext(), R.drawable.ic_rectangle_18));
            mart.setTextColor(Color.WHITE);
            food.setBackground(null);
            food.setTextColor(Color.BLACK);
        }
    }
}
