package uk.ac.aber.ExerciseApp.fxcontroller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import uk.ac.aber.ExerciseApp.Workout;

import java.io.IOException;

/**
 *This class handles the workout fxml file
 *
 * @authors REDACTED 
 *
 * @version 1.0
 */

public class WorkoutController {
    private Parent parent;

    @FXML
    private AnchorPane root;
    @FXML
    private Label exercises;
    @FXML
    private Label exerciseTime;
    @FXML
    private Label breakTime;
    @FXML
    private Label halftimeBreak;
    @FXML
    private Label totalTime;

    /**
     * This is a constructor of workout controller, it tries to load the 'workout.fxml' file
     */
    public WorkoutController() {
        loadFXML();
    }

    /**
     * This is a constructor of workout controller, it tries to load the 'workout.fxml' file
     * Then it sets the text for the number of exercises, time for each exercise,
     * breaks, halftime breaks, and the overall time of the workout
     * @param workout specified workout
     */
    public WorkoutController(Workout workout) {
        loadFXML();
        setWorkout(workout);
    }

    /**
     * This method tries to load the 'workout.fxml' file
     * If it catches an IOException, then it will print exception object and the line it occurs
     */
    private void loadFXML() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/uk/ac/aber/ExerciseApp/fxml/workout.fxml"));
            loader.setController(this);
            parent = loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        root.widthProperty().addListener(event -> scale());
        root.heightProperty().addListener(event -> scale());
    }

    /**
     * This method gets the parent of this controller
     * @return parent
     */
    public Parent getParent() {
        return parent;
    }

    /**
     * This method gets the anchor pane
     * @return root
     */
    public AnchorPane getRoot() {
        return root;
    }

    /**
     * This method scales the anchor pane
     */
    private void scale() {
        root.setPadding(new Insets(0.01 * (root.getWidth() + root.getHeight())));
    }

    /**
     * This method formats the time in minutes, seconds
     * @param s which is the seconds in integer
     * @return The seconds if the answer is 0 when divided by 60. The minutes if seconds has reached 60 otherwise, return the minutes and seconds
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
     * This method sets the text of the number of exercise
     * @param exercises number of exercises
     */
    private void setExercisesText(int exercises) {
        this.exercises.setText(Integer.toString(exercises));
    }

    /**
     * This method sets the text of the time for each exercise
     * The time is formatted using formatTime(s:int):String
     * which is a private method in this class
     * @param exerciseTime exercise time
     */
    private void setExerciseTimeText(int exerciseTime) {
        this.exerciseTime.setText(formatTime(exerciseTime));
    }

    /**
     * This method sets the text of the break time
     * The time is formatted using formatTime(s:int):String
     * which is a private method in this class
     * @param breakTime break time between exercises
     */
    private void setBreakTimeText(int breakTime) {
        this.breakTime.setText(formatTime(breakTime));
    }

    /**
     * This method sets the text of the halftime break
     * The time is formatted using formatTime(s:int):String
     * which is a private method in this class
     * @param halftimeBreak halftime break
     */
    private void setHalftimeBreakText(int halftimeBreak) {
        this.halftimeBreak.setText(formatTime(halftimeBreak));
    }

    /**
     * This method sets the text of the overall time
     * The time is formatted using formatTime(s:int):String
     * which is a private method in this class
     * @param totalTime total time duration
     */
    private void setTotalTimeText(int totalTime) {
        this.totalTime.setText(formatTime(totalTime));
    }

    /**
     * This method gets the values from workout and
     * sets all the text including the number
     * of exercises, time for each exercise, break time,
     * halftime break, and the overall time
     *
     * @param workout which consist of the number of exercises, time for each, breaks in between, and halftime break
     */
    public void setWorkout(Workout workout) {
        setExercisesText(workout.getExercises());
        setExerciseTimeText(workout.getExerciseTime());
        setBreakTimeText(workout.getBreakTime());
        setHalftimeBreakText(workout.getHalftimeBreak());
        setTotalTimeText(workout.getDuration());
    }
}
