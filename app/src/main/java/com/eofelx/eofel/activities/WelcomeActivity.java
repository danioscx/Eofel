package com.eofelx.eofel.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.widget.NestedScrollView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.eofelx.eofel.App;
import com.eofelx.eofel.R;
import com.eofelx.eofel.adapters.SliderHomeAdapter;
import com.eofelx.eofel.models.SliderItem;
import com.eofelx.eofel.utils.Preferences;
import com.google.android.material.progressindicator.LinearProgressIndicator;
import com.google.android.material.textfield.TextInputLayout;
import com.smarteist.autoimageslider.SliderView;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class WelcomeActivity extends AppCompatActivity {

    Button button;
    TextInputLayout email_layout, pass_layout;
    EditText email, pass;

    NestedScrollView scrollView;
    LinearProgressIndicator indicator;
    RequestQueue queue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        Window w = getWindow();
        w.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);

        //Validation id from xml
        queue = Volley.newRequestQueue(getApplicationContext());
        TextView signUp = findViewById(R.id.belum_punya);
        email_layout = findViewById(R.id.wm_email_ly);
        pass_layout = findViewById(R.id.wm_pass_ly);
        email = findViewById(R.id.wm_email_edit);
        pass = findViewById(R.id.wm_pass_edit);
        indicator = findViewById(R.id.indicator);
        scrollView = findViewById(R.id.layout);


        button = findViewById(R.id.validation);

        signUp.setOnClickListener(v-> startActivity(new Intent(getApplicationContext(), SignUpActivity.class)));

        SliderView sliderView = findViewById(R.id.wm_slider);
        int[] url = new int[] {
                R.raw.slider1, R.raw.slider2, R.raw.slider3, R.raw.slider4
        };
        List<SliderItem> items = new ArrayList<>();
        for (int j : url) {
            items.add(new SliderItem(j));
        }
        sliderView.setSliderAdapter(new SliderHomeAdapter(items, item -> {
        }));

        sliderView.startAutoCycle();
        dataValidation();
    }

    private void dataValidation() {
        button.setOnClickListener(v -> {
            if (Objects.requireNonNull(email.getText()).toString().length() < 3 && Objects.requireNonNull(pass.getText()).toString().length() < 3) {
                email_layout.setError("Huruf tidak boleh kurang 3 huruf!");
                pass_layout.setError("Password tidak boleh kurang dari 3 huruf atau angka!");
            } else {
                indicator.setVisibility(View.VISIBLE);
                JSONObject object = new JSONObject();
                try {
                    object.put("email", email.getText().toString());
                    object.put("password", Objects.requireNonNull(pass.getText()).toString());
                    System.out.println(pass.getText().toString());
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST,
                        App.URL + "/user/sign/in", object, response -> {
                    try {
                        String string = Integer.toString(Integer.parseInt(response.getString("status")));
                        if (response.getString("status").equals(string)) {
                            Preferences.setStatus(getApplicationContext(), true);
                            Preferences.setToken(getApplicationContext(), response.getString("token"));
                            startActivity(new Intent(WelcomeActivity.this, RootActivity.class));
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }, error -> {
                    System.out.println(error.networkResponse);
                    indicator.setVisibility(View.GONE);
                }) {
                    @Override
                    public Map<String, String> getHeaders() {
                        Map<String, String>  params = new HashMap<>();
                        params.put("Content-Type", "application/json; charset=utf-8");
                        return params;
                    }
                };
                queue.add(request);

            }
        });
    }
}