package com.easywedding.application.controllers;

import com.easywedding.application.dtos.GuestDto;
import com.easywedding.application.services.GuestService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.util.List;

public class GuestsController {

    @FXML
    private TableView<GuestDto> guestsTable;

    @FXML
    private TableColumn<GuestDto, Long> idColumn;

    @FXML
    private TableColumn<GuestDto, String> nameColumn;

    @FXML
    private TableColumn<GuestDto, String> phoneColumn;

    @FXML
    private TableColumn<GuestDto, Boolean> attendingColumn;

    @FXML
    private TableColumn<GuestDto, Integer> tableNumberColumn;

    @FXML
    private TableColumn<GuestDto, String> notesColumn;

    private final GuestService guestService = new GuestService();

    @FXML
    public void initialize() {
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        phoneColumn.setCellValueFactory(new PropertyValueFactory<>("phone"));
        attendingColumn.setCellValueFactory(new PropertyValueFactory<>("attending"));
        tableNumberColumn.setCellValueFactory(new PropertyValueFactory<>("tableNumber"));
        notesColumn.setCellValueFactory(new PropertyValueFactory<>("notes"));

        loadGuests();
    }

    private void loadGuests() {
        List<GuestDto> guests = guestService.getAllGuests();
        ObservableList<GuestDto> guestList = FXCollections.observableArrayList(guests);
        guestsTable.setItems(guestList);
    }
}
