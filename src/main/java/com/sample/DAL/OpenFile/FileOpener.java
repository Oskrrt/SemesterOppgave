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

public abstract class FileOpener extends Task<List<String>> {
    private final Path pathForComputers = Paths.get("src/main/java/com/sample/DAL/SavedFiles/SavedComputers/Computers.txt");
    private final Path PathForComputersWithAccessories = Paths.get("src/main/java/com/sample/DAL/SavedFiles/SavedComputers/ComputersWithAccessories.txt");

    /*List<Computer> getSavedComputers() {
        List<Computer> allComputers = new ArrayList<>();
        try (BufferedReader reader = Files.newBufferedReader(pathForComputers)) {
            String line;
            String[] components;
            String[] componentInfo;
            List<ComputerComponent> actualComponents = new ArrayList<>();
            while ((line = reader.readLine()) != null) {
                components = line.split(":");
                try {
                    // sets the name and price of the computer, the price will always be the last element so therefore the components[components.length-1]
                    Computer computerFromFile = new Computer(components[0], Double.parseDouble(components[components.length-1]));
                    for (String component : components) {
                        // checks if the component contains ; if it does create a new array with the seperated info
                        // and generates a component with the name and price of the component
                        if(component.contains(";")) {
                            componentInfo = component.split(";");
                            actualComponents.add(generateComponentFromFile(componentInfo));
                        }
                    }
                    // Sets the components to the computer and finally adds the computer to the computer list
                    computerFromFile.setComputerCase((Case) actualComponents.get(0));
                    computerFromFile.setCooling((CoolingSystem) actualComponents.get(1));
                    computerFromFile.setGraphicsCard((GraphicsCard) actualComponents.get(2));
                    computerFromFile.setStorageComponent((StorageComponent) actualComponents.get(3));
                    computerFromFile.setMotherboard((Motherboard) actualComponents.get(4));
                    computerFromFile.setPowerSupply((PowerSupply) actualComponents.get(5));
                    computerFromFile.setCPU((Processor) actualComponents.get(6));
                    computerFromFile.setMemory((RAM) actualComponents.get(7));
                    allComputers.add(computerFromFile);
                } catch (NumberFormatException e) {
                    e.printStackTrace();
                }
            }
        } catch(IOException | NullPointerException e) {
            e.printStackTrace();
        }
        return allComputers;
    }

    private <T extends ComputerComponent> ComputerComponent generateComponentFromFile(String[] componentInfo) {
        try {
            ComputerComponent c = new ComputerComponent(Double.parseDouble(componentInfo[1]), componentInfo[0]);
            return c;
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        return null;
    }*/

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

}
