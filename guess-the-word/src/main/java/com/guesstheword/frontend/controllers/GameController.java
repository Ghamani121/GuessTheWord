package com.guesstheword.frontend.controllers;
import com.guesstheword.backend.dao.GuessDAO;
import com.guesstheword.backend.dao.WordDAO;
import com.guesstheword.backend.models.GameSession;
import com.guesstheword.backend.models.Guess;
import com.guesstheword.frontend.Main;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.control.Button;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;

public class GameController {

    @FXML
    private Button logoutButton; // connect to FXML


    private GameSession session;

    @FXML
    private TextField guessField;

    @FXML
    private Button submitButton;

    @FXML
    private Label messageLabel;

    @FXML
    private Label attemptsLabel;

    @FXML
    private VBox wordGrid; // Holds guess rows

    private final GuessDAO guessDAO = new GuessDAO();
    private int maxAttempts = 5;
    private int attemptsLeft = maxAttempts;
    private String actualWord;

    public void initData(GameSession session) throws Exception {
        this.session = session;

        // Fetch the actual word from DB
        WordDAO wordDAO = new WordDAO();
        actualWord = wordDAO.getWordById(session.getWordId()).getWordText().toUpperCase();

        // Initialize UI
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

        // Render guess row with colored cells
        renderGuess(guess);

        // Check if guess is correct
        boolean isCorrect = guess.equals(actualWord);

        // Save guess to DB
        Guess g = new Guess(session.getSessionId(), guess, isCorrect);
        guessDAO.insertGuess(g);

        // Win/loss logic
        if (isCorrect) {
            messageLabel.setText("🎉 Congratulations! You guessed correctly!");
            submitButton.setDisable(true);
        } else if (attemptsLeft == 0) {
            messageLabel.setText("❌ Better luck next time! Word was: " + actualWord);
            submitButton.setDisable(true);
        } else {
            messageLabel.setText("Try again!");
            guessField.clear();
        }
    }

    /**
     * Creates a row of colored cells for the guess
     */
    private void renderGuess(String guess) {
        HBox row = new HBox(5); 
        row.setAlignment(Pos.CENTER);

        for (int i = 0; i < 5; i++) {
            char g = guess.charAt(i);
            char a = actualWord.charAt(i);

            Label cell = new Label(String.valueOf(g));
            cell.getStyleClass().add("letter-cell");
            cell.setMinSize(40, 40);
            cell.setAlignment(Pos.CENTER);

            if (g == a) {
                // Correct position -> green
                cell.setStyle("-fx-background-color: #6aaa64; -fx-text-fill: white;");
            } else if (actualWord.indexOf(g) != -1) {
                // Present but wrong position -> yellow
                cell.setStyle("-fx-background-color: #c9b458; -fx-text-fill: white;");
            } else {
                // Not in word -> grey
                cell.setStyle("-fx-background-color: #787c7e; -fx-text-fill: white;");
            }

            row.getChildren().add(cell);
        }

        wordGrid.getChildren().add(row);
    }
    @FXML
    private void handleLogout() throws IOException {
        Stage stage = (Stage) logoutButton.getScene().getWindow();
        Main.goToLogin(stage);
    }
}
