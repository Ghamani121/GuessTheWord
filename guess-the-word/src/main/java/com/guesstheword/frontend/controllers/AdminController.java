package com.guesstheword.frontend.controllers;
import com.guesstheword.backend.dao.UserDAO;
import com.guesstheword.backend.models.User;
import com.guesstheword.backend.services.ReportService;
import com.guesstheword.backend.services.UserService;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import com.guesstheword.frontend.Main;

public class AdminController {
    @FXML
    private Button logoutButton; // connect to FXML

    @FXML
    private TabPane tabPane;

    // Daily report tab
    @FXML
    private DatePicker dailyDatePicker;
    @FXML
    private Label totalUsersLabel;
    @FXML
    private Label correctGuessesLabel;

    // User report tab
    @FXML
    private ComboBox<String> userComboBox;
    @FXML
    private DatePicker userDatePicker;
    @FXML
    private Label wordsTriedLabel;
    @FXML
    private Label userCorrectGuessesLabel;

    private final ReportService reportService = new ReportService();
    private final UserService userService = new UserService();
    private final UserDAO userDAO = new UserDAO();

    @FXML
    public void initialize() {
        try {
            // Populate users in ComboBox
            List<String> usernames = userService.getAllUsernames();
            userComboBox.setItems(FXCollections.observableArrayList(usernames));
        } catch (SQLException e) {
            showError("Error loading users: " + e.getMessage());
        }
    }

    // Generate daily report
    @FXML
    private void generateDailyReport() {
        LocalDate date = dailyDatePicker.getValue();
        if (date == null) {
            showError("Please select a date");
            return;
        }

        try {
            Map<String, Integer> report = reportService.getDailyReport(date);
            totalUsersLabel.setText(String.valueOf(report.get("totalUsers")));
            correctGuessesLabel.setText(String.valueOf(report.get("correctGuesses")));
        } catch (SQLException e) {
            showError("Error fetching daily report: " + e.getMessage());
        }
    }

    @FXML
    private void handleLogout() throws IOException {
        Stage stage = (Stage) logoutButton.getScene().getWindow();
        Main.goToLogin(stage);
    }


    // Generate user report
    @FXML
    private void generateUserReport() {
        String username = userComboBox.getValue();
        LocalDate date = userDatePicker.getValue();

        if (username == null || date == null) {
            showError("Please select a user and a date");
            return;
        }

        try {
            User user = userDAO.getUserByUsername(username);
            if (user == null) {
                showError("Selected user not found");
                return;
            }

            Map<String, Integer> report = reportService.getUserReport(user.getUserId(), date);
            wordsTriedLabel.setText(String.valueOf(report.get("wordsTried")));
            userCorrectGuessesLabel.setText(String.valueOf(report.get("correctGuesses")));

        } catch (SQLException e) {
            showError("Error fetching user report: " + e.getMessage());
        }
    }

    private void showError(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR, message, ButtonType.OK);
        alert.showAndWait();
    }
}
