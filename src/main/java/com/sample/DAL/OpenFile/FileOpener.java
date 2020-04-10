package com.sample.DAL.OpenFile;

/*public interface FileOpener {
    String path = null;

    public String read();
}*/

import com.sample.BLL.ComponentFactory;
import com.sample.Models.ComputerComponents.ComputerComponent;
import javafx.concurrent.Task;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

public abstract class FileOpener extends Task<Void> {
    public Path path;

    public FileOpener(Path path) {
        this.path = path;
    }

    public abstract String read();

    public static List<List<? extends ComputerComponent>> getAllComponents() throws IOException {
        List<List<? extends ComputerComponent>> allComponents = new ArrayList<>();
        List<? extends ComputerComponent> allCases, allCoolingSystems, allGPUs, allKeyboards, allMonitors,  allMotherBoards, allMice, allPowerSupplies, allCPUs, allRAMs, allSpeakers, allStorageComponents = new ArrayList<>();
        final File folderOfNewComponents = new File("src/main/java/com/sample/DAL/SavedFiles/NewComponents");
        List<File> allFolders = getComponentFolders(folderOfNewComponents);

        allCPUs = ComponentFactory.createProcessorsFromFile(allFolders.get(0));
        allComponents.add(allCPUs);

        allCases = ComponentFactory.createCasesFromFile(allFolders.get(1));
        allComponents.add(allCases);

        allCoolingSystems = ComponentFactory.createCoolingSystemFromFile(allFolders.get(2));
        allComponents.add(allCoolingSystems);

        allGPUs = ComponentFactory.createGraphicsCardsFromFile(allFolders.get(3));
        allComponents.add(allGPUs);

        allKeyboards = ComponentFactory.createKeyboardsFromFile(allFolders.get(4));
        allComponents.add(allKeyboards);

        allMice = ComponentFactory.createMiceFromFile(allFolders.get(5));
        allComponents.add(allMice);

        allMonitors = ComponentFactory.createMonitorsFromFile(allFolders.get(6));
        allComponents.add(allMonitors);

        allMotherBoards = ComponentFactory.createMotherboardsFromFile(allFolders.get(7));
        allComponents.add(allMotherBoards);

        allPowerSupplies = ComponentFactory.createPowerSuppliesFromFile(allFolders.get(8));
        allComponents.add(allPowerSupplies);

        allRAMs = ComponentFactory.createRAMsFromFile(allFolders.get(9));
        allComponents.add(allRAMs);

        allSpeakers = ComponentFactory.createSpeakersFromFile(allFolders.get(10));
        allComponents.add(allSpeakers);

        allStorageComponents = ComponentFactory.createStorageComponentsFromFile(allFolders.get(11));
        allComponents.add(allStorageComponents);


        for(List<? extends ComputerComponent> c : allComponents){
            System.out.println();
        }
        return allComponents;

    }

    public static List<Path> getFilesFromFolder(final File folder) throws IOException {
        List<Path> filePaths = new ArrayList<>();
        for (final File fileEntry: folder.listFiles()){
            if (fileEntry.isDirectory()){
                getFilesFromFolder(fileEntry);
            } else {
                filePaths.add(Paths.get(fileEntry.getPath()));
            }
        }
        return filePaths;
    }

    private static List<File> getComponentFolders(final File folder){
        List<File> allFolders = new ArrayList<>();
        for (final File fileEntry : folder.listFiles()){
            if (fileEntry.isFile()){
                getComponentFolders(fileEntry);
            } else {
                allFolders.add(fileEntry);
            }
        }
        Collections.sort(allFolders); //alphabetical for indexing
        return allFolders;
    }

}
