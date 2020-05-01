package com.sample.DAL.UpdateFile;

import com.sample.Models.ComputerComponents.ComputerComponent;

import java.io.*;

public class UpdateJobj {
    public static boolean updateFile(final File file, ComputerComponent updatedComponent) throws IOException {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(file))) {
            out.writeObject(updatedComponent);
            out.flush();
            return true;
        }
    }

    public static boolean editFilename(File originalFile, File newFile) {
        return originalFile.renameTo(newFile);
    }
}
