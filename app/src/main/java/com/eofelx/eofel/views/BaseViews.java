package com.eofelx.eofel.views;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

public abstract class BaseViews extends Fragment {

    public interface OnBackPress {
        void backPress();
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
    }

    public void requireFragment(Fragment fragment, int resourceId) {
            getParentFragmentManager()
                .beginTransaction()
                .replace(resourceId, fragment)
                .addToBackStack(null)
                .commit();
    }

    public void requireFragment(Fragment fragment, int resourceId, Bundle bundle) {
        fragment.setArguments(bundle);
        getParentFragmentManager()
                .beginTransaction()
                .replace(resourceId, fragment)
                .addToBackStack(null)
                .commit();
    }
}
