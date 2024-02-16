package winter.task;

public class Event extends Task {
    private static final String indent = "   ";
    protected String startTime;
    protected String endTime;

    public Event (int order,boolean isMarked, String eventName, String startTime, String endTime) {
        super(order,isMarked,eventName);
        this.startTime = startTime;
        this.endTime = endTime;
    }

    @Override
    public String getEndTime() {
        return endTime;
    }
    @Override
    public String getStartTime() {
        return startTime;
    }
    @Override
    public String getType() {
        return "E";
    }

    @Override
    public String toString() {
        String typeCheckbox = "[E]";
        return indent + typeCheckbox + " " + this.doneCheckbox + this.taskName + " (from: " + this.startTime + " to: " + this.endTime + ")";
    }
}
