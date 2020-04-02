package com.sample.BLL;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;

public class AdminLogic {

    public static <T> Boolean saveComponent(T component, Path filePath) {
        try(OutputStream os = Files.newOutputStream(filePath); ObjectOutputStream out = new ObjectOutputStream(os)) {
            out.writeObject(component);
            os.close();
            out.close();
            return true;
        } catch (IOException e) {
            return false;
        }
    }



}
