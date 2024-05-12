package tester;
/*import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;*/

import databaseConnection.ConnectToDB;
import trains.EquipDiningCar;
import trains.EquipSnowplough;
import trains.ShuttleTrain;


import train_lines.*;




public class Tester {
    public static void main(String[] args) {

        Line line = new Line("123");
        line.RegisterObserver(new Maintenance("1"));
        line.RegisterObserver(new Monitoring("2"));

        line.Check();

        /*
        Connection connection = ConnectToDB.connect();
        if (connection != null) {
            try {

                ShuttleTrain shuttleTrain = new ShuttleTrain();
                //shuttleTrain.saveToDatabase();
                shuttleTrain.loadFromDatabase(3);
                EquipSnowplough equipSnowplough = new EquipSnowplough(shuttleTrain);
                equipSnowplough.saveToDatabase();
                shuttleTrain.loadFromDatabase(3);
                System.out.println(shuttleTrain);
                System.out.println(equipSnowplough);
                EquipDiningCar equipDiningCar = new EquipDiningCar(shuttleTrain);
                equipDiningCar.saveToDatabase();
                shuttleTrain.loadFromDatabase(3);
                System.out.println(shuttleTrain);

            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                ConnectToDB.close(connection);
            }
        }*/
    }
}
