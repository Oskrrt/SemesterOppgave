package com.sample.BLL;

import com.sample.DAL.DeleteFile.DeleteJobj;
import com.sample.Models.ComputerComponents.ComputerComponent;

import java.io.File;
import java.nio.file.Files;

public class ComponentDeleter {
    //sends the requested component to be deleted further down into our Data Access Layer for deletion
    public static boolean deleteComponent(final File folder, ComputerComponent component) {
        return DeleteJobj.deleteComponent(folder, component);
    }
}
