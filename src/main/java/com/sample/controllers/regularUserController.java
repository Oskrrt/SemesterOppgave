package com.sample.controllers;

import java.io.IOException;

import com.sample.App;
import com.sample.Models.Users.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

public class regularUserController {
    @FXML
    private Button buildComputerBtn;
    @FXML
    private AnchorPane container;
    @FXML
    private ImageView computerImageView;

    @FXML
    private Label bla;

    // this is basically working as a session variable
    private User loggedInUser = signInController.getLoggedInUser();
    @FXML
    void initialize() throws IOException {
        System.out.println(loggedInUser.getMail());

    }
    protected User getLoggedInUser() {
        return loggedInUser;
    }
    @FXML
     void logOut() throws IOException {
        //loggedInUser = signInController.getLoggedInUser();
        loggedInUser.setLoggedIn(false);
        App.changeView("/fxml/signIn.fxml", 500, 450);
    }

    @FXML
    void onClickBuildComputer(ActionEvent event) throws IOException {
        App.changeView("/fxml/buildComputer.fxml", 0, 0);

    }

    @FXML
    void onClickHome() throws IOException {
        System.out.println("Du trykka home");
        App.changeView("/fxml/homeScreenRegularUser.fxml",0, 0);
    }

    @FXML
    void onClickMyComputers() {
        System.out.println("du trykka my computers");
    }



}