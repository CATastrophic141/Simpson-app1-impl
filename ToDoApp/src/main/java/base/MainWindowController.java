/*
 *  UCF COP3330 Summer 2021 TodoListApplication Assignment 1 Solution
 *  Copyright 2021 Rylan Simpson
 */

package base;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.FileChooser;
import javafx.util.StringConverter;
import java.io.*;
import java.net.URL;
import java.time.LocalDate;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Scanner;

public class MainWindowController implements Initializable {
    //Storage variables
    List<ToDoItem> allItems = new ArrayList<>();
    ObservableList<ToDoItem> viewedItems = FXCollections.observableArrayList();

    @FXML
    private MenuItem saveBtn;

    //Save list into a text file
    @FXML
    private void saveList(ActionEvent event) {
        String filePath;

        try {
            //Open save window
            FileChooser fileChooser = new FileChooser();
            fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Text File", ".txt"));
            File file = fileChooser.showSaveDialog(null);
            //If file path is chosen, get the path and generate the file
            if (file != null) {
                filePath = file.getPath();
                System.out.print(filePath);
                generateSaveFile(filePath);
            }
        }
        catch (Exception e){
            //Do nothing
        }
    }

    private void generateSaveFile(String filePath) throws FileNotFoundException {
        //Set the program to print to the generated file
        PrintStream fileOut = new PrintStream(filePath);
        System.setOut(fileOut);
        //Specify the number of items within the list
        System.out.printf("%d%n%n", allItems.size());
        //For every item within the list, print out the details in a list format
        for (ToDoItem item : allItems) {
            System.out.printf("%d%n", item.getItemID());
            System.out.printf("%s%n", item.getItemName());
            System.out.printf("%s%n", item.getItemDueDateString());
            System.out.printf("%s%n", item.getItemDetails());
            System.out.print(item.getCompletionStatusBoolean());
            System.out.printf("%n%n");
        }
        //Set output back to console
        System.setOut(new PrintStream(new FileOutputStream(FileDescriptor.out)));
    }

    @FXML
    private MenuItem uploadBtn;

    @FXML
    private void uploadList(ActionEvent event) {
        String filePath;

        try {
            //Open upload window
            FileChooser fileChooser = new FileChooser();
            File file = fileChooser.showOpenDialog(null);
            //If file path is chosen, get the path and parse the file
            if (file != null) {
                filePath = file.getPath();
                System.out.print(filePath);
                parseListFile(filePath);
            }
        }
        catch (Exception e){
            //Do nothing
        }
    }

    private void parseListFile(String filePath) {
        //Clear stored lists
        viewedItems.clear();
        allItems.clear();
        //Attempt to access file for parsing
        try (Scanner fileIn = new Scanner(new FileInputStream(filePath))){
            //Get the number of items stored within the file
            int numItems = fileIn.nextInt();

            //Data storage variables
            int id;
            String name;
            String details;
            LocalDate dueDate;
            boolean isComplete;

            //For the number of items
            for (int  i = 0; i< numItems; i++) {
                //Parse each piece of item information
                id = fileIn.nextInt();
                //Buffer scanner
                fileIn.nextLine();

                name = fileIn.nextLine();
                dueDate = LocalDate.parse(fileIn.nextLine(), DateTimeFormatter.ofPattern("yyyy-MM-dd"));
                details = fileIn.nextLine();
                isComplete = fileIn.nextBoolean();

                //Create a ToDoItem
                ToDoItem item = new ToDoItem(name, dueDate, details, isComplete, id);

                //Add the item to both the observable list and the total list
                allItems.add(item);
                viewedItems.add(item);
            }
        } catch (Exception e){
            //Print error message
            System.out.printf("%nError parsing file%n");
        }
        //Refresh the tableview
        itemTable.refresh();
    }

    @FXML
    private Button showCompletedBtn;

    @FXML
    private void setShowCompleted(ActionEvent event){
        //Clear the table
        viewedItems.clear();
        //For each item within the complete list, add the item to the observable list if it is completed
        for (ToDoItem item : allItems) {
            if (Boolean.TRUE.equals(item.getCompletionStatusBoolean())) {
                viewedItems.add(item);
            }
        }
        //Refresh the table
        itemTable.refresh();
    }

    @FXML
    private Button showIncompleteBtn;

    @FXML
    private void setShowIncomplete(ActionEvent event){
        //Clear the table
        viewedItems.clear();
        //For each item within the complete list, add the item to the observable list if it is incomplete
        for (ToDoItem item : allItems) {
            if (Boolean.FALSE.equals(item.getCompletionStatusBoolean())) {
                viewedItems.add(item);
            }
        }
        //Refresh the table
        itemTable.refresh();
    }

    @FXML
    private Button showAllBtn;

    @FXML
    private void setShowAll(ActionEvent event){
        //Clear the list
        viewedItems.clear();
        //Add all items to the observable list
        viewedItems.addAll(allItems);
        //Refresh the table
        itemTable.refresh();
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
    private Button itemSelectBtn;

    @FXML
    private Button itemEditBtn;

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

        //Alter date picker display format to use YYYY-MM-DD format
        final String PATTERN = "yyyy-MM-dd";
        StringConverter<LocalDate> converter = new StringConverter<>() {
            final DateTimeFormatter dateFormatter =
                    DateTimeFormatter.ofPattern(PATTERN);
            @Override
            public String toString(LocalDate date) {
                if (date != null) {
                    return dateFormatter.format(date);
                } else {
                    return "";
                }
            }
            @Override
            public LocalDate fromString(String string) {
                if (string != null && !string.isEmpty()) {
                    return LocalDate.parse(string, dateFormatter);
                } else {
                    return null;
                }
            }
        };
        itemDateBox.setConverter(converter);
        dateColumn.setCellValueFactory(new PropertyValueFactory<>("itemDueDate"));
        itemDateBox.getEditor().setDisable(true);

        detailColumn.setCellValueFactory(new PropertyValueFactory<>("itemDetails"));

        //Add an example item into the initial list
        ToDoItem exampleItem = new ToDoItem("Example", LocalDate.of(2000, Month.JANUARY, 1), "This is an example item");
        viewedItems.add(exampleItem);
        allItems.add(exampleItem);
        //Setup table to use observable list
        itemTable.setItems(getViewedItemList());
    }

    //Retrieve the observable list to be used by the table
    private ObservableList<ToDoItem> getViewedItemList() {
        return viewedItems;
    }

    @FXML
    private void selectItemFromList(ActionEvent event) {
        //Retrieve the index of the selected row
        int selectionIndex = itemTable.getSelectionModel().getSelectedIndex();

        //If no row is selected, end method
        if (selectionIndex == -1) {
            errorLabel.setText("Please select an item to edit");
            return;
        }

        //Get the item from the selected row
        ToDoItem item = itemTable.getItems().get(selectionIndex);

        //Get the information from the selected row
        String name = item.getItemName();
        String details = item.getItemDetails();
        LocalDate dueDate = item.getItemDueDate();
        Boolean status = item.getCompletionStatusBoolean();

        //Set the information entry boxes to display the relevant information
        itemNameBox.setText(name);
        itemDetailBox.setText(details);
        itemDateBox.setValue(dueDate);
        completionStatusBtn.setSelected(status);
        errorLabel.setText(" ");
    }

    private boolean validateTextSize(int textSize){
        return textSize < 1 || textSize > 256;
    }

    @FXML
    private void addItemToList(ActionEvent event) {
        String name;
        LocalDate date = null;
        String details;
        boolean isCompleted;

        //Save name if it is valid
        if (validateTextSize(itemNameBox.getText().length())) {
            errorLabel.setText("Please enter a valid name for the item. ");
            return;
        } else {
            name = itemNameBox.getText();
        }

        //Save details if they are valid
        if (validateTextSize(itemDetailBox.getText().length())) {
            errorLabel.setText("Please enter valid details for the item. ");
            return;
        }
        else {
            details = itemDetailBox.getText();
        }

        //Save date if it is valid
        if (itemDateBox.getValue() != null){
            date = itemDateBox.getValue();
        }


        //Get completion status
        isCompleted = completionStatusBtn.isSelected();

        //Create ToDoItem
        ToDoItem item = new ToDoItem(name, date, details, isCompleted);
        //Add item to lists
        viewedItems.add(item);
        allItems.add(item);
        //Reset information entry fields
        resetInformationEntryFields();
    }

    @FXML
    private void editItemFromList(ActionEvent event) {
        //Get the index of the selected row
        int selectionIndex = itemTable.getSelectionModel().getSelectedIndex();

        //If no row is selected, end method
        if (selectionIndex == -1) {
            errorLabel.setText("Please select an item to edit");
            return;
        }

        //Retrieve ToDoItem
        ToDoItem tempItem = itemTable.getItems().get(selectionIndex);

        //Retrieve the index of the item from the total list
        int itemIDIndex = getIndexOfID(tempItem.getItemID());

        String name;
        LocalDate date = null;
        String details;
        boolean isCompleted;

        //Save name if it is valid
        if (validateTextSize(itemNameBox.getText().length())) {
            errorLabel.setText("Please enter a valid name for the item. ");
            return;
        } else {
            name = itemNameBox.getText();
        }

        //Save details if they are valid
        if (validateTextSize(itemDetailBox.getText().length())) {
            errorLabel.setText("Please enter valid details for the item. ");
            return;
        }
        else {
            details = itemDetailBox.getText();
        }

        //Save date if it is valid
        if (itemDateBox.getValue() != null){
            date = itemDateBox.getValue();
        }

        //Get completion status
        isCompleted = completionStatusBtn.isSelected();

        //Create new ToDoItem
        ToDoItem item = new ToDoItem(name, date, details, isCompleted);

        //Remove duplicate ToDoItem
        viewedItems.remove(selectionIndex);
        allItems.remove(itemIDIndex);
        //Instantiate edited item
        viewedItems.add(item);
        allItems.add(item);
        //Reset information entry
        resetInformationEntryFields();
    }

    @FXML
    private void deleteItemFromList(ActionEvent event) {
        //Get the index of the selected row
        int selectionIndex = itemTable.getSelectionModel().getSelectedIndex();

        //Retrieve the selected item
        ToDoItem tempItem = itemTable.getItems().get(selectionIndex);
        //Retrieve the index of the item from the total list
        int itemIDIndex = getIndexOfID(tempItem.getItemID());

        //Remove the item from the observable list and total list
        viewedItems.remove(selectionIndex);
        allItems.remove(itemIDIndex);
    }

    @FXML
    void clearList(ActionEvent event) {
        //Clear the items from all lists and table
        itemTable.getItems().clear();
        allItems.clear();
        viewedItems.clear();
    }

    private int getIndexOfID(int id) {
        //Loop through items and retrieve index of matching ID
        for(int i = 0; i < allItems.size(); i++) {
            if(allItems.get(i).getItemID() == id){
                return i;
            }
        }
        //Return invalid counter if item is not found
        return -1;
    }

    private void resetInformationEntryFields() {
        //Reset all item information entry fields to their default values
        itemNameBox.clear();
        itemDateBox.getEditor().clear();
        itemDateBox.setValue(null);
        itemDetailBox.clear();
        completionStatusBtn.setSelected(false);
        errorLabel.setText(" ");
    }
}

