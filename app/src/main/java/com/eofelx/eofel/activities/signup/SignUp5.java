package com.eofelx.eofel.activities.signup;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.eofelx.eofel.R;
import com.eofelx.eofel.databinding.ViewSignUp5Binding;
import com.eofelx.eofel.utils.Preferences;
import com.eofelx.eofel.views.BaseViews;
import com.vass.api.Vass;
import com.vass.api.model.User;
import com.vass.api.region.Requests;

import org.jetbrains.annotations.NotNull;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class SignUp5 extends BaseViews {

    ViewSignUp5Binding binding;

    Map<String, Integer> status;

    @Nullable
    @org.jetbrains.annotations.Nullable
    @Override
    public View onCreateView(@NonNull @NotNull LayoutInflater inflater, @Nullable @org.jetbrains.annotations.Nullable ViewGroup container, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        binding = ViewSignUp5Binding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull @NotNull View view, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        status = new HashMap<>();
        allManual();
        validate();
    }

    private void validate() {
        binding.lanjut.setOnClickListener(v -> {
            JSONObject sunday = new JSONObject();
            JSONObject monday = new JSONObject();
            JSONObject tuesday = new JSONObject();
            JSONObject wednesday = new JSONObject();
            JSONObject thursday = new JSONObject();
            JSONObject friday = new JSONObject();
            JSONObject saturday = new JSONObject();
            try {
                sunday.put("open_time", binding.startFrom.getText().toString());
                sunday.put("close_time", binding.endFrom.getText().toString());
                sunday.put("is_open", status.get("sunday"));

                monday.put("open_time", binding.mondayStartFrom.getText().toString());
                monday.put("close_time", binding.mondayEndFrom.getText().toString());
                monday.put("is_open", status.get("monday"));

                tuesday.put("open_time", binding.tuesdayStartFrom.getText().toString());
                tuesday.put("close_time", binding.tuesdayStartFrom.getText().toString());
                tuesday.put("is_open", status.get("tuesday"));

                wednesday.put("open_time", binding.wednesdayStartFrom.getText().toString());
                wednesday.put("close_time", binding.wednesdayEndFrom.getText().toString());
                wednesday.put("is_open", status.get("wednesday"));

                thursday.put("open_time", binding.thursdayStartFrom.getText().toString());
                thursday.put("close_time", binding.thursdayEndFrom.getText().toString());
                thursday.put("is_open", status.get("thursday"));

                friday.put("open_time", binding.fridayStartFrom.getText().toString());
                friday.put("close_time", binding.fridayEndFrom.getText().toString());
                friday.put("is_open", status.get("friday"));

                saturday.put("open_time", binding.saturdayStartFrom.getText().toString());
                saturday.put("close_time", binding.saturdayEndFrom.getText().toString());
                saturday.put("is_open", status.get("saturday"));

            } catch (JSONException e) {
                e.printStackTrace();
            }
            JSONObject object = new JSONObject();
            System.out.println(sunday.toString());
            try {
                object.put("sunday", sunday);
                object.put("monday", monday);
                object.put("tuesday", tuesday);
                object.put("wednesday", wednesday);
                object.put("thursday", thursday);
                object.put("friday", friday);
                object.put("saturday", saturday);

                System.out.println(object.toString());
                Map<String, String>  params = new HashMap<>();
                params.put("Content-Type", "application/json; charset=utf-8");
                params.put("token", Preferences.getToken(requireContext()));
                Requests<User> requests = new Requests<>();
                requests.insert(requireContext(), Vass.CREATE_OPEN_STATUS)
                        .header(params, object)
                        .commit();

            } catch (JSONException e) {
                e.printStackTrace();
            }
        });
    }


    private void allManual() {

        binding.sunday.setOnCheckedChangeListener(((buttonView, isChecked) -> {
            binding.layoutStart.setVisibility(!isChecked ? View.GONE : View.VISIBLE);
            binding.layoutEnd.setVisibility(!isChecked ? View.GONE : View.VISIBLE);
            binding.sundayClosed.setVisibility(isChecked ? View.GONE : View.VISIBLE);
            status.put("sunday", isChecked ? 0 : 1);
        }));

        binding.monday.setOnCheckedChangeListener(((buttonView, isChecked) -> {
            binding.layoutMondayStart.setVisibility(!isChecked ? View.GONE : View.VISIBLE);
            binding.mondayLayoutEnd.setVisibility(!isChecked ? View.GONE : View.VISIBLE);
            binding.mondayClosed.setVisibility(isChecked ? View.GONE : View.VISIBLE);
            status.put("monday", isChecked ? 0 : 1);
        }));


        binding.tuesday.setOnCheckedChangeListener(((buttonView, isChecked) -> {
            binding.tuesdayLayoutStart.setVisibility(!isChecked ? View.GONE : View.VISIBLE);
            binding.tuesdayLayoutEnd.setVisibility(!isChecked ? View.GONE : View.VISIBLE);
            binding.tuesdayClosed.setVisibility(isChecked ? View.GONE : View.VISIBLE);
            status.put("tuesday", isChecked ? 0 : 1);
        }));

        binding.wednesday.setOnCheckedChangeListener(((buttonView, isChecked) -> {
            binding.wednesdayLayoutStart.setVisibility(!isChecked ? View.GONE : View.VISIBLE);
            binding.wednesdayLayoutEnd.setVisibility(!isChecked ? View.GONE : View.VISIBLE);
            binding.wednesdayClosed.setVisibility(isChecked ? View.GONE : View.VISIBLE);
            status.put("wednesday", isChecked ? 0 : 1);
        }));

        binding.thursday.setOnCheckedChangeListener(((buttonView, isChecked) -> {
            binding.thursdayLayoutStart.setVisibility(!isChecked ? View.GONE : View.VISIBLE);
            binding.thursdayLayoutEnd.setVisibility(!isChecked ? View.GONE : View.VISIBLE);
            binding.thursdayClosed.setVisibility(isChecked ? View.GONE : View.VISIBLE);
            status.put("thursday", isChecked ? 0 : 1);
        }));

        binding.friday.setOnCheckedChangeListener(((buttonView, isChecked) -> {
            binding.fridayLayoutStart.setVisibility(!isChecked ? View.GONE : View.VISIBLE);
            binding.fridayLayoutEnd.setVisibility(!isChecked ? View.GONE : View.VISIBLE);
            binding.fridayClosed.setVisibility(isChecked ? View.GONE : View.VISIBLE);
            status.put("friday", isChecked ? 0 : 1);
        }));

        binding.saturday.setOnCheckedChangeListener(((buttonView, isChecked) -> {
            binding.saturdayLayoutStart.setVisibility(!isChecked ? View.GONE : View.VISIBLE);
            binding.saturdayLayoutEnd.setVisibility(!isChecked ? View.GONE : View.VISIBLE);
            binding.saturdayClosed.setVisibility(isChecked ? View.GONE : View.VISIBLE);
            status.put("saturday", isChecked ? 0 : 1);
        }));

    }
}
