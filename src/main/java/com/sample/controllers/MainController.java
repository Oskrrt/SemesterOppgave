package com.sample.controllers;

import java.io.IOException;

import com.sample.App;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class MainController {

    @FXML
    private Button mainButton;

    @FXML
    private void changeToSecondaryView() throws IOException {
        App.changeView("secondaryview.fxml");
    }
}
