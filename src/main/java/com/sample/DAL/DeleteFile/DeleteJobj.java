package com.sample.DAL.DeleteFile;

import com.sample.Models.ComputerComponents.ComputerComponent;

import java.io.File;

public class DeleteJobj {
    //goes through all saved files in NewComponents directory and deletes the matching filename(s) (which is the <name of the component>.jobj)
    //it takes in one folder instead of declaring a folder within the function. This is because we need to recursively walk through the directory
    //and check whether or not it is in fact a file, or a folder. If we declared the path to ./NewComponents/ inside the function it would endlessly
    //recurse the ComputerComponent until an exception is thrown.
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
