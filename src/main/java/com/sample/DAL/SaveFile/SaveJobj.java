package com.sample.DAL.SaveFile;

import com.sample.Models.User;

public class SaveJobj extends FileSaver {

    public SaveJobj(String path, User user) {
        super(path, user);
    }
    @Override
    protected Boolean call() throws Exception {
        return false;
    }
}
