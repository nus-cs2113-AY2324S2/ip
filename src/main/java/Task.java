public class Task {
    private String description;
    private static int taskCount = 0;

    public Task(String description) {
        this.description = description;
    }

    public static int getTaskCount() {
        return taskCount;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
