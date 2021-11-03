/*
 *  UCF COP3330 Summer 2021 TodoListApplication Assignment 1 Solution
 *  Copyright 2021 Rylan Simpson
 */

package base;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.util.ArrayList;

public class MainWindowController{

    /*
    * Try preloading all scenes, and swap between scenes
   */

    //Storage variables
    ArrayList<ToDoItem> listOfItems;
    ToDoItem currentItem;
    boolean showCompleted = true;
    boolean showIncomplete = true;

    @FXML
    private MenuItem saveBtn;

    @FXML
    private void saveList(ActionEvent event) {
        //Open save window
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("FileSavePrompt.fxml"));
            Parent root1 = fxmlLoader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(root1));
            stage.setTitle("File Save");
            stage.show();
        }
        catch(Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private MenuItem uploadBtn;

    @FXML
    private void uploadList(ActionEvent event) {
        //Open upload window
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("FileUploadPrompt.fxml"));
            Parent root1 = fxmlLoader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(root1));
            stage.setTitle("File Upload");
            stage.show();
        }
        catch(Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private CheckBox hideCompletedBtn;

    @FXML
    private void setHideCompletedBtn(ActionEvent event){
        //Change hide completion status
        showCompleted = !showCompleted;
        System.out.printf("Set showCompleted status to %b%n", showCompleted);
    }

    @FXML
    private CheckBox hideIncompleteBtn;

    @FXML
    private void setHideIncompleteBtn(ActionEvent event){
        //Change hide completion status
        showIncomplete = !showIncomplete;
        System.out.printf("Set showIncomplete status to %b%n", showIncomplete);
    }

    @FXML
    private Label errorLabel;

    @FXML
    private CheckBox completionStatusBtn;

    @FXML
    private DatePicker itemDateBox;

    @FXML
    private TextField itemDetailBox;

    @FXML
    private TextField itemNameBox;

    @FXML
    private Button itemAddBtn;

    @FXML
    private Button itemRemoveBtn;

    @FXML
    private TableView<?> itemTable;

    @FXML
    void useListOfItemsAddBtn(ActionEvent event) {
        //Take information from data boxes and add item to table

    }

    @FXML
    void useListOfItemsRmvBtn(ActionEvent event) {
        //Remove item from table
    }

}
