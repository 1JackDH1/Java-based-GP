package uk.ac.aber.ExerciseApp.fxcontroller;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.ToggleButton;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;
import uk.ac.aber.ExerciseApp.Exercise;
import uk.ac.aber.ExerciseApp.ExerciseApp;
import uk.ac.aber.ExerciseApp.Session;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 *This class handles exercise fmxl file
 *
 * @authors REDACTED 
 *
 * @version 1.0
 */

public class ExerciseController implements Initializable {
    private ExerciseApp app;
    private Parent parent;

    @FXML
    private VBox root;
    @FXML
    private ImageView imageView, nextImageView;
    @FXML
    private ProgressBar secondaryBar;
    @FXML
    private ProgressBar primaryBar;
    @FXML
    private HBox btnBox;
    @FXML
    private Button cancelBtn;
    @FXML
    private ToggleButton play;
    @FXML
    private Label title, description, upcomingTitle, upcoming;
    @FXML
    private Label secondaryTimer, primaryTimer;

    private Timeline timeline;
    private Session session;
    private Exercise[] exercises;
    private Exercise breakExercise;
    private int counter;
    private int phase;
    private boolean onBreak;
    private int secondaryCountdown;
    private int primaryCountdown;
    private int secondaryMaximum;
    private int primaryMaximum;
    private MediaPlayer startAudioPlayer;
    private MediaPlayer cooldownAudioPlayer;
    private MediaPlayer endAudioPlayer;

    /**
     * This method initializes the controller and scales the page and its components to fit the window.
     * This method preserves the ratio size of the window and its components
     * It also starts the timeline and the exercise animation, and plays the sounds
     * This method checks the state of the animation whether it is warmup, exercise, or cooldown
     * It catches FileNotFoundException then print exception object and the line it occurs
     * @param location pathname location used to solve relative paths for the root object,
     *                 or null if the location is not known
     * @param resources resources used to localize the root object, or null if the root object
     *                  was not localized.
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        root.widthProperty().addListener(event -> scale());
        root.heightProperty().addListener(event -> scale());

        root.spacingProperty().bind(root.heightProperty().multiply(20).divide(720));

        imageView.fitWidthProperty().bind(root.widthProperty().multiply(500).divide(1280));
        imageView.fitHeightProperty().bind(root.heightProperty().multiply(400).divide(720));

        nextImageView.fitWidthProperty().bind(root.widthProperty().multiply(250).divide(1280));
        nextImageView.fitHeightProperty().bind(root.heightProperty().multiply(160).divide(720));

        secondaryBar.prefWidthProperty().bind(root.widthProperty().multiply(480).divide(1280));
        secondaryBar.prefHeightProperty().bind(root.heightProperty().multiply(24).divide(720));

        primaryBar.prefWidthProperty().bind(root.widthProperty().multiply(720).divide(1280));
        primaryBar.prefHeightProperty().bind(root.heightProperty().multiply(24).divide(720));

        btnBox.spacingProperty().bind(root.widthProperty().multiply(15).divide(1280));

        cancelBtn.prefWidthProperty().bind(root.widthProperty().multiply(160).divide(1280));
        play.prefWidthProperty().bind(root.widthProperty().multiply(160).divide(1280));

        breakExercise = new Exercise(
                "Break Time",
                "Take a Break!",
                new String[]{"src/uk/ac/aber/ExerciseApp/animations/break/break_stretch.gif"});

        Media startAudio = new Media(new File("src/uk/ac/aber/ExerciseApp/audio/starting_sound.mp3").toURI().toString());
        Media cooldownAudio = new Media(new File("src/uk/ac/aber/ExerciseApp/audio/cooldown_beep.mp3").toURI().toString());
        Media endAudio = new Media(new File("src/uk/ac/aber/ExerciseApp/audio/ending_sound.mp3").toURI().toString());

        startAudioPlayer = new MediaPlayer(startAudio);
        cooldownAudioPlayer = new MediaPlayer(cooldownAudio);
        endAudioPlayer = new MediaPlayer(endAudio);

        timeline = new Timeline(new KeyFrame(Duration.seconds(1), actionEvent -> {
            if (--primaryCountdown == 0) {
                switch (++phase) {
                    // Start exercise phase
                    case 1 -> {
                        title.setText("Exercise");
                        exercises = session.getExercises();
                        secondaryMaximum = session.getWorkout().getExerciseTime();
                        primaryMaximum = session.getWorkout().getDuration();
                    }
                    // Start cooldown phase
                    case 2 -> {
                        title.setText("Cooldown");
                        exercises = app.getCooldowns();
                        secondaryMaximum = app.COOLDOWN_TIME / exercises.length;
                        primaryMaximum = secondaryMaximum * exercises.length;
                    }
                    // Ends the session
                    default -> end();
                }
                counter = 0;
                secondaryCountdown = secondaryMaximum - 1;
                primaryCountdown = primaryMaximum - 1;
                next();
            } else if (secondaryCountdown-- == 0) {
                if (onBreak) {
                    next();
                    if (phase == 1) {
                        secondaryMaximum = session.getWorkout().getExerciseTime();
                    }
                } else {
                    try {
                        imageView.setImage(new Image(new FileInputStream(breakExercise.getAnimation(0))));
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }
                    description.setText(breakExercise.getName() + "\n\n" + breakExercise.getDescription());
                    if (counter == (exercises.length + 1) / 2) {
                        secondaryMaximum = session.getWorkout().getHalftimeBreak();
                    } else {
                        secondaryMaximum = session.getWorkout().getBreakTime();
                    }
                    onBreak = true;
                }
                secondaryCountdown = secondaryMaximum - 1;
            }
            updateProgress();
        }));
        timeline.setCycleCount(Timeline.INDEFINITE);
    }

    /**
     * This method sets the scene of the exercise animation
     * It sets its title and goes to the next exercise animation
     * It also updates the progress bars, play the audio, and play the timeline
     * @param session specified session
     */
    public void setScene(Session session) {
        title.setText("Warm Up");

        counter = 0;
        phase = 0;
        onBreak = true;

        this.session = session;
        exercises = app.getWarmups();

        secondaryMaximum = app.WARMUP_TIME / exercises.length;
        secondaryCountdown = secondaryMaximum - 1;
        primaryMaximum = secondaryMaximum * exercises.length;
        primaryCountdown = primaryMaximum - 1;

        next();
        updateProgress();
        playCooldownAudio();
        timeline.play();
    }

    /**
     * This method scales the components of the window such as the font anf the padding
     */
    private void scale() {
        root.setStyle("-fx-font-size: " + 24 * ((root.getWidth() + root.getHeight()) / (1280 + 720)) + "px;");
        root.setPadding(new Insets(20 * ((root.getWidth() + root.getHeight()) / (1280 + 720))));
        description.setPadding(new Insets(0,0,0,20 * ((root.getWidth() + root.getHeight()) / (1280 + 720))));
        description.setStyle("-fx-font-size: " + 18 * ((root.getWidth() + root.getHeight()) / (1280 + 720)) + "px;");
        upcoming.setPadding(new Insets(
                0,
                20 * ((root.getWidth() + root.getHeight()) / (1280 + 720)),
                20 * ((root.getWidth() + root.getHeight()) / (1280 + 720)),
                0
        ));
        title.setStyle("-fx-font-size: " + 36 * ((root.getWidth() + root.getHeight()) / (1280 + 720)) + "px;");
        upcomingTitle.setStyle("-fx-font-size: " + 14 * ((root.getWidth() + root.getHeight()) / (1280 + 720)) + "px;");
        upcoming.setStyle("-fx-font-size: " + 14 * ((root.getWidth() + root.getHeight()) / (1280 + 720)) + "px;");
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
     * @return Returns the root node
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
     * This method moves on to the next exercise animation
     * It catches a FileNotFoundException and then print exception object and the line it occurs
     * Then it displays the interrupt pop-up error message
     */
    private void next() {
        // Display current exercises to main view
        try {
            imageView.setImage(new Image(new FileInputStream(exercises[counter].getAnimation(0))));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            app.getUserInterface().getInterrupt().interrupt("Failed to load image: " + exercises[counter].getAnimation(0));
        }
        description.setText(exercises[counter].getName() + "\n\n" + exercises[counter].getDescription());
        counter++;
        if (exercises.length == counter) { // If last exercise for current phase need to get the next phase of exercises
            if (phase == 0) {
                // Display next exercise from the main set of exercises in the small view
                try {
                    nextImageView.setImage(new Image(new FileInputStream(session.getExercises()[0].getAnimation(0))));
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                    app.getUserInterface().getInterrupt().interrupt("Failed to load image: " + app.getExercises()[0].getAnimation(0));
                }
                upcoming.setText(session.getExercises()[0].getName() + "\n\n" + session.getExercises()[0].getDescription());
            } else if (phase == 1) {
                // Display next exercise from the cooldown in the small view
                try {
                    nextImageView.setImage(new Image(new FileInputStream(app.getCooldowns()[0].getAnimation(0))));
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                    app.getUserInterface().getInterrupt().interrupt("Failed to load image: " + app.getCooldowns()[0].getAnimation(0));
                }
                upcoming.setText(app.getCooldowns()[0].getName() + "\n\n" + app.getCooldowns()[0].getDescription());
            }
        } else {
            if (onBreak && phase == 1) {
                // Display break exercise
                try {
                    nextImageView.setImage(new Image(new FileInputStream(breakExercise.getAnimation(0))));
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                    app.getUserInterface().getInterrupt().interrupt("Failed to load image: " + breakExercise.getAnimation(0));
                }
                upcoming.setText(breakExercise.getName() + "\n\n" + breakExercise.getDescription());
            } else {
                // Otherwise display the next exercise in this current phase
                try {
                    nextImageView.setImage(new Image(new FileInputStream(exercises[counter].getAnimation(0))));
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                    app.getUserInterface().getInterrupt().interrupt("Failed to load image: " + exercises[counter].getAnimation(0));
                }
                upcoming.setText(exercises[counter].getName() + "\n\n" + exercises[counter].getDescription());
            }
        }
        if (phase == 1) {
            onBreak = false;
        }
    }

    /**
     * This method stops the timeline if the session is completed and takes the user back to the view workouts page
     */
    private void end() {
        timeline.stop();
        app.completeSession(session);
        app.getUserInterface().showViewWorkouts();
    }

    /**
     * This method updates the progress bars while the timeline is playing
     * The duration will be in sync with this
     * This method also checks the phase of the workout animation
     */
    private void updateProgress() {
        secondaryTimer.setText(formatTime(secondaryCountdown));
        secondaryBar.setProgress(1 - ((double) secondaryCountdown) / secondaryMaximum);

        primaryTimer.setText(formatTime(primaryCountdown));
        primaryBar.setProgress(1 - ((double) primaryCountdown) / primaryMaximum);

        if (phase == 1) {
            if (onBreak) {
                if (secondaryCountdown == 3) {
                    playStartAudio();
                }
            } else {
                if (secondaryCountdown == 4) {
                    playEndAudio();
                }
            }
        } else {
            if (exercises.length == counter) {
                if (phase == 0) {
                    if (secondaryCountdown == 3) {
                        playStartAudio();
                    }
                } else {
                    if (secondaryCountdown == 0) {
                        playEndAudio();
                    }
                }
            } else {
                if (secondaryCountdown == 0) {
                    playCooldownAudio();
                }
            }
        }
    }

    /**
     * This method plays the start audio
     */
    private void playStartAudio() {
        startAudioPlayer.play();
        startAudioPlayer.seek(Duration.millis(0.0));
    }

    /**
     * This method plays the cooldown audio
     */
    private void playCooldownAudio() {
        cooldownAudioPlayer.play();
        cooldownAudioPlayer.seek(Duration.millis(0.0));
    }

    /**
     * This method plays the end audio
     */
    private void playEndAudio() {
        endAudioPlayer.play();
        endAudioPlayer.seek(Duration.millis(0.0));
    }

    /**
     * This method formats the time in minutes, seconds
     * @param seconds the number of seconds
     * @return the minutes and seconds if the seconds has reached 60 and over
     * Otherwise just return the seconds
     */
    private String formatTime(int seconds) {
        int minutes = seconds / 60;
        if (minutes != 0) {
            return " " + minutes + "m " + seconds % 60 + "s";
        } else {
            return " " + seconds + "s";
        }
    }

    /**
     * This method stops the timeline and takes the user back to the view workouts page
     */
    @FXML
    private void cancel() {
        timeline.stop();
        app.getUserInterface().showViewWorkouts();
    }

    /**
     * This method pauses or plays the timeline for the progress bar and the duration
     * It also resets the label of the button
     */
    @FXML
    private void pause() {
        if (play.isSelected()) {
            play.setText("Play");
            timeline.stop();
        } else {
            play.setText("Pause");
            timeline.play();
        }
    }
}
