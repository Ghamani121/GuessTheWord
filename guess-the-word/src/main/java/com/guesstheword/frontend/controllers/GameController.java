package com.guesstheword.frontend.controllers;

import com.guesstheword.backend.models.GameSession;
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

    // This method is called from LoginController
    public void initData(GameSession session) {
        this.session = session;

        // Initialize UI for the session
        // e.g., display masked word
        wordLabel.setText("-----"); // 5-letter masked word
        messageLabel.setText("Make your first guess!");
    }

    // TODO: Add guess submission logic later
}
