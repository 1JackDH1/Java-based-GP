package tests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import uk.ac.aber.ExerciseApp.*;

import java.lang.reflect.Field;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

/**
 *This is a junit test for the Session class
 *
 * @authors REDACTED 
 *
 * @version 1.0
 */
class SessionTest {

    private Session session;

    // example exercises and workouts are initialized to be used by the testers
    String[] exerciseAnimations = {"./animations/exercises/Bridge/1.gif"};
    Workout testWorkout = new Workout(5, 5, 5, 5);
    Exercise[] testExercises = {new Exercise("Sample", "Test cooldown", exerciseAnimations)};

    @BeforeEach
    void setUp() {
        session = new Session(testExercises, testWorkout);
    }

    @Test
    @DisplayName("Test the exercise getter")
    void getExercises() throws NoSuchFieldException, IllegalAccessException {

        final Field field = session.getClass().getDeclaredField("exercises");
        field.setAccessible(true);
        field.set(session, testExercises);

        final Exercise[] result = session.getExercises();

        // the example exercises are written to the session, then retrieved from the session and compared to itself
        // if they are equal, assertEquals should be true
        assertEquals(testExercises, result);
    }

    @Test
    @DisplayName("Test the workout getter")
    void getWorkout() throws IllegalAccessException, NoSuchFieldException {

        final Field field = session.getClass().getDeclaredField("workout");
        field.setAccessible(true);
        field.set(session, testWorkout);

        final Workout result = session.getWorkout();

        // the example workout is written to the session, then retrieved from the session and compared to itself
        // if they are equal, assertEquals should be true
        assertEquals(testWorkout, result);

    }

    @Test
    @DisplayName("Test the dateTime getter")
    void getDateTime() throws NoSuchFieldException, IllegalAccessException {

        // gets the current date and time of computer
        LocalDateTime testDateTime = LocalDateTime.now();

        final Field field = session.getClass().getDeclaredField("dateTime");
        field.setAccessible(true);
        field.set(session, testDateTime);

        final LocalDateTime result = session.getDateTime();

        // the current dateTime is written into a session and then compared to the current dateTime
        // if they are equal, assertEquals should be true
        assertEquals(testDateTime, result);
    }
}
