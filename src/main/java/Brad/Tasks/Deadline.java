package Brad.Tasks;

public class Deadline extends Task {
    private String time;
    public Deadline (String description, String time) {
        super(description);
        this.time = time;
    }

    public String getTime() {
        return time;
    }

    @Override
    public String getFullDescription() {
        String output = "[D][";
        output += getIsDone() ? "X] " : " ] ";
        output += getTaskDescription();
        output += " (by: " + getTime() + ")";
        return output;
    }
}
