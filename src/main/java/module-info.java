module com.example.testukrclock {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.testukrclock to javafx.fxml;
    exports com.example.testukrclock;
}