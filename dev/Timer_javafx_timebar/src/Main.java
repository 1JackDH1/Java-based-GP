import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.FileNotFoundException;

public class Main extends Application {

    @Override
    public void start(Stage stage){
        VBox vBox;

            MediaPlayerClass mediaPlayerClass = new MediaPlayerClass("file:///C:/Users/jabxz/Videos/Captures/test.mp4"); //LOOP THE ANIMATION
            Scene scene = new Scene(mediaPlayerClass, 1000, 600);
            stage.setScene(scene);
            stage.show();


    }

    public static void main(String[] args) {
        launch(args);
    }
}