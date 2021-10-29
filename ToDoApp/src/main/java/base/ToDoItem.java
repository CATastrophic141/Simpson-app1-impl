package base;

import java.util.ArrayList;

class ToDoItem {
    private String itemName;
    private String itemDueDate;
    private String itemDetails;
    boolean isCompleteStatus;

    public ToDoItem(String name, String date, String details) {
        //Set instance details to passed arguments
    }

    public void setItemName(String name){
        //Set item name
    }

    public void setDueDate(String date){
        //Set due date
    }

    public void setDetails(String details){
        //Set item details
    }

    public String getItemName(){
        //Get item name
        return itemName;
    }

    public String getDueDate(){
        //Get due date
        return itemDueDate;
    }

    public String getItemDetails(){
        //Get item name
        return itemDetails;
    }

    public void setCompleteStatus(){
        //Set item complete status to true
    }
}

