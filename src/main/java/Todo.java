/**
 * The Todo class represents a simple task without a specific deadline.
 * It is a subclass of the Task class.
 */
public class Todo extends Task {

    /**
     * Constructs a Todo object with the given description.
     *
     * @param description The description of the todo task.
     */
    public Todo(String description) {
        super(description);
    }

    /**
     * Returns a formatted string representation of the Todo object.
     *
     * @return A string representation of the Todo object.
     */
    @Override
    public String toString() {
        return "[T]"  +"[" + this.getStatusIcon()+ "] " + super.toString();
    }

    /**
     * Returns a formatted string representation of the Todo object for saving to a file.
     *
     * @return A string representation of the Todo object for saving to a file.
     */
    @Override
    public String toFileString() {
        return "T|" + super.toFileString(); // Prefix with "T" to indicate Todo
    }
}
