public class Exercise {
    private String name;
    private String description;
    private String animation;

    @Override
    public String toString() {
        return "Exercise{" +
                "name='" + name + '\'' +
                '}';
    }

    public Exercise(String name, String description, String animation){
        this.name = name;
        this.description = description;
        this.animation = animation;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAnimation() {
        return animation;
    }

    public void setAnimation(String animation) {
        this.animation = animation;
    }
}
