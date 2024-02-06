public class Event extends Task{
    protected String startOfEvent;
    protected String endOfEvent;

    public Event(String description, String startOfEvent, String endOfEvent) {
        super(description);
        this.startOfEvent = startOfEvent;
        this.endOfEvent = endOfEvent;
        this.taskType = "[E]";
    }

    public String getStartOfEvent() {
        return startOfEvent;
    }

    public void setStartOfEvent(String startOfEvent) {
        this.startOfEvent = startOfEvent;
    }

    public String getEndOfEvent() {
        return endOfEvent;
    }

    public void setEndOfEvent(String endOfEvent) {
        this.endOfEvent = endOfEvent;
    }
    @Override
    public void printTask() {
        System.out.println(taskType + getStatusIcon() + " " + description + " (from: " + startOfEvent + " to: " + endOfEvent + ")");
    }
}
