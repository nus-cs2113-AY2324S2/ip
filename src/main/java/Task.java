public abstract class Task {
    private static int taskCounter = Persistence.loadTasks(List.tasks);
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

    public void setCompleted(boolean value) {
        this.isCompleted = value;
    }
    public abstract String getType();
    public abstract String getLabel();
    public abstract String getRange();

    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + label;
    }
}
