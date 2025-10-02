package com.easywedding.application.controllers;

import com.easywedding.application.core.Router;
import com.easywedding.application.dtos.UserDto;
import com.easywedding.application.services.AuthService;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class LoginController {

    @FXML private TextField usernameField;
    @FXML private PasswordField passwordField;

    private final AuthService authService = new AuthService();

    @FXML
    public void onLogin() {
        String username = usernameField.getText();
        String password = passwordField.getText();

        try {
            UserDto loggedIn = authService.login(username, password);
            if (loggedIn != null) {
                System.out.println("Login successful for user: " + loggedIn.getUsername());
                Router.toDashboard();
            } else {
                System.out.println("Invalid credentials.");
            }
        } catch (Exception e) {
            System.out.println("Login failed: " + e.getMessage());
        }
    }

    @FXML
    public void goToHome() {
        Router.toHome();
    }
}
