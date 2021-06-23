package com.eofelx.eofel.views.home;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import com.eofelx.eofel.R;
import com.eofelx.eofel.views.home.wallet.HistoryView;

import java.util.Objects;

public class WalletViews extends AppCompatActivity implements View.OnClickListener{

    LinearLayout history, withdraw, insert;
    boolean isClicked = false;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_wallet);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(R.string.wallet);
        history = findViewById(R.id.wallet_history);
        withdraw = findViewById(R.id.wallet_withdraw);
        insert = findViewById(R.id.wallet_insert);
        history.setOnClickListener(this);
        withdraw.setOnClickListener(this);
        insert.setOnClickListener(this);

        defaultStyle(history);
        useView(new HistoryView());
    }

    private void useView(Fragment historyView) {

    }

    private void defaultStyle(LinearLayout history) {
        history.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.drawers));
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
            v.setSelected(true);
            if (v.isSelected()) {
                defaultStyle(history);
            }
        } else {
            v.setSelected(false);
            if (!v.isSelected()) {
                changeStyle(history);
            }
        }
        if (v == withdraw) {
            v.setSelected(true);
            if (v.isSelected()) {
                defaultStyle(withdraw);
            }
        } else {
            v.setSelected(false);
            if (!v.isSelected()) {
                changeStyle(withdraw);
            }
        }

        if (v == insert) {
            v.setSelected(true);
            if (v.isSelected()) {
                defaultStyle(insert);
            }
        } else {
            v.setSelected(false);
            if (!v.isSelected()) {
                changeStyle(insert);
            }
        }
    }
}
