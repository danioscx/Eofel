package com.eofelx.eofel.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import com.eofelx.eofel.R;
import com.eofelx.eofel.adapters.SliderHomeAdapter;
import com.eofelx.eofel.models.SliderItem;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.smarteist.autoimageslider.SliderView;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class WelcomeActivity extends AppCompatActivity {

    Button button;
    TextInputLayout email_layout, pass_layout;
    TextInputEditText email, pass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        Window w = getWindow();
        w.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);

        //Validation id from xml
        TextView signUp = findViewById(R.id.belum_punya);
        email_layout = findViewById(R.id.wm_email_ly);
        pass_layout = findViewById(R.id.wm_pass_ly);
        email = findViewById(R.id.wm_email_edit);
        pass = findViewById(R.id.wm_pass_edit);

        button = findViewById(R.id.validation);

        signUp.setOnClickListener(v-> {
            startActivity(new Intent(getApplicationContext(), SignUpActivity.class));
        });

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
                //TODO implementation
            }
        });
    }
}