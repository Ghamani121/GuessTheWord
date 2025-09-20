package com.guesstheword.frontend.controllers;
import com.guesstheword.backend.services.UserService;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import java.sql.SQLException;
import com.guesstheword.frontend.utils.SceneSwitcher;
import javafx.event.ActionEvent;
import javafx.stage.Stage;

public class RegistrationController {

    @FXML
    private TextField usernameField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private Label messageLabel;

    private final UserService userService = new UserService();

    @FXML
    private void handleRegister() {
        String username = usernameField.getText().trim();
        String password = passwordField.getText();

        try {
            String result = userService.register(username, password);
            messageLabel.setText(result);
        } catch (SQLException e) {
            e.printStackTrace();
            messageLabel.setText("Error connecting to database.");
        }
    }

    @FXML
    private void switchToLogin(ActionEvent event) throws Exception {
        Stage stage = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();
        SceneSwitcher.switchScene(stage, "login.fxml", "Guess The Word - Login", 900, 600);
    }

}
