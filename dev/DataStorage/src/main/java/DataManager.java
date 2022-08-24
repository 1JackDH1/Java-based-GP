import javax.xml.crypto.Data;
import java.io.*;
import java.util.ArrayList;

public class DataManager implements Serializable {
    private static final long serialVersionUID = 3L;
    private ArrayList<Session> history;
    private ArrayList<Set> sets;

    private static final String FILENAME = "dataManager.ser";

    public DataManager() {
        history = new ArrayList<>();
        sets = new ArrayList<>();
    }

    public ArrayList<Session> getHistory() {
        return history;
    }

    public ArrayList<Set> getSets() {
        return sets;
    }

    public void save(){
        try{
            FileOutputStream outputFile = new FileOutputStream(FILENAME);
            ObjectOutputStream output = new ObjectOutputStream(outputFile);
            output.writeObject(this);
            output.close();
            outputFile.close();
        } catch(IOException e){
            e.printStackTrace();
        }
    }

    public static DataManager load(){
        DataManager dataManager = new DataManager();
        try{
            FileInputStream inputFile = new FileInputStream(FILENAME);
            ObjectInputStream input = new ObjectInputStream(inputFile);
            dataManager = (DataManager) input.readObject();
            input.close();
            inputFile.close();
        } catch(IOException | ClassNotFoundException e){
            e.printStackTrace();
            //add default sets
        }
        return dataManager;
    }
}
