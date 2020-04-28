package com.sample.BLL;

import com.sample.DAL.DeleteFile.DeleteJobj;
import com.sample.Models.ComputerComponents.ComputerComponent;

import java.io.File;
import java.nio.file.Files;

public class ComponentDeleter {
    public static boolean deleteComponent(final File folder, ComputerComponent component) {
        return DeleteJobj.deleteComponent(folder, component);
    }
}
