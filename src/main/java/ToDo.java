/**
 * Represents a ToDo task, a type of Task with no specific deadline.
 */
public class ToDo extends Task {

    /**
     * The dateOfDeadline for the ToDo task.
     */
    protected String dateOfDeadline;

    /**
     * Constructs a ToDo task with the specified description.
     *
     * @param description The description of the ToDo task.
     */
    public ToDo(String description) {
        super(description);
        typeOfTask = "T";
        this.description = description.replace("todo", "");
    }

    /**
     * Converts the ToDo task to a string format suitable for saving to a file.
     *
     * @return A string representing the ToDo task in file format.
     */
    @Override
    public String toFileString() {
        return "T|" + super.toFileString(); // Prefix with "T" to indicate Todo
    }

}