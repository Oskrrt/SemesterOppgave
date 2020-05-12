package com.sample.controllers;

import com.sample.App;
import com.sample.Exceptions.ValidationException;
import com.sample.BLL.LoginLogic;
import com.sample.DAL.SaveFile.SaveTxt;
import com.sample.Models.Users.Admin;
import com.sample.Models.Users.User;
import javafx.concurrent.WorkerStateEvent;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.io.IOException;

public class registerAdminController {
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

    private LoginLogic repo = new LoginLogic();


    @FXML
    private void register(ActionEvent actionEvent) {
        User newUser = new User();
        try{
            newUser.validateUser(txtEmail.getText(), txtPassword.getText(), txtConfirmPassword.getText());
            newUser.setAdmin(true);
            Admin newAdmin = new Admin(newUser);
            newAdmin.setAdmin(true);
            SaveTxt saver = new SaveTxt(newAdmin);
            Thread saveAdminThread = new Thread(saver);
            saver.setOnSucceeded(this::succeed);
            saver.setOnFailed(this::failed);
            saver.setOnRunning(this::running);
            saveAdminThread.setDaemon(true);
            saveAdminThread.start();
        } catch (ValidationException e){
            lblNotifyIfSignUpSucceeded.setStyle("-fx-text-fill: red");
            lblNotifyIfSignUpSucceeded.setText(e.getMessage());
        }
    }

    private void running(WorkerStateEvent workerStateEvent) {
            lblNotifyIfSignUpSucceeded.setStyle("-fx-text-fill: green");
            lblNotifyIfSignUpSucceeded.setText("Adding new admin...");
    }

    private void failed(WorkerStateEvent workerStateEvent) {
        lblNotifyIfSignUpSucceeded.setStyle("-fx-text-fill: red");
        lblNotifyIfSignUpSucceeded.setText("New admin could not be added.");
    }

    private void succeed(WorkerStateEvent workerStateEvent) {
        txtPassword.setText("");
        txtEmail.setText("");
        txtConfirmPassword.setText("");
        lblNotifyIfSignUpSucceeded.setStyle("-fx-text-fill: green");
        lblNotifyIfSignUpSucceeded.setText("New admin has been added.");

    }


    @FXML
    private void back(ActionEvent actionEvent) throws IOException {
        App.changeView("/fxml/homeScreenAdmin.fxml", 1200, 900);
    }
}
