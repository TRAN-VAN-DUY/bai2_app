package com.example.ticket_app.data;

import androidx.room.Dao;
import androidx.room.Query;

import com.example.ticket_app.model.Theater;

import java.util.List;

@Dao
public interface TheaterDao {
    @Query("SELECT * FROM theaters ORDER BY id")
    List<Theater> getAll();
}
