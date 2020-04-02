package com.sample.controllers;

import com.sample.App;
import com.sample.BLL.AdminLogic;
import com.sample.Models.ComputerComponents.ComputerComponent;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Control;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

import java.io.IOException;

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
    private void saveNewComponentFromForm(){

    }

}
