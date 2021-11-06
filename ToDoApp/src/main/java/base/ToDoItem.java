package base;

import javafx.beans.property.SimpleStringProperty;
import javafx.scene.control.CheckBox;

import java.time.LocalDate;
import java.util.Random;

public class ToDoItem {
    Random rand = new Random();

    private int itemID;
    private SimpleStringProperty itemName;
    private LocalDate itemDueDate;
    private SimpleStringProperty itemDetails;
    private CheckBox completionStatus;

    public ToDoItem(String name, LocalDate date, String details) {
        //Set instance details to passed arguments
        itemName = new SimpleStringProperty(name);
        itemDueDate = date;
        itemDetails = new SimpleStringProperty(details);
        completionStatus = new CheckBox();
        completionStatus.setSelected(false);
        itemID = rand.nextInt();
    }
    public ToDoItem(String name, LocalDate date, String details, boolean selection) {
        //Set instance details to passed arguments
        itemName = new SimpleStringProperty(name);
        itemDueDate = date;
        itemDetails = new SimpleStringProperty(details);
        completionStatus = new CheckBox();
        completionStatus.setSelected(selection);
        itemID = rand.nextInt();
    }

    public String getItemName(){
        return itemName.get();
    }

    public void setItemName(String name) {
        itemName.set(name);
    }

    public LocalDate getItemDueDate(){
        return itemDueDate;
    }

    public String getItemDueDateString() {
        return itemDueDate.toString();
    }

    public void setItemDueDate(LocalDate date) {
        itemDueDate = date;
    }

    public String getItemDetails(){
        return itemDetails.get();
    }

    public void setItemDetails(String details) {
        itemDetails.set(details);
    }

    public CheckBox getCompletionStatus(){
        return completionStatus;
    }

    public Boolean getCompletionStatusBoolean(){
        return completionStatus.isSelected();
    }

    public void setCompletionStatus(CheckBox selection) {
        completionStatus = selection;
    }

    public int getItemID() {
        return itemID;
    }
}

