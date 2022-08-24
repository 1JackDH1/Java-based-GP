import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;

public class Session implements Serializable {
    private static final long serialVersionUID = 2L;
    private transient String description;
    private transient Exercise[] exercises;
    private Set set;
    private LocalDateTime dateTime;

    //constructor for testing purposes
    public Session() {}

    public Session(Exercise[] exercises, Set set) {
        this.exercises = exercises;
        this.set = set;
        dateTime = LocalDateTime.now();
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Exercise[] getExercises() {
        return exercises;
    }

    public Set getSet() {
        return set;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    //total time in seconds in a session
    public int getTimeInSeconds() {
        return (set.getExerciseDuration() * set.getNumExercises()) +
                (set.getPauseDuration() * (set.getNumExercises() - 1)) + set.getHalfTimeBreak() + 360;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Session session = (Session) o;
        return Objects.equals(getDateTime(), session.getDateTime());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getDateTime());
    }

    //toString for testing purposes
    @Override
    public String toString() {
        return "Session{" +
                "number of exercises='" + set.getNumExercises() + '\'' +
                '}';
    }
}
