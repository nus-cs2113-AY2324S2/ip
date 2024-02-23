import java.util.ArrayList;
public class Task {
    private static ArrayList<Task> allTasks = new ArrayList<>();
    private static final String LINE = "____________________________________________________________";
    public String description;
    protected String taskType;
    protected boolean isDone;
    public String status;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
        this.taskType = "";
        allTasks.add(this);
    }

    public static ArrayList<Task> getAllTasks() {
        return allTasks;
    }

    public String getTaskType() {
        return taskType;
    }

    public String getTaskMark() {
        return (isDone ? "[X]" : "[ ]");
    }
    public void markAsDone() {
        isDone = true;
    }

    public void markAsUndone() {
        isDone = false;
    }

    @Override
    public String toString() {
        return this.getTaskType() + this.getTaskMark() + " " + this.description;
    }

    public String toFileString() {
        return (status = isDone ? "1" : "0");
    }
    public void printTask(int count) {
        System.out.println(LINE + "\nGot it. I've added this task:");
        System.out.println("  " + this);
        System.out.println("Now you have " + (count + 1) + " tasks in the list.\n" + LINE);

    }
}