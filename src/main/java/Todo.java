/**
 * Todo task class
 */
public class Todo extends Task {

    /**
     * @param description name of todo task to be added
     */
    public Todo(String description) {
        super(description);
    }

    /** @return the task type, completion status, name of todo */
    public String toString() {
        return "[T] " + getStatusIcon() + " " + super.toString();
    }

}
