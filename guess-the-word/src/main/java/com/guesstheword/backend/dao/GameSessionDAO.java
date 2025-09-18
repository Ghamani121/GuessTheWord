package com.guesstheword.backend.dao;

import com.guesstheword.backend.models.GameSession;
import com.guesstheword.backend.utils.DbConnection;

import java.sql.*;
import java.time.LocalDateTime;

public class GameSessionDAO {

    // Insert a new game session
    public void insertSession(GameSession session) throws SQLException {
        String sql = "INSERT INTO game_session (user_id, word_id, status, start_time) VALUES (?, ?, ?, ?)";
        try (Connection conn = DbConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setInt(1, session.getUserId());
            stmt.setInt(2, session.getWordId());
            stmt.setString(3, session.getStatus());
            stmt.setTimestamp(4, Timestamp.valueOf(LocalDateTime.now()));

            stmt.executeUpdate();

            // Get the generated session_id
            try (ResultSet rs = stmt.getGeneratedKeys()) {
                if (rs.next()) {
                    session.setSessionId(rs.getInt(1));
                }
            }
        }
    }

    // Count how many games the user played today
    public int getGamesPlayedToday(int userId) throws SQLException {
        String sql = "SELECT COUNT(*) FROM game_session WHERE user_id = ? AND DATE(start_time) = CURDATE()";
        try (Connection conn = DbConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, userId);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt(1);
                }
            }
        }
        return 0;
    }

    // Optional: fetch a session by ID (if needed later)
    public GameSession getSessionById(int sessionId) throws SQLException {
        String sql = "SELECT * FROM game_session WHERE session_id = ?";
        try (Connection conn = DbConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, sessionId);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    GameSession session = new GameSession();
                    session.setSessionId(rs.getInt("session_id"));
                    session.setUserId(rs.getInt("user_id"));
                    session.setWordId(rs.getInt("word_id"));
                    session.setStatus(rs.getString("status"));
                    session.setStartTime(rs.getTimestamp("start_time").toLocalDateTime());
                    Timestamp endTime = rs.getTimestamp("end_time");
                    if (endTime != null) session.setEndTime(endTime.toLocalDateTime());
                    return session;
                }
            }
        }
        return null;
    }
}
