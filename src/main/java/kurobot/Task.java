package kurobot;

/**
 * Represent a task with name, task type and whether it is marked.
 * A [T][X] run object correspond to a marked todo task named "run".
 */
public class Task {
    protected String taskType;
    protected boolean isMarked;
    protected String taskName;
    protected int lineLen = 60;
    protected String lineBreak = "-".repeat(lineLen);

    /**
     * Store the name of the task, the type of the task, and the marking status.
     *
     * @param taskType Type of the task, eg todo or event.
     * @param taskName Name of the task.
     * @param isMarked Marked or unmarked.
     */
    public Task(String taskType, String taskName, Boolean isMarked) {
        this.taskType = taskType;
        this.isMarked = isMarked;
        this.taskName = taskName;
    }

    /**
     * Set the task to be marked and print out the current task.
     */
    public void mark() {
        this.isMarked = true;
        System.out.println(lineBreak);
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(this.printTask());
        System.out.println(lineBreak);
    }

    /**
     * Set the task to be unmarked and print out the current task.
     */
    public void unmark() {
        this.isMarked = false;
        System.out.println(lineBreak);
        System.out.println("OK, I've marked this task as not done yet:");
        System.out.println(this.printTask());
        System.out.println(lineBreak);
    }

    /**
     * Set the marking status of the task.
     * X indicates that the task is marked, white space means unmark.
     *
     * @return Symbol that represents the task is marked or unmark.
     */
    public String markStatus() {
        if (isMarked) {
            return "X";
        } else {
            return " ";
        }
    }

    public String getTaskName() {
        return taskName;
    }

    public String printTask() {
        return "[" + this.taskType + "][" + markStatus() + "] " + this.taskName;
    }
}
