package task;

/**
 * This is an abstract class representing a task.
 */
public abstract class Task {
    private final String description;
    private boolean isDone;

    /**
     * This method is used to get the type of the task.
     * @return String This returns the type of the task(todo/event/deadline).
     */
    public abstract String getType();

    /**
     * This is the constructor of the Task class.
     * @param description This is the description of the task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public void markAsDone() {
        this.isDone = true;
    }

    public void markAsNotDone() {
        this.isDone = false;
    }

    /**
     * This method is used to get the description of the task.
     * @return String This returns the description of the task.
     */
    public String getDescription() {
        return description;
    }

    public boolean isDone() {
        return isDone;
    }

    /**
     * This method is used to get the string representation of the task.
     * @return String This returns the string representation of the task.
     */
    @Override
    public String toString() {
        String status = isDone ? "[X]" : "[ ]";
        return "[" + getType() + "]" + status + " " + description;
    }

    /**
     * This method is used to get the string representation of the task to be saved in the file.
     * @return String This returns the string representation of the task to be saved in the file.
     */
    public String saveString() {
        return getType() + " | " + (isDone ? "1" : "0") + " | " + description;
    }
}