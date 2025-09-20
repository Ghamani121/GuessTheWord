package com.guesstheword.backend.dao;

import com.guesstheword.backend.models.Guess;
import com.guesstheword.backend.utils.DbConnection;

import java.sql.*;
import java.time.LocalDate;

public class GuessDAO {

    // Save a guess
    public void insertGuess(Guess guess) throws SQLException {
        String sql = "INSERT INTO guess (session_id, guess_text, is_correct, created_at) VALUES (?, ?, ?, ?)";
        try (Connection conn = DbConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, guess.getSessionId());
            stmt.setString(2, guess.getGuessText());
            stmt.setBoolean(3, guess.isCorrect());
            stmt.setTimestamp(4, Timestamp.valueOf(guess.getCreatedAt()));
            stmt.executeUpdate();
        }
    }

    // Correct guesses count by date
    public int getCorrectGuessesCountByDate(LocalDate date) throws SQLException {
        String sql = "SELECT COUNT(*) FROM guess WHERE is_correct = true AND DATE(created_at) = ?";
        try (Connection conn = DbConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setDate(1, java.sql.Date.valueOf(date));
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) return rs.getInt(1);
        }
        return 0;
    }

    // Correct guesses by user and date
    public int getCorrectGuessesByUserAndDate(int userId, LocalDate date) throws SQLException {
        String sql = "SELECT COUNT(*) FROM guess g JOIN game_session s ON g.session_id = s.session_id " +
                     "WHERE g.is_correct = true AND s.user_id = ? AND DATE(g.created_at) = ?";
        try (Connection conn = DbConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, userId);
            stmt.setDate(2, java.sql.Date.valueOf(date));
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) return rs.getInt(1);
        }
        return 0;
    }
}
