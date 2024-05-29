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
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;
import trains.EquipDiningCar;
import trains.EquipSnowplough;
import trains.TrainBase;
import trains.TrainDatabaseManager;

import java.io.Console;
import java.io.IOException;
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

    @FXML
    private Button deleteTrainBtn;

    @FXML
    private Button addTrainBtn;

    @FXML
    private Button equipDiningCarBtn;

    @FXML
    private Button equipSnowploughBtn;

    @FXML
    private Button openStationViewBtn;

    @FXML
    private Button openTrainLineViewBtn;

    @FXML
    private Button openScheduleViewBtn;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        idColumn.setCellValueFactory(cellData -> cellData.getValue().idProperty().asObject());
        modelColumn.setCellValueFactory(cellData -> cellData.getValue().modelProperty());
        averageSpeedColumn.setCellValueFactory(cellData -> cellData.getValue().averageSpeedProperty().asObject());
        safetyLevelColumn.setCellValueFactory(cellData -> cellData.getValue().safetyLevelProperty().asObject());
        numberOfWagonsColumn.setCellValueFactory(cellData -> cellData.getValue().numberOfWagonsProperty().asObject());

        loadTrainData();
    }

    private void loadTrainData() {
        List<TrainBase> trains = TrainDatabaseManager.getAllTrains();
        ObservableList<TrainBase> trainList = FXCollections.observableArrayList(trains);
        trainTableView.setItems(trainList);
    }

    @FXML
    private void onDeleteButtonClick(ActionEvent event) {
        TrainBase selectedTrain = trainTableView.getSelectionModel().getSelectedItem();
        if (selectedTrain != null) {
            TrainDatabaseManager.deleteTrain(selectedTrain.getId());

            trainTableView.getItems().remove(selectedTrain);
        }
        else {
            Alert warningAlert = new Alert(Alert.AlertType.WARNING);
            warningAlert.setTitle("No Selection");
            warningAlert.setHeaderText(null);
            warningAlert.setContentText("Please select a train to delete.");
            warningAlert.showAndWait();
        }
    }

    @FXML
    private void onAddButtonClick(ActionEvent event) {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("add-train-view.fxml"));
                Parent root = loader.load();

                Stage stage = new Stage();
                stage.setTitle("Add Train");
                stage.setScene(new Scene(root));

                stage.initModality(Modality.WINDOW_MODAL);
                stage.initOwner(addTrainBtn.getScene().getWindow());

                stage.showAndWait();

                loadTrainData();
            } catch (IOException e) {
                e.printStackTrace();
            }
    }

    @FXML
    private void onEquipDiningCarButtonClick(ActionEvent event) {
        TrainBase selectedTrain = trainTableView.getSelectionModel().getSelectedItem();
        if (selectedTrain != null) {
            TrainBase decoratedTrain = new EquipDiningCar(selectedTrain);
            decoratedTrain.saveToDatabase();

            loadTrainData();

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Train Decorated");
            alert.setHeaderText(null);
            alert.setContentText("The selected train has been equipped with a dining car.");
            alert.showAndWait();
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("No Train Selected");
            alert.setHeaderText(null);
            alert.setContentText("Please select a train to equip with a dining car.");
            alert.showAndWait();
        }
    }

    @FXML
    private void onEquipSnowploughButtonClick(ActionEvent event) {
        TrainBase selectedTrain = trainTableView.getSelectionModel().getSelectedItem();
        if (selectedTrain != null) {
            TrainBase decoratedTrain = new EquipSnowplough(selectedTrain);
            decoratedTrain.saveToDatabase();

            loadTrainData();

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Train Decorated");
            alert.setHeaderText(null);
            alert.setContentText("The selected train has been equipped with a snowplough.");
            alert.showAndWait();
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("No Train Selected");
            alert.setHeaderText(null);
            alert.setContentText("Please select a train to equip with a snowplough.");
            alert.showAndWait();
        }
    }

    @FXML
    private void onOpenTrainLineViewButtonClick(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("train_line_view.fxml"));
            Parent root = loader.load();

            Stage mainStage = (Stage) openTrainLineViewBtn.getScene().getWindow();

            Stage stationStage = new Stage();
            stationStage.setTitle("Train Line View");
            stationStage.setScene(new Scene(root));

            stationStage.show();

            mainStage.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void onOpenStationViewButtonClick(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("station-view.fxml"));
            Parent root = loader.load();

            Stage mainStage = (Stage) openStationViewBtn.getScene().getWindow();

            Stage stationStage = new Stage();
            stationStage.setTitle("Station View");
            stationStage.setScene(new Scene(root));

            stationStage.show();

            mainStage.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void onOpenScheduleViewButtonClick(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("schedule-view.fxml"));
            Parent root = loader.load();

            Stage mainStage = (Stage) openScheduleViewBtn.getScene().getWindow();

            Stage stationStage = new Stage();
            stationStage.setTitle("Schedule View");
            stationStage.setScene(new Scene(root));

            stationStage.show();

            mainStage.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
