package com.sample.controllers.addedComponentControllers;

import com.sample.App;
import com.sample.BLL.AdminLogic;
import com.sample.BLL.ComponentDeleter;
import com.sample.BLL.InputValidation.ValidationException;
import com.sample.DAL.OpenFile.Subtypes.OpenAddedComponents;
import com.sample.DAL.OpenFile.Subtypes.OpenMotherBoards;
import com.sample.Models.ComputerComponents.Motherboard;
import com.sample.Models.ComputerComponents.Motherboard;
import com.sample.Models.ComputerComponents.Motherboard;
import javafx.concurrent.WorkerStateEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.util.converter.DoubleStringConverter;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

public class MotherboardViewController {
    @FXML private TableView<Motherboard> table;
    @FXML private TableColumn<Motherboard, Double> price;
    private OpenAddedComponents opener = new OpenMotherBoards();
    private OpenAddedComponents deleter = new OpenMotherBoards();

    //this function sets the tableview as editable, sets a cellfactory for price, as we need to handle exceptions if
    //somebody writes something that won't parse from text to double.
    //finally it starts the thread responsible for loading all added components to the view's tableview.
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

    //this function uses loads added components in their own thread.
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

    //sets the table's placeholder text to an error message if something failed
    private void handleError(WorkerStateEvent workerStateEvent) {
        Label errorPlaceholder = new Label("Could not retrieve saved motherboards");
        table.placeholderProperty().setValue(errorPlaceholder);
    }

    //loads every added component to the tableview.
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

    //this function gets the selected item, asks for confirmation and sends the component to be deleted, after it was deleted successfully the tableview
    //is updated to reflect this deletion. if one presses the delete button without selecting a component, an error pop-up will appear.
    @FXML
    private void deleteMotherboard(){
        try{
            final File folder = new File("src/main/java/com/sample/DAL/SavedFiles/NewComponents/");
            Motherboard toBeDeleted = table.getSelectionModel().getSelectedItem();
            Alert deleteConfirmation = new Alert(Alert.AlertType.CONFIRMATION);
            deleteConfirmation.setTitle("Confirm deletion");
            deleteConfirmation.setHeaderText("Are you sure you want to delete "+toBeDeleted.getProductName()+"?");
            deleteConfirmation.setContentText("This will permanently remove the computer component");
            Optional<ButtonType> result = deleteConfirmation.showAndWait();
            try {
                if (result.get() == ButtonType.OK){
                    ComponentDeleter.deleteComponent(folder, toBeDeleted);
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
        openFilesThread.start();
    }

    //resets the tableview so it only contains the components that are saved. Without this the deleted item would still appear.
    private void handleDeleteSucceed(WorkerStateEvent workerStateEvent) {
        try{
            table.getItems().setAll((List<Motherboard>) deleter.getValue());
            if(table.getItems().toArray().length == 0){
                table.placeholderProperty().setValue(new Label("No Motherboards saved"));
            }
        } catch (NullPointerException e){
            table.placeholderProperty().setValue(new Label("Something went wrong"));
        }
    }

    private void handleDeleteError(WorkerStateEvent workerStateEvent) {
        Alert errorBox = new Alert(Alert.AlertType.ERROR);
        errorBox.setTitle("Something went wrong while deleting");
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
        Motherboard selectedMotherboard = (Motherboard)cellEditEvent.getRowValue();
        File file = new File("src/main/java/com/sample/DAL/SavedFiles/NewComponents/Motherboards/"+selectedMotherboard.getProductName()+".jobj");
        try{
            selectedMotherboard.setDescription(cellEditEvent.getNewValue().toString());
            selectedMotherboard.validate();
            AdminLogic.editFile(file, selectedMotherboard);
            table.refresh();
        } catch (IOException e){
            Alert errorBox = new Alert(Alert.AlertType.ERROR);
            errorBox.setTitle("Something went wrong while editing file");
        } catch (ValidationException e) {
            Alert errorBox = new Alert(Alert.AlertType.ERROR);
            errorBox.setHeaderText(e.getLocalizedMessage());
            errorBox.showAndWait();
            selectedMotherboard.setDescription(originalDescription);
            table.refresh();
        }
    }

    @FXML
    private void editPrice(TableColumn.CellEditEvent cellEditEvent){
        double originalPrice = (double)cellEditEvent.getOldValue();
        Motherboard selectedMotherboard = (Motherboard)cellEditEvent.getRowValue();
        File file = new File("src/main/java/com/sample/DAL/SavedFiles/NewComponents/Motherboards/"+selectedMotherboard.getProductName()+".jobj");

        try{
            if(String.valueOf(cellEditEvent.getNewValue()).equals("NaN")){
                throw new ValidationException("");
            }
            selectedMotherboard.setPrice((double)cellEditEvent.getNewValue());
            selectedMotherboard.validate();
            AdminLogic.editFile(file, selectedMotherboard);
            table.refresh();
        } catch (IOException e){
            Alert errorBox = new Alert(Alert.AlertType.ERROR);
            errorBox.setTitle("Something went wrong while editing file");
        } catch (ValidationException e) {
            Alert errorBox = new Alert(Alert.AlertType.ERROR);
            errorBox.setHeaderText("Invalid price");
            errorBox.showAndWait();
            selectedMotherboard.setPrice(originalPrice);
            table.refresh();
        }
    }

    @FXML
    private void editName(TableColumn.CellEditEvent cellEditEvent) {
        String originalName = cellEditEvent.getOldValue().toString();
        Motherboard selectedMotherboard = (Motherboard)cellEditEvent.getRowValue();
        File originalFile = new File("src/main/java/com/sample/DAL/SavedFiles/NewComponents/Motherboards/"+selectedMotherboard.getProductName()+".jobj");
        try{
            selectedMotherboard.setProductName(cellEditEvent.getNewValue().toString());
            selectedMotherboard.validate();

            //because components are stored with their product names as their file names, we need to take extra care not to
            //save a component to a new file just because their name has been updated (FileOutputStream creates a new file if one is not found).
            // This if-test checks if newFile and originalFile are the same, and if not - updates the old filename before doing any outputstreams.
            File newFile = new File("src/main/java/com/sample/DAL/SavedFiles/NewComponents/Motherboards/"+selectedMotherboard.getProductName()+".jobj");
            if (newFile.equals(originalFile)){
                AdminLogic.editFile(originalFile, selectedMotherboard);
            } else {
                //difference detected, change the filename before outputstreaming "new" object
                if (AdminLogic.editFileName(originalFile, newFile)){
                    AdminLogic.editFile(newFile, selectedMotherboard);
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
            selectedMotherboard.setProductName(originalName);
            table.refresh();
        }
    }

    @FXML
    private void editManufacturer(TableColumn.CellEditEvent cellEditEvent) {
        String originalManufacturer = cellEditEvent.getOldValue().toString();
        Motherboard selectedMotherboard = (Motherboard)cellEditEvent.getRowValue();
        File file = new File("src/main/java/com/sample/DAL/SavedFiles/NewComponents/Motherboards/"+selectedMotherboard.getProductName()+".jobj");
        try{
            selectedMotherboard.setProductionCompany(cellEditEvent.getNewValue().toString());
            selectedMotherboard.validate();
            AdminLogic.editFile(file, selectedMotherboard);
            table.refresh();
        } catch (IOException e){
            Alert errorBox = new Alert(Alert.AlertType.ERROR);
            errorBox.setTitle("Something went wrong while editing file");
        } catch (ValidationException e) {
            Alert errorBox = new Alert(Alert.AlertType.ERROR);
            errorBox.setHeaderText(e.getLocalizedMessage());
            selectedMotherboard.setProductionCompany(originalManufacturer);
            errorBox.showAndWait();
            table.refresh();
        }
    }

    @FXML
    private void editSerialNumber(TableColumn.CellEditEvent cellEditEvent) {
        String originalSerialNumber = cellEditEvent.getOldValue().toString();
        Motherboard selectedMotherboard = (Motherboard)cellEditEvent.getRowValue();
        File file = new File("src/main/java/com/sample/DAL/SavedFiles/NewComponents/Motherboards/"+selectedMotherboard.getProductName()+".jobj");
        try{
            selectedMotherboard.setSerialNumber(cellEditEvent.getNewValue().toString());
            selectedMotherboard.validate();
            AdminLogic.editFile(file, selectedMotherboard);
            table.refresh();
        } catch (IOException e){
            Alert errorBox = new Alert(Alert.AlertType.ERROR);
            errorBox.setTitle("Something went wrong while editing file");
        } catch (ValidationException e) {
            Alert errorBox = new Alert(Alert.AlertType.ERROR);
            errorBox.setHeaderText(e.getLocalizedMessage());
            errorBox.showAndWait();
            selectedMotherboard.setSerialNumber(originalSerialNumber);
            table.refresh();
        }
    }

    @FXML
    private void editCPUSupport(TableColumn.CellEditEvent cellEditEvent) {
        String originalCPUSupport = cellEditEvent.getOldValue().toString();
        Motherboard selectedMotherboard = (Motherboard)cellEditEvent.getRowValue();
        File file = new File("src/main/java/com/sample/DAL/SavedFiles/NewComponents/Motherboards/"+selectedMotherboard.getProductName()+".jobj");
        try{
            selectedMotherboard.setCPUSupport(cellEditEvent.getNewValue().toString());
            selectedMotherboard.validate();
            AdminLogic.editFile(file, selectedMotherboard);
            table.refresh();
        } catch (IOException e){
            Alert errorBox = new Alert(Alert.AlertType.ERROR);
            errorBox.setTitle("Something went wrong while editing file");
        } catch (ValidationException e) {
            Alert errorBox = new Alert(Alert.AlertType.ERROR);
            errorBox.setHeaderText(e.getLocalizedMessage());
            errorBox.showAndWait();
            selectedMotherboard.setCPUSupport(originalCPUSupport);
            table.refresh();
        }
    }

    @FXML
    private void editMemoryType(TableColumn.CellEditEvent cellEditEvent) {
        String originalMemoryType = cellEditEvent.getOldValue().toString();
        Motherboard selectedMotherboard = (Motherboard)cellEditEvent.getRowValue();
        File file = new File("src/main/java/com/sample/DAL/SavedFiles/NewComponents/Motherboards/"+selectedMotherboard.getProductName()+".jobj");
        try{
            selectedMotherboard.setMemoryType(cellEditEvent.getNewValue().toString());
            selectedMotherboard.validate();
            AdminLogic.editFile(file, selectedMotherboard);
            table.refresh();
        } catch (IOException e){
            Alert errorBox = new Alert(Alert.AlertType.ERROR);
            errorBox.setTitle("Something went wrong while editing file");
        } catch (ValidationException e) {
            Alert errorBox = new Alert(Alert.AlertType.ERROR);
            errorBox.setHeaderText(e.getLocalizedMessage());
            errorBox.showAndWait();
            selectedMotherboard.setMemoryType(originalMemoryType);
            table.refresh();
        }
    }

    @FXML
    private void editMemoryDIMMs(TableColumn.CellEditEvent cellEditEvent) {
        String originalDIMMs = cellEditEvent.getOldValue().toString();
        Motherboard selectedMotherboard = (Motherboard)cellEditEvent.getRowValue();
        File file = new File("src/main/java/com/sample/DAL/SavedFiles/NewComponents/Motherboards/"+selectedMotherboard.getProductName()+".jobj");
        try{
            selectedMotherboard.setMemoryDIMMs(cellEditEvent.getNewValue().toString());
            selectedMotherboard.validate();
            AdminLogic.editFile(file, selectedMotherboard);
            table.refresh();
        } catch (IOException e){
            Alert errorBox = new Alert(Alert.AlertType.ERROR);
            errorBox.setTitle("Something went wrong while editing file");
        } catch (ValidationException e) {
            Alert errorBox = new Alert(Alert.AlertType.ERROR);
            errorBox.setHeaderText(e.getLocalizedMessage());
            errorBox.showAndWait();
            selectedMotherboard.setMemoryDIMMs(originalDIMMs);
            table.refresh();
        }
    }

    @FXML
    private void editGraphicsInterface(TableColumn.CellEditEvent cellEditEvent) {
        String originalGraphicsInterface = cellEditEvent.getOldValue().toString();
        Motherboard selectedMotherboard = (Motherboard)cellEditEvent.getRowValue();
        File file = new File("src/main/java/com/sample/DAL/SavedFiles/NewComponents/Motherboards/"+selectedMotherboard.getProductName()+".jobj");
        try{
            selectedMotherboard.setGraphicInterface(cellEditEvent.getNewValue().toString());
            selectedMotherboard.validate();
            AdminLogic.editFile(file, selectedMotherboard);
            table.refresh();
        } catch (IOException e){
            Alert errorBox = new Alert(Alert.AlertType.ERROR);
            errorBox.setTitle("Something went wrong while editing file");
        } catch (ValidationException e) {
            Alert errorBox = new Alert(Alert.AlertType.ERROR);
            errorBox.setHeaderText(e.getLocalizedMessage());
            errorBox.showAndWait();
            selectedMotherboard.setGraphicInterface(originalGraphicsInterface);
            table.refresh();
        }
    }

    @FXML
    private void editExpansionSlots(TableColumn.CellEditEvent cellEditEvent) {
        String originalExpansionSlots = cellEditEvent.getOldValue().toString();
        Motherboard selectedMotherboard = (Motherboard)cellEditEvent.getRowValue();
        File file = new File("src/main/java/com/sample/DAL/SavedFiles/NewComponents/Motherboards/"+selectedMotherboard.getProductName()+".jobj");
        try{
            selectedMotherboard.setExpansionSlots(cellEditEvent.getNewValue().toString());
            selectedMotherboard.validate();
            AdminLogic.editFile(file, selectedMotherboard);
            table.refresh();
        } catch (IOException e){
            Alert errorBox = new Alert(Alert.AlertType.ERROR);
            errorBox.setTitle("Something went wrong while editing file");
        } catch (ValidationException e) {
            Alert errorBox = new Alert(Alert.AlertType.ERROR);
            errorBox.setHeaderText(e.getLocalizedMessage());
            errorBox.showAndWait();
            selectedMotherboard.setExpansionSlots(originalExpansionSlots);
            table.refresh();
        }
    }

    @FXML
    private void editM2slot(TableColumn.CellEditEvent cellEditEvent) {
        String originalM2slot = cellEditEvent.getOldValue().toString();
        Motherboard selectedMotherboard = (Motherboard)cellEditEvent.getRowValue();
        File file = new File("src/main/java/com/sample/DAL/SavedFiles/NewComponents/Motherboards/"+selectedMotherboard.getProductName()+".jobj");
        try{
            selectedMotherboard.setM2Slot(cellEditEvent.getNewValue().toString());
            selectedMotherboard.validate();
            AdminLogic.editFile(file, selectedMotherboard);
            table.refresh();
        } catch (IOException e){
            Alert errorBox = new Alert(Alert.AlertType.ERROR);
            errorBox.setTitle("Something went wrong while editing file");
        } catch (ValidationException e) {
            Alert errorBox = new Alert(Alert.AlertType.ERROR);
            errorBox.setHeaderText(e.getLocalizedMessage());
            errorBox.showAndWait();
            selectedMotherboard.setM2Slot(originalM2slot);
            table.refresh();
        }
    }

    @FXML
    private void editDisplayInterface(TableColumn.CellEditEvent cellEditEvent) {
        String originalDisplayInterface = cellEditEvent.getOldValue().toString();
        Motherboard selectedMotherboard = (Motherboard)cellEditEvent.getRowValue();
        File file = new File("src/main/java/com/sample/DAL/SavedFiles/NewComponents/Motherboards/"+selectedMotherboard.getProductName()+".jobj");
        try{
            selectedMotherboard.setDisplayInterface(cellEditEvent.getNewValue().toString());
            selectedMotherboard.validate();
            AdminLogic.editFile(file, selectedMotherboard);
            table.refresh();
        } catch (IOException e){
            Alert errorBox = new Alert(Alert.AlertType.ERROR);
            errorBox.setTitle("Something went wrong while editing file");
        } catch (ValidationException e) {
            Alert errorBox = new Alert(Alert.AlertType.ERROR);
            errorBox.setHeaderText(e.getLocalizedMessage());
            errorBox.showAndWait();
            selectedMotherboard.setDisplayInterface(originalDisplayInterface);
            table.refresh();
        }
    }

    @FXML
    private void editWIFI(TableColumn.CellEditEvent cellEditEvent) {
        String originalWIFI = cellEditEvent.getOldValue().toString();
        Motherboard selectedMotherboard = (Motherboard)cellEditEvent.getRowValue();
        File file = new File("src/main/java/com/sample/DAL/SavedFiles/NewComponents/Motherboards/"+selectedMotherboard.getProductName()+".jobj");
        try{
            selectedMotherboard.setWIFI(cellEditEvent.getNewValue().toString());
            selectedMotherboard.validate();
            AdminLogic.editFile(file, selectedMotherboard);
            table.refresh();
        } catch (IOException e){
            Alert errorBox = new Alert(Alert.AlertType.ERROR);
            errorBox.setTitle("Something went wrong while editing file");
        } catch (ValidationException e) {
            Alert errorBox = new Alert(Alert.AlertType.ERROR);
            errorBox.setHeaderText(e.getLocalizedMessage());
            errorBox.showAndWait();
            selectedMotherboard.setWIFI(originalWIFI);
            table.refresh();
        }
    }

    @FXML
    private void editAudio(TableColumn.CellEditEvent cellEditEvent) {
        String originalAudio = cellEditEvent.getOldValue().toString();
        Motherboard selectedMotherboard = (Motherboard)cellEditEvent.getRowValue();
        File file = new File("src/main/java/com/sample/DAL/SavedFiles/NewComponents/Motherboards/"+selectedMotherboard.getProductName()+".jobj");
        try{
            selectedMotherboard.setAudio(cellEditEvent.getNewValue().toString());
            selectedMotherboard.validate();
            AdminLogic.editFile(file, selectedMotherboard);
            table.refresh();
        } catch (IOException e){
            Alert errorBox = new Alert(Alert.AlertType.ERROR);
            errorBox.setTitle("Something went wrong while editing file");
        } catch (ValidationException e) {
            Alert errorBox = new Alert(Alert.AlertType.ERROR);
            errorBox.setHeaderText(e.getLocalizedMessage());
            errorBox.showAndWait();
            selectedMotherboard.setAudio(originalAudio);
            table.refresh();
        }
    }

    @FXML
    private void editFormFactor(TableColumn.CellEditEvent cellEditEvent) {
        String originalFormFactor = cellEditEvent.getOldValue().toString();
        Motherboard selectedMotherboard = (Motherboard)cellEditEvent.getRowValue();
        File file = new File("src/main/java/com/sample/DAL/SavedFiles/NewComponents/Motherboards/"+selectedMotherboard.getProductName()+".jobj");
        try{
            selectedMotherboard.setFormFactor(cellEditEvent.getNewValue().toString());
            selectedMotherboard.validate();
            AdminLogic.editFile(file, selectedMotherboard);
            table.refresh();
        } catch (IOException e){
            Alert errorBox = new Alert(Alert.AlertType.ERROR);
            errorBox.setTitle("Something went wrong while editing file");
        } catch (ValidationException e) {
            Alert errorBox = new Alert(Alert.AlertType.ERROR);
            errorBox.setHeaderText(e.getLocalizedMessage());
            errorBox.showAndWait();
            selectedMotherboard.setFormFactor(originalFormFactor);
            table.refresh();
        }
    }
}
