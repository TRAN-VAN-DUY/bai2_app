package com.example.ticket_app;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.ticket_app.data.AppDatabase;
import com.example.ticket_app.model.Theater;
import com.example.ticket_app.ui.DisplayCardAdapter;
import com.example.ticket_app.ui.DisplayItem;

import java.util.ArrayList;
import java.util.List;

public class TheatersActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        TextView tvTitle = findViewById(R.id.tvTitle);
        findViewById(R.id.btnBackList).setOnClickListener(v -> finish());
        ListView listView = findViewById(R.id.listView);
        tvTitle.setText("Danh sách rạp (chọn phim)");

        List<Theater> theaters = AppDatabase.getInstance(this).theaterDao().getAll();
        List<DisplayItem> displayItems = new ArrayList<>();
        for (Theater theater : theaters) {
            displayItems.add(new DisplayItem(theater.name, theater.address));
        }

        listView.setAdapter(new DisplayCardAdapter(this, displayItems));
        listView.setOnItemClickListener((parent, view, position, id) -> {
            Theater theater = theaters.get(position);
            Intent intent = new Intent(this, TheaterMoviesActivity.class);
            intent.putExtra("theater_id", theater.id);
            intent.putExtra("theater_name", theater.name);
            startActivity(intent);
        });
    }
}
