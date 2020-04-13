package com.sample.controllers.addedComponentTableControllers;

import com.sample.App;
import com.sample.BLL.ComponentFactory;
import com.sample.DAL.OpenFile.OpenAddedComponents.OpenCases;
import com.sample.DAL.OpenFile.OpenAddedComponents.OpenCoolingSystems;
import com.sample.Models.ComputerComponents.Case;
import com.sample.Models.ComputerComponents.CoolingSystem;
import javafx.concurrent.WorkerStateEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class CoolingSystemViewController implements Initializable {
    @FXML private TableView<CoolingSystem> table;
    private OpenCoolingSystems opener = new OpenCoolingSystems();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            Thread openCoolingSystemsThread = new Thread(opener);
            opener.setOnSucceeded(this::handleSucceed);
            opener.setOnFailed(this::handleError);
            openCoolingSystemsThread.setDaemon(true);
            openCoolingSystemsThread.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void handleError(WorkerStateEvent workerStateEvent) {
        Label errorPlaceholder = new Label("Could not retrieve saved cooling systems");
        table.placeholderProperty().setValue(errorPlaceholder);
    }

    private void handleSucceed(WorkerStateEvent workerStateEvent) {
        table.getItems().setAll((List<CoolingSystem>) opener.getValue());
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
