package Tasks;

/**
 * The Task class represents a task with a description and completion status.
 * It serves as a base class for different subtasks such as Todo, Deadline, and Event.
 */
public class Task {
    public String description;
    protected boolean isDone;

    /** BADGE is the symbol representing each type of subtask */
    private final static char BADGE = 'X';

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    public void setDone(boolean done) {
        this.isDone = done;
    }

    public char getBadge() {
        return BADGE;
    }
}
