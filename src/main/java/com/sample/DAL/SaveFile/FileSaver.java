package com.sample.DAL.SaveFile;

import com.sample.Models.Users.User;
import javafx.concurrent.Task;

import java.io.DataOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Arrays;

abstract public class FileSaver extends Task<Boolean> {
    protected final Path path = Paths.get("src/main/java/com/sample/DAL/SavedFiles/Users.txt");
    protected User userToRegister;
    public FileSaver(User userToRegister) {
        this.userToRegister = userToRegister;
    }

     boolean writeToFile() throws IOException {
         // Declaring two long fileSize variables, one before writing and one after writing. If the size is changed after writing the writing went successfully
         long fileSize = Files.size(path);
         String contentToWrite = userToRegister.getMail()+":"+userToRegister.getPassword()+":"+userToRegister.getAdmin()+"\n";
         Files.write(path, contentToWrite.getBytes(), StandardOpenOption.APPEND);
         long fileSizeAfter = Files.size(path);
         System.out.println(fileSize+"------"+fileSizeAfter);
         System.out.println(fileSizeAfter>fileSize);
         return fileSizeAfter>fileSize;
     }

}
