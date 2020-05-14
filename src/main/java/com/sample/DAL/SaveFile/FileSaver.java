package com.sample.DAL.SaveFile;

import com.sample.BLL.ComponentFactory;
import com.sample.Models.Computer.Computer;
import com.sample.Models.Computer.ComputerWithAccessories;
import com.sample.Models.ComputerComponents.ComputerComponent;
import com.sample.Models.Users.User;
import javafx.concurrent.Task;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

abstract public class FileSaver extends Task<Boolean> {
    protected final Path path = Paths.get("src/main/java/com/sample/DAL/SavedFiles/Users.txt");
    private User userToRegister;
    private Computer computerToSave;
    private ComputerWithAccessories computerWithAccessoriesToSave;

    FileSaver(Object dataToSave) {
        if(dataToSave instanceof User) {
            this.userToRegister = (User) dataToSave;
        } else if (dataToSave instanceof Computer) {
            this.computerToSave = (Computer) dataToSave;
        } else if (dataToSave instanceof ComputerWithAccessories) {
            this.computerWithAccessoriesToSave = (ComputerWithAccessories) dataToSave;
        }
    }

    boolean writeToFile(Object dataToSave) throws IOException {
        Path path;
        if (dataToSave instanceof User) {
            path = Paths.get("src/main/java/com/sample/DAL/SavedFiles/Users.txt");
            // Declaring two long fileSize variables, one before writing and one after writing. If the size is changed after writing the writing went successfully
            long fileSize = Files.size(path);
            String contentToWrite = ((User) dataToSave).getMail()+":"+ ((User) dataToSave).getPassword()+":"+ ((User) dataToSave).getAdmin()+"\n";
            Files.write(path, contentToWrite.getBytes(), StandardOpenOption.APPEND);
            long fileSizeAfter = Files.size(path);
            return fileSizeAfter>fileSize;
        } else if(dataToSave instanceof ComputerWithAccessories) {
            path = Paths.get("src/main/java/com/sample/DAL/SavedFiles/SavedComputers/ComputersWithAccessories.txt");
            // Declaring two long fileSize variables, one before writing and one after writing. If the size is changed after writing the writing went successfully
            long fileSize = Files.size(path);
            String contentToWrite = ((ComputerWithAccessories) dataToSave).getValuesToSaveToFile()+":"+((ComputerWithAccessories) dataToSave).getPrice()+"\n";
            Files.write(path, contentToWrite.getBytes(), StandardOpenOption.APPEND);
            long fileSizeAfter = Files.size(path);
            return fileSizeAfter>fileSize;
        } else if(dataToSave instanceof Computer) {
            path = Paths.get("src/main/java/com/sample/DAL/SavedFiles/SavedComputers/Computers.txt");
            // Declaring two long fileSize variables, one before writing and one after writing. If the size is changed after writing the writing went successfully
            long fileSize = Files.size(path);
            String contentToWrite = ((Computer) dataToSave).getValuesToSaveToFile()+":"+((Computer) dataToSave).getPrice()+"\n";
            Files.write(path, contentToWrite.getBytes(), StandardOpenOption.APPEND);
            long fileSizeAfter = Files.size(path);
            return fileSizeAfter>fileSize;
        }
        return false;
    }

    //this function takes in a computercomponent and a string identifying which type of component it is. Because we save every component
    //to their own JOBJ file, we first need to figure out which path to generate. after generating the correct path we add the components
    //product name to the end of the path, to get the component name as the filename.
    public static <T extends ComputerComponent> Boolean saveComponent(T component, String type) throws IOException {
        Path filePath = Paths.get(ComponentFactory.createPath(type)+component.getProductName()+".jobj"); //saves in correct directory with components name as file name.
        try(FileOutputStream os = new FileOutputStream(String.valueOf(filePath)); ObjectOutputStream out = new ObjectOutputStream(os)) {
            out.writeObject(component);
            return true;
        }
    }
}
