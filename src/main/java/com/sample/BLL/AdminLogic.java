package com.sample.BLL;

import com.sample.App;
import com.sample.Models.ComputerComponents.ComputerComponent;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;

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


}
