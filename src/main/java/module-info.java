module com.adair.cgdemo {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.adair.cgdemo to javafx.fxml;
    exports com.adair.cgdemo;
}