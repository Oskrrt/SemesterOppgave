package com.sample.controllers.regularUserControllers;


import com.sample.DAL.OpenFile.OpenTxt;
import com.sample.Models.Computer.Computer;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.WorkerStateEvent;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Tab;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.TextFieldTableCell;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class myComputersController {
    private regularUserController connector = new regularUserController();
    private OpenTxt opener;
    @FXML
    private TableView<Computer> table;

    @FXML
    void initialize() {
        System.out.println(table);
        startThread();
    }
    private void startThread() {
        try{
            opener = new OpenTxt();
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
        table.getItems().setAll(opener.getValue());

       /* TableColumn<String, String> col1 = new TableColumn<>("Name");
        col1.setCellValueFactory(data-> new SimpleStringProperty("Hei du"));

        TableColumn<String, String> col2 = new TableColumn<>("Price");
        col2.setCellValueFactory(data-> new SimpleStringProperty("faen å stygg du"));
        table.getColumns().add(col1);
        table.getColumns().add(col2);
        ObservableList<String> strings = FXCollections.observableArrayList("entore", "enfire");
        table.setItems(strings);*/
        /*for (TableColumn<String, String> col : cols) {
            col.setCellValueFactory(data -> {
                SimpleStringProperty s = new SimpleStringProperty(data.getValue());
                System.out.println(s);
                return s;
            });
        }*/

        //table.setItems(info);
        //table.getItems().setAll(info);
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
