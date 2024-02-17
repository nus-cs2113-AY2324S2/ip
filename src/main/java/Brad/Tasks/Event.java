package Brad.Tasks;

public class Event extends Task {
    private String time;
    public Event (String description, String time, boolean isDone) {
        super(description, isDone);
        this.time = time;
    }

    @Override
    public String getTime() {
        return time;
    }

    @Override
    public String getFullDescription() {
        String output = "[E][";
        output += getIsDone() ? "X] " : " ] ";
        output += getTaskDescription();
        output += " (from: " + getTime() + ")";
        return output;
    }

}
