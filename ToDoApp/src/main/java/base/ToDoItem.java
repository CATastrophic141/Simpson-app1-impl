package base;

class ToDoItem {
     String itemName;
     String itemDueDate;
     String itemDetails;
    boolean isCompleteStatus;

    public ToDoItem(String name, String date, String details) {
        //Set instance details to passed arguments
        itemName = name;
        itemDueDate = date;
        itemDetails = details;
        isCompleteStatus = false;
    }

    public void setCompleteStatus(){
        //Set item complete status to true
        isCompleteStatus = !isCompleteStatus;
    }
}

