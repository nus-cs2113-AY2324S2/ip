package Helper.TaskType;

import Helper.Task;

/**
 * The Todo class represents a task without any timeframe or deadline.
 * It inherits from the Task class and adds functionality specific to Todo tasks.
 */

public class Todo extends Task {

    /**
     * Constructs an Todo object with the given description and time frame.
     *
     * @param description Description of the todo task.
     */

    public Todo(String description) {
        super(description);
    }

    /**
     * {@inheritDoc}
     *
     * Overrides the parent method to include an identifier for Todo tasks.
     *
     * @return A string representation of the Todo object.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    /**
     *{@inheritDoc}
     *
     * Overrides the parent method to include a custom file representation for Todo tasks.
     * The representation includes the task type identifier, status, and description.
     *
     * @return A string representation of the Todo object for file storage.
     */
    @Override
    public String toFileString() {
        return "T | " + (isDone ? "1" : "0") + " | " + description;
    }
}
