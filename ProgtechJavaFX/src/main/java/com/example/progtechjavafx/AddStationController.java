package com.example.progtechjavafx;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;
import stations.Station;
import stations.StationDatabaseManager;
import trains.*;

public class AddStationController {

    @FXML
    private TextField nameTextField;

    @FXML
    private Button addButton;

    private void initialize() {

    }

    @FXML
    private void handleAddButtonAction(ActionEvent event) {
        String name = nameTextField.getText();

        StationDatabaseManager.addStation(new Station(name) );

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Add Successful");
        alert.setHeaderText(null);
        alert.setContentText("The station has been successfully added.");
        alert.showAndWait();

        // Close the window
        Stage stage = (Stage) addButton.getScene().getWindow();
        stage.close();
    }
}
