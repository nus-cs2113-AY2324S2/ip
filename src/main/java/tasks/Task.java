package tasks;

/**
 * Represents a task to be tracked by Dor
 */
public abstract class Task {
    private String name;
    private boolean isDone;

    /**
     * Constructor for Task when no task name or task done status is provided
     */
    public Task() {
        this("placeholder");
        this.isDone = false;
    }

    /**
     * Constructor for Task when task name is provided but task done status is not
     *
     * @param name Name of the task
     */
    public Task(String name) {
        this.name = name;
        this.isDone = false;
    }

    /**
     * Constructor for Task when task name and task done status are provided
     *
     * @param name Name of the task
     * @param isDone Whether the task is done or not
     */
    public Task(String name, boolean isDone) {
        this.name = name;
        this.isDone = isDone;
    }

    /**
     * Returns the name of the task
     *
     * @return Task name
     */
    public String getName() {
        return name;
    }

    /**
     * Returns the done status of the task
     *
     * @return Task done status
     */
    public String getDoneStatus() {
        if (isDone) {
            return "X";
        }
        return " ";
    }

    /**
     * Marks a task as done
     */
    public void markDone() {
        isDone = true;
    }

    /**
     * Marks a task as not done
     */
    public void markNotDone() {
        isDone = false;
    }

    /**
     * Returns type of the task. Abstract method to be implemented by subclasses of Task
     */
    public abstract String getType();

    /**
     * Returns a String representation of the task for printing
     *
     * @return String representation of the task
     */
    @Override
    public String toString() {
        return ("[" + getDoneStatus() + "] " + this.name);
    }
}
