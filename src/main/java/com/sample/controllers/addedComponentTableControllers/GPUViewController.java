package com.sample.controllers.addedComponentTableControllers;

import com.sample.App;
import com.sample.DAL.OpenFile.OpenAddedComponents.OpenGPUs;
import com.sample.DAL.OpenFile.OpenAddedComponents.OpenRAM;
import com.sample.Models.ComputerComponents.GraphicsCard;
import javafx.concurrent.WorkerStateEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class GPUViewController implements Initializable {
    @FXML
    private TableView<GraphicsCard> table;
    private OpenGPUs opener = new OpenGPUs();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            Thread openGPUsThread = new Thread(opener);
            opener.setOnSucceeded(this::handleSucceed);
            opener.setOnFailed(this::handleError);
            openGPUsThread.setDaemon(true);
            openGPUsThread.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void handleError(WorkerStateEvent workerStateEvent) {
        Label errorPlaceholder = new Label("Could not retrieve saved cooling systems");
        table.placeholderProperty().setValue(errorPlaceholder);
    }

    private void handleSucceed(WorkerStateEvent workerStateEvent) {
        table.getItems().setAll((List<GraphicsCard>) opener.getValue());
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
