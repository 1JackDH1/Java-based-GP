package uk.ac.aber.ExerciseApp.fxcontroller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import uk.ac.aber.ExerciseApp.ExerciseApp;
import uk.ac.aber.ExerciseApp.Workout;

import java.net.URL;
import java.util.ResourceBundle;

/**
 *This class handles the workout popup fxml file
 *
 * @authors REDACTED 
 *
 * @version 1.0
 */

public class WorkoutPopupController implements Initializable {
    private WorkoutController workoutController;
    private ExerciseApp app;
    private Parent parent;

    private Stage stage;

    /**
     * This is the width of the workout pop-up window
     * The size cannot be changed
     */
    private static final int WIDTH = 640;

    /**
     * This is the height of the workout pop-up window
     * The size cannot be changed
     */
    private static final int HEIGHT = 360;

    private Workout currentWorkout;

    @FXML
    private VBox root;
    @FXML
    private HBox topHBox, bottomHBox;
    @FXML
    private Button closeBtn, startBtn, deleteBtn, createNewBtn;

    /**
     * This method initializes the controllers and scales its windows and its components
     * @param url pathname location used to solve relative paths for the root object,
     *            or null if the location is not known
     * @param resourceBundle resources used to localize the root object, or null if the root object
     *                       was not localized
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        workoutController = new WorkoutController();
        root.getChildren().add(0, workoutController.getParent());
        AnchorPane anchorPane = workoutController.getRoot();
        anchorPane.getStyleClass().remove("interactive");
        anchorPane.maxWidthProperty().bind(root.widthProperty().divide(2));

        root.widthProperty().addListener(event -> scale());
        root.heightProperty().addListener(event -> scale());

        root.spacingProperty().bind(root.heightProperty().multiply(20).divide(HEIGHT));

        topHBox.spacingProperty().bind(root.widthProperty().multiply(15).divide(HEIGHT));
        bottomHBox.spacingProperty().bind(root.widthProperty().multiply(15).divide(HEIGHT));

        closeBtn.prefWidthProperty().bind(root.widthProperty().multiply(260).divide(WIDTH));
        startBtn.prefWidthProperty().bind(root.widthProperty().multiply(260).divide(WIDTH));
        deleteBtn.prefWidthProperty().bind(root.widthProperty().multiply(260).divide(WIDTH));
        createNewBtn.prefWidthProperty().bind(root.widthProperty().multiply(260).divide(WIDTH));
    }

    /**
     * This methods displays the workout information
     * This includes the number of exercises, time for each exercise,
     * the break in between, half time break, and the overall time
     * Then it displays it
     * @param workout specified workout
     */
    public void setScene(Workout workout) {
        currentWorkout = workout;
        workoutController.setWorkout(workout);
        stage.setFullScreen(false);
        stage.setWidth(WIDTH);
        stage.setHeight(HEIGHT);
        stage.show();
    }

    /**
     * This method used to scale the components of the window such as a font
     */
    private void scale() {
        root.setStyle("-fx-font-size: " + 24 * ((root.getWidth() + root.getHeight()) / (WIDTH + HEIGHT)) + "px;");
        root.setPadding(new Insets(20 * ((root.getWidth() + root.getHeight()) / (WIDTH + HEIGHT))));
    }

    /**
     * This method sets the app of this controller
     * @param app exercise application
     */
    public void setApp(ExerciseApp app) {
        this.app = app;
    }

    /**
     * This method sets the parent of this controller
     * @param parent parent node
     */
    public void setParent(Parent parent) {
        this.parent = parent;
    }

    /**
     * This method sets up the pop-up window
     * It sets its title as 'Workout'
     * It also makes sures that the window is closable
     */
    public void setUpStage() {
        stage = new Stage();
        stage.setTitle("Workout");
        stage.setAlwaysOnTop(true);
        stage.initModality(Modality.APPLICATION_MODAL);
        Scene scene = new Scene(parent, WIDTH, HEIGHT);
        stage.setScene(scene);
        stage.setOnCloseRequest(windowEvent -> close());
    }

    /**
     * This method closes a window
     */
    @FXML
    private void close() {
        stage.close();
    }

    /**
     * This method starts an exercise workout
     */
    @FXML
    private void start() {
        app.getUserInterface().showExercise(app.startSession(currentWorkout));
        close();
    }

    /**
     * This method deletes a workout from the data manager
     */
    @FXML
    private void delete() {
        app.getDataManager().removeWorkout(currentWorkout);
        app.getUserInterface().showViewWorkouts();
        close();
    }

    /**
     * This method creates a new workout which will take
     * the user to the create a workout page where they
     * can input the number of exercises and the times
     * they want for the exercise
     */
    @FXML
    private void createNew() {
        app.getUserInterface().showCreateWorkout();
        close();
    }
}
