package com.sample.BLL;

import com.sample.DAL.SaveFile.SaveTxt;
import com.sample.Models.User;
import javafx.concurrent.WorkerStateEvent;
import javafx.event.EventHandler;

import java.io.IOException;

public class Repository {
    SaveTxt saver;
    public static boolean validateSignIn(User userTryingToLogIn) {
        if (userTryingToLogIn.getMail().equals("oskar_ruyter@hotmail.com") && userTryingToLogIn.getPassword().equals("1234")) {
            return true;
        }
        return false;
    }

   /* public boolean validateSignUp(User userToRegister) {
        System.out.println("Inne i repository");
        saver = new SaveTxt("src/main/java/com/sample/DAL/SavedFiles/Users.txt", userToRegister);
        Thread tr = new Thread(saver);
        saver.setOnSucceeded(this::succeed);
        tr.setDaemon(true);
        tr.start();
        System.out.println("den som faktisk betyr noe "+saver.getValue());
        return true;
    }*/



   /* private boolean succeed(WorkerStateEvent e) {
        System.out.println("ferdig med tråd");
        System.out.println(saver.getValue());
        return saver.getValue();
}
*/
    /*public void testFil() throws IOException {
        SaveTxt saver = new SaveTxt("src/main/java/com/sample/DAL/SavedFiles/Users.txt");
         saver.hei();
    }*/
}
