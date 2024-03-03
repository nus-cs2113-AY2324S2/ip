/**
 * The Todo class represents a to-do task
 */
public class Todo extends Task {

    /**
     * Constructs a Todo object with the specified description.
     * @param description Description of the to-do task.
     * @throws IllegalArgumentException if the description is empty or contains only "todo."
     */
    public Todo(String description) {
        super(description);
        if (description.trim().equalsIgnoreCase("todo")) {
            throw new IllegalArgumentException("The description of a todo cannot be empty.");
        }
        this.description = description.substring(5).trim(); // Remove "todo" and trim
        this.taskType = "[T]";
    }

    /**
     * Constructs a Todo object with the specified mark status and description.
     * @param isDone Mark status of the to-do task.
     * @param description Description of the to-do task.
     */
    public Todo(boolean isDone, String description) {
        super(description);
        this.isDone = isDone;
        this.taskType = "[T]";
    }

    /**
     * Converts the to-do task to a string representation.
     * @return String representation of the to-do task.
     */
    @Override
    public String toString() {
        return super.toString();
    }

    /**
     * Converts the to-do task to a string representation for file storage.
     * @return String representation for file storage.
     */
    @Override
    public String toFileString() {
        return "T | " + super.toFileString();
    }
}
