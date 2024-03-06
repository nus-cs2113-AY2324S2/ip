/**
 * Represents a task with a to-do item, with no specific time period.
 */
public class ToDo extends Task {

    /**
     * Constructs a ToDo object with a description specified by the user.
     *
     * @param description The description of the to-do task.
     */
    public ToDo(String description) {
        super(description);
        taskType = "T";
        this.description = description.replace("todo", "");
    }

    /**
     * Returns the string representation of the todo for being saved in a file.
     *
     * @return The string representation of the task for saving.
     */
    @Override
    public String saveTaskRepresentation() {
        return "T|" + super.saveTaskRepresentation(); // Prefix with "T" to indicate Todo
    }
}
