import java.io.*;
import java.util.ArrayList;

/**
 * Class for sample code and experimentation
 * of Java serialization and deserialization.
 */

public class Serialised_stored {
    public static void serializeSession(Session session){
        try {
            FileOutputStream outputFile = new FileOutputStream("session.ser");
            ObjectOutputStream output = new ObjectOutputStream(outputFile);
            output.writeObject(session);
            output.close();
            outputFile.close();
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    public static Session deserializeSession(){
        Session session = new Session();
        try{
            FileInputStream inputFile = new FileInputStream("session.ser");
            ObjectInputStream input = new ObjectInputStream(inputFile);
            session = (Session) input.readObject();
        } catch (IOException | ClassNotFoundException e){
            e.printStackTrace();
        }
        return session;
    }

    public static void main(String[] args) {

        Exercise pushup = new Exercise("Pushup", "-", "abc");
        Exercise squat = new Exercise("Squat", "-", "bcd");
        Exercise pullup = new Exercise("Pullup", "-", "cde");
        /*
        //Serialization of arbitrary exercise objects
        try{
            FileOutputStream outputFile = new FileOutputStream("exercises.ser");
            ObjectOutputStream output = new ObjectOutputStream(outputFile);
            output.writeObject(pushup);
            output.writeObject(squat);
            output.writeObject(pullup);
            output.close();
            outputFile.close();
        } catch (IOException e){
            e.printStackTrace();
        }

        //Deserialization of arbitrary exercise objects
        Exercise first;
        Exercise second;
        Exercise third;
        //Exercise fourth = null;
        try{
            FileInputStream inputFile = new FileInputStream("exercises.ser");
            ObjectInputStream input = new ObjectInputStream(inputFile);
            first = (Exercise) input.readObject();
            second = (Exercise) input.readObject();
            third = (Exercise) input.readObject();
            //fourth = (Exercise) input.readObject();   throws EOFException
            input.close();
            inputFile.close();
        } catch (IOException | ClassNotFoundException e){
            e.printStackTrace();
            return;
        }
        System.out.println("First exercise in file: " + first.getName());
        System.out.println("Second exercise in file: " + second.getName());
        System.out.println("Third exercise in file: " + third.getName());

        //Serialize arraylist
        ArrayList<Exercise> exercises = new ArrayList<>();
        exercises.add(pushup);
        exercises.add(squat);
        exercises.add(pullup);
        try{
            FileOutputStream outputFile = new FileOutputStream("exercise_list.ser");
            ObjectOutputStream output = new ObjectOutputStream(outputFile);
            output.writeObject(exercises);
            output.close();
            outputFile.close();
        } catch (IOException e){
            e.printStackTrace();
        }

        //Deserialize arraylist
        ArrayList<Exercise> newList = new ArrayList<>();
        try{
            FileInputStream inputFile = new FileInputStream("exercise_list.ser");
            ObjectInputStream input = new ObjectInputStream(inputFile);
            newList = (ArrayList<Exercise>) input.readObject();
        } catch (IOException | ClassNotFoundException e){
            e.printStackTrace();
        }
        System.out.println(newList);
         */


        //Serialization and deserialization of session object
        Session testSession = new Session();
        serializeSession(testSession);

        Session newSession;
        newSession = deserializeSession();
        System.out.println(newSession);
    }
}
