package brad.tasks;

public class Event extends Tasks {
    private String startTime;
    private String endTime;
    public Event(String description, String startTime, String endTime, boolean isDone) {
        super(description, isDone);
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public String getStartTime() {
        return startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    @Override
    public String getTime() {
        return getStartTime() + " to: " + getEndTime();
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
