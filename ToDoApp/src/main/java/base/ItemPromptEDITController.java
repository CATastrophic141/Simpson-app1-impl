package base;

import javafx.fxml.FXML;
import javafx.scene.control.*;

//Use extension to access data
public class ItemPromptEDITController extends MainWindowController{
    @FXML
    private Button itemPromptOkEDITBtn;

    @FXML
    private CheckBox isCompleteEDITBtn;

    @FXML
    private DatePicker itemDateBoxEDIT;

    @FXML
    private TextField itemDetailsTextBoxEDIT;

    @FXML
    private TextField itemNameTextBoxEDIT;

    @FXML
    private Label errorMsgDateEDIT;

    @FXML
    private void editItem(){
        //On "ok" button press, retrieve strings from the fields and check boolean value
        String nameEntry;
        String dateEntry;
        String detailEntry;
        //Change values of itemEntry
        //If isComplete is selected update boolean value
        //Close window
    }
}
