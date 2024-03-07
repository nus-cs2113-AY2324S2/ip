package baymax;

/**
 * Represents a task containing its description and completion status.
 *
 */
public class Task {
    protected String description;
    protected boolean isDone;

    protected String type; // T or D or E


    /**
     * Constructs a new Task object with the given description
     * and completion status is set as not done.
     *
     * @param description The description of the task.
     */
     public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    public void markAsDone() {
        this.isDone = true;
    }

    public void unmarkDone() {
        this.isDone = false;
    }

    /**
     * Returns a string containing the task's completion status and description.
     *
     * @return String of the task.
     */
    public String toString() {
        return " [" +getStatusIcon() + "] " + description;
    }

    /**
     * Returns a string containing the task's completion status and description,
     * which is formatted to be stored in the baymax.txt file.
     *
     * @return String of the task formatted for file storage
     */
    public String toFileString() {
        return null;
    }

    /**
     * Returns a string containing the task's completion status and description,
     * which is formatted to be printed on the terminal.
     *
     * @return String of the task formatted for printing
     */
    public String toAddString() {
        return null;
    }

}
