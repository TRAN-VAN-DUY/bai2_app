package com.example.ticket_app.data;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.example.ticket_app.model.Movie;
import com.example.ticket_app.model.Showtime;
import com.example.ticket_app.model.Theater;
import com.example.ticket_app.model.Ticket;
import com.example.ticket_app.model.User;

@Database(entities = { User.class, Movie.class, Theater.class, Showtime.class,
        Ticket.class }, version = 1, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {
    private static volatile AppDatabase INSTANCE;

    public abstract UserDao userDao();

    public abstract MovieDao movieDao();

    public abstract TheaterDao theaterDao();

    public abstract ShowtimeDao showtimeDao();

    public abstract TicketDao ticketDao();

    public static AppDatabase getInstance(Context context) {
        if (INSTANCE == null) {
            synchronized (AppDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(), AppDatabase.class, "ticket_app_db")
                            .allowMainThreadQueries()
                            .addCallback(new Callback() {
                                @Override
                                public void onCreate(@NonNull SupportSQLiteDatabase db) {
                                    super.onCreate(db);

                                    db.execSQL("INSERT INTO users (id, username, password, fullName) VALUES " +
                                            "(1, 'duy', '123456', 'Duy Nguyen')," +
                                            "(2, 'an', '123456', 'An Tran')");

                                    db.execSQL(
                                            "INSERT INTO movies (id, title, durationMinutes, genre, description) VALUES "
                                                    +
                                                    "(1, 'Dune: Part Two', 166, 'Sci-Fi', 'Cuoc chien tren hanh tinh cat.'),"
                                                    +
                                                    "(2, 'Inside Out 2', 96, 'Animation', 'Hanh trinh cam xuc cua Riley.'),"
                                                    +
                                                    "(3, 'Godzilla x Kong', 115, 'Action', 'Tran chien quai vat khong lo.')");

                                    db.execSQL("INSERT INTO theaters (id, name, address) VALUES " +
                                            "(1, 'CGV Vincom', '72 Le Thanh Ton, Q1')," +
                                            "(2, 'Lotte Cinema', '469 Nguyen Huu Tho, Q7')");

                                    db.execSQL(
                                            "INSERT INTO showtimes (id, movieId, theaterId, startTime, price) VALUES " +
                                                    "(1, 1, 1, '2026-03-27 19:30', 95000)," +
                                                    "(2, 2, 1, '2026-03-27 17:00', 80000)," +
                                                    "(3, 3, 2, '2026-03-28 20:15', 90000)," +
                                                    "(4, 1, 2, '2026-03-29 21:00', 100000)");
                                }
                            })
                            .build();
                }
            }
        }
        return INSTANCE;
    }
}
