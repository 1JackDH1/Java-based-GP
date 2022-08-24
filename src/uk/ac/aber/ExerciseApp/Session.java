package uk.ac.aber.ExerciseApp;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 *This class consist of the array of exercises
 * the workout and the local date and time
 *
 * @authors REDACTED 
 *
 * @version 1.0
 */
public class Session implements Serializable {
    private Exercise[] exercises;
    private Workout workout;
    private LocalDateTime dateTime;

    /**
     * Constructor of the session method
     * @param exercises the individual exercises in the animation
     * @param workout which consist of the number of exercises, time for each, breaks in between, and halftime break
     */
    public Session(Exercise[] exercises, Workout workout) {
        this.exercises = exercises;
        this.workout = workout;
        this.dateTime = LocalDateTime.now();
    }

    /**
     * This method gets the exercises
     * @return exercise
     */
    public Exercise[] getExercises() {
        return exercises;
    }

    /**
     * This method gets the workout
     * @return workout which consist of the number of exercises, time for each, breaks in between, and halftime break
     */
    public Workout getWorkout() {
        return workout;
    }

    /**
     * This method gets the local date and time
     * @return the local date and time
     */
    public LocalDateTime getDateTime() {
        return dateTime;
    }
}
