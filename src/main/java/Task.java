public class Task {
    protected String description;
    protected boolean isDone;
    public static int noOfTasks = 0;
    public Task(String description) {
        this.description = description;
        this.isDone = false;
        noOfTasks += 1;
    }
    public String getStatusIcon() {
        return (isDone ? "X" : " "); //mark done task with "X"
    }
    public void markAsDone() {
        isDone = true;
    }
    public void markAsNotDone() {
        isDone = false;
    }
}
