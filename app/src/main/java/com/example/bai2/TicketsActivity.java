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
        tvTitle.setText("Ve da dat cua ban");

        List<TicketDetail> tickets = AppDatabase.getInstance(this).ticketDao()
                .getTicketDetailsByUserId(sessionManager.getUserId());

        List<DisplayItem> displayItems = new ArrayList<>();
        for (TicketDetail ticket : tickets) {
            String subtitle = "Rap: " + ticket.theaterName + "\nGio chieu: " + ticket.startTime
                    + "\nGhe: " + ticket.seatNumber + " | Gia: "
                    + String.format(Locale.getDefault(), "%.0f VND", ticket.price)
                    + "\nDat luc: " + ticket.bookedAt;
            displayItems.add(new DisplayItem(ticket.movieTitle, subtitle));
        }

        if (displayItems.isEmpty()) {
            displayItems.add(new DisplayItem("Chua co ve", "Ban chua dat ve nao."));
        }

        listView.setAdapter(new DisplayCardAdapter(this, displayItems));
    }
}
