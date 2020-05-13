package com.sample.controllers.regularUserControllers;

import com.sample.App;
import com.sample.BLL.UserLogic;
import com.sample.Models.Computer.Computer;
import com.sample.Models.Computer.ComputerWithAccessories;
import com.sample.Models.ComputerComponents.*;
import com.sample.Models.Users.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.text.DecimalFormat;
import java.util.List;

public class addAccessoriesController {
    private regularUserController connector = new regularUserController();
    private static buildComputerController bc = new buildComputerController();
    private static User loggedInUser = bc.getLoggedInUser();
    private static ComputerWithAccessories computerBeingBuilt = new ComputerWithAccessories(loggedInUser.getComputerInProduction(), null, null, null, null);

    @FXML
    private AnchorPane container;
    @FXML
    private HBox accessories;
    @FXML
    private AnchorPane apMonitor;
    @FXML
    private AnchorPane apSpeaker;
    @FXML
    private AnchorPane apMouse;
    @FXML
    private AnchorPane apKeyboard;
    @FXML
    private Label lblPrice;

    @FXML
    void back(ActionEvent event) throws IOException {
        App.changeView("/fxml/BuildComputer/buildComputer.fxml", 0 ,0);
    }
    User getLoggedInUser() {
        return loggedInUser;
    }
    @FXML
    void initialize() {
        AnchorPane[] containers = {apMonitor, apSpeaker, apMouse, apKeyboard};
        //Makes the info in the containers invisible while they do not have any actual information to showcase.
        for (AnchorPane ap : containers) {
            ap.setVisible(false);
        }

        // a list of all the accessories that are currently selected
        List<ComputerComponent> accessoriesNotNull = UserLogic.getCurrentlyChosenComponentsForAccessorisedComputer(computerBeingBuilt);
        DecimalFormat formatter = new DecimalFormat("#0.00");
        lblPrice.setText("$ "+formatter.format(UserLogic.calculatePriceOfComputer(accessoriesNotNull)+computerBeingBuilt.getComputer().getPrice()));
        // a list of all the components that are currently selected
        List<ComputerComponent> componentsNotNull = UserLogic.getCurrentlyChosenComponents(computerBeingBuilt.getComputer());
        for(ComputerComponent chosenAccessories : accessoriesNotNull) {
            AnchorPane ap = (AnchorPane) container.lookup("#ap"+chosenAccessories.getClass().getSimpleName());
            System.out.println(chosenAccessories.getClass().getSimpleName());
            ap.setVisible(true);
            placeComponentInfo(chosenAccessories, ap);
        }
        //System.out.println(UserLogic.calculatePriceOfComputer(computerBeingBuilt));
    }

    private void placeComponentInfo(ComputerComponent component, AnchorPane ap) {
        if (component instanceof Mouse) {
            ((Label)ap.getChildren().get(0)).setText(component.getProductName());
            ((Label)ap.getChildren().get(4)).setText("$ "+component.getPrice());
            ((Label)ap.getChildren().get(5)).setText(((Mouse) component).getIsWireless());
            ((Label)ap.getChildren().get(6)).setText(component.getProductionCompany());
            ((Label)ap.getChildren().get(7)).setText(component.getSerialNumber());
            ((Label)ap.getChildren().get(8)).setText(component.getDescription());
        } else if (component instanceof Keyboard) {
            ((Label)ap.getChildren().get(0)).setText(component.getProductName());
            ((Label)ap.getChildren().get(5)).setText("$ "+component.getPrice());
            ((Label)ap.getChildren().get(6)).setText(((Keyboard) component).getLanguage());
            ((Label)ap.getChildren().get(7)).setText(((Keyboard) component).getIsWireless());
            ((Label)ap.getChildren().get(8)).setText(component.getProductionCompany());
            ((Label)ap.getChildren().get(9)).setText(component.getSerialNumber());
            ((Label)ap.getChildren().get(10)).setText(component.getDescription());
        } else if (component instanceof Speaker) {
            ((Label)ap.getChildren().get(0)).setText(component.getProductName());
            ((Label)ap.getChildren().get(4)).setText("$ "+component.getPrice());
            ((Label)ap.getChildren().get(5)).setText(((Speaker) component).getInputType());
            ((Label)ap.getChildren().get(6)).setText(component.getProductionCompany());
            ((Label)ap.getChildren().get(7)).setText(component.getSerialNumber());
            ((Label)ap.getChildren().get(8)).setText(component.getDescription());
        } else if (component instanceof Monitor) {
            ((Label)ap.getChildren().get(0)).setText(component.getProductName());
            ((Label)ap.getChildren().get(7)).setText("$ "+component.getPrice());
            ((Label)ap.getChildren().get(8)).setText(((Monitor) component).getDisplayType());
            ((Label)ap.getChildren().get(9)).setText(((Monitor) component).getInches());
            ((Label)ap.getChildren().get(10)).setText(((Monitor) component).getResolution());
            ((Label)ap.getChildren().get(11)).setText(((Monitor) component).getConnector());
            ((Label)ap.getChildren().get(12)).setText(component.getProductionCompany());
            ((Label)ap.getChildren().get(13)).setText(component.getSerialNumber());
            ((Label)ap.getChildren().get(14)).setText(component.getDescription());
        }
    }

    public void updateComputer(ComputerComponent component) {
        System.out.println(component.getClass());
        if (component instanceof Mouse) {
            computerBeingBuilt.setMouse((Mouse) component);
        } else if (component instanceof Keyboard) {
            computerBeingBuilt.setKeyboard((Keyboard) component);
        } else if (component instanceof Speaker) {
            computerBeingBuilt.setSpeaker(((Speaker) component));
        } else if (component instanceof Monitor) {
            computerBeingBuilt.setMonitor((Monitor) component);
        }
    }
    @FXML
    void removeComponent(ActionEvent event) {
        String idOfClickedButton = ((Button)event.getSource()).getId();
        System.out.println(idOfClickedButton);
        switch (idOfClickedButton) {
            case "removeMonitor":
                computerBeingBuilt.setMonitor(null);
                break;
            case "removeSpeaker":
                computerBeingBuilt.setSpeaker(null);
                break;
            case "removeMouse":
                computerBeingBuilt.setMouse(null);
                break;
            case "removeKeyboard":
                computerBeingBuilt.setKeyboard(null);
                break;
        }
        initialize();
    }
    private double generateTotalPriceOfComputerAndAccessories(List<ComputerComponent> accessoriesNotNull, ComputerWithAccessories computerBeingBuilt) {
        double price = computerBeingBuilt.getComputer().getPrice();
        for (ComputerComponent accessory : accessoriesNotNull) {
            price+=accessory.getPrice();
        }
        return price;
    }
    @FXML
    void onClickNext(ActionEvent event) throws IOException {
        List<ComputerComponent> accessoriesNotNull = UserLogic.getCurrentlyChosenComponentsForAccessorisedComputer(computerBeingBuilt);
        if (accessoriesNotNull.size() < 1) {
            Alert a = new Alert(Alert.AlertType.INFORMATION);
            a.setTitle("Computer unfinished");
            a.setHeaderText(null);
            a.setContentText("Please select at least one accessory");
            a.showAndWait();
            return;
        }
        //computerBeingBuilt.setPrice(generateTotalPriceOfComputerAndAccessories(accessoriesNotNull, computerBeingBuilt));
        double price = computerBeingBuilt.getComputer().getPrice() + UserLogic.calculatePriceOfComputer(accessoriesNotNull);
        computerBeingBuilt.setPrice(price);
        System.out.println("før setter pc til bruker "+computerBeingBuilt.getPrice());
        loggedInUser.setComputerInProduction(computerBeingBuilt);
        System.out.println("Etter setter pc til bruker "+loggedInUser.getComputerInProduction().getPrice());
        App.changeView("/fxml/BuildComputer/saveAccessorisedComputer.fxml", 0, 0);
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
    void openChoiceWindow(MouseEvent event) throws IOException {
        String idOfClickedVBox = ((VBox)event.getSource()).getId();
        App.changeView("/fxml/BuildComputer/chooseComponentWindows/choose" +idOfClickedVBox+".fxml", 0,0 );
    }

}
