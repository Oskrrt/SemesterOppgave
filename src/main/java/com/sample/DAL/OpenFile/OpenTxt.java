package com.sample.DAL.OpenFile;

import com.sample.Models.Users.User;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class OpenTxt extends FileOpener {

    public OpenTxt(Path path) {
        super(path);
    }

    public String read() {
        return "hei";
    }

    @Override
    protected Void call() throws Exception {
        return null;
    }

    public void readAll() throws IOException {
        String halla = "";
        try(BufferedReader reader = Files.newBufferedReader(path)) {
            String line;
            while ((line = reader.readLine()) != null) {
                halla += line;
            }
        } catch(IOException e) {
            e.printStackTrace();
        }
        System.out.println(halla);
    }

    public User getUserTryingToLogIn(String mail) throws IOException {
        User userTryingToLogIn = new User();
        String[] userFromFile = null;
        try (BufferedReader reader = Files.newBufferedReader(path)) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.contains(mail)) {
                    userFromFile = line.split(":");
                    break;
                }
            }
            userTryingToLogIn.setMail(userFromFile[0]);
            userTryingToLogIn.setPassword(userFromFile[1]);
        } catch(IOException e) {
            e.printStackTrace();
        }
        if (userFromFile != null) {
            return userTryingToLogIn;
        } else {
            // Dersom fil lesingen ikke fungerte vil userFromFile fremdeles være == null.
            return null;
        }
    }
}
