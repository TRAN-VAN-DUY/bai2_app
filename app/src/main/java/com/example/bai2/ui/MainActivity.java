package com.example.ticket_app;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.ticket_app.data.SessionManager;

public class MainActivity extends AppCompatActivity {
    private SessionManager sessionManager;
    private TextView tvWelcome;
    private Button btnLoginLogout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sessionManager = new SessionManager(this);
        tvWelcome = findViewById(R.id.tvWelcome);
        btnLoginLogout = findViewById(R.id.btnLoginLogout);
        Button btnMovies = findViewById(R.id.btnMovies);
        Button btnTheaters = findViewById(R.id.btnTheaters);
        Button btnShowtimes = findViewById(R.id.btnShowtimes);
        Button btnMyTickets = findViewById(R.id.btnMyTickets);

        btnLoginLogout.setOnClickListener(v -> {
            if (sessionManager.isLoggedIn()) {
                sessionManager.logout();
                updateUi();
            } else {
                startActivity(new Intent(this, LoginActivity.class));
            }
        });

        btnMovies.setOnClickListener(v -> startActivity(new Intent(this, MoviesActivity.class)));
        btnTheaters.setOnClickListener(v -> startActivity(new Intent(this, TheatersActivity.class)));
        btnShowtimes.setOnClickListener(v -> startActivity(new Intent(this, ShowtimesActivity.class)));
        btnMyTickets.setOnClickListener(v -> {
            if (sessionManager.isLoggedIn()) {
                startActivity(new Intent(this, TicketsActivity.class));
            } else {
                Intent intent = new Intent(this, LoginActivity.class);
                intent.putExtra("redirect_tickets", true);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        updateUi();
    }

    private void updateUi() {
        if (sessionManager.isLoggedIn()) {
            tvWelcome.setText("Xin chao, " + sessionManager.getUsername());
            btnLoginLogout.setText("Dang xuat");
        } else {
            tvWelcome.setText("Ban chua dang nhap");
            btnLoginLogout.setText("Dang nhap");
        }
    }
}