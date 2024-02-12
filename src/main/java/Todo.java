public class Todo extends Task{
    protected String taskType;
    public Todo (String task, String taskType) {
        super(task);
        this.taskType = taskType;
    }

    @Override
    public String getTaskType() {
        return taskType;
    }
}
