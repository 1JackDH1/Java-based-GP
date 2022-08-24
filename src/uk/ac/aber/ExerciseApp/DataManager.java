package uk.ac.aber.ExerciseApp;

import java.io.*;
import java.util.ArrayList;

/**
 *This class handles the arrayList of
 * workout and arrayList of session
 *
 * serialVersionUID is made static and final as it is the version ID and deserialization requires this number to
 * make sure that the loaded class corresponds to the serialized object.
 *
 * @authors REDACTED 
 *
 * @version 1.0
 */
public class DataManager implements Serializable {
    /**
     * Serialization ID to prevent issues when using different versions of java.
     */
    private static final long serialVersionUID = 1L;
    private ArrayList<Workout> workouts;
    private ArrayList<Session> history;

    /**
     * The name of the file that will be used to save and load data from.
     */
    private static final String FILENAME = "dataManager.ser";

    /**
     * Constructor of DataManager
     * Creates a new workout and history
     * Tries to load the "dataManager.ser" file
     * Catches IOException, then print exception object and the line it occurs, then adds four new workouts
     * Catches ClassNotFoundException then print exception object and the line it occurs
     */
    public DataManager() {
        this.workouts = new ArrayList<>();
        this.history = new ArrayList<>();
        try {
            load();
        } catch (IOException e) {
            e.printStackTrace();
            generatePresets();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }


    /**
     * This method gets the workout
     * @return workout which consist of the number of exercises, time for each, breaks in between, and halftime break
     */
    public ArrayList<Workout> getWorkouts() {
        return workouts;
    }

    /**
     * This method adds a workout
     * @param workout specified workout
     * @return workout which consist of the number of exercises, time for each, breaks in between, and halftime break
     * @throws IllegalArgumentException if trying to add identical workout
     */
    public boolean addWorkout(Workout workout) throws IllegalArgumentException {
        for (Workout w : workouts) {
            if (w.equals(workout)) {
                throw new IllegalArgumentException("Attempted to add duplicate workout");
            }
        }
        return workouts.add(workout);
    }

    /**
     * This method removes a workout
     * @param workout specified workout
     * @return the workout which consist of the number of exercises, time for each, breaks in between, and halftime break
     */
    public boolean removeWorkout(Workout workout) {
        return workouts.remove(workout);
    }

    /**
     * This method gets the the arraylist of session
     * @return the arraylist of session
     */
    public ArrayList<Session> getHistory() {
        return history;
    }

    /**
     * This method adds a session
     * @param session specified session
     * @return the added session in the history
     */
    public boolean addSession(Session session) {
        return history.add(session);
    }

    /**
     * This method creates four new workout objects
     * They have preconfigured values for the number of exercises,
     * time for each exercise, the break time, and the half time break
     */
    private void generatePresets() {
        addWorkout(new Workout(5, 120, 10, 30));
        addWorkout(new Workout(10, 180, 15, 45));
        addWorkout(new Workout(20, 300, 10, 30));
        addWorkout(new Workout(30, 300, 30, 60));
    }


    /**
     * This method loads the data manager file "dataManager.ser"
     * @throws IOException If the file does not exist or is corrupted
     * @throws ClassNotFoundException If the class is not found
     */
    public void load() throws IOException, ClassNotFoundException {
        DataManager dataManager;
        ObjectInputStream input = new ObjectInputStream(new FileInputStream(FILENAME));
        dataManager = (DataManager) input.readObject();
        this.workouts = dataManager.workouts;
        this.history = dataManager.history;
        input.close();
    }

    /**
     * This method tries to save the data manager file "dataManager.ser"
     * Catches IOException, then print exception object and the line it occurs
     */
    public void save() {
        try {
            ObjectOutputStream output = new ObjectOutputStream(new FileOutputStream(FILENAME));
            output.writeObject(this);
            output.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
