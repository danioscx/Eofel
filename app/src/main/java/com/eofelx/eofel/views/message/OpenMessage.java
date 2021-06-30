package com.eofelx.eofel.views.message;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.eofelx.eofel.R;
import com.eofelx.eofel.adapters.Adapter;
import com.eofelx.eofel.adapters.OpenMessageAdapter;
import com.eofelx.eofel.models.BaseModel;
import com.eofelx.eofel.models.MessageModelData;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class OpenMessage extends AppCompatActivity {

    RecyclerView recyclerView;
    List<MessageModelData> data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_open_message);
        ImageView back = findViewById(R.id.back_from_message);
        back.setOnClickListener(v -> finish());

        recyclerView = findViewById(R.id.recycler_view_content_message);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false);
        layoutManager.setStackFromEnd(true);
        recyclerView.setLayoutManager(layoutManager);


        TextView title = findViewById(R.id.title_message);
        title.setText(getIntent().getExtras().getString("name"));

        data = new ArrayList<>();

        data.add(new MessageModelData(1, "10:12", "Barang Ready bang?"));
        data.add(new MessageModelData(0, "10:13", "Bisa kami bantu kak? semua barang ready ya ka?"));
        data.add(new MessageModelData(1, "10:14", "Mau pesen 10 bisa ka? tapi di diskon ya"));
        data.add(new MessageModelData(0, "10:15", "Baik ka kami ada diskon kakak bisa gunakan langsung"));
        data.add(new MessageModelData(1, "10:16", "Langsung saya order"));
        data.add(new MessageModelData(0, "10:17", "Terima kasih ka"));

        OpenMessageAdapter adapter = new OpenMessageAdapter(data, new Adapter.OnClickListener() {
            @Override
            public <T extends BaseModel> void onClick(T click) {

            }
        });
        recyclerView.setAdapter(adapter);

        ImageButton button = findViewById(R.id.kirim_pesan);
        EditText editText = findViewById(R.id.text_pesan);
        button.setOnClickListener(v -> {
            if (editText.getText().toString().length() == 0) {
                return;
            } else {
                @SuppressLint("SimpleDateFormat") DateFormat dateFormat = new SimpleDateFormat("h:mm a");
                String date = dateFormat.format(Calendar.getInstance().getTime());
                data.add(new MessageModelData(0, date, editText.getText().toString()));
                adapter.notifyDataSetChanged();
                editText.getText().clear();
                recyclerView.scrollToPosition(data.size() - 1);

            }
        });
    }
}