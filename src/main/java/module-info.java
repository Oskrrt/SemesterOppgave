module com.sample {
    requires javafx.controls;
    requires javafx.fxml;

    opens com.sample.controllers to javafx.fxml;
    exports com.sample;
}