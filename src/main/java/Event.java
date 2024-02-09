public class Event extends Task {
    private String time;
    public Event (String description, String time) {
        super(description);
        this.time = time;
    }

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
