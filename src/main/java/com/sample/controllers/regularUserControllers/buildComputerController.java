package com.sample.controllers.regularUserControllers;

import com.sample.App;
import com.sample.BLL.UserLogic;
import com.sample.Models.Computer.Computer;
import com.sample.Models.ComputerComponents.*;
import com.sample.Models.Users.User;
import javafx.beans.property.SimpleStringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Control;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Polyline;
import javafx.scene.text.Font;


import java.io.IOException;
import java.text.DecimalFormat;
import java.util.List;

public class buildComputerController {
    private regularUserController connector = new regularUserController();
    private User loggedInUser = connector.getLoggedInUser();
    private static Computer computerBeingBuilt = new Computer(null,null, null, null, null, null ,null, null, null, 0);


    @FXML
    private AnchorPane container;
    @FXML
    private CheckBox chkAccessories;
    @FXML
    private Label lblPrice;
    @FXML
    private Polyline polyCooling;
    @FXML
    private Polyline polyRam;
    @FXML
    private Polyline polyCase;
    @FXML
    private Polyline polyPowerSupply;
    @FXML
    private Polyline polyCpu;
    @FXML
    private Polyline polyGpu;
    @FXML
    private Polyline polyMotherboard;
    @FXML
    private Polyline polyStorage;

    @FXML
    void initialize() {
        loggedInUser.setComputerInProduction(computerBeingBuilt);
        List<ComputerComponent> componentsNotNull = UserLogic.getCurrentlyChosenComponents(loggedInUser.getComputerInProduction());
        DecimalFormat formatter = new DecimalFormat("#0.00");
        lblPrice.setText("$ "+formatter.format(UserLogic.calculatePriceOfComputer(componentsNotNull)));
        extendPolyLinesAccordingToNameOfProduct(componentsNotNull);
        for(ComputerComponent chosenComponents : componentsNotNull) {
            Label lbl = (Label) container.lookup("#"+chosenComponents.getClass().getSimpleName());
            lbl.setText(chosenComponents.getProductName());
        }
        if(computerBeingBuilt.getCreator() == null) {
            computerBeingBuilt.setCreator(loggedInUser);
        }

        //System.out.println(loggedInUser.getComputerInProduction());
        //System.out.println(loggedInUser.getComputerInProduction().allFieldsSet());

        if (loggedInUser.getComputerInProduction().getCreator().getMail().equals(loggedInUser.getMail())) {
            /*if(loggedInUser.getComputerInProduction().getComputerCase() != null) {
                Case.setText(loggedInUser.getComputerInProduction().getComputerCase().getProductName());
            } else if(loggedInUser.getComputerInProduction().getComputerCase() == null){
                Case.setText("Case");
                System.out.println("getComputerCase = null");
            }*/
        } else {
        }
    }
    public void updateComputer(ComputerComponent component) {

        if (component instanceof Case) {
            loggedInUser.getComputerInProduction().setComputerCase((Case) component);
        } else if (component instanceof CoolingSystem) {

            loggedInUser.getComputerInProduction().setCooling((CoolingSystem) component);
        } else if (component instanceof GraphicsCard) {
            loggedInUser.getComputerInProduction().setGraphicsCard(((GraphicsCard) component));
        } else if (component instanceof StorageComponent) {
            loggedInUser.getComputerInProduction().setStorageComponent((StorageComponent) component);
        } else if (component instanceof Motherboard) {
            loggedInUser.getComputerInProduction().setMotherboard((Motherboard) component);
        } else if (component instanceof PowerSupply) {
            loggedInUser.getComputerInProduction().setPowerSupply((PowerSupply) component);
        } else if (component instanceof Processor) {
            loggedInUser.getComputerInProduction().setCPU((Processor) component);
        } else if (component instanceof RAM) {
            loggedInUser.getComputerInProduction().setMemory((RAM) component);
        }
    }

    private void extendPolyLinesAccordingToNameOfProduct(List<ComputerComponent> chosenComponents) {
        for (ComputerComponent component : chosenComponents) {
            if (component instanceof CoolingSystem) {
                polyCooling.getPoints().set(0, -249.0);
            } else if (component instanceof Case) {
                polyCase.getPoints().set(0, -80.0);
            } else if (component instanceof RAM) {
                polyRam.getPoints().set(6, 90.0);
            } else if (component instanceof Processor) {
                polyCpu.getPoints().set(0, 50.0);
            } else if (component instanceof GraphicsCard) {
                polyGpu.getPoints().set(0, -15.0);
            } else if (component instanceof Motherboard) {
                polyMotherboard.getPoints().set(0, -250.0);
            } else if (component instanceof StorageComponent) {
                polyStorage.getPoints().set(0, -20.0);
            } else if (component instanceof PowerSupply) {
                //Label lbl = (Label) container.lookup("#"+component.getClass().getSimpleName());
                polyPowerSupply.getPoints().set(6, -115.0);
            }

        }

    }

    User getLoggedInUser() {
        return loggedInUser;
    }
    @FXML
    void logOut() throws IOException {
        connector.logOut();
    }

    @FXML
    void onClickHome() throws IOException {
        connector.onClickHome();
    }

    @FXML
    void onClickMyComputers() throws IOException {
        connector.onClickMyComputers();
    }


    @FXML
    void makeBigger(MouseEvent event) {
        String idOfHoveredLabel = "#"+((Control)event.getSource()).getId();
        Label hoveredLabel = (Label)container.lookup(idOfHoveredLabel);
        hoveredLabel.setFont(new Font("System", 22));
    }

    @FXML
    void makeNormal(MouseEvent event) {
        String idOfHoveredLabel = "#"+((Control)event.getSource()).getId();
        Label hoveredLabel = (Label)container.lookup(idOfHoveredLabel);
        hoveredLabel.setFont(new Font("System", 20));
    }

    @FXML
    void openChoiceWindow(MouseEvent event) throws IOException {
        String idOfClickedLabel = ((Control)event.getSource()).getId();
        App.changeView("/fxml/BuildComputer/chooseComponentWindows/choose" +idOfClickedLabel+".fxml", 0,0 );
    }

    @FXML
    void onClickNext(ActionEvent event) throws IOException {
        String location = "";
        List<ComputerComponent> componentsNotNull = UserLogic.getCurrentlyChosenComponents(loggedInUser.getComputerInProduction());
        loggedInUser.getComputerInProduction().setPrice(UserLogic.calculatePriceOfComputer(componentsNotNull));
        if (!loggedInUser.getComputerInProduction().allFieldsSet()) {
            Alert a = new Alert(Alert.AlertType.INFORMATION);
            a.setTitle("Computer unfinished");
            a.setHeaderText(null);
            a.setContentText("Please select all of the computer components before proceeding");
            a.showAndWait();
            return;
        }
        if (chkAccessories.isSelected()) {
            location = "/fxml/BuildComputer/addAccessories.fxml";
        } else {
            location = "/fxml/BuildComputer/SaveComputer.fxml";
        }
        App.changeView(location, 0, 0);
    }
}
