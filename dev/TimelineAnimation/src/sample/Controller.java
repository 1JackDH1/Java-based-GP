package sample;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.ToggleButton;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.util.Duration;

import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {
    @FXML
    private ImageView imageView;
    @FXML
    private ProgressBar exerciseBar;
    @FXML
    private ProgressBar totalBar;
    @FXML
    private ToggleButton play;
    @FXML
    private ToggleButton cancel;
    @FXML
    private Label exerciseTimer;
    @FXML
    private Label totalTimer;

    private Timeline timeline;
    private int warmupCountdown;
    private int exerciseCountdown;
    private int cooldownCountdown;
    private int totalCountdown;
    private int exerciseCounter;
    private boolean onBreak;
    private boolean isHalftime;

    private static final String[] warmups = {
        "ForwardBend",
        "NeckStretch",
        "PelvisStretch",
        "QuadStretch",
        "ShoulderStretch"
    };

    private static final String[] exercises = {
            "pushUp",
            "oneLegPlank",
            "oneArmPlank",
            "hollowHoldToJackKnife",
            "getUpSquatJump"
    };

    private static final String[] cooldowns = {
            "CatCow",
            "ChildsPose",
            "QuadStretch",
            "StandingForwardBend",
            "SumoSquatStretch"
    };

    private static final int warmupTime = 180; //3 minutes
    private static final int exerciseTime = 10;
    private static final int breakTime = 5;
    private static final int halftimeBreak = 15;
    private static final int cooldownTime = 180; //3 minutes


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        exerciseCounter = 0;
        int setTime = exercises.length * (exerciseTime + breakTime) + halftimeBreak - breakTime; //need to add the time for warmup and cooldown here
        totalCountdown = setTime - 1;
        totalTimer.setText(formatTime(totalCountdown));
        //warmUp function can go here
        nextExercise();

        timeline = new Timeline(new KeyFrame(Duration.seconds(1), actionEvent -> {
            if(exerciseCountdown < 4){
                exerciseTimer.setTextFill(Color.RED); //when time is nearly done change text to red to warn user
            }
            if(exerciseCountdown >= 4){
                exerciseTimer.setTextFill(Color.YELLOWGREEN); //reset the colour back to green once its 4 seconds or over
            }
            if (--totalCountdown == 0) {
                end();
            } else if (exerciseCountdown-- == 0) {
                if (onBreak) {
                    nextExercise();
                } else {
                    nextBreak();
                }
            }
            //if finished all exercises and break call the cooldown function

            exerciseTimer.setText(formatTime(exerciseCountdown));
            if (onBreak) {
                if (isHalftime) {
                    exerciseBar.setProgress(1 - ((double) exerciseCountdown) / halftimeBreak);
                } else {
                    exerciseBar.setProgress(1 - ((double) exerciseCountdown) / breakTime);
                }
            } else {
                exerciseBar.setProgress(1 - ((double) exerciseCountdown) / exerciseTime);
            }

            totalTimer.setText(formatTime(totalCountdown));
            totalBar.setProgress(1 - ((double) totalCountdown) / setTime);
        }));
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
    }

    //We need the warmup animation first before we can implement function
    private void warmUp(){
        exerciseTimer.setTextFill(Color.YELLOWGREEN); //reset text colour to green
        onBreak = false;
        imageView.setImage(new Image(this.getClass().getResource("missing warm up animation").toExternalForm()));
        warmupCountdown = warmupTime - 1;
        exerciseTimer.setText(formatTime(warmupCountdown));
    }


    private void nextBreak() {
        exerciseTimer.setTextFill(Color.YELLOWGREEN); //reset text colour to green
        onBreak = true;
        imageView.setImage(new Image(this.getClass().getResource("break_stretch.gif").toExternalForm()));
        if (exerciseCounter == (exercises.length + 1) / 2) {
            exerciseCountdown = halftimeBreak - 1;
            isHalftime = true;
        } else {
            exerciseCountdown = breakTime - 1;
            isHalftime = false;
        }
        exerciseTimer.setText(formatTime(exerciseCountdown));
    }

    private void nextExercise() {
        exerciseTimer.setTextFill(Color.YELLOWGREEN); //reset text colour to green
        onBreak = false;
        imageView.setImage(new Image(this.getClass().getResource(exercises[exerciseCounter] + ".gif").toExternalForm()));
        exerciseCountdown = exerciseTime - 1;
        exerciseTimer.setText(formatTime(exerciseCountdown));
        exerciseCounter++;
    }

    //We need the cooldown animation first before we can implement the function
    private void coolDown(){
        exerciseTimer.setTextFill(Color.YELLOWGREEN); //reset text colour to green
        onBreak = false;
        imageView.setImage(new Image(this.getClass().getResource("missing cooldown animation").toExternalForm()));
        cooldownCountdown = cooldownTime - 1;
        exerciseTimer.setText(formatTime(cooldownCountdown));
    }

    private void end() {
        timeline.stop();
        exerciseTimer.setText("0s"); //fix bug displaying -1 before
        totalTimer.setText("0s"); //does not work on total timer :(
        System.out.println("End");
    }

    private String formatTime(int seconds) {
        int minutes = seconds / 60;
        if (minutes != 0) {
            return minutes + "m " + seconds % 60 + "s";
        } else {
            return seconds + "s";
        }
    }

    @FXML
    private void togglePlay() {
        if (play.isSelected()) {
            play.setText("Play");
            timeline.stop();
        } else {
            play.setText("Pause");
            timeline.play();
        }
    }

    @FXML
    private void stopGoHome(){
        timeline.stop();
        //temporary, until go home function is called
        imageView.setImage(new Image(this.getClass().getResource("home.jpg").toExternalForm())); //temporary, remove when go to home is called
        imageView.setFitHeight(600);
        imageView.setFitWidth(600);
        imageView.setTranslateY(100);
        exerciseBar.setVisible(false);
        totalBar.setVisible(false);
        play.setVisible(false);
        cancel.setVisible(false);
        exerciseTimer.setVisible(false);
        totalTimer.setVisible(false);

        //then call function to go home -needs linking with the UI
    }
}
