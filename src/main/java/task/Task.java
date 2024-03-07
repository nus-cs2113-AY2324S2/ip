package task;

import java.io.Serializable;

public abstract class Task implements Serializable {
    protected static final String DONE = "X";
    protected static final String IN_PROGRESS = " ";
    protected boolean isDone;
    protected final String description;

    public Task(String description) {
        this.isDone = false;
        this.description = description;
    }

    public void mark() {
        this.isDone = true;
    }

    public void unmark() {
        this.isDone = false;
    }

    /**
     * Checks if the description of the task contains the keyword.
     * 
     * @param keyword the keyword to be checked.
     * @return true if the task contains the keyword, false otherwise.
     */
    public boolean containsWord(String keyword) {
        return description.contains(keyword);
    }

    /**
     * Checks if the task contains the time keyword.
     * 
     * @param keyword the time keyword to be checked.
     * @return true if the task contains the keyword, false otherwise.
     */
    public boolean containsTime(String keyword) {
        return false;
    }

    /**
     * Generates a simplified string representation of the task for data saving.
     * 
     * @return a string representation of the task for data saving.
     */
    public abstract String toSave();

    /**
     * Checks if the task is marked.
     * 
     * @return true if the task is marked as done, false otherwise.
     */
    public boolean isMarked() {
        return isDone;
    }

    @Override
    public String toString() {
        String status = isDone ? DONE : IN_PROGRESS;
        return "[" + status + "] " + description;
    }
}
