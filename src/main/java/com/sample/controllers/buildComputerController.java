package com.sample.controllers;

import javafx.beans.property.ReadOnlyDoubleProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.IOException;

public class buildComputerController {

    private regularUserController mainControllerForRegularUsers = new regularUserController();
    @FXML
    private ImageView computerImageView;

    @FXML
    private Button homeBtn;

    @FXML
    private Button myComputersbtn;

    @FXML
    private Button logOutBtn;

    @FXML
    void initialize() {
        /*Image computerImage = new Image("file:src/main/java/com/sample/Images/pc.png");
        computerImageView.setFitWidth(700);
        System.out.println(computerImage.getUrl());
        computerImageView.setImage(computerImage);*/
    }

    @FXML
    void logOut(ActionEvent event) throws IOException {
        mainControllerForRegularUsers.logOut();
    }

    @FXML
    void onClickHome(ActionEvent event) throws IOException {
        mainControllerForRegularUsers.onClickHome();
    }

    @FXML
    void onClickMyComputers(ActionEvent event) {
        mainControllerForRegularUsers.onClickMyComputers();
    }

}
