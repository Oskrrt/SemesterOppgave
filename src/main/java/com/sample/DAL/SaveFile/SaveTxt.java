package com.sample.DAL.SaveFile;

import com.sample.Models.Users.User;

public class SaveTxt extends FileSaver {
    public SaveTxt(User userToRegister) {
        super(userToRegister);
    }

    @Override
    protected Boolean call() throws Exception {
        System.out.println("Inne i call metoden");
        return super.writeToFile();
    }
}
