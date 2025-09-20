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
            // Login succeeded, start a new game session automatically
            try {
                User user = userDAO.getUserByUsername(username);
                int userId = user.getUserId();

                // Start game session
                GameSession session = gameService.startGame(userId);

                // Pass session info to GameController (optional)
                Stage stage = (Stage) usernameField.getScene().getWindow();
                SceneSwitcher.switchScene(stage, "game.fxml", "Guess The Word", 500, 400, controller -> {
                    try {
                        ((GameController) controller).initData(session);
                    } catch (Exception e) {
                        e.printStackTrace();
                        messageLabel.setText("Error initializing game: " + e.getMessage());
                    }
                });//
            } catch (IllegalStateException ex) {
                // Daily limit reached or other game-start errors
                messageLabel.setText(ex.getMessage());
            } catch (Exception ex) {
                messageLabel.setText("Error starting game: " + ex.getMessage());
                ex.printStackTrace();
            }
        } else {
            messageLabel.setText(result); // show error message
        }
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
