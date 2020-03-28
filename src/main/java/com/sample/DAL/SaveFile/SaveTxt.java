package com.sample.DAL.SaveFile;

import com.sample.Models.User;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Arrays;

public class SaveTxt extends FileSaver {
    public SaveTxt(String path, User userToRegister) {
        super(path, userToRegister);
    }

    @Override
    protected Boolean call() throws Exception {
        System.out.println("Inne i call metoden");
        return super.writeToFile();
    }


}
