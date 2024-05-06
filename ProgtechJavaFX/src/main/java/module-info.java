module com.example.progtechjavafx {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.progtechjavafx to javafx.fxml;
    exports com.example.progtechjavafx;
}