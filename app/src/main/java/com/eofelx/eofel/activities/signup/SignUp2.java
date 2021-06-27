package com.eofelx.eofel.activities.signup;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.eofelx.eofel.R;
import com.eofelx.eofel.views.BaseViews;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import org.jetbrains.annotations.NotNull;

import java.util.Objects;

public class SignUp2 extends BaseViews {


    @Nullable
    @org.jetbrains.annotations.Nullable
    @Override
    public View onCreateView(@NonNull @NotNull LayoutInflater inflater, @Nullable @org.jetbrains.annotations.Nullable ViewGroup container, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.view_sign_up_2, container, false);
    }

    @Override
    public void onViewCreated(@NonNull @NotNull View view, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Button button = view.findViewById(R.id.lanjut);
        TextInputEditText editText = view.findViewById(R.id.kota);
        TextInputLayout p_editText = view.findViewById(R.id.kota_layout);
        TextInputEditText repeat = view.findViewById(R.id.kecamatan);
        TextInputLayout p_repeat = view.findViewById(R.id.layout_kecamatan);

        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (count < 3) {
                    p_editText.setError("Tidak boleh kurang dari 3 huruf");
                } else
                    p_editText.setError(null);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        repeat.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (count < 3) {
                    p_repeat.setError("Tidak boleh kurang dari 3 huruf");
                } else {
                    p_repeat.setError(null);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        button.setOnClickListener(v -> {
            if (Objects.requireNonNull(editText.getText()).length() < 3 && Objects.requireNonNull(repeat.getText()).length() < 3) {
                p_editText.setError("Kota tidak valid");
                p_repeat.setError("Kecamatan tidak valid!");
            } else {
                getParentFragmentManager()
                        .beginTransaction()
                        .replace(R.id.next_register, new SignUp3())
                        .addToBackStack(null)
                        .commit();
            }
        });
    }
}