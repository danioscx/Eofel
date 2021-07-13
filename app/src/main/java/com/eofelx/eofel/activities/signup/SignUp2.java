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
import com.eofelx.eofel.utils.Preferences;
import com.eofelx.eofel.views.BaseViews;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.vass.api.Vass;
import com.vass.api.model.User;
import com.vass.api.region.Requests;

import org.jetbrains.annotations.NotNull;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;
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
        TextInputEditText toko = view.findViewById(R.id.toko);
        TextInputLayout p_editText = view.findViewById(R.id.nama_toko_layout);
        TextInputEditText description = view.findViewById(R.id.description);
        TextInputLayout p_repeat = view.findViewById(R.id.layout_description);

        toko.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (s.toString().length() < 3) {
                    p_editText.setError("Tidak boleh kurang dari 3 huruf");
                } else
                    p_editText.setError(null);
            }
        });
        description.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                if (s.toString().length() < 3) {
                    p_repeat.setError("Tidak boleh kurang dari 3 huruf");
                } else {
                    p_repeat.setError(null);
                }
            }
        });

        button.setOnClickListener(v -> {
            if (Objects.requireNonNull(toko.getText()).length() < 3 && Objects.requireNonNull(description.getText()).length() < 3) {
                p_editText.setError("Kota tidak valid");
                p_repeat.setError("Kecamatan tidak valid!");
            } else {
                JSONObject object = new JSONObject();
                try {
                    object.put("name", toko.getText().toString());
                    object.put("description", Objects.requireNonNull(description.getText()).toString());
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                Map<String, String>  params = new HashMap<>();
                params.put("Content-Type", "application/json; charset=utf-8");
                params.put("token", Preferences.getToken(requireContext()));
                Requests<User> requests = new Requests<>();
                requests.insert(requireContext(), Vass.CREATE_MERCHANT)
                        .header(params, object)
                        .commit();
                getParentFragmentManager()
                        .beginTransaction()
                        .replace(R.id.next_register, new SignUp3())
                        .addToBackStack(null)
                        .commit();
            }
        });
    }
}