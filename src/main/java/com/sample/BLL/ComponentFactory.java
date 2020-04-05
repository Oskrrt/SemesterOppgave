package com.sample.BLL;

import com.sample.Models.ComputerComponents.*;
import javafx.scene.Node;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class ComponentFactory {

    public static ComputerComponent createComponent(List<Node> formData, String type){
        String description = ((TextArea)formData.get(0)).getText();
        double price = Double.parseDouble(((TextField)formData.get(1)).getText());
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
}
