package tasks;

/**
 * Represents a ToDo-type task to be tracked by Dor. Subclass of Task
 */
public class ToDo extends Task {

    /**
     * Constructor for ToDo when no task name or task done status is provided.
     * Invokes the superclass constructor with no parameters
     */
    public ToDo() {
        super();
    }

    /**
     * Constructor for ToDo when task name is provided but task done status is not.
     * Invokes the superclass constructor with one parameter
     *
     * @param name Name of the task
     */
    public ToDo(String name) {
        super(name);
    }

    /**
     * Constructor for ToDo when task name and task done status are provided.
     * Invokes the superclass constructor with two parameters
     *
     * @param name Name of the task
     * @param isDone Whether the task is done or not
     */
    public ToDo(String name, boolean isDone) {
        super(name, isDone);
    }

    /**
     * Returns the task-type "ToDo", represented by the String "T"
     *
     * @return String representation of the task being of "ToDo" type
     */
    @Override
    public String getType() {
        return "T";
    }

    /**
     * Returns a String representation of the task for printing
     *
     * @return String representation of the task
     */
    @Override
    public String toString() {
        return ("[T]" + super.toString());
    }
}
