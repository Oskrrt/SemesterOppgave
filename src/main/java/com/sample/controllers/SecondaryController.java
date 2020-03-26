package com.sample.controllers;

import java.io.IOException;

import com.sample.App;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class SecondaryController {

    @FXML
    public Button secondaryButton;

    @FXML
    private void changeToMainView() throws IOException {
        App.changeView("mainview.fxml");
    }
}