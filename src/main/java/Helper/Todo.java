package Helper;

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
     * Returns a string representation of the Todo object.
     * The representation includes the task type identifier and description.
     *
     * @return A string representation of the todo object.
     */

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    /**
     * Returns a string representation of the Todo object in a format suitable for file storage.
     * The representation includes the task type identifier, status, and description.
     *
     * @return A string representation of the Todo object for file storage.
     */

    @Override
    public String toFileString() {
        return "T | " + (isDone ? "1" : "0") + " | " + description;
    }
}
