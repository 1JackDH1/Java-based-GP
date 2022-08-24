package uk.ac.aber.ExerciseApp;

import java.io.Serializable;

/**
 * This class consists of the integer variables relevant to all
 * instances of workout objects, as well as the related getter
 * and setter methods.
 *
 * @authors REDACTED 
 *
 * @version 1.0
 */
public class Workout implements Serializable {
    private int exercises;
    private int exerciseTime;
    private int breakTime;
    private int halftimeBreak;

    /**
     * Constructor for workout objects, assigned by the setter methods.
     * @param exercises number of exercises
     * @param exerciseTime time in seconds taken for each exercise
     * @param breakTime time in seconds taken for each break between exercises
     * @param halftimeBreak time in seconds taken for the break halfway through a session
     * @throws IllegalArgumentException Data is outside the specified bounds
     */
    public Workout(int exercises, int exerciseTime, int breakTime, int halftimeBreak) throws IllegalArgumentException {
        setExercises(exercises);
        setExerciseTime(exerciseTime);
        setBreakTime(breakTime);
        setHalftimeBreak(halftimeBreak);
    }

    /**
     * Method to get the number of exercises from a workout.
     * @return number of exercises
     */
    public int getExercises() {
        return exercises;
    }

    /**
     * Method to set the number of exercises in a workout.
     * @param exercises number of exercises
     * @throws IllegalArgumentException if the number of exercises is not
     * more than 0 and less than 31
     */
    public void setExercises(int exercises) throws IllegalArgumentException {
        if (0 < exercises && exercises < 31) {
            this.exercises = exercises;
        } else {
            throw new IllegalArgumentException("Number of Exercises is out of bounds! Must be [1-30].");
        }
    }

    /**
     * Method to get the exercise time from a workout.
     * @return exercise time in seconds
     */
    public int getExerciseTime() {
        return exerciseTime;
    }

    /**
     * Method to set the exercise time in a workout.
     * @param exerciseTime exercise time in seconds
     * @throws IllegalArgumentException if the exercise time is not equal or
     * more than 5 and equal or less than 18000
     */
    public void setExerciseTime(int exerciseTime) throws IllegalArgumentException {
        if (5 <= exerciseTime && exerciseTime <= 18000) {
            this.exerciseTime = exerciseTime;
        } else {
            throw new IllegalArgumentException("Time for each exercise is out of bounds! Must be minimum 5 seconds and maximum 300 minutes.");
        }
    }

    /**
     * Method to get the break time from a workout.
     * @return break time in seconds
     */
    public int getBreakTime() {
        return breakTime;
    }

    /**
     * Method to set the break time in a workout.
     * @param breakTime break time in seconds
     * @throws IllegalArgumentException if the break time is not equal or
     * more than 5 and equal or less than 18000
     */
    public void setBreakTime(int breakTime) throws IllegalArgumentException {
        if (5 <= breakTime && breakTime <= 18000) {
            this.breakTime = breakTime;
        } else {
            throw new IllegalArgumentException("Break after each exercise is out of bounds! Must be minimum 5 seconds and maximum 300 minutes.");
        }
    }

    /**
     * Method to get the halftime break from a workout.
     * @return halftime break in seconds
     */
    public int getHalftimeBreak() {
        return halftimeBreak;
    }

    /**
     * Method to set the halftime break in a workout.
     * @param halftimeBreak halftime break in seconds
     * @throws IllegalArgumentException if the halftime break is not equal or
     * more than 5 and equal or less than 18000
     */
    public void setHalftimeBreak(int halftimeBreak) throws IllegalArgumentException {
        if (halftimeBreak != 0) {
            if (5 <= halftimeBreak && halftimeBreak <= 18000) {
                this.halftimeBreak = halftimeBreak;
            } else {
                throw new IllegalArgumentException("Half-time break is out of bounds! Must be minimum 5 seconds and maximum 300 minutes or use 0 to replicate the break time.");
            }
        } else {
            this.halftimeBreak = breakTime;
        }
    }

    /**
     * Method to obtain the workout duration derived from all
     * the workout integer variables.
     * @return workout duration in seconds
     */
    public int getDuration() {
        return exercises * (exerciseTime + breakTime) + halftimeBreak - breakTime;
    }
}
