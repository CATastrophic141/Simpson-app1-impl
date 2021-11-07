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

    @FXML
    private void saveList(ActionEvent event) {
        String filePath;
        //Open save window
        try {
            FileChooser fileChooser = new FileChooser();
            fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Text File", ".txt"));
            File file = fileChooser.showSaveDialog(null);
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
        PrintStream fileOut = new PrintStream(filePath);
        System.setOut(fileOut);
        System.out.printf("%d%n%n", allItems.size());
        for (ToDoItem item : allItems) {
            System.out.printf("%d%n", item.getItemID());
            System.out.printf("%s%n", item.getItemName());
            System.out.printf("%s%n", item.getItemDueDateString());
            System.out.printf("%s%n", item.getItemDetails());
            System.out.print(item.getCompletionStatusBoolean());
            System.out.printf("%n%n");
        }
    }

    @FXML
    private MenuItem uploadBtn;

    @FXML
    private void uploadList(ActionEvent event) {
        String filePath;
        //Open upload window
        try {
            FileChooser fileChooser = new FileChooser();
            File file = fileChooser.showOpenDialog(null);
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
            System.out.printf("%n");
            int numItems = fileIn.nextInt();
            System.out.printf("File found, num items saved: %d %n", numItems);
            int id;
            String name;
            String details;
            LocalDate dueDate;
            boolean isComplete;
            //for the number of items
            for (int  i = 0; i< numItems; i++) {
                id = fileIn.nextInt();
                System.out.printf("id saved: %d %n", id);
                fileIn.next();
                name = fileIn.nextLine();
                System.out.printf("name saved: %s %n", name);
                dueDate = LocalDate.parse(fileIn.nextLine(), DateTimeFormatter.ofPattern("yyyy-MM-dd"));
                System.out.printf("date saved: %s %n", dueDate);
                details = fileIn.nextLine();
                System.out.printf("details saved: %s %n", details);
                isComplete = fileIn.nextBoolean();
                System.out.printf("status saved: %b %n", isComplete);
                ToDoItem item = new ToDoItem(name, dueDate, details, isComplete, id);

                allItems.add(item);
                viewedItems.add(item);
            }
        } catch (Exception e){
            //Print error message
            System.out.printf("%nError parsing file%n");
        }
        itemTable.refresh();
    }

    @FXML
    private Button showCompletedBtn;

    @FXML
    private void setShowCompleted(ActionEvent event){
        viewedItems.clear();
        for (ToDoItem item : allItems) {
            if (Boolean.TRUE.equals(item.getCompletionStatusBoolean())) {
                viewedItems.add(item);
            }
        }
        itemTable.refresh();
    }

    @FXML
    private Button showIncompleteBtn;

    @FXML
    private void setShowIncomplete(ActionEvent event){
        viewedItems.clear();
        for (ToDoItem item : allItems) {
            if (Boolean.FALSE.equals(item.getCompletionStatusBoolean())) {
                viewedItems.add(item);
            }
        }
        itemTable.refresh();
    }

    @FXML
    private Button showAllBtn;

    @FXML
    private void setShowAll(ActionEvent event){
        viewedItems.clear();
        viewedItems.addAll(allItems);
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

        //Alter date picker display format
        final String pattern = "yyyy-MM-dd";
        StringConverter<LocalDate> converter = new StringConverter<>() {
            final DateTimeFormatter dateFormatter =
                    DateTimeFormatter.ofPattern(pattern);
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

        ToDoItem exampleItem = new ToDoItem("Example", LocalDate.of(2000, Month.JANUARY, 1), "This is an example item");

        viewedItems.add(exampleItem);
        allItems.add(exampleItem);
        itemTable.setItems(getViewedItemList());
    }

    public ObservableList<ToDoItem> getViewedItemList() {
        return viewedItems;
    }

    @FXML
    public void selectItemFromList(ActionEvent event) {
        int selectionIndex = itemTable.getSelectionModel().getSelectedIndex();

        if (selectionIndex == -1) {
            errorLabel.setText("Please select an item to edit");
            return;
        }

        ToDoItem item = itemTable.getItems().get(selectionIndex);

        String name = item.getItemName();
        String details = item.getItemDetails();
        LocalDate dueDate = item.getItemDueDate();
        Boolean status = item.getCompletionStatusBoolean();

        itemNameBox.setText(name);
        itemDetailBox.setText(details);
        itemDateBox.setValue(dueDate);
        completionStatusBtn.setSelected(status);
        errorLabel.setText(" ");
    }

    @FXML
    public void addItemToList(ActionEvent event) {
        String name;
        LocalDate date = null;
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

        //Save date if it is valid
        if (itemDateBox.getValue() != null){
            date = itemDateBox.getValue();
        }


        //Get completion status
        isCompleted = completionStatusBtn.isSelected();

        ToDoItem item = new ToDoItem(name, date, details, isCompleted);
        viewedItems.add(item);
        allItems.add(item);
        itemNameBox.clear();
        itemDateBox.getEditor().clear();
        itemDateBox.setValue(null);
        itemDetailBox.clear();
        completionStatusBtn.setSelected(false);
        errorLabel.setText(" ");
    }

    @FXML
    public void editItemFromList(ActionEvent event) {

        int selectionIndex = itemTable.getSelectionModel().getSelectedIndex();

        if (selectionIndex == -1) {
            errorLabel.setText("Please select an item to edit");
            return;
        }

        ToDoItem tempItem = itemTable.getItems().get(selectionIndex);

        int itemIDIndex = getIndexOfID(tempItem.getItemID());

        String name;
        LocalDate date = null;
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

        //Save date if it is valid
        if (itemDateBox.getValue() != null){
            date = itemDateBox.getValue();
        }

        //Get completion status
        isCompleted = completionStatusBtn.isSelected();

        ToDoItem item = new ToDoItem(name, date, details, isCompleted);

        viewedItems.remove(selectionIndex);
        allItems.remove(itemIDIndex);
        viewedItems.add(item);
        allItems.add(item);
        itemNameBox.clear();
        itemDateBox.getEditor().clear();
        itemDateBox.setValue(null);
        itemDetailBox.clear();
        completionStatusBtn.setSelected(false);
        errorLabel.setText(" ");
    }

    @FXML
    public void deleteItemFromList(ActionEvent event) {
        int selectionIndex = itemTable.getSelectionModel().getSelectedIndex();

        ToDoItem tempItem = itemTable.getItems().get(selectionIndex);
        int itemIDIndex = getIndexOfID(tempItem.getItemID());

        viewedItems.remove(selectionIndex);
        allItems.remove(itemIDIndex);
        errorLabel.setText(" ");
    }

    @FXML
    void clearList(ActionEvent event) {
        itemTable.getItems().clear();
        allItems.clear();
        viewedItems.clear();
        errorLabel.setText(" ");
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
}

