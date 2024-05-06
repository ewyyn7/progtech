package tester;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import databaseConnection.ConnectToDB;
import trains.ShuttleTrain;

public class Tester {
    public static void main(String[] args) {
        Connection connection = ConnectToDB.connect();
        if (connection != null) {
            try {

                ShuttleTrain shuttleTrain = new ShuttleTrain();
                //shuttleTrain.saveToDatabase();
                shuttleTrain.loadFromDatabase(3);
                System.out.println(shuttleTrain);

            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                ConnectToDB.close(connection);
            }
        }
    }
}
