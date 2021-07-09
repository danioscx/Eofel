package com.eofelx.eofel.activities.signup;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.eofelx.eofel.App;
import com.eofelx.eofel.R;
import com.eofelx.eofel.views.BaseViews;
import com.google.android.material.progressindicator.LinearProgressIndicator;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import org.jetbrains.annotations.NotNull;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class SignUp1 extends BaseViews {

    LinearProgressIndicator indicator;
    TextInputEditText editText, address;
    TextInputLayout layoutEditText, layoutAddress;
    String name, email;
    RequestQueue queue;

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
        editText = view.findViewById(R.id.nomor_telpon);
        layoutEditText = view.findViewById(R.id.layout_nomor_telepon);
        address = view.findViewById(R.id.password);
        layoutAddress = view.findViewById(R.id.layout_alamat_lengkap);
        indicator = view.findViewById(R.id.request);
        queue = Volley.newRequestQueue(requireContext());

        assert getArguments() != null;
        name = getArguments().getString("name");
        email = getArguments().getString("email");

        //Toast.makeText(requireContext(), name + email, Toast.LENGTH_SHORT).show();

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
            }

            @Override
            public void afterTextChanged(Editable s) {
                if (s.toString().length() < 3) {
                    layoutAddress.setError("Tidak boleh kurang dari 3 huruf");
                } else {
                    layoutAddress.setError(null);
                }
            }
        });

        button.setOnClickListener(v -> {
            if (Objects.requireNonNull(editText.getText()).length() < 6 && Objects.requireNonNull(address.getText()).length() < 3) {
                layoutEditText.setError("Nomor telepon tidak valid");
                layoutAddress.setError("Tidak boleh kurang dari 3 huruf");
            } else {
                System.out.println(address.getText().toString());
                String url = App.URL + "/app/check/phone";
                JSONObject object = new JSONObject();
                try {
                    object.put(App.USER_PHONE, Objects.requireNonNull(editText.getText()).toString());
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                indicator.setVisibility(View.VISIBLE);
                //Check email
                JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST,
                        url, object, response -> {
                    try {
                        if (response.getString("status").equals("409")) {
                            System.out.println("already exists");
                            layoutEditText.setError("Phone number already exists");
                            indicator.setVisibility(View.GONE);
                        } else {
                            applyUser();
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                }, System.out::println) {
                    @Override
                    public Map<String, String> getHeaders() {
                        Map<String, String> header = new HashMap<>();
                        header.put("Content-Type", "application/json; charset=utf-8");
                        return header;
                    }
                };
                queue.add(request);
            }
        });
    }

    private void applyUser() {
        String url = App.URL + "/app/create/user";
        JSONObject object = new JSONObject();
        try {
            object.put(App.USER_NAME, name);
            object.put(App.USER_EMAIL, email);
            object.put(App.USER_PHONE, Objects.requireNonNull(editText.getText()).toString());
            object.put(App.USER_PASSWORD, Objects.requireNonNull(address.getText()).toString());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        indicator.setVisibility(View.VISIBLE);
        //Check email
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST,
                url, object, response -> {
            System.out.println("already ");

        }, System.out::println) {
            @Override
            public Map<String, String> getHeaders() {
                Map<String, String> header = new HashMap<>();
                header.put("Content-Type", "application/json; charset=utf-8");
                return header;
            }
        };
        queue.add(request);
    }
}