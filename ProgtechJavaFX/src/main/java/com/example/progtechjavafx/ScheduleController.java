package com.example.progtechjavafx;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import schedules.Schedule;
import schedules.ScheduleDatabaseManager;
import stations.Station;
import stations.StationDatabaseManager;
import train_lines.Line;
import train_lines.LineDatabaseManager;
import trains.*;

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

    private final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    public static class ScheduleDisplay {
        private final String startDestination;
        private final String finalDestination;
        private final String train;
        private final String startTime;
        private final String arrivalTime;

        public ScheduleDisplay(String startDestination, String finalDestination, String train, String startTime, String arrivalTime) {
            this.startDestination = startDestination;
            this.finalDestination = finalDestination;
            this.train = train;
            this.startTime = startTime;
            this.arrivalTime = arrivalTime;
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

            String startDestination = baseStation != null ? baseStation.getName() : "Unknown";
            String finalDestination = finalStation != null ? finalStation.getName() : "Unknown";
            String trainName = train != null ? train[0] : "Unknown";

            LocalDateTime startTime = LocalDateTime.now();
            LocalDateTime arrivalTime = startTime.plusMinutes(schedule.getTime());

            scheduleDisplayList.add(new ScheduleDisplay(
                    startDestination,
                    finalDestination,
                    trainName,
                    startTime.format(dateTimeFormatter),
                    arrivalTime.format(dateTimeFormatter)
            ));
        }

        scheduleTableView.setItems(scheduleDisplayList);
    }
}
