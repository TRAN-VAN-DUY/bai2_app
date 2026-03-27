package com.example.ticket_app.data;

import androidx.room.Dao;
import androidx.room.Query;

import com.example.ticket_app.model.Movie;

import java.util.List;

@Dao
public interface MovieDao {
    @Query("SELECT * FROM movies ORDER BY id")
    List<Movie> getAll();
}
