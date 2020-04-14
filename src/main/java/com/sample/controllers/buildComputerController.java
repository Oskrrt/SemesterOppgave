package com.sample.controllers;

import com.sample.App;
import com.sample.BLL.UserLogic;
import com.sample.Models.Computer.Computer;
import com.sample.Models.ComputerComponents.Case;
import com.sample.Models.ComputerComponents.ComputerComponent;
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
    private static Computer computerBeingBuilt = new Computer(null, null, null, null, null ,null, null, null);


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
        // The null values will be set when the user chooses the parts.
    }
    public void updateComputer(ComputerComponent component) {
        computerBeingBuilt.setComputerCase((Case) component);

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

    @FXML
    void openChoiceWindow(MouseEvent event) throws IOException {
        String idOfClickedLabel = ((Control)event.getSource()).getId();
        if (computerBeingBuilt.getComputerCase()!= null) {
            System.out.println("albaba"+ computerBeingBuilt.getComputerCase().getHDAudioJacks());
        }

        UserLogic.openCorrectWindow(idOfClickedLabel);
    }
}
