package com.guesstheword.backend.models;

import java.time.LocalDateTime;

public class GameSession {
    private int session_id;
    private int user_id;
    private int word_id;
    private LocalDateTime start_time;
    private LocalDateTime end_time;
    private String status; // IN_PROGRESS, WON, LOST

    // Empty constructor
    public GameSession() {}

    // Constructor for new session
    public GameSession(int user_id, int word_id, String status) {
        this.user_id = user_id;
        this.word_id = word_id;
        this.start_time = LocalDateTime.now();
        this.status = status;
    }

    // Full constructor
    public GameSession(int session_id, int user_id, int word_id,
                       LocalDateTime start_time, LocalDateTime end_time, String status) {
        this.session_id = session_id;
        this.user_id = user_id;
        this.word_id = word_id;
        this.start_time = start_time;
        this.end_time = end_time;
        this.status = status;
    }

    public int getSessionId() { return session_id; }
    public void setSessionId(int session_id) { this.session_id = session_id; }

    public int getUserId() { return user_id; }
    public void setUserId(int user_id) { this.user_id = user_id; }

    public int getWordId() { return word_id; }
    public void setWordId(int word_id) { this.word_id = word_id; }

    public LocalDateTime getStartTime() { return start_time; }
    public void setStartTime(LocalDateTime start_time) { this.start_time = start_time; }

    public LocalDateTime getEndTime() { return end_time; }
    public void setEndTime(LocalDateTime end_time) { this.end_time = end_time; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
}
