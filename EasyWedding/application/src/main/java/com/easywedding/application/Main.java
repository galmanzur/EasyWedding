package com.easywedding.application;

import com.easywedding.application.core.AppState;
import com.easywedding.application.core.Router;
import javafx.application.Application;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.util.Objects;

/**
 * JavaFX entry point.
 *
 * Responsibilities:
 *  - Initialize the primary Stage and the Router.
 *  - Decide the first screen (Home for guests, or Dashboard if already logged in).
 *  - Apply basic window settings (title/size/icons).
 *  - Install a global uncaught-exception handler for visibility.
 *
 * Notes:
 *  - Navigation between screens is centralized in Router.
 *  - Authentication/session state is held in AppState.
 */
public class Main extends Application {

    @Override
    public void start(Stage stage) {
        // Basic window config
        stage.setTitle("EasyWedding");
        stage.setMinWidth(900);
        stage.setMinHeight(600);

        try {
            stage.getIcons().add(new Image(
                    Objects.requireNonNull(Main.class.getResourceAsStream("/images/icon.png"))));
        } catch (Exception ignore) {
            // no icon available; safe to ignore
        }

        // Initialize the Router with the primary stage
        Router.init(stage);

        // In the future: apply a global stylesheet on first scene load (if Router supports it).
        // Router.setGlobalStylesheet("/css/app.css");

        // Choose initial screen
        // If you have a persisted login/token flow, you can decide here.
        if (AppState.isLoggedIn()) {
            Router.toDashboard();
        } else {
            Router.toHome();
        }

        // Uncaught exception handler – useful during development
        Thread.setDefaultUncaughtExceptionHandler((t, ex) -> {
            System.err.println("[FX] Uncaught exception in thread " + t.getName());
            ex.printStackTrace();
        });

        // Also catch exceptions on the FX Application Thread explicitly
        Thread.currentThread().setUncaughtExceptionHandler((t, ex) -> {
            System.err.println("[FX] Uncaught exception (FX thread):");
            ex.printStackTrace();
        });
    }

    public static void main(String[] args) {
        launch(args);
    }
}
