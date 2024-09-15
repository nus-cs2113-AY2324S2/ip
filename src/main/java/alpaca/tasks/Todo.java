package alpaca.tasks;

/**
 * Represents a to-do task without a specific due date or time.
 */
public class Todo extends Task{

    /**
     * Constructs a {@code Todo} task with the specified description.
     *
     * @param description The text description of the to-do task.
     */
    public Todo(String description) {
        super(description);
    }

    /**
     * Returns a string representation of the to-do task, including its completion status and description.
     *
     * @return A string indicating the task is a to-do, its completion status, and its description.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    /**
     * Formats the to-do task for saving to a file, incorporating its type, completion status, and description.
     *
     * @return A string suitable for saving the to-do task to a file.
     */
    public String save() {
        return "T | " + IntIsDone + " | " + description;
    }
}
