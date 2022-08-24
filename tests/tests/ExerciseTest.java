package tests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import uk.ac.aber.ExerciseApp.Exercise;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;
/**
 * This class consists the JUint tests for the Exercise class.
 * An Exercise object containing the pushup, pushup file and pushup description is re-initialised bofore each unit test
 *
 * @authors REDACTED 
 *
 * @version 1.0
 */
class ExerciseTest {
    Exercise exercise;

    @BeforeEach
    void setUp() {
        exercise = new Exercise(
                "Push Up",
                "A standard push up is the more challenging version of a knee push up. Assume a high plank position and complete the push up in the same way, allowing your elbows to flare out at a 45-degree angle.",
                new String[]{"src/uk/ac/aber/ExerciseApp/animations/exercises/PushUp/1.gif"});
    }

    @Test
    @DisplayName("Test Exercise getters")
    void testGetters() {
        // Get the exercise name, should return Push Up
        assertEquals("Push Up", exercise.getName(),
                "Expected: Push Up, but found " + exercise.getName() + " Instead");

        // Get the exercise description, should return A standard push up is the more challenging version of a knee push up. Assume a high plank position and complete the push up in the same way, allowing your elbows to flare out at a 45-degree angle.
        assertEquals("A standard push up is the more challenging version of a knee push up. Assume a high plank position and complete the push up in the same way, allowing your elbows to flare out at a 45-degree angle.",
                exercise.getDescription(),
                "Expected: A standard push up is the more challenging version of a knee push up. Assume a high plank position and complete the push up in the same way, allowing your elbows to flare out at a 45-degree angle., but found " + exercise.getDescription() + " Instead");

        // Get the first exercise animation, should return src/uk/ac/aber/ExerciseApp/animations/exercises/PushUp/1.gif
        assertEquals("src/uk/ac/aber/ExerciseApp/animations/exercises/PushUp/1.gif", exercise.getAnimation(0),
                "Expected: src/uk/ac/aber/ExerciseApp/animations/exercises/PushUp/1.gif, but found " + exercise.getAnimation(0) + " Instead");

        // Get the exercise animations, should return [src/uk/ac/aber/ExerciseApp/animations/exercises/PushUp/1.gif]
        assertEquals("[src/uk/ac/aber/ExerciseApp/animations/exercises/PushUp/1.gif]", Arrays.toString(exercise.getAnimations()),
                "Expected: src/uk/ac/aber/ExerciseApp/animations/exercises/PushUp/1.gif, but found " + Arrays.toString(exercise.getAnimations()) + " Instead");
    }

    @Test
    @DisplayName("Test Exercise setters")
    void testSetters() {
        // Set the new data
        exercise.setName("Bridge");
        exercise.setDescription("Directions:\n" +
                "1.\tLie on your back with your knees bent, feet flat on the floor, and your arms extended by your sides.\n" +
                "2.\tPushing through your feet and bracing your core, raise your bottom off the ground until your hips are fully extended, squeezing your glutes at the top.\n" +
                "3.\tSlowly return to the starting position and repeat.\n");
        exercise.setAnimation(0, "src/uk/ac/aber/ExerciseApp/animations/exercises/Bridge/1.gif");

        // Get the exercise name, should return Bridge
        assertEquals("Bridge", exercise.getName(),
                "Expected: Bridge, but found " + exercise.getName() + " Instead");

        /* Get the exercise description, should return
        Directions:
        1.  Lie on your back with your knees bent, feet flat on the floor, and your arms extended by your sides.
        2.  Pushing through your feet and bracing your core, raise your bottom off the ground until your hips are fully extended, squeezing your glutes at the top.
        3.  Slowly return to the starting position and repeat.
         */
        assertEquals("Directions:\n" +
                        "1.\tLie on your back with your knees bent, feet flat on the floor, and your arms extended by your sides.\n" +
                        "2.\tPushing through your feet and bracing your core, raise your bottom off the ground until your hips are fully extended, squeezing your glutes at the top.\n" +
                        "3.\tSlowly return to the starting position and repeat.\n",
                exercise.getDescription(),
                "Expected: Directions:\n" +
                        "1.\tLie on your back with your knees bent, feet flat on the floor, and your arms extended by your sides.\n" +
                        "2.\tPushing through your feet and bracing your core, raise your bottom off the ground until your hips are fully extended, squeezing your glutes at the top.\n" +
                        "3.\tSlowly return to the starting position and repeat.\n, but found " + exercise.getDescription() + " Instead");

        // Get the first exercise animation, should return src/uk/ac/aber/ExerciseApp/animations/exercises/Bridge/1.gif
        assertEquals("src/uk/ac/aber/ExerciseApp/animations/exercises/Bridge/1.gif", exercise.getAnimation(0),
                "Expected: src/uk/ac/aber/ExerciseApp/animations/exercises/Bridge/1.gif, but found " + exercise.getAnimation(0) + " Instead");
    }
}
