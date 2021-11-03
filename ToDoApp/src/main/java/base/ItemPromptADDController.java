package base;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

//Use extension to access data
public class ItemPromptADDController{
    @FXML
    private Button itemPromptOkADDBtn;

    @FXML
    private TextField itemNameTextBoxADD;

    @FXML
    private DatePicker itemDateBoxADD;

    @FXML
    private TextField itemDetailsTextBoxADD;

    @FXML
    private Label errorMsgDateADD;

    @FXML
    private void createItem(ActionEvent event) throws IOException {
//        Stage stage = new Stage()
//        stage.initModality(Modality.APPLICATION_MODAL)
//        stage.setTitle("Add To-Do Item")

        String dateRegex = "^(d{4})-(0?[1-9]|1[012])-(0?[1-9]|[12][0-9]|3[01])$";
        String dateEntry;
        String detailEntry;
        //On "ok" button press, retrieve strings from the fields and check boolean value

        //Control date setting
        if (itemDateBoxADD.getValue().toString().equals(dateRegex)){
            errorMsgDateADD.setText("Please enter a valid date. Try using the calendar picker");
            return;
        }
        else if (itemDateBoxADD.getValue().toString().equals("")) {
            dateEntry = "No Date";
        }
        else {
            dateEntry = itemDateBoxADD.getValue().toString();
        }

        //Control detail setting
        if (itemDetailsTextBoxADD.getText().trim().isEmpty()){
            errorMsgDateADD.setText("Please enter details");
            return;
        }
        else {
            detailEntry = itemDetailsTextBoxADD.getText().trim();
        }
        //Create new ToDoItem
        ToDoItem item = new ToDoItem(dateEntry, detailEntry);

        //Get main window controller
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("Scene.fxml"));
        fxmlLoader.load();
        MainWindowController mainController = fxmlLoader.getController();

        //Add item to main controller data
        mainController.addItem(item);

        //Close window
        Stage stage = (Stage) itemPromptOkADDBtn.getScene().getWindow();
        stage.close();
    }
}
