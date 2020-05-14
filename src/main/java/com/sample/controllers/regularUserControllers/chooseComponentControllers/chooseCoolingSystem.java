package com.sample.controllers.regularUserControllers.chooseComponentControllers;

import com.sample.App;
import com.sample.DAL.OpenFile.Subtypes.OpenCoolingSystems;
import com.sample.Models.ComputerComponents.CoolingSystem;
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

public class chooseCoolingSystem {
    private buildComputerController bc = new buildComputerController();
    @FXML
    private GridPane gp;
    @FXML
    private Label errorLbl;
    @FXML
    private Label caseLabel;
    @FXML
    void back(ActionEvent event) throws IOException {
        App.changeView("/fxml/BuildComputer/buildComputer.fxml", 0 ,0);
    }
    private OpenCoolingSystems opener = new OpenCoolingSystems(false);

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
        gp.setVisible(false);
        String ex = workerStateEvent.getSource().getException().getMessage();
        errorLbl.setText(ex);
    }

    private void handleSucceed(WorkerStateEvent workerStateEvent) {
        List<CoolingSystem> allCoolingSystems = (List<CoolingSystem>) opener.getValue();
        // Makes all the product containers invisible, sets each one visible later depending on whether they have a component to showcase or not
        for (int i = 0; i < gp.getChildren().size(); i++) {
            gp.getChildren().get(i).setVisible(false);
            gp.getChildren().get(i).setStyle("-fx-border-color: black");
        }
        placeComponentInfo(allCoolingSystems);
    }

    @FXML
    void chooseComponent(ActionEvent event) throws IOException {
        String idOfClickedLabel = ((Control)event.getSource()).getId();
        Parent p = ((Control) event.getSource()).getParent();
        List<Node> ContainerContent = p.getChildrenUnmodifiable();
        String[] priceWithoutDollar = ((Label)ContainerContent.get(6)).getText().split(" ");
        String productName = ((Label)ContainerContent.get(0)).getText();
        String price = priceWithoutDollar[1];
        String widthCm = ((Label)ContainerContent.get(8)).getText();
        String heightCm = ((Label)ContainerContent.get(9)).getText();
        String manufacturer = ((Label)ContainerContent.get(10)).getText();
        String serialNumber = ((Label)ContainerContent.get(11)).getText();
        String description = ((Label)ContainerContent.get(12)).getText();
        try {
            CoolingSystem chosenCooling = new CoolingSystem(widthCm, heightCm, Double.parseDouble(price), description, productName, manufacturer, serialNumber);
            bc.updateComputer(chosenCooling);
            App.changeView("/fxml/BuildComputer/buildComputer.fxml", 0,0);
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
    }

    private  void placeComponentInfo(List<CoolingSystem> coolingSystems) {
        List<Node> productContainers = gp.getChildren();
        System.out.println(coolingSystems.get(0).getClass().getSimpleName());
        // this loop will have a lot of confusing casts because javafx's handling of Nodes vs Label etc.
        // all it does is set the values of the labels to the values of the components in the list.
        for (int i = 0; i < coolingSystems.size(); i++) {
            productContainers.get(i).setVisible(true);
            ((Label)((AnchorPane)productContainers.get(i)).getChildren().get(0)).setText(coolingSystems.get(i).getProductName());
            ((Label)((AnchorPane)productContainers.get(i)).getChildren().get(6)).setText("$ "+coolingSystems.get(i).getPrice());
            ((Label)((AnchorPane)productContainers.get(i)).getChildren().get(8)).setText(coolingSystems.get(i).getWidthCM());
            ((Label)((AnchorPane)productContainers.get(i)).getChildren().get(9)).setText(coolingSystems.get(i).getHeightCM());
            ((Label)((AnchorPane)productContainers.get(i)).getChildren().get(10)).setText(coolingSystems.get(i).getProductionCompany());
            ((Label)((AnchorPane)productContainers.get(i)).getChildren().get(11)).setText(coolingSystems.get(i).getSerialNumber());
            ((Label)((AnchorPane)productContainers.get(i)).getChildren().get(12)).setText(coolingSystems.get(i).getDescription());
        }
    }
}

