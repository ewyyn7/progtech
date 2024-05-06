package tester;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import databaseConnection.ConnectToDB;
public class Tester {
    public static void main(String[] args) {
        Connection connection = ConnectToDB.connect();
        if (connection != null) {
            try {
                String query = "SELECT * FROM trains";
                ResultSet resultSet = ConnectToDB.executeQuery(connection, query);

                while (resultSet.next()) {
                    int id = resultSet.getInt("id");
                    System.out.println("ID: " + id);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                ConnectToDB.close(connection);
            }
        }
    }
}
