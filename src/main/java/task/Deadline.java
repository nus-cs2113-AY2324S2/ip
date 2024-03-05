package task;

public class Deadline extends Task {
    private static final int TASK_NAME_INDEX = 0;
    private static final int DUE_DATE_INDEX = 1;
    private static final String TASK_TYPE = "[D]";
    private final String dueTime;

    public Deadline(String[] deadlineInfo) {
        this(deadlineInfo[TASK_NAME_INDEX],
                deadlineInfo[DUE_DATE_INDEX]);
    }

    Deadline(String description, String dueTime) {
        super(description);
        this.dueTime = dueTime;
    }

    @Override
    public boolean containsTime(String keyword) {
        return dueTime.contains(keyword);
    }

    /**
     * {@inheritDoc}
     * 
     * This method returns the string representation of the deadline
     * @return the string representation of the deadline
     */
    @Override
    public String toString() {
        String status = isDone ? DONE : IN_PROGRESS;
        return String.format("%s[%s] %s (by: %s)",
                TASK_TYPE, status, description, dueTime);
    }
}
