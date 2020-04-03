package com.sample.controllers;

import com.sample.App;
import com.sample.BLL.AdminLogic;
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
    private GridPane caseFormGrid;
    @FXML
    private GridPane graphicsCardFormGrid;
    @FXML
    private GridPane coolingFormGrid;
    @FXML
    private GridPane CPUFormGrid;
    @FXML
    private GridPane keyboardFormGrid;
    @FXML
    private GridPane monitorFormGrid;
    @FXML
    private GridPane motherBoardFormGrid;
    @FXML
    private GridPane mouseFormGrid;
    @FXML
    private GridPane powerSupplyFormGrid;
    @FXML
    private GridPane RAMFormGrid;
    @FXML
    private GridPane speakerFormGrid;



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
    private void swap(){
        try {
            App.changeView("selectComponentToAdd.fxml", 1200, 900);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void saveNewComponentFromForm(Event event){
        String type = ((Control)event.getSource()).getParent().getId(); //gets the id of the form grid, so we know what component to create.
        Parent form = ((Node)event.getSource()).getParent(); //gets the entire form grid from fxml
        List<Node> formData = form.getChildrenUnmodifiable(); //gets the children (aka textfields) in the form grid

        Path filePath = Paths.get("src/main/java/com/sample/DAL/SavedFiles/NewComponent.txt");
        if (AdminLogic.saveComponent(AdminLogic.createComponent(formData, type), filePath)){
            System.out.println("JA MANNNN");
        } else {
            //TODO: output error message to view
        }
    }

}
