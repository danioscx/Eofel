package com.eofelx.eofel.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.eofelx.eofel.R;
import com.eofelx.eofel.utils.Preferences;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Handler handler = new Handler();
        handler.postDelayed(() -> {
            if (Preferences.getStatus(getApplicationContext())) {
                startActivity(new Intent(MainActivity.this, RootActivity.class));
            } else {
                startActivity(new Intent(MainActivity.this, WelcomeActivity.class));
            }
            finish();
        }, 1000L);
    }
}