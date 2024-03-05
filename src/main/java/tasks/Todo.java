package tasks;

/**
 * Represents a todo task in KikuBot.
 */
public class Todo extends Task {
    /**
     * Constructs a new Todo instance with the specified description.
     *
     * @param description The description of the todo task.
     */
    public Todo(String description) {
        super(description);
    }

    /**
     * Returns a string representation of the todo task formatted for printing,
     * including the type of the task and its status and description.
     *
     * @return A string representation of the todo task.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    /**
     * Returns a string representation of the todo task formatted for file storage,
     * including the type of the task and its status and description,
     * separated by a pipe character.
     *
     * @return A string formatted for saving the todo task to a file.
     */
    public String toFileFormat() {
        return "T | " + (isDone ? "1" : "0") + " | " + description;
    }
}
