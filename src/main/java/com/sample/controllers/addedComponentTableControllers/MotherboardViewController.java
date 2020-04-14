package com.sample.controllers.addedComponentTableControllers;

import com.sample.App;
import com.sample.DAL.OpenFile.Subtypes.OpenAddedComponents;
import com.sample.DAL.OpenFile.Subtypes.OpenMotherBoards;
import com.sample.Models.ComputerComponents.Motherboard;
import javafx.concurrent.WorkerStateEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class MotherboardViewController implements Initializable {
    @FXML
    private TableView<Motherboard> table;
    private OpenAddedComponents opener = new OpenMotherBoards();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            Thread openMotherBoardsThread = new Thread(opener);
            opener.setOnSucceeded(this::handleSucceed);
            opener.setOnFailed(this::handleError);
            openMotherBoardsThread.setDaemon(true);
            openMotherBoardsThread.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void handleError(WorkerStateEvent workerStateEvent) {
        Label errorPlaceholder = new Label("Could not retrieve saved cooling systems");
        table.placeholderProperty().setValue(errorPlaceholder);
    }

    private void handleSucceed(WorkerStateEvent workerStateEvent) {
        table.getItems().setAll((List<Motherboard>) opener.getValue());
    }

    @FXML
    private void viewSwapper(){
        try {
            App.changeView("/fxml/viewAddedComponents.fxml", 1200, 900);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
