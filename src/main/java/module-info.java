module com.example.homework {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires sqlite.jdbc;
    requires org.junit.jupiter.api;


    opens com.example.homework to javafx.fxml;
    exports com.example.homework;
}