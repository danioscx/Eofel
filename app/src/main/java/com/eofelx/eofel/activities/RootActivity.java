package com.eofelx.eofel.activities;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.eofelx.eofel.R;
import com.eofelx.eofel.views.HomeViews;
import com.eofelx.eofel.views.LibraryViews;
import com.eofelx.eofel.views.PersonViews;
import com.eofelx.eofel.views.home.SearchResultViews;
import com.eofelx.eofel.views.home.WalletActivity;
import com.ferfalk.simplesearchview.SimpleSearchView;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class RootActivity extends AppCompatActivity implements
        BottomNavigationView.OnNavigationItemSelectedListener,
        SimpleSearchView.OnQueryTextListener
{

    boolean isBackPressed = false;

    SimpleSearchView searchView;

    BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_root);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        searchView = findViewById(R.id.search_view);
        searchView.setOnQueryTextListener(this);

        bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(this);
        selectViews(new HomeViews(), HomeViews.class.getSimpleName());
    }

    private void selectViews(Fragment fragment, String tag) {
        getSupportFragmentManager()
                .beginTransaction()
                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                .replace(R.id.main_fragment, fragment, tag)
                .commit();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_toolbar, menu);
        MenuItem item = menu.findItem(R.id.action_search);
        searchView.setMenuItem(item);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            getSupportFragmentManager().popBackStack();
            return true;
        }
        if (item.getItemId() == R.id.action_wallet) {
            startActivity(new Intent(getApplicationContext(), WalletActivity.class));
        } else if (item.getItemId() == R.id.action_notification) {
            startActivity(new Intent(getApplicationContext(), NotificationActivity.class));
        }
        return false;
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.home) {
            selectViews(HomeViews.getInstance(), HomeViews.class.getSimpleName());
            return true;
        } else if (item.getItemId() == R.id.messages) {
          //TODO
        } else if (item.getItemId() == R.id.add_new) {
            //TODO
        } else if (item.getItemId() == R.id.library) {
            selectViews(new LibraryViews(), LibraryViews.class.getSimpleName());
            return true;
        } else if (item.getItemId() == R.id.person) {
            selectViews(new PersonViews(), PersonViews.class.getSimpleName());
            return true;
        }
        return false;
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        Bundle bundle = new Bundle();
        bundle.putString("query", query);
        SearchResultViews resultViews = new SearchResultViews();
        resultViews.setArguments(bundle);
        getSupportFragmentManager()
                .beginTransaction()
                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                .replace(R.id.main_fragment, resultViews, SearchResultViews.class.getSimpleName())
                .addToBackStack(null)
                .commit();
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        return false;
    }

    @Override
    public boolean onQueryTextCleared() {
        return false;
    }

    @Override
    public void onBackPressed() {
        if (isBackPressed) {
            super.onBackPressed();
            return;
        }
        isBackPressed = true;

        Toast.makeText(getApplicationContext(), "Tap once again to exit's", Toast.LENGTH_SHORT).show();
        Handler handler = new Handler();
        handler.postDelayed(() -> isBackPressed = false, 1000L);
    }
}
