package com.guesstheword.backend.dao;

import com.guesstheword.backend.models.Guess;
import com.guesstheword.backend.utils.DbConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class GuessDAO {

    // Insert a new guess
    public void insertGuess(Guess guess) throws SQLException {
        String sql = "INSERT INTO guess (session_id, guess_text, is_correct, created_at) VALUES (?, ?, ?, ?)";
        try (Connection conn = DbConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setInt(1, guess.getSessionId());
            stmt.setString(2, guess.getGuessText());
            stmt.setBoolean(3, guess.isCorrect());
            stmt.setTimestamp(4, Timestamp.valueOf(guess.getCreatedAt()));

            stmt.executeUpdate();

            // Get generated guess_id
            try (ResultSet rs = stmt.getGeneratedKeys()) {
                if (rs.next()) {
                    guess.setGuessId(rs.getInt(1));
                }
            }
        }
    }

    // Fetch all guesses for a session
    public List<Guess> getGuessesBySession(int sessionId) throws SQLException {
        List<Guess> guesses = new ArrayList<>();
        String sql = "SELECT * FROM guess WHERE session_id = ? ORDER BY created_at ASC";

        try (Connection conn = DbConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, sessionId);

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Guess guess = new Guess(
                            rs.getInt("guess_id"),
                            rs.getInt("session_id"),
                            rs.getString("guess_text"),
                            rs.getBoolean("is_correct"),
                            rs.getTimestamp("created_at").toLocalDateTime()
                    );
                    guesses.add(guess);
                }
            }
        }
        return guesses;
    }
}
