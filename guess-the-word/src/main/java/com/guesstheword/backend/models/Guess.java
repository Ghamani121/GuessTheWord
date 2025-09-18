package com.guesstheword.backend.models;

import java.time.LocalDateTime;

public class Guess {
    private int guess_id;
    private int session_id;
    private String guess_text;
    private boolean is_correct;
    private LocalDateTime created_at;

    // Empty constructor
    public Guess() {}

    // Constructor for new guess
    public Guess(int session_id, String guess_text, boolean is_correct) {
        this.session_id = session_id;
        this.guess_text = guess_text;
        this.is_correct = is_correct;
        this.created_at = LocalDateTime.now();
    }

    // Full constructor
    public Guess(int guess_id, int session_id, String guess_text, boolean is_correct, LocalDateTime created_at) {
        this.guess_id = guess_id;
        this.session_id = session_id;
        this.guess_text = guess_text;
        this.is_correct = is_correct;
        this.created_at = created_at;
    }

    public int getGuessId() { return guess_id; }
    public void setGuessId(int guess_id) { this.guess_id = guess_id; }

    public int getSessionId() { return session_id; }
    public void setSessionId(int session_id) { this.session_id = session_id; }

    public String getGuessText() { return guess_text; }
    public void setGuessText(String guess_text) { this.guess_text = guess_text; }

    public boolean isCorrect() { return is_correct; }
    public void setCorrect(boolean is_correct) { this.is_correct = is_correct; }

    public LocalDateTime getCreatedAt() { return created_at; }
    public void setCreatedAt(LocalDateTime created_at) { this.created_at = created_at; }
}
