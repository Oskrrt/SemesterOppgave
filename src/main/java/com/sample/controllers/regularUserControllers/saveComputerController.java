package com.sample.controllers.regularUserControllers;

import com.sample.App;
import com.sample.DAL.SaveFile.SaveTxt;
import com.sample.Exceptions.ValidationException;
import com.sample.Models.Computer.Computer;
import com.sample.Models.Users.User;
import javafx.concurrent.WorkerStateEvent;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextInputDialog;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.text.DecimalFormat;
import java.util.List;
import java.util.Optional;

public class saveComputerController {
    private regularUserController connector = new regularUserController();
    private static buildComputerController bc = new buildComputerController();
    private static User loggedInUser = bc.getLoggedInUser();
    private Computer computerToBeSaved = loggedInUser.getComputerInProduction();
    private SaveTxt saver;
    @FXML
    private VBox container;

    @FXML
    void initialize() {
        placeComponentInfo();
    }

    private void placeComponentInfo() {
        List<Node> aps = container.getChildren();
        ((Label)((AnchorPane)aps.get(0)).getChildren().get(1)).setText(computerToBeSaved.getComputerCase().getProductName());
        ((Label)((AnchorPane)aps.get(1)).getChildren().get(1)).setText(computerToBeSaved.getMemory().getProductName());
        ((Label)((AnchorPane)aps.get(2)).getChildren().get(1)).setText(computerToBeSaved.getCPU().getProductName());
        ((Label)((AnchorPane)aps.get(3)).getChildren().get(1)).setText(computerToBeSaved.getCooling().getProductName());
        ((Label)((AnchorPane)aps.get(4)).getChildren().get(1)).setText(computerToBeSaved.getMotherboard().getProductName());
        ((Label)((AnchorPane)aps.get(5)).getChildren().get(1)).setText(computerToBeSaved.getGraphicsCard().getProductName());
        ((Label)((AnchorPane)aps.get(6)).getChildren().get(1)).setText(computerToBeSaved.getStorageComponent().getProductName());
        ((Label)((AnchorPane)aps.get(7)).getChildren().get(1)).setText(computerToBeSaved.getPowerSupply().getProductName());
        DecimalFormat df2 = new DecimalFormat("#0.00");
        ((Label)((AnchorPane)aps.get(8)).getChildren().get(1)).setText("$ "+df2.format(computerToBeSaved.getPrice()));
    }

    @FXML
    void back(ActionEvent event) throws IOException {
        App.changeView("/fxml/BuildComputer/buildComputer.fxml", 0, 0);
    }

    @FXML
    void logOut(ActionEvent event) throws IOException {
        connector.logOut();
    }

    @FXML
    void onClickHome(ActionEvent event) throws IOException {
        connector.onClickHome();
    }

    @FXML
    void onClickMyComputers(ActionEvent event) throws IOException {
        connector.onClickMyComputers();
    }

    @FXML
    void onClickSave(ActionEvent event) {
        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("Save Computer");
        dialog.setHeaderText(null);
        dialog.setContentText("Enter the name of this computer: ");
        Optional<String> result = dialog.showAndWait();
        result.ifPresent(name->{
            try {
                //System.out.println(name);
                computerToBeSaved.validateName(name);
                computerToBeSaved.setName(name);
                saver = new SaveTxt(computerToBeSaved);
                Thread tr = new Thread(saver);
                saver.setOnSucceeded(this::succeed);
                tr.setDaemon(true);
                tr.start();
            } catch (ValidationException e) {
                Alert a = new Alert(Alert.AlertType.ERROR);
                a.setTitle("Invalid Name");
                a.setHeaderText(null);
                a.setContentText(e.getMessage());
                a.showAndWait();
                onClickSave(event);
            }
        });
    }

    private void succeed(WorkerStateEvent e) {
        if (saver.getValue()) {
            Alert a = new Alert(Alert.AlertType.INFORMATION);
            a.setTitle("Computer saved");
            a.setHeaderText("Computer saved successfully!");
            a.setContentText(null);
            a.showAndWait();
        } else if (!saver.getValue()) {
            Alert a = new Alert(Alert.AlertType.ERROR);
            a.setTitle("Error");
            a.setHeaderText("Could not save the computer.\nPlease try again.");
            a.setContentText(null);
            a.showAndWait();
        }
    }

}
