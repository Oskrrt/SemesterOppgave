package com.sample.controllers.regularUserControllers;

import com.sample.App;
import com.sample.BLL.UserLogic;
import com.sample.Models.Computer.Computer;
import com.sample.Models.ComputerComponents.*;
import com.sample.Models.Users.User;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Font;
import javafx.stage.Stage;


import java.io.IOException;
import java.text.DecimalFormat;
import java.util.List;

public class buildComputerController {
    private regularUserController connector = new regularUserController();
    private User loggedInUser = connector.getLoggedInUser();
    private static Computer computerBeingBuilt = new Computer(null,null, null, null, null, null ,null, null, null, 0);
    private static boolean clearAccesories;

    @FXML
    private AnchorPane container;
    @FXML
    private CheckBox chkAccessories;
    @FXML
    private Label lblPrice;

    @FXML
    void initialize() {
        loggedInUser.setComputerInProduction(computerBeingBuilt);
        List<ComputerComponent> componentsNotNull = UserLogic.getCurrentlyChosenComponents(loggedInUser.getComputerInProduction());
        DecimalFormat formatter = new DecimalFormat("#0.00");
        lblPrice.setText("$ "+formatter.format(UserLogic.calculatePriceOfComputer(componentsNotNull)));
        for(ComputerComponent chosenComponents : componentsNotNull) {
            Label lbl = (Label) container.lookup("#"+chosenComponents.getClass().getSimpleName());
            lbl.setText(chosenComponents.getProductName());
        }
        if(loggedInUser.getComputerInProduction().getCreator() == null) {
            computerBeingBuilt.setCreator(loggedInUser);
        }
        System.out.println("loggedInUser: "+loggedInUser.getMail());
        System.out.println("Pc sin creator: "+computerBeingBuilt.getCreator().getMail());

        if (!loggedInUser.getComputerInProduction().getCreator().getMail().equals(loggedInUser.getMail())) {
            clearGUI();
            loggedInUser.getComputerInProduction().setCreator(loggedInUser);
            clearAccesories = true;
        }
    }
    public boolean clearAccessories() {
        return clearAccesories;
    }
    private void clearGUI() {
        System.out.println("Inne i clear");
        for (Node child : container.getChildren()) {
            if (child instanceof Label) {
                ((Label) child).setText(UserLogic.getDefaultTextForLabels(child.getId()));
            }
        }
        loggedInUser.getComputerInProduction().setMemory(null);
        loggedInUser.getComputerInProduction().setCPU(null);
        loggedInUser.getComputerInProduction().setPowerSupply(null);
        loggedInUser.getComputerInProduction().setMotherboard(null);
        loggedInUser.getComputerInProduction().setStorageComponent(null);
        loggedInUser.getComputerInProduction().setGraphicsCard(null);
        loggedInUser.getComputerInProduction().setCooling(null);
        loggedInUser.getComputerInProduction().setComputerCase(null);

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

    User getLoggedInUser() {
        return loggedInUser;
    }
    @FXML
    void logOut(ActionEvent event) throws IOException, InterruptedException {
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
    void onClickNext() throws IOException {
        String location;
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
