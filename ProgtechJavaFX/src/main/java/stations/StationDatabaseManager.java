package stations;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import databaseConnection.ConnectToDB;

public class StationDatabaseManager {


    public static void addStation(Station station) {


        try { Connection conn = ConnectToDB.connect();
            String sql = "INSERT INTO stations (name) VALUES (?)";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, station.getName());
            stmt.executeUpdate();
            System.out.println(station.getName() + " station added to DB.");
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static Station getStation(int id) {

        Station station = null;

        try {Connection conn = ConnectToDB.connect();
            String sql = "SELECT * FROM stations WHERE id = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                String name = rs.getString("name");
                station = new Station(id, name);
            }
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return station;
    }

    public static List<Station> getAllStations() {

        List<Station> stations = new ArrayList<>();


        try {
            Connection conn = ConnectToDB.connect();
            Statement stmt = conn.createStatement();
            String sql = "SELECT * FROM stations";
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                stations.add(new Station(id, name));
            }
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return stations;
    }

    public static void updateStation(Station station) {
        String sql = "UPDATE stations SET name = ? WHERE id = ?";

        try {Connection conn = ConnectToDB.connect();
             PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, station.getName());
            stmt.setInt(2, station.getId());
            stmt.executeUpdate();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void deleteStation(int id) {
        String sql = "DELETE FROM stations WHERE id = ?";

        try {Connection conn = ConnectToDB.connect();
             PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, id);
            stmt.executeUpdate();
            System.out.println("Station with ID " + id + " successfully deleted.");
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
