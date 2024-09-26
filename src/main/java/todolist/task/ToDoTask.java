package todolist.task;

public class ToDoTask extends Task {

    /**
     * Constructor for ToDoTask
     * @param name the name of the task
     */
    public ToDoTask(String name) {
        super(name);
    }

    /**
     * Constructor for ToDoTask
     * @param name the name of the task
     * @param isDone the status of the task
     */
    public ToDoTask(String name, boolean isDone) {
        super(name, isDone);
    }

    /**
     * Get the full String representation of the task
     * @return the full String of the task
     */
    @Override
    public String storeDataString() {
        return "T" + "|" + (this.isDone ? 1 : 0) + "|" + this.name;
    }

    /**
     * Get the full String representation of the task
     * @return the full String of the task
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
