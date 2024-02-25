package Casper;

/**
 * An abstract class representing a task in general (to be implemented further).
 */
public abstract class Task {
    protected String description;
    protected boolean isDone;
    private static final String SEPARATOR = "    _______________________________________________________________________";

    /**
     * Prints the message separated by bounding lines (only for other Task-like classes).
     */
    public static void wrapEchoMessage(String message){
        System.out.println(SEPARATOR);
        System.out.println(message);
        System.out.println(SEPARATOR);
    }

    public Task(String description){
        this.description = description;
        this.isDone = false;
    }

    /**
     * Gets the icon representing the task.
     *
     * @return A String representing the status (X represents a marked task).
     */
    public String getStatusIcon(){
        return (isDone ? "X" : " ");
    }

    /**
     * Change the given task's status as marked.
     */
    public void markTask(){
        this.isDone=true;
    }

    /**
     * Change the given task's status as unmarked.
     */
    public void unMarkTask(){
        this.isDone=false;
    }

    @Override
    public String toString() {
        return String.format("[%s] %s", getStatusIcon(), description);
    }

    public abstract String saveFormat();
}
