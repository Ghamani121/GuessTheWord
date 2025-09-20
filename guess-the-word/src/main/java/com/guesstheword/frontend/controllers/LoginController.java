package com.guesstheword.frontend.controllers;
import com.guesstheword.frontend.utils.SceneSwitcher;
import com.guesstheword.backend.services.UserService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.stage.Stage;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import com.guesstheword.backend.services.GameService;
import com.guesstheword.backend.models.GameSession;
import com.guesstheword.backend.models.User;
import com.guesstheword.backend.dao.UserDAO;



public class LoginController {

    @FXML
    private TextField usernameField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private Label messageLabel;

    private final UserService userService = new UserService();
    private final GameService gameService = new GameService();
    private final UserDAO userDAO=new UserDAO();

@FXML
private void handleLogin() {
    try {
        String username = usernameField.getText();
        String password = passwordField.getText();

        String result = userService.login(username, password);

        if (result.equals("Login successful!")) {
            // Get the logged-in user
            User user = userDAO.getUserByUsername(username);
            Stage stage = (Stage) usernameField.getScene().getWindow();

            if (user.isAdmin()) {
                // Admin user -> go to admin.fxml
                SceneSwitcher.switchScene(stage, "admin.fxml", "Admin Dashboard - Guess The Word", 900, 600);
            } else {
                //Regular user = start a new game session
                try {
                    GameSession session = gameService.startGame(user.getUserId());

                    // Pass session info to GameController
                    SceneSwitcher.switchScene(stage, "game.fxml", "Guess The Word", 900, 600, controller -> {
                        try {
                            ((GameController) controller).initData(session);
                        } catch (Exception e) {
                            e.printStackTrace();
                            messageLabel.setText("Error initializing game: " + e.getMessage());
                        }
                    });
                } catch (IllegalStateException ex) {
                    messageLabel.setText(ex.getMessage());
                } catch (Exception ex) {
                    messageLabel.setText("Error starting game: " + ex.getMessage());
                    ex.printStackTrace();
                }
            }
        } else {
            messageLabel.setText(result); // show login error
        }
    } catch (Exception e) {
        messageLabel.setText("Error: " + e.getMessage());
        e.printStackTrace();
    }
}

        @FXML
        private void switchToRegister(ActionEvent event) throws Exception {
            Stage stage = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();
            SceneSwitcher.switchScene(stage, "registration.fxml", "Guess The Word - Registration", 900, 600);
        }
}
