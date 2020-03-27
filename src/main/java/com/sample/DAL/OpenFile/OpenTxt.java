package com.sample.DAL.OpenFile;

public class OpenTxt extends FileOpener {

    public OpenTxt(String path) {
        super(path);
    }

    public String read() {
        return "hei";
    }

    @Override
    protected Void call() throws Exception {
        return null;
    }
}
