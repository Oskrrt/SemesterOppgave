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
    private TableView<Case> caseTable;
    @FXML
    private TableView<CoolingSystem> coolingTable;
    @FXML
    private TableView<Processor> CPUTable;
    @FXML
    private TableView<GraphicsCard> GPUTable;
    @FXML
    private TableView<Keyboard> keyboardTable;
    @FXML
    private TableView<Monitor> monitorTable;
    @FXML
    private TableView<Motherboard> motherboardTable;
    @FXML
    private TableView<Mouse> mouseTable;
    @FXML
    private TableView<PowerSupply> powerSupplyTable;
    @FXML
    private TableView<RAM> RAMTable;
    @FXML
    private TableView<Speaker> speakerTable;
    @FXML
    private TableView<StorageComponent> storageComponentTable;
    @FXML
    private AnchorPane casePane;

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
                    App.changeView("selectComponentToAdd.fxml", 1200, 900);
                    break;
                case "btnBackToHome":
                    App.changeView("homeScreenAdmin.fxml", 1200, 900);
                    break;
                case "btnGoToList":
                case "backToAddedComponents":
                    App.changeView("viewAddedComponents.fxml", 1200, 900);
                    break;
                case "btnLogOut":
                    App.changeView("signIn.fxml", 500, 450);
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
    private void selectComponentViews(Event event){
        String buttonPressed = ((Control)event.getSource()).getId();
        try{
            AdminLogic.swapViewsBasedOnButtonPressed_VIEW_COMPONENTS(buttonPressed);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    //TODO figure out this null shit wtf
    public void showAddedCases(){
        caseTable = (TableView<Case>)casePane.lookup("#caseTable");
        final File folderOfCases = new File("src/main/java/com/sample/DAL/SavedFiles/NewComponents/Cases");

        try {
            List<Case> cases = ComponentFactory.createCasesFromFile(folderOfCases);
            caseTable.getItems().setAll(cases);
        } catch (IOException e){
            e.printStackTrace();
            //lblError.setText("Could not retrieve from file");
        }


    }

    private void showAddedCoolingSystems(Event event){

    }

    private void showAddedCPUs(Event event){

    }

    private void showAddedGPUs(Event event){

    }

    private void showAddedKeyboards(Event event){

    }

    private void showAddedMice(Event event){

    }

    private void showAddedMonitors(Event event){

    }

    private void showAddedMotherboards(Event event){

    }

    private void showAddedPowersupplies(Event event){

    }

    private void showAddedRAM(Event event){

    }

    private void showAddedSpeakers(Event event){

    }

    private void showAddedStorageComponents(Event event){

    }

}
