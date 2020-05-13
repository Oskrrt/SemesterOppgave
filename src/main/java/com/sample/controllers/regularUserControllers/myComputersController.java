package com.sample.controllers.regularUserControllers;


import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.TextFieldTableCell;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class myComputersController {
    private regularUserController connector = new regularUserController();

    @FXML
    private TableView<String> table;

    @FXML
    void initialize() {
       /* System.out.println(table);
        ObservableList<TableColumn<String, ?>> cols = table.getColumns();

        ObservableList<String> he = FXCollections.observableArrayList();
        he.add("fdkafndsk");
        he.add("fdnmdfnsgfn");
        for (TableColumn col : cols) {
            col.setCellFactory(TextFieldTableCell.forTableColumn());
        }
        //table.getColumns().addAll(cols);
        table.setItems(he);*/
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
