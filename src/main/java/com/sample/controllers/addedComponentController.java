package com.sample.controllers;

import com.sample.App;
import com.sample.DAL.init;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Control;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/*
this controller is basically just a middle-man between adminUserController and every single addedComponentController.
We originally planned on only using this controller for showing all added components, but as we wanted a single
view for every table, this provided NullReferenceExceptions every time we swapped views. The solution was to create
a single controller for every view. You'll find each controller in com/sample/controllers/addedComponentControllers/*

*/
public class addedComponentController extends adminUserController implements Initializable {

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //remove comment if you need to initialize all components.
//        try {
//            init.initFiles();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
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
                case "btnGoToList":
                case "backToAddedComponents":
                    App.changeView("/fxml/viewAddedComponents.fxml", 1200, 900);
                    break;
                case "btnLogOut":
                    App.changeView("/fxml/signIn.fxml", 500, 450);
                    break;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    /*
    view-swappers
     */

    @FXML
    public void showAddedCases() throws IOException {
        App.changeView("/fxml/AddedComponents/AddedCases.fxml", 1200,900);
    }

    @FXML
    private void showAddedCoolingSystems(Event event) throws IOException {
        App.changeView("/fxml/AddedComponents/AddedCoolingSystems.fxml", 1200,900);
    }

    @FXML
    private void showAddedCPUs(Event event) throws IOException {
        App.changeView("/fxml/AddedComponents/AddedCPUs.fxml", 1200,900);
    }

    @FXML
    private void showAddedGPUs(Event event) throws IOException {
        App.changeView("/fxml/AddedComponents/AddedGraphicsCards.fxml", 1200,900);
    }

    @FXML
    private void showAddedKeyboards(Event event) throws IOException {
        App.changeView("/fxml/AddedComponents/AddedKeyboards.fxml", 1200,900);
    }

    @FXML
    private void showAddedMice(Event event) throws IOException {
        App.changeView("/fxml/AddedComponents/AddedMice.fxml", 1200,900);
    }

    @FXML
    private void showAddedMonitors(Event event) throws IOException {
        App.changeView("/fxml/AddedComponents/AddedMonitors.fxml", 1200,900);
    }

    @FXML
    private void showAddedMotherboards(Event event) throws IOException {
        App.changeView("/fxml/AddedComponents/AddedMotherboards.fxml", 1200,900);
    }

    @FXML
    private void showAddedPowersupplies(Event event) throws IOException {
        App.changeView("/fxml/AddedComponents/AddedPowerSupplies.fxml", 1200,900);
    }

    @FXML
    private void showAddedRAM(Event event) throws IOException {
        App.changeView("/fxml/AddedComponents/AddedRAM.fxml", 1200,900);
    }

    @FXML
    private void showAddedSpeakers(Event event) throws IOException {
        App.changeView("/fxml/AddedComponents/AddedSpeakers.fxml", 1200,900);
    }

    @FXML
    private void showAddedStorageComponents(Event event) throws IOException {
        App.changeView("/fxml/AddedComponents/AddedStorageComponents.fxml", 1200,900);
    }


}
