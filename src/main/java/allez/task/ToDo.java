package allez.task;

/**
 * ToDo are tasks without a time period designated.
 * Inherits Task class
 */
public class ToDo extends Task {

    public ToDo(String description) {
        super(description);
    }

    /**
     * Returns all the details of the task as a string.
     *
     * @return task type, task status and task description
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
