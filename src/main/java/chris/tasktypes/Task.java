package chris.tasktypes;

public class Task {
    private final String description;
    private boolean isDone;

    public Task(String description, boolean isDone) {
        this.description = description.trim();
        this.isDone = isDone;
    }

    /**
     * Returns a string to be used for displaying to user
     * @return string to be printed
     */
    public String toString() {
        return "[" + getStatusIcon() + "] " + description;
    }

    /**
     * Returns a string to be used for storage
     * @return string to be stored
     */
    public String saveString() { return description; }

    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    /**
     * Toggles isDone and returns isDone
     * @return whether task is completed
     */
    public boolean markTask() {
        isDone = !isDone;
        return isDone;
    }

    public String getDescription() {
        return description;
    }

}
