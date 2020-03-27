package com.sample.BLL;

import com.sample.Models.User;

public class Repository {
    public static boolean validateSignIn(User userTryingToLogIn) {
        if (userTryingToLogIn.getMail().equals("oskar_ruyter@hotmail.com") && userTryingToLogIn.getPassword().equals("1234")) {
            return true;
        }
        return false;
    }
}
