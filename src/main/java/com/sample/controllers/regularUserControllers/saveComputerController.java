package com.sample.controllers.regularUserControllers;

import com.sample.App;
import com.sample.Models.Computer.Computer;
import com.sample.Models.Users.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.layout.VBox;

import java.io.IOException;

public class saveComputerController {
    private regularUserController connector = new regularUserController();
    private static buildComputerController bc = new buildComputerController();
    private static User loggedInUser = bc.getLoggedInUser();
    private Computer computerToBeSaved = loggedInUser.getComputerInProduction();
    @FXML
    private VBox container;
    @FXML
    void back(ActionEvent event) throws IOException {
        App.changeView("/fxml/BuildComputer/buildComputer.fxml", 0, 0);
    }

    @FXML
    void logOut(ActionEvent event) throws IOException {
        connector.logOut();
    }

    @FXML
    void onClickHome(ActionEvent event) throws IOException {
        connector.onClickHome();
    }

    @FXML
    void onClickMyComputers(ActionEvent event) {
        connector.onClickMyComputers();
    }

    @FXML
    void onClickSave(ActionEvent event) {

    }

}
