package com.sample.controllers.regularUserControllers.chooseComponentControllers;

import com.sample.App;

import com.sample.DAL.OpenFile.Subtypes.OpenPowerSupplies;
import com.sample.Exceptions.ValidationException;
import com.sample.Models.ComputerComponents.*;
import com.sample.controllers.regularUserControllers.buildComputerController;
import javafx.concurrent.WorkerStateEvent;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Control;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

public class choosePowerSupply {
    private buildComputerController bc = new buildComputerController();
    @FXML
    private GridPane gp;
    @FXML
    private Label errorLbl;
    @FXML private ChoiceBox<String> filter;
    @FXML private TextField querySearch;
    @FXML
    void back(ActionEvent event) throws IOException {
        App.changeView("/fxml/BuildComputer/buildComputer.fxml", 0 ,0);
    }
    private OpenPowerSupplies opener = new OpenPowerSupplies(false);

    public void initialize() {
        filter.getItems().add("Name");
        filter.getItems().add("Serial number");
        filter.getSelectionModel().selectFirst();
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

    @FXML
    private void startSearch(){
        querySearch.textProperty().addListener((observable, oldText, newText) -> {
            search(newText);
        });
    }
    private void search(String query) {
        List<PowerSupply> newList;
        try{
            List<PowerSupply> listToSearch = (List<PowerSupply>) opener.perform();
            switch (filter.getValue()){
                case "Name":
                    newList = listToSearch.stream().filter(c -> c.getProductName().toLowerCase().contains(query.toLowerCase())).collect(Collectors.toList());
                    placeComponentInfo(newList);
                    break;
                case "Serial number":
                    newList = listToSearch.stream().filter(c -> c.getSerialNumber().toLowerCase().contains(query.toLowerCase())).collect(Collectors.toList());
                    placeComponentInfo(newList);
            }
        } catch (IOException | ValidationException e) {
            e.printStackTrace();
        }
    }

    private void handleError(WorkerStateEvent workerStateEvent) {
        gp.setVisible(false);
        String ex = workerStateEvent.getSource().getException().getMessage();
        errorLbl.setText(ex);
    }

    private void handleSucceed(WorkerStateEvent workerStateEvent) {
        List<PowerSupply> allPowerSupplies = (List<PowerSupply>) opener.getValue();
        placeComponentInfo(allPowerSupplies);
    }

    @FXML
    void chooseComponent(ActionEvent event) throws IOException {
        String idOfClickedLabel = ((Control)event.getSource()).getId();
        Parent p = ((Control) event.getSource()).getParent();
        List<Node> ContainerContent = p.getChildrenUnmodifiable();
        String[] priceWithoutDollar = ((Label)ContainerContent.get(5)).getText().split(" ");
        String productName = ((Label)ContainerContent.get(0)).getText();
        String price = priceWithoutDollar[1];
        String powerSource = ((Label)ContainerContent.get(6)).getText();
        String voltage = ((Label)ContainerContent.get(7)).getText();
        String watts = ((Label)ContainerContent.get(8)).getText();
        String manufacturer = ((Label)ContainerContent.get(9)).getText();
        String serialNumber = ((Label)ContainerContent.get(10)).getText();
        String description = ((Label)ContainerContent.get(11)).getText();
        try {
            PowerSupply powerSupply = new PowerSupply(Double.parseDouble(price),description, productName, manufacturer, serialNumber, powerSource, voltage, watts);
            bc.updateComputer(powerSupply);
            App.changeView("/fxml/BuildComputer/buildComputer.fxml", 0,0);
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
    }

    private  void placeComponentInfo(List<PowerSupply> powerSupplies) {
        // Makes all the product containers invisible, sets each one visible later depending on whether they have a component to showcase or not
        for (int i = 0; i < gp.getChildren().size(); i++) {
            gp.getChildren().get(i).setVisible(false);
            gp.getChildren().get(i).setStyle("-fx-border-color: black");
        }
        List<Node> productContainers = gp.getChildren();
        // this loop will have a lot of confusing casts because javafx's handling of Nodes vs Label etc.
        // all it does is set the values of the labels to the values of the components in the list.
        for (int i = 0; i < powerSupplies.size(); i++) {
            productContainers.get(i).setVisible(true);
            ((Label)((AnchorPane)productContainers.get(i)).getChildren().get(0)).setText(powerSupplies.get(i).getProductName());
            ((Label)((AnchorPane)productContainers.get(i)).getChildren().get(5)).setText( "$ "+powerSupplies.get(i).getPrice());
            ((Label)((AnchorPane)productContainers.get(i)).getChildren().get(6)).setText(powerSupplies.get(i).getPowerSource());
            ((Label)((AnchorPane)productContainers.get(i)).getChildren().get(7)).setText(powerSupplies.get(i).getVoltage());
            ((Label)((AnchorPane)productContainers.get(i)).getChildren().get(8)).setText(powerSupplies.get(i).getWatts());
            ((Label)((AnchorPane)productContainers.get(i)).getChildren().get(9)).setText(powerSupplies.get(i).getProductionCompany());
            ((Label)((AnchorPane)productContainers.get(i)).getChildren().get(10)).setText(powerSupplies.get(i).getSerialNumber());
            ((Label)((AnchorPane)productContainers.get(i)).getChildren().get(11)).setText(powerSupplies.get(i).getDescription());
        }
    }
}

