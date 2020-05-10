package com.sample.controllers;

import com.sample.App;
import com.sample.BLL.UserLogic;
import com.sample.Models.Computer.Computer;
import com.sample.Models.ComputerComponents.*;
import com.sample.Models.Users.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Control;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Font;

import java.io.IOException;
import java.util.List;

public class buildComputerController {
    private regularUserController connector = new regularUserController();
    private User loggedInUser = connector.getLoggedInUser();
    private regularUserController mainControllerForRegularUsers = new regularUserController();
    private static Computer computerBeingBuilt = new Computer(null, null, null, null, null ,null, null, null);


    @FXML
    private ImageView computerImageView;

    @FXML
    private AnchorPane container;

    @FXML
    private Button homeBtn;

    @FXML
    private Button myComputersbtn;

    @FXML
    private Button logOutBtn;

    @FXML
    private Label GraphicsCard;

    @FXML
    private Label CPU;

    @FXML
    private Label RAM;

    @FXML
    private Label PowerSupply;

    @FXML
    private Label CoolingSystem;

    @FXML
    private Label MotherBoard;

    @FXML
    private Label Case;

    @FXML
    private Label StorageComponent;
    @FXML
    void initialize() {
        List<ComputerComponent> componentsNotNull = UserLogic.getCurrentlyChosenComponents(computerBeingBuilt);
        for(ComputerComponent chosenComponents : componentsNotNull) {
            System.out.println("from list"+chosenComponents);
            System.out.println(chosenComponents.getClass().getSimpleName());
            Label lbl = (Label) container.lookup("#"+chosenComponents.getClass().getSimpleName());
            lbl.setText(chosenComponents.getProductName());
        }
        if(computerBeingBuilt.getCreator() == null) {
            computerBeingBuilt.setCreator(loggedInUser);
        }
        System.out.println(loggedInUser.getMail());
        loggedInUser.setComputerInProduction(computerBeingBuilt);
        if (loggedInUser.getComputerInProduction().getCreator().getMail().equals(loggedInUser.getMail())) {
            System.out.println("Samme bruker");
            /*if(loggedInUser.getComputerInProduction().getComputerCase() != null) {
                Case.setText(loggedInUser.getComputerInProduction().getComputerCase().getProductName());
            } else if(loggedInUser.getComputerInProduction().getComputerCase() == null){
                Case.setText("Case");
                System.out.println("getComputerCase = null");
            }*/
        } else {
            System.out.println("Ikke samme bruker");
        }

    }
    public void updateComputer(ComputerComponent component) throws IOException {

        //System.out.println(component.getProductName());

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

    @FXML
    void logOut(ActionEvent event) throws IOException {
        mainControllerForRegularUsers.logOut();
    }

    @FXML
    void onClickHome(ActionEvent event) throws IOException {
        mainControllerForRegularUsers.onClickHome();
    }

    @FXML
    void onClickMyComputers(ActionEvent event) {
        mainControllerForRegularUsers.onClickMyComputers();
    }


    @FXML
    void makeBigger(MouseEvent event) {
        String idOfHoveredLabel = "#"+((Control)event.getSource()).getId();
        Label hoveredLabel = (Label)container.lookup(idOfHoveredLabel);
        hoveredLabel.setFont(new Font("System", 25));
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
        if (computerBeingBuilt.getComputerCase()!= null) {
            System.out.println("albaba"+ computerBeingBuilt.getComputerCase().getHDAudioJacks());
        }
        App.changeView("/fxml/chooseComponentWindows/choose"+idOfClickedLabel+".fxml", 0,0 );
    }
}
