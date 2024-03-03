import java.util.ArrayList;

/**
 * The Task class represents a task with a description, task type, and mark status.
 */
public class Task {
    private static ArrayList<Task> allTasks = new ArrayList<>();
    private static final String LINE = "____________________________________________________________";
    public String description;
    protected String taskType;
    protected boolean isDone;
    public String status;

    /**
     * Constructs a Task object with the specified description.
     * @param description Description of the task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
        this.taskType = "";
        allTasks.add(this);
    }

    /**
     * Retrieves all tasks stored in the TaskList.
     * @return ArrayList containing all tasks.
     */
    public static ArrayList<Task> getAllTasks() {
        return allTasks;
    }

    /**
     * Retrieves the task type.
     * @return Task type.
     */
    public String getTaskType() {
        return taskType;
    }

    /**
     * Retrieves the task mark.
     * @return Task mark.
     */
    public String getTaskMark() {
        return (isDone ? "[X]" : "[ ]");
    }

    /**
     * Marks the task as done.
     */
    public void markAsDone() {
        isDone = true;
    }

    /**
     * Marks the task as undone.
     */
    public void markAsUndone() {
        isDone = false;
    }

    /**
     * Converts input to a concise string.
     * @return The string representation of the task.
     */
    @Override
    public String toString() {
        return this.getTaskType() + this.getTaskMark() + " " + this.description;
    }

    /**
     * Converts input to a string for file storage.
     * @return The string representation for file storage.
     */
    public String toFileString() {
        return (status = isDone ? "1" : "0");
    }

    /**
     * Prints the task details along with the total number of tasks.
     * @param count Total number of tasks.
     */
    public void printTask(int count) {
        System.out.println(LINE + "\nGot it. I've added this task:");
        System.out.println("  " + this);
        System.out.println("Now you have " + (count) + " tasks in the list.\n" + LINE);

    }
}
