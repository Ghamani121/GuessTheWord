package com.guesstheword.frontend.utils;

import java.util.function.Consumer;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class SceneSwitcher {

    // Switch scene with fixed width/height (existing behavior)
    public static void switchScene(Stage stage, String fxmlFile, String title, int width, int height) throws Exception {
        FXMLLoader loader = new FXMLLoader(SceneSwitcher.class.getResource("/frontend/views/" + fxmlFile));
        Scene scene = new Scene(loader.load(), width, height);
        stage.setTitle(title);
        stage.setScene(scene);
        stage.show();
    }

    // Switch scene with fixed width/height and controller initializer
    public static <T> void switchScene(Stage stage, String fxmlFile, String title, int width, int height, Consumer<T> controllerInitializer) throws Exception {
        FXMLLoader loader = new FXMLLoader(SceneSwitcher.class.getResource("/frontend/views/" + fxmlFile));
        Scene scene = new Scene(loader.load(), width, height);
        T controller = loader.getController();
        if (controllerInitializer != null) {
            controllerInitializer.accept(controller);
        }
        stage.setTitle(title);
        stage.setScene(scene);
        stage.show();
    }

    // NEW: Switch scene using FXML preferred size (fullscreen capable)
    public static void switchScene(Stage stage, String fxmlFile, String title) throws Exception {
        FXMLLoader loader = new FXMLLoader(SceneSwitcher.class.getResource("/frontend/views/" + fxmlFile));
        Scene scene = new Scene(loader.load()); // Use preferred size from FXML
        stage.setTitle(title);
        stage.setScene(scene);
        stage.setMaximized(true); // Fullscreen with window border
        stage.show();
    }

    // NEW: Switch scene with controller initializer without fixed size
    public static <T> void switchScene(Stage stage, String fxmlFile, String title, Consumer<T> controllerInitializer) throws Exception {
        FXMLLoader loader = new FXMLLoader(SceneSwitcher.class.getResource("/frontend/views/" + fxmlFile));
        Scene scene = new Scene(loader.load());
        T controller = loader.getController();
        if (controllerInitializer != null) {
            controllerInitializer.accept(controller);
        }
        stage.setTitle(title);
        stage.setScene(scene);
        stage.setMaximized(true);
        stage.show();
    }
}
