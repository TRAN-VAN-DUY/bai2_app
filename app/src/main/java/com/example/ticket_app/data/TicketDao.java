package com.example.ticket_app.data;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.ticket_app.model.Ticket;
import com.example.ticket_app.model.TicketDetail;

import java.util.List;

@Dao
public interface TicketDao {
    @Insert
    long insert(Ticket ticket);

    @Query("SELECT tk.id AS id, m.title AS movieTitle, th.name AS theaterName, s.startTime AS startTime, tk.seatNumber AS seatNumber, s.price AS price, tk.bookedAt AS bookedAt "
            +
            "FROM tickets tk " +
            "INNER JOIN showtimes s ON tk.showtimeId = s.id " +
            "INNER JOIN movies m ON s.movieId = m.id " +
            "INNER JOIN theaters th ON s.theaterId = th.id " +
            "WHERE tk.userId = :userId " +
            "ORDER BY tk.id DESC")
    List<TicketDetail> getTicketDetailsByUserId(int userId);

    @Query("SELECT seatNumber FROM tickets WHERE showtimeId = :showtimeId")
    List<String> getBookedSeatsByShowtime(int showtimeId);

    @Query("SELECT COUNT(*) FROM tickets WHERE showtimeId = :showtimeId AND seatNumber = :seatNumber")
    int countSeatForShowtime(int showtimeId, String seatNumber);
}
