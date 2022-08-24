import java.io.Serializable;
import java.util.Objects;

public class Set implements Serializable {
    private static final long serialVersionUID = 1L;
    private int numExercises;
    private int exerciseDuration;
    private int pauseDuration;
    private int halfTimeBreak;

    public Set(int numExercises, int exerciseDuration, int pauseDuration, int halfTimeBreak) {
        setNumExercises(numExercises);
        setExerciseDuration(exerciseDuration);
        setPauseDuration(pauseDuration);
        setHalfTimeBreak(halfTimeBreak);
    }

    public int getNumExercises() {
        return numExercises;
    }

    public void setNumExercises(int numExercises) {
        if(numExercises < 1 || numExercises > 30){
            throw new IllegalArgumentException(
                    "The number of exercises in a set must be more than zero and less than thirty one");
        }
        this.numExercises = numExercises;
    }

    public int getExerciseDuration() {
        return exerciseDuration;
    }

    public void setExerciseDuration(int exerciseDuration) {
        if(exerciseDuration < 1){
            throw new IllegalArgumentException(
                    "The exercise duration in a set must be more than zero");
        }
        this.exerciseDuration = exerciseDuration;
    }

    public int getPauseDuration() {
        return pauseDuration;
    }

    public void setPauseDuration(int pauseDuration) {
        if(pauseDuration < 0){
            throw new IllegalArgumentException("Pause duration in a set must be a non-negative value");
        }
        this.pauseDuration = pauseDuration;
    }

    public int getHalfTimeBreak() {
        return halfTimeBreak;
    }

    public void setHalfTimeBreak(int halfTimeBreak) {
        if(halfTimeBreak < 0){
            throw new IllegalArgumentException("Half time break in a set must be a non-negative value");
        }
        this.halfTimeBreak = halfTimeBreak;
    }

    //compare by all class variables or implement a unique ID for sets?
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Set set = (Set) o;
        return getNumExercises() == set.getNumExercises() &&
                getExerciseDuration() == set.getExerciseDuration() &&
                getPauseDuration() == set.getPauseDuration() &&
                getHalfTimeBreak() == set.getHalfTimeBreak();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getNumExercises(), getExerciseDuration(), getPauseDuration(), getHalfTimeBreak());
    }
}
