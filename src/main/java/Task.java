public class Task {
    private static int taskCounter = 0;
    protected String label;
    protected boolean isCompleted;

    public Task(String input) {
        taskCounter++;
        this.label = input;
        this.isCompleted = false;
    }

    public String getStatusIcon() {
        return (isCompleted ? "X" : " "); // mark done task with X
    }
    public static int enumerateTask() {
        return taskCounter;
    }

    public void setCompletedTrue() {
        this.isCompleted = true;
    }

    public void setCompletedFalse() {
        this.isCompleted = false;
    }

    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + label;
    }
}
