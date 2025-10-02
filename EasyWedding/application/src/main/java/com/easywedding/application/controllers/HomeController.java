package com.easywedding.application.controllers;

import com.easywedding.application.core.Router;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

/** Public home screen: logo + navigation to Login/Signup. */
public class HomeController {
    @FXML private Button loginBtn;
    @FXML private Button signupBtn;

    @FXML
    public void initialize() {
        loginBtn.setOnAction(e -> Router.toLogin());
        signupBtn.setOnAction(e -> Router.toSignup());
    }
}
