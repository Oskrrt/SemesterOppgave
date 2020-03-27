package com.sample.DAL.OpenFile;

/*public interface FileOpener {
    String path = null;

    public String read();
}*/

import javafx.concurrent.Task;

abstract class FileOpener extends Task<Void> {
    private String path;

    public FileOpener(String path) {
        this.path = path;
    }

    public abstract String read();
}