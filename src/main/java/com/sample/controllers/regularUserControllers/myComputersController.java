package com.sample.controllers.regularUserControllers;


import com.sample.DAL.OpenFile.OpenTxt;
import com.sample.DAL.OpenFile.Subtypes.OpenAddedComponents;
import com.sample.DAL.OpenFile.Subtypes.OpenCases;
import com.sample.Exceptions.ValidationException;
import com.sample.Models.Computer.Computer;
import com.sample.Models.Computer.ComputerWithAccessories;
import com.sample.Models.ComputerComponents.Case;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.WorkerStateEvent;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

import javax.xml.stream.events.Comment;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

public class myComputersController {

    private regularUserController connector = new regularUserController();
    private OpenTxt opener;
    @FXML
    private TableView<Computer> table;
    @FXML
    private TableView<ComputerWithAccessories> accessoryTable;

    @FXML
    void initialize() {
        System.out.println(table);
        startThread();
        populateAccessoryTable();
    }



    private void startThread() {
        try{
            opener = new OpenTxt(connector.getLoggedInUser());
            Thread tr = new Thread(opener);
            opener.setOnSucceeded(this::succeed);
            opener.setOnFailed(this::threadError);
            tr.setDaemon(true);
            tr.start();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    private void succeed(WorkerStateEvent e) {
        if (opener.getValue() == null){
            Label errorPlaceholder = new Label("A file has been tampered with. Your computers are not accessible.");
            table.placeholderProperty().setValue(errorPlaceholder);
        } else {
            ObservableList<Computer> info = FXCollections.observableArrayList(opener.getValue());
            table.getItems().setAll(info);
        }


    }

    private void populateAccessoryTable(){
        if (opener.getSavedComputersWithAccessories() == null){
            Label errorPlaceholder = new Label("A file has been tampered with. Your computers are not accessible.");
            accessoryTable.placeholderProperty().setValue(errorPlaceholder);
        } else{
            ObservableList<ComputerWithAccessories> info = FXCollections.observableArrayList(opener.getSavedComputersWithAccessories());
            accessoryTable.getItems().setAll((info));
        }

    }

    private void threadError(WorkerStateEvent e) {
        var ex = e.getSource().getException();
        Alert errorBox = new Alert(Alert.AlertType.ERROR);
        errorBox.setTitle(ex.getMessage());
        ex.printStackTrace();
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

}
