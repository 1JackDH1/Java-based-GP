import javafx.application.Platform;
import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.util.Duration;
import java.lang.StrictMath;
import java.util.TimerTask;


import static java.lang.StrictMath.floor;

public class TimeBar extends HBox {
    //slider time bar for time
    Slider time = new Slider();
    Slider timeForAllVid = new Slider();
    Button playButton = new Button("Play");
    Button pauseButton = new Button("Pause");
    Button cancelButton = new Button("Cancel");
    MediaPlayer mediaPlayer;
   // Label timeLabel = new Label("0:0");
    private static int minInHour = 60;


    //TIMELINE
    //Timeline timeline;


    //constructor
    public TimeBar(MediaPlayer mPlayer) {
        mediaPlayer = mPlayer;

        //set the hbox to center
        setAlignment(Pos.CENTER);
        setPadding(new Insets(5, 10, 5, 10));
        HBox.setHgrow(time, Priority.ALWAYS);
        HBox.setHgrow(timeForAllVid, Priority.ALWAYS);
        //set and add the buttons and labels
        playButton.setPrefWidth(60);
        playButton.setTranslateY(80);
        playButton.setTranslateX(360);
        pauseButton.setPrefWidth(60);
        pauseButton.setTranslateY(80);
        pauseButton.setTranslateX(380);
        cancelButton.setPrefWidth(60);
        cancelButton.setTranslateX(150);
        cancelButton.setTranslateY(80);



//        timeLabel.setTranslateX(10);
//        timeLabel.setTextFill(Color.WHITE);
//        timeLabel.setFont(new Font("Arial", 30));

        timeForAllVid.setTranslateY(140);
        timeForAllVid.setTranslateX(-340);



        //add the components
      //  getChildren().add(timeLabel);
        getChildren().add(playButton);
        getChildren().add(pauseButton);
        getChildren().add(cancelButton);
        getChildren().add(time);
        getChildren().add(timeForAllVid);



        //add functionality for button
        playButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                MediaPlayer.Status status = mediaPlayer.getStatus();
                //if animation is playing
                if (status == status.PLAYING) {

                    //check if curren time of animation is less than it's duration, if so keep playing
                    if (mediaPlayer.getCurrentTime().greaterThanOrEqualTo(mediaPlayer.getTotalDuration())) {
                        //restart video if ends
                       // mediaPlayer.seek(mediaPlayer.getStartTime());
                        mediaPlayer.play();

                    } else {
                      //  mediaPlayer.pause(); //changed from pause();
                        playButton.setText("Play");

                    }
                } //end of main if statement

                //if video is paused allow it to be played again
                if (status == MediaPlayer.Status.HALTED || status == MediaPlayer.Status.STOPPED || status == MediaPlayer.Status.PAUSED) {
                    mediaPlayer.play();
                    playButton.setText("Play");

                }

            }
        });

        //add functionality for pause button
        pauseButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                //mediaPlayer.pause();
              //  time.setValue(50);

            }
        });

        //add functionality for cancel button
        cancelButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                mediaPlayer.stop(); //stops animation
                //but needs to go back to the menu screen
            }
        });


        //add the functionality for the time slider
        mediaPlayer.currentTimeProperty().addListener(new InvalidationListener() {
            @Override
            public void invalidated(Observable observable) {

                if(!pauseButton.isPressed()){
                    updateTime(); //update the time
                }

                if(pauseButton.isPressed()){
                    time.setValue(time.getValue()); //pauses timebar where it's current value is at
                }


            }
        });

        //jump at parts of video
        time.valueProperty().addListener(new InvalidationListener() {
            @Override
            public void invalidated(Observable observable) {
                if (time.isPressed()) {
                    mediaPlayer.seek(mediaPlayer.getMedia().getDuration().multiply(time.getValue() / 100));

                }
            }
        });
    }

    //update the time
    protected void updateTime() {
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                Duration current = mediaPlayer.getCurrentTime();
                time.setValue(mediaPlayer.getCurrentTime().toMillis() / mediaPlayer.getTotalDuration().toMillis() * 100);
                timeForAllVid.setValue(mediaPlayer.getCurrentTime().toMillis() / mediaPlayer.getTotalDuration().toMillis() * 100);
               // timeLabel.setText(setTimeFormat(current, mediaPlayer.getTotalDuration()));

            }
        });

    }

   //format the time
    private static String setTimeFormat(Duration current, Duration duration) {
        int currentTime = (int) floor(current.toSeconds());
        int currentTimeHours = currentTime / (minInHour * minInHour);
        if (currentTimeHours > 0) currentTime -= currentTimeHours * minInHour * minInHour;
        int currentTimeMinutes = currentTime / minInHour;
        int currentTimeSeconds = currentTime - currentTimeHours * minInHour * minInHour - currentTimeMinutes * minInHour;

        if (duration.greaterThan(Duration.ZERO)) {
            int durationTime = (int) floor(duration.toSeconds());
            int durationHours = durationTime / (minInHour * minInHour);
            if (durationHours > 0) durationTime -= durationHours * minInHour * minInHour;
            int durationMinutes = durationTime / minInHour;
            int durationSeconds = durationTime - durationHours * minInHour * minInHour - durationMinutes * minInHour;
            if (durationHours > 0) {
                return String.format("%d:%02d:%02d / %d:%02d:%02d", currentTimeHours, currentTimeMinutes, currentTimeSeconds, durationHours, durationMinutes, durationSeconds);
            } else {
                return String.format("%02d:%02d / %02d:%02d", currentTimeMinutes, currentTimeSeconds, durationMinutes, durationSeconds);
            }
        } else {
            if (currentTimeHours > 0) {
                return String.format("%d:%02d:%02d", currentTimeHours, currentTimeMinutes, currentTimeSeconds);
            } else {
                return String.format("%02d:%02d", currentTimeMinutes, currentTimeSeconds);
            }
        }

    }
}
