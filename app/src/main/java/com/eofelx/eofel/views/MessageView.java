package com.eofelx.eofel.views;

import android.content.Intent;
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
import com.eofelx.eofel.adapters.MessageAdapter;
import com.eofelx.eofel.models.BaseModel;
import com.eofelx.eofel.models.MessageModel;
import com.eofelx.eofel.views.message.OpenMessage;
import com.google.android.material.chip.Chip;
import com.google.android.material.chip.ChipGroup;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class MessageView extends BaseViews {

    Chip kurir, mitra, pengguna;
    ChipGroup chipGroup;
    RecyclerView recyclerView;
    List<MessageModel> models;

    @Nullable
    @org.jetbrains.annotations.Nullable
    @Override
    public View onCreateView(@NonNull @NotNull LayoutInflater inflater, @Nullable @org.jetbrains.annotations.Nullable ViewGroup container, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.view_message, container, false);
    }

    @Override
    public void onViewCreated(@NonNull @NotNull View view, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        kurir = view.findViewById(R.id.message_kurir);
        mitra = view.findViewById(R.id.message_mitra);
        pengguna = view.findViewById(R.id.message_pengguna);
        chipGroup = view.findViewById(R.id.message_chip_group);
        recyclerView = view.findViewById(R.id.message_recycler_view);
        models = new ArrayList<>();

        models.add(new MessageModel(R.raw.slider1, "Ramdhani", "Hi kak apakah barang tersedia"));
        models.add(new MessageModel(R.raw.slider1, "Andrew", "Hi kak apakah barang tersedia"));
        models.add(new MessageModel(R.raw.slider1, "Jhon", "Hi kak apakah barang tersedia"));
        models.add(new MessageModel(R.raw.slider1, "William", "Hi kak apakah barang tersedia"));
        models.add(new MessageModel(R.raw.slider1, "Roger", "Hi kak apakah barang tersedia"));

        LinearLayoutManager layoutManager = new LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);
        MessageAdapter adapter = new MessageAdapter(models, new Adapter.OnClickListener() {
            @Override
            public <T extends BaseModel> void onClick(T click) {
                Bundle bundle = new Bundle();
                bundle.putString("name", ((MessageModel)click).getName());
                Intent intent = new Intent(requireContext(), OpenMessage.class);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
        recyclerView.setAdapter(adapter);

        chipGroup.setOnCheckedChangeListener((group, checkedId) -> {
            Chip chip = group.findViewById(checkedId);
            if (chip == null) {
                return;
            }
            if (Objects.requireNonNull(chip.getText()).toString().equals("Pelanggan") && chip.getText() != null) {
                if (models.size() > 0) {
                    models.clear();
                    models.add(new MessageModel(R.raw.slider1, "Ramdhani", "Hi kak apakah barang tersedia"));
                    models.add(new MessageModel(R.raw.slider1, "Andrew", "Hi kak apakah barang tersedia"));
                    models.add(new MessageModel(R.raw.slider1, "Jhon", "Hi kak apakah barang tersedia"));
                    models.add(new MessageModel(R.raw.slider1, "William", "Hi kak apakah barang tersedia"));
                    models.add(new MessageModel(R.raw.slider1, "Roger", "Hi kak apakah barang tersedia"));
                }
            }
            if (Objects.requireNonNull(chip.getText()).toString().equals("Mitra") && chip.getText() != null) {
                if (models.size() > 0) {
                    models.clear();
                    models.add(new MessageModel(R.raw.slider2, "Alex", "Hi kak apakah barang tersedia"));
                    models.add(new MessageModel(R.raw.slider2, "Natalie", "Hi kak apakah barang tersedia"));
                    models.add(new MessageModel(R.raw.slider2, "Jack", "Hi kak apakah barang tersedia"));
                    models.add(new MessageModel(R.raw.slider2, "Koala", "Hi kak apakah barang tersedia"));
                    models.add(new MessageModel(R.raw.slider2, "Sora", "Hi kak apakah barang tersedia"));
                }
            }
            if (Objects.requireNonNull(chip.getText()).toString().equals("Kurir") && chip.getText() != null) {
                if (models.size() > 0) {
                    models.clear();
                    models.add(new MessageModel(R.raw.slider3, "Siti", "Hi kak apakah barang tersedia"));
                    models.add(new MessageModel(R.raw.slider3, "Risa", "Hi kak apakah barang tersedia"));
                    models.add(new MessageModel(R.raw.slider3, "Soon", "Hi kak apakah barang tersedia"));
                    models.add(new MessageModel(R.raw.slider3, "Rush", "Hi kak apakah barang tersedia"));
                    models.add(new MessageModel(R.raw.slider3, "Golang", "Hi kak apakah barang tersedia"));
                }
            }
            chip.setOnClickListener(v -> {
                adapter.notifyDataSetChanged();
            });
        });
    }
}
