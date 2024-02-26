package brad.tasks;

public class Deadline extends Tasks {
    private String time;
    public Deadline(String description, String time, boolean isDone) {
        super(description, isDone);
        this.time = time;
    }

    @Override
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
