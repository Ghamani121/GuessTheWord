package com.guesstheword.frontend.controllers;

import com.guesstheword.frontend.utils.SceneSwitcher;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;

public class GameController {

    @FXML
    private Label wordLabel;

    @FXML
    private Label attemptsLabel;

    @FXML
    private Label messageLabel;

    @FXML
    private TextField guessInput;

    private String wordToGuess = "JAVA";  // later this can come from backend
    private char[] currentProgress;
    private int attemptsLeft = 5;

    @FXML
    public void initialize() {
        currentProgress = new char[wordToGuess.length()];
        for (int i = 0; i < wordToGuess.length(); i++) {
            currentProgress[i] = '_';
        }
        updateWordLabel();
    }

    @FXML
    private void handleGuess() {
        String guess = guessInput.getText().trim().toUpperCase();
        guessInput.clear();

        if (guess.isEmpty()) {
            messageLabel.setText("Enter a letter or word!");
            return;
        }

        if (guess.length() == 1) {
            // Single letter guess
            char ch = guess.charAt(0);
            boolean correct = false;
            for (int i = 0; i < wordToGuess.length(); i++) {
                if (wordToGuess.charAt(i) == ch) {
                    currentProgress[i] = ch;
                    correct = true;
                }
            }
            if (!correct) {
                attemptsLeft--;
            }
        } else {
            // Whole word guess
            if (guess.equals(wordToGuess)) {
                currentProgress = wordToGuess.toCharArray();
            } else {
                attemptsLeft--;
            }
        }

        updateWordLabel();

        if (new String(currentProgress).equals(wordToGuess)) {
            messageLabel.setText("🎉 You guessed it!");
            guessInput.setDisable(true);
        } else if (attemptsLeft <= 0) {
            messageLabel.setText("❌ You lost! The word was: " + wordToGuess);
            guessInput.setDisable(true);
        }
    }

    private void updateWordLabel() {
        wordLabel.setText(String.valueOf(currentProgress).replace("", " ").trim());
        attemptsLabel.setText("Attempts left: " + attemptsLeft);
    }

    @FXML
    private void handleLogout() throws Exception {
        Stage stage = (Stage) wordLabel.getScene().getWindow();
        SceneSwitcher.switchScene(stage, "login.fxml", "Login", 400, 250);
    }
}
