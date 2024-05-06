package trains;

import databaseConnection.ConnectToDB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TrainDatabaseManager {
    public static void saveToDatabase(String model, int averageSpeed, int safetyLevel, int numberOfWagons){
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

    public static void updateToDatabase(int id, String model, int averageSpeed, int safetyLevel, int numberOfWagons){
        Connection connection = null;
        try {
            connection = ConnectToDB.connect();
            String query = "UPDATE trains SET model = ?, average_speed = ?, " +
                    "safety_level = ?, number_of_wagons = ? WHERE id = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, model);
            statement.setInt(2, averageSpeed);
            statement.setInt(3, safetyLevel);
            statement.setInt(4, numberOfWagons);
            statement.setInt(5, id);
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

    public static String[] loadFromDatabase(int id){
        Connection connection = null;
        try {
            connection = ConnectToDB.connect();
            String query = "SELECT * FROM trains WHERE id = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                String model = resultSet.getString("model");
                int averageSpeed = resultSet.getInt("average_speed");
                int safetyLevel = resultSet.getInt("safety_level");
                int numberOfWagons = resultSet.getInt("number_of_wagons");
                return new String[]{model, String.valueOf(averageSpeed), String.valueOf(safetyLevel), String.valueOf(numberOfWagons)};
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
        return null;
    }
}
