package com.example.ticket_app;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.ticket_app.data.AppDatabase;
import com.example.ticket_app.model.ShowtimeDetail;
import com.example.ticket_app.ui.DisplayCardAdapter;
import com.example.ticket_app.ui.DisplayItem;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class MovieShowtimesActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        int theaterId = getIntent().getIntExtra("theater_id", -1);
        int movieId = getIntent().getIntExtra("movie_id", -1);
        String movieTitle = getIntent().getStringExtra("movie_title");

        if (theaterId == -1 || movieId == -1) {
            finish();
            return;
        }

        TextView tvTitle = findViewById(R.id.tvTitle);
        findViewById(R.id.btnBackList).setOnClickListener(v -> finish());
        ListView listView = findViewById(R.id.listView);

        tvTitle.setText("Suat chieu: " + movieTitle);

        List<ShowtimeDetail> showtimes = AppDatabase.getInstance(this).showtimeDao().getByTheaterAndMovie(theaterId,
                movieId);
        List<DisplayItem> items = new ArrayList<>();
        for (ShowtimeDetail showtime : showtimes) {
            items.add(new DisplayItem(showtime.startTime,
                    showtime.theaterName + " | " + String.format(Locale.getDefault(), "%.0f VND", showtime.price)));
        }

        listView.setAdapter(new DisplayCardAdapter(this, items));
        listView.setOnItemClickListener((parent, view, position, id) -> {
            Intent intent = new Intent(this, SeatSelectionActivity.class);
            intent.putExtra("showtime_id", showtimes.get(position).id);
            startActivity(intent);
        });
    }
}
