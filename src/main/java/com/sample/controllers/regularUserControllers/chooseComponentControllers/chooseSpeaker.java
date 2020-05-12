package com.sample.controllers.regularUserControllers.chooseComponentControllers;

import com.sample.App;


import com.sample.DAL.OpenFile.Subtypes.OpenSpeakers;
import com.sample.Models.ComputerComponents.*;
import com.sample.controllers.regularUserControllers.addAccessoriesController;
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

public class chooseSpeaker {
    private addAccessoriesController aac = new addAccessoriesController();
    @FXML
    private GridPane gp;
    @FXML
    private Label errorLbl;
    @FXML
    private Label caseLabel;
    @FXML
    void back(ActionEvent event) throws IOException {
        App.changeView("/fxml/BuildComputer/addAccessories.fxml", 0 ,0);
    }
    private OpenSpeakers opener = new OpenSpeakers();

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
        List<Speaker> allSpeakers = (List<Speaker>) opener.getValue();
        // Makes all the product containers invisible, sets each one visible later depending on whether they have a component to showcase or not
        for (int i = 0; i < gp.getChildren().size(); i++) {
            gp.getChildren().get(i).setVisible(false);
            gp.getChildren().get(i).setStyle("-fx-border-color: black");
        }
        placeComponentInfo(allSpeakers);
    }

    @FXML
    void chooseComponent(ActionEvent event) throws IOException {
        String idOfClickedLabel = ((Control)event.getSource()).getId();
        Parent p = ((Control) event.getSource()).getParent();
        List<Node> ContainerContent = p.getChildrenUnmodifiable();
        String[] priceWithoutDollar = ((Label)ContainerContent.get(4)).getText().split(" ");
        String productName = ((Label)ContainerContent.get(0)).getText();
        String price = priceWithoutDollar[1];
        String inputType = ((Label)ContainerContent.get(5)).getText();
        String manufacturer = ((Label)ContainerContent.get(6)).getText();
        String serialNumber = ((Label)ContainerContent.get(7)).getText();
        String description = ((Label)ContainerContent.get(8)).getText();
        try {
            Speaker speaker = new Speaker(Double.parseDouble(price),description, productName, manufacturer, serialNumber, inputType);
            aac.updateComputer(speaker);
            App.changeView("/fxml/BuildComputer/addAccessories.fxml", 0,0);
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
    }

    private  void placeComponentInfo(List<Speaker> speakers) {
        List<Node> productContainers = gp.getChildren();
        // this loop will have a lot of confusing casts because javafx's handling of Nodes vs Label etc.
        // all it does is set the values of the labels to the values of the components in the list.
        for (int i = 0; i < speakers.size(); i++) {
            productContainers.get(i).setVisible(true);
            ((Label)((AnchorPane)productContainers.get(i)).getChildren().get(0)).setText(speakers.get(i).getProductName());
            ((Label)((AnchorPane)productContainers.get(i)).getChildren().get(4)).setText("$ "+speakers.get(i).getPrice());
            ((Label)((AnchorPane)productContainers.get(i)).getChildren().get(5)).setText(speakers.get(i).getInputType());
            ((Label)((AnchorPane)productContainers.get(i)).getChildren().get(6)).setText(speakers.get(i).getProductionCompany());
            ((Label)((AnchorPane)productContainers.get(i)).getChildren().get(7)).setText(speakers.get(i).getSerialNumber());
            ((Label)((AnchorPane)productContainers.get(i)).getChildren().get(8)).setText(speakers.get(i).getDescription());
        }
    }
}

