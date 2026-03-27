package com.example.ticket_app;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.ticket_app.data.AppDatabase;
import com.example.ticket_app.model.Movie;
import com.example.ticket_app.ui.DisplayCardAdapter;
import com.example.ticket_app.ui.DisplayItem;

import java.util.ArrayList;
import java.util.List;

public class MoviesActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        TextView tvTitle = findViewById(R.id.tvTitle);
        findViewById(R.id.btnBackList).setOnClickListener(v -> finish());
        ListView listView = findViewById(R.id.listView);
        tvTitle.setText("Danh sach phim (chon rap)");

        List<Movie> movies = AppDatabase.getInstance(this).movieDao().getAll();
        List<DisplayItem> displayItems = new ArrayList<>();
        for (Movie movie : movies) {
            displayItems.add(new DisplayItem(movie.title,
                    "The loai: " + movie.genre + " | " + movie.durationMinutes + " phut\n" + movie.description));
        }

        listView.setAdapter(new DisplayCardAdapter(this, displayItems));
        listView.setOnItemClickListener((parent, view, position, id) -> {
            Movie movie = movies.get(position);
            Intent intent = new Intent(this, MovieTheatersActivity.class);
            intent.putExtra("movie_id", movie.id);
            intent.putExtra("movie_title", movie.title);
            startActivity(intent);
        });
    }
}
