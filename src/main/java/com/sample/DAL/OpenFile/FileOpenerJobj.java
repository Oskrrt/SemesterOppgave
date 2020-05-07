package com.sample.DAL.OpenFile;

import com.sample.Models.ComputerComponents.ComputerComponent;
import javafx.concurrent.Task;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Array;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public abstract class FileOpenerJobj extends Task<List<? extends ComputerComponent>> {

    //recursively loops through every file in the specified folder until all the filepaths have been read.
    //this solution is in large part thanks to: https://stackoverflow.com/questions/1844688/how-to-read-all-files-in-a-folder-from-java
    public static List<Path> getFilesFromFolder(final File folder) throws IOException {
        List<Path> filePaths = new ArrayList<>();
        for (final File fileEntry: Objects.requireNonNull(folder.listFiles())){
            if (fileEntry.isDirectory()){
                getFilesFromFolder(fileEntry);
            } else {
                filePaths.add(Paths.get(fileEntry.getPath()));
            }
        }
        return filePaths;
    }
}
