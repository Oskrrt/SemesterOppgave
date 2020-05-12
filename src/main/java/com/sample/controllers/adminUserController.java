package com.sample.controllers;

import com.sample.App;
import com.sample.BLL.AdminLogic;
import com.sample.BLL.ComponentFactory;
import com.sample.Exceptions.ValidationException;
import com.sample.Models.ComputerComponents.*;
import javafx.event.ActionEvent;
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


    //because there is one form for every component that can be added, we needed to swap views correctly
    //without knowing which button was pressed. This function gets the FX-ID of the button pressed and sends it
    //to a function in AdminLogic that loads the correct FXML file based on the button's FX-ID
    @FXML
    private void handleSelectionButtonEvent(Event event){
        String buttonPressed = ((Control)event.getSource()).getId();
        try{
            AdminLogic.swapViewsBasedOnButtonPressed(buttonPressed);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    //finds which button has been pressed and changes view accordingly
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

    //this function creates a new ComputerComponent from a form, regardless of which type of component. It then sends this
    //new object to ComponentFactory, along with a string telling the factory which specific component has to be created.
    //if the component validates, it is saved using AdminLogic.saveComponent, along with which type of component it is
    @FXML
    private void saveNewComponentFromForm(Event event){
        lblError.setText("");
        String type = ((Control)event.getSource()).getParent().getId(); //gets the id of the form grid, so we know what component to create (Case, CoolingComponent, Keyboard etc).
        Parent form = ((Node)event.getSource()).getParent(); //the entire form grid from fxml
        List<Node> formData = form.getChildrenUnmodifiable(); //gets the children (aka textfields), and therefore the form data in the form grid
        ComputerComponent componentToSave = null;
        try {
            componentToSave = ComponentFactory.createComponent(formData, type);
            componentToSave.validate();
            if (AdminLogic.saveComponent(componentToSave, type)){
                lblError.setText("Component saved.");
            } else {
                lblError.setTextFill(Color.web("#D8000C"));
                lblError.setText("Could not create component");
            }
        } catch (ValidationException e) {
            lblError.setTextFill(Color.web("#D8000C"));
            lblError.setText(e.getMessage());
        } catch (IOException e){
            lblError.setTextFill(Color.web("#D8000C"));
            lblError.setText("Could not save component");
        }
    }


    //simple function to change controllers. This is for the viewing of added components
    @FXML
    private void goToAddedComponentController() throws IOException {
        App.changeView("/fxml/viewAddedComponents.fxml", 1200,900);
    }

    @FXML
    private void goToRegistration(ActionEvent actionEvent) throws IOException {
        App.changeView("/fxml/signUpForm_ADMIN.fxml", 672, 503);
    }
}
