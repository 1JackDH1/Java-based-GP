package uk.ac.aber.ExerciseApp.fxcontroller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.control.ToggleButton;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.TextAlignment;
import uk.ac.aber.ExerciseApp.Session;

import java.io.IOException;
import java.time.format.DateTimeFormatter;

/**
 *This class handles the session fmxl file
 *
 * @authors REDACTED 
 *
 * @version 1.0
 */
public class SessionController {
    private Parent parent;

    private Label description;

    @FXML
    private VBox root;
    @FXML
    private GridPane grid;
    @FXML
    private Label date, exercises, duration;
    @FXML
    private ToggleButton toggleButton;

    /**
     * This is a parameterised constructor for the session controller class which gets initializes the session page
     * and uses a session object to get the the workout length times and the time that the workout was completed.
     * @param session is the session of the workout that was completed
     */
    public SessionController(Session session) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/uk/ac/aber/ExerciseApp/fxml/session.fxml"));
            loader.setController(this);
            parent = loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        date.setText(session.getDateTime().format(formatter));
        exercises.setText(session.getExercises().length + " Exercises");
        duration.setText(formatTime(session.getWorkout().getDuration()));

        description = new Label(
                session.getExercises().length +
                        " exercises of " +
                        formatTime(session.getWorkout().getExerciseTime()) +
                        ", with " +
                        formatTime(session.getWorkout().getBreakTime()) +
                        " between exercises and a " +
                        formatTime(session.getWorkout().getHalftimeBreak()) +
                        " pause in the middle."
        );
        description.setWrapText(true);
        description.setAlignment(Pos.CENTER);
        description.setTextAlignment(TextAlignment.CENTER);
        description.prefWidthProperty().bind(root.widthProperty());
        description.getStyleClass().add("border");
    }

    /**
     * This method gets the parent of the controller
     * @return the parent
     */
    public Parent getParent() {
        return parent;
    }

    /**
     * This method takes a time in seconds and re-formats is to include minutes and seconds as a string
     * @param s the time in seconds
     * @return the string of the formatted time
     */
    private String formatTime(int s) {
        if (s / 60 == 0) {
            return s + "s";
        } else if (s % 60 == 0) {
            return s / 60 + "m";
        } else {
            return s / 60 + "m " + s % 60 + "s";
        }
    }

    /**
     * Handles the dropdown button to display more information such as the time
     * about a workout session in the session page
     */
    @FXML
    private void toggleDescription() {
        if (toggleButton.isSelected()) {
            toggleButton.setText("▲");
            root.getChildren().add(description);
        } else {
            toggleButton.setText("▼");
            root.getChildren().remove(description);
        }
    }

}
