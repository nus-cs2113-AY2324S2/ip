public class Task {
    //Attributes
    protected TaskType taskType;
    protected String name;
    protected boolean isDone;

    //Constructors
    Task (String name) {
        this.name = name;
        isDone = false;
        taskType = TaskType.TASK;

    }

    //Methods
    public TaskType getTaskType () {
        return taskType;
    }

    public String getCSV () {
        return (isDone ? "1" : "0") + "," + name;
    }

    public String toString () {
        return (isDone ? "[X] " : "[ ] ") + name;
    }
}
