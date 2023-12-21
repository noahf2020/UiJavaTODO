package com.example.demo1;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.StageStyle;

import java.io.*;

public class HelloController {
    @FXML
    private Label welcomeText;
    public TextField inputValue;
    public Button saveTodo;
    public Button deleteTodo;
    public Button completeTodo;
    public ListView listViewJawn;
    public ListView listViewJawnRD;
    public ListView listViewCompleted;
    public Button deleteAll;
    public Button recoverAll;
    public Button recoverSelected;


    public Button completeAll;

//    public void initialize() throws Exception {
//        this.RestoreData();
//
//    }

    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Welcome to JavaFX Application!");
    }

    @FXML
    protected void logInput() throws Exception {

        System.out.println("Log ");
        listViewJawn.getItems().add(inputValue.getText());

    }

    @FXML
    protected  void deleteItem(){
        listViewJawnRD.getItems().add(listViewJawn.getSelectionModel().getSelectedItem());
        listViewJawn.getItems().remove(listViewJawn.getSelectionModel().getSelectedIndex());
    }
    @FXML
    protected void deleteAll(){
        for (Object element :listViewJawn.getItems())
            listViewJawnRD.getItems().add(element);
            listViewJawn.getItems().clear();
    }

    @FXML
    protected void recoverAll(){
        for (Object element :listViewJawnRD.getItems())
            listViewJawn.getItems().add(element);
        listViewJawnRD.getItems().clear();
    }
    @FXML
    protected void recoverSelected(){
        listViewJawn.getItems().add(listViewJawnRD.getSelectionModel().getSelectedItem());
        listViewJawnRD.getItems().remove(listViewJawnRD.getSelectionModel().getSelectedIndex());
    }
    @FXML
    protected  void completeItem(){

        listViewCompleted.getItems().add(listViewJawn.getSelectionModel().getSelectedItem());
        listViewJawn.getItems().remove(listViewJawn.getSelectionModel().getSelectedIndex());
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Completed Todos");
        alert.setHeaderText("Congradulations on your todos");
        alert.initStyle(StageStyle.UTILITY);

        alert.show();
    }
    @FXML
    protected  void completeALL(){
        for (Object element :listViewJawn.getItems())
            listViewCompleted.getItems().add(element);
            listViewJawn.getItems().clear();
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Completed Todos");
        alert.setHeaderText("Congradulations on your todos!");
        alert.initStyle(StageStyle.UTILITY);

        alert.show();
    }


    public void saveData() throws Exception {
        File fileForData = new File("TaskData");
        FileOutputStream outputStream = new FileOutputStream(fileForData);
        ObjectOutputStream objOutputStream = new ObjectOutputStream(outputStream);
        objOutputStream.writeObject(listViewJawn.getItems().size());
        for (Object item : listViewJawn.getItems()) {
            objOutputStream.writeObject(item);
        }
        objOutputStream.flush();
        outputStream.flush();



    }


    public void RestoreData() throws Exception {
        File fileForData = new File("TaskData");
        FileInputStream inputStream = new FileInputStream(fileForData);
        ObjectInputStream objInputStream = new ObjectInputStream(inputStream);
        Integer numOfSavedObjects = (Integer)objInputStream.readObject();
        for (int i = 0; i < numOfSavedObjects; i = i + 1) {
            String listText = (String) objInputStream.readObject();
            listViewJawn.getItems().add(listText);
        }
        inputStream.close();


    }

}