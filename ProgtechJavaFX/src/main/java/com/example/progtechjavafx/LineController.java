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
import train_lines.*;
import trains.EquipDiningCar;
import trains.TrainBase;
import trains.TrainDatabaseManager;


import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
public class LineController implements Initializable {


    @FXML
    private TableView<Line> lineTableView;

    @FXML
    private TableColumn<Line, Integer> idColumn;

    @FXML
    private TableColumn<Line, Integer> lengthColumn;
    @FXML
    private TableColumn<Line, Integer> baseColumn;
    @FXML
    private TableColumn<Line, Integer> finalColumn;
    @FXML
    private Button checkButton;
    @FXML
    private Button addButton;

    @FXML
    private Button openStationViewBtn;

    @FXML
    private Button openTrainViewBtn;

    @FXML
    private Button openScheduleViewBtn;



    public void initialize(URL location, ResourceBundle resources) {
        idColumn.setCellValueFactory(cellData -> cellData.getValue().idProperty().asObject());
        lengthColumn.setCellValueFactory(cellData -> cellData.getValue().lengthProperty().asObject());
        baseColumn.setCellValueFactory(cellData -> cellData.getValue().baseProperty().asObject());
        finalColumn.setCellValueFactory(cellData -> cellData.getValue().finalProperty().asObject());


        loadLineData();
    }

    private void loadLineData() {
        List<Line> lines = LineDatabaseManager.getAllLines();
        ObservableList<Line> lineList = FXCollections.observableArrayList(lines);
        lineTableView.setItems(lineList);
    }

    @FXML
    private void onCheck(ActionEvent event) {
        Line line = lineTableView.getSelectionModel().getSelectedItem();
        if (line != null) {
            line.RegisterObserver(new Maintenance("1"));
            line.RegisterObserver(new Monitoring("2"));



            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Check result");
            alert.setHeaderText(null);
            alert.setContentText(line.Check());
            alert.showAndWait();
            loadLineData();
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("No line selected");
            alert.setHeaderText(null);
            alert.setContentText("Please select a line.");
            alert.showAndWait();
        }
    }

    @FXML
    private void onAddButtonClick(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("add_line_view.fxml"));
            Parent root = loader.load();

            Stage stage = new Stage();
            stage.setTitle("Add station");
            stage.setScene(new Scene(root));

            stage.initModality(Modality.WINDOW_MODAL);
            stage.initOwner(addButton.getScene().getWindow());

            stage.showAndWait();

            loadLineData();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void onDeleteButtonClick(ActionEvent event) {
        Line selectedLine = lineTableView.getSelectionModel().getSelectedItem();
        if (selectedLine != null) {
            LineDatabaseManager.deleteLine(selectedLine.getId());

            lineTableView.getItems().remove(selectedLine);
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

            Stage mainStage = (Stage) openTrainViewBtn.getScene().getWindow();

            Stage stationStage = new Stage();
            stationStage.setTitle("Train View");
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
