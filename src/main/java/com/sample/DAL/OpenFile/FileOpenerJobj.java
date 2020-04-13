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
