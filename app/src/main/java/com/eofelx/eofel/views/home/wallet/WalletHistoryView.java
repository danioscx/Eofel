package com.eofelx.eofel.views.home.wallet;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.eofelx.eofel.R;
import com.eofelx.eofel.adapters.Adapter;
import com.eofelx.eofel.adapters.WalletAdapter;
import com.eofelx.eofel.models.BaseModel;
import com.eofelx.eofel.models.WalletModel;
import com.eofelx.eofel.views.BaseViews;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class WalletHistoryView extends BaseViews {


    RecyclerView recyclerView;

    @Nullable
    @org.jetbrains.annotations.Nullable
    @Override
    public View onCreateView(@NonNull @NotNull LayoutInflater inflater, @Nullable @org.jetbrains.annotations.Nullable ViewGroup container, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.wallet_history_view, container, false);
    }

    @Override
    public void onViewCreated(@NonNull @NotNull View view, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerView = view.findViewById(R.id.wallet_history_recycler);
        LinearLayoutManager manager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(manager);
        List<WalletModel> models = new ArrayList<>();
        models.add(new WalletModel("12/01/2020", "Rp 1.000.000", "success"));
        models.add(new WalletModel("11/01/2020", "Rp 1.000.000", "success"));
        models.add(new WalletModel("12/03/2020", "Rp 1.000.000", "error"));
        models.add(new WalletModel("12/04/2020", "Rp 1.000.000", "error"));
        models.add(new WalletModel("12/05/2020", "Rp 1.000.000", "success"));
        models.add(new WalletModel("12/06/2020", "Rp 1.000.000", "error"));
        models.add(new WalletModel("12/05/2020", "Rp 1.000.000", "success"));
        models.add(new WalletModel("12/05/2020", "Rp 1.000.000", "success"));
        models.add(new WalletModel("12/05/2020", "Rp 1.000.000", "success"));
        models.add(new WalletModel("12/05/2020", "Rp 1.000.000", "success"));

        recyclerView.setAdapter(new WalletAdapter(models, new Adapter.OnClickListener() {
            @Override
            public <T extends BaseModel> void onClick(T click) {

            }
        }));

    }
}
