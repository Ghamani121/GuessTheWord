package com.guesstheword.backend.services;

import com.guesstheword.backend.dao.GameSessionDAO;
import com.guesstheword.backend.dao.GuessDAO;
import com.guesstheword.backend.dao.UserDAO;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

public class ReportService {

    private final GameSessionDAO sessionDAO = new GameSessionDAO();
    private final GuessDAO guessDAO = new GuessDAO();
    private final UserDAO userDAO = new UserDAO();

    // Report for a day: number of users and correct guesses
    public Map<String, Integer> getDailyReport(LocalDate date) throws SQLException {
        Map<String, Integer> report = new HashMap<>();
        int totalUsers = userDAO.getUsersCountByDate(date);
        int correctGuesses = guessDAO.getCorrectGuessesCountByDate(date);

        report.put("totalUsers", totalUsers);
        report.put("correctGuesses", correctGuesses);

        return report;
    }

    // Report for a user: date, number of words tried, number of correct guesses
    public Map<String, Integer> getUserReport(int userId, LocalDate date) throws SQLException {
        Map<String, Integer> report = new HashMap<>();
        int wordsTried = sessionDAO.getGamesCountByUserAndDate(userId, date);
        int correctGuesses = guessDAO.getCorrectGuessesByUserAndDate(userId, date);

        report.put("wordsTried", wordsTried);
        report.put("correctGuesses", correctGuesses);

        return report;
    }
}
