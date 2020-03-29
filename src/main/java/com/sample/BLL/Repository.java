package com.sample.BLL;

import com.sample.DAL.OpenFile.OpenTxt;
import com.sample.Models.Users.User;
import org.apache.commons.codec.digest.DigestUtils;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Repository {
    private static final Path pathToUserFile = Paths.get("src/main/java/com/sample/DAL/SavedFiles/Users.txt");

    public static User validateSignIn(String mail, String password) throws IOException {
        OpenTxt opener = new OpenTxt(pathToUserFile);
        String hashedPassword = DigestUtils.shaHex(password);
        User userFromFile = opener.getUserTryingToLogIn(mail);
        if (userFromFile == null) {
            return null;
        }
        if (userFromFile.getMail().equals(mail) && userFromFile.getPassword().equals(hashedPassword)) {
            userFromFile.setLoggedIn(true);
            return userFromFile;
        } else {
            return null;
        }
    }

}
