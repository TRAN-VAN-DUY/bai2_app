package com.example.ticket_app.data;

import androidx.room.Dao;
import androidx.room.Query;

import com.example.ticket_app.model.Movie;
import com.example.ticket_app.model.ShowtimeDetail;
import com.example.ticket_app.model.Theater;

import java.util.List;

@Dao
public interface ShowtimeDao {
        @Query("SELECT s.id AS id, m.title AS movieTitle, t.name AS theaterName, s.startTime AS startTime, s.price AS price "
                        +
                        "FROM showtimes s " +
                        "INNER JOIN movies m ON s.movieId = m.id " +
                        "INNER JOIN theaters t ON s.theaterId = t.id " +
                        "ORDER BY s.startTime")
        List<ShowtimeDetail> getAllWithDetails();

        @Query("SELECT s.id AS id, m.title AS movieTitle, t.name AS theaterName, s.startTime AS startTime, s.price AS price "
                        +
                        "FROM showtimes s " +
                        "INNER JOIN movies m ON s.movieId = m.id " +
                        "INNER JOIN theaters t ON s.theaterId = t.id " +
                        "WHERE s.id = :showtimeId LIMIT 1")
        ShowtimeDetail getByIdWithDetails(int showtimeId);

        @Query("SELECT DISTINCT m.id, m.title, m.durationMinutes, m.genre, m.description " +
                        "FROM showtimes s " +
                        "INNER JOIN movies m ON s.movieId = m.id " +
                        "WHERE s.theaterId = :theaterId " +
                        "ORDER BY m.title")
        List<Movie> getMoviesByTheater(int theaterId);

        @Query("SELECT s.id AS id, m.title AS movieTitle, t.name AS theaterName, s.startTime AS startTime, s.price AS price "
                        +
                        "FROM showtimes s " +
                        "INNER JOIN movies m ON s.movieId = m.id " +
                        "INNER JOIN theaters t ON s.theaterId = t.id " +
                        "WHERE s.theaterId = :theaterId AND s.movieId = :movieId " +
                        "ORDER BY s.startTime")
        List<ShowtimeDetail> getByTheaterAndMovie(int theaterId, int movieId);

        @Query("SELECT s.id FROM showtimes s WHERE s.theaterId = :theaterId AND s.movieId = :movieId ORDER BY s.startTime LIMIT 1")
        Integer getFirstShowtimeIdByTheaterAndMovie(int theaterId, int movieId);

        @Query("SELECT DISTINCT t.id, t.name, t.address " +
                        "FROM showtimes s " +
                        "INNER JOIN theaters t ON s.theaterId = t.id " +
                        "WHERE s.movieId = :movieId " +
                        "ORDER BY t.name")
        List<Theater> getTheatersByMovie(int movieId);
}
