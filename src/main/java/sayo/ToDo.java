package sayo;


/**
 * Represents a to-do task with a description.
 * Inherits from the Task class.
 */
public class ToDo extends Task {

    /**
     * Constructs a new ToDo with the given description.
     *
     * @param description The description of the to-do task.
     */
    public ToDo(String description) {
        super(description);
    }

    /**
     * Returns the string representation of the to-do task.
     * Prefixes the base task string representation with "[T]" to denote a to-do task.
     *
     * @return A string representation of the to-do task.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
    
    /**
     * Returns the string representation of the to-do task formatted for file storage.
     * Prefixes the base task file format with "T |" to denote a to-do task.
     *
     * @return A string formatted for saving to a file.
     */
    public String toFileFormat() {
        return "T | " + super.toFileFormat();
    }

    /**
     * Creates a ToDo object from a string format.
     * Parses the string based on the predefined format and creates a ToDo object accordingly.
     *
     * @param fileFormat A string representing the task in file format.
     * @return A new ToDo object populated with the data from the fileFormat string.
     */
    public static Task fromFileFormat(String fileFormat) {
        String[] parts = fileFormat.split(" \\| ");
        boolean isDone = parts[1].trim().equals("1");
        String description = parts[2].trim();

        ToDo todo = new ToDo(description);
        if (isDone) {
            todo.markAsDone();
        }
        return todo;
    }

}