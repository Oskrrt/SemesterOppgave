package com.sample.controllers;

import com.sample.BLL.UserLogic;
import javafx.beans.property.ReadOnlyDoubleProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Control;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.stage.Window;

import java.io.IOException;

public class buildComputerController {

    private regularUserController mainControllerForRegularUsers = new regularUserController();
    @FXML
    private ImageView computerImageView;

    @FXML
    private AnchorPane container;

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

    @FXML
    void openChoiceWindow(MouseEvent event) throws ClassNotFoundException {
        System.out.println(((Control)event.getSource()).getId());
        String idOfClickedLabel = ((Control)event.getSource()).getId();
        AnchorPane ap = new AnchorPane();
        ap.setId("ap");
        ap.setPrefWidth(1000);
        ap.setPrefHeight(750);
        Stage choiceStage = new Stage();
        Scene choiceScene = new Scene(ap);
        choiceStage.setScene(choiceScene);
        UserLogic.renderChoiceWindow(choiceStage, idOfClickedLabel);
    }

    @FXML
    void makeBigger(MouseEvent event) {
        String idOfHoveredLabel = "#"+((Control)event.getSource()).getId();
        Label hoveredLabel = (Label)container.lookup(idOfHoveredLabel);
        hoveredLabel.setFont(new Font("System", 25));
    }

    @FXML
    void makeNormal(MouseEvent event) {
        String idOfHoveredLabel = "#"+((Control)event.getSource()).getId();
        Label hoveredLabel = (Label)container.lookup(idOfHoveredLabel);
        hoveredLabel.setFont(new Font("System", 20));
    }
}
