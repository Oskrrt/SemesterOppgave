package com.sample.controllers.addedComponentControllers;

import com.sample.App;
import com.sample.BLL.AdminLogic;
import com.sample.BLL.ComponentDeleter;
import com.sample.DAL.OpenFile.Subtypes.OpenCases;
import com.sample.Exceptions.ValidationException;
import com.sample.DAL.OpenFile.Subtypes.OpenAddedComponents;
import com.sample.DAL.OpenFile.Subtypes.OpenCoolingSystems;
import com.sample.Models.ComputerComponents.Case;
import com.sample.Models.ComputerComponents.CoolingSystem;
import javafx.concurrent.WorkerStateEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.AnchorPane;
import javafx.util.converter.DoubleStringConverter;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.*;
import java.util.stream.Collectors;


public class CoolingSystemViewController implements Initializable {
    @FXML private AnchorPane componentPane;
    @FXML private TableView<CoolingSystem> table;
    @FXML private TableColumn<CoolingSystem, Double> price;
    private OpenAddedComponents opener = new OpenCoolingSystems(true);
    private OpenAddedComponents deleter = new OpenCoolingSystems(true);
    @FXML private ChoiceBox<String> filter;
    @FXML private TextField querySearch;
    //this function sets the tableview for added components as editable, sets a cellfactory for price, as we need to handle exceptions if
    //somebody writes something that won't parse from text to double.
    //finally it starts the thread responsible for loading all added components to the view's tableview.
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        table.setEditable(true);
        filter.getItems().add("Name");
        filter.getItems().add("Serial number");
        filter.getSelectionModel().selectFirst();
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

    //this function loads added components in their own thread.
    private void startThread(){
        Thread openCoolingComponentFilesThread = new Thread(opener);
        opener.setOnSucceeded(this::handleSucceed);
        opener.setOnFailed(this::handleError);
        openCoolingComponentFilesThread.setDaemon(true);
        toggleGUIDisable();
        openCoolingComponentFilesThread.start();


    }

    private void toggleGUIDisable() {
        componentPane.setDisable(!componentPane.isDisable());
    }

    //sets the table's placeholder text to an error message if something failed (most likely invalid data injected into a file)
    private void handleError(WorkerStateEvent workerStateEvent) {
        var exception = workerStateEvent.getSource().getException().getMessage();
        Label errorPlaceholder = new Label("Could not retrieve saved cooling systems" + exception);
        table.placeholderProperty().setValue(errorPlaceholder);
        toggleGUIDisable();
    }

    //loads every added component to the tableview.
    private void handleSucceed(WorkerStateEvent workerStateEvent) {
        table.getItems().setAll((List<CoolingSystem>) opener.getValue());
        toggleGUIDisable();
    }

    //back-button that loads the view where the user selects what added components to see
    @FXML
    private void viewSwapper(){
        try {
            App.changeView("/fxml/viewAddedComponents.fxml", 1200, 900);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //this function gets the selected item, asks for confirmation and sends the component to be deleted, after it was deleted successfully the tableview
    //is updated to reflect this deletion. if one presses the delete button without selecting a component, an error pop-up will appear.
    @FXML
    private void deleteCoolingSystem(){
        try{
            final File folder = new File("src/main/java/com/sample/DAL/SavedFiles/NewComponents/");
            CoolingSystem CoolingSystemToBeDeleted = table.getSelectionModel().getSelectedItem();
            Alert deleteConfirmation = new Alert(Alert.AlertType.CONFIRMATION);
            deleteConfirmation.setTitle("Confirm deletion");
            deleteConfirmation.setHeaderText("Are you sure you want to delete "+CoolingSystemToBeDeleted.getProductName()+"?");
            deleteConfirmation.setContentText("This will permanently remove the computer component");
            Optional<ButtonType> result = deleteConfirmation.showAndWait();
            try {
                if (result.get() == ButtonType.OK){
                    ComponentDeleter.deleteComponent(folder, CoolingSystemToBeDeleted);
                    startDeleteThread();
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

    //this seperate thread does the same task as startThread(). The reason we needed a seperate function to update the tableview after deletion
    // is because thread.start() will only run once. If we tried to run the same thread again, it would not actually perform its task.
    private void startDeleteThread(){
        Thread openFilesThread = new Thread(deleter);
        deleter.setOnSucceeded(this::handleDeleteSucceed);
        deleter.setOnFailed(this::handleDeleteError);
        openFilesThread.setDaemon(true);
        toggleGUIDisable();
        openFilesThread.start();
    }

    //resets the tableview so it only contains the components that are saved. Without this the deleted item would still appear.
    private void handleDeleteSucceed(WorkerStateEvent workerStateEvent) {
        try{
            table.getItems().setAll((List<CoolingSystem>) deleter.getValue());
            if(table.getItems().toArray().length == 0){
                table.placeholderProperty().setValue(new Label("No cooling systems saved"));
            }
        } catch (NullPointerException e){
            table.placeholderProperty().setValue(new Label("Something went wrong"));
        }
        toggleGUIDisable();
    }

    private void handleDeleteError(WorkerStateEvent workerStateEvent) {
        Alert errorBox = new Alert(Alert.AlertType.ERROR);
        errorBox.setTitle("Something went wrong while deleting");
        errorBox.showAndWait();
        toggleGUIDisable();
    }

    private void search(String query) {
        List<CoolingSystem> newList;
        OpenAddedComponents searcher = new OpenCoolingSystems(false);
        try{
            List<CoolingSystem> listToSearch = (List<CoolingSystem>) searcher.perform();
            table.getItems().clear();
            switch (filter.getValue()){
                case "Name":
                    newList = listToSearch.stream().filter(c -> c.getProductName().toLowerCase().contains(query.toLowerCase())).collect(Collectors.toList());
                    table.getItems().addAll(newList);
                    break;
                case "Serial number":
                    newList = listToSearch.stream().filter(c -> c.getSerialNumber().toLowerCase().contains(query.toLowerCase())).collect(Collectors.toList());
                    table.getItems().addAll(newList);
            }
        } catch (IOException | ValidationException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void startSearch(){
        querySearch.textProperty().addListener((observable, oldText, newText) -> {
            search(newText);
        });
    }

    //because of Java-FX's quirks, we needed a single function for every single tablecolumn that could be edited. All these functions
    //perform the same task, which is:
    // - get a hold of the original value of the edited tablecell
    //   (if the computercomponent did not validate, the tablecell
    //   would still show the wrong value after committing the edit).
    //   This is used for rolling it back to the original value if a validationException is thrown.
    //
    // - create a path using the selected components product name (filenames are the product names)
    //
    // - use the model's set-methods to change the selected object
    //
    // - validate the new object
    //
    // - if no ValidationException is thrown, edit the existing file to reflect the new changes (This is a bit different in editName(). See the comment there.)
    //
    // - since the tableview's edit methods are dynamic, we dont need to update it the same way our delete solution does, table.refresh() is enough.
    @FXML
    private void editDescription(TableColumn.CellEditEvent cellEditEvent){
        String originalDescription = cellEditEvent.getOldValue().toString();
        CoolingSystem selectedCoolingSystem = (CoolingSystem)cellEditEvent.getRowValue();
        File file = new File("src/main/java/com/sample/DAL/SavedFiles/NewComponents/CoolingSystems/"+selectedCoolingSystem.getProductName()+".jobj");
        try{
            selectedCoolingSystem.setDescription(cellEditEvent.getNewValue().toString());
            selectedCoolingSystem.validate();
            AdminLogic.editFile(file, selectedCoolingSystem);
            table.refresh();
        } catch (IOException e){
            Alert errorBox = new Alert(Alert.AlertType.ERROR);
            errorBox.setTitle("Something went wrong while editing file");
        } catch (ValidationException e) {
            Alert errorBox = new Alert(Alert.AlertType.ERROR);
            errorBox.setHeaderText(e.getLocalizedMessage());
            errorBox.showAndWait();
            selectedCoolingSystem.setDescription(originalDescription);
            table.refresh();
        }
    }

    @FXML
    private void editPrice(TableColumn.CellEditEvent cellEditEvent){
        double originalPrice = (double)cellEditEvent.getOldValue();
        CoolingSystem selectedCoolingSystem = (CoolingSystem)cellEditEvent.getRowValue();
        File file = new File("src/main/java/com/sample/DAL/SavedFiles/NewComponents/CoolingSystems/"+selectedCoolingSystem.getProductName()+".jobj");

        try{
            if(String.valueOf(cellEditEvent.getNewValue()).equals("NaN")){
                throw new ValidationException("");
            }
            selectedCoolingSystem.setPrice((double)cellEditEvent.getNewValue());
            selectedCoolingSystem.validate();
            AdminLogic.editFile(file, selectedCoolingSystem);
            table.refresh();
        } catch (IOException e){
            Alert errorBox = new Alert(Alert.AlertType.ERROR);
            errorBox.setTitle("Something went wrong while editing file");
        } catch (ValidationException e) {
            Alert errorBox = new Alert(Alert.AlertType.ERROR);
            errorBox.setHeaderText("Invalid price");
            errorBox.showAndWait();
            selectedCoolingSystem.setPrice(originalPrice);
            table.refresh();
        }
    }


    //because components are stored with their product names as their file names, we need to take extra care not to
    //save a component to a new file just because their name has been updated (FileOutputStream creates a new file if one is not found).
    // This if-test checks if newFile and originalFile are the same, and if not - updates the old filename before doing any outputstreams.
    @FXML
    private void editName(TableColumn.CellEditEvent cellEditEvent) {
        String originalName = cellEditEvent.getOldValue().toString();
        CoolingSystem selectedCoolingSystem = (CoolingSystem)cellEditEvent.getRowValue();
        File originalFile = new File("src/main/java/com/sample/DAL/SavedFiles/NewComponents/CoolingSystems/"+selectedCoolingSystem.getProductName()+".jobj");
        try{
            selectedCoolingSystem.setProductName(cellEditEvent.getNewValue().toString());
            selectedCoolingSystem.validate();

            File newFile = new File("src/main/java/com/sample/DAL/SavedFiles/NewComponents/CoolingSystems/"+selectedCoolingSystem.getProductName()+".jobj");
            if (newFile.equals(originalFile)){
                AdminLogic.editFile(originalFile, selectedCoolingSystem);
            } else {
                //difference detected, change the filename before outputstreaming "new" object
                if (AdminLogic.editFileName(originalFile, newFile)){
                    AdminLogic.editFile(newFile, selectedCoolingSystem);
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
            selectedCoolingSystem.setProductName(originalName);
            table.refresh();
        }
    }

    @FXML
    private void editManufacturer(TableColumn.CellEditEvent cellEditEvent) {
        String originalManufacturer = cellEditEvent.getOldValue().toString();
        CoolingSystem selectedCoolingSystem = (CoolingSystem)cellEditEvent.getRowValue();
        File file = new File("src/main/java/com/sample/DAL/SavedFiles/NewComponents/CoolingSystems/"+selectedCoolingSystem.getProductName()+".jobj");
        try{
            selectedCoolingSystem.setProductionCompany(cellEditEvent.getNewValue().toString());
            selectedCoolingSystem.validate();
            AdminLogic.editFile(file, selectedCoolingSystem);
            table.refresh();
        } catch (IOException e){
            Alert errorBox = new Alert(Alert.AlertType.ERROR);
            errorBox.setTitle("Something went wrong while editing file");
        } catch (ValidationException e) {
            Alert errorBox = new Alert(Alert.AlertType.ERROR);
            errorBox.setHeaderText(e.getLocalizedMessage());
            selectedCoolingSystem.setProductionCompany(originalManufacturer);
            errorBox.showAndWait();
            table.refresh();
        }
    }

    @FXML
    private void editSerialNumber(TableColumn.CellEditEvent cellEditEvent) {
        String originalSerialNumber = cellEditEvent.getOldValue().toString();
        CoolingSystem selectedCoolingSystem = (CoolingSystem)cellEditEvent.getRowValue();
        File file = new File("src/main/java/com/sample/DAL/SavedFiles/NewComponents/CoolingSystems/"+selectedCoolingSystem.getProductName()+".jobj");
        try{
            selectedCoolingSystem.setSerialNumber(cellEditEvent.getNewValue().toString());
            selectedCoolingSystem.validate();
            AdminLogic.editFile(file, selectedCoolingSystem);
            table.refresh();
        } catch (IOException e){
            Alert errorBox = new Alert(Alert.AlertType.ERROR);
            errorBox.setTitle("Something went wrong while editing file");
        } catch (ValidationException e) {
            Alert errorBox = new Alert(Alert.AlertType.ERROR);
            errorBox.setHeaderText(e.getLocalizedMessage());
            errorBox.showAndWait();
            selectedCoolingSystem.setSerialNumber(originalSerialNumber);
            table.refresh();
        }
    }


    @FXML
    private void editWidth(TableColumn.CellEditEvent cellEditEvent) {
        String originalWidth = cellEditEvent.getOldValue().toString();
        CoolingSystem selectedCoolingSystem = (CoolingSystem)cellEditEvent.getRowValue();
        File file = new File("src/main/java/com/sample/DAL/SavedFiles/NewComponents/CoolingSystems/"+selectedCoolingSystem.getProductName()+".jobj");
        try{
            selectedCoolingSystem.setWidthCM(cellEditEvent.getNewValue().toString());
            selectedCoolingSystem.validate();
            AdminLogic.editFile(file, selectedCoolingSystem);
            table.refresh();
        } catch (IOException e){
            Alert errorBox = new Alert(Alert.AlertType.ERROR);
            errorBox.setTitle("Something went wrong while editing file");
        } catch (ValidationException e) {
            Alert errorBox = new Alert(Alert.AlertType.ERROR);
            errorBox.setHeaderText(e.getLocalizedMessage());
            selectedCoolingSystem.setWidthCM(originalWidth);
            errorBox.showAndWait();
            table.refresh();
        }
    }

    @FXML
    private void editHeight(TableColumn.CellEditEvent cellEditEvent) {
        String originalHeight = cellEditEvent.getOldValue().toString();
        CoolingSystem selectedCoolingSystem = (CoolingSystem)cellEditEvent.getRowValue();
        File file = new File("src/main/java/com/sample/DAL/SavedFiles/NewComponents/CoolingSystems/"+selectedCoolingSystem.getProductName()+".jobj");
        try{
            selectedCoolingSystem.setHeightCM(cellEditEvent.getNewValue().toString());
            selectedCoolingSystem.validate();
            AdminLogic.editFile(file, selectedCoolingSystem);
            table.refresh();
        } catch (IOException e){
            Alert errorBox = new Alert(Alert.AlertType.ERROR);
            errorBox.setTitle("Something went wrong while editing file");
        } catch (ValidationException e) {
            Alert errorBox = new Alert(Alert.AlertType.ERROR);
            errorBox.setHeaderText(e.getLocalizedMessage());
            selectedCoolingSystem.setHeightCM(originalHeight);
            errorBox.showAndWait();
            table.refresh();
        }
    }


}
