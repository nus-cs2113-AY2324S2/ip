package winter.task;

public class Event extends Task {
    protected String startTime;
    protected String endTime;

    public Event (int order,boolean marked, String eventName, String startTime, String endTime) {
        super(order,marked,eventName);
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
    public String displayCurrentTask () {
        String typeCheckbox = "[E]";
        return typeCheckbox + " " + this.doneCheckbox + this.taskName;
    }
}
