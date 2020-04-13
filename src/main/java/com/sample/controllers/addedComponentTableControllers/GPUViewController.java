package com.sample.controllers.addedComponentTableControllers;

import com.sample.App;
import com.sample.BLL.ComponentFactory;
import com.sample.Models.ComputerComponents.GraphicsCard;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableView;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class GPUViewController implements Initializable {
    @FXML
    private TableView<GraphicsCard> table;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            table.getItems().setAll(ComponentFactory.createGraphicsCardsFromFile());
        } catch (IOException e) {
            e.printStackTrace();
        }
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
