package com.sample.controllers;

import com.sample.App;
import com.sample.BLL.AdminLogic;
import com.sample.Models.ComputerComponents.Case;
import com.sample.Models.ComputerComponents.*;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Control;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import com.sample.DAL.init;


public class addedComponentController extends adminUserController implements Initializable {
    @FXML private TableView<Case> caseTable;
    @FXML private TableView<CoolingSystem> coolingTable;
    @FXML private TableView<Processor> CPUTable;
    @FXML private TableView<GraphicsCard> GPUTable;
    @FXML private TableView<Keyboard> keyboardTable;
    @FXML private TableView<Monitor> monitorTable;
    @FXML private TableView<Motherboard> motherboardTable;
    @FXML private TableView<Mouse> mouseTable;
    @FXML private TableView<PowerSupply> powerSupplyTable;
    @FXML private TableView<RAM> RAMTable;
    @FXML private TableView<Speaker> speakerTable;
    @FXML private TableView<StorageComponent> storageComponentTable;
    @FXML private AnchorPane casePane;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // remove comment if you need to initialize all components.
        // init.initFiles();
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
