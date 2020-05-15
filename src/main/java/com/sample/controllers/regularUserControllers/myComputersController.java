package com.sample.controllers.regularUserControllers;


import com.sample.DAL.OpenFile.OpenTxt;
import com.sample.Models.Computer.Computer;
import com.sample.Models.Computer.ComputerWithAccessories;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.WorkerStateEvent;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableView;

import java.io.IOException;

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
        ObservableList<Computer> info = FXCollections.observableArrayList(opener.getValue());
        table.getItems().setAll(info);

    }

    private void populateAccessoryTable(){
        ObservableList<ComputerWithAccessories> info = FXCollections.observableArrayList(opener.getSavedComputersWithAccessories());
        accessoryTable.getItems().setAll((info));
    }

    private void threadError(WorkerStateEvent e) {
        var ex = e.getSource().getException();
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
