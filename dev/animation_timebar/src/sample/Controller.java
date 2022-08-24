package sample;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.ToggleButton;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Duration;
import org.w3c.dom.ls.LSOutput;

import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {
    @FXML
    private ImageView imageView;

    @FXML
    private ProgressBar progressBar;
    @FXML
    private ProgressBar secondProgressBar;
    @FXML
    private ProgressBar thirdProgressBar;
    @FXML
    private ProgressBar fourthProgressBar;
    @FXML
    private ProgressBar fifthProgressBar;
    @FXML
    private ProgressBar sixthProgressBar;
    @FXML
    private ProgressBar seventhProgressBar;

    @FXML
    private ToggleButton play;
    @FXML
    private ToggleButton cancel;

    @FXML
    private Label timer;
    @FXML
    private Label exerciseLabel;

    private Timeline timeline;
    private int countdown;

    private int warmUpSeconds = 7; //really 3 minutes, put 7 seconds for sake of testing
    private int exerciseSeconds = 5;
    private int inBetweenBreaks = 10; //time in between exercises
    private int coolDownSeconds = 7; //really 3 minutes, put 7 seconds for sake of testing
    private int overAllCountDown = warmUpSeconds + exerciseSeconds + inBetweenBreaks + exerciseSeconds;
    private int overAllSeconds = warmUpSeconds + exerciseSeconds + inBetweenBreaks + exerciseSeconds;

    public void getOverAllCountDown(){
        timeline = new Timeline(new KeyFrame(Duration.seconds(1), actionEvent -> {
            secondProgressBar.setProgress(1 - ((double)overAllCountDown) / overAllSeconds);
            overAllCountDown--;
        }));
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //need to do a 3 minute warmup first
        imageView.setImage(new Image(this.getClass().getResource("pushUp.gif").toExternalForm()));
        timer.setText(warmUpSeconds + " s");
        countdown = warmUpSeconds;

        timeline = new Timeline(new KeyFrame(Duration.seconds(1), actionEvent -> {
            timer.setText(countdown + " s");
            progressBar.setProgress(1 - ((double) countdown) / warmUpSeconds);
            secondProgressBar.setProgress(1 - ((double) countdown) / warmUpSeconds);
            if (countdown-- == 0) {
                reset();
                //if timer is finished move on to the next animation exercise
                oneLegPlank();
            }
        }));


        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
    }

        private void oneLegPlank(){
        progressBar.setProgress(0);
            imageView.setImage(new Image(this.getClass().getResource("oneLegPlank.gif").toExternalForm()));
            exerciseLabel.setText("Now for the excercises, one leg plank!");
            timer.setText(exerciseSeconds + " s");
            countdown = exerciseSeconds;

            timeline = new Timeline(new KeyFrame(Duration.seconds(1), actionEvent -> {
                timer.setText(countdown + " s");
                progressBar.setProgress(1 - ((double) countdown) / exerciseSeconds);
                thirdProgressBar.setProgress(1- ((double) countdown) / exerciseSeconds);
                if (countdown-- == 0) {
                    reset();
                    //if timer is finished move on to the next animation exercise
                    InBetweenBreaks1();
                }
            }));

            timeline.setCycleCount(Timeline.INDEFINITE);
            timeline.play();
        }


    private void jackKnife() {
        progressBar.setProgress(0);
        imageView.setImage(new Image(this.getClass().getResource("jackKnife.gif").toExternalForm()));
        exerciseLabel.setText("Jack Knife");
        timer.setText(exerciseSeconds + " s");
        countdown = exerciseSeconds;

        timeline = new Timeline(new KeyFrame(Duration.seconds(1), actionEvent -> {
            timer.setText(countdown + " s");
            progressBar.setProgress(1 - ((double) countdown) / exerciseSeconds);
            fifthProgressBar.setProgress(1 - ((double) countdown) / exerciseSeconds);
            if (countdown-- == 0) {
                reset();
                //if timer is finished move on to the next animation exercise
                InBetweenBreaks2();
            }
        }));

        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
    }

    //in the end is a 3 minute cooldown

    private void InBetweenBreaks1() {
        progressBar.setProgress(0);
        imageView.setImage(null);
        exerciseLabel.setText("Lets take a " + inBetweenBreaks + " second break");
        timer.setText(inBetweenBreaks + " s");
        countdown = inBetweenBreaks;

        timeline = new Timeline(new KeyFrame(Duration.seconds(1), actionEvent -> {
            timer.setText(countdown + " s");
            progressBar.setProgress(1 - ((double) countdown) / inBetweenBreaks);
            fourthProgressBar.setProgress(1 - ((double) countdown) / inBetweenBreaks);
            if (countdown-- == 0) {
                reset();
                //if timer is finished move on to the next animation exercise
                jackKnife();
            }
        }));

        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
    }

    private void InBetweenBreaks2() {
        progressBar.setProgress(0);
        imageView.setImage(null);
        exerciseLabel.setText("Lets take a " + inBetweenBreaks + " second break");
        timer.setText(inBetweenBreaks + " s");
        countdown = inBetweenBreaks;

        timeline = new Timeline(new KeyFrame(Duration.seconds(1), actionEvent -> {
            timer.setText(countdown + " s");
            progressBar.setProgress(1 - ((double) countdown) / inBetweenBreaks);
            sixthProgressBar.setProgress(1 - ((double) countdown) / inBetweenBreaks);
            if (countdown-- == 0) {
                reset();
                //if timer is finished move on to the next animation exercise
                coolDown();
            }
        }));

        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
    }

    private void coolDown(){
        progressBar.setProgress(0);
        imageView.setImage(new Image(this.getClass().getResource("1_arm_plank.gif").toExternalForm()));
        exerciseLabel.setText("Well done, now lets cool down with 1 arm plank");
        timer.setText(exerciseSeconds + " s");
        countdown = exerciseSeconds;

        timeline = new Timeline(new KeyFrame(Duration.seconds(1), actionEvent -> {
            timer.setText(countdown + " s");
            progressBar.setProgress(1 - ((double) countdown) / exerciseSeconds);
            seventhProgressBar.setProgress(1 - ((double) countdown) / exerciseSeconds);
            if (countdown-- == 0) {
                reset();
                //if timer is finished move on to the next animation exercise
                imageView.setImage(null); //done the cooldown
            exerciseLabel.setText("Good Job for completing the exercise!\n You can now eat a box of pizza:)");
            exerciseLabel.setTranslateY(50);
            timer.setVisible(false);
            play.setVisible(false);
            cancel.setVisible(false);
            progressBar.setVisible(false);
            secondProgressBar.setVisible(false);
                thirdProgressBar.setVisible(false);
                fourthProgressBar.setVisible(false);
                fifthProgressBar.setVisible(false);
                sixthProgressBar.setVisible(false);
                seventhProgressBar.setVisible(false);

            }
        }));

        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
    }

    private void reset() {
        timeline.stop();
        countdown = exerciseSeconds;
        play.setSelected(true);
        togglePlay();
    }

    @FXML
    private void stopThenHome(){
        timeline.stop();
        imageView.setImage(null);
        exerciseLabel.setText("Go To Home");
        System.out.println("Test commit 2"); //testing commit changes on git

    }

    @FXML
    private void togglePlay() {

        long startTime = System.currentTimeMillis();
        if (play.isSelected() && timeline.getStatus() == Animation.Status.RUNNING) {
            play.setText("Play");
            timeline.pause();
        } else if(timeline.getStatus() == Animation.Status.PAUSED){
            play.setText("Pause");
            timeline.play();
        }
        long totalTime = System.currentTimeMillis() - startTime;
        System.out.println(totalTime); //prints the time it takes for pause and play to work
    }


}


