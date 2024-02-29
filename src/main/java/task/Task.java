package task;

/**
 * Representation of a Task class.
 */

public class Task {
    public static final String DONE = "1";
    public static final String NOT_DONE = "0";

    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public void setDone(String status) {
        isDone = status.equals(DONE);
    }

    public void markAsDone() {
        this.isDone = true;
        System.out.println("\tNice! I've marked this task as done:");
        System.out.println("\t\t" + getTaskDetails() + "\n");
    }

    public void unmarkAsDone() {
        this.isDone = false;
        System.out.println("\tOK, I've marked this task as not done yet:");
        System.out.println("\t\t" + getTaskDetails() + "\n");
    }

    /**
     * Returns whether a task is done as a string.
     * @return "X" if task is done and " " if undone.
     */

    public String getStatusIcon() {
        return (this.isDone ? "X" : " ");
    }

    /**
     * Returns a string which contains the details of a task for printing.
     * @return details of a task in the correct format
     */

    public String getTaskDetails() {
        return "[T][" + this.getStatusIcon() + "] " + this.description;
    }

    public String getTaskAsString() {
        return "";
    }

    /**
     * Returns whether a task is done as an integer.
     * @return "1" if task is done and "0" if undone
     */

    public String getDoneAsInteger() {
        if (this.isDone) {
            return "1";
        }
        return "0";
    }

    public String getDescription() {
        return this.description;
    }
}
