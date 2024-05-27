package com.example.progtechjavafx;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Modality;
import javafx.stage.Stage;
import stations.Station;
import stations.StationDatabaseManager;
import trains.TrainBase;
import trains.TrainDatabaseManager;


import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
public class StationController implements Initializable {

    @FXML
    private Button deleteButton;

    @FXML
    private Button addButton;
    @FXML
    private TableView<Station> stationTableView;

    @FXML
    private TableColumn<Station, Integer> idColumn;

    @FXML
    private TableColumn<Station, String> nameColumn;

    @FXML
    private Button openTrainViewButton;

    public void initialize(URL location, ResourceBundle resources) {
        idColumn.setCellValueFactory(cellData -> cellData.getValue().idProperty().asObject());
        nameColumn.setCellValueFactory(cellData -> cellData.getValue().nameProperty());

        loadStationData();
    }

    private void loadStationData() {
        List<Station> stations = StationDatabaseManager.getAllStations();
        ObservableList<Station> stationList = FXCollections.observableArrayList(stations);
        stationTableView.setItems(stationList);
    }

    @FXML
    private void onAddButtonClick(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("add-station-view.fxml"));
            Parent root = loader.load();

            Stage stage = new Stage();
            stage.setTitle("Add station");
            stage.setScene(new Scene(root));

            stage.initModality(Modality.WINDOW_MODAL);
            stage.initOwner(addButton.getScene().getWindow());

            stage.showAndWait();

            loadStationData();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @FXML
    private void onDeleteButtonClick(ActionEvent event) {
        Station selectedStation = stationTableView.getSelectionModel().getSelectedItem();
        if (selectedStation != null) {
            StationDatabaseManager.deleteStation(selectedStation.getId());

            stationTableView.getItems().remove(selectedStation);
        } else {
            Alert warningAlert = new Alert(Alert.AlertType.WARNING);
            warningAlert.setTitle("No Selection");
            warningAlert.setHeaderText(null);
            warningAlert.setContentText("Please select a train to delete.");
            warningAlert.showAndWait();
        }
    }

    @FXML
    private void onOpenTrainViewButtonClick(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("train-view.fxml"));
            Parent root = loader.load();

            Stage mainStage = (Stage) openTrainViewButton.getScene().getWindow();

            Stage stationStage = new Stage();
            stationStage.setTitle("Train View");
            stationStage.setScene(new Scene(root));

            stationStage.show();

            mainStage.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
