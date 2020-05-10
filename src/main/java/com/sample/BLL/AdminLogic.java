package com.sample.BLL;

import com.sample.App;
import com.sample.DAL.SaveFile.FileSaver;
import com.sample.DAL.UpdateFile.UpdateJobj;
import com.sample.Models.ComputerComponents.*;

import java.io.*;


public class AdminLogic {

    //takes in any component and sends it further down the line to our Data Access Layer
    public static <T extends ComputerComponent> Boolean saveComponent(T component, String type) throws IOException {
        return FileSaver.saveComponent(component, type);
    }


    //Each FXML-file containing the different forms for every component is named after the buttonPressed fxid + Form.fxml (for example: btnCaseForm.fxml)
    //this made it easy to swap view correctly as we always know which file needs to be loaded based on which button was pressed.
    public static void swapViewsBasedOnButtonPressed(String buttonPressed) throws IOException {
        App.changeView("/fxml/ComponentForms/"+buttonPressed+"Form.fxml", 650, 700);
    }

    //takes in a file and a component and sends it further down the line to our Data Access Layer
    public static boolean editFile(File fileName, ComputerComponent newComponent) throws IOException{
        return UpdateJobj.updateFile(fileName, newComponent);
    }

    //takes in two files, one with the original filename and one with the new filename, and sends it further down the line to our Data Access Layer
    public static boolean editFileName(File originalFile, File newFile) {
        return UpdateJobj.editFilename(originalFile, newFile);
    }
}
