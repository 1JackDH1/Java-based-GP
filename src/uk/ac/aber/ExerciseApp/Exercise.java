package uk.ac.aber.ExerciseApp;

import java.io.Serializable;

/**
 * This class consists of the string and string array variables
 * related to all instances of Exercise objects, as well as the
 * related getter and setter methods.
 *
 * @authors REDACTED 
 *
 * @version 1.0
 */
public class Exercise implements Serializable {
    private String name;
    private String description;
    private String[] animations;

    /**
     * Constructor for exercise objects, assigned directly.
     * @param name name of the exercise
     * @param description description of the exercise
     * @param animations array of path names to the exercise animations
     * stored within this project
     */
    public Exercise(String name, String description, String[] animations) {
        this.name = name;
        this.description = description;
        this.animations = animations;
    }

    /**
     * Method to get the name from an exercise.
     * @return name of the exercise
     */
    public String getName() {
        return name;
    }

    /**
     * Method to set the name to an exercise.
     * @param name name of the exercise
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Method to get the description from an exercise.
     * @return description of the exercise
     */
    public String getDescription() {
        return description;
    }

    /**
     * Method to set the description to an exercise
     * @param description description of the exercise
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Method to get all the animation(s) path names from an exercise.
     * @return path names for the exercise animation(s)
     */
    public String[] getAnimations() {
        return animations;
    }

    /**
     * Method to get one of the animation(s) path names from an exercise.
     * @param i index for the animations array
     * @return one pathname for the exercise animation
     */
    public String getAnimation(int i) {
        return animations[i];
    }

    /**
     * Method to set an animation pathname into a specific position
     * into the animations array of an exercise.
     * @param i index for the animations array
     * @param animation pathname for the exercise animation
     */
    public void setAnimation(int i, String animation) {
        animations[i] = animation;
    }
}
