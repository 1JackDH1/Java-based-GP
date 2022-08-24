package uk.ac.aber.ExerciseApp.fxcontroller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import uk.ac.aber.ExerciseApp.ExerciseApp;
import uk.ac.aber.ExerciseApp.Workout;

import java.net.URL;
import java.util.Arrays;
import java.util.ResourceBundle;

/**
 * This class handles the createWorkout fmxl file
 *
 * @authors REDACTED 

 * @version 1.0
 */
public class CreateWorkoutController implements Initializable {
    private ExerciseApp app;
    private Parent parent;

    @FXML
    private HBox root;
    @FXML
    private VBox left;
    @FXML
    private GridPane grid;
    @FXML
    private GridPane sGrid;
    @FXML
    private GridPane eGrid;
    @FXML
    private GridPane bGrid;
    @FXML
    private GridPane htGrid;
    @FXML
    private VBox vBox;
    @FXML
    private HBox hBox;
    @FXML
    private Spinner exerciseNum;
    @FXML
    private Spinner exerciseTimeMin;
    @FXML
    private Spinner exerciseTimeSec;
    @FXML
    private Spinner exerciseBreakMin;
    @FXML
    private Spinner exerciseBreakSec;
    @FXML
    private Spinner halftimeMin;
    @FXML
    private Spinner halftimeSec;
    @FXML
    private Button cancelBtn;
    @FXML
    private Button addBtn;
    @FXML
    private Button startBtn;

    /**
     * This method initializes the controller and scales the page and its components to fit the window.
     * the spinners that collect the workout time parameters are also initialized
     *
     * @param location  The location used to resolve relative paths for the root object, or null if the location is not known.
     * @param resources The resources used to localize the root object, or null if the root object was not localized.
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        root.widthProperty().addListener(event -> scale());
        root.heightProperty().addListener(event -> scale());

        grid.vgapProperty().bind(root.heightProperty().multiply(20).divide(1280));
        sGrid.hgapProperty().bind(root.widthProperty().multiply(15).divide(1280));

        eGrid.maxWidthProperty().bind(root.widthProperty().multiply(270).divide(1280));
        bGrid.maxWidthProperty().bind(root.widthProperty().multiply(270).divide(1280));
        htGrid.maxWidthProperty().bind(root.widthProperty().multiply(270).divide(1280));

        vBox.spacingProperty().bind(root.heightProperty().multiply(20).divide(1280));
        hBox.spacingProperty().bind(root.widthProperty().multiply(15).divide(1280));

        exerciseNum.maxWidthProperty().bind(eGrid.widthProperty());

        cancelBtn.prefWidthProperty().bind(root.widthProperty().multiply(160).divide(1280));
        addBtn.prefWidthProperty().bind(root.widthProperty().multiply(250).divide(1280));
        startBtn.prefWidthProperty().bind(root.widthProperty().multiply(250).divide(1280));

        setUpSpinner(exerciseNum, 1, 30);

        for (Spinner spinner : Arrays.asList(exerciseTimeMin, exerciseBreakMin, halftimeMin)) {
            setUpSpinner(spinner, 0, 299);
        }

        for (Spinner spinner : Arrays.asList(exerciseTimeSec, exerciseBreakSec, halftimeSec)) {
            setUpSpinner(spinner, 0, 60);
        }
    }

    /**
     * This method sets the spinners to their default empty values and the number of exercises field to 1
     */
    public void setScene() {
        exerciseNum.getValueFactory().setValue(1);

        for (Spinner spinner : Arrays.asList(exerciseTimeMin, exerciseTimeSec, exerciseBreakMin, exerciseBreakSec, halftimeMin, halftimeSec)) {
            spinner.getValueFactory().setValue(null);
            spinner.getEditor().setText("");
        }
    }

    /**
     * This method scales its page, padding and its components such as the font
     */
    private void scale() {
        root.setStyle("-fx-font-size: " + 24 * ((root.getWidth() + root.getHeight()) / (1280 + 720)) + "px;");
        left.setPadding(new Insets(20 * ((root.getWidth() + root.getHeight()) / (1280 + 720))));

        AnchorPane.setTopAnchor(grid, 100 * root.getHeight() / 720);
        AnchorPane.setRightAnchor(grid, 150 * root.getWidth() / 1280);
        AnchorPane.setBottomAnchor(grid, 100 * root.getHeight() / 720);
        AnchorPane.setLeftAnchor(grid, 150 * root.getWidth() / 1280);
    }

    /**
     * This method contains overridden methods to change the behavior of how the spinner is incremented and decremented
     * and sets the minimum and maximum values that any values in the spinner can be.
     * The method also handles the regex to make sure that only integers are able to be input into the spinners.
     *
     * @param spinner is the spinner object to be edited
     * @param min     is the minimum value the spinner can have
     * @param max     is the maximum value the spinner can have
     */
    private void setUpSpinner(Spinner spinner, int min, int max) {
        spinner.setValueFactory(new SpinnerValueFactory() {
            /**
             * This method allows the spinner to be decremented by i and wrap around if the value is less than the min
             * @param i is the value to be decremented by
             */
            @Override
            public void decrement(int i) {
                if (spinner.getEditor().getText().equals("")) {
                    spinner.getValueFactory().setValue(min);
                } else if ((int) spinner.getValue() <= min) {
                    spinner.getValueFactory().setValue(max);
                } else {
                    spinner.getValueFactory().setValue(Integer.parseInt(spinner.getEditor().getText()) - i);
                }
            }

            /**
             * This method allows the spinner to be incremented by i and wrap around if the value is greater than the max
             * @param i is the value to be decremented by
             */
            @Override
            public void increment(int i) {
                if (spinner.getEditor().getText().equals("")) {
                    spinner.getValueFactory().setValue(min);
                } else if ((int) spinner.getValue() >= max) {
                    spinner.getValueFactory().setValue(min);
                } else {
                    spinner.getValueFactory().setValue(Integer.parseInt(spinner.getEditor().getText()) + i);
                }
            }
        });
        spinner.getEditor().textProperty().addListener((observableValue, oldValue, newValue) -> {
            if (!newValue.matches("\\d*")) {
                spinner.getEditor().setText(newValue.replaceAll("[^\\d]", ""));
            }
            spinner.getValueFactory().setValue(Integer.parseInt(spinner.getEditor().getText()));
        });
    }

    /**
     * This method sets the app for the controller
     *
     * @param app is the new exercise app
     */
    public void setApp(ExerciseApp app) {
        this.app = app;
    }

    /**
     * This method gets the parent of the controller
     *
     * @return the parent
     */
    public Parent getParent() {
        return parent;
    }

    /**
     * This method sets the parent of the controller
     *
     * @param parent is the new parent
     */
    public void setParent(Parent parent) {
        this.parent = parent;
    }

    /**
     * THis method creates a workout and combines the values from each set of the minutes and seconds
     * for each time to add them to the workout in seconds. the method then gets added.
     * The methods also handles the fields being empty or illegal by throwing an error message
     *
     * @return the new created workout
     */
    private Workout createWorkout() {
        try {
            Workout workout = new Workout(
                    (int) exerciseNum.getValue(),
                    (int) exerciseTimeMin.getValue() * 60 + (int) exerciseTimeSec.getValue(),
                    (int) exerciseBreakMin.getValue() * 60 + (int) exerciseBreakSec.getValue(),
                    (int) halftimeMin.getValue() * 60 + (int) halftimeSec.getValue());
            app.getDataManager().addWorkout(workout);
            return workout;
        } catch (NullPointerException e) {
            app.getUserInterface().getInterrupt().interrupt("Please make sure none of the input fields are blank!");
        } catch (IllegalArgumentException e) {
            app.getUserInterface().getInterrupt().interrupt(e.getMessage());
        }
        return null;
    }

    /**
     * This method cancels the workout by returning the user to the view workouts page
     */
    @FXML
    private void cancel() {
        app.getUserInterface().showViewWorkouts();
    }

    /**
     * This method creates a workout from the existing field and returns the user to the view workouts page
     */
    @FXML
    private void createAdd() {
        Workout workout = createWorkout();
        if (workout != null) {
            app.getUserInterface().showViewWorkouts();
        }
    }

    /**
     * This method creates a workout from the existing field and starts the newly created workout in the exercise page
     */
    @FXML
    private void createStart() {
        Workout workout = createWorkout();
        if (workout != null) {
            app.getUserInterface().showExercise(app.startSession(workout));
        }
    }
}
