
package sample;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class ErrorHandlingController {
    @FXML
    Label title;
    @FXML
    Button close;
    @FXML
    Button retry;

    public ErrorHandlingController() {
    }

    private void errorHandler() {
        try{
            //try run software here
        }
        catch(Exception e){
            //if there are any errors, rather than execute show the message box
        }
    }

    private void showErrorBox() {
    }

    @FXML
    private void closeErrorBox() {
        Stage stage = (Stage)this.close.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void retry() {
    }
}
