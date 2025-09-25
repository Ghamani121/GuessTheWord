package com.guesstheword.frontend;

import java.io.IOException;

import com.guesstheword.backend.models.User;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        // Load the login page first
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/frontend/views/login.fxml"));
        Scene scene = new Scene(loader.load());
        stage.setTitle("Guess The Word");
        stage.setScene(scene);

        // Maximize window while keeping borders
        stage.setMaximized(true);

        stage.show();
    }

    // Utility method to switch to the appropriate page after login
    public static void openNextPage(Stage stage, User user) throws Exception {
        FXMLLoader loader;
        Scene scene;

        if (user.isAdmin()) {
            loader = new FXMLLoader(Main.class.getResource("/frontend/views/admin.fxml"));
            scene = new Scene(loader.load());
            stage.setTitle("Admin Dashboard - Guess The Word");
        } else {
            loader = new FXMLLoader(Main.class.getResource("/frontend/views/game.fxml"));
            scene = new Scene(loader.load());
            stage.setTitle("Guess The Word");
        }

        stage.setScene(scene);
        stage.setMaximized(true); // keep maximized on scene switch
        stage.show();
    }

    public static void goToLogin(Stage stage) throws IOException {
        FXMLLoader loader = new FXMLLoader(Main.class.getResource("/frontend/views/login.fxml"));
        Scene scene = new Scene(loader.load());
        stage.setScene(scene);
        stage.setTitle("Guess The Word - Login");
        stage.setMaximized(true); // keep maximized
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
