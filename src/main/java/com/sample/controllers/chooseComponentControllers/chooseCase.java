package com.sample.controllers.chooseComponentControllers;

import com.sample.DAL.OpenFile.Subtypes.OpenAddedComponents;
import com.sample.DAL.OpenFile.Subtypes.OpenCases;
import com.sample.Models.Computer.Computer;
import com.sample.Models.ComputerComponents.Case;
import com.sample.controllers.buildComputerController;
import javafx.collections.ObservableList;
import javafx.concurrent.WorkerStateEvent;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Control;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;

import java.sql.SQLOutput;
import java.util.List;

public class chooseCase {

    @FXML
    private Label lblHeader;

    @FXML
    private GridPane gp;

    @FXML
    private Label lblProductName;

    @FXML
    private Label lblManufacturer;

    @FXML
    private Label lblPrice;

    @FXML
    private Label lblDepth;

    @FXML
    private Label lblHeight;

    @FXML
    private Label lblWidth;

    @FXML
    private Label lblAudioJacks;

    @FXML
    private Label lblUsbPorts;

    @FXML
    private Label lblProductName1;

    @FXML
    private Label lblManufacturer1;

    @FXML
    private Label lblPrice1;

    @FXML
    private Label lblDepth1;

    @FXML
    private Label lblHeight1;

    @FXML
    private Label lblWidth1;

    @FXML
    private Label lblAudioJacks1;

    @FXML
    private Label lblUsbPorts1;

    @FXML
    private Label lblProductName2;

    @FXML
    private Label lblManufacturer2;

    @FXML
    private Label lblPrice2;

    @FXML
    private Label lblDepth2;

    @FXML
    private Label lblHeight2;

    @FXML
    private Label lblWidth2;

    @FXML
    private Label lblAudioJacks2;

    @FXML
    private Label lblUsbPorts2;

    @FXML
    private Label lblProductName3;

    @FXML
    private Label lblManufacturer3;

    @FXML
    private Label lblPrice3;

    @FXML
    private Label lblDepth3;

    @FXML
    private Label lblHeight3;

    @FXML
    private Label lblWidth3;

    @FXML
    private Label lblAudioJacks3;

    @FXML
    private Label lblUsbPorts3;

    @FXML
    private Label lblProductName4;

    @FXML
    private Label lblManufacturer4;

    @FXML
    private Label lblPrice4;

    @FXML
    private Label lblDepth4;

    @FXML
    private Label lblHeight4;

    @FXML
    private Label lblWidth4;

    @FXML
    private Label lblAudioJacks4;

    @FXML
    private Label lblUsbPorts4;

    @FXML
    private Label lblProductName5;

    @FXML
    private Label lblManufacturer5;

    @FXML
    private Label lblPrice5;

    @FXML
    private Label lblDepth5;

    @FXML
    private Label lblHeight5;

    @FXML
    private Label lblWidth5;

    @FXML
    private Label lblAudioJacks5;

    @FXML
    private Label lblUsbPorts5;

    @FXML
    private Label lblProductName6;

    @FXML
    private Label lblManufacturer6;

    @FXML
    private Label lblPrice6;

    @FXML
    private Label lblDepth6;

    @FXML
    private Label lblHeight6;

    @FXML
    private Label lblWidth6;

    @FXML
    private Label lblAudioJacks6;

    @FXML
    private Label lblUsbPorts6;

    @FXML
    private Label lblProductName7;

    @FXML
    private Label lblManufacturer7;

    @FXML
    private Label lblPrice7;

    @FXML
    private Label lblDepth7;

    @FXML
    private Label lblHeight7;

    @FXML
    private Label lblWidth7;

    @FXML
    private Label lblAudioJacks7;

    @FXML
    private Label lblUsbPorts7;

    @FXML
    private Label lblProductName8;

    @FXML
    private Label lblManufacturer8;

    @FXML
    private Label lblPrice8;

    @FXML
    private Label lblDepth8;

    @FXML
    private Label lblHeight8;

    @FXML
    private Label lblWidth8;

    @FXML
    private Label lblAudioJacks8;

    @FXML
    private Label lblUsbPorts8;

    private OpenCases opener = new OpenCases();
    private buildComputerController bcc = new buildComputerController();

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
        Label errorPlaceholder = new Label("Could not retrieve saved cases");
        //table.placeholderProperty().setValue(errorPlaceholder);
        System.out.println("RIP");
    }

    private void handleSucceed(WorkerStateEvent workerStateEvent) {
        List<Case>  allCases = (List<Case>) opener.getValue();
        // Makes all the product containers invisible, sets each one visible later depending on wether they have a component to showcase
        for (int i = 0; i < gp.getChildren().size(); i++) {
            gp.getChildren().get(i).setVisible(false);
        }
        placeComponentInfo(allCases);
    }

    @FXML
    void chooseComponent(ActionEvent event) {
        String idOfClickedLabel = ((Control)event.getSource()).getId();
        Parent p = ((Control) event.getSource()).getParent();

        List<Node> ContainerContent = p.getChildrenUnmodifiable();
        String[] priceWithoutKr = ((Label)ContainerContent.get(7)).getText().split(" ");
        String productName = ((Label)ContainerContent.get(0)).getText();
        String price = priceWithoutKr[0];
        String usbPorts = ((Label)ContainerContent.get(8)).getText();
        String hdAudioJacks = ((Label)ContainerContent.get(9)).getText();
        String widthCm = ((Label)ContainerContent.get(10)).getText();
        String heightCm = ((Label)ContainerContent.get(11)).getText();
        String depthCm = ((Label)ContainerContent.get(12)).getText();
        String manufacturer = ((Label)ContainerContent.get(13)).getText();
        try {
            Case chosenCase = new Case(usbPorts, hdAudioJacks, widthCm, heightCm, depthCm, Double.parseDouble(price), "", productName, manufacturer, "");
            bcc.updateComputer(chosenCase);
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }

    }

    private  void placeComponentInfo(List<Case> cases) {
        List<Node> productContainers = gp.getChildren();
        // this loop will have a lot of confusing casts because javafx's handling of Nodes vs Label etc.
        // all it does is set the values of the labels to the values of the Case object.
        for (int i = 0; i < cases.size(); i++) {
            productContainers.get(i).setVisible(true);
            ((Label)((AnchorPane)productContainers.get(i)).getChildren().get(0)).setText(cases.get(i).getProductName());
            ((Label)((AnchorPane)productContainers.get(i)).getChildren().get(7)).setText(String.valueOf(cases.get(i).getPrice())+" kr");
            ((Label)((AnchorPane)productContainers.get(i)).getChildren().get(8)).setText(cases.get(i).getNumberOfUSBPorts());
            ((Label)((AnchorPane)productContainers.get(i)).getChildren().get(9)).setText(cases.get(i).getHDAudioJacks());
            ((Label)((AnchorPane)productContainers.get(i)).getChildren().get(10)).setText(cases.get(i).getWidthCM());
            ((Label)((AnchorPane)productContainers.get(i)).getChildren().get(11)).setText(cases.get(i).getHeightCM());
            ((Label)((AnchorPane)productContainers.get(i)).getChildren().get(12)).setText(cases.get(i).getDepthCM());
            ((Label)((AnchorPane)productContainers.get(i)).getChildren().get(13)).setText(cases.get(i).getProductionCompany());
        }
    }
}
