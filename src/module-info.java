module ExerciseApp {
    requires javafx.fxml;
    requires javafx.controls;
    requires javafx.graphics;
    requires java.xml;
    requires javafx.media;

    opens uk.ac.aber.ExerciseApp;
    opens uk.ac.aber.ExerciseApp.fxml;
    opens uk.ac.aber.ExerciseApp.fxcontroller;
}