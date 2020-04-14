

package com.sample;


import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;

import javafx.stage.Stage;

import java.io.IOException;

/**
 * JavaFX App
 */
public class App extends Application {

    private static Scene scene;

    @Override
    public void start(Stage stage) throws IOException {
        scene = new Scene(loadFXML("/fxml/signIn.fxml"), 500, 450);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }

    public static void changeView(String fxml, double width, double height) throws IOException {
        Stage stage = (Stage)scene.getWindow();

        // this checks if any width and height values were given in the calling of the method and resizes the window accordingly
        if (width > 1 && height > 1) {
            stage.setWidth(width);
            stage.setHeight(height);
        }
        stage.centerOnScreen();
        scene.setRoot(loadFXML(fxml));
    }

    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml));
        return fxmlLoader.load();
    }


    public static void main(String[] args) {
        launch();
    }
}