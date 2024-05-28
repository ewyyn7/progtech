package com.example.progtechjavafx;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;
import stations.Station;
import stations.StationDatabaseManager;
import train_lines.Line;
import train_lines.LineDatabaseManager;
import trains.*;
import java.util.Random;

public class AddLineController {

    @FXML
    private TextField lengthField;

    @FXML
    private TextField baseField;

    @FXML
    private TextField finalField;

    @FXML
    private Button addButton;


    private Random random=new Random();
    private void initialize() {

    }

    @FXML
    private void handleAddButtonAction(ActionEvent event) {
        int length = Integer.parseInt(lengthField.getText());
        int base = Integer.parseInt(baseField.getText());
        int finl = Integer.parseInt(finalField.getText());
        int id=random.nextInt(100000);

        LineDatabaseManager.addLine(id, length,base,finl);

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Add Successful");
        alert.setHeaderText(null);
        alert.setContentText("The line has been successfully added.");
        alert.showAndWait();

        // Close the window
        Stage stage = (Stage) addButton.getScene().getWindow();
        stage.close();
    }
}

