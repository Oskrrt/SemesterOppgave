package com.sample.DAL.SaveFile;

import com.sample.Models.User;
import javafx.concurrent.Task;

abstract public class FileSaver extends Task<Boolean> {
    protected String path;
    protected User userToRegister;
    public FileSaver(String path, User userToRegister) {
        this.path = path;
        this.userToRegister = userToRegister;
    }

}
