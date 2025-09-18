package com.guesstheword.frontend.utils;

import java.util.function.Consumer;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class SceneSwitcher {
    public static void switchScene(Stage stage, String fxmlFile, String title, int width, int height) throws Exception {
        FXMLLoader loader = new FXMLLoader(SceneSwitcher.class.getResource("/frontend/views/" + fxmlFile));
        Scene scene = new Scene(loader.load(), width, height);
        stage.setTitle(title);
        stage.setScene(scene);
        stage.show();
    }

    // New method: pass data to controller
    public static <T> void switchScene(Stage stage, String fxmlFile, String title, int width, int height, Consumer<T> controllerInitializer) throws Exception {
        FXMLLoader loader = new FXMLLoader(SceneSwitcher.class.getResource("/frontend/views/" + fxmlFile));
        Scene scene = new Scene(loader.load(), width, height);
        stage.setTitle(title);

        // Initialize controller with your data
        T controller = loader.getController();
        if (controllerInitializer != null) {
            controllerInitializer.accept(controller);
        }

        stage.setScene(scene);
        stage.show();
    }

}

