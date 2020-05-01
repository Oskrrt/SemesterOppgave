package com.sample.controllers.addedComponentControllers;

import com.sample.App;
import com.sample.BLL.AdminLogic;
import com.sample.BLL.ComponentDeleter;
import com.sample.BLL.EditTable;
import com.sample.BLL.InputValidation.ValidationException;
import com.sample.DAL.OpenFile.Subtypes.OpenAddedComponents;
import com.sample.DAL.OpenFile.Subtypes.OpenCases;
import com.sample.Models.ComputerComponents.Case;
import com.sample.Models.ComputerComponents.ComputerComponent;
import com.sample.Models.Users.Admin;
import javafx.beans.value.ObservableValue;
import javafx.concurrent.WorkerStateEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.util.Callback;
import javafx.util.converter.DoubleStringConverter;
import kong.unirest.JsonPatchItem;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.Stack;

public class caseViewController {
    @FXML private TableView<Case> table;
    @FXML private TableColumn<Case, String> description;
    @FXML private TableColumn<Case, Double> price;
    @FXML private TableColumn<Case, String> manufacturer;
    @FXML private TableColumn<Case, String> productName;
    @FXML private TableColumn<Case, String> serialNumber;
    @FXML private TableColumn<Case, String> USBPorts;
    @FXML private TableColumn<Case, String> audioJacks;
    @FXML private TableColumn<Case, String> width;
    @FXML private TableColumn<Case, String> height;
    @FXML private TableColumn<Case, String> depth;



    private OpenAddedComponents opener = new OpenCases();
    private OpenAddedComponents deleter = new OpenCases();

    public void initialize() {
        table.setEditable(true);
        price.setCellFactory(TextFieldTableCell.forTableColumn(new DoubleStringConverter(){
            @Override
            public Double fromString(String s) {
                try{
                    return super.fromString(s);
                } catch (NumberFormatException e){
                    return Double.NaN;
                }
            }
        }));
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
        StackTraceElement[] stack = Thread.currentThread().getStackTrace();
        for (StackTraceElement s : stack){
            System.out.println(s);
        }
        Label errorPlaceholder = new Label("Could not retrieve saved cases");
        table.placeholderProperty().setValue(errorPlaceholder);
    }

    private void handleSucceed(WorkerStateEvent workerStateEvent) {
        try{
            table.getItems().setAll((List<Case>) opener.getValue());

        } catch (NullPointerException e){
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
                    updateTableViewThread();
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

    private void updateTableViewThread(){
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


    @FXML
    private void editDescription(TableColumn.CellEditEvent cellEditEvent){
        String originalDescription = cellEditEvent.getOldValue().toString();
        Case selectedCase = (Case)cellEditEvent.getRowValue();
        File file = new File("src/main/java/com/sample/DAL/SavedFiles/NewComponents/Cases/"+selectedCase.getProductName()+".jobj");
        try{
            selectedCase.setDescription(cellEditEvent.getNewValue().toString());
            selectedCase.validate();
            AdminLogic.editFile(file, selectedCase);
            table.refresh();
        } catch (IOException e){
            Alert errorBox = new Alert(Alert.AlertType.ERROR);
            errorBox.setTitle("Something went wrong while editing file");
        } catch (ValidationException e) {
            Alert errorBox = new Alert(Alert.AlertType.ERROR);
            errorBox.setHeaderText(e.getLocalizedMessage());
            errorBox.showAndWait();
            selectedCase.setDescription(originalDescription);
            table.refresh();
        }
    }

    @FXML
    private void editPrice(TableColumn.CellEditEvent cellEditEvent){
        double originalPrice = (double)cellEditEvent.getOldValue();
        Case selectedCase = (Case)cellEditEvent.getRowValue();
        File file = new File("src/main/java/com/sample/DAL/SavedFiles/NewComponents/Cases/"+selectedCase.getProductName()+".jobj");

        try{
            if(String.valueOf(cellEditEvent.getNewValue()).equals("NaN")){
                throw new ValidationException("");
            }
            selectedCase.setPrice((double)cellEditEvent.getNewValue());
            selectedCase.validate();
            AdminLogic.editFile(file, selectedCase);
            table.refresh();
        } catch (IOException e){
            Alert errorBox = new Alert(Alert.AlertType.ERROR);
            errorBox.setTitle("Something went wrong while editing file");
        } catch (ValidationException e) {
            Alert errorBox = new Alert(Alert.AlertType.ERROR);
            errorBox.setHeaderText("Invalid price");
            errorBox.showAndWait();
            selectedCase.setPrice(originalPrice);
            table.refresh();
        }
    }

    @FXML
    private void editName(TableColumn.CellEditEvent cellEditEvent) {
        String originalName = cellEditEvent.getOldValue().toString();
        Case selectedCase = (Case)cellEditEvent.getRowValue();
        File originalFile = new File("src/main/java/com/sample/DAL/SavedFiles/NewComponents/Cases/"+selectedCase.getProductName()+".jobj");
        try{
            selectedCase.setProductName(cellEditEvent.getNewValue().toString());
            selectedCase.validate();

            //because components are stored with their product names as their file names, we need to take extra care not to
            //save a component to a new file just because their name has been updated (FileOutputStream creates a new file if one is not found).
            // This if-test checks if newFile and originalFile are the same, and if not - updates the old filename before doing any outputstreams.
            File newFile = new File("src/main/java/com/sample/DAL/SavedFiles/NewComponents/Cases/"+selectedCase.getProductName()+".jobj");
            if (newFile.equals(originalFile)){
                AdminLogic.editFile(originalFile, selectedCase);
            } else {
                //difference detected, change the filename before outputstreaming "new" object
                if (AdminLogic.editFileName(originalFile, newFile)){
                    AdminLogic.editFile(newFile, selectedCase);
                }
            }
            table.refresh();
        } catch (IOException e){
            Alert errorBox = new Alert(Alert.AlertType.ERROR);
            errorBox.setTitle("Something went wrong while editing file");
        } catch (ValidationException e) {
            Alert errorBox = new Alert(Alert.AlertType.ERROR);
            errorBox.setHeaderText(e.getLocalizedMessage());
            errorBox.showAndWait();
            selectedCase.setProductName(originalName);
            table.refresh();
        }
    }

    @FXML
    private void editManufacturer(TableColumn.CellEditEvent cellEditEvent) {
        String originalManufacturer = cellEditEvent.getOldValue().toString();
        Case selectedCase = (Case)cellEditEvent.getRowValue();
        File file = new File("src/main/java/com/sample/DAL/SavedFiles/NewComponents/Cases/"+selectedCase.getProductName()+".jobj");
        try{
            selectedCase.setProductionCompany(cellEditEvent.getNewValue().toString());
            selectedCase.validate();
            AdminLogic.editFile(file, selectedCase);
            table.refresh();
        } catch (IOException e){
            Alert errorBox = new Alert(Alert.AlertType.ERROR);
            errorBox.setTitle("Something went wrong while editing file");
        } catch (ValidationException e) {
            Alert errorBox = new Alert(Alert.AlertType.ERROR);
            errorBox.setHeaderText(e.getLocalizedMessage());
            selectedCase.setProductionCompany(originalManufacturer);
            errorBox.showAndWait();
            table.refresh();
        }
    }

    @FXML
    private void editSerialNumber(TableColumn.CellEditEvent cellEditEvent) {
        String originalSerialNumber = cellEditEvent.getOldValue().toString();
        Case selectedCase = (Case)cellEditEvent.getRowValue();
        File file = new File("src/main/java/com/sample/DAL/SavedFiles/NewComponents/Cases/"+selectedCase.getProductName()+".jobj");
        try{
            selectedCase.setSerialNumber(cellEditEvent.getNewValue().toString());
            selectedCase.validate();
            AdminLogic.editFile(file, selectedCase);
            table.refresh();
        } catch (IOException e){
            Alert errorBox = new Alert(Alert.AlertType.ERROR);
            errorBox.setTitle("Something went wrong while editing file");
        } catch (ValidationException e) {
            Alert errorBox = new Alert(Alert.AlertType.ERROR);
            errorBox.setHeaderText(e.getLocalizedMessage());
            errorBox.showAndWait();
            selectedCase.setSerialNumber(originalSerialNumber);
            table.refresh();
        }
    }

    @FXML
    private void editUSBports(TableColumn.CellEditEvent cellEditEvent) {
        String oldUSBPorts = cellEditEvent.getOldValue().toString();
        Case selectedCase = (Case)cellEditEvent.getRowValue();
        File file = new File("src/main/java/com/sample/DAL/SavedFiles/NewComponents/Cases/"+selectedCase.getProductName()+".jobj");
        try{
            selectedCase.setNumberOfUSBPorts(cellEditEvent.getNewValue().toString());
            selectedCase.validate();
            AdminLogic.editFile(file, selectedCase);
            table.refresh();
        } catch (IOException e){
            Alert errorBox = new Alert(Alert.AlertType.ERROR);
            errorBox.setTitle("Something went wrong while editing file");
        } catch (ValidationException e) {
            Alert errorBox = new Alert(Alert.AlertType.ERROR);
            errorBox.setHeaderText(e.getLocalizedMessage());
            selectedCase.setNumberOfUSBPorts(oldUSBPorts);
            errorBox.showAndWait();
            table.refresh();
        }
    }

    @FXML
    private void editAudioJacks(TableColumn.CellEditEvent cellEditEvent) {
        String originalAudioJacks = cellEditEvent.getOldValue().toString();
        Case selectedCase = (Case)cellEditEvent.getRowValue();
        File file = new File("src/main/java/com/sample/DAL/SavedFiles/NewComponents/Cases/"+selectedCase.getProductName()+".jobj");
        try{
            selectedCase.setHDAudioJacks(cellEditEvent.getNewValue().toString());
            selectedCase.validate();
            AdminLogic.editFile(file, selectedCase);
            table.refresh();
        } catch (IOException e){
            Alert errorBox = new Alert(Alert.AlertType.ERROR);
            errorBox.setTitle("Something went wrong while editing file");
        } catch (ValidationException e) {
            Alert errorBox = new Alert(Alert.AlertType.ERROR);
            errorBox.setHeaderText(e.getLocalizedMessage());
            selectedCase.setHDAudioJacks(originalAudioJacks);
            errorBox.showAndWait();
            table.refresh();
        }
    }

    @FXML
    private void editWidth(TableColumn.CellEditEvent cellEditEvent) {
        String originalWidth = cellEditEvent.getOldValue().toString();
        Case selectedCase = (Case)cellEditEvent.getRowValue();
        File file = new File("src/main/java/com/sample/DAL/SavedFiles/NewComponents/Cases/"+selectedCase.getProductName()+".jobj");
        try{
            selectedCase.setWidthCM(cellEditEvent.getNewValue().toString());
            selectedCase.validate();
            AdminLogic.editFile(file, selectedCase);
            table.refresh();
        } catch (IOException e){
            Alert errorBox = new Alert(Alert.AlertType.ERROR);
            errorBox.setTitle("Something went wrong while editing file");
        } catch (ValidationException e) {
            Alert errorBox = new Alert(Alert.AlertType.ERROR);
            errorBox.setHeaderText(e.getLocalizedMessage());
            selectedCase.setWidthCM(originalWidth);
            errorBox.showAndWait();
            table.refresh();
        }
    }

    @FXML
    private void editHeight(TableColumn.CellEditEvent cellEditEvent) {
        String originalHeight = cellEditEvent.getOldValue().toString();
        Case selectedCase = (Case)cellEditEvent.getRowValue();
        File file = new File("src/main/java/com/sample/DAL/SavedFiles/NewComponents/Cases/"+selectedCase.getProductName()+".jobj");
        try{
            selectedCase.setHeightCM(cellEditEvent.getNewValue().toString());
            selectedCase.validate();
            AdminLogic.editFile(file, selectedCase);
            table.refresh();
        } catch (IOException e){
            Alert errorBox = new Alert(Alert.AlertType.ERROR);
            errorBox.setTitle("Something went wrong while editing file");
        } catch (ValidationException e) {
            Alert errorBox = new Alert(Alert.AlertType.ERROR);
            errorBox.setHeaderText(e.getLocalizedMessage());
            selectedCase.setHeightCM(originalHeight);
            errorBox.showAndWait();
            table.refresh();
        }
    }

    @FXML
    private void editDepth(TableColumn.CellEditEvent cellEditEvent) {
        String originalDepth = cellEditEvent.getOldValue().toString();
        Case selectedCase = (Case)cellEditEvent.getRowValue();
        File file = new File("src/main/java/com/sample/DAL/SavedFiles/NewComponents/Cases/"+selectedCase.getProductName()+".jobj");
        try{
            selectedCase.setDepthCM(cellEditEvent.getNewValue().toString());
            selectedCase.validate();
            AdminLogic.editFile(file, selectedCase);
            table.refresh();
        } catch (IOException e){
            Alert errorBox = new Alert(Alert.AlertType.ERROR);
            errorBox.setTitle("Something went wrong while editing file");
        } catch (ValidationException e) {
            Alert errorBox = new Alert(Alert.AlertType.ERROR);
            errorBox.setHeaderText(e.getLocalizedMessage());
            selectedCase.setDescription(originalDepth);
            errorBox.showAndWait();
            table.refresh();
        }
    }
}
