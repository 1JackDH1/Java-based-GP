package uk.ac.aber.fxcontroller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import uk.ac.aber.ExerciseApp;

import java.net.URL;
import java.util.ResourceBundle;

public class ExerciseController implements Initializable {
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
    private void cancel() {
        app.getUserInterface().showViewSets();
    }

    @FXML
    private void pause() {

    }
}
