package com.sample.controllers.regularUserControllers;

import java.io.IOException;

import com.sample.App;
import com.sample.Models.Users.User;
import com.sample.controllers.signInController;
import javafx.fxml.FXML;

public class regularUserController {

    // this is basically working as a session variable
    private User loggedInUser = signInController.getLoggedInUser();
    @FXML
    void initialize() throws IOException {
        System.out.println(loggedInUser.getMail());

    }
    User getLoggedInUser() {
        return loggedInUser;
    }
    @FXML
    void logOut() throws IOException {
        //loggedInUser = signInController.getLoggedInUser();
        loggedInUser.setLoggedIn(false);
        App.changeView("/fxml/signIn.fxml", 500, 450);
    }

    @FXML
    void onClickBuildComputer() throws IOException {
        App.changeView("/fxml/BuildComputer/buildComputer.fxml", 0, 0);

    }

    @FXML
    void onClickHome() throws IOException {
        System.out.println("Du trykka home");
        App.changeView("/fxml/homeScreenRegularUser.fxml",0, 0);
    }

    @FXML
    void onClickMyComputers() throws IOException {
        App.changeView("/fxml/myComputers.fxml",0, 0);
    }



}