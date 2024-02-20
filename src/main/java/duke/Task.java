package duke;

import java.util.List;

public abstract class Task {

    /** Description of task */
    protected String description;
    /** Boolean indicating if task is done */
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getDescription() {
        return description;
    }

    public boolean isDone() {
        return isDone;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setDone(boolean done) {
        this.isDone = done;
    }

    @Override
    public abstract String toString();

    public abstract List<TaskParams> findMissingParams();

    /**
     * Checks if the object has missing parameters.
     *
     * @return Boolean indicating if there are missing parameters.
     */
    public boolean hasMissingParams() {
        return (!this.findMissingParams().isEmpty());
    }
}
