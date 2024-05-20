package com.example.progtechjavafx;

        import javafx.collections.FXCollections;
        import javafx.collections.ObservableList;
        import javafx.event.ActionEvent;
        import javafx.fxml.FXML;
        import javafx.scene.control.Alert;
        import javafx.scene.control.ComboBox;
        import javafx.scene.control.TextField;
        import javafx.stage.Stage;
        import trains.*;

public class AddTrainController {

    @FXML
    private ComboBox<String> trainTypeComboBox;

    @FXML
    private void initialize() {
        ObservableList<String> trainTypes = FXCollections.observableArrayList("Mixed Train", "Shuttle Train", "Mail Train");
        trainTypeComboBox.setItems(trainTypes);
    }

    @FXML
    private void handleAddButtonAction(ActionEvent event) {
        String selectedType = trainTypeComboBox.getValue();

        TrainBase newTrain;
        if ("Mixed Train".equals(selectedType)) {
            newTrain = new MixedTrain();
        } else if ("Shuttle Train".equals(selectedType)) {
            newTrain = new ShuttleTrain();
        } else if ("Mail Train".equals(selectedType)) {
            newTrain = new MailTrain();
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Invalid Train Type");
            alert.setHeaderText(null);
            alert.setContentText("Please select a valid train type.");
            alert.showAndWait();
            return;
        }

        TrainDatabaseManager.saveToDatabase(newTrain.getModel(),
                newTrain.GetAverageSpeed(), newTrain.GetSafetyLevel(), newTrain.GetNumberOfWagons());

        Stage stage = (Stage) trainTypeComboBox.getScene().getWindow();
        stage.close();

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Add Successful");
        alert.setHeaderText(null);
        alert.setContentText("The train has been successfully added.");
        alert.showAndWait();
    }
}