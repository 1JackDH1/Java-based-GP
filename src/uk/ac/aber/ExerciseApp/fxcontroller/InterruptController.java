package uk.ac.aber.ExerciseApp.fxcontroller;

import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * A class to handle all errors for the user
 *
 * @authors REDACTED 

 * @version 1.1
 */
public class InterruptController {
    private Parent parent;
    private Stage stage;

    /**
     * Width of stage
     */
    private static final int WIDTH = 600;

    /**
     * Height of stage
     */
    private static final int HEIGHT = 200;

    @FXML
    private AnchorPane root;
    @FXML
    private Label messageLabel;

    /**
     * Initialises the interruptController object
     *
     * @throws IOException If the FXMLLoader fails to load the interrupt.fxml file
     */
    public InterruptController() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/uk/ac/aber/ExerciseApp/fxml/interrupt.fxml"));
        loader.setController(this);
        parent = loader.load();
        stage = new Stage();
        stage.setTitle("Interrupt Message");
        stage.setAlwaysOnTop(true);
        stage.setResizable(false);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setOnCloseRequest(Event::consume);
        stage.setScene(new Scene(parent, WIDTH, HEIGHT));
    }

    /**
     * Display interrupt message
     *
     * @param message Message to display
     */
    public void interrupt(String message) {
        messageLabel.setText(message);
        stage.showAndWait();
    }

    /**
     * End interrupt
     */
    @FXML
    private void endInterrupt() {
        stage.close();
    }
}
