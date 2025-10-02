package com.easywedding.application.controllers;

import com.easywedding.application.dtos.GuestDto;
import com.easywedding.application.services.GuestService;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.util.List;
import java.util.Optional;

/**
 * JavaFX controller for managing guests (client-side).
 * Aligned with server WebAPI endpoints in GuestController:
 *  - GET    /api/guests/wedding/{weddingId}
 *  - GET    /api/guests/{id}
 *  - GET    /api/guests/search?name=...
 *  - GET    /api/guests/table/{tableNumber}
 *  - GET    /api/guests/table/{tableNumber}/count
 *  - POST   /api/guests
 *  - PUT    /api/guests/{id}
 *  - DELETE /api/guests/{id}
 *
 * All HTTP calls run on a background thread (Task) to keep the UI responsive.
 */
public class GuestController {

    // ===== UI controls (bind in FXML) =====
    @FXML private TableView<GuestDto> guestsTable;

    @FXML private TableColumn<GuestDto, Long> idColumn;
    @FXML private TableColumn<GuestDto, String> nameColumn;
    @FXML private TableColumn<GuestDto, String> phoneColumn;
    @FXML private TableColumn<GuestDto, Boolean> attendingColumn;
    @FXML private TableColumn<GuestDto, Integer> tableNumberColumn;
    @FXML private TableColumn<GuestDto, String> notesColumn;

    @FXML private TextField searchField;       // for /search?name=
    @FXML private TextField tableFilterField;  // for /table/{tableNumber}
    @FXML private Label tableCountLabel;       // shows /table/{tableNumber}/count
    @FXML private Button reloadButton;         // reload list
    @FXML private Button addButton;            // create guest
    @FXML private Button editButton;           // update selected guest
    @FXML private Button deleteButton;         // delete selected guest

    // ===== State & services =====
    private final ObservableList<GuestDto> model = FXCollections.observableArrayList();

    /** Base service that calls the WebAPI; parameterized by weddingId. */
    private GuestService guestService;

    /** Current wedding context (can be injected externally). */
    private long weddingId = Long.getLong("easywedding.weddingId", 1L); // fallback if not provided

    @FXML
    public void initialize() {
        // Bind table columns
//        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        phoneColumn.setCellValueFactory(new PropertyValueFactory<>("phone"));
        attendingColumn.setCellValueFactory(new PropertyValueFactory<>("attending"));
        tableNumberColumn.setCellValueFactory(new PropertyValueFactory<>("tableNumber"));
        notesColumn.setCellValueFactory(new PropertyValueFactory<>("notes"));

        guestsTable.setItems(model);

        // Init service with current weddingId
        this.guestService = new GuestService(weddingId);

        // First load
        reloadGuestsByWedding();
    }

    /** Allows setting the weddingId from outside (e.g., after login). */
    public void setWeddingId(long weddingId) {
        this.weddingId = weddingId;
        if (this.guestService == null) {
            this.guestService = new GuestService(weddingId);
        } else {
            this.guestService.setWeddingId(weddingId);
        }
    }

    // ===== UI event handlers =====

    @FXML
    private void onReloadClick() {
        reloadGuestsByWedding();
    }

    @FXML
    private void onSearchClick() {
        String name = Optional.ofNullable(searchField.getText()).orElse("").trim();
        if (name.isEmpty()) {
            reloadGuestsByWedding();
            return;
        }
        runAsync(
                () -> guestService.searchByName(name),
                this::setTableItemsSafe,
                ex -> showError("Search failed", ex)
        );
    }

    @FXML
    private void onFilterByTableClick() {
        String raw = Optional.ofNullable(tableFilterField.getText()).orElse("").trim();
        if (raw.isEmpty()) {
            reloadGuestsByWedding();
            return;
        }
        try {
            long tableNumber = Long.parseLong(raw);
            runAsync(
                    () -> guestService.getByTableNumber(tableNumber),
                    list -> {
                        setTableItemsSafe(list);
                        // also pull count for this table
                        runAsync(
                                () -> guestService.countByTableNumber(tableNumber),
                                count -> Platform.runLater(() -> tableCountLabel.setText("Count: " + count)),
                                ex -> Platform.runLater(() -> tableCountLabel.setText("Count: N/A"))
                        );
                    },
                    ex -> showError("Table filter failed", ex)
            );
        } catch (NumberFormatException nfe) {
            showWarn("Invalid table number: " + raw);
        }
    }

    @FXML
    private void onAddClick() {
        // Example placeholder; replace with a dialog form in real app
        GuestDto dto = new GuestDto();
        dto.setName("New Guest");
        dto.setPhone("050-0000000");
        dto.setAttending(true);
        dto.setTableNumber(1);
        dto.setNotes("");

        runAsync(
                () -> guestService.createGuest(dto),
                created -> {
                    showInfo("Guest created (ID=" + created.getId() + ")");
                    reloadGuestsByWedding();
                },
                ex -> showError("Create failed", ex)
        );
    }

    @FXML
    private void onEditClick() {
        GuestDto selected = guestsTable.getSelectionModel().getSelectedItem();
        if (selected == null) {
            showWarn("Please select a guest to edit.");
            return;
        }
        // Example: toggle attending. Replace with proper edit dialog.
        selected.setAttending(Boolean.TRUE.equals(selected.getAttending()) ? Boolean.FALSE : Boolean.TRUE);

        runAsync(
                () -> guestService.updateGuest(selected.getId(), selected),
                updated -> {
                    showInfo("Guest updated (ID=" + updated.getId() + ")");
                    reloadGuestsByWedding();
                },
                ex -> showError("Update failed", ex)
        );
    }

    @FXML
    private void onDeleteClick() {
        GuestDto selected = guestsTable.getSelectionModel().getSelectedItem();
        if (selected == null) {
            showWarn("Please select a guest to delete.");
            return;
        }
        if (!confirm("Delete guest ID " + selected.getId() + "?")) return;

        runAsync(
                () -> { guestService.deleteGuest(selected.getId()); return null; },
                ignored -> {
                    showInfo("Guest deleted.");
                    reloadGuestsByWedding();
                },
                ex -> showError("Delete failed", ex)
        );
    }

    // ===== Internals =====

    /** Reload table with guests of the current wedding. */
    private void reloadGuestsByWedding() {
        runAsync(
                guestService::getByWeddingId,
                this::setTableItemsSafe,
                ex -> showError("Failed to load guests for weddingId=" + weddingId, ex)
        );
    }

    /** Safely set TableView items on the FX Application Thread. */
    private void setTableItemsSafe(List<GuestDto> items) {
        Platform.runLater(() -> {
            model.setAll(items);
            guestsTable.refresh();
        });
    }

    /** Run a supplier on a background thread; dispatch success/error on FX thread. */
    private <T> void runAsync(SupplierWithException<T> supplier,
                              java.util.function.Consumer<T> onSuccess,
                              java.util.function.Consumer<Throwable> onError) {
        Task<T> task = new Task<>() {
            @Override protected T call() throws Exception { return supplier.get(); }
        };
        task.setOnSucceeded(e -> onSuccess.accept(task.getValue()));
        task.setOnFailed(e -> onError.accept(task.getException()));
        new Thread(task, "Guests-IO").start();
    }

    // Simple dialog helpers
    private void showInfo(String msg) { Platform.runLater(() -> new Alert(Alert.AlertType.INFORMATION, msg).showAndWait()); }
    private void showWarn(String msg) { Platform.runLater(() -> new Alert(Alert.AlertType.WARNING, msg).showAndWait()); }
    private void showError(String title, Throwable ex) {
        Platform.runLater(() -> {
            Alert a = new Alert(Alert.AlertType.ERROR);
            a.setHeaderText(title);
            a.setContentText(ex.getMessage());
            a.showAndWait();
        });
    }
    private boolean confirm(String msg) {
        Alert a = new Alert(Alert.AlertType.CONFIRMATION, msg, ButtonType.OK, ButtonType.CANCEL);
        return a.showAndWait().orElse(ButtonType.CANCEL) == ButtonType.OK;
    }

    /** Functional interface to allow lambdas that throw checked exceptions. */
    @FunctionalInterface
    private interface SupplierWithException<T> {
        T get() throws Exception;
    }
}
