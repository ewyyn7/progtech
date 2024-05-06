package tester;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import databaseConnection.ConnectToDB;
public class Tester {
    public static void main(String[] args) {
        ConnectToDB dbCon = new ConnectToDB();
        Connection connection = dbCon.connect();
    }
}
