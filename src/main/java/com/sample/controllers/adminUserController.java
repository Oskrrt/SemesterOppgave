package com.sample.controllers;

import com.sample.App;
import com.sample.BLL.AdminLogic;
import com.sample.BLL.ComponentFactory;
import com.sample.BLL.InputValidation.ValidationException;
import com.sample.Models.ComputerComponents.*;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.paint.Color;

import java.io.IOException;
import java.util.List;

public class adminUserController {

    @FXML Label lblError;

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
        lblError.setText("");
        String type = ((Control)event.getSource()).getParent().getId(); //gets the id of the form grid, so we know what component to create.
        Parent form = ((Node)event.getSource()).getParent(); //gets the entire form grid from fxml
        List<Node> formData = form.getChildrenUnmodifiable(); //gets the children (aka textfields) in the form grid
        ComputerComponent componentToSave = null;
        try {
            componentToSave = ComponentFactory.createComponent(formData, type);
            componentToSave.validate();
            if (AdminLogic.saveComponent(componentToSave, type)){
            } else {
                //TODO: output error message to view
            }
        } catch (ValidationException e) {
            lblError.setTextFill(Color.web("#D8000C"));
            lblError.setText(e.getMessage());
        }



    }


    @FXML
    private void goToAddedComponentController() throws IOException {
        App.changeView("/fxml/viewAddedComponents.fxml", 1200,900);
    }

}
