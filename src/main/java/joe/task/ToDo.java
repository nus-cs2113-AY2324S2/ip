package joe.task;

/**
 * ToDo subclass that inherits from Task
 */
public class ToDo extends Task {
    protected static final String TODO_SYMBOL = "[T]";

    @Override
    public TaskType getTaskType() {
        return TaskType.TODO;
    }

    public ToDo(String taskName) {
        super(taskName);
    }

    @Override
    public String getTaskStatus() {
        return TODO_SYMBOL + super.getTaskStatus();
    }
}
