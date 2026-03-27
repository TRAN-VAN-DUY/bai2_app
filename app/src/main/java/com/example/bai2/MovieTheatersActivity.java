package com.example.ticket_app;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.ticket_app.data.AppDatabase;
import com.example.ticket_app.model.Theater;
import com.example.ticket_app.ui.DisplayCardAdapter;
import com.example.ticket_app.ui.DisplayItem;

import java.util.ArrayList;
import java.util.List;

public class MovieTheatersActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        int movieId = getIntent().getIntExtra("movie_id", -1);
        String movieTitle = getIntent().getStringExtra("movie_title");
        if (movieId == -1) {
            finish();
            return;
        }

        TextView tvTitle = findViewById(R.id.tvTitle);
        findViewById(R.id.btnBackList).setOnClickListener(v -> finish());
        ListView listView = findViewById(R.id.listView);

        tvTitle.setText("Rap chieu: " + movieTitle);

        List<Theater> theaters = AppDatabase.getInstance(this).showtimeDao().getTheatersByMovie(movieId);
        List<DisplayItem> items = new ArrayList<>();
        for (Theater theater : theaters) {
            items.add(new DisplayItem(theater.name, theater.address));
        }

        listView.setAdapter(new DisplayCardAdapter(this, items));
        listView.setOnItemClickListener((parent, view, position, id) -> {
            Theater theater = theaters.get(position);
            Integer showtimeId = AppDatabase.getInstance(this).showtimeDao()
                    .getFirstShowtimeIdByTheaterAndMovie(theater.id, movieId);
            if (showtimeId == null) {
                Toast.makeText(this, "Rap nay chua co suat chieu", Toast.LENGTH_SHORT).show();
                return;
            }

            Intent intent = new Intent(this, SeatSelectionActivity.class);
            intent.putExtra("showtime_id", showtimeId);
            startActivity(intent);
        });
    }
}
