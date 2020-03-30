package com.sample.controllers;

import java.io.IOException;

import com.sample.App;
import com.sample.BLL.Repository;
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
            App.changeView("signUpForm.fxml", 0, 0);
        }
        if (elementClicked.equals("btnSignIn")) {
            App.changeView("signIn.fxml", 0, 0);
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
        User userTryingToLogIn = Repository.validateSignIn(mail, password);
        if (userTryingToLogIn != null && userTryingToLogIn.getLoggedIn()) {
            loggedInUser = userTryingToLogIn;
            System.out.println(userTryingToLogIn.getClass());
            if (loggedInUser instanceof Admin) {
                App.changeView("homeScreenAdmin.fxml", 1200, 900);
            } else {
                App.changeView("homeScreenRegularUser.fxml", 1200, 900);
            }
        } else {
            lblFailedSignIn.setText("Email or Password is incorrect");
        }
    }

    /*public void testApi() throws MalformedURLException, UnsupportedEncodingException {
        *//*HttpResponse <String> httpResponse = Unirest.get("<https://comppartsapi.herokuapp.com/>")
                .asString();
        System.out.println(httpResponse.getHeaders().get("Content-Type"));*//*
        System.out.println(Unirest.get("https://comppartsapi.herokuapp.com/computerparts/cpu").asJson());
        *//*HttpResponse <JsonNode> response = Unirest.get("http://httpbin.org").queryString("fruit", "apple").asJson();
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        //JsonParser jp = new JsonParser();
        JsonElement je = JsonParser.parseString(response.getBody().toString());
        String prettyJsonString = gson.toJson(je);
        System.out.println(prettyJsonString);*//*

        String host = "https://restcountries-v1.p.rapidapi.com/name/norge";
        String charset = "UTF-8";
        String x_rapid_api_host="restcountries-v1.p.rapidapi.com";
        String key = "8cce559715mshd62100f2b2e9db5p198d5cjsna3abec073ac8";
        String s = "Pulp";
        String query = String.format("s=%s", URLEncoder.encode(s, charset));


        HttpResponse<JsonNode> response = Unirest.get("https://comppartsapi.herokuapp.com/computerparts/cpu").asJson();

        System.out.println(response.getBody());
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        JsonElement je = JsonParser.parseString(response.getBody().toString());
        String prettyJsonString = gson.toJson(je);
        System.out.println(prettyJsonString);


    }*/
}
