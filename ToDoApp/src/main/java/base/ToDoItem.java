/*
 *  UCF COP3330 Fall 2021 Application Assignment 1 Solution
 *  Copyright 2021 Rylan Simpson
 */

package base;

import javafx.beans.property.SimpleStringProperty;
import javafx.scene.control.CheckBox;
import java.time.LocalDate;
import java.util.Random;

public class ToDoItem {
    Random rand = new Random();

    //Attributes
    private final int itemID;
    private final SimpleStringProperty itemName;
    private final LocalDate itemDueDate;
    private final SimpleStringProperty itemDetails;
    private final CheckBox completionStatus;

    //Constructors methods
    public ToDoItem(String name, LocalDate date, String details) {
        //Set instance details to passed arguments
        itemName = new SimpleStringProperty(name);
        itemDueDate = date;
        itemDetails = new SimpleStringProperty(details);
        completionStatus = new CheckBox();
        completionStatus.setSelected(false);
        //Generate a random ID
        itemID = rand.nextInt();
    }
    public ToDoItem(String name, LocalDate date, String details, boolean selection) {
        //Set instance details to passed arguments
        itemName = new SimpleStringProperty(name);
        itemDueDate = date;
        itemDetails = new SimpleStringProperty(details);
        completionStatus = new CheckBox();
        completionStatus.setSelected(selection);
        //Generate a random ID
        itemID = rand.nextInt();
    }

    public ToDoItem(String name, LocalDate date, String details, boolean selection, int id) {
        //Set instance details to passed arguments
        itemName = new SimpleStringProperty(name);
        itemDueDate = date;
        itemDetails = new SimpleStringProperty(details);
        completionStatus = new CheckBox();
        completionStatus.setSelected(selection);
        itemID = id;
    }

    //Getter methods

    public String getItemName(){
        return itemName.get();
    }


    public LocalDate getItemDueDate(){
        return itemDueDate;
    }

    public String getItemDueDateString() {
        return itemDueDate.toString();
    }

    public String getItemDetails(){
        return itemDetails.get();
    }

    //This getter is used by the checkbox cell factory
    public CheckBox getCompletionStatus(){
        return completionStatus;
    }

    public Boolean getCompletionStatusBoolean(){
        return completionStatus.isSelected();
    }


    public int getItemID() {
        return itemID;
    }
}

//Testing version of ToDoItem that does not contain a checkbox item
class ToDoItemTest {
    Random rand = new Random();

    //Attributes
    private final int itemID;
    private final SimpleStringProperty itemName;
    private final LocalDate itemDueDate;
    private final SimpleStringProperty itemDetails;
    private final boolean completionStatus;

    //Constructors method

    public ToDoItemTest(String name, LocalDate date, String details, boolean selection) {
        //Set instance details to passed arguments
        itemName = new SimpleStringProperty(name);
        itemDueDate = date;
        itemDetails = new SimpleStringProperty(details);
        completionStatus = selection;
        //Generate a random ID
        itemID = rand.nextInt();
    }

    //Getter methods

    public String getItemName(){
        return itemName.get();
    }

    public LocalDate getItemDueDate(){
        return itemDueDate;
    }

    public String getItemDueDateString() {
        return itemDueDate.toString();
    }

    public String getItemDetails(){
        return itemDetails.get();
    }

    public Boolean getCompletionStatusBoolean(){
        return completionStatus;
    }

    public int getItemID() {
        return itemID;
    }
}

