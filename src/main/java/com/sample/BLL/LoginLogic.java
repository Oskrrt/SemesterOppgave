package com.sample.BLL;

import com.sample.DAL.OpenFile.OpenTxt;
import com.sample.DAL.SaveFile.SaveTxt;
import com.sample.Models.Users.Admin;
import com.sample.Models.Users.User;
import com.sample.controllers.regularUserController;
import com.sample.controllers.signInController;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import org.apache.commons.codec.digest.DigestUtils;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import static javafx.geometry.Pos.CENTER_RIGHT;

public class LoginLogic {
    private static regularUserController controller = new regularUserController();
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
            //TODO Fjerne den komenterte kode blokken før vi leverer
            // den oppretter et admin objekt
           /* userFromFile.setAdmin("true");
            Admin admin = new Admin(userFromFile);
            admin.setAdmin("true");
            System.out.println("admin:: "+admin.getAdmin());
            System.out.println(admin.getClass());
            SaveTxt saver = new SaveTxt(admin);
            Thread tr = new Thread(saver);
            tr.setDaemon(true);
            tr.start();*/
            return userFromFile;
        } else {
            return null;
        }
    }

    /*public static HBox renderNavBar() throws IOException {
        HBox navBar = new HBox();
        Button btnHome = new Button("Home");
        Button btnMyComputers = new Button("My computers");
        Button btnLogOut = new Button("Log out");
        btnHome.setOnMouseClicked((e)-> {
            try {
                controller.onClickHome();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        });
        btnMyComputers.setOnMouseClicked((e)->{
            controller.onClickMyComputers();
        });
        btnLogOut.setOnMouseClicked((e)-> {
            try {
                controller.logOut();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        });
        navBar.getChildren().addAll(styleButtons(btnHome, btnMyComputers, btnLogOut));
        navBar.setStyle("-fx-border-color: black; -fx-border-width: 0 0 1 0");
        navBar.setLayoutY(0);
        navBar.setLayoutX(0);
        navBar.setPrefWidth(1200);
        navBar.setPrefHeight(100);
        navBar.setAlignment(CENTER_RIGHT);
        navBar.setSpacing(30);
        navBar.setPadding(new Insets(0, 50, 0, 0));
        return navBar;
    }*/

    private static Button[] styleButtons(Button... buttons) {
        for (Button btn : buttons) {
            btn.setStyle("-fx-border-color: black; -fx-background-color: white; -fx-cursor: hand");
            btn.setPrefWidth(200);
            btn.setPrefHeight(50);
        }
        return buttons;
    }


}
