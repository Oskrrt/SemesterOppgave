package com.sample.controllers.regularUserControllers.chooseComponentControllers;

import com.sample.App;
import com.sample.DAL.OpenFile.Subtypes.OpenCases;
import com.sample.Exceptions.ValidationException;
import com.sample.Models.ComputerComponents.Case;
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

public class chooseCase {
    private buildComputerController bc = new buildComputerController();
    @FXML
    private GridPane gp;
    @FXML private Label errorLbl;
    @FXML private ChoiceBox<String> filter;
    @FXML private TextField querySearch;
    @FXML
    void back(ActionEvent event) throws IOException {
        App.changeView("/fxml/BuildComputer/buildComputer.fxml", 0 ,0);
    }
    private OpenCases opener = new OpenCases(false);

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
        List<Case> newList;
        try{
            List<Case> listToSearch = (List<Case>) opener.perform();
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
        List<Case> allCases = (List<Case>) opener.getValue();
        placeComponentInfo(allCases);
    }

    @FXML
    void chooseComponent(ActionEvent event) throws IOException {
        Parent p = ((Control) event.getSource()).getParent();
        List<Node> ContainerContent = p.getChildrenUnmodifiable();
        String[] priceWithoutDollar = ((Label)ContainerContent.get(8)).getText().split(" ");
        String productName = ((Label)ContainerContent.get(0)).getText();
        String price = priceWithoutDollar[1];
        String usbPorts = ((Label)ContainerContent.get(9)).getText();
        String hdAudioJacks = ((Label)ContainerContent.get(10)).getText();
        String widthCm = ((Label)ContainerContent.get(11)).getText();
        String heightCm = ((Label)ContainerContent.get(12)).getText();
        String depthCm = ((Label)ContainerContent.get(13)).getText();
        String manufacturer = ((Label)ContainerContent.get(14)).getText();
        String serialNumber = ((Label)ContainerContent.get(15)).getText();
        String description = ((Label)ContainerContent.get(16)).getText();
        try {
            Case chosenCase = new Case(usbPorts, hdAudioJacks, widthCm, heightCm, depthCm, Double.parseDouble(price), description, productName, manufacturer, serialNumber);
            bc.updateComputer(chosenCase);
            App.changeView("/fxml/BuildComputer/buildComputer.fxml", 0,0);
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
    }

    private void placeComponentInfo(List<Case> cases) {

        // Makes all the product containers invisible, sets each one visible later depending on whether they have a component to showcase or not
        for (int i = 0; i < gp.getChildren().size(); i++) {
            gp.getChildren().get(i).setVisible(false);
            gp.getChildren().get(i).setStyle("-fx-border-color: black");
        }
        List<Node> productContainers = gp.getChildren();
        // this loop will have a lot of confusing casts because javafx's handling of Nodes vs Label etc.
        // all it does is set the values of the labels to the values of the components in the list.
        for (int i = 0; i < cases.size(); i++) {
            productContainers.get(i).setVisible(true);
            ((Label)((AnchorPane)productContainers.get(i)).getChildren().get(0)).setText(cases.get(i).getProductName());
            ((Label)((AnchorPane)productContainers.get(i)).getChildren().get(8)).setText("$ "+cases.get(i).getPrice());
            ((Label)((AnchorPane)productContainers.get(i)).getChildren().get(9)).setText(cases.get(i).getNumberOfUSBPorts());
            ((Label)((AnchorPane)productContainers.get(i)).getChildren().get(10)).setText(cases.get(i).getHDAudioJacks());
            ((Label)((AnchorPane)productContainers.get(i)).getChildren().get(11)).setText(cases.get(i).getWidthCM());
            ((Label)((AnchorPane)productContainers.get(i)).getChildren().get(12)).setText(cases.get(i).getHeightCM());
            ((Label)((AnchorPane)productContainers.get(i)).getChildren().get(13)).setText(cases.get(i).getDepthCM());
            ((Label)((AnchorPane)productContainers.get(i)).getChildren().get(14)).setText(cases.get(i).getProductionCompany());
            ((Label)((AnchorPane)productContainers.get(i)).getChildren().get(15)).setText(cases.get(i).getSerialNumber());
            ((Label)((AnchorPane)productContainers.get(i)).getChildren().get(16)).setText(cases.get(i).getDescription());
        }
    }
}
