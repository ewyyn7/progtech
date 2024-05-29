package com.example.progtechjavafx;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;
import javafx.util.StringConverter;
import stations.Station;
import stations.StationDatabaseManager;
import train_lines.LineDatabaseManager;

import java.io.Console;
import java.util.List;
import java.util.Random;

public class AddLineController {

    @FXML
    private TextField lengthField;

    @FXML
    private ComboBox<Station> baseComboBox;

    @FXML
    private ComboBox<Station> finalComboBox;

    @FXML
    private Button addButton;


    private Random random=new Random();

    @FXML
    public void initialize() {
        List<Station> stations = StationDatabaseManager.getAllStations();
        ObservableList<Station> stationList = FXCollections.observableArrayList(stations);

        baseComboBox.setItems(stationList);
        finalComboBox.setItems(stationList);

        baseComboBox.setCellFactory(param -> new ListCell<>() {
            @Override
            protected void updateItem(Station item, boolean empty) {
                super.updateItem(item, empty);
                setText(empty ? "" : item.getName());
            }
        });

        finalComboBox.setCellFactory(param -> new ListCell<>() {
            @Override
            protected void updateItem(Station item, boolean empty) {
                super.updateItem(item, empty);
                setText(empty ? "" : item.getName());
            }
        });

        baseComboBox.setConverter(new StringConverter<>() {
            @Override
            public String toString(Station station) {
                return station != null ? station.getName() : "";
            }

            @Override
            public Station fromString(String string) {
                return null;
            }
        });

        finalComboBox.setConverter(new StringConverter<>() {
            @Override
            public String toString(Station station) {
                return station != null ? station.getName() : "";
            }

            @Override
            public Station fromString(String string) {
                return null;
            }
        });
    }

    @FXML
    private void handleAddButtonAction(ActionEvent event) {
        try {
            int length = Integer.parseInt(lengthField.getText());
            Station baseStation = baseComboBox.getSelectionModel().getSelectedItem();
            Station finalStation = finalComboBox.getSelectionModel().getSelectedItem();

            if (baseStation == null || finalStation == null) {
                throw new IllegalArgumentException("Base and final stations must be selected.");
            }
            if (length < 0){
                throw new IllegalArgumentException("Length can't be negative.");
            }
            if (baseStation == finalStation){
                throw new IllegalArgumentException("Base and final stations can't be the same.");
            }

            int baseStationId = baseStation.getId();
            int finalStationId = finalStation.getId();
            int id = random.nextInt(100000);

            LineDatabaseManager.addLine(id, length, baseStationId, finalStationId);

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Add Successful");
            alert.setHeaderText(null);
            alert.setContentText("The line has been successfully added.");
            alert.showAndWait();

            Stage stage = (Stage) addButton.getScene().getWindow();
            stage.close();
        } catch (NumberFormatException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Input Error");
            alert.setHeaderText(null);
            alert.setContentText("Please enter a valid length.");
            alert.showAndWait();
        } catch (IllegalArgumentException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Selection Error");
            alert.setHeaderText(null);
            alert.setContentText(e.getMessage());
            alert.showAndWait();
        }
    }
}

