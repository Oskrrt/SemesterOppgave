package com.sample.controllers.regularUserControllers.chooseComponentControllers;

import com.sample.App;
import com.sample.DAL.OpenFile.Subtypes.OpenMotherBoards;
import com.sample.Models.ComputerComponents.*;
import com.sample.controllers.regularUserControllers.buildComputerController;
import javafx.concurrent.WorkerStateEvent;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Control;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;

import java.io.IOException;
import java.util.List;

public class chooseMotherboard {
    private buildComputerController bc = new buildComputerController();
    @FXML
    private GridPane gp;

    @FXML
    private Label caseLabel;
    @FXML
    void back(ActionEvent event) throws IOException {
        App.changeView("/fxml/BuildComputer/buildComputer.fxml", 0 ,0);
    }
    private OpenMotherBoards opener = new OpenMotherBoards();

    public void initialize() {
        try {
            Thread openCaseFilesThread = new Thread(opener);
            opener.setOnSucceeded(this::handleSucceed);
            opener.setOnFailed(this::handleError);
            openCaseFilesThread.setDaemon(true);
            openCaseFilesThread.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }



    private void handleError(WorkerStateEvent workerStateEvent) {
        Label errorPlaceholder = new Label("Could not retrieve saved cooling systems");
    }

    private void handleSucceed(WorkerStateEvent workerStateEvent) {
        List<Motherboard> allMotherBoards = (List<Motherboard>) opener.getValue();
        // Makes all the product containers invisible, sets each one visible later depending on whether they have a component to showcase or not
        for (int i = 0; i < gp.getChildren().size(); i++) {
            gp.getChildren().get(i).setVisible(false);
            gp.getChildren().get(i).setStyle("-fx-border-color: black");
        }
        placeComponentInfo(allMotherBoards);
    }

    @FXML
    void chooseComponent(ActionEvent event) throws IOException {
        String idOfClickedLabel = ((Control)event.getSource()).getId();
        Parent p = ((Control) event.getSource()).getParent();
        List<Node> ContainerContent = p.getChildrenUnmodifiable();
        String[] priceWithoutDollar = ((Label)ContainerContent.get(6)).getText().split(" ");
        String productName = ((Label)ContainerContent.get(0)).getText();
        String price = priceWithoutDollar[1];
        String cpuSupport = ((Label)ContainerContent.get(7)).getText();
        String memoryType = ((Label)ContainerContent.get(8)).getText();
        String memoryDimms = ((Label)ContainerContent.get(9)).getText();
        String graphicsInterface = ((Label)ContainerContent.get(21)).getText();
        String expansionSlots = ((Label)ContainerContent.get(22)).getText();
        String m2Slots = ((Label)ContainerContent.get(23)).getText();
        String displayInterface = ((Label)ContainerContent.get(24)).getText();
        String wifi = ((Label)ContainerContent.get(25)).getText();
        String audio = ((Label)ContainerContent.get(26)).getText();
        String formFactor = ((Label)ContainerContent.get(27)).getText();
        String manufacturer = ((Label)ContainerContent.get(10)).getText();
        String serialNumber = ((Label)ContainerContent.get(11)).getText();
        String description = ((Label)ContainerContent.get(12)).getText();
        try {
            Motherboard motherboard = new Motherboard(Double.parseDouble(price),description, productName, manufacturer, serialNumber, cpuSupport, memoryType, memoryDimms, graphicsInterface, expansionSlots, m2Slots, displayInterface, wifi, audio, formFactor);
            bc.updateComputer(motherboard);
            App.changeView("/fxml/BuildComputer/buildComputer.fxml", 0,0);
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
    }

    private  void placeComponentInfo(List<Motherboard> motherboards) {
        List<Node> productContainers = gp.getChildren();
        System.out.println(motherboards.get(0).getClass().getSimpleName());
        // this loop will have a lot of confusing casts because javafx's handling of Nodes vs Label etc.
        // all it does is set the values of the labels to the values of the components in the list.
        for (int i = 0; i < motherboards.size(); i++) {
            productContainers.get(i).setVisible(true);
            ((Label)((AnchorPane)productContainers.get(i)).getChildren().get(0)).setText(motherboards.get(i).getProductName());
            ((Label)((AnchorPane)productContainers.get(i)).getChildren().get(6)).setText("$ "+motherboards.get(i).getPrice());
            ((Label)((AnchorPane)productContainers.get(i)).getChildren().get(7)).setText(motherboards.get(i).getCPUSupport());
            ((Label)((AnchorPane)productContainers.get(i)).getChildren().get(8)).setText(motherboards.get(i).getMemoryType());
            ((Label)((AnchorPane)productContainers.get(i)).getChildren().get(9)).setText(motherboards.get(i).getMemoryDIMMs());
            ((Label)((AnchorPane)productContainers.get(i)).getChildren().get(21)).setText(motherboards.get(i).getGraphicInterface());
            ((Label)((AnchorPane)productContainers.get(i)).getChildren().get(22)).setText(motherboards.get(i).getExpansionSlots());
            ((Label)((AnchorPane)productContainers.get(i)).getChildren().get(24)).setText(motherboards.get(i).getM2Slot());
            ((Label)((AnchorPane)productContainers.get(i)).getChildren().get(24)).setText(motherboards.get(i).getDisplayInterface());
            ((Label)((AnchorPane)productContainers.get(i)).getChildren().get(25)).setText(motherboards.get(i).getWIFI());
            ((Label)((AnchorPane)productContainers.get(i)).getChildren().get(26)).setText(motherboards.get(i).getAudio());
            ((Label)((AnchorPane)productContainers.get(i)).getChildren().get(27)).setText(motherboards.get(i).getFormFactor());
            ((Label)((AnchorPane)productContainers.get(i)).getChildren().get(10)).setText(motherboards.get(i).getProductionCompany());
            ((Label)((AnchorPane)productContainers.get(i)).getChildren().get(11)).setText(motherboards.get(i).getSerialNumber());
            ((Label)((AnchorPane)productContainers.get(i)).getChildren().get(12)).setText(motherboards.get(i).getDescription());
        }
    }
}

