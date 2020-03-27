package com.sample.controllers;

import com.sample.BLL.Repository;
import com.sample.DAL.SaveFile.SaveTxt;
import com.sample.Models.User;
import javafx.concurrent.WorkerStateEvent;
import javafx.event.ActionEvent;
import javafx.event.Event;
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
    private MainController mainController = new MainController();
    private Repository repo = new Repository();
    private SaveTxt saver;

    @FXML
    void signUp(ActionEvent event) throws IOException {
        lblNotifyIfSignUpSucceeded.setText("");
        //User userToRegister = new User(txtEmail.getText(), txtPassword.getText());
        User userToRegister = new User();
        if (!userToRegister.validateUser(txtEmail.getText(), txtPassword.getText(), txtConfirmPassword.getText())) {
            lblNotifyIfSignUpSucceeded.setStyle("-fx-text-fill: red");
            lblNotifyIfSignUpSucceeded.setText("Failed to sign up");
            return;
        }

        saver = new SaveTxt("src/main/java/com/sample/DAL/SavedFiles/Users.txt", userToRegister);
        Thread tr = new Thread(saver);
        saver.setOnSucceeded(this::succeed);
        tr.setDaemon(true);
        tr.start();
        //boolean he = repo.validateSignUp(userToRegister);
        //repo.testFil();

        //mainController.changeView(event);
    }

    private void succeed(WorkerStateEvent e) {
        if (!saver.getValue()) {
            lblNotifyIfSignUpSucceeded.setText("Successfully registered your account");
            btnSignIn.setVisible(true);
            btnSignIn.setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent mouseEvent) {
                    try {
                        mainController.changeView(mouseEvent);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            });
        } else {
            //Kommer bare hit dersom det ikke gikk å skrive brukerinformasjonen til fil.
            lblNotifyIfSignUpSucceeded.setText("Could not register the account");
        }
    }
}
