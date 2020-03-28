package com.sample.DAL.OpenFile;

/*public interface FileOpener {
    String path = null;

    public String read();
}*/

import javafx.concurrent.Task;

import java.nio.file.Path;

abstract class FileOpener extends Task<Void> {
    public Path path;

    public FileOpener(Path path) {
        this.path = path;
    }

    public abstract String read();
}