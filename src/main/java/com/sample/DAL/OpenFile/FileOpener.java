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

}
