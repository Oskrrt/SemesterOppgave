package com.sample.DAL.SaveFile;

import com.sample.Models.Users.User;
import javafx.concurrent.Task;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Arrays;

abstract public class FileSaver extends Task<Boolean> {
    protected String path;
    protected User userToRegister;
    public FileSaver(String path, User userToRegister) {
        this.path = path;
        this.userToRegister = userToRegister;
    }

     boolean writeToFile() throws IOException {
         // lager to byte arrays av samme fil, en før og en etter skrivingen. Dersom de er like betyr det at skrivingen feilet og brukeren blir ikke registrert.
         long fileSize = Files.size(Paths.get(path));
         String contentToWrite = userToRegister.getMail()+":"+userToRegister.getPassword()+"\n";
         Files.write(Paths.get(path), contentToWrite.getBytes(), StandardOpenOption.APPEND);
         long fileSizeAfter = Files.size(Paths.get(path));
         System.out.println(fileSize+"------"+fileSizeAfter);
         System.out.println(fileSizeAfter>fileSize);
         return fileSizeAfter>fileSize;
     }

}
