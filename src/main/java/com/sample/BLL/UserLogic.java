package com.sample.BLL;

import com.sample.Models.ComputerComponents.Case;
import com.sample.Models.ComputerComponents.ComputerComponent;
import com.sample.Models.ComputerComponents.CoolingSystem;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;

public class UserLogic {
    public static void renderChoiceWindow(Stage choiceStage, String idOfClickedLabel) throws ClassNotFoundException {
        Label lbl = new Label(idOfClickedLabel);
        AnchorPane ap = (AnchorPane) choiceStage.getScene().lookup("#ap");
        ap.getChildren().add(lbl);
        choiceStage.show();
        List<ComputerComponent> componentList = new ArrayList<>();
        Case case1 = new Case("3", "1", "21.5", "45.5", "474", 931.0, "Corsair Carbide Series SPEC06 RGB is a compact ATX-case with a clean design, built in RGB lighting and a glass sidepanel", "Corsair Carbide SPEC-06 RGB", "Corsair", "12345");
        componentList.add(case1);

        System.out.println(idOfClickedLabel);
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
}
