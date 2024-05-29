package com.example.progtechjavafx;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;
import javafx.util.StringConverter;
import schedules.Schedule;
import schedules.ScheduleDatabaseManager;
import stations.Station;
import stations.StationDatabaseManager;
import train_lines.Line;
import train_lines.LineDatabaseManager;
import trains.TrainBase;
import trains.TrainDatabaseManager;

import java.util.List;
import java.util.Random;

public class AddScheduleController {
    @FXML
    private ComboBox<TrainBase> trainComboBox;

    @FXML
    private ComboBox<Line> lineComboBox;

    @FXML
    private TextField timeField;

    @FXML
    private Button addButton;


    @FXML
    private void initialize() {
        List<TrainBase> trains = TrainDatabaseManager.getAllTrains();
        ObservableList<TrainBase> trainList = FXCollections.observableArrayList(trains);
        trainComboBox.setItems(trainList);

        List<Line> lines = LineDatabaseManager.getAllLines();
        ObservableList<Line> lineList = FXCollections.observableArrayList(lines);
        lineComboBox.setItems(lineList);

        lineComboBox.setCellFactory(param -> new ListCell<>() {
            @Override
            protected void updateItem(Line item, boolean empty) {
                super.updateItem(item, empty);
                setText(empty ? "" : "Line " + item.getId());
            }
        });

        lineComboBox.setConverter(new StringConverter<>() {
            @Override
            public String toString(Line line) {
                return line != null ? "Line " + line.getId() : "";
            }

            @Override
            public Line fromString(String string) {
                return null;
            }
        });

        trainComboBox.setCellFactory(param -> new ListCell<>() {
            @Override
            protected void updateItem(TrainBase item, boolean empty) {
                super.updateItem(item, empty);
                setText(empty ? "" : "Train " + item.getId());
            }
        });

        trainComboBox.setConverter(new StringConverter<>() {
            @Override
            public String toString(TrainBase train) {
                return train != null ? "Train " + train.getId() : "";
            }

            @Override
            public TrainBase fromString(String string) {
                return null;
            }
        });
    }

    @FXML
    private void handleAddButtonAction(ActionEvent event) {
        try {
            TrainBase selectedTrain = trainComboBox.getSelectionModel().getSelectedItem();
            Line selectedLine = lineComboBox.getSelectionModel().getSelectedItem();
            int time = Integer.parseInt(timeField.getText());

            if (time < 0){
                throw new IllegalArgumentException("Time must be above 0.");
            }
            if (selectedTrain == null || selectedLine == null) {
                throw new IllegalArgumentException("Train and line must be selected.");
            }

            ScheduleDatabaseManager.saveToDatabase(selectedTrain.getId(), selectedLine.getId(), time);

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Add Successful");
            alert.setHeaderText(null);
            alert.setContentText("The schedule has been successfully added.");
            alert.showAndWait();

            Stage stage = (Stage) addButton.getScene().getWindow();
            stage.close();
        } catch (NumberFormatException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Input Error");
            alert.setHeaderText(null);
            alert.setContentText("Please enter a valid time in minutes.");
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