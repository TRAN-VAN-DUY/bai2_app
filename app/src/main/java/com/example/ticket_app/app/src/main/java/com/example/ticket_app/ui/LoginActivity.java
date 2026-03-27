package com.example.ticket_app;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.ticket_app.data.AppDatabase;
import com.example.ticket_app.data.SessionManager;
import com.example.ticket_app.model.User;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        EditText edtUsername = findViewById(R.id.edtUsername);
        EditText edtPassword = findViewById(R.id.edtPassword);
        Button btnLogin = findViewById(R.id.btnLogin);
        Button btnCancel = findViewById(R.id.btnCancel);
        ImageButton btnBackLogin = findViewById(R.id.btnBackLogin);

        AppDatabase db = AppDatabase.getInstance(this);
        SessionManager sessionManager = new SessionManager(this);

        btnBackLogin.setOnClickListener(v -> finish());
        btnCancel.setOnClickListener(v -> finish());

        btnLogin.setOnClickListener(v -> {
            String username = edtUsername.getText().toString().trim();
            String password = edtPassword.getText().toString().trim();

            if (TextUtils.isEmpty(username) || TextUtils.isEmpty(password)) {
                Toast.makeText(this, "Vui long nhap tai khoan va mat khau", Toast.LENGTH_SHORT).show();
                return;
            }

            User user = db.userDao().login(username, password);
            if (user == null) {
                Toast.makeText(this, "Sai thong tin dang nhap", Toast.LENGTH_SHORT).show();
                return;
            }

            sessionManager.saveLogin(user.id, user.username);
            Toast.makeText(this, "Dang nhap thanh cong", Toast.LENGTH_SHORT).show();

            int showtimeId = getIntent().getIntExtra("showtime_id", -1);
            boolean redirectTickets = getIntent().getBooleanExtra("redirect_tickets", false);

            Intent nextIntent;
            if (showtimeId != -1) {
                nextIntent = new Intent(this, SeatSelectionActivity.class);
                nextIntent.putExtra("showtime_id", showtimeId);
            } else if (redirectTickets) {
                nextIntent = new Intent(this, TicketsActivity.class);
            } else {
                nextIntent = new Intent(this, MainActivity.class);
            }

            nextIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(nextIntent);
            finish();
        });
    }
}
