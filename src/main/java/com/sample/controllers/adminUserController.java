package com.sample.controllers;

import com.sample.App;
import com.sample.BLL.AdminLogic;
import com.sample.BLL.ComponentFactory;
import com.sample.Models.ComputerComponents.ComputerComponent;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Control;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class adminUserController {
    @FXML
    private Button testButton;
    @FXML
    private Button btnBack;
    @FXML
    private GridPane buttonGrida;
    @FXML
    private GridPane computerCase;
    @FXML
    private GridPane graphicsCard;
    @FXML
    private GridPane coolingSystem;
    @FXML
    private GridPane CPU;
    @FXML
    private GridPane keyboard;
    @FXML
    private GridPane monitor;
    @FXML
    private GridPane motherBoard;
    @FXML
    private GridPane mouse;
    @FXML
    private GridPane powerSupply;
    @FXML
    private GridPane RAM;
    @FXML
    private GridPane speaker;
    @FXML
    private GridPane storageComponent;



    @FXML
    private void handleBackButton(){
        try {
            App.changeView("selectComponentToAdd.fxml", 1200, 900);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void handleSelectionButtonEvent(Event event){
        String buttonPressed = ((Control)event.getSource()).getId();
            try{
                AdminLogic.swapViewsBasedOnButtonPressed(buttonPressed);
            } catch (IOException e) {
                e.printStackTrace();
            }
    }

    @FXML
    private void viewSwapper(Event event){
        String buttonPressed = ((Control)event.getSource()).getId();
        try {
            if (buttonPressed.equals("btnCreateBack"))
            App.changeView("selectComponentToAdd.fxml", 1200, 900);
            else if(buttonPressed.equals("btnBackToHome")){
                App.changeView("homeScreenAdmin.fxml", 1200, 900);
            } else if (buttonPressed.equals("btnGoToCreation")){
                App.changeView("selectComponentToAdd.fxml", 1200, 900);
            } else if (buttonPressed.equals("btnGoToList")){
                App.changeView("viewAddedComponents.fxml", 1200, 900);
            } else if (buttonPressed.equals("btnLogOut")){
                App.changeView("signIn.fxml", 500, 450);
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
        System.out.println(componentToSave.getDescription());
        System.out.println(componentToSave);
        if (AdminLogic.saveComponent(componentToSave, type)){
            System.out.println("JA MANNNN");
        } else {
            //TODO: output error message to view
        }
    }

}
