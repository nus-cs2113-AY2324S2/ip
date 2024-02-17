public class Event extends Task {
    private String startTime;
    private String endTime;

    Event(String taskName, String startTime, String endTime) {
        super(taskName);
        this.startTime = startTime;
        this.endTime = endTime;
    }

    @Override
    String getSummary() {
        if (completed) {
            return "[E][X] " + taskName + " (from: " + startTime + " to: " + endTime + ")";
        } else {
            return "[E][ ] " + taskName + " (from: " + startTime + " to: " + endTime + ")";
        }
    }
}
