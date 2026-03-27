package com.example.ticket_app.data;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.ticket_app.model.User;

@Dao
public interface UserDao {
    @Insert
    long insert(User user);

    @Query("SELECT * FROM users WHERE username = :username AND password = :password LIMIT 1")
    User login(String username, String password);

    @Query("SELECT * FROM users WHERE id = :id LIMIT 1")
    User getById(int id);
}
