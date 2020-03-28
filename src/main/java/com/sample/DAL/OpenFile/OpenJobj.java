package com.sample.DAL.OpenFile;

import java.nio.file.Path;

public class OpenJobj extends FileOpener {
   private String path;
   public OpenJobj(Path path) {
       super(path);
   }
    public String read() {
        return null;
    }

    @Override
    protected Void call() throws Exception {
        return null;
    }
}
