package com.example.progtechjavafx;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;
import schedules.Schedule;
import schedules.ScheduleDatabaseManager;
import stations.Station;
import stations.StationDatabaseManager;
import train_lines.Line;
import train_lines.LineDatabaseManager;
import trains.*;

import java.io.IOException;
import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;
import java.util.List;

public class ScheduleController {
    @FXML
    private TableView<ScheduleDisplay> scheduleTableView;

    @FXML
    private TableColumn<ScheduleDisplay, String> startDestinationColumn;

    @FXML
    private TableColumn<ScheduleDisplay, String> finalDestinationColumn;

    @FXML
    private TableColumn<ScheduleDisplay, String> trainColumn;

    @FXML
    private TableColumn<ScheduleDisplay, String> startTimeColumn;

    @FXML
    private TableColumn<ScheduleDisplay, String> arrivalTimeColumn;

    @FXML
    private Button openTrainViewButton;

    @FXML
    private Button addButton;

    @FXML
    private Button deleteButton;

    private final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    public static class ScheduleDisplay {
        private final int id;
        private final String startDestination;
        private final String finalDestination;
        private final String train;
        private final String startTime;
        private final String arrivalTime;

        public ScheduleDisplay(int id, String startDestination, String finalDestination, String train, String startTime, String arrivalTime) {
            this.id = id;
            this.startDestination = startDestination;
            this.finalDestination = finalDestination;
            this.train = train;
            this.startTime = startTime;
            this.arrivalTime = arrivalTime;
        }

        public int getId(){
            return id;
        }

        public String getStartDestination() {
            return startDestination;
        }

        public String getFinalDestination() {
            return finalDestination;
        }

        public String getTrain() {
            return train;
        }

        public String getStartTime() {
            return startTime;
        }

        public String getArrivalTime() {
            return arrivalTime;
        }
    }

    @FXML
    private void initialize() {
        startDestinationColumn.setCellValueFactory(new PropertyValueFactory<>("startDestination"));
        finalDestinationColumn.setCellValueFactory(new PropertyValueFactory<>("finalDestination"));
        trainColumn.setCellValueFactory(new PropertyValueFactory<>("train"));
        startTimeColumn.setCellValueFactory(new PropertyValueFactory<>("startTime"));
        arrivalTimeColumn.setCellValueFactory(new PropertyValueFactory<>("arrivalTime"));

        loadScheduleData();
    }

    private void loadScheduleData() {
        List<Schedule> schedules = ScheduleDatabaseManager.getAllSchedules();
        ObservableList<ScheduleDisplay> scheduleDisplayList = FXCollections.observableArrayList();

        for (Schedule schedule : schedules) {
            Line line = LineDatabaseManager.getLine(schedule.getLineId());
            Station baseStation = StationDatabaseManager.getStation(line.getBase_station_id());
            Station finalStation = StationDatabaseManager.getStation(line.getFinal_station_id());
            String[] train = TrainDatabaseManager.loadFromDatabase(schedule.getTrainId());

            int scheduleId = schedule.getId();
            String startDestination = baseStation != null ? baseStation.getName() : "Unknown";
            String finalDestination = finalStation != null ? finalStation.getName() : "Unknown";
            String trainName = train != null ? train[0] : "Unknown";

            LocalDateTime startTime = LocalDateTime.now();
            LocalDateTime arrivalTime = startTime.plusMinutes(schedule.getTime());

            scheduleDisplayList.add(new ScheduleDisplay(
                    scheduleId,
                    startDestination,
                    finalDestination,
                    trainName,
                    startTime.format(dateTimeFormatter),
                    arrivalTime.format(dateTimeFormatter)
            ));
        }

        scheduleTableView.setItems(scheduleDisplayList);
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

    @FXML
    private void handleAddButtonAction(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("add-schedule-view.fxml"));
            Parent root = loader.load();

            Stage stage = new Stage();
            stage.setTitle("Add Schedule");
            stage.setScene(new Scene(root));

            stage.initModality(Modality.WINDOW_MODAL);
            stage.initOwner(addButton.getScene().getWindow());

            stage.showAndWait();

            loadScheduleData();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void handleDeleteButtonAction(ActionEvent event) {
        ScheduleDisplay selectedSchedule = scheduleTableView.getSelectionModel().getSelectedItem();
        if (selectedSchedule != null) {
            ScheduleDatabaseManager.deleteSchedule(selectedSchedule.getId());
            loadScheduleData();
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("No Selection");
            alert.setHeaderText(null);
            alert.setContentText("Please select a schedule to delete.");
            alert.showAndWait();
        }
    }
}
