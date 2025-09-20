package com.guesstheword.frontend.controllers;
import com.guesstheword.backend.dao.GuessDAO;
import com.guesstheword.backend.dao.WordDAO;
import com.guesstheword.backend.models.GameSession;
import com.guesstheword.backend.models.Guess;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Button;

public class GameController {

    private GameSession session;

    @FXML
    private Label wordLabel;

    @FXML
    private TextField guessField;

    @FXML
    private Button submitButton;

    @FXML
    private Label messageLabel;

    @FXML
    private Label attemptsLabel;

    private final GuessDAO guessDAO = new GuessDAO();
    private int maxAttempts = 5;
    private int attemptsLeft = maxAttempts;
    private String actualWord;

    public void initData(GameSession session) throws Exception {
        this.session = session;

        // Fetch actual word text from DB
        WordDAO wordDAO = new WordDAO();
        actualWord = wordDAO.getWordById(session.getWordId()).getWordText();

        // Initialize UI
        wordLabel.setText("-----"); // masked
        messageLabel.setText("Make your first guess!");
        attemptsLabel.setText("Attempts left: " + attemptsLeft);
    }

    @FXML
    private void handleGuess(ActionEvent event) throws Exception {
        String guess = guessField.getText().trim().toUpperCase();

        // Validate input
        if (guess.length() != 5) {
            messageLabel.setText("Enter a 5-letter word!");
            return;
        }

        // Decrement attempts
        attemptsLeft--;
        attemptsLabel.setText("Attempts left: " + attemptsLeft);

        // Compare letters
        StringBuilder display = new StringBuilder();
        boolean isCorrect = true;

        for (int i = 0; i < 5; i++) {
            char g = guess.charAt(i);
            char a = actualWord.charAt(i);

            if (g == a) {
                display.append("[G]").append(g); // Green
            } else if (actualWord.indexOf(g) != -1) {
                display.append("[O]").append(g); // Orange
                isCorrect = false;
            } else {
                display.append("[X]").append(g); // Grey
                isCorrect = false;
            }
        }

        wordLabel.setText(display.toString());

        // Save guess to DB
        Guess g = new Guess(session.getSessionId(), guess, isCorrect);
        guessDAO.insertGuess(g);

        // Check win/loss
        if (isCorrect) {
            messageLabel.setText("Congratulations! You guessed correctly!");
            submitButton.setDisable(true);
        } else if (attemptsLeft == 0) {
            messageLabel.setText("Better luck next time! Word was: " + actualWord);
            submitButton.setDisable(true);
        } else {
            messageLabel.setText("Try again!");
            guessField.clear();
        }
    } 
}
