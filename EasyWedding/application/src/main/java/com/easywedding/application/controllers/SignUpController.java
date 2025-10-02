package com.easywedding.application.controllers;

import com.easywedding.application.dtos.UserDto;
import com.easywedding.application.dtos.WeddingDto;
import com.easywedding.application.services.AuthService;
import com.easywedding.application.services.WeddingService;
import com.easywedding.core.enums.PermissionLevel;
import com.easywedding.application.core.Router;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.time.LocalDate;
import java.util.List;

public class SignUpController {

    @FXML private TextField usernameField;
    @FXML private PasswordField passwordField;
    @FXML private ComboBox<PermissionLevel> permissionLevelCombo;

    @FXML private RadioButton existingWeddingRadio;
    @FXML private RadioButton newWeddingRadio;

    @FXML private ComboBox<WeddingDto> weddingListCombo;
    @FXML private TextField newWeddingDescriptionField;
    @FXML private TextField newWeddingLocationField;
    @FXML private DatePicker newWeddingDatePicker;

    private final AuthService authService = new AuthService();
    private final WeddingService weddingService = new WeddingService();

    @FXML
    public void initialize() {
        permissionLevelCombo.getItems().setAll(PermissionLevel.values());

        ToggleGroup group = new ToggleGroup();
        existingWeddingRadio.setToggleGroup(group);
        newWeddingRadio.setToggleGroup(group);

        existingWeddingRadio.setOnAction(e -> toggleWeddingOption(true));
        newWeddingRadio.setOnAction(e -> toggleWeddingOption(false));

        loadWeddingList();
    }

    private void toggleWeddingOption(boolean joinExisting) {
        weddingListCombo.setDisable(!joinExisting);
        newWeddingDescriptionField.setDisable(joinExisting);
        newWeddingLocationField.setDisable(joinExisting);
        newWeddingDatePicker.setDisable(joinExisting);
    }

    private void loadWeddingList() {
        try {
            List<WeddingDto> weddings = weddingService.getAllWeddings();
            weddingListCombo.getItems().addAll(weddings);
        } catch (Exception e) {
            showAlert("Error loading weddings: " + e.getMessage());
        }
    }

    @FXML
    private void onSignUp() {
        String username = usernameField.getText();
        String password = passwordField.getText();
        PermissionLevel level = permissionLevelCombo.getValue();

        if (username.isBlank() || password.isBlank() || level == null) {
            showAlert("Please fill all fields.");
            return;
        }

        try {
            Long weddingId;

            if (existingWeddingRadio.isSelected()) {
                WeddingDto selected = weddingListCombo.getValue();
                if (selected == null) {
                    showAlert("Please select a wedding.");
                    return;
                }
                weddingId = selected.getId();
            } else {
                String desc = newWeddingDescriptionField.getText();
                String loc = newWeddingLocationField.getText();
                LocalDate date = newWeddingDatePicker.getValue();

                if (desc.isBlank() || loc.isBlank() || date == null) {
                    showAlert("Please fill all wedding fields.");
                    return;
                }

                WeddingDto newWedding = new WeddingDto(null, desc, date, loc);
                WeddingDto created = weddingService.createWedding(newWedding);
                weddingId = created.getId();
            }

            UserDto user = new UserDto();
            user.setUsername(username);
            user.setPassword(password);
            user.setPermissionLevel(level);
            user.setWeddingId(weddingId);

            authService.register(user);
            showAlert("Signed up successfully!");
            Router.toHome();
        } catch (Exception e) {
            showAlert("Sign up failed: " + e.getMessage());
        }
    }

    @FXML
    private void goToHome() {
        Router.toHome();
    }

    private void showAlert(String msg) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION, msg, ButtonType.OK);
        alert.showAndWait();
    }
}
