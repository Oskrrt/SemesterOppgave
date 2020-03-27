module com.sample {
    requires javafx.controls;
    requires javafx.fxml;
    requires unirest.java;
    requires java.sql;
    requires com.google.gson;
    requires commons.codec;

    opens com.sample.controllers to javafx.fxml;
    exports com.sample;
}