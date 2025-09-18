package com.guesstheword.backend.services;
import com.guesstheword.backend.dao.UserDAO;
import com.guesstheword.backend.models.User;
import com.guesstheword.backend.utils.PasswordUtil;

import java.sql.SQLException;

public class UserService {
    private final UserDAO userDAO = new UserDAO();

    // Registration
    public String register(String username, String password) throws SQLException {
        // Validate username
        if (username.length() < 5 || !username.matches(".*[A-Z].*") || !username.matches(".*[a-z].*")) {
            return "Username must be ≥5 chars with upper and lower case letters.";
        }

        // Validate password
        if (password.length() < 5 ||
                !password.matches(".*[A-Za-z].*") ||
                !password.matches(".*[0-9].*") ||
                !password.matches(".*[$%*@].*")) {
            return "Password must be ≥5 chars with letters, numbers, and one special char ($,%*,@).";
        }

        // Check if username exists
        if (userDAO.usernameExists(username)) {
            return "Username already exists!";
        }

        // Hash password and save
        String hash = PasswordUtil.hashPassword(password);
        User user = new User(username, hash,false);
        userDAO.insertUser(user);

        return "User registered successfully!";
    }

    // Login
    public String login(String username, String password) throws SQLException {
        User user = userDAO.getUserByUsername(username);
        if (user == null) return "Username does not exist.";

        if (PasswordUtil.verifyPassword(password, user.getHashPassword())) {
            return "Login successful!";
        } else {
            return "Incorrect password.";
        }
    }
}
