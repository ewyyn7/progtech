package train_lines;

import databaseConnection.ConnectToDB;
import stations.Station;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class LineDatabaseManager {

    public static void addLine(int id,int length, int base_station_id, int final_station_id) {
        try { Connection conn = ConnectToDB.connect();
            String sql = "INSERT INTO train_lines (id, length, base_station_id, final_station_id) VALUES (?, ?, ?, ?)";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, id) ;
            stmt.setInt(2, length);
            stmt.setInt(3, base_station_id);
            stmt.setInt(4, final_station_id);
            stmt.executeUpdate();
            System.out.println(id + " line added to DB.");
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static List<Line> getAllLines() {

        List<Line> lines = new ArrayList<>();


        try {
            Connection conn = ConnectToDB.connect();
            Statement stmt = conn.createStatement();
            String sql = "SELECT * FROM train_lines";
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                int id = rs.getInt("id");
                int length = rs.getInt("length");
                int base_station_id = rs.getInt("base_station_id");
                int final_station_id=rs.getInt("final_station_id");
                lines.add(new Line(id,length,base_station_id, final_station_id));
            }
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return lines;
    }

    public static Line getLine(int id) {

        Line line = null;

        try {Connection conn = ConnectToDB.connect();
            String sql = "SELECT * FROM train_lines WHERE id = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {

                int length = rs.getInt("length");
                int base_station_id = rs.getInt("base_station_id");
                int final_station_id=rs.getInt("final_station_id");
                line = new Line(id, length,base_station_id,final_station_id);
            }
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return line;
    }




    public static void deleteLine(int id) {
        String sql = "DELETE FROM train_lines WHERE id = ?";

        try {Connection conn = ConnectToDB.connect();
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, id);
            stmt.executeUpdate();
            System.out.println("Line with ID " + id + " successfully deleted.");
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
