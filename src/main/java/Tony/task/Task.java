package Tony.task;

public class Task {
    public String description;
    protected boolean isDone;

    /**
     * Represents a {@code Task} object that saves all tasks
     * @param description is the String input from user
     */
    public Task(String description) {
        this.description = description.trim();
        this.isDone = false;
    }

    /**
     * Returns a String 'X' when task is marked done
     * @return a String based on boolean <code>isDone</code> to mark when a task is done
     */

    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    /**
     * Assigns boolean <code>isDone</code> to true when task is done.
     */
    public void markDone() {
        isDone = true;
    }
    /**
     * Assigns boolean <code>isDone</code> to false when task is not done.
     */
    public void markNotDone(){
        isDone = false;
    }

    /**
     * Returns formatted String to display task description in list
     * @return String of task description and with icon that identifies whether task is done.
     */
    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] "+ description;
    }
}
