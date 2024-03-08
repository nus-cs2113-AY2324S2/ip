package daisy.task;

/**
 * The todo class handles the creation and saving formatting of todo tasks.
 */
public class Todo extends Task{

    /**
     * Creates a todo instance.
     * @param taskName the task name of the todo
     */
    public Todo(String taskName) {
        super(taskName);
    }

    /**
     * Returns a String representation of the todo in the format such that it is suitable for program printouts.
     * @return a String representation of the todo suitable for printing
     */
    public String toString() {
        return String.format("[T][%s] %s", (this.isDone)? "X":" ", this.taskName);
    }

    /**
     * Returns a String representation of the todo in the format such that it is suitable for loading and saving.
     * @return a String representation of the todo suitable for loading and saving
     */
    public String save() {
        return String.format("T,%s,%s", (this.isDone)? "1":"0", this.taskName);
    }

}
