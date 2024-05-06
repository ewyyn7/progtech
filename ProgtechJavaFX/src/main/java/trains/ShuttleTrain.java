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
        Connection connection = null;
        try {
            connection = ConnectToDB.connect();
            String query = "INSERT INTO trains (model, average_speed, safety_level, number_of_wagons) VALUES (?, ?, ?, ?)";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, model);
            statement.setInt(2, averageSpeed);
            statement.setInt(3, safetyLevel);
            statement.setInt(4, numberOfWagons);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void loadFromDatabase(int id) {
        Connection connection = null;
        try {
            connection = ConnectToDB.connect();
            String query = "SELECT * FROM trains WHERE id = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                this.id = id;
                this.model = resultSet.getString("model");
                this.averageSpeed = resultSet.getInt("average_speed");
                this.safetyLevel = resultSet.getInt("safety_level");
                this.numberOfWagons = resultSet.getInt("number_of_wagons");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
