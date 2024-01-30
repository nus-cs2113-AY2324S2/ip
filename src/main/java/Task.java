public class Task {
    protected String name;
    protected boolean isCompleted;
    private static int totalNumTasks = 0;

    public Task(String name) {
        this.name = name;
        this.isCompleted = false;
        totalNumTasks += 1;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isCompleted() {
        return isCompleted;
    }

    public void setCompleteness(boolean completed) {
        isCompleted = completed;
    }

    public static int getTotalNumTasks() {
        return totalNumTasks;
    }

    public String getStatusIcon() {
        return (isCompleted ? "X" : " ");
    }
}
