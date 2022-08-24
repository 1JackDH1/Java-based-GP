package tests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import uk.ac.aber.ExerciseApp.Workout;

import static org.junit.jupiter.api.Assertions.*;

/**
 *This is a junit test for the Workout class
 *
 * @authors REDACTED 
 *
 * @version 1.0
 */
public class WorkoutTest {
    private Workout workout;

    @BeforeEach
    public void setUp() {
        workout = new Workout(1, 5, 5, 5);
    }

    @Test
    @DisplayName("Exercises Getter and Setter")
    void testGetSetExercises() {
        //Get number of exercises in workout. Should return a value of 1.
        assertEquals(1, workout.getExercises(),
                "Value should be 1, but found " + workout.getExercises() + " instead.");

        //Set number of exercises to 0. Should throw an IllegalArgumentException.
        assertThrows(IllegalArgumentException.class, () -> workout.setExercises(0),
                "Attempt to set value below the specified bounds was successful when it shouldn't be.");

        //Set number of exercises to 31. Should throw an IllegalArgumentException.
        assertThrows(IllegalArgumentException.class, () -> workout.setExercises(31),
                "Attempt to set value above the specified bounds was successful when it shouldn't be.");

        //Set number of exercises to 2. Should not throw any exception.
        assertDoesNotThrow(() -> {
            workout.setExercises(2);
        }, "Attempt to set the value 2 was rejected.");

        //Get number of exercises in workout. Should return a value of 2.
        assertEquals(2, workout.getExercises(),
                "Value should be 2, but found " + workout.getExercises() + " instead.");
    }

    @Test
    @DisplayName("Exercise Time Getter and Setter")
    void testGetSetExerciseTime() {
        //Get time of exercises in workout. Should return a value of 5.
        assertEquals(5, workout.getExerciseTime(),
                "Value should be 5, but found " + workout.getExerciseTime() + " instead.");

        //Set time of exercises to 4. Should throw an IllegalArgumentException.
        assertThrows(IllegalArgumentException.class, () -> workout.setExerciseTime(4),
                "Attempt to set value below the specified bounds was successful when it shouldn't be.");

        //Set time of exercises to 18001. Should throw an IllegalArgumentException.
        assertThrows(IllegalArgumentException.class, () -> workout.setExerciseTime(18001),
                "Attempt to set value above the specified bounds was successful when it shouldn't be.");

        //Set time of exercises to 7. Should not throw any exception.
        assertDoesNotThrow(() -> {
            workout.setExerciseTime(7);
        }, "Attempt to set the value 7 was rejected.");

        //Get time of exercises in workout. Should return a value of 7.
        assertEquals(7, workout.getExerciseTime(),
                "Value should be 18000, but found " + workout.getExerciseTime() + " instead.");
    }

    @Test
    @DisplayName("Break Time Getter and Setter")
    void testGetSetBreakTime() {
        //Get break time in workout. Should return a value of 5.
        assertEquals(5, workout.getBreakTime(),
                "Value should be 5, but found " + workout.getBreakTime() + " instead.");

        //Set break time to 4. Should throw an IllegalArgumentException.
        assertThrows(IllegalArgumentException.class, () -> workout.setBreakTime(4),
                "Attempt to set value below the specified bounds was successful when it shouldn't be.");

        //Set break time to 18001. Should throw an IllegalArgumentException.
        assertThrows(IllegalArgumentException.class, () -> workout.setBreakTime(18001),
                "Attempt to set value above the specified bounds was successful when it shouldn't be.");

        //Set break time to 7. Should not throw any exception.
        assertDoesNotThrow(() -> workout.setBreakTime(7), "Attempt to set the value 7 was rejected.");

        //Get break time in workout. Should return a value of 7.
        assertEquals(7, workout.getBreakTime(),
                "Value should be 7, but found " + workout.getBreakTime() + " instead.");
    }

    @Test
    @DisplayName("Half Time Break Getter and Setter")
    void testGetSetHalfTimeBreak() {
        //Get half time break in workout. Should return a value of 5.
        assertEquals(5, workout.getHalftimeBreak(),
                "Value should be 5, but found " + workout.getHalftimeBreak() + " instead.");

        //Set half time break to 4. Should throw an IllegalArgumentException.
        assertThrows(IllegalArgumentException.class, () -> workout.setHalftimeBreak(4),
                "Attempt to set value below the specified bounds was successful when it shouldn't be.");

        //Set half time break to 18001. Should throw an IllegalArgumentException.
        assertThrows(IllegalArgumentException.class, () -> workout.setHalftimeBreak(18001),
                "Attempt to set value above the specified bounds was successful when it shouldn't be.");

        //Set half time break to 7. Should not throw any exception.
        assertDoesNotThrow(() -> {
            workout.setHalftimeBreak(7);
        }, "Attempt to set the value 7 was rejected.");

        //Get half time break in workout. Should return a value of 7.
        assertEquals(7, workout.getHalftimeBreak(),
                "Value should be 7, but found " + workout.getHalftimeBreak() + " instead.");
    }

    @Test
    @DisplayName("Workout Duration")
    void testGetDuration() {
        workout.setExerciseTime(120);
        workout.setBreakTime(10);
        workout.setHalftimeBreak(30);
        workout.setExercises(5);

        //Get workout duration. Should return a value of 670.
        assertEquals(670, workout.getDuration(),
                "The value should be 670, but found " + workout.getDuration() + " instead");
    }
}
