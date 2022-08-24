import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class ExerciseApp {
    //private UserInterface userInterface;
    private Exercise[] exercises;
    private Exercise[] warmups;
    private Exercise[] cooldowns;
    private DataManager dataManager;

    //warmup and cooldown times measured in minutes
    public final int WARMUP_TIME = 3;
    public final int COOLDOWN_TIME = 3;

    public ExerciseApp(/* UserInterface userInterface */){
        //this.userInterface = userInterface;
        exercises = new Exercise[30];
        warmups = new Exercise[5];
        cooldowns = new Exercise[5];
        dataManager = DataManager.load();
    }

    private void createExercises(){
        //take the GIF files for animation
        //write out all 30 exercises
    }

    /*
    public UserInterface getUserInterface(){ return userInterface;}
     */

    public Exercise[] getExercises() {
        return exercises;
    }

    public Exercise[] getWarmups() {
        return warmups;
    }

    public Exercise[] getCooldowns() {
        return cooldowns;
    }

    public DataManager getDataManager() {
        return dataManager;
    }

    public boolean addSet(Set set){
        try{
            dataManager.getSets().add(set);
            return true;
        } catch(Exception e){
            return false;
        }
    }

    public boolean removeSet(Set set){
        try{
            dataManager.getSets().remove(set);
            return true;
        } catch(Exception e){
            return false;
        }
    }

    public void startSession(Set set){
        Session newSession = new Session(randomExerciseSelection(exercises, set.getNumExercises()), set);
        //start the session
    }

    public boolean completeSession(Session session){
        try {
            dataManager.getHistory().add(session);
            return true;
        } catch(Exception e){
            return false;
        }
    }

    public Exercise[] randomExerciseSelection(Exercise[] exercises, int numExercises){
        Random rand = new Random();
        Exercise[] sessionExercises = new Exercise[numExercises];
        ArrayList<Exercise> tempList = new ArrayList<>();
        Collections.addAll(tempList, exercises);
        for(int i = 0; i < numExercises; i++) {
            int index = rand.nextInt(tempList.size());
            sessionExercises[i] = tempList.get(index);
            tempList.remove(index);
        }
        return sessionExercises;
    }

    /*
    public void serializeArrayLists(){
        try{
            FileOutputStream outputFile = new FileOutputStream("listsData.ser");
            ObjectOutputStream output = new ObjectOutputStream(outputFile);
            output.writeObject(dataManager);
            output.close();
            outputFile.close();
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    public void deserializeArrayLists(){
        try{
            FileInputStream inputFile = new FileInputStream("listsData.set");
            ObjectInputStream input = new ObjectInputStream(inputFile);
            dataManager = (DataManager) input.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
     */
}
