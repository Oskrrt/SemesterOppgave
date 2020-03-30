package com.sample.DAL.OpenFile;

import com.sample.Models.Users.Admin;
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

   /* public void readAll() throws IOException {
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
    }*/

    public User getUserTryingToLogIn(String mail) {
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
            userTryingToLogIn.setAdmin(Boolean.parseBoolean(userFromFile[2]));
            // determines wether the user trying to log in is an admin user or a regular user using the decorator design pattern
            if (userTryingToLogIn.getAdmin()) {
                Admin admin = new Admin(userTryingToLogIn);
                return admin;
            }
        } catch(IOException e) {
            e.printStackTrace();
        } catch (NullPointerException NPE) {
            return null;
        }
        return userTryingToLogIn;
    }
}
