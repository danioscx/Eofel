package com.eofelx.eofel.views.home;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.eofelx.eofel.R;
import com.eofelx.eofel.views.home.wallet.InsetActivity;
import com.eofelx.eofel.views.home.wallet.WalletHistoryView;
import com.google.android.material.bottomsheet.BottomSheetBehavior;

import org.jetbrains.annotations.NotNull;

import java.util.Objects;

public class WalletActivity extends AppCompatActivity implements View.OnClickListener{

    LinearLayout history, withdraw, insert;
    BottomSheetBehavior<LinearLayout> behavior;
    LinearLayout bottomSheet;
    CoordinatorLayout layout;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.wallet_activity);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Saldo");
        history = findViewById(R.id.wallet_history);
        withdraw = findViewById(R.id.wallet_withdraw);
        insert = findViewById(R.id.wallet_insert);
        history.setOnClickListener(this);
        withdraw.setOnClickListener(this);
        insert.setOnClickListener(this);

        bottomSheet= findViewById(R.id.bottom_sheet);
        behavior = BottomSheetBehavior.from(bottomSheet);
        layout = findViewById(R.id.parent_x);
        layout.setVisibility(View.GONE);
        behavior.addBottomSheetCallback(new BottomSheetBehavior.BottomSheetCallback() {
            @Override
            public void onStateChanged(@NonNull @NotNull View bottomSheet, int newState) {
                if (newState == BottomSheetBehavior.STATE_HIDDEN) {
                    layout.setVisibility(View.GONE);
                }
            }

            @Override
            public void onSlide(@NonNull @NotNull View bottomSheet, float slideOffset) {

            }
        });
        behavior.setState(BottomSheetBehavior.STATE_HIDDEN);
        layout.setOnClickListener(v -> {
            behavior.setState(BottomSheetBehavior.STATE_HIDDEN);
            Handler handler = new Handler();
            handler.postDelayed(() -> layout.setVisibility(View.GONE), 500L);
        });
        overridePendingTransition(
                R.anim.enter, R.anim.exit
        );

        defaultStyle(history);
        useView(new WalletHistoryView());
    }

    private void useView(Fragment historyView) {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.frag_content, historyView)
                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                .commit();
    }

    private void defaultStyle(LinearLayout history) {
        history.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.drawers_green));
    }

    private void changeStyle(LinearLayout layout) {
        layout.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.wallet));
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
            return true;
        }
        return false;
    }

    @Override
    public void onClick(View v) {
        if (v == history) {
            behavior.setState(BottomSheetBehavior.STATE_HIDDEN);
            v.setSelected(true);
            if (v.isSelected()) {
                defaultStyle(history);
            }
        }
        if (v == withdraw) {
            layout.setVisibility(View.VISIBLE);
            behavior.setState(BottomSheetBehavior.STATE_EXPANDED);
        }
        if (v == insert) {
            startActivity(new Intent(getApplicationContext(), InsetActivity.class));
        }
    }

    @Override
    public void onBackPressed() {
        if (behavior.getState() == BottomSheetBehavior.STATE_EXPANDED) {
            behavior.setState(BottomSheetBehavior.STATE_HIDDEN);
        } else {
            super.onBackPressed();
        }
    }
}
