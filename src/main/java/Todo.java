/**
 * The Todo class represents a simple task without a specific deadline or start time.
 * It extends the Task class and includes functionality for storing the task's completion status.
 */
public class Todo extends Task {
    /**
     * Constructs a Todo object with the given description and completion status.
     *
     * @param description The description of the todo task.
     * @param isDone      The completion status of the todo task.
     */

    public Todo(String description, boolean isDone) {
        super(description, isDone);
        this.isDone = isDone;
    }

    /**
     * Returns the type identifier for the Todo task, which is "[T]".
     *
     * @return The type identifier for the Todo task.
     */
    public String getType() {
        return "[T]";
    }

    /**
     * Returns a string representation of the Todo object in a format suitable for saving to a file.
     *
     * @return A string representation of the Todo object in a format suitable for saving to a file.
     */
    @Override
    public String toSaveFormat() {
        return "T | " + (isDone ? "1" : "0") + " | " + description;
    }
}