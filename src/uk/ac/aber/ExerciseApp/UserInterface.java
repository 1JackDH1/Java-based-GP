package uk.ac.aber.ExerciseApp;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import uk.ac.aber.ExerciseApp.fxcontroller.*;

import java.io.IOException;
import java.util.Random;

/**
 *This class handles all the fxml controllers
 * and tries to load all the fxml files
 * and display them
 *
 * @authors REDACTED 
 *
 * @version 1.0
 */
public class UserInterface extends Application {
    private ExerciseApp app;

    private InterruptController interrupt;
    private ViewWorkoutController viewWorkout;
    private WorkoutPopupController workoutPopup;
    private CreateWorkoutController createWorkout;
    private HistoryController history;
    private ExerciseController exercise;
    private AboutController about;

    private Scene scene;
    private Stage window;

    /**
     * This sets the width of the view workout window
     * The size cannot be changed
     */
    private static final int WIDTH = 1280;

    /**
     * This sets the height of the view workout window
     * The size cannot be changed
     */
    private static final int HEIGHT = 720;

    /**
     * This is the starting method in javafx
     * It tries to load all the fxml files and its respective controllers
     * Then displays the window of the loaded fxml file and controller
     * @param primaryStage stage container for the user interface
     * @throws IOException if the "viewWorkouts.fxml" file is not found or has a problem
     * @throws IOException if the "workoutPopup.fxml" file is not found or has a problem
     * @throws IOException if the "createWorkout.fxml" file is not found or has a problem
     * @throws IOException if the "history.fxml" file is not found or has a problem
     * @throws IOException if the "exercise.fxml" file is not found or has a problem
     * @throws IOException if the "about.fxml" file is not found or has a problem
     * Catches the IOException, then print exception object and the line it occurs, then it displays an interrupt pop-up
     * which says which fxml file cannot be loaded
     *
     */
    @Override
    public void start(Stage primaryStage) throws IOException {
        app = new ExerciseApp(this);

        FXMLLoader loader;
        Parent parent;
        String errorMessage = "Error loading file! ";

        interrupt = new InterruptController();

        try {
            //try loading viewWorkouts file
            loader = new FXMLLoader(getClass().getResource("fxml/viewWorkouts.fxml"));
            parent = loader.load();
            viewWorkout = loader.getController();
            viewWorkout.setApp(app);
            viewWorkout.setParent(parent);
        }

        //show pop up error message if it encounters a problem loading the file
        catch (IOException e) {
            e.printStackTrace();
            interrupt.interrupt(errorMessage + "fxml/viewWorkouts.fxml");
        }

        try {
            //try loading the workout file
            loader = new FXMLLoader(getClass().getResource("fxml/workoutPopup.fxml"));
            parent = loader.load();
            workoutPopup = loader.getController();
            workoutPopup.setApp(app);
            workoutPopup.setParent(parent);
            workoutPopup.setUpStage();
        }
        //show pop up error message if it encounters a problem loading the file
        catch (IOException e) {
            e.printStackTrace();
            interrupt.interrupt(errorMessage + "fxml/workoutPopup.fxml");
        }

        try {
            //try loading the create workout file
            loader = new FXMLLoader(getClass().getResource("fxml/createWorkout.fxml"));
            parent = loader.load();
            createWorkout = loader.getController();
            createWorkout.setApp(app);
            createWorkout.setParent(parent);
        }
        //show pop up error message if it encounters a problem loading the file
        catch (IOException e) {
            e.printStackTrace();
            interrupt.interrupt(errorMessage + "fxml/createWorkout.fxml");
        }

        try {
            //try loading the history file
            loader = new FXMLLoader(getClass().getResource("fxml/history.fxml"));
            parent = loader.load();
            history = loader.getController();
            history.setApp(app);
            history.setParent(parent);
            history.loadImages();
        }
        //show pop up error message if it encounters a problem loading the file
        catch (IOException e) {
            e.printStackTrace();
            interrupt.interrupt(errorMessage + "fxml/history.fxml");
        }

        try {
            //try loading the exercise file
            loader = new FXMLLoader(getClass().getResource("fxml/exercise.fxml"));
            parent = loader.load();
            exercise = loader.getController();
            exercise.setApp(app);
            exercise.setParent(parent);
        }
        //show pop up error message if it encounters a problem loading the file
        catch (IOException e) {
            e.printStackTrace();
            interrupt.interrupt(errorMessage + "fxml/exercise.fxml");
        }

        try {
            //try loading the about file
            about = new AboutController();
        } catch (IOException e) {
            e.printStackTrace();
            interrupt.interrupt(errorMessage + "fxml/about.fxml");
        }

        window = primaryStage;
        window.setTitle("Exercise App");
        scene = new Scene(viewWorkout.getParent(), WIDTH, HEIGHT);
        window.setScene(scene);
//        testUserInterface(); //Test
        showViewWorkouts();
        window.show();
        window.setOnCloseRequest(event -> app.getDataManager().save());
    }

    /**
     * Main method where the code is run
     * @param args main method arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

    /**
     * This method gets the interrupt controller which is the pop-up window that contains an error message
     * @return the interrupt controller which is the pop-up window that contains an error message
     */
    public InterruptController getInterrupt() {
        return interrupt;
    }

    /**
     * This method displays a window containing the Create a workout, history, about buttons,
     * as well as sort,  and ascending and descending order of the overall times per workout
     * It also contains the created workouts
     */
    public void showViewWorkouts() {
        viewWorkout.setScene();
        show(viewWorkout.getParent());
    }

    /**
     * This method displays the pop-up menu when a workout tile is clicked
     * @param workout which consist of the number of exercises, time for each, breaks in between, and halftime break
     */
    public void showWorkoutPopup(Workout workout) {
        workoutPopup.setScene(workout);
    }

    /**
     * This method displays the workout in a form of tiles in the middle of the screen
     * It contains information such as the number of exercises, time for each exercises,
     * break in between, half time break, and the overall time
     */
    public void showCreateWorkout() {
        createWorkout.setScene();
        show(createWorkout.getParent());
    }

    /**
     * This method displays the exercise animations when an excercise session has started
     * This includes the warm ups, exercises, breaks, halftime breaks, and cool downs
     * @param session specified session
     */
    public void showExercise(Session session) {
        exercise.setScene(session);
        show(exercise.getParent());
    }

    /**
     * This method displays the history page. This includes the session on the left.
     * The sessiom on the left has toggle buttons where the user can click in order for
     * the drop down to work. This contains descriptions of the exercise.
     * On the right of the screen it displays a graph. The graph shows the amount of time in minutes has been
     * spent doing the workouts within a period of time using local date. This is only shown if an entire workout
     * is completed and will not be added if the exercise workout is not completed
     */
    public void showHistory() {
        history.resetToDefault();
        history.setScene();
        show(history.getParent());
    }

    /**
     * This method displays the about page which contains the tools and their versions which we used in order
     * to make the software.
     * It also includes the members of the group plus the group manager and creator
     */
    public void showAbout() {
        about.show();
    }

    /**
     * This method displays a window
     * @param parent parent node
     */
    private void show(Parent parent) {
        scene.setRoot(parent);
    }

    /**
     * This method is used to test the user interface, it generates a random number of workouts
     * The workouts will appear in the homepage where they will be in a form of tiles in the middle of the screen
     * These workouts will contain information such as the number of exercises, the time for each exercise, time
     * for the break in between,f time break hal, and the overall time of the workout
     */
    private void testUserInterface() {
        Random random = new Random();
        int workouts = random.nextInt(50);
        for (int i = 0; i < workouts; i++) {
            app.getDataManager().addWorkout(
                    new Workout(
                            random.nextInt(29) + 1,
                            random.nextInt(1000) + 5,
                            random.nextInt(1000) + 5,
                            random.nextInt(2000) + 5));
        }
        int sessions = random.nextInt(50);
        for (int i = 0; i < sessions; i++) {
            app.getDataManager().addSession(app.startSession(app.getDataManager().getWorkouts().get(random.nextInt(workouts))));
        }
    }
}
