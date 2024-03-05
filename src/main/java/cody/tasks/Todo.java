package cody.tasks;

/**
 * The Todo class represents a basic task without any additional time constraints.
 */
public class Todo extends Task {

    /**
     * Constructs a new Todo task with the specified description.
     *
     * @param description The description of the task.
     */
    public Todo (String description) {
        super(description);
    }

    /**
     * Returns the type of the task, which is "T" for Todo.
     *
     * @return The type of the task.
     */
    @Override
    public String getTaskType() {
        return "T";
    }
}
