public class Task {
    protected String description;
    protected boolean isDone;

    private static int numberOfTasks = 0;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
        if (description.equals("list") || description.contains("mark")) {
            return;
        }
        numberOfTasks += 1;
    }

    @Override
    public String toString() {
        return "[" + this.getStatusIcon()
                + "] " + this.getDescription();
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    public void markAsDone() {
        this.isDone = true;
    }

    public void markAsNotDone() {
        this.isDone = false;
    }

    public String getDescription() {
        return description;
    }

    public static int getNumberOfTasks() {
        return numberOfTasks;
    }
}