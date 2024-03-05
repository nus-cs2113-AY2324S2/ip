package task;

public class Event extends Task {
    private static final int TASK_NAME_INDEX = 0;
    private static final int START_DATE_INDEX = 1;
    private static final int END_DATE_INDEX = 2;
    private static final String TASK_TYPE = "[E]";
    private final String startTime;
    private final String dueTime;

    public Event(String[] eventInfo) {
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
    public boolean containsTime(String keyword) {
        return startTime.contains(keyword) || dueTime.contains(keyword);
    }

    /**
     * {@inheritDoc}
     * 
     * This method returns the string representation of the event
     * @return the string representation of the event
     */
    @Override
    public String toString() {
        String status = isDone ? DONE : IN_PROGRESS;
        return String.format("%s[%s] %s (from: %s to: %s)",
                TASK_TYPE, status, description, startTime, dueTime);
    }
}
