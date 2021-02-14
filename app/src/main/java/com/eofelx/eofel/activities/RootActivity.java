package com.eofelx.eofel.activities;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.eofelx.engine.RestApi;
import com.eofelx.engine.models.Posts;
import com.eofelx.engine.utils.RequestPost;
import com.eofelx.eofel.R;
import com.eofelx.eofel.views.HomeViews;
import com.eofelx.eofel.views.LibraryViews;
import com.eofelx.eofel.views.PersonViews;
import com.eofelx.eofel.views.home.SearchResultViews;
import com.ferfalk.simplesearchview.SimpleSearchView;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.List;

public class RootActivity extends AppCompatActivity implements
        BottomNavigationView.OnNavigationItemSelectedListener,
        SimpleSearchView.OnQueryTextListener {

    SimpleSearchView searchView;

    RestApi api;
    RequestPost post;

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


        api = new RestApi(this);
        post = new RequestPost();
        post.getPosts(new RequestPost.OnRequestResponseModel() {
            @Override
            public void onResponse(List<Posts> posts) {
                System.out.println(posts.get(0).getId());
            }
        });

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
        return false;
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.home) {
            selectViews(new HomeViews(), HomeViews.class.getSimpleName());
            return true;
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
}
