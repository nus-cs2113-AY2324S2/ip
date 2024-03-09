/**
 * This class represents a simple to-do task without any specific deadline or time frame.
 * It inherits from the `Task` class and does not add any additional fields.
 */
public class ToDo extends Task {

    /**
     * Constructor for the ToDo class.
     *
     * @param description the description of the task
     */
    public ToDo(String description) {
        super(description);
    }

    /**
     * Overrides the `toString` method from the `Task` class to provide a specific
     * string representation for to-do tasks, with a simpler format.
     *
     * @return the string representation of the to-do task
     */
    @Override
    public String toString() {
        return "[T]" + super.getStatusIcon() + " " + super.getDescription();
    }

    /**
     * Implements the `toFileString` method from the `Task` class to provide a string
     * representation suitable for storing the to-do task in a file.
     *
     * @return the string representation of the to-do task for file storage
     */
    @Override
    public String toFileString() {
        return "[T]" + super.getStatusIcon() + " " + super.getDescription();
    }
}
