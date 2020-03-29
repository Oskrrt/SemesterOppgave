package com.sample.controllers;

import java.io.IOException;

import com.sample.App;
import com.sample.BLL.Repository;
import com.sample.Models.Users.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

public class regularUserController {
    @FXML
    private Button buildComputerBtn;
    @FXML
    private AnchorPane container;
    @FXML
    private ImageView computerImageView;

    // this is basically working as a session variable
    private User loggedInUser = signInController.getLoggedInUser();
    @FXML
    void initialize() throws IOException {
        loggedInUser = signInController.getLoggedInUser();
        container.getChildren().add(Repository.renderNavBar());
    }

    public void logOut() throws IOException {
        loggedInUser = signInController.getLoggedInUser();
        loggedInUser.setLoggedIn(false);
        System.out.println(loggedInUser.getLoggedIn());
        App.changeView("signIn.fxml", 500, 450);
    }

    @FXML
    void onClickBuildComputer(ActionEvent event) throws IOException {
        App.changeView("buildComputer.fxml", 0, 0);
        Image computerImage = new Image("file:src/main/java/com/sample/Images/pc.png");
        System.out.println(computerImage.getUrl());

    }


    public void onClickHome() throws IOException {
        App.changeView("homeScreenRegularUser.fxml",0, 0);
    }

    @FXML
    public void onClickMyComputers() {
        System.out.println("du trykka my computers");
    }



}