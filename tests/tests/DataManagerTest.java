package tests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import uk.ac.aber.ExerciseApp.DataManager;
import uk.ac.aber.ExerciseApp.Exercise;
import uk.ac.aber.ExerciseApp.Session;
import uk.ac.aber.ExerciseApp.Workout;

import static org.junit.jupiter.api.Assertions.*;
/**
 * This class consists the JUint tests for the DataManager class.
 * A DataManager object is re-initialised before each unit test
 *
 * @authors REDACTED 
 *
 * @version 1.0
 */
class DataManagerTest {
    private DataManager dataManager;

    @BeforeEach
    void setUp() {
        dataManager = new DataManager();
        dataManager.getWorkouts().clear();
        dataManager.getHistory().clear();
    }

    @Test
    void TestAddWorkout() {
        Workout workout = new Workout(10, 10, 10, 10);

        // Add a new entry. Should return true for successful insert.
        assertTrue(dataManager.addWorkout(workout),
                "Entry (10, 10, 10, 10) not successfully added.");

        // Add a duplicate entry. Should throw an IllegalArgumentException.
        assertThrows(IllegalArgumentException.class, () -> {
            dataManager.addWorkout(workout);
        });
    }

    @Test
    void removeWorkout() {
        Workout workout = new Workout(10, 10, 10, 10);

        // Remove entry from empty data manager. Should return false for unsuccessful delete.
        assertFalse(dataManager.removeWorkout(workout),
                "Entry (10, 10, 10, 10) cannot be removed as it does not exist.");

        // Add a new entry. Should return true for successful insert.
        assertTrue(dataManager.addWorkout(workout),
                "Entry (10, 10, 10, 10) not successfully added.");

        // Remove entry from data manager. Should return true for successful delete.
        assertTrue(dataManager.removeWorkout(workout),
                "Entry (10, 10, 10, 10) not removed.");
    }

    @Test
    void testAddSession() {
        Workout workout = new Workout(10, 10, 10, 10);
        Session session = new Session(new Exercise[10], workout);

        // Add a new entry. Should return true for successful insert.
        assertTrue(dataManager.addSession(session),
                "Entry (new Exercise[10], workout) not successfully added.");
    }

    @Test
    void TestSaveAndLoad() {
        // Add a workout
        dataManager.addWorkout(new Workout(1, 5, 5, 5));

        // Save the data manager
        dataManager.save();

        // Add another workout
        dataManager.addWorkout(new Workout(10, 10, 10, 10));

        // Attempt to load the saved data to data manager
        assertDoesNotThrow(() -> dataManager.load(),
                "Failed to load data from dataManager.ser");

        // Check if the data manager if the same
        assertEquals(1, dataManager.getWorkouts().size(),
                "The data manager is not the same.");
    }
}
