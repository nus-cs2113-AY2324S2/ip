package tasks;

/**
 * Represents a task to be added into an arraylist. It contains the description of the task and if it has been marked done
 */
public class Task{

    protected String description;
    protected boolean isDone;

    Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Retrieves description of task
     * @return description description of task
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * Marks task as done
     * @param done boolean value of marked/unmarked task
     */
    public void setDone(boolean done) {
        isDone = done;
    }

    /**
     * Overrides toString to print out task
     * @return done + description returns in formatted string
     */
    public String toString() {
        String done = "[ ] ";
        if (isDone) {
            done = "[X] ";
        }
        return done + description;
    }

}