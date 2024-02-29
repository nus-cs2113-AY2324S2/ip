package geepee.task;

public class Todo extends Task {

    /**
     * Initialises an instance of the Todo class with completion status as false.
     * Calls the Task constructor to initialise the task description and completion status.
     * 
     * @param description Description of todo task.
     */
    public Todo(String description) {
        super(description);
    }

    /**
     * Initialises an instance of the Todo class with a given completion status.
     * Calls the Task constructor to initialise the task description and completion status.
     * 
     * @param description Description of todo task.
     * @param isDone Completion status of task.
     */
    public Todo(String description, boolean isDone) {
        super(description);
        this.isDone = isDone;
    }

    public String toString() {
        return String.format("[T]" + super.toString());
    }

    /**
     * Returns a string containing details of the todo task for storage in the data file.
     */
    public String toFileFriendlyString() {
        return String.format("T;" + super.toFileFriendlyString());
    }
}
