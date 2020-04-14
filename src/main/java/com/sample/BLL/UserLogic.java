package com.sample.BLL;

import com.sample.Models.ComputerComponents.ComputerComponent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class UserLogic {
    /*public static void renderChoiceWindow(Stage choiceStage, String idOfClickedLabel) throws ClassNotFoundException, IOException {
        Label lbl = new Label(idOfClickedLabel);
        AnchorPane ap = (AnchorPane) choiceStage.getScene().lookup("#ap");
        ap.getChildren().add(lbl);
        choiceStage.show();
        List<ComputerComponent> componentList = getAllComponents(idOfClickedLabel);
        System.out.println(componentList.get(0).getPrice());
    }*/

    public static void openCorrectWindow(String componentToChoose) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(UserLogic.class.getResource("/fxml/chooseComponentWindows/choose"+componentToChoose+".fxml"));
            Parent root = fxmlLoader.load();
            Stage stage = new Stage();
            // the following line makes it so the user can't interact with other parts of the application while this window is open.
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setTitle(componentToChoose+"s");
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static List<ComputerComponent> getAllComponents(String type) throws IOException {
        List<? extends ComputerComponent> list = new ArrayList<>();
        switch (type) {
            case "Case":
                list = ComponentFactory.createCasesFromFile();
                break;
            case "graphicsCard":
                list = ComponentFactory.createGraphicsCardsFromFile();
                break;
            case "coolingSystem":

                list = ComponentFactory.createCoolingSystemFromFile();
                break;
            case "CPU":

                list = ComponentFactory.createProcessorsFromFile();
                break;
            case "keyboard":

                list = ComponentFactory.createKeyboardsFromFile();
                break;
            case "monitor":
                list = ComponentFactory.createMonitorsFromFile();
                break;
            case "motherBoard":
                list = ComponentFactory.createMotherboardsFromFile();
                break;
            case "mouse":
                list = ComponentFactory.createMiceFromFile();
                break;
            case "powerSupply":
                list = ComponentFactory.createPowerSuppliesFromFile();
                break;
            case "RAM":
                list = ComponentFactory.createRAMsFromFile();
                break;
            case "speaker":
                list = ComponentFactory.createSpeakersFromFile();
                break;
            case "storageComponent":
                list = ComponentFactory.createStorageComponentsFromFile();
                break;
        }
            return (List<ComputerComponent>) list;
    }


}


