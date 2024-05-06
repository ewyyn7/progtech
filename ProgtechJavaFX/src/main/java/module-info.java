module com.example.progtechjavafx {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens com.example.progtechjavafx to javafx.fxml;
    exports com.example.progtechjavafx;
}