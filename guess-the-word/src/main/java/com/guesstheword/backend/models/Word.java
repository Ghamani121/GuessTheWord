package com.guesstheword.backend.models;

import java.time.LocalDateTime;

public class Word {
    private int word_id;
    private String word_text;
    private LocalDateTime created_at;

    // Empty constructor
    public Word() {}

    // Constructor without ID (for new words)
    public Word(String word_text) {
        this.word_text = word_text;
        this.created_at = LocalDateTime.now();
    }

    // Full constructor
    public Word(int word_id, String word_text, LocalDateTime created_at) {
        this.word_id = word_id;
        this.word_text = word_text;
        this.created_at = created_at;
    }

    public int getWordId() { return word_id; }
    public void setWordId(int word_id) { this.word_id = word_id; }

    public String getWordText() { return word_text; }
    public void setWordText(String word_text) { this.word_text = word_text; }

    public LocalDateTime getCreatedAt() { return created_at; }
    public void setCreatedAt(LocalDateTime created_at) { this.created_at = created_at; }
}
