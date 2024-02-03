package ToDoListFeature;

public abstract class ToDo {
    protected final String name;
    protected boolean isDone;

    /**
     * Constructor for ToDo
     * @param name the name of the ToDo
     */
    public ToDo(String name) {
        this.name = name;
        this.isDone = false;
    }

    /**
     * Get the name of the ToDo
     * @return the name of the ToDo
     */
    public String getName() {
        return this.name;
    }

    /**
     * Mark a task as done
     */
    public void mark() {
        this.isDone = true;
    }

    /**
     * Mark a task as undone
     */
    public void unmark() {
        this.isDone = false;
    }

    /**
     * Get the full String representation of the task
     * @return the full String of the task
     */
    @Override
    public String toString() {
        return isDone ? "[X] " + this.name : "[ ] " + this.name;
    }

}
