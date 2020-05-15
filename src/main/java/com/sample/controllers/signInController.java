package com.sample.controllers;

import java.io.IOException;

import com.sample.App;
import com.sample.BLL.LoginLogic;
import com.sample.DAL.OpenFile.FileOpener;
import com.sample.DAL.SaveFile.FileSaver;
import com.sample.Models.Users.Admin;
import com.sample.Models.Users.User;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;

public class signInController {
    @FXML
    private AnchorPane ap;
    @FXML
    private Button mainButton;


    @FXML
    public void changeView(Event event) throws IOException {
        String elementClicked = ((Control) event.getSource()).getId();
        if (elementClicked.equals("signUpLink")) {
            App.changeView("/fxml/signUpForm.fxml", 0, 0);
        }
        if (elementClicked.equals("btnSignIn")) {
            App.changeView("/fxml/signIn.fxml", 0, 0);
        }
    }
    @FXML
    private TextField txtEmail;

    @FXML
    private PasswordField txtPassword;

    @FXML
    private Label lblFailedSignIn;
    private static User loggedInUser;

    public static User getLoggedInUser() {
        return loggedInUser;
    }

    public void signIn(Event event) throws IOException {
        // makes it so you can log in by pressing enter on your keyboard
        if (event instanceof KeyEvent) {
            if (((KeyEvent) event).getCode() != KeyCode.ENTER) {
                return;
            }
        }
        String mail = txtEmail.getText().toLowerCase();
        String password = txtPassword.getText();
        User userTryingToLogIn = LoginLogic.validateSignIn(mail, password);
        if (userTryingToLogIn != null && userTryingToLogIn.getLoggedIn()) {
            loggedInUser = userTryingToLogIn;
            if (loggedInUser instanceof Admin) {
                App.changeView("/fxml/homeScreenAdmin.fxml", 1200, 900);
            } else {
                App.changeView("/fxml/homeScreenRegularUser.fxml", 1200, 900);
            }
        } else {
            lblFailedSignIn.setText("Email or Password is incorrect");
        }
    }

}
