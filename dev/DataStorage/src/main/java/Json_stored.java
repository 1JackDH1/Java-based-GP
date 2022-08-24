import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Class for sample code and experimentation
 * of JSON file storage by means of ObjectMapper (to parse JSON)
 * from the fasterxml.jackson.databind library (version 2.12.0)
 *
 * NOT IN USE
 */

public class Json_stored {
    /*
    public static void main(String[] args) {
        ArrayList<Exercise> completedExercises = new ArrayList<>();
        Exercise pushup = new Exercise("Pushup");
        Exercise squat = new Exercise("Squat");
        Exercise pullup = new Exercise("Pullup");
        completedExercises.add(pushup);
        completedExercises.add(squat);
        completedExercises.add(pullup);

        ObjectMapper mapper = new ObjectMapper();
        try{
            mapper.writeValue(new File("completed_exercises.json"), completedExercises);
        } catch(IOException e){
            e.printStackTrace();
        }

        ArrayList<Exercise> readFile = new ArrayList<>();
        try {
            readFile = mapper.readValue(Paths.get("completed_exercises.json").toFile(), new TypeReference<ArrayList<Exercise>>(){});
        } catch(IOException e){
            e.printStackTrace();
        }
        System.out.println(readFile.toString());
    }

     */
}