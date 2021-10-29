package base;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

//Use extension to access data
public class ItemPromptADDController extends MainWindowController{
    @FXML
    private Button itemPromptOkADDBtn;

    @FXML
    private DatePicker itemDateBoxADD;

    @FXML
    private TextField itemDetailsTextBoxADD;

    @FXML
    private TextField itemNameTextBoxADD;

    @FXML
    private Label errorMsgDateADD;

    @FXML
    private void createItem(){
        //On "ok" button press, retrieve strings from the fields and check boolean value
        String nameEntry = "";
        String dateEntry = "";
        String detailEntry = "";
        //Create new ToDoItem
        ToDoItem item = new ToDoItem(nameEntry, dateEntry, detailEntry);
        //Add item to arraylist of items in to do list
        //Close window
    }
}
