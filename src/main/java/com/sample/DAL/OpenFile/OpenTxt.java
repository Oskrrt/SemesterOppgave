package com.sample.DAL.OpenFile;

import com.sample.Exceptions.ValidationException;
import com.sample.Models.Computer.Computer;
import com.sample.Models.Computer.ComputerWithAccessories;
import com.sample.Models.ComputerComponents.*;
import com.sample.Models.Users.Admin;
import com.sample.Models.Users.User;

import java.io.BufferedReader;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.Key;
import java.util.ArrayList;
import java.util.List;

public class OpenTxt extends FileOpener {
    private User loggedInUser;
    public OpenTxt(User loggedInUser){
        this.loggedInUser = loggedInUser;
    }
    private final Path pathForComputers = Paths.get("src/main/java/com/sample/DAL/SavedFiles/SavedComputers/Computers.txt");
    private final Path PathForComputersWithAccessories = Paths.get("src/main/java/com/sample/DAL/SavedFiles/SavedComputers/ComputersWithAccessories.txt");

    @Override
    protected List<Computer> call() throws NumberFormatException {
        return getSavedComputers();
    }



    public List<Computer> getSavedComputers() throws NumberFormatException {
        List<Computer> allComputers = new ArrayList<>();
        try (BufferedReader reader = Files.newBufferedReader(pathForComputers)) {
            String line;
            String[] components;
            String[] componentInfo;
            List<ComputerComponent> actualComponents = new ArrayList<>();
            while ((line = reader.readLine()) != null) {
                components = line.split(":");
                if (components[components.length-2].equals(loggedInUser.getMail())){
                    try {
                        // sets the name and price of the computer, the price will always be the last element so therefore the components[components.length-1]
                        Computer computerFromFile = new Computer(components[0], Double.parseDouble(components[components.length-1]));
                        for (String component : components) {
                            // checks if the component contains ; if it does create a new array with the seperated info
                            if(component.contains(";")) {
                                componentInfo = component.split(";");
                                if (generateComponent(componentInfo) == null){
                                    return null;
                                } else {
                                    actualComponents.add(generateComponent(componentInfo));
                                }                            }
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
                    } catch (Exception e) {
                        return null;
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return allComputers;
    }

    public List<ComputerWithAccessories> getSavedComputersWithAccessories() throws NumberFormatException {
        List<ComputerWithAccessories> allComputers = new ArrayList<>();
        try (BufferedReader reader = Files.newBufferedReader(PathForComputersWithAccessories)) {
            String line;
            String[] components;
            String[] componentInfo;
            List<ComputerComponent> actualComponents = new ArrayList<>();
            while ((line = reader.readLine()) != null) {
                components = line.split(":");
                if (components[components.length-2].equals(loggedInUser.getMail())){
                    try {
                        // sets the name and price of the computer, the price will always be the last element so therefore the components[components.length-1]
                        ComputerWithAccessories computerFromFile = new ComputerWithAccessories(components[0], Double.parseDouble(components[components.length-1]));
                        for (String component : components) {
                            // checks if the component contains ; if it does create a new array with the seperated info
                            if(component.contains(";")) {
                                componentInfo = component.split(";");
                                if (generateComponent(componentInfo) == null){
                                    return null;
                                } else {
                                    actualComponents.add(generateComponent(componentInfo));
                                }
                            }
                        }
                        // Sets the components to the computer and finally adds the computer to the computer list
                        computerFromFile.setMouse((Mouse) actualComponents.get(0));
                        computerFromFile.setMonitor((Monitor) actualComponents.get(1));
                        computerFromFile.setKeyboard((Keyboard) actualComponents.get(2));
                        computerFromFile.setSpeaker((Speaker) actualComponents.get(3));
                        computerFromFile.setComputerCase((Case) actualComponents.get(4));
                        computerFromFile.setCooling((CoolingSystem) actualComponents.get(5));
                        computerFromFile.setGraphicsCard((GraphicsCard) actualComponents.get(6));
                        computerFromFile.setStorageComponent((StorageComponent) actualComponents.get(7));
                        computerFromFile.setMotherboard((Motherboard) actualComponents.get(8));
                        computerFromFile.setPowerSupply((PowerSupply) actualComponents.get(9));
                        computerFromFile.setCPU((Processor) actualComponents.get(10));
                        computerFromFile.setMemory((RAM) actualComponents.get(11));
                        allComputers.add(computerFromFile);
                    } catch (Exception e) {
                        return null;
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return allComputers;
    }

    private ComputerComponent generateComponent(String[] componentInfo){
        switch (componentInfo[0]){
            case "Mouse":
                return generateMouse(componentInfo);
            case "Monitor":
                return generateMonitor(componentInfo);
            case "Keyboard":
                return generateKeyboard(componentInfo);
            case "Speaker":
                return generateSpeaker(componentInfo);
            case "Case":
                return generateCase(componentInfo);
            case "CoolingSystem":
                return generateCooling(componentInfo);
            case "GraphicsCard":
                return generateGPU(componentInfo);
            case "StorageComponent":
                return generateStorage(componentInfo);
            case "Motherboard":
                return generateMotherboard(componentInfo);
            case "PowerSupply":
                return generatePowerSupply(componentInfo);
            case "Processor":
                return generateCPU(componentInfo);
            case "RAM":
                return generateRAM(componentInfo);
        }
        return null;
    }

    private Mouse generateMouse(String[] info) {
        boolean wireless = false;
        try{
            if (info[6].equals("Yes")){
                wireless = true;
            }
            Mouse component = new Mouse(Double.parseDouble(info[1]),info[2], info[3],info[4], info[5], wireless);
            if (component.validate()){
                return component;
            }
        } catch (NumberFormatException | ValidationException e) {
            return null;
        }
        return null;
    }

    private Monitor generateMonitor(String[] info) {
        try{
            Monitor component = new Monitor(Double.parseDouble(info[1]),info[2], info[3],info[4], info[5], info[6], info[7], info[8], info[9]);
            if (component.validate()){
                return component;
            }
        } catch (NumberFormatException | ValidationException e) {
            return null;
        }
        return null;
    }

    private Keyboard generateKeyboard(String[] info) {
        boolean wireless = false;
        try{
            if (info[6].equals("Yes")){
                wireless = true;
            }
            Keyboard component = new Keyboard(Double.parseDouble(info[1]),info[2], info[3],info[4], info[5], info[6], wireless);
            if (component.validate()){
                return component;
            }
        } catch (NumberFormatException | ValidationException e) {
            return null;
        }
        return null;
    }

    private Speaker generateSpeaker(String[] info) {
        try{
            Speaker component = new Speaker(Double.parseDouble(info[1]),info[2], info[3],info[4], info[5], info[6]);
            if (component.validate()){
                return component;
            }
        } catch (NumberFormatException | ValidationException e) {
            return null;
        }
        return null;
    }

    private RAM generateRAM(String[] info) {
        try{
            RAM component = new RAM(Double.parseDouble(info[1]),info[2], info[3],info[4], info[5], info[6], info[7]);
            if (component.validate()){
                return component;
            }
        } catch (NumberFormatException | ValidationException e) {
            return null;
        }
        return null;
    }

    private Processor generateCPU(String[] info) {
        try{
            Processor component = new Processor(Double.parseDouble(info[1]),info[2], info[3],info[4], info[5], info[6], info[7], info[8]);
            if (component.validate()){
                return component;
            }
        } catch (NumberFormatException | ValidationException e) {
            return null;
        }
        return null;
    }

    private PowerSupply generatePowerSupply(String[] info) {
        try{
            PowerSupply component = new PowerSupply(Double.parseDouble(info[1]),info[2], info[3],info[4], info[5], info[6], info[7], info[8]);
            if (component.validate()){
                return component;
            }
        } catch (NumberFormatException | ValidationException e) {
            return null;
        }
        return null;
    }

    private Motherboard generateMotherboard(String[] info) {
        try{
            Motherboard component = new Motherboard(Double.parseDouble(info[1]),info[2], info[3],info[4], info[5], info[6],info[7], info[8], info[9], info[10], info[11], info[12], info[13], info[14], info[15]);
            if (component.validate()){
                return component;
            }
        } catch (NumberFormatException | ValidationException e) {
            return null;
        }
        return null;
    }

    private GraphicsCard generateGPU(String[] info) {
        try{
            GraphicsCard component = new GraphicsCard(Double.parseDouble(info[1]),info[2], info[3],info[4], info[5], info[6], info[7]);
            if (component.validate()){
                return component;
            }
        } catch (NumberFormatException | ValidationException e) {
            return null;
        }
        return null;
    }

    private StorageComponent generateStorage(String[] info) {
        try{
            StorageComponent component = new StorageComponent(Double.parseDouble(info[1]),info[2], info[3],info[4], info[5], info[6]);
            if (component.validate()){
                return component;
            }
        } catch (NumberFormatException | ValidationException e) {
            return null;
        }
        return null;
    }

    private CoolingSystem generateCooling(String[] info) {
        try{
            CoolingSystem component = new CoolingSystem(info[6],info[7],Double.parseDouble(info[1]),info[2],info[3], info[4], info[5]);
            if (component.validate()){
                return component;
            }
        } catch (NumberFormatException | ValidationException e) {
            return null;
        }
        return null;
    }

    private Case generateCase(String[] info) {

        try {
            Case component = new Case(info[6], info[7], info[8], info[9], info[10], Double.parseDouble(info[1]), info[2], info[3],  info[4],  info[5]);
            if (component.validate()){
                return component;
            }
        } catch (NumberFormatException | ValidationException e) {
            return null;
        }
        return null;
    }

    public User getUserTryingToLogIn(String mail) {
        Path path = Paths.get("src/main/java/com/sample/DAL/SavedFiles/Users.txt");
        User userTryingToLogIn = new User();

        String[] userFromFile = null;
        try (BufferedReader reader = Files.newBufferedReader(path)) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.contains(mail)) {
                    userFromFile = line.split(":");
                    break;
                }
            }
            userTryingToLogIn.setMail(userFromFile[0]);
            userTryingToLogIn.setPassword(userFromFile[1]);
            userTryingToLogIn.setAdmin(Boolean.parseBoolean(userFromFile[2]));
            // determines whether the user trying to log in is an admin user or a regular user using the decorator design pattern
            if (userTryingToLogIn.getAdmin()) {
                Admin admin = new Admin(userTryingToLogIn);
                return admin;
            }
        } catch(IOException e) {
            e.printStackTrace();
        } catch (NullPointerException NPE) {
            return null;
        }
        return userTryingToLogIn;
    }
}
