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
        // lager to byte arrays av samme fil, en før og en etter skrivingen. Dersom de er like betyr det at skrivingen feilet og brukeren blir ikke registrert.
        byte[] fileBeforeWriting = Files.readAllBytes(Paths.get(path));
        System.out.println("Inne i call metoden");
        String contentToWrite = userToRegister.getMail()+":"+userToRegister.getPassword()+"\n";
        Files.write(Paths.get(path), contentToWrite.getBytes(), StandardOpenOption.APPEND);
        byte[] fileAfterWriting = Files.readAllBytes(Paths.get(path));
        return Arrays.equals(fileBeforeWriting, fileAfterWriting);
    }

    public void hei() throws IOException {
        String halla = "";
        try(BufferedReader reader  = Files.newBufferedReader(Paths.get(path))) {
            String line;
            while ((line = reader.readLine()) != null) {
                halla += line;
            }
        } catch(IOException e) {
            e.printStackTrace();
        }
        System.out.println(halla);
    }
}
