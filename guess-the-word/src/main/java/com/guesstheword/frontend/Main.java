package com.guesstheword.frontend;

import com.guesstheword.backend.dao.UserDAO;
import com.guesstheword.backend.models.User;
import com.guesstheword.frontend.utils.SceneSwitcher;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        // Load the login page first
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/frontend/views/login.fxml"));
        Scene scene = new Scene(loader.load(), 400, 250);
        stage.setTitle("Guess The Word");
        stage.setScene(scene);
        stage.show();

        // Pass stage to LoginController (optional if using SceneSwitcher)
        // SceneSwitcher can handle switching after login based on isAdmin
    }

    // Utility method to switch to appropriate page after login
    public static void openNextPage(Stage stage, User user) throws Exception {
        if (user.isAdmin()) {
            FXMLLoader loader = new FXMLLoader(Main.class.getResource("/frontend/views/admin.fxml"));
            Scene scene = new Scene(loader.load(), 600, 400); // adjust size
            stage.setScene(scene);
            stage.setTitle("Admin Dashboard - Guess The Word");
            stage.show();
        } else {
            FXMLLoader loader = new FXMLLoader(Main.class.getResource("/frontend/views/game.fxml"));
            Scene scene = new Scene(loader.load(), 500, 400); // adjust size
            stage.setScene(scene);
            stage.setTitle("Guess The Word");
            stage.show();
        }
    }

    public static void main(String[] args) {
        launch();
    }
}
