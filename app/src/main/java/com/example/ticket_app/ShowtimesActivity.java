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

public class ShowtimesActivity extends AppCompatActivity {
    private List<ShowtimeDetail> showtimeDetails;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        TextView tvTitle = findViewById(R.id.tvTitle);
        findViewById(R.id.btnBackList).setOnClickListener(v -> finish());
        ListView listView = findViewById(R.id.listView);
        tvTitle.setText("Lịch chiêu (nhấn để đặt vé)");

        showtimeDetails = AppDatabase.getInstance(this).showtimeDao().getAllWithDetails();
        List<DisplayItem> displayItems = new ArrayList<>();

        for (ShowtimeDetail detail : showtimeDetails) {
            String subtitle = "Rạp: " + detail.theaterName + "\nGiờ: " + detail.startTime
                    + "\nGiá: " + String.format(Locale.getDefault(), "%.0f VND", detail.price);
            displayItems.add(new DisplayItem(detail.movieTitle, subtitle));
        }

        listView.setAdapter(new DisplayCardAdapter(this, displayItems));
        listView.setOnItemClickListener((parent, view, position, id) -> {
            Intent intent = new Intent(this, SeatSelectionActivity.class);
            intent.putExtra("showtime_id", showtimeDetails.get(position).id);
            startActivity(intent);
        });
    }
}
