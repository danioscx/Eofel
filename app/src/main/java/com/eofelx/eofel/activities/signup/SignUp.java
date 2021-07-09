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
import androidx.fragment.app.Fragment;

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

public class SignUp extends BaseViews {

    Button button;
    TextInputEditText name, email;
    TextInputLayout names, emails;

    LinearProgressIndicator indicator;

    RequestQueue queue;

    @Nullable
    @org.jetbrains.annotations.Nullable
    @Override
    public View onCreateView(@NonNull @NotNull LayoutInflater inflater, @Nullable @org.jetbrains.annotations.Nullable ViewGroup container, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.view_sign_up, container, false);
    }

    @Override
    public void onViewCreated(@NonNull @NotNull View view, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        button = view.findViewById(R.id.lanjut);
        name = view.findViewById(R.id.nama_lengkap);
        email = view.findViewById(R.id.alamat_email);
        names = view.findViewById(R.id.layout_name);
        emails = view.findViewById(R.id.emails);
        queue = Volley.newRequestQueue(requireActivity());
        indicator = view.findViewById(R.id.request);
        name.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (count < 3) {
                    names.setError(getString(R.string.error_name));
                } else {
                    names.setError(null);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        email.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                System.out.println(s.toString().length());
                if (s.toString().length() < 3) {
                    emails.setError("Tidak boleh kurang dari 3 huruf");
                } else {
                    emails.setError(null);
                }
            }
        });
        nextView();
    }

    private void nextView() {
        button.setOnClickListener(v -> {
            if (Objects.requireNonNull(name.getText()).length() < 3 && Objects.requireNonNull(email.getText()).length() < 3) {
                names.setError(getString(R.string.error_name));
                emails.setError(getString(R.string.error_name));
            } else {
                String url = App.URL + "/app/check/email";
                JSONObject object = new JSONObject();
                try {
                    object.put("email", Objects.requireNonNull(email.getText()).toString());
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                indicator.setVisibility(View.VISIBLE);
                JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST,
                        url, object, response -> {
                    try {
                        if (response.getString("status").equals("409")) {
                            System.out.println("already exists");
                            emails.setError("Email already exists");
                            indicator.setVisibility(View.GONE);
                        } else
                            nextFragment(new SignUp1());
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                }, System.out::println) {
                    @Override
                    public Map<String, String> getHeaders() throws AuthFailureError {
                        Map<String, String> header = new HashMap<>();
                        header.put("Content-Type", "application/json; charset=utf-8");
                        return header;
                    }
                };
                queue.add(request);
            }
        });
    }

    private void nextFragment(Fragment fragment) {
        Bundle bundle = new Bundle();
        bundle.putString("name", Objects.requireNonNull(name.getText()).toString());
        bundle.putString("email", Objects.requireNonNull(email.getText()).toString());
        fragment.setArguments(bundle);
        getParentFragmentManager()
                .beginTransaction()
                .replace(R.id.next_register, fragment)
                .addToBackStack(null)
                .commit();
    }
}
