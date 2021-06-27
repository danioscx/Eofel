package com.eofelx.eofel.views;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.eofelx.eofel.R;
import com.eofelx.eofel.activities.SignUpActivity;
import com.eofelx.eofel.activities.WelcomeActivity;

public class PersonViews extends BaseViews {


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.view_person, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        LinearLayout layout = view.findViewById(R.id.login_or_detail);
        layout.setOnClickListener(v -> {
            //TODO session
            startActivity(new Intent(requireContext(), WelcomeActivity.class));
        });
    }
}
