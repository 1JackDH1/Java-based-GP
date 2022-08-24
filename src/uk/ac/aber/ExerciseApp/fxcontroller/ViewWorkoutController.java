package uk.ac.aber.ExerciseApp.fxcontroller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.ToggleButton;
import javafx.scene.layout.*;
import uk.ac.aber.ExerciseApp.ExerciseApp;
import uk.ac.aber.ExerciseApp.Workout;

import java.net.URL;
import java.util.Collections;
import java.util.Comparator;
import java.util.ResourceBundle;

/**
 *This class handles the view workout fxml file
 *
 * @authors REDACTED 
 *
 * @version 1.0
 */

public class ViewWorkoutController implements Initializable {
    private ExerciseApp app;
    private Parent parent;

    private Comparator<Workout>[] comparators;

    @FXML
    private HBox root;
    @FXML
    private VBox left;
    @FXML
    private ScrollPane center;
    @FXML
    private GridPane grid;
    @FXML
    private ChoiceBox<String> sortByBox;
    @FXML
    private ToggleButton orderByToggle;
    @FXML
    private Button createWorkoutBtn;
    @FXML
    private Button historyBtn;
    @FXML
    private Button saveBtn;
    @FXML
    private Button aboutBtn;

    /**
     * This method initializes the controller and scales the window and its components
     * It preserves the size ratio of the window and its components when resizing the window
     * @param location pathname location used to solve relative paths for the root object,
     *                 or null if the location is not known
     * @param resources resources used to localize the root object, or null if the root object
     *                  was not localized
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        root.widthProperty().addListener(event -> scale());
        root.heightProperty().addListener(event -> scale());

        left.spacingProperty().bind(root.heightProperty().multiply(20).divide(720));
        grid.prefWidthProperty().bind(center.widthProperty());
        grid.hgapProperty().bind(center.widthProperty().add(center.heightProperty()).multiply(0.02));
        grid.vgapProperty().bind(center.widthProperty().add(center.heightProperty()).multiply(0.02));

        sortByBox.prefWidthProperty().bind(root.widthProperty().multiply(220).divide(1280));
        orderByToggle.prefWidthProperty().bind(root.widthProperty().multiply(180).divide(1280));
        createWorkoutBtn.prefWidthProperty().bind(root.widthProperty().multiply(160).divide(1280));
        historyBtn.prefWidthProperty().bind(root.widthProperty().multiply(160).divide(1280));
        saveBtn.prefWidthProperty().bind(root.widthProperty().multiply(160).divide(1280));
        aboutBtn.prefWidthProperty().bind(root.widthProperty().multiply(160).divide(1280));

        sortByBox.getItems().addAll("Exercises",
                "Exercise time",
                "Break time",
                "Halftime break",
                "Total time");
        resetToDefault();
        sortByBox.setOnAction(actionEvent -> setScene());

        comparators = new Comparator[sortByBox.getItems().size()];
        comparators[0] = Comparator.comparingInt(Workout::getExercises);
        comparators[1] = Comparator.comparingInt(Workout::getExerciseTime);
        comparators[2] = Comparator.comparingInt(Workout::getBreakTime);
        comparators[3] = Comparator.comparingInt(Workout::getHalftimeBreak);
        comparators[4] = Comparator.comparingInt(Workout::getDuration);
    }

    /**
     * This method sets the sort by box as 'Total time'
     * and it resets the toggle button as false
     */
    public void resetToDefault() {
        sortByBox.setValue("Total time");
        orderByToggle.setSelected(false);
    }

    /**
     * This method sorts the workout either by
     * the number of exercises, time for each, break times, halftime break
     * This method also allows these categories to be ordered in
     * ascending or descending order once the toggle button is clicked
     *
     */
    public void setScene() {
        grid.getChildren().clear();
        app.getDataManager().getWorkouts().sort(comparators[sortByBox.getItems().indexOf(sortByBox.getValue())]);
        if (orderByToggle.isSelected()) {
            Collections.reverse(app.getDataManager().getWorkouts());
        }
        int num = app.getDataManager().getWorkouts().size();

        int rows = num / 3 + 1;

        int row = 0;
        int col = 0;

        for (Workout workout: app.getDataManager().getWorkouts()) {
            WorkoutController workoutController = new WorkoutController(workout);
            grid.add(workoutController.getParent(),  col++ + 1, row + 1);
            if (col == 3) {
                row++;
            }
            col %= 3;

            workoutController.getParent().setOnMouseClicked(mouseEvent -> app.getUserInterface().showWorkoutPopup(workout));
        }

        grid.add(new Pane(), 1, rows + 1);
    }

    /**
     * This method scales its components such as the font
     */
    private void scale() {
        root.setStyle("-fx-font-size: " + 24 * ((root.getWidth() + root.getHeight()) / (1280 + 720)) + "px;");
        left.setPadding(new Insets(20 * ((root.getWidth() + root.getHeight()) / (1280 + 720))));
    }

    /**
     * This method sets the app of this controller
     * @param app exercise application
     */
    public void setApp(ExerciseApp app) {
        this.app = app;
    }

    /**
     * This method gets the parent of this controller
     * @return root node
     */
    public Parent getParent() {
        return parent;
    }

    /**
     * This method sets the parent of this controller
     * @param parent parent node
     */
    public void setParent(Parent parent) {
        this.parent = parent;
    }

    /**
     * This method checks if the toggle button is
     * clicked for the ascending/descending order
     */
    @FXML
    private void orderBy() {
        if (orderByToggle.isSelected()) {
            orderByToggle.setText("Descending");
        } else {
            orderByToggle.setText("Ascending");
        }
        setScene();
    }

    /**
     * This method displays the create workout page
     * where the user can input the number of exercises
     * and the times for them
     */
    @FXML
    private void createWorkout() {
        app.getUserInterface().showCreateWorkout();
    }

    /**
     * This method displays the history page with the
     * sessions completed with the graph
     */
    @FXML
    private void history() {
        app.getUserInterface().showHistory();
    }

    /**
     * This method saves the sessions completed by the user
     */
    @FXML
    private void save() {
        app.getDataManager().save();
    }

    /**
     * This method displays the about page which consist of the tools used with their version
     * plus the group members and the project manager and creator
     */
    @FXML
    private void about() {
        app.getUserInterface().showAbout();
    }
}
