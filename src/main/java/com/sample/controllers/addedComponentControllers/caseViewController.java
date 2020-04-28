package com.sample.controllers.addedComponentControllers;

import com.sample.App;
import com.sample.BLL.ComponentDeleter;
import com.sample.BLL.EditTable;
import com.sample.DAL.OpenFile.Subtypes.OpenAddedComponents;
import com.sample.DAL.OpenFile.Subtypes.OpenCases;
import com.sample.Models.ComputerComponents.Case;
import javafx.concurrent.WorkerStateEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import kong.unirest.JsonPatchItem;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.net.URL;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

public class caseViewController {
    @FXML private TableColumn description;
    @FXML private TableView<Case> table;


    private OpenAddedComponents opener = new OpenCases();
    private OpenAddedComponents deleter = new OpenCases();

    public void initialize() {
        table.setEditable(true);
        startThread();

    }

    @FXML
    private void viewSwapper(){
        try {
            App.changeView("/fxml/viewAddedComponents.fxml", 1200, 900);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void startThread(){
        try {
            Thread openCaseFilesThread = new Thread(opener);
            opener.setOnSucceeded(this::handleSucceed);
            opener.setOnFailed(this::handleError);
            openCaseFilesThread.setDaemon(true);
            openCaseFilesThread.start();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void handleError(WorkerStateEvent workerStateEvent) {
        Label errorPlaceholder = new Label("Could not retrieve saved cases");
        table.placeholderProperty().setValue(errorPlaceholder);
    }

    private void handleSucceed(WorkerStateEvent workerStateEvent) {
        try{
            table.getItems().setAll((List<Case>) opener.getValue());

        } catch (NullPointerException e){
            System.out.println("Inside succeed catch");
            table.refresh();
            table.placeholderProperty().setValue(new Label("No cases saved"));
        }
    }

    @FXML
    private void deleteCase(){
        try{
            final File folder = new File("src/main/java/com/sample/DAL/SavedFiles/NewComponents/");
            Case caseToBeDeleted = table.getSelectionModel().getSelectedItem();
            Alert deleteConfirmation = new Alert(Alert.AlertType.CONFIRMATION);
            deleteConfirmation.setTitle("Confirm deletion");
            deleteConfirmation.setHeaderText("Are you sure you want to delete "+caseToBeDeleted.getProductName()+"?");
            deleteConfirmation.setContentText("This will permanently remove the computer component");
            Optional<ButtonType> result = deleteConfirmation.showAndWait();
            try {
                if (result.get() == ButtonType.OK){
                    ComponentDeleter.deleteComponent(folder, caseToBeDeleted);
                    updateAfterDeleteThread();
                }
            } catch(Exception e){
                e.printStackTrace();
            }
        } catch(NullPointerException e){
            Alert errorBox = new Alert(Alert.AlertType.ERROR);
            errorBox.setTitle("No component selected");
            errorBox.setHeaderText("You must select a component to delete it");
            errorBox.showAndWait();
        }

    }

    private void updateAfterDeleteThread(){
        Thread openFilesThread = new Thread(deleter);
        deleter.setOnSucceeded(this::handleDeleteSucceed);
        deleter.setOnFailed(this::handleDeleteError);
        openFilesThread.setDaemon(true);
        openFilesThread.start();
    }

    private void handleDeleteSucceed(WorkerStateEvent workerStateEvent) {
        try{
            table.setEditable(true);
            table.getItems().setAll((List<Case>) deleter.getValue());
            if(table.getItems().toArray().length == 0){
                table.placeholderProperty().setValue(new Label("No cases saved"));
            }
        } catch (NullPointerException e){
            table.placeholderProperty().setValue(new Label("Something went wrong"));
        }
    }

    private void handleDeleteError(WorkerStateEvent workerStateEvent) {
        Alert errorBox = new Alert(Alert.AlertType.ERROR);
        errorBox.setTitle("Something went wrong while deleting");
    }


    @FXML private void editCase(TableColumn.CellEditEvent editedCell){
        Case selectedCase = table.getSelectionModel().getSelectedItem();
        selectedCase.setDescription(editedCell.getNewValue().toString());
    }

    private void initEditableTable(){



    }
    @FXML private void test(){

    }
}
