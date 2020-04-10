package com.sample.BLL;

import com.sample.Models.ComputerComponents.Case;
import com.sample.Models.ComputerComponents.ComputerComponent;
import com.sample.Models.ComputerComponents.CoolingSystem;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class UserLogic {
    public static void renderChoiceWindow(Stage choiceStage, String idOfClickedLabel) throws ClassNotFoundException, IOException {
        Label lbl = new Label(idOfClickedLabel);
        AnchorPane ap = (AnchorPane) choiceStage.getScene().lookup("#ap");
        ap.getChildren().add(lbl);
        choiceStage.show();
        List<ComputerComponent> componentList = getAllComponents(idOfClickedLabel);
        System.out.println(componentList.get(0).getPrice());

    }

    private static List<ComputerComponent> getAllComponents(String type) throws IOException {
        List<? extends ComputerComponent> list = new ArrayList<>();
        String filepath = "src/main/java/com/sample/DAL/SavedFiles/NewComponents/"+type+"s";
        File folderOfComponents = new File(filepath);
        switch (type) {
            case "Case":
                list = ComponentFactory.createCasesFromFile(folderOfComponents);
                break;
            case "graphicsCard":
                list = ComponentFactory.createGraphicsCardsFromFile(folderOfComponents);
                break;
            case "coolingSystem":

                list = ComponentFactory.createCoolingSystemFromFile(folderOfComponents);
                break;
            case "CPU":

                list = ComponentFactory.createProcessorsFromFile(folderOfComponents);
                break;
            case "keyboard":

                list = ComponentFactory.createKeyboardsFromFile(folderOfComponents);
                break;
            case "monitor":

                list = ComponentFactory.createMonitorsFromFile(folderOfComponents);
                break;
            case "motherBoard":
                list = ComponentFactory.createMotherboardsFromFile(folderOfComponents);
                break;
            case "mouse":
                list = ComponentFactory.createMiceFromFile(folderOfComponents);
                break;
            case "powerSupply":
                list = ComponentFactory.createPowerSuppliesFromFile(folderOfComponents);
                break;
            case "RAM":
                list = ComponentFactory.createRAMsFromFile(folderOfComponents);
                break;
            case "speaker":
                list = ComponentFactory.createSpeakersFromFile(folderOfComponents);
                break;
            case "storageComponent":
                list = ComponentFactory.createStorageComponentsFromFile(folderOfComponents);
                break;
        }
            return (List<ComputerComponent>) list;
    }


}

    /*private static List<? extends ComputerComponent> getTypeOfComponent(String type) {
        List<? extends ComputerComponent> list;
        switch (type) {
            case "Case" :
                list = new ArrayList<Case>();
                break;


            case "CoolingSystem" :
                list =  new ArrayList<CoolingSystem>();
                break;

        }
        return null;
    }*/

