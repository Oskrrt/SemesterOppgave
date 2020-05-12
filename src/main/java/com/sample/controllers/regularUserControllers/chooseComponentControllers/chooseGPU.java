package com.sample.controllers.regularUserControllers.chooseComponentControllers;

import com.sample.App;
import com.sample.DAL.OpenFile.Subtypes.OpenGPUs;
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

public class chooseGPU {
    private buildComputerController bc = new buildComputerController();
    @FXML
    private GridPane gp;

    @FXML
    private Label caseLabel;
    @FXML
    void back(ActionEvent event) throws IOException {
        App.changeView("/fxml/BuildComputer/buildComputer.fxml", 0 ,0);
    }
    private OpenGPUs opener = new OpenGPUs();

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
        List<GraphicsCard> allGPUs = (List<GraphicsCard>) opener.getValue();
        // Makes all the product containers invisible, sets each one visible later depending on whether they have a component to showcase or not
        for (int i = 0; i < gp.getChildren().size(); i++) {
            gp.getChildren().get(i).setVisible(false);
            gp.getChildren().get(i).setStyle("-fx-border-color: black");
        }
        placeComponentInfo(allGPUs);
    }

    @FXML
    void chooseComponent(ActionEvent event) throws IOException {
        String idOfClickedLabel = ((Control)event.getSource()).getId();
        Parent p = ((Control) event.getSource()).getParent();
        List<Node> ContainerContent = p.getChildrenUnmodifiable();
        String[] priceWithoutDollar = ((Label)ContainerContent.get(5)).getText().split(" ");
        String productName = ((Label)ContainerContent.get(0)).getText();
        String price = priceWithoutDollar[1];
        String memoryCapacity = ((Label)ContainerContent.get(6)).getText();
        String memoryType = ((Label)ContainerContent.get(7)).getText();
        String manufacturer = ((Label)ContainerContent.get(8)).getText();
        String serialNumber = ((Label)ContainerContent.get(9)).getText();
        String description = ((Label)ContainerContent.get(10)).getText();
        try {
            GraphicsCard gpu = new GraphicsCard(Double.parseDouble(price),description, productName, manufacturer, serialNumber, memoryCapacity, memoryType);
            bc.updateComputer(gpu);
            App.changeView("/fxml/BuildComputer/buildComputer.fxml", 0,0);
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
    }

    private  void placeComponentInfo(List<GraphicsCard> GPUs) {
        List<Node> productContainers = gp.getChildren();
        System.out.println(GPUs.get(0).getClass().getSimpleName());
        // this loop will have a lot of confusing casts because javafx's handling of Nodes vs Label etc.
        // all it does is set the values of the labels to the values of the components in the list.
        for (int i = 0; i < GPUs.size(); i++) {
            productContainers.get(i).setVisible(true);
            ((Label)((AnchorPane)productContainers.get(i)).getChildren().get(0)).setText(GPUs.get(i).getProductName());
            ((Label)((AnchorPane)productContainers.get(i)).getChildren().get(5)).setText( "$ "+GPUs.get(i).getPrice());
            ((Label)((AnchorPane)productContainers.get(i)).getChildren().get(6)).setText(GPUs.get(i).getMemoryCapacity());
            ((Label)((AnchorPane)productContainers.get(i)).getChildren().get(7)).setText(GPUs.get(i).getMemoryType());
            ((Label)((AnchorPane)productContainers.get(i)).getChildren().get(8)).setText(GPUs.get(i).getProductionCompany());
            ((Label)((AnchorPane)productContainers.get(i)).getChildren().get(9)).setText(GPUs.get(i).getSerialNumber());
            ((Label)((AnchorPane)productContainers.get(i)).getChildren().get(10)).setText(GPUs.get(i).getDescription());
        }
    }
}

