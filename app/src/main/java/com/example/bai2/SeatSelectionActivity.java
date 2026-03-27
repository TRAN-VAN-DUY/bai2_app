package com.example.ticket_app;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.ticket_app.data.AppDatabase;
import com.example.ticket_app.data.SessionManager;
import com.example.ticket_app.model.ShowtimeDetail;
import com.example.ticket_app.model.Ticket;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;

public class SeatSelectionActivity extends AppCompatActivity {
    private final Map<String, Button> seatButtons = new HashMap<>();
    private final Set<String> bookedSeats = new HashSet<>();
    private final Set<String> selectedSeats = new HashSet<>();

    private int showtimeId;
    private SessionManager sessionManager;
    private AppDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        showtimeId = getIntent().getIntExtra("showtime_id", -1);
        if (showtimeId == -1) {
            finish();
            return;
        }

        sessionManager = new SessionManager(this);
        if (!sessionManager.isLoggedIn()) {
            Intent intent = new Intent(this, LoginActivity.class);
            intent.putExtra("showtime_id", showtimeId);
            startActivity(intent);
            finish();
            return;
        }

        setContentView(R.layout.activity_seat_selection);
        findViewById(R.id.btnBackSeat).setOnClickListener(v -> finish());

        TextView tvInfo = findViewById(R.id.tvSeatShowtimeInfo);
        TextView tvSelected = findViewById(R.id.tvSeatSelected);
        GridLayout gridSeats = findViewById(R.id.gridSeats);
        Button btnConfirm = findViewById(R.id.btnConfirmSeatBooking);

        db = AppDatabase.getInstance(this);
        ShowtimeDetail detail = db.showtimeDao().getByIdWithDetails(showtimeId);
        if (detail == null) {
            finish();
            return;
        }

        tvInfo.setText(detail.movieTitle + "\nRap: " + detail.theaterName + "\nGio: " + detail.startTime +
                "\nGia: " + String.format(Locale.getDefault(), "%.0f VND", detail.price));

        bookedSeats.addAll(db.ticketDao().getBookedSeatsByShowtime(showtimeId));
        createSeatGrid(gridSeats, tvSelected);

        btnConfirm.setOnClickListener(v -> {
            if (selectedSeats.isEmpty()) {
                Toast.makeText(this, "Vui long chon ghe", Toast.LENGTH_SHORT).show();
                return;
            }

            List<String> unavailableSeats = new ArrayList<>();
            for (String seat : selectedSeats) {
                if (db.ticketDao().countSeatForShowtime(showtimeId, seat) > 0) {
                    unavailableSeats.add(seat);
                }
            }

            if (!unavailableSeats.isEmpty()) {
                Toast.makeText(this, "Mot so ghe da duoc dat: " + formatSeats(unavailableSeats), Toast.LENGTH_SHORT)
                        .show();
                bookedSeats.clear();
                bookedSeats.addAll(db.ticketDao().getBookedSeatsByShowtime(showtimeId));
                selectedSeats.removeAll(unavailableSeats);
                updateSelectedText(tvSelected);
                refreshSeatColors();
                return;
            }

            String bookedAt = new SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.getDefault()).format(new Date());
            for (String seat : selectedSeats) {
                Ticket ticket = new Ticket();
                ticket.userId = sessionManager.getUserId();
                ticket.showtimeId = showtimeId;
                ticket.seatNumber = seat;
                ticket.bookedAt = bookedAt;
                db.ticketDao().insert(ticket);
            }

            Toast.makeText(this, "Dat " + selectedSeats.size() + " ve thanh cong", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(this, TicketsActivity.class));
            finish();
        });
    }

    private void createSeatGrid(GridLayout gridSeats, TextView tvSelected) {
        char[] rows = { 'A', 'B', 'C', 'D', 'E' };
        int cols = 6;

        for (char row : rows) {
            for (int col = 1; col <= cols; col++) {
                String seatCode = row + String.valueOf(col);
                Button seatButton = new Button(this);
                seatButton.setText(seatCode);
                seatButton.setTextColor(getColor(android.R.color.white));
                seatButton.setAllCaps(false);
                seatButton.setPadding(0, 0, 0, 0);

                GridLayout.LayoutParams params = new GridLayout.LayoutParams();
                params.width = 0;
                params.height = GridLayout.LayoutParams.WRAP_CONTENT;
                params.columnSpec = GridLayout.spec(GridLayout.UNDEFINED, 1f);
                params.setMargins(6, 6, 6, 6);
                seatButton.setLayoutParams(params);

                seatButtons.put(seatCode, seatButton);
                gridSeats.addView(seatButton);

                if (bookedSeats.contains(seatCode)) {
                    seatButton.setEnabled(false);
                } else {
                    seatButton.setOnClickListener(v -> {
                        if (selectedSeats.contains(seatCode)) {
                            selectedSeats.remove(seatCode);
                        } else {
                            selectedSeats.add(seatCode);
                        }
                        updateSelectedText(tvSelected);
                        refreshSeatColors();
                    });
                }
            }
        }

        updateSelectedText(tvSelected);
        refreshSeatColors();
    }

    private void refreshSeatColors() {
        for (Map.Entry<String, Button> entry : seatButtons.entrySet()) {
            String seat = entry.getKey();
            Button button = entry.getValue();

            if (bookedSeats.contains(seat)) {
                button.setEnabled(false);
                button.setBackgroundResource(R.drawable.bg_seat_booked);
            } else if (selectedSeats.contains(seat)) {
                button.setEnabled(true);
                button.setBackgroundResource(R.drawable.bg_seat_booked);
            } else {
                button.setEnabled(true);
                button.setBackgroundResource(R.drawable.bg_seat_available);
            }
        }
    }

    private void updateSelectedText(TextView tvSelected) {
        if (selectedSeats.isEmpty()) {
            tvSelected.setText("Ghe dang chon: Chua chon");
            return;
        }
        tvSelected.setText("Ghe dang chon: " + formatSeats(new ArrayList<>(selectedSeats)));
    }

    private String formatSeats(List<String> seats) {
        Collections.sort(seats);
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < seats.size(); i++) {
            if (i > 0) {
                builder.append(", ");
            }
            builder.append(seats.get(i));
        }
        return builder.toString();
    }
}
