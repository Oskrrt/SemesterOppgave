package com.sample.DAL.DeleteFile;

import com.sample.Models.ComputerComponents.ComputerComponent;

import java.io.File;

public class DeleteJobj {
    //goes through all saved files in NewComponents directory and deletes the matching filename (which is the <name of the component>.jobj)
    public static boolean deleteComponent(final File folder, ComputerComponent component){
        for (final File fileEntry : folder.listFiles()) {
            if (fileEntry.isDirectory()) {
                deleteComponent(fileEntry, component);
            } else {
                if (fileEntry.getName().equals(component.getProductName()+".jobj")){
                    return fileEntry.delete();
                }
            }
        }
        return false;
    }
}
