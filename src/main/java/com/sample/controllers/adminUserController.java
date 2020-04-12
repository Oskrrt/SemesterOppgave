package com.sample.controllers;

import com.sample.App;
import com.sample.BLL.AdminLogic;
import com.sample.BLL.ComponentFactory;
import com.sample.Models.ComputerComponents.*;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class adminUserController {


    @FXML
    private void handleSelectionButtonEvent(Event event){
        String buttonPressed = ((Control)event.getSource()).getId();
        try{
            AdminLogic.swapViewsBasedOnButtonPressed_ADD_COMPONENTS(buttonPressed);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @FXML
    private void viewSwapper(Event event){
        String buttonPressed = ((Control)event.getSource()).getId();
        try {
            switch (buttonPressed) {
                case "btnCreateBack":
                case "btnGoToCreation":
                    App.changeView("/fxml/selectComponentToAdd.fxml", 1200, 900);
                    break;
                case "btnBackToHome":
                    App.changeView("/fxml/homeScreenAdmin.fxml", 1200, 900);
                    break;
                case "btnLogOut":
                    App.changeView("/fxml/signIn.fxml", 500, 450);
                    break;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void saveNewComponentFromForm(Event event){
        String type = ((Control)event.getSource()).getParent().getId(); //gets the id of the form grid, so we know what component to create.
        Parent form = ((Node)event.getSource()).getParent(); //gets the entire form grid from fxml
        List<Node> formData = form.getChildrenUnmodifiable(); //gets the children (aka textfields) in the form grid
        ComputerComponent componentToSave = ComponentFactory.createComponent(formData, type);
        assert componentToSave != null;
        if (AdminLogic.saveComponent(componentToSave, type)){
            System.out.println("JA MANNNN");
        } else {
            //TODO: output error message to view
        }
    }


    @FXML
    private void goToAddedComponentController() throws IOException {
        App.changeView("/fxml/viewAddedComponents.fxml", 1200,900);
    }

}
