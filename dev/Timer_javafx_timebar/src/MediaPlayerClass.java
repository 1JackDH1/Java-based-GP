import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.util.Duration;


public class MediaPlayerClass extends BorderPane {
    Media media;
    MediaPlayer mediaPlayer;
    MediaView mediaView;
    Pane mediaPane;
    TimeBar timeBar;

    //constructor
    public MediaPlayerClass(String fileName){
        //make objects
        media = new Media(fileName);
        mediaPlayer = new MediaPlayer(media);
        mediaView = new MediaView(mediaPlayer);

        mediaPane = new Pane();
        mediaPane.getChildren().add(mediaView);

        //when animation finishes loop
        mediaPlayer.setOnEndOfMedia(new Runnable(){
           @Override
            public void run(){
                mediaPlayer.seek(Duration.ZERO);
                mediaPlayer.play();
            }
        });

        //add the media view
        setCenter(mediaPane);
        mediaPane.setTranslateX(300); //from 350
        mediaPane.setTranslateY(100); //from 100
        mediaView.setScaleX(2); //from 2
        mediaView.setScaleY(2); //from 2
        timeBar = new TimeBar(mediaPlayer);
        setBottom(timeBar);
        timeBar.setTranslateY(-150);
        setStyle("-fx-background-color: black");
        mediaPlayer.play();


    }
}
