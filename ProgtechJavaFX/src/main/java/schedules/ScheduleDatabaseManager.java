package schedules;

import databaseConnection.ConnectToDB;
import trains.MailTrain;
import trains.MixedTrain;
import trains.ShuttleTrain;
import trains.TrainBase;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ScheduleDatabaseManager {
    public static void saveToDatabase(int trainId, int lineId, int time){
        Connection connection = null;
        try {
            connection = ConnectToDB.connect();
            String query = "INSERT INTO schedules (train_id, line_id, time) VALUES (?, ?, ?)";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, trainId);
            statement.setInt(2, lineId);
            statement.setInt(3, time);
            statement.executeUpdate();
            System.out.println("New schedule created and added to DB.");
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
            String query = "SELECT * FROM schedules WHERE id = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                int trainId = resultSet.getInt("train_id");
                int lineId = resultSet.getInt("line_id");
                int time = resultSet.getInt("time");
                return new String[]{String.valueOf(trainId), String.valueOf(lineId), String.valueOf(time)};
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

    public static List<Schedule> getAllSchedules() {
        List<Schedule> schedules = new ArrayList<>();
        Connection connection = null;
        Statement stmt = null;
        ResultSet rs = null;

        try {
            connection = ConnectToDB.connect();

            stmt = connection.createStatement();
            rs = stmt.executeQuery("SELECT * FROM schedules");

            while (rs.next()) {
                Schedule schedule = new Schedule();
                if (schedule != null) {
                    schedule.loadFromDatabase(rs.getInt("id"));
                    schedules.add(schedule);
                }
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

        return schedules;
    }

    public static void deleteSchedule(int scheduleId) {
        try (Connection connection = ConnectToDB.connect();
             PreparedStatement stmt = connection.prepareStatement("DELETE FROM schedules WHERE id = ?")) {
            stmt.setInt(1, scheduleId);
            stmt.executeUpdate();
            System.out.println("Schedule with ID " + scheduleId + " successfully deleted.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
