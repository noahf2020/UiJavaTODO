package com.example.demo1;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.Externalizable;
import java.io.IOException;


public class HelloApplication extends Application {


  HelloController myController;

    @Override

    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        // Load the root node and get the controller
        fxmlLoader.load();
        myController = (HelloController) fxmlLoader.getController();


        // Set up the scene
        Scene scene = new Scene(fxmlLoader.load(), 640, 330);
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();

        // Call the method on the controller
    }

    public static void main(String[] args) {
        launch();
    }


    public void stop() throws Exception {


        // Call the method
        myController.RestoreData();

    }

}