package base;

class ToDoItem {
    private String itemDueDate;
    private String itemDetails;
    boolean isCompleteStatus;

    public ToDoItem(String date, String details) {
        //Set instance details to passed arguments
        itemDueDate = date;
        itemDetails = details;
        isCompleteStatus = false;
    }

    public void setDueDate(String date){
        //Set due date
        itemDueDate = date;
    }

    public void setDetails(String details){
        //Set item details
        itemDetails = details;
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
        isCompleteStatus = !isCompleteStatus;
    }
}

