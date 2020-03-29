package com.sample.controllers;

import java.io.IOException;

import com.sample.App;
import com.sample.Models.Users.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

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
    private ImageView computerImageView;
    // this is basically working as a session variable
    private User loggedInUser = signInController.getLoggedInUser();
    @FXML
    private void logOut() throws IOException {
        loggedInUser.setLoggedIn(false);
        App.changeView("signIn.fxml", 500, 450);
    }

    @FXML
    void onClickBuildComputer(ActionEvent event) throws IOException {
        App.changeView("buildComputer.fxml", 0, 0);
        Image computerImage = new Image("file:src/main/java/com/sample/Images/pc.png");
        computerImageView.setImage(computerImage);
    }

    @FXML
    void onClickHome(ActionEvent event) throws IOException {
        App.changeView("secondaryView.fxml",0, 0);
    }

    @FXML
    void onClickMyComputers(ActionEvent event) {

    }



}