package com.easywedding.application.core;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.net.URL;

public class Router {
    private static Stage primaryStage;

    public static void init(Stage stage) {
        primaryStage = stage;
    }

    public static void go(String fxmlPath, String title) {
        try {
            // fxmlPath must start with "/" and be under src/main/resources
            URL url = Router.class.getResource(fxmlPath);
            if (url == null) {
                throw new IllegalStateException("FXML not found on classpath: " + fxmlPath);
            }

            FXMLLoader loader = new FXMLLoader(url); // location is set here
            Scene scene = new Scene(loader.load());
            primaryStage.setScene(scene);
            primaryStage.setTitle(title);
            primaryStage.show();

        } catch (Exception e) {
            throw new RuntimeException("Failed to load view: " + fxmlPath, e);
        }
    }

    // convenience
    public static void toHome()           { go("/fxml/home.fxml", "EasyWedding - Home"); }
    public static void toSignup()         { go("/fxml/signup.fxml", "Sign Up"); }
    public static void toLogin()          { go("/fxml/login.fxml", "Login"); }
    public static void toDashboard()      { go("/fxml/main_dashboard.fxml", "Dashboard"); }
    public static void toGuests()         { go("/fxml/guests.fxml", "Guests"); }
    public static void toTables()         { go("/fxml/tables.fxml", "Tables"); }
    public static void toRsvps()          { go("/fxml/rsvps.fxml", "RSVPs"); }
}
