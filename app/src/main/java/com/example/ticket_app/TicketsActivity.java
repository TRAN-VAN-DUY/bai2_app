package com.example.ticket_app;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.ticket_app.data.AppDatabase;
import com.example.ticket_app.data.SessionManager;
import com.example.ticket_app.model.TicketDetail;
import com.example.ticket_app.ui.DisplayCardAdapter;
import com.example.ticket_app.ui.DisplayItem;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class TicketsActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        SessionManager sessionManager = new SessionManager(this);
        if (!sessionManager.isLoggedIn()) {
            Intent intent = new Intent(this, LoginActivity.class);
            intent.putExtra("redirect_tickets", true);
            startActivity(intent);
            finish();
            return;
        }

        setContentView(R.layout.activity_list);

        TextView tvTitle = findViewById(R.id.tvTitle);
        findViewById(R.id.btnBackList).setOnClickListener(v -> finish());
        ListView listView = findViewById(R.id.listView);
        tvTitle.setText("Vé đã đặt của bạn");

        List<TicketDetail> tickets = AppDatabase.getInstance(this).ticketDao()
                .getTicketDetailsByUserId(sessionManager.getUserId());

        List<DisplayItem> displayItems = new ArrayList<>();
        for (TicketDetail ticket : tickets) {
            String subtitle = "Rạp: " + ticket.theaterName + "\nGiờ chiếu: " + ticket.startTime
                    + "\nGhế: " + ticket.seatNumber + " | Giá: "
                    + String.format(Locale.getDefault(), "%.0f VND", ticket.price)
                    + "\nĐặt lúc: " + ticket.bookedAt;
            displayItems.add(new DisplayItem(ticket.movieTitle, subtitle));
        }

        if (displayItems.isEmpty()) {
            displayItems.add(new DisplayItem("Chưa có vé", "Bạn chưa đặt vé nào."));
        }

        listView.setAdapter(new DisplayCardAdapter(this, displayItems));
    }
}
