package base;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

//Use extension to access data
public class SavePromptController extends MainWindowController{
    @FXML
    private TextField savePath;

    @FXML
    private TextField fileNameText;

    @FXML
    private Button fileSaveOkBtn;

    @FXML
    private void saveList(){
        //On ok button press
        //Save string from file name text box
        String fileName;
        //Save string from file path text box
        String filePath;
        //Create file
        //Set output to file
        //Save number of lists
            //For every list
                //Save list name
                //Save number of items
                //For every item
                //Save item name
                //Save due date
                //Save details
                //Save completion status
        //Close window
    }
}
