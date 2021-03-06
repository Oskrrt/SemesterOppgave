package com.sample.BLL;

import com.sample.Exceptions.InvalidFileDataException;
import com.sample.Exceptions.ValidationException;
import com.sample.DAL.OpenFile.FileOpenerJobj;
import com.sample.Models.ComputerComponents.Case;
import com.sample.Models.ComputerComponents.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.io.*;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class ComponentFactory {

    //this beefy function is essantially just one large switch-case statement. It takes in the form data along with a string specifying which
    //specific component has been requested to be made. Because the FXML files have been made so that the order of elements in the List correspond
    //with the order of textfields in the form, we can be confident that, for example, formData.get(5)).getText() will always contain the number
    //of USB-ports written in the form.
    public static ComputerComponent createComponent(List<Node> formData, String type) throws ValidationException {
        double price;
        String description = ((TextArea)formData.get(0)).getText();
        try{
            price = Double.parseDouble(((TextField)formData.get(1)).getText());
        } catch (NumberFormatException e){
            throw new ValidationException("Price must be a number with two decimals: X.XX");
        }
        String productName = ((TextField)formData.get(2)).getText();
        String manufacturer = ((TextField)formData.get(3)).getText();
        String serialNumber = ((TextField)formData.get(4)).getText();
        switch(type){
            case "computerCase":
                return new Case(
                        ((TextField)formData.get(5)).getText(), //Number of USB ports
                        ((TextField)formData.get(6)).getText(), //Number of HD Audio jacks
                        ((TextField)formData.get(7)).getText(), //Width in cm
                        ((TextField)formData.get(8)).getText(), //Height in cm
                        ((TextField)formData.get(9)).getText(), //Depth in cm
                        price,
                        description,
                        productName,
                        manufacturer,
                        serialNumber);
            case "graphicsCard":
                return new GraphicsCard(
                        price,
                        description,
                        productName,
                        manufacturer,
                        serialNumber,
                        ((TextField)formData.get(5)).getText(),  //memory capacity
                        ((TextField)formData.get(6)).getText()); //memory type
            case "coolingSystem":
                return new CoolingSystem(
                        ((TextField)formData.get(5)).getText(), //Width in cm
                        ((TextField)formData.get(6)).getText(), //Height in cm
                        price,
                        description,
                        productName,
                        manufacturer,
                        serialNumber);
            case "CPU":
                return new Processor(
                        price,
                        description,
                        productName,
                        manufacturer,
                        serialNumber,
                        ((TextField)formData.get(5)).getText(),  //Core count
                        ((TextField)formData.get(6)).getText(),  // Thread count
                        ((TextField)formData.get(7)).getText()); // Max frequency (Hz)
            case "keyboard":
                return new Keyboard(
                        price,
                        description,
                        productName,
                        manufacturer,
                        serialNumber,
                        ((TextField)formData.get(5)).getText(),       //Language layout
                        ((RadioButton)formData.get(6)).isSelected()); //Boolean wireless keyboard
            case "monitor":
                return new Monitor(
                        price,
                        description,
                        productName,
                        manufacturer,
                        serialNumber,
                        ((TextField)formData.get(5)).getText(),  //Display type
                        ((TextField)formData.get(6)).getText(),  //Inches
                        ((TextField)formData.get(7)).getText(),  //Resolution
                        ((TextField)formData.get(8)).getText()); //Connector type
            case "motherBoard":
                return new Motherboard(
                        price,
                        description,
                        productName,
                        manufacturer,
                        serialNumber,
                        ((TextField)formData.get(5)).getText(),   //CPU support
                        ((TextField)formData.get(6)).getText(),   //Memory type
                        ((TextField)formData.get(7)).getText(),   //Memory DIMMs
                        ((TextField)formData.get(8)).getText(),   //Graphic interface
                        ((TextField)formData.get(9)).getText(),   //Expansion slots
                        ((TextField)formData.get(10)).getText(),  //M.2 slot
                        ((TextField)formData.get(11)).getText(),  //Display interface
                        ((TextField)formData.get(12)).getText(),  //WIFI
                        ((TextField)formData.get(13)).getText(),  //Audio
                        ((TextField)formData.get(14)).getText()); //Form factor
            case "mouse":
                return new Mouse(
                        price,
                        description,
                        productName,
                        manufacturer,
                        serialNumber,
                        ((RadioButton)formData.get(5)).isSelected()); //Boolean wireless
            case "powerSupply":
                return new PowerSupply(
                        price,
                        description,
                        productName,
                        manufacturer,
                        serialNumber,
                        ((TextField)formData.get(5)).getText(),  //Power source
                        ((TextField)formData.get(6)).getText(),  //Voltage
                        ((TextField)formData.get(7)).getText()); //Wattage
            case "RAM":
                return new RAM(
                        price,
                        description,
                        productName,
                        manufacturer,
                        serialNumber,
                        ((TextField)formData.get(5)).getText(),  //Storage capacity (GB)
                        ((TextField)formData.get(6)).getText()); //MegaHz
            case "speaker":
                return new Speaker(
                        price,
                        description,
                        productName,
                        manufacturer,
                        serialNumber,
                        ((TextField)formData.get(5)).getText()); //Input type
            case "storageComponent":
                return new StorageComponent(
                        price,
                        description,
                        productName,
                        manufacturer,
                        serialNumber,
                        ((TextField)formData.get(5)).getText()); //Storage capacity (GB)
        }

        return null;
    }


    //generates path to make sure computercomponents get saved to their respective directories
    public static String createPath(String componentType){
        String path = "";
        switch(componentType){
            case "computerCase":
                path = "src/main/java/com/sample/DAL/SavedFiles/NewComponents/Cases/";
                break;
            case "graphicsCard":
                path = "src/main/java/com/sample/DAL/SavedFiles/NewComponents/GraphicsCards/";
                break;
            case "coolingSystem":
                path = "src/main/java/com/sample/DAL/SavedFiles/NewComponents/CoolingSystems/";
                break;
            case "CPU":
                path = "src/main/java/com/sample/DAL/SavedFiles/NewComponents/CPUs/";
                break;
            case "keyboard":
                path = "src/main/java/com/sample/DAL/SavedFiles/NewComponents/Keyboards/";
                break;
            case "monitor":
                path = "src/main/java/com/sample/DAL/SavedFiles/NewComponents/Monitors/";
                break;
            case "motherBoard":
                path = "src/main/java/com/sample/DAL/SavedFiles/NewComponents/MotherBoards/";
                break;
            case "mouse":
                path = "src/main/java/com/sample/DAL/SavedFiles/NewComponents/Mice/";
                break;
            case "powerSupply":
                path = "src/main/java/com/sample/DAL/SavedFiles/NewComponents/PowerSupplies/";
                break;
            case "RAM":
                path = "src/main/java/com/sample/DAL/SavedFiles/NewComponents/RAMs/";
                break;
            case "speaker":
                path = "src/main/java/com/sample/DAL/SavedFiles/NewComponents/Speakers/";
                break;
            case "storageComponent":
                path = "src/main/java/com/sample/DAL/SavedFiles/NewComponents/StorageComponents/";
                break;
        }
        return path;
    }



    /*
    these next twelve functions all perform the same task, only returning different types of Lists.
    First, they get a list of paths which contain all the saved JOBJ documents in their relevant directories (/NewComponents/Cases, /CoolingSystems, /CPUs etc)
    using FileOpenerJobj.getFilesFromFolder. Then we loop through all the files and create new objects using ois.readObject. If the object
    validates, we add it to our list. String invalidFile is used for handling an exception in which the current Thread cannot read from the file because invalid data has been added manually.
     */
    public static List<Case> createCasesFromFile() throws IOException, ValidationException {
        final File caseFolder = new File("src/main/java/com/sample/DAL/SavedFiles/NewComponents/Cases");
        ObservableList<Case> allCases = FXCollections.observableArrayList();
        List<Path> filePaths = FileOpenerJobj.getFilesFromFolder(caseFolder);
        String invalidFile = "";
        for (Path file : filePaths){
            invalidFile = file.getFileName().toString();
            try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(String.valueOf(file)))){
                Case foundCase = (Case) ois.readObject();
                try {
                    if (foundCase.validate()){
                        allCases.add(foundCase);
                    }
                } catch (ValidationException e) {
                    throw new ValidationException(e.getMessage());
                }
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                throw new StreamCorruptedException(invalidFile+" has been injected with invalid data. Please reinstall the program or contact support");
            }
        }
        return allCases;
    }

    public static List<CoolingSystem> createCoolingSystemFromFile() throws IOException, InvalidFileDataException, ValidationException {
        final File coolingFolder = new File("src/main/java/com/sample/DAL/SavedFiles/NewComponents/CoolingSystems");
        List<CoolingSystem> allCoolingSystems = new ArrayList<>();
        List<Path> filePaths = FileOpenerJobj.getFilesFromFolder(coolingFolder);
        String invalidFile = "";
        for (Path file : filePaths){
            invalidFile = file.getFileName().toString(); // only relevant if ObjectInputStream throws exception
            try (FileInputStream fi = new FileInputStream(String.valueOf(file)); ObjectInputStream ois = new ObjectInputStream(fi)){
                CoolingSystem foundCoolingSystem = (CoolingSystem) ois.readObject();
                if (foundCoolingSystem.validate()){
                    allCoolingSystems.add(foundCoolingSystem);
                }

            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                throw new InvalidFileDataException(invalidFile+" has been injected with invalid data. Please reinstall the program or contact support");
            } catch (ValidationException e) {
                throw new ValidationException(e.getMessage());
            }
        }
        return allCoolingSystems;
    }
    public static List<GraphicsCard> createGraphicsCardsFromFile() throws IOException, InvalidFileDataException, ValidationException {
        final File GPUFolder = new File("src/main/java/com/sample/DAL/SavedFiles/NewComponents/GraphicsCards");
        List<GraphicsCard> allGPUs = new ArrayList<>();
        List<Path> filePaths = FileOpenerJobj.getFilesFromFolder(GPUFolder);
        String invalidFile = "";
        for (Path file : filePaths){
            invalidFile = file.getFileName().toString();
            try (FileInputStream fi = new FileInputStream(String.valueOf(file)); ObjectInputStream ois = new ObjectInputStream(fi)){
                GraphicsCard foundGPU = (GraphicsCard) ois.readObject();
                if (foundGPU.validate()){
                    allGPUs.add(foundGPU);
                }
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (ValidationException e) {
                throw new ValidationException(e.getMessage());
            } catch (IOException e){
                throw new InvalidFileDataException(invalidFile+" has been injected with invalid data. Please reinstall the program or contact support");
            }
        }
        return allGPUs;
    }

    public static List<Keyboard> createKeyboardsFromFile() throws IOException, InvalidFileDataException, ValidationException {
        final File keyboardFolder = new File("src/main/java/com/sample/DAL/SavedFiles/NewComponents/Keyboards");
        List<Keyboard> allKeyboards = new ArrayList<>();
        List<Path> filePaths = FileOpenerJobj.getFilesFromFolder(keyboardFolder);
        String invalidFile = "";
        for (Path file : filePaths){
            invalidFile = file.getFileName().toString();
            try (FileInputStream fi = new FileInputStream(String.valueOf(file)); ObjectInputStream ois = new ObjectInputStream(fi)){
                Keyboard foundKeyboard = (Keyboard) ois.readObject();
                if (foundKeyboard.validate()){
                    allKeyboards.add(foundKeyboard);
                }
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (ValidationException e) {
                throw new ValidationException(e.getMessage());
            } catch (IOException e){
                throw new InvalidFileDataException(invalidFile+" has been injected with invalid data. Please reinstall the program or contact support");
            }
        }
        return allKeyboards;
    }

    public static List<Mouse> createMiceFromFile() throws IOException, InvalidFileDataException, ValidationException {
        final File mouseFolder = new File("src/main/java/com/sample/DAL/SavedFiles/NewComponents/Mice");
        List<Mouse> allMice = new ArrayList<>();
        List<Path> filePaths = FileOpenerJobj.getFilesFromFolder(mouseFolder);
        String invalidFile = "";
        for (Path file : filePaths){
            invalidFile = file.getFileName().toString();
            try (FileInputStream fi = new FileInputStream(String.valueOf(file)); ObjectInputStream ois = new ObjectInputStream(fi)){
                Mouse foundMouse = (Mouse) ois.readObject();
                if (foundMouse.validate()){
                    allMice.add(foundMouse);
                }
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (ValidationException e) {
                throw new ValidationException(e.getMessage());
            } catch (IOException e){
                throw new InvalidFileDataException(invalidFile+" has been injected with invalid data. Please reinstall the program or contact support");
            }
        }
        return allMice;
    }

    public static List<Monitor> createMonitorsFromFile() throws IOException, InvalidFileDataException, ValidationException {
        final File monitorFolder = new File("src/main/java/com/sample/DAL/SavedFiles/NewComponents/Monitors");
        List<Monitor> allMonitors = new ArrayList<>();
        List<Path> filePaths = FileOpenerJobj.getFilesFromFolder(monitorFolder);
        String invalidFile = "";
        for (Path file : filePaths){
            invalidFile = file.getFileName().toString();
            try (FileInputStream fi = new FileInputStream(String.valueOf(file)); ObjectInputStream ois = new ObjectInputStream(fi)){
                Monitor foundMonitor = (Monitor) ois.readObject();
                if (foundMonitor.validate()){
                    allMonitors.add(foundMonitor);
                }
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (ValidationException e) {
                throw new ValidationException(e.getMessage());
            } catch (IOException e){
                throw new InvalidFileDataException(invalidFile+" has been injected with invalid data. Please reinstall the program or contact support");
            }
        }
        return allMonitors;
    }

    public static List<Motherboard> createMotherboardsFromFile() throws IOException, InvalidFileDataException, ValidationException {
        final File motherboardFolder = new File("src/main/java/com/sample/DAL/SavedFiles/NewComponents/MotherBoards");
        List<Motherboard> allMotherBoards = new ArrayList<>();
        List<Path> filePaths = FileOpenerJobj.getFilesFromFolder(motherboardFolder);
        String invalidFile = "";
        for (Path file : filePaths){
            invalidFile = file.getFileName().toString();
            try (FileInputStream fi = new FileInputStream(String.valueOf(file)); ObjectInputStream ois = new ObjectInputStream(fi)){
                Motherboard foundMotherboard = (Motherboard) ois.readObject();
                if (foundMotherboard.validate()){
                    allMotherBoards.add(foundMotherboard);
                }
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (ValidationException e) {
                throw new ValidationException(e.getMessage());
            } catch (IOException e){
                throw new InvalidFileDataException(invalidFile+" has been injected with invalid data. Please reinstall the program or contact support");
            }
        }
        return allMotherBoards;
    }

    public static List<PowerSupply> createPowerSuppliesFromFile() throws IOException, InvalidFileDataException, ValidationException {
        final File powerSupplyFolder = new File("src/main/java/com/sample/DAL/SavedFiles/NewComponents/PowerSupplies");
        List<PowerSupply> allPowerSupplies = new ArrayList<>();
        List<Path> filePaths = FileOpenerJobj.getFilesFromFolder(powerSupplyFolder);
        String invalidFile = "";
        for (Path file : filePaths){
            invalidFile = file.getFileName().toString();
            try (FileInputStream fi = new FileInputStream(String.valueOf(file)); ObjectInputStream ois = new ObjectInputStream(fi)){
                PowerSupply foundPowerSupply = (PowerSupply) ois.readObject();
                if (foundPowerSupply.validate()){
                    allPowerSupplies.add(foundPowerSupply);
                }
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (ValidationException e) {
                throw new ValidationException(e.getMessage());
            } catch (IOException e){
                throw new InvalidFileDataException(invalidFile+" has been injected with invalid data. Please reinstall the program or contact support");
            }
        }
        return allPowerSupplies;
    }

    public static List<Processor> createProcessorsFromFile() throws IOException, InvalidFileDataException, ValidationException {
        final File CPUFolder = new File("src/main/java/com/sample/DAL/SavedFiles/NewComponents/CPUs");
        List<Processor> allCPUs = new ArrayList<>();
        List<Path> filePaths = FileOpenerJobj.getFilesFromFolder(CPUFolder);
        String invalidFile = "";
        for (Path file : filePaths){
            invalidFile = file.getFileName().toString();
            try (FileInputStream fi = new FileInputStream(String.valueOf(file)); ObjectInputStream ois = new ObjectInputStream(fi)){
                Processor foundCPU = (Processor) ois.readObject();
                if (foundCPU.validate()){
                    allCPUs.add(foundCPU);
                }

            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (ValidationException e) {
                throw new ValidationException(e.getMessage());
            } catch (IOException e){
                throw new InvalidFileDataException(invalidFile+" has been injected with invalid data. Please reinstall the program or contact support");
            }
        }
        return allCPUs;
    }

    public static List<RAM> createRAMsFromFile() throws IOException, InvalidFileDataException, ValidationException {
        final File RAMFolder = new File("src/main/java/com/sample/DAL/SavedFiles/NewComponents/RAMs");
        List<RAM> allRAMs = new ArrayList<>();
        List<Path> filePaths = FileOpenerJobj.getFilesFromFolder(RAMFolder);
        String invalidFile = "";
        for (Path file : filePaths){
            invalidFile = file.getFileName().toString();
            try (FileInputStream fi = new FileInputStream(String.valueOf(file)); ObjectInputStream ois = new ObjectInputStream(fi)){
                RAM foundRAM = (RAM) ois.readObject();
                if(foundRAM.validate()){
                    allRAMs.add(foundRAM);
                }
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (ValidationException e) {
                throw new ValidationException(e.getMessage());
            } catch (IOException e){
                throw new InvalidFileDataException(invalidFile+" has been injected with invalid data. Please reinstall the program or contact support");
            }
        }
        return allRAMs;
    }

    public static List<Speaker> createSpeakersFromFile() throws IOException, InvalidFileDataException, ValidationException {
        final File speakerFolder = new File("src/main/java/com/sample/DAL/SavedFiles/NewComponents/Speakers");
        List<Speaker> allSpeakers = new ArrayList<>();
        List<Path> filePaths = FileOpenerJobj.getFilesFromFolder(speakerFolder);
        String invalidFile = "";
        for (Path file : filePaths){
            invalidFile = file.getFileName().toString();
            try (FileInputStream fi = new FileInputStream(String.valueOf(file)); ObjectInputStream ois = new ObjectInputStream(fi)){
                Speaker foundSpeaker = (Speaker) ois.readObject();
                if (foundSpeaker.validate()){
                    allSpeakers.add(foundSpeaker);
                }
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (ValidationException e) {
                throw new ValidationException(e.getMessage());
            } catch (IOException e){
                throw new InvalidFileDataException(invalidFile+" has been injected with invalid data. Please reinstall the program or contact support");
            }
        }
        return allSpeakers;
    }

    public static List<StorageComponent> createStorageComponentsFromFile() throws IOException, InvalidFileDataException, ValidationException {
        final File storageComponentFolder = new File("src/main/java/com/sample/DAL/SavedFiles/NewComponents/StorageComponents");
        List<StorageComponent> allStorageComponents = new ArrayList<>();
        List<Path> filePaths = FileOpenerJobj.getFilesFromFolder(storageComponentFolder);
        String invalidFile = "";
        for (Path file : filePaths){
            invalidFile = file.getFileName().toString();
            try (FileInputStream fi = new FileInputStream(String.valueOf(file)); ObjectInputStream ois = new ObjectInputStream(fi)){
                StorageComponent foundStorageComponent = (StorageComponent) ois.readObject();
                if (foundStorageComponent.validate()){
                    allStorageComponents.add(foundStorageComponent);
                }
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (ValidationException e) {
                throw new ValidationException(e.getMessage());
            } catch (IOException e){
                throw new InvalidFileDataException(invalidFile+" has been injected with invalid data. Please reinstall the program or contact support");
            }
        }
        return allStorageComponents;
    }

}
