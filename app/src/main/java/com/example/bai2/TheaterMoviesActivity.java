package com.example.ticket_app;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.ticket_app.data.AppDatabase;
import com.example.ticket_app.model.Movie;
import com.example.ticket_app.ui.DisplayCardAdapter;
import com.example.ticket_app.ui.DisplayItem;

import java.util.ArrayList;
import java.util.List;

public class TheaterMoviesActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        int theaterId = getIntent().getIntExtra("theater_id", -1);
        String theaterName = getIntent().getStringExtra("theater_name");
        if (theaterId == -1) {
            finish();
            return;
        }

        TextView tvTitle = findViewById(R.id.tvTitle);
        findViewById(R.id.btnBackList).setOnClickListener(v -> finish());
        ListView listView = findViewById(R.id.listView);

        tvTitle.setText("Phim tai " + theaterName);

        List<Movie> movies = AppDatabase.getInstance(this).showtimeDao().getMoviesByTheater(theaterId);
        List<DisplayItem> items = new ArrayList<>();
        for (Movie movie : movies) {
            items.add(
                    new DisplayItem(movie.title, "The loai: " + movie.genre + " | " + movie.durationMinutes + " phut"));
        }

        listView.setAdapter(new DisplayCardAdapter(this, items));
        listView.setOnItemClickListener((parent, view, position, id) -> {
            Movie movie = movies.get(position);
            Integer showtimeId = AppDatabase.getInstance(this).showtimeDao()
                    .getFirstShowtimeIdByTheaterAndMovie(theaterId, movie.id);
            if (showtimeId == null) {
                Toast.makeText(this, "Phim nay chua co suat chieu", Toast.LENGTH_SHORT).show();
                return;
            }

            Intent intent = new Intent(this, SeatSelectionActivity.class);
            intent.putExtra("showtime_id", showtimeId);
            startActivity(intent);
        });
    }
}
