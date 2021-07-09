package com.eofelx.eofel.activities.signup;

import android.content.Context;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.AutoCompleteTextView;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.eofelx.eofel.R;
import com.eofelx.eofel.views.BaseViews;
import com.google.android.material.progressindicator.LinearProgressIndicator;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import org.jetbrains.annotations.NotNull;

import java.util.Objects;

public class SignUp3 extends BaseViews {

    LinearProgressIndicator progressIndicator;

    @Nullable
    @org.jetbrains.annotations.Nullable
    @Override
    public View onCreateView(@NonNull @NotNull LayoutInflater inflater, @Nullable @org.jetbrains.annotations.Nullable ViewGroup container, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.view_sign_up_3, container, false);
    }

    @Override
    public void onViewCreated(@NonNull @NotNull View view, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Button button = view.findViewById(R.id.lanjut);
        TextInputEditText editText = view.findViewById(R.id.password);
        AutoCompleteTextView p_editText = view.findViewById(R.id.province);
        p_editText.setFocusable(true);
        /*TextInputEditText repeat = view.findViewById(R.id.password_repeat);
        TextInputLayout p_repeat = view.findViewById(R.id.password_repeat_lay);
        progressIndicator = view.findViewById(R.id.request);
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.toString().length() < 6) {
                    p_editText.setError("Password tidak boleh kurang dari 6 huruf");
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
                if (!s.toString().equals(Objects.requireNonNull(editText.getText()).toString())) {
                    p_repeat.setError("Password harus sama!");
                } else {
                    p_repeat.setError(null);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        button.setOnClickListener(v -> {
            if (Objects.requireNonNull(editText.getText()).length() < 6 && !Objects.requireNonNull(repeat.getText()).toString().equals(editText.getText().toString())) {
                p_editText.setError("Password tidak boleh kurang dari 6 huruf!");
                p_repeat.setError("Password tidak sama!");
            } else {
                progressIndicator.setVisibility(View.VISIBLE);
                getParentFragmentManager()
                        .beginTransaction()
                        .replace(R.id.next_register, new SignUp4())
                        .addToBackStack(null)
                        .commit();
            }
        });*/
    }
}