/*
 *  UCF COP3330 Summer 2021 TodoListApplication Assignment 1 Solution
 *  Copyright 2021 Rylan Simpson
 */

package base;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.CheckBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.stage.Stage;
import javafx.util.Callback;

import java.net.URL;
import java.time.LocalDate;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class MainWindowController implements Initializable {

    /*
    * Try preloading all scenes, and swap between scenes
   */

    //Storage variables
    AppResource resources = new AppResource();

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
        resources.showCompleted = !resources.showCompleted;
        System.out.printf("Set showCompleted status to %b%n", resources.showCompleted);
    }

    @FXML
    private CheckBox hideIncompleteBtn;

    @FXML
    private void setHideIncompleteBtn(ActionEvent event){
        //Change hide completion status
        resources.showIncomplete = !resources.showIncomplete;
        System.out.printf("Set showIncomplete status to %b%n", resources.showIncomplete);
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
    private Button clearAllItemsBtn;

    @FXML
    private TableView<ToDoItem> itemTable;
    @FXML
    private TableColumn<ToDoItem, CheckBox> completionColumn;
    @FXML
    private TableColumn<ToDoItem, String> nameColumn;
    @FXML
    private TableColumn<ToDoItem, LocalDate> dateColumn;
    @FXML
    private TableColumn<ToDoItem, String> detailColumn;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //Set up columns for custom use

        completionColumn.setCellValueFactory(new PropertyValueFactory<>("CompletionStatus"));

        nameColumn.setCellValueFactory(new PropertyValueFactory<>("itemName"));
        nameColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        nameColumn.setOnEditCommit(
                t -> (t.getTableView().getItems().get(
                        t.getTablePosition().getRow())
                ).setItemName(t.getNewValue())
        );

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("YYYY-MM-DD");
        dateColumn.setCellValueFactory(new PropertyValueFactory<>("itemDueDate"));


        detailColumn.setCellValueFactory(new PropertyValueFactory<>("itemDetails"));
        detailColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        detailColumn.setOnEditCommit(
                t -> (t.getTableView().getItems().get(
                        t.getTablePosition().getRow())
                ).setItemDetails(t.getNewValue())
        );


        itemTable.setItems(getItemList());
    }

    public ObservableList<ToDoItem> getItemList() {
        resources.items.add(new ToDoItem("Example", LocalDate.of(2000, Month.JANUARY, 1), "This is an example item"));
        return resources.items;
    }

    @FXML
    public void addItemToList(ActionEvent event) {
        String name;
        LocalDate date;
        String details;
        boolean isCompleted;

        //Save name if it is valid
        if (itemNameBox.getText().length() < 1 || itemNameBox.getText().length() > 256) {
            errorLabel.setText("Please enter a valid name for the item. ");
            return;
        } else {
            name = itemNameBox.getText();
        }

        //Save details if they are valid
        if (itemDetailBox.getText().length() < 1 || itemDetailBox.getText().length() > 256) {
            errorLabel.setText("Please enter valid details for the item. ");
            return;
        }
        else {
            details = itemDetailBox.getText();
        }

        //Save date, invalid values are ignored
        date = itemDateBox.getValue();

        //Get completion status
        isCompleted = completionStatusBtn.isSelected();

        ToDoItem item = new ToDoItem(name, date, details, isCompleted);
        resources.items.add(item);
        itemNameBox.clear();
        itemDateBox.getEditor().clear();
        itemDateBox.setValue(null);
        itemDetailBox.clear();
        completionStatusBtn.setSelected(false);
    }

    @FXML
    public void deleteItemFromList(ActionEvent event) {
            int selectionIndex = itemTable.getSelectionModel().getSelectedIndex();
            resources.items.remove(selectionIndex);
    }

    @FXML
    void clearList(ActionEvent event) {
        itemTable.getItems().clear();
    }

}


 class AppResource {
     ObservableList<ToDoItem> items = FXCollections.observableArrayList();
     boolean showCompleted = true;
     boolean showIncomplete = true;
}
