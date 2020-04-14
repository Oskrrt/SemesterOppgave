module com.sample {
    requires javafx.controls;
    requires javafx.fxml;
    requires unirest.java;
    requires java.sql;
    requires com.google.gson;
    requires commons.codec;

    opens com.sample.controllers to javafx.fxml;
    opens com.sample.controllers.addedComponentControllers to javafx.fxml;
    opens com.sample.controllers.chooseComponentControllers to javafx.fxml;
    opens com.sample.Models.Computer to javafx.base;
    opens com.sample.Models.ComputerComponents to javafx.base;
    opens com.sample.Models.Users to javafx.base;

    exports com.sample;
}

