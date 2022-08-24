package uk.ac.aber.fxcontroller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import uk.ac.aber.ExerciseApp;
import uk.ac.aber.UserInterface;

import java.net.URL;
import java.util.ResourceBundle;

public class HistoryController implements Initializable {
    private ExerciseApp app;
    private Parent parent;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void setScene() {

    }

    private void scale() {

    }

    public void setApp(ExerciseApp app) {
        this.app = app;
    }

    public Parent getParent() {
        return parent;
    }

    public void setParent(Parent parent) {
        this.parent = parent;
    }

    @FXML
    private void back() {
        app.getUserInterface().showViewSets();
    }

    @FXML
    private void viewGraph() {

    }
}
