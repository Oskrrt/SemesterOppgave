package com.sample.controllers;

import com.sample.App;
import com.sample.Exceptions.ValidationException;
import com.sample.BLL.LoginLogic;
import com.sample.DAL.SaveFile.SaveTxt;
import com.sample.Models.Users.User;
import javafx.concurrent.WorkerStateEvent;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

import java.io.IOException;

public class SignUpFormController {
    @FXML
    private TextField txtEmail;
    @FXML
    private PasswordField txtPassword;
    @FXML
    private Button btnSignUp;
    @FXML
    private Button btnSignIn;
    @FXML
    private Label lblNotifyIfSignUpSucceeded;

    @FXML
    private PasswordField txtConfirmPassword;
    private signInController signInController = new signInController();
    private LoginLogic repo = new LoginLogic();
    private SaveTxt saver;

    @FXML
    void backToStart() throws IOException {
        App.changeView("/fxml/signIn.fxml", 0, 0);
    }
    @FXML
    void signUp(ActionEvent event) {
        lblNotifyIfSignUpSucceeded.setText("");
        User userToRegister = new User();
        try {
            userToRegister.validateUser(txtEmail.getText(), txtPassword.getText(), txtConfirmPassword.getText());
            saver = new SaveTxt(userToRegister);
            Thread tr = new Thread(saver);
            saver.setOnSucceeded(this::succeed);
            tr.setDaemon(true);
            tr.start();
        } catch (ValidationException ve) {
            lblNotifyIfSignUpSucceeded.setStyle("-fx-text-fill: red");
            lblNotifyIfSignUpSucceeded.setText(ve.getMessage());
        }

    }

    private void succeed(WorkerStateEvent e) {
        // if the two arrays in SaveTxt.java were not identical(false), that means the user was successfully registered
        if (saver.getValue()) {
            lblNotifyIfSignUpSucceeded.setStyle("-fx-text-fill: green");
            lblNotifyIfSignUpSucceeded.setText("Successfully registered your account");
            btnSignIn.setVisible(true);
            btnSignIn.setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent mouseEvent) {
                    try {
                        signInController.changeView(mouseEvent);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            });
        } else {
            //Kommer bare hit dersom det ikke gikk å skrive brukerinformasjonen til fil.
            lblNotifyIfSignUpSucceeded.setStyle("-fx-text-fill: red");
            lblNotifyIfSignUpSucceeded.setText("Could not register the account");
        }
    }
}
