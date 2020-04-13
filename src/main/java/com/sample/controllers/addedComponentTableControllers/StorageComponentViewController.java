package com.sample.controllers.addedComponentTableControllers;

import com.sample.App;
import com.sample.BLL.ComponentFactory;
import com.sample.DAL.OpenFile.OpenAddedComponents.OpenStorageComponents;
import com.sample.Models.ComputerComponents.Motherboard;
import com.sample.Models.ComputerComponents.StorageComponent;
import javafx.concurrent.WorkerStateEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class StorageComponentViewController implements Initializable {

    @FXML
    public TableView<StorageComponent> table;
    private OpenStorageComponents opener = new OpenStorageComponents();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            Thread openStorageComponentsThread = new Thread(opener);
            opener.setOnSucceeded(this::handleSucceed);
            opener.setOnFailed(this::handleError);
            openStorageComponentsThread.setDaemon(true);
            openStorageComponentsThread.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void handleError(WorkerStateEvent workerStateEvent) {
        Label errorPlaceholder = new Label("Could not retrieve saved cooling systems");
        table.placeholderProperty().setValue(errorPlaceholder);
    }

    private void handleSucceed(WorkerStateEvent workerStateEvent) {
        table.getItems().setAll((List<StorageComponent>) opener.getValue());
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
