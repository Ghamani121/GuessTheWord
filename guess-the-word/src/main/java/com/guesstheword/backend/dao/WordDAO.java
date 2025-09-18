package com.guesstheword.backend.dao;

import com.guesstheword.backend.models.Word;
import com.guesstheword.backend.utils.DbConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class WordDAO {

    // Fetch all words from Db
    public List<Word> getAllWords() throws SQLException {
        List<Word> words = new ArrayList<>();
        String sql = "SELECT * FROM word";

        try (Connection conn = DbConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Word word = new Word();
                word.setWordId(rs.getInt("word_id"));
                word.setWordText(rs.getString("word_text"));
                word.setCreatedAt(rs.getTimestamp("created_at").toLocalDateTime());
                words.add(word);
            }
        }
        return words;
    }

    // Optional: fetch word by ID
    public Word getWordById(int wordId) throws SQLException {
        String sql = "SELECT * FROM word WHERE word_id = ?";
        try (Connection conn = DbConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, wordId);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    Word word = new Word();
                    word.setWordId(rs.getInt("word_id"));
                    word.setWordText(rs.getString("word_text"));
                    word.setCreatedAt(rs.getTimestamp("created_at").toLocalDateTime());
                    return word;
                }
            }
        }
        return null;
    }
}
