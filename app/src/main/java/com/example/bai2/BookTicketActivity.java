package com.example.ticket_app;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

public class BookTicketActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        int showtimeId = getIntent().getIntExtra("showtime_id", -1);
        Intent intent = new Intent(this, SeatSelectionActivity.class);
        if (showtimeId != -1) {
            intent.putExtra("showtime_id", showtimeId);
        }
        startActivity(intent);
        finish();
    }
}
