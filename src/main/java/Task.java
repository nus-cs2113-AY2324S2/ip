public class Task {
    private String name;
    private Boolean isDone;
    /**
     * used for casting back types
     * 0 for no specific types
     * 1 for Todo types
     * 2 for Event types
     * 3 for Deadline types
     */
    private int taskType;

    public void setName(String name) {
        this.name = name;
    }

    public void setIsDone(Boolean isDone) {
        this.isDone = isDone;
    }

    public void setTaskType(int taskType) {
        this.taskType = taskType;
    }

    public String getName() {
        return name;
    }

    public Boolean getIsDone() {
        return isDone;
    }

    public int getTaskType() {
        return taskType;
    }

    public void printTask() {
        System.out.println("[" + (isDone ? "X" : " ") + "] " + name);
    }
    public Task() {
        setName("task");
        setIsDone(false);
        taskType = 0;
    }

    public Task(String name, Boolean isDone) {
        setName(name);
        setIsDone(isDone);
    }
}
