package Tasks;

/**
 * The Todo class represents a simple todo task that extends the Task class.
 * It includes additional information about the task's duration.
 */
public class Todo extends Task {
    /**
     * Constructs an {@code Todo} task with specified description.
     *
     * @param description The description of the todoTask.
     */
    public Todo(String description) {
        super(description);
    }

    /**
     * Returns the status icon representing the completion status of the todo task.
     *
     * @return A string representing the status icon, including "[T]" for todo and "[X]" if the task is done.
     */
    @Override
    public String getStatusIcon() {
        return (isDone ? "[T][X] " + super.description: "[T][ ] " + super.description); // mark done task with X
    }
}
