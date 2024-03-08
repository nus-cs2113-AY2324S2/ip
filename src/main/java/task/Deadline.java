package task;

/**
 * The Deadline class represents a task that must finish before a specific date/time.
 */
public class Deadline extends Task {
    private static final int TASK_NAME_INDEX = 0;
    private static final int DUE_DATE_INDEX = 1;
    private static final String TASK_TYPE = "[D]";
    private final String dueTime;

    public Deadline(String[] deadlineInfo) {
        this(deadlineInfo[TASK_NAME_INDEX],
                deadlineInfo[DUE_DATE_INDEX]);
    }

    public Deadline(boolean isDone, String[] deadlineInfo) {
        this(deadlineInfo[TASK_NAME_INDEX],
                deadlineInfo[DUE_DATE_INDEX]);
        this.isDone = isDone;
    }

    private Deadline(String description, String dueTime) {
        super(description);
        this.dueTime = dueTime;
    }

    @Override
    public boolean containsTime(String keyword) {
        return dueTime.contains(keyword);
    }

    @Override
    public String toSave() {
        return "D | " + (isDone ? "1" : "0") + " | " + description + " | " + dueTime;
    }

    @Override
    public String toString() {
        String status = isDone ? DONE : IN_PROGRESS;
        return String.format("%s[%s] %s (by: %s)",
                TASK_TYPE, status, description, dueTime);
    }
}
