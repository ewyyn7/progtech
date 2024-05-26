package com.example.progtechjavafx;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import stations.Station;
import stations.StationDatabaseManager;


import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
public class StationController implements Initializable{

    @FXML
    private TableView<Station> stationTableView;

    @FXML
    private TableColumn<Station, Integer> idColumn;

    @FXML
    private TableColumn<Station, String> nameColumn;
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
}
