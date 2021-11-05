package base;

import javafx.beans.property.SimpleStringProperty;
import javafx.scene.control.CheckBox;
import java.time.LocalDate;

public class ToDoItem {
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
    }
    public ToDoItem(String name, LocalDate date, String details, boolean selection) {
        //Set instance details to passed arguments
        itemName = new SimpleStringProperty(name);
        itemDueDate = date;
        itemDetails = new SimpleStringProperty(details);
        completionStatus = new CheckBox();
        completionStatus.setSelected(selection);
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

    public String getItemDueDateAsString(){
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

    public void setCompletionStatus(CheckBox selection) {
        completionStatus = selection;
    }
}

