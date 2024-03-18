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

    public String getName(){
        return name;
    }

    //Methods
    public TaskType getTaskType () {
        return taskType;
    }

    /**
     * @return a CS representation of a Task object.
     */
    public String getCSV () {
        return (isDone ? "1" : "0") + "," + name;
    }

    /**
     * @return a String representation of a Task object.
     */
    public String toString () {
        return (isDone ? "[X] " : "[ ] ") + name;
    }
}
