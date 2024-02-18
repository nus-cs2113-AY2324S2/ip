package joe.task;

public class Deadline extends Task {
    protected static final String DEADLINE_SYMBOL = "[D]";
    protected String finishBy;

    @Override
    public TaskType getTaskType() {
        return TaskType.DEADLINE;
    }

    public Deadline(String taskName, String finishBy) {
        super(taskName);
        this.finishBy = finishBy;
    }

    @Override
    public String getTaskStatus() {
        return DEADLINE_SYMBOL + super.getTaskStatus() + " (by: " + finishBy + ")";
    }
}
