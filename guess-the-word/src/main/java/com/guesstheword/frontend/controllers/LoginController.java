package com.guesstheword.frontend.controllers;
import com.guesstheword.frontend.utils.SceneSwitcher;
import com.guesstheword.backend.services.UserService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.stage.Stage;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;


public class LoginController {

    @FXML
    private TextField usernameField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private Label messageLabel;

    private final UserService userService = new UserService();

    @FXML
    private void handleLogin() {
        try {
            String username = usernameField.getText();
            String password = passwordField.getText();

            String result = userService.login(username, password);
            messageLabel.setText(result);

        } catch (Exception e) {
            messageLabel.setText("Error: " + e.getMessage());
            e.printStackTrace();
        }
    }

        @FXML
        private void switchToRegister(ActionEvent event) throws Exception {
            Stage stage = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();
            SceneSwitcher.switchScene(stage, "registration.fxml", "Guess The Word - Registration", 400, 250);
        }
}
