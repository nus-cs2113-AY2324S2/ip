package todolist.task;

public abstract class Task {
    protected final String name;
    protected boolean isDone;

    /**
     * Constructor for Task
     * @param name the name of the Task
     */
    public Task(String name) {
        this.name = name;
        this.isDone = false;
    }

    /**
     * Get the name of the Task
     * @return the name of the Task
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

    public abstract String storeDataString();

    /**
     * Get the full String representation of the task
     * @return the full String of the task
     */
    @Override
    public String toString() {
        return isDone ? "[X] " + this.name : "[ ] " + this.name;
    }

}
