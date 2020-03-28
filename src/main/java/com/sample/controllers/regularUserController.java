package com.sample.controllers;

import java.io.IOException;

import com.sample.App;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class regularUserController {
    @FXML
    private Button buildComputerBtn;

    @FXML
    private Button homeBtn;

    @FXML
    private Button myComputersbtn;

    @FXML
    private Button logOutBtn;
    @FXML
    public Button secondaryButton;

    @FXML
    private void logOut() throws IOException {
        App.changeView("signIn.fxml", 500, 450);
    }

    @FXML
    void onClickBuildComputer(ActionEvent event) {

    }

    @FXML
    void onClickHome(ActionEvent event) throws IOException {
        App.changeView("secondaryView.fxml",0, 0);
    }

    @FXML
    void onClickMyComputers(ActionEvent event) {

    }



}