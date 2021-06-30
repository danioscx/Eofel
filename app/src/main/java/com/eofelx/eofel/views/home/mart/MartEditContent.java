package com.eofelx.eofel.views.home.mart;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.InputFilter;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import com.eofelx.eofel.R;
import com.eofelx.eofel.adapters.SliderHomeAdapter;
import com.eofelx.eofel.models.SliderItem;
import com.smarteist.autoimageslider.SliderView;

import java.util.ArrayList;
import java.util.List;

public class MartEditContent extends AppCompatActivity {
    int maxLength = 150;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mart_edit_content);

        TextView baca = findViewById(R.id.baca_selengkapnya);
        TextView desc = findViewById(R.id.deskripsi);

        String text = getResources().getString(R.string.special_title);
        desc.setText(text.substring(0, maxLength));

        baca.setOnClickListener(v -> {
            desc.setText(text);
        });

        /*SliderView sliderView = findViewById(R.id.slider_edit);
        int[] url = new int[] {
                R.raw.slider1, R.raw.slider2, R.raw.slider3, R.raw.slider4
        };
        List<SliderItem> items = new ArrayList<>();
        for (int j : url) {
            items.add(new SliderItem(j));
        }
        sliderView.setSliderAdapter(new SliderHomeAdapter(items, item -> {
        }));*/
    }
}