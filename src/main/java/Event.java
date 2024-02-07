public class Event extends Task {
    protected String startTime;
    protected String dueTime;
    private static final int TASK_NAME_INDEX = 0;
    private static final int START_DATE_INDEX = 1;
    private static final int END_DATE_INDEX = 2;

    Event(String[] eventInfo) {
        this(eventInfo[TASK_NAME_INDEX],
                eventInfo[END_DATE_INDEX],
                eventInfo[START_DATE_INDEX]);
    }
    Event(String description, String dueTime, String startTime) {
        super(description);
        this.startTime = startTime;
        this.dueTime = dueTime;
    }

    @Override
    public String toString() {
        String status = isDone ? "X" : " ";
        return String.format("[E][%s] %s (from: %s to: %s)",
                status, description, startTime, dueTime);
    }
}
