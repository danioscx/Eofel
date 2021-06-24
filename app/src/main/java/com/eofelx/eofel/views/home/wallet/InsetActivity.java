package com.eofelx.eofel.views.home.wallet;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.eofelx.eofel.R;

public class InsetActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.wallet_insert_activity);

        overridePendingTransition(
                R.anim.enter, R.anim.exit
        );
    }
}
