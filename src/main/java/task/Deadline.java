package task;

public class Deadline extends Task {
    private final String dueTime;
    private static final int TASK_NAME_INDEX = 0;
    private static final int DUE_DATE_INDEX = 1;

    public Deadline(String[] deadlineInfo) {
        this(deadlineInfo[TASK_NAME_INDEX],
                deadlineInfo[DUE_DATE_INDEX]);
    }
    Deadline(String description, String dueTime) {
        super(description);
        this.dueTime = dueTime;
    }

    @Override
    public String toString() {
        String status = isDone ? "X" : " ";
        return String.format("[D][%s] %s (by: %s)",
                status, description, dueTime);
    }
}
