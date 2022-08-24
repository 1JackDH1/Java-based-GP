package tests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import uk.ac.aber.ExerciseApp.*;

import java.lang.reflect.Field;
import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
/**
 * This class consists the JUint tests for the ExerciseApp class.
 * An ExerciseApp and a UserInterface object is re-initialised before each unit test
 *
 * @authors REDACTED 
 *
 * @version 1.0
 */
public class ExerciseAppTest {

    private ExerciseApp exerciseApp;
    private UserInterface userInterface;

    @BeforeEach
    public void setUp() {
        userInterface = new UserInterface();
        exerciseApp = new ExerciseApp(userInterface);
    }

    @Test
    @DisplayName("Test the user interface getter")
    void getUserInterface() {
        // compares the useInterface with the exerciseApp's userInterface. should return true
        assertEquals(userInterface, exerciseApp.getUserInterface(), "The user interface is not equal.");
    }

    @Test
    @DisplayName("Test the exercises getter")
    void getExercises() throws NoSuchFieldException, IllegalAccessException {

        String[] exerciseAnimations = new String[2];

        exerciseAnimations[0] = ("./animations/exercises/Bridge/1.gif");
        exerciseAnimations[1] = ("./animations/exercises/ChairSquat/1.gif");

        Exercise[] exercises = new Exercise[1];
        exercises[0] = new Exercise("Sample", "Test exercise", exerciseAnimations);

        final Field field = exerciseApp.getClass().getDeclaredField("exercises");
        field.setAccessible(true);
        field.set(exerciseApp, exercises);

        final Exercise[] result = exerciseApp.getExercises();
        // compares the exercises with the exerciseApp's exercises. should return true
        assertEquals(exercises, result);
    }

    @Test
    @DisplayName("Test the warmups getter")
    void getWarmups() throws NoSuchFieldException, IllegalAccessException {
        String[] exerciseAnimations = new String[2];

        exerciseAnimations[0] = ("./animations/warmups/ForwardBend/1.gif");
        exerciseAnimations[1] = ("./animations/warmups/NeckStretch/1.gif");

        Exercise[] warmups = new Exercise[1];
        warmups[0] = new Exercise("Sample", "Test warmup", exerciseAnimations);

        // Field made final
        final Field field = exerciseApp.getClass().getDeclaredField("warmups");
        field.setAccessible(true);
        field.set(exerciseApp, warmups);

        final Exercise[] result = exerciseApp.getWarmups();
        // compares the warmups with the exerciseApp's warmups. should return true
        assertEquals(warmups, result);
    }

    @Test
    @DisplayName("Test the cooldowns getter")
    void getCooldowns() throws NoSuchFieldException, IllegalAccessException {
        String[] exerciseAnimations = new String[2];

        exerciseAnimations[0] = ("./animations/cooldowns/CatCow/1.gif");
        exerciseAnimations[1] = ("./animations/cooldowns/ChildsPose/1.gif");

        Exercise[] cooldowns = new Exercise[1];
        cooldowns[0] = new Exercise("Sample", "Test cooldown", exerciseAnimations);

        final Field field = exerciseApp.getClass().getDeclaredField("cooldowns");
        field.setAccessible(true);
        field.set(exerciseApp, cooldowns);

        final Exercise[] result = exerciseApp.getCooldowns();
        // compares the cooldowns with the exerciseApp's cooldowns. should return true
        assertEquals(cooldowns, result);

    }

    @Test
    @DisplayName("Test the startSession method")
    void startSession() {

        Workout testWorkout = new Workout(15, 5, 5, 5);
        Session testSession = exerciseApp.startSession(testWorkout);
        Exercise[] testSessionExercises = testSession.getExercises();

        // Makes sure that the length of the exercises array matches the number of exercises fromm the workout
        // Should return true if they are equal
        assertEquals(15, testSessionExercises.length, "Session number of exercises is incorrect");

        // creates a hashSet and adds all exercises to them to check if there are any duplicates
        // the hashSet.add() returns false if we add an already existing exercise to the set
        boolean testSessionDuplicates = false;
        Set<Exercise> testSessionExercisesSet = new HashSet<>();
        for (Exercise exercise: testSessionExercises) {
            if (!testSessionExercisesSet.add(exercise)) {
                testSessionDuplicates = true;
            }
        }

        // If there are no duplicates, testSessionDuplicates should be false.
        assertFalse(testSessionDuplicates);

        // Makes sure that there are no nulls in the exercises by looping around the array looking for nulls
        boolean testSessionNulls = false;
        for (Exercise exercise: testSessionExercises) {
            if (exercise == null) {
                testSessionNulls = true;
                break;
            }
        }

        // if there are no nulls, testSessionNulls should be false
        assertFalse(testSessionNulls);
    }
}
