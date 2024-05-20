package com.example.progtechjavafx;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import trains.TrainBase;
import trains.TrainDatabaseManager;

import java.io.Console;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class TrainViewController implements Initializable{
    @FXML
    private TableView<TrainBase> trainTableView;

    @FXML
    private TableColumn<TrainBase, Integer> idColumn;

    @FXML
    private TableColumn<TrainBase, String> modelColumn;

    @FXML
    private TableColumn<TrainBase, Integer> averageSpeedColumn;

    @FXML
    private TableColumn<TrainBase, Integer> safetyLevelColumn;

    @FXML
    private TableColumn<TrainBase, Integer> numberOfWagonsColumn;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        idColumn.setCellValueFactory(cellData -> cellData.getValue().idProperty().asObject());
        modelColumn.setCellValueFactory(cellData -> cellData.getValue().modelProperty());
        averageSpeedColumn.setCellValueFactory(cellData -> cellData.getValue().averageSpeedProperty().asObject());
        safetyLevelColumn.setCellValueFactory(cellData -> cellData.getValue().safetyLevelProperty().asObject());
        numberOfWagonsColumn.setCellValueFactory(cellData -> cellData.getValue().numberOfWagonsProperty().asObject());

        List<TrainBase> trains = TrainDatabaseManager.getAllTrains();

        ObservableList<TrainBase> trainList = FXCollections.observableArrayList(trains);

        for (TrainBase train : trainList) {
            trainTableView.getItems().add(train);
        }

        trainTableView.refresh();
    }
}
