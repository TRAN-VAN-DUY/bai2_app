package com.example.ticket_app.model;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Index;
import androidx.room.PrimaryKey;

@Entity(tableName = "tickets", foreignKeys = {
        @ForeignKey(entity = User.class, parentColumns = "id", childColumns = "userId", onDelete = ForeignKey.CASCADE),
        @ForeignKey(entity = Showtime.class, parentColumns = "id", childColumns = "showtimeId", onDelete = ForeignKey.CASCADE)
}, indices = { @Index("userId"), @Index("showtimeId") })
public class Ticket {
    @PrimaryKey(autoGenerate = true)
    public int id;

    public int userId;
    public int showtimeId;

    @NonNull
    public String seatNumber;

    @NonNull
    public String bookedAt;
}
