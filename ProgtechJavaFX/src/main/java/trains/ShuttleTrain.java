package trains;

import databaseConnection.ConnectToDB;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.PreparedStatement;

public class ShuttleTrain extends TrainBase {
    protected int id;
    private String model = "Shuttle Train";
    private int averageSpeed = 140;
    private int safetyLevel = 10;
    private int numberOfWagons = 3;

    @Override
    public int getId() {
        return id;
    }

    @Override
    public String getModel() {
        return model;
    }

    @Override
    public int GetAverageSpeed() {
        return averageSpeed;
    }

    @Override
    public int GetSafetyLevel() {
        return safetyLevel;
    }

    @Override
    public int GetNumberOfWagons() {
        return numberOfWagons;
    }

    @Override
    public void saveToDatabase() {
        TrainDatabaseManager.saveToDatabase(model, averageSpeed, safetyLevel, numberOfWagons);
    }

    @Override
    public void loadFromDatabase(int id) {
            String[] result = TrainDatabaseManager.loadFromDatabase(id);
            if (result != null) {
                this.id = id;
                this.model = result[0];
                this.averageSpeed = Integer.parseInt(result[1]);
                this.safetyLevel = Integer.parseInt(result[2]);
                this.numberOfWagons = Integer.parseInt(result[3]);
            }
    }

    @Override
    public String toString() {
        return "ShuttleTrain{" +
                "id=" + id +
                ", model='" + model + '\'' +
                ", averageSpeed=" + averageSpeed +
                ", safetyLevel=" + safetyLevel +
                ", numberOfWagons=" + numberOfWagons +
                '}';
    }
}
