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

public class SignUp1 extends BaseViews {

    @Nullable
    @org.jetbrains.annotations.Nullable
    @Override
    public View onCreateView(@NonNull @NotNull LayoutInflater inflater, @Nullable @org.jetbrains.annotations.Nullable ViewGroup container, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.view_sign_up_1, container, false);
    }

    @Override
    public void onViewCreated(@NonNull @NotNull View view, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Button button = view.findViewById(R.id.lanjut);
        TextInputEditText editText = view.findViewById(R.id.nomor_telpon);
        TextInputLayout layoutEditText = view.findViewById(R.id.layout_nomor_telepon);
        TextInputEditText address = view.findViewById(R.id.alamat_lengkap);
        TextInputLayout layoutAddress = view.findViewById(R.id.layout_alamat_lengkap);

        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.toString().length() < 6) {
                    layoutEditText.setError("Nomor telepon tidak valid");
                } else {
                    layoutEditText.setError(null);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        address.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (count < 3) {
                    layoutAddress.setError("Tidak boleh kurang dari 3 huruf");
                } else {
                    layoutAddress.setError(null);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        button.setOnClickListener(v -> {
            if (Objects.requireNonNull(editText.getText()).length() < 6 && Objects.requireNonNull(address.getText()).length() < 3) {
                layoutEditText.setError("Nomor telepon tidak valid");
                layoutAddress.setError("Tidak boleh kurang dari 3 huruf");
            } else {
                getParentFragmentManager().beginTransaction()
                        .replace(R.id.next_register, new SignUp2())
                        .addToBackStack(null)
                        .commit();
            }
        });
    }
}