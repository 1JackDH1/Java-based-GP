package uk.ac.aber.ExerciseApp.fxcontroller;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * This class handles about fmxl file
 *
 * @authors REDACTED 
 *
 * @version 1.0
 */
public class AboutController {
    private Parent parent;
    private Stage stage;

    /**
     * This is the width of the about pop-up window
     * The size cannot be changed
     */
    private static final int WIDTH = 640;

    /**
     * This is the height of the about pop-up window
     * The size cannot be changed
     */
    private static final int HEIGHT = 640;

    /**
     * This is the about controller constructor
     * It loads the about.fxml file with all of its components
     * @throws IOException error in reading the fxml file
     */
    public AboutController() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/uk/ac/aber/ExerciseApp/fxml/about.fxml"));
        loader.setController(this);
        parent = loader.load();
        stage = new Stage();
        stage.setResizable(false);
        stage.setTitle("About Exercise with Chris");
        stage.setScene(new Scene(parent, WIDTH, HEIGHT));
    }

    /**
     * This method gets the parent of this class, the parent handles all of the hierarchal scene graph operations
     * @return parent
     */
    public Parent getParent() {
        return parent;
    }

    /**
     * This method displays the window
     */
    public void show() {
        stage.show();
    }
}

