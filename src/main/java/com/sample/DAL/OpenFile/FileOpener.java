package com.sample.DAL.OpenFile;




import com.sample.Models.Computer.Computer;
import com.sample.Models.Computer.ComputerWithAccessories;
import com.sample.Models.ComputerComponents.*;
import javafx.concurrent.Task;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public abstract class FileOpener extends Task<List<Computer>> {


/*
    List<String> getComputerInfo() {
        List<String> computerInfo = new ArrayList<>();
        try (BufferedReader reader = Files.newBufferedReader(pathForComputers)) {
            String line;
            String[] componentInfo;
            List<ComputerComponent> actualComponents = new ArrayList<>();
            while ((line = reader.readLine()) != null) {
                componentInfo = line.split(":");
                for (String component : componentInfo) {
                    computerInfo.add(component);
                }
            }
        } catch(IOException | NullPointerException e) {
            e.printStackTrace();
        }
        return computerInfo;
    }
*/
}
