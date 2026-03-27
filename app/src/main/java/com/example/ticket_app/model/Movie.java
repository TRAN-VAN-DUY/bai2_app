package com.example.ticket_app.model;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "movies")
public class Movie {
    @PrimaryKey(autoGenerate = true)
    public int id;

    @NonNull
    public String title;

    public int durationMinutes;

    @NonNull
    public String genre;

    @NonNull
    public String description;
}
