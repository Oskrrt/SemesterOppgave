package com.sample.BLL;

import com.sample.App;
import com.sample.Models.ComputerComponents.*;
import javafx.scene.Node;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import org.w3c.dom.Text;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class AdminLogic {

    public static <T extends ComputerComponent> Boolean saveComponent(T component, Path filePath) {
        try(OutputStream os = Files.newOutputStream(filePath); ObjectOutputStream out = new ObjectOutputStream(os)) {
            out.writeObject(component);
            os.close();
            out.close();
            return true;
        } catch (IOException e) {
            return false;
        }
    }


    public static void swapViewsBasedOnButtonPressed(String buttonPressed) throws IOException {
        App.changeView(buttonPressed+"Form.fxml", 530, 610 );
    }

    public static ComputerComponent createComponent(List<Node> formData, String type){
        String description = ((TextArea)formData.get(0)).getText();
        double price = Double.parseDouble(((TextField)formData.get(1)).getText());
        String productName = ((TextField)formData.get(2)).getText();
        String manufacturer = ((TextField)formData.get(3)).getText();
        String serialNumber = ((TextField)formData.get(4)).getText();
        switch(type){
            case "caseFormGrid":
                return new Case(((TextField)formData.get(5)).getText(),((TextField)formData.get(6)).getText(), ((TextField)formData.get(7)).getText(), ((TextField)formData.get(8)).getText(),price,description,productName,manufacturer,serialNumber);
            case "graphicsCardFormGrid":
                return new GraphicsCard(
                        price,
                        description,
                        productName,
                        manufacturer,
                        serialNumber,
                        ((TextField)formData.get(5)).getText(),  //memory capacity
                        ((TextField)formData.get(6)).getText()); //memory type
            case "coolingFormGrid":
                return new CoolingSystem(
                        ((TextField)formData.get(5)).getText(), //Width in cm
                        ((TextField)formData.get(6)).getText(), //Height in cm
                        price,
                        description,
                        productName,
                        manufacturer,
                        serialNumber);
            case "CPUFormGrid":
                return new Processor(
                        price,
                        description,
                        productName,
                        manufacturer,
                        serialNumber,
                        ((TextField)formData.get(5)).getText(),  //Core count
                        ((TextField)formData.get(6)).getText(),  // Thread count
                        ((TextField)formData.get(7)).getText()); // Max frequency (Hz)
            case "keyboardFormGrid":
                return new Keyboard(
                        price,
                        description,
                        productName,
                        manufacturer,
                        serialNumber,
                        ((TextField)formData.get(5)).getText(),       //Language layout
                        ((RadioButton)formData.get(6)).isSelected()); //Boolean wireless keyboard
            case "monitorFormGrid":
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
            case "motherBoardFormGrid":
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
            case "mouseFormGrid":
                return new Mouse(
                        price,
                        description,
                        productName,
                        manufacturer,
                        serialNumber,
                        ((RadioButton)formData.get(5)).isSelected()); //Boolean wireless
            case "powerSupplyFormGrid":
                return new PowerSupply(
                        price,
                        description,
                        productName,
                        manufacturer,
                        serialNumber,
                        ((TextField)formData.get(5)).getText(),  //Power source
                        ((TextField)formData.get(6)).getText(),  //Voltage
                        ((TextField)formData.get(7)).getText()); //Wattage
            case "RAMFormGrid":
                return new RAM(
                        price,
                        description,
                        productName,
                        manufacturer,
                        serialNumber,
                        ((TextField)formData.get(5)).getText(),  //Storage capacity (GB)
                        ((TextField)formData.get(6)).getText()); //MegaHz
            case "speakerFormGrid":
                return new Speaker(
                        price,
                        description,
                        productName,
                        manufacturer,
                        serialNumber,
                        ((TextField)formData.get(5)).getText()); //Input type
        }

        return null;
    }

}
