package com.eofelx.eofel.activities.signup;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AutoCompleteTextView;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.eofelx.eofel.R;
import com.eofelx.eofel.utils.Preferences;
import com.eofelx.eofel.views.BaseViews;
import com.google.android.material.progressindicator.LinearProgressIndicator;
import com.google.android.material.textfield.TextInputEditText;
import com.vass.api.Vass;
import com.vass.api.model.Region;
import com.vass.api.model.User;
import com.vass.api.region.Requests;

import org.jetbrains.annotations.NotNull;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class SignUp3 extends BaseViews {

    LinearProgressIndicator progressIndicator;
    AutoCompleteTextView province, regencies, district, villages;
    TextInputEditText address, pos_code;
    Button button;
    String province_id = "";
    String regencies_id = "";
    String district_id = "";
    String villages_id = "";

    @Nullable
    @org.jetbrains.annotations.Nullable
    @Override
    public View onCreateView(@NonNull @NotNull LayoutInflater inflater, @Nullable @org.jetbrains.annotations.Nullable ViewGroup container, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.view_sign_up_3, container, false);
    }

    @Override
    public void onViewCreated(@NonNull @NotNull View view, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        progressIndicator = view.findViewById(R.id.request);
        button = view.findViewById(R.id.lanjut);
        province = view.findViewById(R.id.province);
        regencies = view.findViewById(R.id.regencies);
        district = view.findViewById(R.id.district);
        villages = view.findViewById(R.id.villages);
        address = view.findViewById(R.id.detail_edit);
        pos_code = view.findViewById(R.id.pos_code);
        Requests<Region> requests = new Requests<>();
        requests.get(requireContext(), Vass.ALL_PROVINCES).apply(province, new Requests.ItemClickListener() {
            @Override
            public void onClick(String position) {
                getRegencies(position);
                province_id = position;
            }

            @Override
            public void getText(String name) {
                province.setText(name);
            }
        });

        validate();

    }

    private void getRegencies(String position) {
        Requests<Region> requests = new Requests<>();
        requests.get(requireContext(), Vass.REGENCIES + position).apply(regencies, new Requests.ItemClickListener() {
            @Override
            public void onClick(String position) {
                getDistricts(position);
                regencies_id = position;
            }

            @Override
            public void getText(String name) {
                regencies.setText(name);
            }
        });
    }

    private void getDistricts(String position) {
        Requests<Region> requests = new Requests<>();
        requests.get(requireContext(), Vass.DISTRICT + position).apply(district, new Requests.ItemClickListener() {
            @Override
            public void onClick(String position) {
                getVillages(position);
                district_id = position;
            }

            @Override
            public void getText(String name) {
                district.setText(name);
            }
        });
    }

    private void getVillages(String position) {
        Requests<Region> requests = new Requests<>();
        requests.get(requireContext(), Vass.VILLAGES + position).apply(villages, new Requests.ItemClickListener() {
            @Override
            public void onClick(String position) {
                villages_id = position;
            }

            @Override
            public void getText(String name) {
                villages.setText(name);
            }
        });
    }

    private void validate() {

        button.setOnClickListener(v -> {
            progressIndicator.setVisibility(View.VISIBLE);
            JSONObject object = new JSONObject();
            try {
                object.put("address", Objects.requireNonNull(address.getText()).toString());
                object.put("pos_code", Objects.requireNonNull(pos_code.getText()).toString());
                object.put("province", province_id);
                object.put("regency", regencies_id);
                object.put("district", district_id);
                object.put("villages", villages_id);
            } catch (JSONException e) {
                e.printStackTrace();
            }

            System.out.println(object.toString());

            Map<String, String> params = new HashMap<>();
            params.put("Content-Type", "application/json; charset=utf-8");
            params.put("token", Preferences.getToken(requireContext()));
            Requests<User> requests = new Requests<>();
            requests.insert(requireContext(), Vass.CREATE_MERCHANT_INFO)
                    .header(params, object)
                    .commit();
            requireFragment(new SignUp5(), R.id.next_register);
        });

    }
}