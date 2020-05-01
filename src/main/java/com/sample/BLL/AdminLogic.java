package com.sample.BLL;

import com.sample.App;
import com.sample.DAL.SaveFile.FileSaver;
import com.sample.DAL.UpdateFile.UpdateJobj;
import com.sample.Models.ComputerComponents.*;
import com.sample.controllers.adminUserController;
import javafx.scene.Node;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import org.w3c.dom.Text;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;


public class AdminLogic {

    public static <T extends ComputerComponent> Boolean saveComponent(T component, String type) {
        return FileSaver.saveComponent(component, type);
    }


    public static void swapViewsBasedOnButtonPressed_ADD_COMPONENTS(String buttonPressed) throws IOException {
        App.changeView("/fxml/ComponentForms/"+buttonPressed+"Form.fxml", 650, 700);
    }

    public static boolean editFile(File fileName, ComputerComponent newComponent) throws IOException{
        return UpdateJobj.updateFile(fileName, newComponent);
    }

    public static boolean editFileName(File originalFile, File newFile) {
        return UpdateJobj.editFilename(originalFile, newFile);
    }
}
