package task;

/**
 * Abstract Level of Task
 */
public abstract class Task {
    protected String description;
    /**
     * If this task is marked as done by the user
     */
    protected boolean isDone;
    /**
     * The type of Task
     */
    protected String identity;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getDescription() {
        return description;
    }

    public String getBy() {
        return null;
    }

    public String getFrom() {
        return null;
    }

    public String getTo() {
        return null;
    }

    public boolean getIsDone() {
        return isDone;
    }

    public String getStatusIcon() {
        return (isDone ? "[X]" : "[ ]");
    }

    @Override
    public String toString() {
        return description;
    }

    public void changeStatus(boolean status) {
        isDone = status;
    }

    public String getIdentity() {
        return identity;
    }
    public boolean getCanSearchDate() {
        return false;
    }

    public void setCanSearchDate(boolean canSearchDate){

    }
}
