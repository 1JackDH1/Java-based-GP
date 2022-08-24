package uk.ac.aber.ExerciseApp;

import java.io.*;
import java.util.*;

/**
 *This class handles the userinterface,
 * exercises, warmups, cooldowns, and the datamanager
 *
 *
 * @authors REDACTED 
 *
 * @version 1.0
 */
public class ExerciseApp {

    private UserInterface userInterface;
    private Exercise[] exercises;
    private Exercise[] warmups;
    private Exercise[] cooldowns;
    private DataManager dataManager;

    /**
     * This is the time of warm up in seconds
     * The size cannot be changed
     */
    public final int WARMUP_TIME = 180;

    /**
     * This is the time of cool down in seconds
     * The size cannot be changed
     */
    public final int COOLDOWN_TIME = 180;


    /**
     * This is the constructor of the exercise app
     * It creates an exercise, warmups, cooldowns, and the data manager object
     * @param userInterface this loads all the fxml files and controllers
     */
    public ExerciseApp(UserInterface userInterface) {
        this.userInterface = userInterface;
        exercises = new Exercise[30];
        warmups = new Exercise[5];
        cooldowns = new Exercise[5];
        dataManager = new DataManager();

        loadExercises();
    }

    /**
     * This method gets the userinterface which loads the fxml files and controllers
     * @return the userinterface which loads the fxml files and controllers
     */
    public UserInterface getUserInterface() {
        return userInterface;
    }

    /**
     * This method loads the gif animations in the animations folder inside the src folder
     * Tries to load the description.txt per gif animation
     * Catches FileNotFoundException if the file is not found, then print exception object and the line it occur
     */
    private void loadExercises() {
        File dir = new File("src/uk/ac/aber/ExerciseApp/animations/");

        for (File sub : Objects.requireNonNull(dir.listFiles())) {
            if (sub.isDirectory()) {
                int count = 0;
                for (File exercise : Objects.requireNonNull(sub.listFiles())) {
                    if (exercise.isDirectory()) {
                        String name = exercise.getName().replaceAll("(.)([A-Z])", "$1 $2");
                        StringBuilder description = new StringBuilder();
                        String[] animations = new String[Objects.requireNonNull(exercise.listFiles()).length];
                        int step = 0;
                        for (File animation : Objects.requireNonNull(exercise.listFiles())) {
                            if (animation.getName().equals("description.txt")) {
                                try {
                                    Scanner reader = new Scanner(animation);
                                    while (reader.hasNext()) {
                                        description.append(reader.nextLine()).append("\n");
                                    }
                                } catch (FileNotFoundException e) {
                                    e.printStackTrace();
                                }
                            } else {
                                try {
                                    animations[step++] = animation.getPath();
                                } catch (ArrayIndexOutOfBoundsException e) {
                                    e.printStackTrace();
                                }
                            }
                        }
                        if (sub.getName().contains("warmups")) {
                            warmups[count++] = new Exercise(name, description.toString(), animations);
                        } else if (sub.getName().contains("exercises")) {
                            exercises[count++] = new Exercise(name, description.toString(), animations);
                        } else if (sub.getName().contains("cooldowns")) {
                            cooldowns[count++] = new Exercise(name, description.toString(), animations);
                        }
                    }
                }
            }
        }
    }

    /**
     * This method gets the exercise which is the main exercise the user has to follow
     * @return the exercise
     */
    public Exercise[] getExercises() {
        return exercises;
    }

    /**
     * This method gets the warmups which is a set of five warm up exercises before the main exercise
     * @return the warmups
     */
    public Exercise[] getWarmups() {
        return warmups;
    }


    /**
     * This method gets the cooldowns which is a set of five cool down exercises after the main exercises are completed
     * @return cooldowns
     */
    public Exercise[] getCooldowns() {
        return cooldowns;
    }

    /**
     * This method gets the data manager which handles the workout and session arraylist
     * @return the data manager
     */
    public DataManager getDataManager() {
        return dataManager;
    }

    /**
     * This method starts a session
     * This method generates a random number of exercises between 1 and 30
     * @param workout which consist of the number of exercises, time for each, break time, and halftime break
     * @return the session which contains the array of workout and exercise.
     * It also has the local date time for the history
     */
    public  Session startSession(Workout workout) {
        Random rand = new Random();
        Exercise[] sessionExercises = new Exercise[workout.getExercises()];
        ArrayList<Exercise> tempList = new ArrayList<>();
        Collections.addAll(tempList, exercises);
        for(int i = 0; i < sessionExercises.length; i++) {
            int index = rand.nextInt(tempList.size());
            sessionExercises[i] = tempList.remove(index);
        }

        return new Session(sessionExercises, workout);
    }

    /**
     * This method adds the session to the data manager class
     * @param session which contains the array of workout and exercise.
     * It also has the local date time for the history
     */
    public void completeSession(Session session) {
        dataManager.addSession(session);
    }
}
